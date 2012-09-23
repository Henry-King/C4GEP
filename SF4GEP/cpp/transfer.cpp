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
jclass class_Boolean=NULL;
jmethodID boolValueMethod=NULL;
jclass class_Float=NULL;
jmethodID floatValueMethod=NULL;
jclass class_Integer=NULL;
jmethodID integerValueMethod=NULL;
jclass class_Long=NULL;
jmethodID longValueMethod=NULL;
jclass class_List=NULL;
jmethodID getListSize=NULL;
jmethodID getListObject=NULL;
jclass class_Enum=NULL;
jmethodID getEnumName=NULL;
jclass class_GepAlgRun=NULL;
jfieldID  gepAlgConfigurationID=NULL;
jfieldID  dataSetID=NULL;
jfieldID  populationsID=NULL;
jmethodID getCurrentPopulationID=NULL;
jobject dataSet=NULL;
jclass class_DataSet=NULL;
jfieldID rowNumID=NULL;
jobject rowNum=NULL;
jfieldID columnNumID=NULL;
jobject columnNum=NULL;
jmethodID toDeepArrayID=NULL;
jobject currentPopulation=NULL;
jclass class_Population=NULL;
jmethodID getNormalGeneTypeID=NULL;
jmethodID  getHomeoticGeneTypeID=NULL;
jmethodID  getNormalGeneIndexID=NULL;
jmethodID  getHomeoticGeneIndexID=NULL;
void iniSysId(JNIEnv *);	//获得java类库自带的函数ID
void iniGepAlgRunId(JNIEnv *);	//获得GepAlgRun中的ID
void iniDataSetId(JNIEnv *,jobject); //获得DataSet中的ID
void iniPopulationId(JNIEnv *,jobject);	 //获得Population中的ID
float** createDataSet(JNIEnv *);//将DataSet转化为c++二维数组
char** createNormalGeneType(JNIEnv *);
char** createHomeoticGeneType(JNIEnv *);
char** createNormalGeneIndex(JNIEnv *);
char** createHomeoticGeneIndex(JNIEnv *);
JNIEXPORT void JNICALL Java_domain_service_algOutput_AlgCudaRunStep_calcOnCuda(JNIEnv *env, jobject, jobject gepAlgRun){
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
void iniSysId(JNIEnv *env){
	if(!class_Boolean)
		class_Boolean=env->FindClass("Ljava/lang/Boolean;");
	if(!boolValueMethod)
		boolValueMethod=env->GetMethodID(class_Boolean,"booleanValue","()Z");
	if(!class_Float)
		class_Float=env->FindClass("Ljava/lang/Float;");
	if(!floatValueMethod)
		floatValueMethod=env->GetMethodID(class_Float,"floatValue","()F");
	if(!class_Integer)
		class_Integer=env->FindClass("Ljava/lang/Integer;");
	if(!integerValueMethod)
		integerValueMethod=env->GetMethodID(class_Integer,"intValue","()I");
	if(!class_Long)
		class_Long=env->FindClass("Ljava/lang/Long;");
	if(!longValueMethod)
		longValueMethod=env->GetMethodID(class_Long,"longValue","()J");
	if(!class_List)
		class_List=env->FindClass("Ljava/util/List;");
	if(!getListSize)
		getListSize=env->GetMethodID(class_List,"size","()I");
	if(!getListObject)
		getListObject=env->GetMethodID(class_List,"get","(I)Ljava/lang/Object;");
	if(!class_Enum)
		class_Enum=env->FindClass("Ljava/lang/Enum;");
	if(!getEnumName)
		getEnumName=(*env).GetMethodID(class_Enum,"name","()Ljava/lang/String;");
}
void iniGepAlgRunId(JNIEnv *env){
	if(!class_GepAlgRun)
		class_GepAlgRun=env->FindClass("Ldomain/core/algOutput/GepAlgRun;");
	if(!gepAlgConfigurationID)
		gepAlgConfigurationID=env->GetFieldID(class_GepAlgRun,"gepAlgConfiguration","Ldomain/core/algconfiguration/GepAlgConfiguration;");
	if(!dataSetID)
		dataSetID=env->GetFieldID(class_GepAlgRun,"dataSet","Ldomain/core/algInputDataProcess/DataSet;");
	if(!populationsID)
		populationsID=env->GetFieldID(class_GepAlgRun,"populations","Ljava/util/List;");
	if(!getCurrentPopulationID)
		getCurrentPopulationID=env->GetMethodID(class_GepAlgRun,"getCurrentPopulation","()Ldomain/core/algOutput/Population;");
}
void iniDataSetId(JNIEnv * env,jobject gepAlgRun){
	if(!dataSet)
		dataSet=env->GetObjectField(gepAlgRun,dataSetID);
	if(!class_DataSet)
		class_DataSet=env->GetObjectClass(dataSet);
	if(!rowNumID)
		rowNumID=env->GetFieldID(class_DataSet,"rowNum","Ljava/lang/Integer;");
	if(!rowNum)
		rowNum=env->GetObjectField(dataSet,rowNumID);//得到rowNum
	if(!columnNumID)
		columnNumID=env->GetFieldID(class_DataSet,"columnNum","Ljava/lang/Integer;");
	if(!columnNum)
		columnNum=env->GetObjectField(dataSet,columnNumID);//得到ColumnNum
	if(!toDeepArrayID)
		toDeepArrayID=env->GetMethodID(class_DataSet,"toDeepArray","()[[F");
}
void iniPopulationId(JNIEnv * env,jobject gepAlgRun){
	if(!currentPopulation)
		currentPopulation=env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	if(!class_Population)
		class_Population=env->FindClass("Ldomain/core/algOutput/Population;");
	if(!getNormalGeneTypeID)
		getNormalGeneTypeID=env->GetMethodID(class_Population,"getNormalGeneType","()[[C");
	if(!getHomeoticGeneTypeID)
		getHomeoticGeneTypeID=env->GetMethodID(class_Population,"getHomeoticGeneType","()[[C");
	if(!getNormalGeneIndexID)
		getNormalGeneIndexID=env->GetMethodID(class_Population,"getNormalGeneIndex","()[[C");
	if(!getHomeoticGeneIndexID)
		getHomeoticGeneIndexID=env->GetMethodID(class_Population,"getHomeoticGeneIndex","()[[C");
}
float** createDataSet(JNIEnv *env){
	jobjectArray dataSetArr=(jobjectArray)(*env).CallObjectMethod(dataSet,toDeepArrayID);
	//动态分配--------------------------------------------------
	int sizeOfDataSetArr=(*env).GetArrayLength(dataSetArr);//获得行数
	jarray dataSetCpCols =(jarray)(*env).GetObjectArrayElement(dataSetArr, 0);
	int colOfDataSetArr =(*env).GetArrayLength(dataSetCpCols); //获得列数
	float** dataSetCp=(float**)malloc(sizeof(float*)*sizeOfDataSetArr);//分配二维数组行数
	for(int i=0;i<sizeOfDataSetArr;i++)
		dataSetCp[i]=(float*)malloc(sizeof(float)*colOfDataSetArr);//分配二维数组列数
	//赋值----------------------------------------------------------
	for(int i=0;i<sizeOfDataSetArr;i++){
		dataSetCpCols =(jarray)(*env).GetObjectArrayElement(dataSetArr, i);
		jfloat *coldata = (*env).GetFloatArrayElements((jfloatArray)dataSetCpCols, 0 );
		for (int j=0; j<colOfDataSetArr; j++) {
			//printf("%f ",coldata[j]);
			dataSetCp[i][j]=coldata[j];
		}
	}
	return dataSetCp;
}

char** createNormalGeneType(JNIEnv *env) {
	jobjectArray  normalGeneTypes=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getNormalGeneTypeID);
	if(normalGeneTypes==NULL){
		return NULL;
	}
	//动态分配----------------------------------------------------------------------
	int sizeOfNormalGeneTypes=(*env).GetArrayLength(normalGeneTypes);//获得行数
	jarray  normalGeneTypesCol=(jarray)(*env).GetObjectArrayElement(normalGeneTypes, 0);
	int colOfNormalGeneTypes =(*env).GetArrayLength(normalGeneTypesCol); //获得列数
	char** normalGeneTypesCp=(char**)malloc(sizeof(char*)*sizeOfNormalGeneTypes);//分配二维数组行数
	for(int i=0;i<sizeOfNormalGeneTypes;i++)
		normalGeneTypesCp[i]=(char*)malloc(sizeof(char)*colOfNormalGeneTypes);//分配二维数组列数
	//赋值-------------------------------------------------------------------
	for(int i=0;i<sizeOfNormalGeneTypes;i++){
		normalGeneTypesCol=(jarray)(*env).GetObjectArrayElement(normalGeneTypes, 0);
		jchar *coldata = (*env).GetCharArrayElements((jcharArray)normalGeneTypesCol, 0 );
		for (int j=0; j<colOfNormalGeneTypes; j++) {
			//printf("%d ",sizeOfNormalGeneTypes);
			normalGeneTypesCp[i][j]=coldata[j];
		}
	}
	return normalGeneTypesCp;
}
char** createHomeoticGeneType(JNIEnv *env){
	jobjectArray  homeoticGeneTypes=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getHomeoticGeneTypeID);
	if(homeoticGeneTypes==NULL){
		return NULL;
	}
	//动态分配----------------------------------------------------------------------
	int sizeOfHomeoticGeneTypes=(*env).GetArrayLength(homeoticGeneTypes);//获得行数
	jarray  homeoticGeneTypesCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneTypes, 0);
	int colOfHomeoticGeneTypes =(*env).GetArrayLength(homeoticGeneTypesCol); //获得列数
	char** homeoticGeneTypesCp=(char**)malloc(sizeof(char*)*sizeOfHomeoticGeneTypes);//分配二维数组行数
	for(int i=0;i<sizeOfHomeoticGeneTypes;i++)
		homeoticGeneTypesCp[i]=(char*)malloc(sizeof(char)*colOfHomeoticGeneTypes);//分配二维数组列数
	//赋值-------------------------------------------------------------------
	for(int i=0;i<sizeOfHomeoticGeneTypes;i++){
		homeoticGeneTypesCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneTypes, 0);
		jchar *coldata = (*env).GetCharArrayElements((jcharArray)homeoticGeneTypesCol, 0 );
		for (int j=0; j<colOfHomeoticGeneTypes; j++) {
			//printf("%d ",colOfHomeoticGeneTypes);
			homeoticGeneTypesCp[i][j]=coldata[j];
		}
	}
	return homeoticGeneTypesCp;
}

