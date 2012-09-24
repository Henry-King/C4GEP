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
#include "javaToC.h"
JNIEXPORT void JNICALL Java_domain_service_algOutput_AlgGpuRunStep_calcOnCuda(JNIEnv *env, jobject, jobject gepAlgRun){
	iniAllId(env);
	createDataSet(env,gepAlgRun);
	createNormalGeneType(env,gepAlgRun);
	createHomeoticGeneType(env,gepAlgRun);
	createNormalGeneIndex(env,gepAlgRun);
	createHomeoticGeneIndex(env,gepAlgRun);
	return;
}

