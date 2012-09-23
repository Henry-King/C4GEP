/*
 * kernel.cuh
 *
 *  Created on: 2012-9-23
 *      Author: Alexander
 */

#ifndef KERNEL_CUH_
#define KERNEL_CUH_
//类测试
#include "fitness.cuh"
#include <float.h>
#include <math.h>
#include "device_launch_parameters.h"

__device__ int getfunNum(int i)
{
	int x;
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
	case 4://'sin'
		x=1;break;
	case 5://'cos'
		x=1;break;
	default:
		x=0;break;
	}
	return x;
}
__device__ float opFun(int op,float a,float b)
{
	float result;
	switch(op)
	{
	case 0:
		result=a+b;break;
	case 1:
		result=a-b;break;
	case 2:
		result=a*b;break;
	case 3:
		result=a/b;break;//处理b为0的情况
	case 4:
		result=sinf(a);break;
	case 5:
		result=cosf(a);break;
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
	int lenofhometicgene,int numofhomeoticgenes,float M)
{
	int bid=blockIdx.x;//个体
	int tid=threadIdx.x;//基因
	char *row_normalGenes=(char*)((char*)normalgenes+bid*pitchNG);//当前个体
	char *row_normalGenesIndex=(char*)((char*)normalgenesIndex+bid*pitchNI);//当前个体标记
	//row_normalGenes[tid]为基因

	int indexofgene=tid*lenofgene;//当前基因的起始位置

	//获取基因的有效长度len
	int len=getgenelen(row_normalGenes,row_normalGenesIndex,indexofgene);
	//计算当前基因的适应值
	float *fitness=(float*)share;
	//改为动态
	for(int n=0;n<rowofdata;n++)
	{
		int l=len;
		float *row_data=(float*)((char*)data+n*pitchDT);
		if(len==0)
		{
			fitness[n*numofnormalgenes+tid]=row_data[(int)row_normalGenesIndex[indexofgene]];
		}
		else
		{
			for(int i=len;i>=0;i--)
			{
				if((int)row_normalGenes[indexofgene+i]==1)//是函数
				{
					//赋值
					if((int)row_normalGenes[indexofgene+l]==0)
						allarray[rowofdata*bid+n*lenofgene+l]=row_data[(int)row_normalGenesIndex[indexofgene+l]];
					if((int)row_normalGenes[indexofgene+l-1]==0)
						allarray[rowofdata*bid+n*lenofgene+l-1]=row_data[(int)row_normalGenesIndex[indexofgene+l-1]];
					//计算
					allarray[rowofdata*bid+n*lenofgene+i]=opFun((int)row_normalGenesIndex[indexofgene+i],allarray[rowofdata*bid+n*lenofgene+l],allarray[rowofdata*bid+n*lenofgene+l-1]);
					l-=getfunNum((int)row_normalGenesIndex[indexofgene+i]);
				}
			}
			fitness[n*numofnormalgenes+tid]=allarray[rowofdata*bid+n*lenofgene+0];

		}
	}
	//////////////////////////////////////////////////////////////////////////
	//加入同源基因thread=hometicgene;
	__syncthreads();
	char *row_hometicgene=(char*)((char*)homeoticgenes+bid*pitchHG);
	char *row_hometicgeneindex=(char*)((char*)homeoticgenesIndex+bid*pitchHI);
	int indexofhometicgene=tid*lenofhometicgene;//当前同源基因的起始位置
	//获取同源基因有效长度
	len=getgenelen(row_hometicgene,row_hometicgeneindex,indexofhometicgene);
	//计算适应值
	int lenofhometic=rowofdata*numofnormalgenes;
	float *fitness2=(float*)&share[lenofhometic];

	for(int n=0;n<rowofdata;n++)
	{
		int l=len;
		if(len==0)
		fitness2[n*numofhomeoticgenes+tid]=fitness[n*numofnormalgenes+(int)row_hometicgeneindex[indexofhometicgene]];
		else
		{
			for(int i=len;i>=0;i--)
			{
				if(row_hometicgene[indexofhometicgene+i]==1)//是函数
				{
					if(row_hometicgene[indexofhometicgene+l]==2)
						allarray[rowofdata*bid+n*lenofhometicgene+l]=fitness[n*numofnormalgenes+(int)row_hometicgeneindex[indexofhometicgene+l]];
					if(row_hometicgene[indexofhometicgene+l-1]==2)
						allarray[rowofdata*bid+n*lenofhometicgene+l-1]=fitness[n*numofnormalgenes+(int)row_hometicgeneindex[indexofhometicgene+l-1]];
					allarray[rowofdata*bid+n*lenofhometicgene+i]=opFun(row_hometicgene[indexofhometicgene+i],allarray[rowofdata*bid+n*lenofhometicgene+l],allarray[rowofdata*bid+n*lenofhometicgene+l-1]);//sum2[n][l],sum2[n][l-1]);
				/*	if(isnan(sum2[n][i])==true)
					{
						sum2[n][0]=sum2[n][i];
						break;
					}*/
					l-=getfunNum((int)row_hometicgene[indexofhometicgene+i]);
				}
			}
			fitness2[n*numofhomeoticgenes+tid]=allarray[rowofdata*bid+n*lenofhometicgene+0];//sum2[n][0];
		}
	}
	//////////////////////////////////////////////////////////////////////////
	__syncthreads();
	//__shared__ float fitness3[numofhomeoticgenes];//每个同源基因的拟合值
	//改为动态数组
	int lenoffit3=lenofhometic+rowofdata*numofhomeoticgenes;
	float *fitness3=(float*)&share[lenoffit3];
	float sumoffitness=0;
	for(int n=0;n<rowofdata;n++)
	{
		float *row_data=(float*)((char*)data+n*pitchDT);
		sumoffitness+=fabs(M-fabs((fitness2[n*numofhomeoticgenes+tid]-row_data[colofdata-1])));
	}
	if(isnan(sumoffitness)==true)
		fitness3[tid]=0;
	else
		fitness3[tid]=sumoffitness;
	__syncthreads();

	if(tid==0)
	{
		float max=0;
		int index=0;
		for(int i=0;i<numofhomeoticgenes;i++)
		{
			if(max<fitness3[i])
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
			row_fv[i]=fitness2[i*numofhomeoticgenes+index];
		}
	}
}
void freecpu()
{
	free(fitness);
}
float *exekernel()
{
	cout<<"initialing cpu......"<<endl;
	initcpu();
	cout<<"initialing data......"<<endl;
	initdata();

	print();

	cout<<"initialing gpu......"<<endl;
	initgpu();
	cputogpu();
	int threadsPerBlock=(numofnormalgenes>numofhomeoticgenes?numofnormalgenes:numofhomeoticgenes);
	int blocksPerGrid=numofpopulation;
	cudaEvent_t start,stop;
	cudaEventCreate(&start);
	cudaEventCreate(&stop);
	cudaEventRecord(start,0);
	//cudaPrintfInit();
	//设置share memory的大小
	size_t share_size=(row*numofnormalgenes+row*numofhomeoticgenes+numofhomeoticgenes)*sizeof(float);
	//分配非share动态数组的大小
	int sizeofarray=row*lenofgene*numofpopulation;
	float *dev_array;
	cudaMalloc((void**)&dev_array,sizeof(float)*sizeofarray);

	kernel<<<blocksPerGrid,threadsPerBlock,share_size>>>(dev_normalGenes,dev_normalGenesIndex,dev_homeoticGenes,dev_homeoticGenesIndex,dev_data,pitchNG,pitchNI,pitchHG,pitchHI,pitchDT,row,col,dev_fitness,dev_numofhometic,dev_fittedvalue,pitchFV,dev_array,lenofgene,numofnormalgenes,lenofhometicgene,numofhomeoticgenes,M);
//	cudaPrintfDisplay(stdout,true);
	//cudaPrintfEnd();

	cudaEventRecord(stop,0);
	cudaEventSynchronize(stop);
	float elapsedtime;
	cudaEventElapsedTime(&elapsedtime,start,stop);
	printf("%f\n",elapsedtime);

	gputocpu();
	cout<<endl;
	for(int i=0;i<numofpopulation;i++)
		cout<<fitness[i]<<" ";
	cout<<endl;
	cout<<"the number of best hometic"<<endl;
	for(int i=0;i<numofpopulation;i++)
		cout<<(int)numofhometic[i]<<" ";
	cout<<endl;
	cout<<"fittedvalue......"<<endl;
	for(int i=0;i<numofpopulation;i++)
	{
		for(int j=0;j<row;j++)
			cout<<fittedvalue[i][j]<<" ";
		cout<<endl;
	}
	cout<<endl;
	cout<<"free cpu and gpu......"<<endl;
	cudaFree(dev_array);
	freecpuandgpu();
	cout<<"end..."<<endl;
	return fitness;
}




#endif /* KERNEL_CUH_ */
