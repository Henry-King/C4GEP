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
		result=a/b;break;//����bΪ0�����
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
thread=gene��block=individual;
�����������������Ⱥ��ģ,��С����������
*/
extern __shared__ float share[];//��̬���乲������
__global__ void kernel(char *normalgenes,char *normalgenesIndex,char *homeoticgenes,char *homeoticgenesIndex,float *data,
	size_t pitchNG,size_t pitchNI,size_t pitchHG,size_t pitchHI,size_t pitchDT,int rowofdata,int colofdata,float *fit,
	char *numofhometic,float *fittedvalue,size_t pitchFV,float *allarray,int lenofgene,int numofnormalgenes,
	int lenofhometicgene,int numofhomeoticgenes,float M,float accuracy)
{
	int bid=blockIdx.x;//����
	int tid=threadIdx.x;//����
	char *row_normalGenes=(char*)((char*)normalgenes+bid*pitchNG);//��ǰ����
	char *row_normalGenesIndex=(char*)((char*)normalgenesIndex+bid*pitchNI);//��ǰ������
	//row_normalGenes[tid]Ϊ����

	int indexofgene=tid*lenofgene;//��ǰ�������ʼλ��

	//��ȡ�������Ч����len
	int len=getgenelen(row_normalGenes,row_normalGenesIndex,indexofgene);
	//���㵱ǰ�������Ӧֵ
	//��Ϊ��̬
	float *fitness=(float*)share;

	for(int n=0;n<rowofdata;n++)
	{
		int l=len;
		float *row_data=(float*)((char*)data+n*pitchDT);
		if(len==0)
		{
			fitness[tid*rowofdata+n]=row_data[(int)row_normalGenesIndex[indexofgene]];//��tid�������n���������ݵ����ֵ
		}
		else
		{
			for(int i=len;i>=0;i--)
			{
				if((int)row_normalGenes[indexofgene+i]==1)//�Ǻ���
				{
					//��ֵ
					if((int)row_normalGenes[indexofgene+l]==0)
						allarray[lenofgene*rowofdata*bid*tid+n*lenofgene+l]=row_data[(int)row_normalGenesIndex[indexofgene+l]];
					if((int)row_normalGenes[indexofgene+l-1]==0)
						allarray[lenofgene*rowofdata*bid*tid+n*lenofgene+l-1]=row_data[(int)row_normalGenesIndex[indexofgene+l-1]];
					//����
					allarray[lenofgene*rowofdata*bid*tid+n*lenofgene+i]=opFun((int)row_normalGenesIndex[indexofgene+i],allarray[lenofgene*rowofdata*bid*tid+n*lenofgene+l],allarray[lenofgene*rowofdata*bid*tid+n*lenofgene+l-1]);
					l-=getfunNum((int)row_normalGenesIndex[indexofgene+i]);
				}
			}
			fitness[tid*rowofdata+n]=allarray[lenofgene*rowofdata*bid*tid+n*lenofgene+0];//��tid�������n�����ݶ�Ӧ�����ֵ
			//cuPrintf("%f\n",fitness[tid*rowofdata+n]);
		}
	}
	//////////////////////////////////////////////////////////////////////////
	//����ͬԴ����thread=hometicgene;

	__syncthreads();
	char *row_hometicgene=(char*)((char*)homeoticgenes+bid*pitchHG);
	char *row_hometicgeneindex=(char*)((char*)homeoticgenesIndex+bid*pitchHI);
	int indexofhometicgene=tid*lenofhometicgene;//��ǰͬԴ�������ʼλ��
	//��ȡͬԴ������Ч����
	len=getgenelen(row_hometicgene,row_hometicgeneindex,indexofhometicgene);
	//������Ӧֵ
	int lenofhometic=rowofdata*numofnormalgenes;
	float *fitness2=(float*)&fitness[lenofhometic];
	for(int n=0;n<rowofdata;n++)
	{
		int l=len;
		if(len==0)
			fitness2[tid*rowofdata+n]=fitness[rowofdata*(int)row_hometicgeneindex[indexofhometicgene]+n];
		else
		{
			for(int i=len;i>=0;i--)
			{
				if(row_hometicgene[indexofhometicgene+i]==1)//�Ǻ���
				{
					if(row_hometicgene[indexofhometicgene+l]==2)
						allarray[lenofhometicgene*rowofdata*bid*tid+n*lenofhometicgene+l]=fitness[rowofdata*(int)row_hometicgeneindex[indexofhometicgene+l]+n];
					if(row_hometicgene[indexofhometicgene+l-1]==2)
						allarray[lenofhometicgene*rowofdata*bid*tid+n*lenofhometicgene+l-1]=fitness[rowofdata*(int)row_hometicgeneindex[indexofhometicgene+l-1]+n];
					allarray[lenofhometicgene*rowofdata*bid*tid+n*lenofhometicgene+i]=opFun(row_hometicgene[indexofhometicgene+i],allarray[lenofhometicgene*rowofdata*bid*tid+n*lenofhometicgene+l],allarray[lenofhometicgene*rowofdata*bid*tid+n*lenofhometicgene+l-1]);//sum2[n][l],sum2[n][l-1]);
					l=l-getfunNum((int)row_hometicgene[indexofhometicgene+i]);
				}
			}
			fitness2[tid*rowofdata+n]=allarray[lenofhometicgene*rowofdata*bid*tid+n*lenofhometicgene+0];//sum2[n][0];
			//cuPrintf("%f\n",fitness2[tid*rowofdata+n]);
		}
	}
	//////////////////////////////////////////////////////////////////////////
	__syncthreads();
	//__shared__ float fitness3[numofhomeoticgenes];//ÿ��ͬԴ��������ֵ
	//��Ϊ��̬����
	int lenoffit3=rowofdata*numofhomeoticgenes;
	float *fitness3=(float*)&fitness2[lenoffit3];
	float sumoffitness=0;
	for(int n=0;n<rowofdata;n++)
	{
		float *row_data=(float*)((char*)data+n*pitchDT);
		float result=fabs((fitness2[tid*rowofdata+n]-row_data[colofdata-1]));
		result=fabs(M-result);
		if(result<accuracy||result>=M*rowofdata)
			result=0;
		sumoffitness=sumoffitness+result;
	}
	if(isnan(sumoffitness)==true)
		fitness3[tid]=0;
	if(sumoffitness>M*rowofdata)
		sumoffitness=0;
	else
		fitness3[tid]=sumoffitness;
	__syncthreads();

	if(tid==0)
	{
		float max=0.0f;
		int index=0;
		for(int i=0;i<numofhomeoticgenes;i++)
		{
			if(max<fitness3[i])
			{
				max=fitness3[i];
				index=i;
			}
		}
		numofhometic[bid]=(char)index;//��¼ͬԴ����ı��
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
