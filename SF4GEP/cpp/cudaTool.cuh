/*
 * fitness.cuh
 *
 *  Created on: 2012-9-23
 *      Author: Alexander
 */

#ifndef FITNESS_CUH_
#define FITNESS_CUH_
#include "cuda_runtime.h"
#include <stdlib.h>
#include "kernelRun.cuh"

static float *fitness;//适应值
static float *dev_data;
static float *dev_fitness;
static char *numofhometic;//计算出最大适应值的同源基因编号
static float **fittedvalue;//计算出最大适应值的同源基因对应的拟合值
static char *dev_numofhometic;
static float *dev_fittedvalue;

static char *dev_normalGenes;
static char *dev_normalGenesIndex;
static char *dev_homeoticGenes;
static char *dev_homeoticGenesIndex;

static size_t pitchNG;
static size_t pitchNI;
static size_t pitchHG;
static size_t pitchHI;
static size_t pitchDT;
static size_t pitchFV;

void initcpu(int numofpopulation,int row)
{
	fittedvalue=(float**)malloc(sizeof(float*)*numofpopulation);
	fittedvalue[0]=(float*)malloc(sizeof(float)*numofpopulation*row);
	for(int i=1;i<numofpopulation;i++)
		fittedvalue[i]=fittedvalue[i-1]+row;
	fitness=(float*)malloc(sizeof(float)*numofpopulation);//动态分配适应值数组，长度为中群大小
	numofhometic=(char*)malloc(sizeof(char)*numofpopulation);
}

void initgpu(int lenofnormalgene,int numofnormalgenes,int numofpopulation,int col,int row,int lenofhometicgene,int numofhomeoticgenes)
{
	//在设备为二维数组分配空间
	cudaMallocPitch((void**)&dev_normalGenes,&pitchNG,lenofnormalgene*numofnormalgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_normalGenesIndex,&pitchNI,lenofnormalgene*numofnormalgenes*sizeof(char),numofpopulation);

	cudaMallocPitch((void**)&dev_data,&pitchDT,col*sizeof(float),row);

	cudaMallocPitch((void**)&dev_homeoticGenes,&pitchHG,lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_homeoticGenesIndex,&pitchHI,lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation);
	cudaMallocPitch((void**)&dev_fittedvalue,&pitchFV,row*sizeof(float),numofpopulation);
	//为一维数组分配空间
	cudaMalloc((void**)&dev_fitness,numofpopulation*sizeof(float));
	cudaMalloc((void**)&dev_numofhometic,sizeof(char)*numofpopulation);
}

void freecpuandgpu(int numofpopulation,char** normalGenes,char** normalGenesIndex,char** homeoticGenes,char** homeoticGenesIndex)
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
void cputogpu(int lenofgene,int numofnormalgenes,int numofpopulation,int col,int row,int lenofhometicgene,int numofhomeoticgenes,float **data,char **normalGenes,char **normalGenesIndex,char **homeoticGenes,char **homeoticGenesIndex)//拷贝：cpu到gpu
{
	cudaMemcpy2D(dev_normalGenes,pitchNG,normalGenes[0],lenofgene*numofnormalgenes*sizeof(char),lenofgene*numofnormalgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
	cudaMemcpy2D(dev_normalGenesIndex,pitchNI,normalGenesIndex[0],lenofgene*numofnormalgenes,lenofgene*numofnormalgenes,numofpopulation,cudaMemcpyHostToDevice);

	cudaMemcpy2D(dev_data,pitchDT,data[0],col*sizeof(float),col*sizeof(float),row,cudaMemcpyHostToDevice);

	cudaMemcpy2D(dev_homeoticGenes,pitchHG,homeoticGenes[0],lenofhometicgene*numofhomeoticgenes*sizeof(char),lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
	cudaMemcpy2D(dev_homeoticGenesIndex,pitchHI,homeoticGenesIndex[0],lenofhometicgene*numofhomeoticgenes*sizeof(char),lenofhometicgene*numofhomeoticgenes*sizeof(char),numofpopulation,cudaMemcpyHostToDevice);
}
void gputocpu(int numofpopulation,int row)//拷贝：gpu到cpu
{
	cudaMemcpy(fitness,dev_fitness,sizeof(float)*numofpopulation,cudaMemcpyDeviceToHost);
	cudaMemcpy(numofhometic,dev_numofhometic,sizeof(char)*numofpopulation,cudaMemcpyDeviceToHost);
	cudaMemcpy2D(fittedvalue[0],row*sizeof(float),dev_fittedvalue,pitchFV,sizeof(float)*row,numofpopulation,cudaMemcpyDeviceToHost);
}
float* getFitnessArray(){
	return fitness;
}
char* getHomeoticArray(){
	return numofhometic;
}
float **getFittedValueArray(){
	return fittedvalue;
}
void callKernel(int normalGeneNum,int homeoticGeneNum,int populationSize,int rowNum,int columnNum,int normalGeneLength,int homeoticGeneLength,int selectionRange){
	int threadsPerBlock=(normalGeneNum>homeoticGeneNum?normalGeneNum:homeoticGeneNum);
	int blocksPerGrid=populationSize;
	size_t share_size=(rowNum*normalGeneNum+rowNum*homeoticGeneNum+homeoticGeneNum)*sizeof(float);
	int sizeofarray=rowNum*normalGeneLength*populationSize;
	float *dev_array;
	cudaMalloc((void**)&dev_array,sizeof(float)*sizeofarray);
	kernel<<<blocksPerGrid,threadsPerBlock,share_size>>>(dev_normalGenes,dev_normalGenesIndex,dev_homeoticGenes,dev_homeoticGenesIndex,dev_data,pitchNG,pitchNI,pitchHG,pitchHI,pitchDT,rowNum,columnNum,dev_fitness,dev_numofhometic,dev_fittedvalue,pitchFV,dev_array,normalGeneLength,normalGeneNum,homeoticGeneLength,homeoticGeneNum,selectionRange);
	cudaDeviceSynchronize();
	cudaFree(dev_array);
}
#endif /* FITNESS_CUH_ */
