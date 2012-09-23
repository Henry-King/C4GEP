/*
 * copyToGpu.h
 *
 *  Created on: 2012-9-23
 *      Author: Alexander
 */
#ifndef COPYTOGPU_H_
#define COPYTOGPU_H_
#include <jni.h>
#include <stdio.h>
static jclass class_Boolean=NULL;
static jmethodID boolValueMethod=NULL;
static jclass class_Float=NULL;
static jmethodID floatValueMethod=NULL;
static jclass class_Integer=NULL;
static jmethodID integerValueMethod=NULL;
static jclass class_Long=NULL;
static jmethodID longValueMethod=NULL;
static jclass class_List=NULL;
static jmethodID getListSize=NULL;
static jmethodID getListObject=NULL;
static jclass class_Enum=NULL;
static jmethodID getEnumName=NULL;
static jclass class_GepAlgRun=NULL;
static jfieldID  gepAlgConfigurationID=NULL;
static jfieldID  dataSetID=NULL;
static jfieldID  populationsID=NULL;
static jmethodID getCurrentPopulationID=NULL;
static jobject dataSet=NULL;
static jclass class_DataSet=NULL;
static jfieldID rowNumID=NULL;
static jobject rowNum=NULL;
static jfieldID columnNumID=NULL;
static jobject columnNum=NULL;
static jmethodID toDeepArrayID=NULL;
static jobject currentPopulation=NULL;
static jclass class_Population=NULL;
static jmethodID getNormalGeneTypeID=NULL;
static jmethodID  getHomeoticGeneTypeID=NULL;
static jmethodID  getNormalGeneIndexID=NULL;
static jmethodID  getHomeoticGeneIndexID=NULL;
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
		rowNum=env->GetObjectField(dataSet,rowNumID);//�õ�rowNum
	if(!columnNumID)
		columnNumID=env->GetFieldID(class_DataSet,"columnNum","Ljava/lang/Integer;");
	if(!columnNum)
		columnNum=env->GetObjectField(dataSet,columnNumID);//�õ�ColumnNum
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
	//��̬����--------------------------------------------------
	int sizeOfDataSetArr=(*env).GetArrayLength(dataSetArr);//�������
	jarray dataSetCpCols =(jarray)(*env).GetObjectArrayElement(dataSetArr, 0);
	int colOfDataSetArr =(*env).GetArrayLength(dataSetCpCols); //�������
	float** dataSetCp=(float**)malloc(sizeof(float*)*sizeOfDataSetArr);//�����ά��������
	for(int i=0;i<sizeOfDataSetArr;i++)
		dataSetCp[i]=(float*)malloc(sizeof(float)*colOfDataSetArr);//�����ά��������
	//��ֵ----------------------------------------------------------
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
	//��̬����----------------------------------------------------------------------
	int sizeOfNormalGeneTypes=(*env).GetArrayLength(normalGeneTypes);//�������
	jarray  normalGeneTypesCol=(jarray)(*env).GetObjectArrayElement(normalGeneTypes, 0);
	int colOfNormalGeneTypes =(*env).GetArrayLength(normalGeneTypesCol); //�������
	char** normalGeneTypesCp=(char**)malloc(sizeof(char*)*sizeOfNormalGeneTypes);//�����ά��������
	for(int i=0;i<sizeOfNormalGeneTypes;i++)
		normalGeneTypesCp[i]=(char*)malloc(sizeof(char)*colOfNormalGeneTypes);//�����ά��������
	//��ֵ-------------------------------------------------------------------
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
	//��̬����----------------------------------------------------------------------
	int sizeOfHomeoticGeneTypes=(*env).GetArrayLength(homeoticGeneTypes);//�������
	jarray  homeoticGeneTypesCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneTypes, 0);
	int colOfHomeoticGeneTypes =(*env).GetArrayLength(homeoticGeneTypesCol); //�������
	char** homeoticGeneTypesCp=(char**)malloc(sizeof(char*)*sizeOfHomeoticGeneTypes);//�����ά��������
	for(int i=0;i<sizeOfHomeoticGeneTypes;i++)
		homeoticGeneTypesCp[i]=(char*)malloc(sizeof(char)*colOfHomeoticGeneTypes);//�����ά��������
	//��ֵ-------------------------------------------------------------------
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
	//��̬����----------------------------------------------------------------------
	int sizeOfNormalGeneIndex=(*env).GetArrayLength(normalGeneIndex);//�������
	jarray  normalGeneIndexCol=(jarray)(*env).GetObjectArrayElement(normalGeneIndex, 0);
	int colOfNormalGeneIndex =(*env).GetArrayLength(normalGeneIndexCol); //�������
	char** normalGeneIndexCp=(char**)malloc(sizeof(char*)*sizeOfNormalGeneIndex);//�����ά��������
	for(int i=0;i<sizeOfNormalGeneIndex;i++)
		normalGeneIndexCp[i]=(char*)malloc(sizeof(char)*colOfNormalGeneIndex);//�����ά��������
	//��ֵ-------------------------------------------------------------------
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
	//��̬����----------------------------------------------------------------------
	int sizeOfHomeoticGeneIndex=(*env).GetArrayLength(homeoticGeneIndex);//�������
	jarray  homeoticGeneIndexCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneIndex, 0);
	int colOfHomeoticGeneIndex =(*env).GetArrayLength(homeoticGeneIndexCol); //�������
	char** homeoticGeneIndexCp=(char**)malloc(sizeof(char*)*sizeOfHomeoticGeneIndex);//�����ά��������
	for(int i=0;i<sizeOfHomeoticGeneIndex;i++)
		homeoticGeneIndexCp[i]=(char*)malloc(sizeof(char)*colOfHomeoticGeneIndex);//�����ά��������
	//��ֵ-------------------------------------------------------------------
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



#endif /* COPYTOGPU_H_ */
