/*
 * fitness.cuh
 *
 *  Created on: 2012-9-23
 *      Author: Alexander
 */

#ifndef FITNESS_CUH_
#define FITNESS_CUH_
#include "cuda_runtime.h"
#include "device_launch_parameters.h"
#include <stdlib.h>

int numofpopulation=20; //��Ⱥ��С
int numofnormalgenes=4; //�������
int numofhomeoticgenes=4;//ͬԴ�������
int lenofheader=7; //��ͨ����ͷ��
int lenofgene=15; //��ͨ���򳤶�
int lenofhometicgene=15;//ͬԴ���򳤶�
int lenofhometicheader=7; //ͬԴ����ͷ��
int row=21; //�������ݵ�����
int col=3; //�������ݵ�����
int numofVariable=2; //��������
int numofFun=6; //��������
int numofFunOp=2; //�������������
float M=10.0; //

float **data;//�������ݼ�
float *fitness;//��Ӧֵ
char *strVariable;//������
char *strFun;//������
char *dev_strVariable;
char *dev_strFun;
float *dev_data;
float *dev_fitness;
char *numofhometic;//����������Ӧֵ��ͬԴ������
float **fittedvalue;//����������Ӧֵ��ͬԴ�����Ӧ�����ֵ
char *dev_numofhometic;
float *dev_fittedvalue;

char **normalGenes;//��ͨ��������뺯��������
char **normalGenesIndex;
char *dev_normalGenes;
char *dev_normalGenesIndex;
char **homeoticGenes;
char **homeoticGenesIndex;
char *dev_homeoticGenes;
char *dev_homeoticGenesIndex;

size_t pitchNG;
size_t pitchNI;
size_t pitchHG;
size_t pitchHI;
size_t pitchDT;
size_t pitchFV;

int funcNum;//����������

void initcpu()
{
	fittedvalue=(float**)malloc(sizeof(float*)*numofpopulation);
	fittedvalue[0]=(float*)malloc(sizeof(float)*numofpopulation*row);
	for(int i=1;i<numofpopulation;i++)
		fittedvalue[i]=fittedvalue[i-1]+row;

	strFun=(char*)malloc(sizeof(char)*numofFun);//��̬�����ź���������
	strVariable=(char*)malloc(sizeof(char)*numofVariable);//��̬�����ű���������
	fitness=(float*)malloc(sizeof(float)*numofpopulation);//��̬������Ӧֵ���飬����Ϊ��Ⱥ��С
	numofhometic=(char*)malloc(sizeof(char)*numofpopulation);
}
void initgpu()
{
	//���豸Ϊ��ά�������ռ�
	cudaMallocPitch((void**)&dev_normalGenes,&pitchNG,lenofgene*numofnormalgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_normalGenesIndex,&pitchNI,lenofgene*numofnormalgenes*sizeof(char),numofpopulation);

	cudaMallocPitch((void**)&dev_data,&pitchDT,col*sizeof(float),row);

	cudaMallocPitch((void**)&dev_homeoticGenes,&pitchHG,lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_homeoticGenesIndex,&pitchHI,lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_fittedvalue,&pitchFV,row*sizeof(float),numofpopulation);
	//Ϊһά�������ռ�
	cudaMalloc((void**)&dev_fitness,numofpopulation*sizeof(float));
	cudaMalloc((void**)&dev_numofhometic,sizeof(char)*numofpopulation);
}

void freecpuandgpu()
{
	cudaFree(dev_normalGenes);
	cudaFree(dev_normalGenesIndex);
	cudaFree(dev_homeoticGenes);
	cudaFree(dev_homeoticGenesIndex);
	cudaFree(dev_fitness);
	//free(fitness);
	free(numofhometic);

	for(int i=0;i<numofpopulation;i++)//�ͷ�����Ŀռ�
	{
		free(normalGenes[i]);
		free(normalGenesIndex[i]);
		free(homeoticGenes[i]);
		free(homeoticGenesIndex[i]);
	}
	free(normalGenes);
	free(normalGenesIndex);
	free(homeoticGenes);
	free(homeoticGenesIndex);
	free(fittedvalue[0]);
	free(fittedvalue);
}
void cputogpu()//������cpu��gpu
{
	cudaMemcpy2D(dev_normalGenes,pitchNG,normalGenes[0],lenofgene*numofnormalgenes*sizeof(char),lenofgene*numofnormalgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
	cudaMemcpy2D(dev_normalGenesIndex,pitchNI,normalGenesIndex[0],lenofgene*numofnormalgenes,lenofgene*numofnormalgenes,numofpopulation,cudaMemcpyHostToDevice);

	cudaMemcpy2D(dev_data,pitchDT,data[0],col*sizeof(float),col*sizeof(float),row,cudaMemcpyHostToDevice);

	cudaMemcpy2D(dev_homeoticGenes,pitchHG,homeoticGenes[0],lenofhometicgene*numofhomeoticgenes*sizeof(char),lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
	cudaMemcpy2D(dev_homeoticGenesIndex,pitchHI,homeoticGenesIndex[0],lenofhometicgene*numofhomeoticgenes*sizeof(char),lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
}
void gputocpu()//������gpu��cpu
{
	cudaMemcpy(fitness,dev_fitness,sizeof(float)*numofpopulation,cudaMemcpyDeviceToHost);
	cudaMemcpy(numofhometic,dev_numofhometic,sizeof(char)*numofpopulation,cudaMemcpyDeviceToHost);
	cudaMemcpy2D(fittedvalue[0],row*sizeof(float),dev_fittedvalue,pitchFV,sizeof(float)*row,numofpopulation,cudaMemcpyDeviceToHost);
}

#endif /* FITNESS_CUH_ */
