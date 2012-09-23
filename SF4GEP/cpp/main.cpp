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
#include "copyToGpu.h"
JNIEXPORT void JNICALL Java_domain_service_algOutput_AlgGpuRunStep_calcOnCuda(JNIEnv *env, jobject, jobject gepAlgRun){
	iniSysId(env);
	iniGepAlgRunId(env);
	iniDataSetId(env,gepAlgRun);
	iniPopulationId(env,gepAlgRun);
	createDataSet(env);
	createNormalGeneType(env);
	createHomeoticGeneType(env);
	createNormalGeneIndex(env);
	createHomeoticGeneIndex(env);
	return;
}

