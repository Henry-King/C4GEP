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
JNIEXPORT void JNICALL Java_domain_service_algOutput_AlgCudaRunStep_calcOnCuda(JNIEnv *env, jobject, jobject gepAlgRun){
	//java����Դ��ĺ���
	jclass class_Boolean=env->FindClass("Ljava/lang/Boolean;");
	jmethodID boolValueMethod=(*env).GetMethodID(class_Boolean,"booleanValue","()Z");

	jclass class_Float=env->FindClass("Ljava/lang/Float;");
	jmethodID floatValueMethod=(*env).GetMethodID(class_Float,"floatValue","()F");

	jclass class_Integer=env->FindClass("Ljava/lang/Integer;");
	jmethodID integerValueMethod=(*env).GetMethodID(class_Integer,"intValue","()I");

	jclass class_Long=env->FindClass("Ljava/lang/Long;");
	jmethodID longValueMethod=(*env).GetMethodID(class_Long,"longValue","()J");

	jclass class_List=(*env).FindClass("Ljava/util/List;");
	jmethodID getListSize=(*env).GetMethodID(class_List,"size","()I");
	jmethodID getListObject=(*env).GetMethodID(class_List,"get","(I)Ljava/lang/Object;");

	jclass class_Enum=(*env).FindClass("Ljava/lang/Enum;");
	jmethodID getEnumName=(*env).GetMethodID(class_Enum,"name","()Ljava/lang/String;");

	//GepAlgRun-------------------------------------------------------------------------------
	 jclass  class_GepAlgRun=(*env).FindClass("Ldomain/core/algOutput/GepAlgRun;");
	 jfieldID  gepAlgConfigurationID=(*env).GetFieldID(class_GepAlgRun,"gepAlgConfiguration","Ldomain/core/algconfiguration/GepAlgConfiguration;");
	 jfieldID  dataSetID=(*env).GetFieldID(class_GepAlgRun,"dataSet","Ldomain/core/algInputDataProcess/DataSet;");
	 jfieldID  populationsID=(*env).GetFieldID(class_GepAlgRun,"populations","Ljava/util/List;");
	 jmethodID getCurrentPopulationID=(*env).GetMethodID(class_GepAlgRun,"getCurrentPopulation","()Ldomain/core/algOutput/Population;");

	 //DataSet--------------------------------------------------------------------------
	  jobject dataSet=(*env).GetObjectField(gepAlgRun,dataSetID);
	 jclass  class_DataSet=(*env).GetObjectClass(dataSet);
	 jfieldID rowNumID=(*env).GetFieldID(class_DataSet,"rowNum","Ljava/lang/Integer;");
	 jobject  rowNum=(*env).GetObjectField(dataSet,rowNumID);//�õ�rowNum
	 int rowNumCpp=(*env).CallIntMethod(rowNum,integerValueMethod);

	 jfieldID columnNumID=(*env).GetFieldID(class_DataSet,"columnNum","Ljava/lang/Integer;");
	 jobject  columnNum=(*env).GetObjectField(dataSet,columnNumID);//�õ�ColumnNum
	 int  columnNumCpp=(*env).CallIntMethod(columnNum,integerValueMethod);

	 jmethodID toDeepArrayID=(*env).GetMethodID(class_DataSet,"toDeepArray","()[[F");
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
         printf("%f ",coldata[j]);
		 dataSetCp[i][j]=coldata[j];
        }

	}

	 //Population----------------------------------------------------------------------------
	 jobject currentPopulation=(*env).CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	 jclass  class_Population=(*env).FindClass("Ldomain/core/algOutput/Population;");
	 jmethodID  getNormalGeneTypeID=(*env).GetMethodID(class_Population,"getNormalGeneType","()[[C");
	 jmethodID  getHomeoticGeneTypeID=(*env).GetMethodID(class_Population,"getHomeoticGeneType","()[[C");
	 jmethodID  getNormalGeneIndexID=(*env).GetMethodID(class_Population,"getNormalGeneIndex","()[[C");
	 jmethodID  getHomeoticGeneIndexID=(*env).GetMethodID(class_Population,"getHomeoticGeneIndex","()[[C");


	 jobjectArray  normalGeneTypes=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getNormalGeneTypeID);
	 if(normalGeneTypes==NULL){
		  return;
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
            printf("%d ",sizeOfNormalGeneTypes);
			normalGeneTypesCp[i][j]=coldata[j];
          }
	 }


	 jobjectArray  homeoticGeneTypes=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getHomeoticGeneTypeID);
	 if(homeoticGeneTypes==NULL){
		  return;
	 }
	 //��̬����----------------------------------------------------------------------
	 int sizeOfHomeoticGeneTypes=(*env).GetArrayLength(homeoticGeneTypes);//�������
	 jarray  homeoticGeneTypesCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneTypes, 0);
	 int colOfHomeoticGeneTypes =(*env).GetArrayLength(homeoticGeneTypesCol); //�������
	 char** homeoticGeneTypesCp=(char**)malloc(sizeof(char*)*sizeOfHomeoticGeneTypes);//�����ά��������
	 for(int i=0;i<sizeOfNormalGeneTypes;i++)
         homeoticGeneTypesCp[i]=(char*)malloc(sizeof(char)*colOfHomeoticGeneTypes);//�����ά��������
	 //��ֵ-------------------------------------------------------------------
	 for(int i=0;i<sizeOfHomeoticGeneTypes;i++){
		  homeoticGeneTypesCol=(jarray)(*env).GetObjectArrayElement(homeoticGeneTypes, 0);
		  jchar *coldata = (*env).GetCharArrayElements((jcharArray)homeoticGeneTypesCol, 0 );
          for (int j=0; j<colOfHomeoticGeneTypes; j++) {
            printf("%d ",colOfHomeoticGeneTypes);
			homeoticGeneTypesCp[i][j]=coldata[j];
          }
	 }


	 jobjectArray  normalGeneIndex=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getNormalGeneIndexID);
	 if(normalGeneIndex==NULL){
		  return;
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
            printf("%d ",coldata[j]);
			normalGeneIndexCp[i][j]=coldata[j];
          }
	 }

	 jobjectArray  homeoticGeneIndex=(jobjectArray)(*env).CallObjectMethod(currentPopulation,getHomeoticGeneIndexID);
	 if(homeoticGeneIndex==NULL){
		  return;
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
            printf("%d ",coldata[j]);
			homeoticGeneIndexCp[i][j]=coldata[j];
          }
	 }
	 return;
}

