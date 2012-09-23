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

int numofpopulation=20; //种群大小
int numofnormalgenes=4; //基因个数
int numofhomeoticgenes=4;//同源基因个数
int lenofheader=7; //普通基因头长
int lenofgene=15; //普通基因长度
int lenofhometicgene=15;//同源基因长度
int lenofhometicheader=7; //同源基因头长
int row=21; //测试数据的行数
int col=3; //测试数据的列数
int numofVariable=2; //变量个数
int numofFun=6; //函数个数
int numofFunOp=2; //最大函数操作个数
float M=10.0; //

float **data;//测试数据集
float *fitness;//适应值
char *strVariable;//变量集
char *strFun;//函数集
char *dev_strVariable;
char *dev_strFun;
float *dev_data;
float *dev_fitness;
char *numofhometic;//计算出最大适应值的同源基因编号
float **fittedvalue;//计算出最大适应值的同源基因对应的拟合值
char *dev_numofhometic;
float *dev_fittedvalue;

char **normalGenes;//普通基因变量与函数的索引
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

int funcNum;//操作数个数

void initcpu()
{
	fittedvalue=(float**)malloc(sizeof(float*)*numofpopulation);
	fittedvalue[0]=(float*)malloc(sizeof(float)*numofpopulation*row);
	for(int i=1;i<numofpopulation;i++)
		fittedvalue[i]=fittedvalue[i-1]+row;

	strFun=(char*)malloc(sizeof(char)*numofFun);//动态分配存放函数的数组
	strVariable=(char*)malloc(sizeof(char)*numofVariable);//动态分配存放变量的数组
	fitness=(float*)malloc(sizeof(float)*numofpopulation);//动态分配适应值数组，长度为中群大小
	numofhometic=(char*)malloc(sizeof(char)*numofpopulation);
}
void initgpu()
{
	//在设备为二维数组分配空间
	cudaMallocPitch((void**)&dev_normalGenes,&pitchNG,lenofgene*numofnormalgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_normalGenesIndex,&pitchNI,lenofgene*numofnormalgenes*sizeof(char),numofpopulation);

	cudaMallocPitch((void**)&dev_data,&pitchDT,col*sizeof(float),row);

	cudaMallocPitch((void**)&dev_homeoticGenes,&pitchHG,lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_homeoticGenesIndex,&pitchHI,lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_fittedvalue,&pitchFV,row*sizeof(float),numofpopulation);
	//为一维数组分配空间
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

	for(int i=0;i<numofpopulation;i++)//释放申请的空间
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
void cputogpu()//拷贝：cpu到gpu
{
	cudaMemcpy2D(dev_normalGenes,pitchNG,normalGenes[0],lenofgene*numofnormalgenes*sizeof(char),lenofgene*numofnormalgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
	cudaMemcpy2D(dev_normalGenesIndex,pitchNI,normalGenesIndex[0],lenofgene*numofnormalgenes,lenofgene*numofnormalgenes,numofpopulation,cudaMemcpyHostToDevice);

	cudaMemcpy2D(dev_data,pitchDT,data[0],col*sizeof(float),col*sizeof(float),row,cudaMemcpyHostToDevice);

	cudaMemcpy2D(dev_homeoticGenes,pitchHG,homeoticGenes[0],lenofhometicgene*numofhomeoticgenes*sizeof(char),lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
	cudaMemcpy2D(dev_homeoticGenesIndex,pitchHI,homeoticGenesIndex[0],lenofhometicgene*numofhomeoticgenes*sizeof(char),lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
}
void gputocpu()//拷贝：gpu到cpu
{
	cudaMemcpy(fitness,dev_fitness,sizeof(float)*numofpopulation,cudaMemcpyDeviceToHost);
	cudaMemcpy(numofhometic,dev_numofhometic,sizeof(char)*numofpopulation,cudaMemcpyDeviceToHost);
	cudaMemcpy2D(fittedvalue[0],row*sizeof(float),dev_fittedvalue,pitchFV,sizeof(float)*row,numofpopulation,cudaMemcpyDeviceToHost);
}

#endif /* FITNESS_CUH_ */
