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
static jclass class_DataSet=NULL;
static jfieldID rowNumID=NULL;
static jfieldID columnNumID=NULL;
static jmethodID toDeepArrayID=NULL;
static jclass class_Population=NULL;
static jmethodID getNormalGeneTypeID=NULL;
static jmethodID  getHomeoticGeneTypeID=NULL;
static jmethodID  getNormalGeneIndexID=NULL;
static jmethodID  getHomeoticGeneIndexID=NULL;
static jclass gepAlgConfigurationClass=NULL;
static jclass individualConfClass=NULL;
static jfieldID individualConfigurationID=NULL;
static jfieldID populationSizeID=NULL;
static void iniSysId(JNIEnv *env){
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
static void iniGepAlgRunId(JNIEnv *env){
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
static void iniDataSetId(JNIEnv * env){
	if(!class_DataSet)
		class_DataSet=env->FindClass("Ldomain/core/algInputDataProcess/DataSet;");
	if(!rowNumID)
		rowNumID=env->GetFieldID(class_DataSet,"rowNum","Ljava/lang/Integer;");
	if(!columnNumID)
		columnNumID=env->GetFieldID(class_DataSet,"columnNum","Ljava/lang/Integer;");
	if(!toDeepArrayID)
		toDeepArrayID=env->GetMethodID(class_DataSet,"toDeepArray","()[[F");
}
static void iniPopulationId(JNIEnv * env){
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
static void iniConf(JNIEnv * env){
	if(!gepAlgConfigurationClass)
		gepAlgConfigurationClass=env->FindClass("Ldomain/core/algconfiguration/GepAlgConfiguration;");
	if(!individualConfigurationID)
		individualConfigurationID=env->GetFieldID(gepAlgConfigurationClass,"individualConfiguration","Ldomain/core/algconfiguration/IndividualConfiguration;");
	if(!individualConfClass)
		individualConfClass=env->FindClass("Ldomain/core/algconfiguration/IndividualConfiguration;");
	if(!populationSizeID)
		populationSizeID=env->GetFieldID(individualConfClass,"individualNumber","Ljava/lang/Integer;");

}
void iniAllId(JNIEnv * env){
	iniSysId(env);
	iniGepAlgRunId(env);
	iniDataSetId(env);
	iniPopulationId(env);
	iniConf(env);
}
float** createDataSet(JNIEnv *env,jobject gepAlgRun){
	jobject dataSet=env->GetObjectField(gepAlgRun,dataSetID);
	jobjectArray dataSetArr=(jobjectArray)(*env).CallObjectMethod(dataSet,toDeepArrayID);
	//动态分配--------------------------------------------------
	int sizeOfDataSetArr=env->GetArrayLength(dataSetArr);//获得行数
	jarray dataSetCpCols =(jarray)(*env).GetObjectArrayElement(dataSetArr, 0);
	int colOfDataSetArr =env->GetArrayLength(dataSetCpCols); //获得列数
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
char** createNormalGeneType(JNIEnv *env,jobject gepAlgRun) {
	jobject currentPopulation=env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
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
char** createHomeoticGeneType(JNIEnv *env,jobject gepAlgRun){
	jobject currentPopulation=env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
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

char** createNormalGeneIndex(JNIEnv* env,jobject gepAlgRun) {
	jobject currentPopulation=env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	jobjectArray normalGeneIndex=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getNormalGeneIndexID);
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

char** createHomeoticGeneIndex(JNIEnv* env,jobject gepAlgRun) {
	jobject currentPopulation=env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
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
int getPopulationSize(JNIEnv* env,jobject gepAlgRun){
	return 0;
}
int getNormalGeneNum(){
	return 0;
}
int getHomeoticGeneNum(){
	return 0;
}
int getNormalGeneHeaderLength(){
	return 0;
}
int getNormalGeneLength(){
	return 0;
}
int getHomeoticGeneHeaderLength(){
	return 0;
}
int getHomeoticGeneLength(){
	return 0;
}
int getRowNum(){
	return 0;
}
int getColumnNum(){
	return 0;
}
int getVaribaleNum(){
	return 0;
}
int getFuncNum(){
	return 0;
}
int getMaxArity(){
	return 0;
}
float getSelectionRange(){
	return 0;
}


#endif /* COPYTOGPU_H_ */