char** createNormalGeneIndex(JNIEnv* env) {
	jobjectArray  normalGeneIndex=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getNormalGeneIndexID);
	if(normalGeneIndex==NULL){
		return NULL;
	}
	//动态分配----------------------------------------------------------------------
	int sizeOfNormalGeneIndex=(*env).GetArrayLength(normalGeneIndex);//获得行数
	jarray  normalGeneIndexCol=(jarray)(*env).GetObjectArrayElement(normalGeneIndex, 0);
	int colOfNormalGeneIndex =(*env).GetArrayLength(normalGeneIndexCol); //获得列数
	char** normalGeneIndexCp=(char**)malloc(sizeof(char*)*sizeOfNormalGeneIndex);//分配二维数组行数
	for(int i=0;i<sizeOfNormalGeneIndex;i++)
		normalGeneIndexCp[i]=(char*)malloc(sizeof(char)*colOfNormalGeneIndex);//分配二维数组列数
	//赋值-------------------------------------------------------------------
	for(int i=0;i<sizeOfNormalGeneIndex;i++){
		normalGeneIndexCol=(jarray)(*env).GetObjectArrayElement(normalGeneIndex, 0);
		jchar *coldata = (*env).GetCharArrayElements((jcharArray)normalGeneIndexCol, 0 );
		for (int j=0; j<colOfNormalGeneIndex; j++) {
			//printf("%d ",coldata[j]);
			normalGeneIndexCp[i][j]=coldata[j];
		}
	}
	return normalGeneIndexCp;
}

