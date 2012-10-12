/*
 * transfer.cpp
 *
 *  Created on: 2012-9-5
 *      Author: Alexander
 */

#include "domain_service_algOutput_AlgGpuRunStep.h"
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include "jniTransform.h"
#include "cudaTool.cuh"
static void initContext(JNIEnv*,jobject);
static void cToJava(JNIEnv*,jobject);
static int populationSize;
static int rowNum;
static int columnNum;
static int normalGeneLength;
static int homeoticGeneLength;
static int homeoticGeneNum;
static int normalGeneNum;
static int selectionRange;
static float accuracy;
static float** dataSet;
static char** normalGeneType;
static char** homeoticGeneType;
static char** normalGeneIndex;
static char** homeoticGeneIndex;
JNIEXPORT void JNICALL Java_domain_service_algOutput_AlgGpuRunStep_calcOnCuda(JNIEnv *env, jobject me, jobject gepAlgRun){
//	cudaSetDevice(0);
//	printf("a\n");
//	fflush(stdout);
	initContext(env,gepAlgRun);
//	printf("b\n");
//	fflush(stdout);
	initcpu(populationSize,rowNum);
//	printf("c\n");
//	fflush(stdout);
	initgpu(normalGeneLength,normalGeneNum,populationSize,columnNum,rowNum,homeoticGeneLength,homeoticGeneNum);
//	printf("d\n");
//	fflush(stdout);
	cputogpu(normalGeneLength,normalGeneNum,populationSize,columnNum,rowNum,homeoticGeneLength,homeoticGeneNum,dataSet,normalGeneType,normalGeneIndex,homeoticGeneType,homeoticGeneIndex);
//	printf("e\n");
//	fflush(stdout);
	callKernel(normalGeneNum,homeoticGeneNum,populationSize,rowNum,columnNum,normalGeneLength,homeoticGeneLength,selectionRange,accuracy);
//	printf("f\n");
//	fflush(stdout);
	gputocpu(populationSize,rowNum);
//	printf("g\n");
//	fflush(stdout);
	cToJava(env,gepAlgRun);
//	printf("h\n");
//	fflush(stdout);
	freecpuandgpu(populationSize,normalGeneType,normalGeneIndex,homeoticGeneType,homeoticGeneIndex,dataSet);
//	cudaDeviceReset();
	return;
}
static void initContext(JNIEnv *env,jobject gepAlgRun){
	iniAllId(env);
	populationSize=getPopulationSize(env,gepAlgRun);
	rowNum=getRowNum(env,gepAlgRun);
	columnNum=getColumnNum(env,gepAlgRun);
	normalGeneLength=getNormalGeneLength(env,gepAlgRun);
	normalGeneNum=getNormalGeneNum(env,gepAlgRun);
	homeoticGeneLength=getHomeoticGeneLength(env,gepAlgRun);
	homeoticGeneNum=getHomeoticGeneNum(env,gepAlgRun);
	selectionRange=getSelectionRange(env,gepAlgRun);
	accuracy=getAccuracy(env,gepAlgRun);
//	printf("a\n");
//	fflush(stdout);
	dataSet=createDataSet(env,gepAlgRun,rowNum,columnNum);
//	printf("b\n");
//	fflush(stdout);
	normalGeneType=createNormalGeneType(env,gepAlgRun,populationSize,normalGeneLength,normalGeneNum);
//	printf("c\n");
//	fflush(stdout);
	homeoticGeneType=createHomeoticGeneType(env,gepAlgRun,populationSize,homeoticGeneLength,homeoticGeneNum);
//	printf("d\n");
//	fflush(stdout);
	normalGeneIndex=createNormalGeneIndex(env,gepAlgRun,populationSize,normalGeneLength,normalGeneNum);
//	printf("e\n");
//	fflush(stdout);
	homeoticGeneIndex=createHomeoticGeneIndex(env,gepAlgRun,populationSize,homeoticGeneLength,homeoticGeneNum);
//	printf("f\n");
//	fflush(stdout);
}
static void cToJava(JNIEnv *env,jobject gepAlgRun){
//	printf("Hi\n");
//	fflush(stdout);
	toJavaFitness(env,gepAlgRun,getFitnessArray());
	toJavaFittedValue(env,gepAlgRun,getFittedValueArray());
	toJavaHomeoticGeneIndex(env,gepAlgRun,getHomeoticArray());
}
