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
static float** dataSet;
static char** normalGeneType;
static char** homeoticGeneType;
static char** normalGeneIndex;
static char** homeoticGeneIndex;
JNIEXPORT void JNICALL Java_domain_service_algOutput_AlgGpuRunStep_calcOnCuda(JNIEnv *env, jobject me, jobject gepAlgRun){
	initContext(env,gepAlgRun);
	initcpu(populationSize,rowNum);
	initgpu(normalGeneLength,normalGeneNum,populationSize,columnNum,rowNum,homeoticGeneLength,homeoticGeneNum);
	cputogpu(normalGeneLength,normalGeneNum,populationSize,columnNum,rowNum,homeoticGeneLength,homeoticGeneNum,dataSet,normalGeneType,normalGeneIndex,homeoticGeneType,homeoticGeneIndex);
	callKernel(normalGeneNum,homeoticGeneNum,populationSize,rowNum,columnNum,normalGeneLength,homeoticGeneLength,selectionRange);
	gputocpu(populationSize,rowNum);
	cToJava(env,gepAlgRun);
	freecpuandgpu(populationSize,normalGeneType,normalGeneIndex,homeoticGeneType,homeoticGeneIndex);
	return;
}
static void initContext(JNIEnv *env,jobject gepAlgRun){
	iniAllId(env);
	dataSet=createDataSet(env,gepAlgRun);
	normalGeneType=createNormalGeneType(env,gepAlgRun);
	homeoticGeneType=createHomeoticGeneType(env,gepAlgRun);
	normalGeneIndex=createNormalGeneIndex(env,gepAlgRun);
	homeoticGeneIndex=createHomeoticGeneIndex(env,gepAlgRun);
	populationSize=getPopulationSize(env,gepAlgRun);
	rowNum=getRowNum(env,gepAlgRun);
	columnNum=getColumnNum(env,gepAlgRun);
	normalGeneLength=getNormalGeneLength(env,gepAlgRun);
	normalGeneNum=getNormalGeneNum(env,gepAlgRun);
	homeoticGeneLength=getHomeoticGeneLength(env,gepAlgRun);
	homeoticGeneNum=getHomeoticGeneNum(env,gepAlgRun);
	selectionRange=getSelectionRange(env,gepAlgRun);
}
static void cToJava(JNIEnv *env,jobject gepAlgRun){
//	printf("Hi\n");
//	fflush(stdout);
	toJavaFitness(env,gepAlgRun,getFitnessArray());
	toJavaFittedValue(env,gepAlgRun,getFittedValueArray());
	toJavaHomeoticGeneIndex(env,gepAlgRun,getHomeoticArray());
}