char** createHomeoticGeneIndex(JNIEnv* env) {
	jobjectArray  homeoticGeneIndex=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getHomeoticGeneIndexID);
	if(homeoticGeneIndex==NULL){
		return NULL;
	}
	//动态分配----------------------------------------------------------------------
	int sizeOfHomeoticGeneIndex=(*env).GetArrayLength(homeoticGeneIndex);//获得行数
	jarray  homeoticGeneIndexCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneIndex, 0);
	int colOfHomeoticGeneIndex =(*env).GetArrayLength(homeoticGeneIndexCol); //获得列数
	char** homeoticGeneIndexCp=(char**)malloc(sizeof(char*)*sizeOfHomeoticGeneIndex);//分配二维数组行数
	for(int i=0;i<sizeOfHomeoticGeneIndex;i++)
		homeoticGeneIndexCp[i]=(char*)malloc(sizeof(char)*colOfHomeoticGeneIndex);//分配二维数组列数
	//赋值-------------------------------------------------------------------
	for(int i=0;i<sizeOfHomeoticGeneIndex;i++){
		homeoticGeneIndexCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneIndex, 0);
		jchar *coldata = (*env).GetCharArrayElements((jcharArray)homeoticGeneIndexCol, 0 );
		for (int j=0; j<colOfHomeoticGeneIndex; j++) {
			//printf("%d ",coldata[j]);
			homeoticGeneIndexCp[i][j]=coldata[j];
		}
	}
	return homeoticGeneIndexCp;
}

