/*
 * cudaKernel.cuh
 *
 *  Created on: 2012-10-6
 *      Author: Alexander
 */

#ifndef CUDAKERNEL_CUH_
#define CUDAKERNEL_CUH_
#include <float.h>
#include "device_launch_parameters.h"
__device__ int getfunNum(int i)
{
	int x=0;
	switch(i)
	{
	case 0://'+'
		x=2;break;
	case 1://'-'
		x=2;break;
	case 2://'*'
		x=2;break;
	case 3://'/'
		x=2;break;
	default:
		x=0;break;
	}
	return x;
}
__device__ float opFun(int op,float a,float b)
{
	float result=0;
	switch(op)
	{
	case 0:
		result=a+b;break;
	case 1:
		result=b-a;break;
	case 2:
		result=a*b;break;
	case 3:
		result=b/a;break;//处理b为0的情况
	}
	return result;
}
__device__ int getgenelen(char *genes,char *geneindex,int i)
{
	int j=0,len=0;
	while(j<=len)
	{
		if(genes[i+j]==1)
		{
			int k=getfunNum(geneindex[i+j]);
			len+=k;
		}
		j++;
	}
	return len;
}
//////////////////////////////////////////////////////////////////////////
/*
thread=gene，block=individual;
可以增大基因数和种群规模,缩小测试数据量
*/
extern __shared__ float share[];//动态分配共享数组
__global__ void kernel(char *normalgenes,char *normalgenesIndex,char *homeoticgenes,char *homeoticgenesIndex,float *data,
	size_t pitchNG,size_t pitchNI,size_t pitchHG,size_t pitchHI,size_t pitchDT,int rowofdata,int colofdata,float *fit,
	char *numofhometic,float *fittedvalue,size_t pitchFV,float *allarray,int lenofgene,int numofnormalgenes,
	int lenofhometicgene,int numofhomeoticgenes,float M,float accuracy)
{
	int bid=blockIdx.x;//个体
	int tid=threadIdx.x;//基因
	char *row_normalGenes=(char*)((char*)normalgenes+bid*pitchNG);//当前个体
	char *row_normalGenesIndex=(char*)((char*)normalgenesIndex+bid*pitchNI);//当前个体标记
	//row_normalGenes[tid]为基因
	float *fitness=(float*)share;
	int lenofhometic=rowofdata*numofnormalgenes;
	float *fitness2=(float*)&fitness[lenofhometic];
	int lenoffit3=rowofdata*numofhomeoticgenes;
	float *fitness3=(float*)&fitness2[lenoffit3];

	int len;
	if (tid<numofnormalgenes) {
		int indexofgene=tid*lenofgene;//当前基因的起始位置
		len=getgenelen(row_normalGenes,row_normalGenesIndex,indexofgene);
		for(int n=0;n<rowofdata;n++)
		{
			int l=len;
			float *row_data=(float*)((char*)data+n*pitchDT);
			if(len==0)
			{
				fitness[tid*rowofdata+n]=row_data[(int)row_normalGenesIndex[indexofgene]];//第tid个基因第n个测试数据的拟合值
				break;
			}
			else
			{
				int index_all=lenofgene*rowofdata*bid*tid+tid*rowofdata*lenofgene+n*lenofgene;
				for(int i=len;i>=0;i--)
				{
					if((int)row_normalGenes[indexofgene+i]==1)//是函数
					{
						//赋值
						if((int)row_normalGenes[indexofgene+l]==0)
							allarray[index_all+l]=row_data[(int)row_normalGenesIndex[indexofgene+l]];
						if((int)row_normalGenes[indexofgene+l-1]==0)
							allarray[index_all+l-1]=row_data[(int)row_normalGenesIndex[indexofgene+l-1]];
						//计算
						allarray[index_all+i]=opFun((int)row_normalGenesIndex[indexofgene+i],allarray[index_all+l],allarray[index_all+l-1]);
						l-=getfunNum((int)row_normalGenesIndex[indexofgene+i]);
					}
				}
				fitness[tid*rowofdata+n]=allarray[index_all];//第tid个基因第n个数据对应的拟合值
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////
	//加入同源基因thread=hometicgene;

	__syncthreads();
	if (tid<numofhomeoticgenes) {
		char *row_hometicgene=(char*)((char*)homeoticgenes+bid*pitchHG);
		char *row_hometicgeneindex=(char*)((char*)homeoticgenesIndex+bid*pitchHI);
		int indexofhometicgene=tid*lenofhometicgene;//当前同源基因的起始位置
		//获取同源基因有效长度
		len=getgenelen(row_hometicgene,row_hometicgeneindex,indexofhometicgene);
		//计算适应值
		for(int n=0;n<rowofdata;n++)
		{
			int l=len;
			if(len==0)
			{
				fitness2[tid*rowofdata+n]=fitness[rowofdata*(int)row_hometicgeneindex[indexofhometicgene]];
				break;
			}
			else
			{
				int index_all=lenofhometicgene*rowofdata*bid*tid+tid*rowofdata*lenofhometicgene+n*lenofhometicgene;
				for(int i=len;i>=0;i--)
				{
					allarray[index_all+i]=0;
					if(row_hometicgene[indexofhometicgene+i]==1)//是函数
					{
						if(row_hometicgene[indexofhometicgene+l]==2)
							allarray[index_all+l]=fitness[rowofdata*(int)row_hometicgeneindex[indexofhometicgene+l]+n];
						if(row_hometicgene[indexofhometicgene+l-1]==2)
							allarray[index_all+l-1]=fitness[rowofdata*(int)row_hometicgeneindex[indexofhometicgene+l-1]+n];
						allarray[index_all+i]=opFun(row_hometicgene[indexofhometicgene+i],allarray[index_all+l],allarray[index_all+l-1]);//sum2[n][l],sum2[n][l-1]);
						l=l-getfunNum((int)row_hometicgene[indexofhometicgene+i]);
					}
				}
				fitness2[tid*rowofdata+n]=allarray[index_all];//sum2[n][0];
			}
		}
	}
		//////////////////////////////////////////////////////////////////////////
		//__shared__ float fitness3[numofhomeoticgenes];//每个同源基因的拟合值
		//改为动态数组
	__syncthreads();
	if (tid<numofhomeoticgenes) {
		float sumoffitness=0;
		for(int n=0;n<rowofdata;n++)
		{
			float *row_data=(float*)((char*)data+n*pitchDT);
			float result=fabs((fitness2[tid*rowofdata+n]-row_data[colofdata-1]));
			result=fabs(M-result);
			if(result<=accuracy||result>1.0*M*rowofdata)
				result=0;
			sumoffitness=sumoffitness+result;
		}
		if(isnan(sumoffitness)==true||sumoffitness>1.0*M*rowofdata)
			sumoffitness=0;
		fitness3[tid]=sumoffitness;
	}
	__syncthreads();

	if(tid==0)
	{
		float max=0.0f;
		int index=0;
		float ss=1.0*M*rowofdata;
		for(int i=0;i<numofhomeoticgenes;i++)
		{
			if(fitness3[i]<ss&&max<=fitness3[i])
			{
				max=fitness3[i];
				index=i;
			}
		}
		numofhometic[bid]=(char)index;//记录同源基因的编号
		fit[bid]=max;
		float *row_fv=(float*)(((char*)fittedvalue)+(bid*pitchFV));
		for(int i=0;i<rowofdata;i++)
		{
			row_fv[i]=fitness2[index*numofhomeoticgenes+i];
		}
	}
	__syncthreads();
}

#endif /* CUDAKERNEL_CUH_ */
