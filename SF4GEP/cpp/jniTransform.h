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
static jclass class_Boolean = NULL;
static jmethodID boolValueMethod = NULL;
static jclass class_Float = NULL;
static jmethodID floatValueMethod = NULL;
static jclass class_Integer = NULL;
static jmethodID integerValueMethod = NULL;
static jclass class_Long = NULL;
static jmethodID longValueMethod = NULL;
static jclass class_List = NULL;
static jmethodID getListSize = NULL;
static jmethodID getListObject = NULL;
static jclass class_Enum = NULL;
static jmethodID getEnumName = NULL;
static jclass class_GepAlgRun = NULL;
static jfieldID gepAlgConfigurationID = NULL;
static jfieldID dataSetID = NULL;
static jfieldID populationsID = NULL;
static jmethodID getCurrentPopulationID = NULL;
static jclass class_DataSet = NULL;
static jfieldID rowNumID = NULL;
static jfieldID columnNumID = NULL;
static jmethodID toDeepArrayID = NULL;
static jclass class_Population = NULL;
static jmethodID getNormalGeneTypeID = NULL;
static jmethodID getHomeoticGeneTypeID = NULL;
static jmethodID getNormalGeneIndexID = NULL;
static jmethodID getHomeoticGeneIndexID = NULL;
static jclass gepAlgConfigurationClass = NULL;
static jclass individualConfClass = NULL;
static jfieldID individualConfigurationID = NULL;
static jfieldID populationSizeID = NULL;
static jfieldID geneConfigurationID = NULL;
static jclass geneConfigurationClass = NULL;
static jfieldID normalGeneNumberID = NULL;
static jfieldID normalGeneHeaderLengthID = NULL;
static jfieldID homeoticGeneNumberID = NULL;
static jfieldID homeoticGeneHeaderLengthID = NULL;
static jfieldID normalGeneLengthID = NULL;
static jfieldID homeoticGeneLengthID = NULL;
static jfieldID selectionRangeID = NULL;
static jfieldID functionUsedID = NULL;
static jfieldID accuracyID=NULL;
static jmethodID setFitnessID=NULL;
static jmethodID setFittedValueID=NULL;
static jmethodID setGeneNumID=NULL;
static jclass strClass=NULL;
static jmethodID ctorID=NULL;
static jmethodID subStringID=NULL;
static jmethodID toCharArrID=NULL;
static void iniSysId(JNIEnv *env) {
//	if (!class_Boolean)
		class_Boolean = env->FindClass("Ljava/lang/Boolean;");
//	if (!boolValueMethod)
		boolValueMethod = env->GetMethodID(class_Boolean, "booleanValue","()Z");
//	if (!class_Float)
		class_Float = env->FindClass("Ljava/lang/Float;");
//	if (!floatValueMethod)
		floatValueMethod = env->GetMethodID(class_Float, "floatValue", "()F");
//	if (!class_Integer)
		class_Integer = env->FindClass("Ljava/lang/Integer;");
//	if (!integerValueMethod)
		integerValueMethod = env->GetMethodID(class_Integer, "intValue", "()I");
//	if (!class_Long)
		class_Long = env->FindClass("Ljava/lang/Long;");
//	if (!longValueMethod)
		longValueMethod = env->GetMethodID(class_Long, "longValue", "()J");
//	if (!class_List)
		class_List = env->FindClass("Ljava/util/List;");
//	if (!getListSize)
		getListSize = env->GetMethodID(class_List, "size", "()I");
//	if (!getListObject)
		getListObject = env->GetMethodID(class_List, "get","(I)Ljava/lang/Object;");
//	if (!class_Enum)
		class_Enum = env->FindClass("Ljava/lang/Enum;");
//	if (!getEnumName)
		getEnumName = (*env).GetMethodID(class_Enum, "name","()Ljava/lang/String;");
}
static void iniGepAlgRunId(JNIEnv *env) {
//	if (!class_GepAlgRun)
		class_GepAlgRun = env->FindClass("Ldomain/core/algOutput/GepAlgRun;");
//	if (!gepAlgConfigurationID)
		gepAlgConfigurationID = env->GetFieldID(class_GepAlgRun,"gepAlgConfiguration","Ldomain/core/algconfiguration/GepAlgConfiguration;");
//	if (!dataSetID)
		dataSetID = env->GetFieldID(class_GepAlgRun, "dataSet","Ldomain/core/algInputDataProcess/DataSet;");
//	if (!populationsID)
		populationsID = env->GetFieldID(class_GepAlgRun, "populations","Ljava/util/List;");
//	if (!getCurrentPopulationID)
		getCurrentPopulationID = env->GetMethodID(class_GepAlgRun,"getCurrentPopulation", "()Ldomain/core/algOutput/Population;");
}
static void iniDataSetId(JNIEnv * env) {
//	if (!class_DataSet)
		class_DataSet = env->FindClass(
				"Ldomain/core/algInputDataProcess/DataSet;");
//	if (!rowNumID)
		rowNumID = env->GetFieldID(class_DataSet, "rowNum",
				"Ljava/lang/Integer;");
//	if (!columnNumID)
		columnNumID = env->GetFieldID(class_DataSet, "columnNum",
				"Ljava/lang/Integer;");
//	if (!toDeepArrayID)
		toDeepArrayID = env->GetMethodID(class_DataSet, "toDeepArray", "()[[F");
}
static void iniPopulationId(JNIEnv * env) {
//	if (!class_Population)
		class_Population = env->FindClass("Ldomain/core/algOutput/Population;");
//	if (!getNormalGeneTypeID)
		getNormalGeneTypeID = env->GetMethodID(class_Population,"getNormalGeneType", "()[[C");
//	if (!getHomeoticGeneTypeID)
		getHomeoticGeneTypeID = env->GetMethodID(class_Population,"getHomeoticGeneType", "()[[C");
//	if (!getNormalGeneIndexID)
		getNormalGeneIndexID = env->GetMethodID(class_Population,"getNormalGeneIndex", "()[[C");
//	if (!getHomeoticGeneIndexID)
		getHomeoticGeneIndexID = env->GetMethodID(class_Population,"getHomeoticGeneIndex", "()[[C");
//	if(!setFitnessID)
		setFitnessID = env->GetMethodID(class_Population, "setFitness","([F)V");
//	if(!setFittedValueID)
		setFittedValueID = env->GetMethodID(class_Population,"setFittedValue", "([F)V");
	//if(!setGeneNumID)
		setGeneNumID = env->GetMethodID(class_Population, "setGeneNum","([C)V");
}
static void iniConf(JNIEnv * env) {
//	if (!gepAlgConfigurationClass)
		gepAlgConfigurationClass = env->FindClass("Ldomain/core/algconfiguration/GepAlgConfiguration;");
//	if (!individualConfigurationID)
		individualConfigurationID = env->GetFieldID(gepAlgConfigurationClass,"individualConfiguration","Ldomain/core/algconfiguration/IndividualConfiguration;");
//	if (!individualConfClass)
		individualConfClass = env->FindClass("Ldomain/core/algconfiguration/IndividualConfiguration;");
//	if (!populationSizeID)
		populationSizeID = env->GetFieldID(individualConfClass,"individualNumber", "Ljava/lang/Integer;");
//	if (!geneConfigurationID)
		geneConfigurationID = env->GetFieldID(individualConfClass,"geneConfiguration","Ldomain/core/algconfiguration/GeneConfiguration;");
//	if (!geneConfigurationClass)
		geneConfigurationClass = env->FindClass("Ldomain/core/algconfiguration/GeneConfiguration;");
//	if (!normalGeneNumberID)
		normalGeneNumberID = env->GetFieldID(geneConfigurationClass,"normalGeneNumber", "Ljava/lang/Integer;");
//	if (!normalGeneHeaderLengthID)
		normalGeneHeaderLengthID = env->GetFieldID(geneConfigurationClass,"normalGeneHeaderLength", "Ljava/lang/Integer;");
//	if (!homeoticGeneNumberID)
		homeoticGeneNumberID = env->GetFieldID(geneConfigurationClass,"homeoticGeneNumber", "Ljava/lang/Integer;");
//	if (!homeoticGeneHeaderLengthID)
		homeoticGeneHeaderLengthID = env->GetFieldID(geneConfigurationClass,"homeoticGeneHeaderLength", "Ljava/lang/Integer;");
//	if (!normalGeneLengthID)
		normalGeneLengthID = env->GetFieldID(geneConfigurationClass,"normalGeneLength", "Ljava/lang/Integer;");
//	if (!homeoticGeneLengthID)
		homeoticGeneLengthID = env->GetFieldID(geneConfigurationClass,"homeoticGeneLength", "Ljava/lang/Integer;");
//	if (!selectionRangeID)
		selectionRangeID = env->GetFieldID(gepAlgConfigurationClass,"selectionRange", "Ljava/lang/Float;");
//	if(!accuracyID)
		accuracyID= env->GetFieldID(gepAlgConfigurationClass,"accuracy", "Ljava/lang/Float;");
}
static void iniString(JNIEnv * env){
	strClass = env->FindClass("Ljava/lang/String;");
	ctorID = env->GetMethodID(strClass, "<init>","([BLjava/lang/String;)V");
	subStringID = env->GetMethodID(strClass, "substring","(II)Ljava/lang/String;");
	toCharArrID = env->GetMethodID(strClass, "toCharArray", "()[C");
}
static jobject getIndividualConfObject(JNIEnv* env, jobject gepAlgRun) {
	jobject algConfObject = env->GetObjectField(gepAlgRun,
			gepAlgConfigurationID);
	jobject individualConfObject = env->GetObjectField(algConfObject,
			individualConfigurationID);
	return individualConfObject;
}
static jobject getGeneConfObj(JNIEnv* env, jobject gepAlgRun) {
	jobject individualConfObject = getIndividualConfObject(env, gepAlgRun);
	jobject geneConfObject = env->GetObjectField(individualConfObject,
			geneConfigurationID);
	return geneConfObject;
}
static jint getIntValueOnGeneConf(JNIEnv* env, jobject gepAlgRun,
		jfieldID field) {
	jobject geneConfObject = getGeneConfObj(env, gepAlgRun);
	jobject fieldObj = env->GetObjectField(geneConfObject, field);
	jint num = env->CallIntMethod(fieldObj, integerValueMethod);
	return num;
}
static jint getIntValueOnDataSet(JNIEnv* env, jobject gepAlgRun,
		jfieldID field) {
	jobject dataSetObj = env->GetObjectField(gepAlgRun, dataSetID);
	jobject obj = env->GetObjectField(dataSetObj, field);
	jint num = env->CallIntMethod(obj, integerValueMethod);
	return num;
}
void iniAllId(JNIEnv * env) {
	iniSysId(env);
	iniGepAlgRunId(env);
	iniDataSetId(env);
	iniPopulationId(env);
	iniConf(env);
	iniString(env);
}
static char** createGeneArray(int populationSize,int columns){
	char** geneArray = (char**)malloc(sizeof(char*)*populationSize);
	geneArray[0]=(char*)malloc(sizeof(char)*populationSize*columns);
	for (int i = 1; i < populationSize; i++)
		geneArray[i] = geneArray[i-1]+columns;
	return geneArray;
}
static float** createDataArray(int rows,int columns){
	float** array=(float**)malloc(sizeof(float*)*rows);
	array[0]=(float*)malloc(sizeof(float)*rows*columns);
	for(int i=1;i<rows;i++)
		array[i]=array[i-1]+columns;
	return array;
}
static char** createGene(JNIEnv *env, jobject gepAlgRun,int rows,int columns,jobjectArray geneInfo){
	jcharArray normalGeneTypesCol;
	char** gene = createGeneArray(rows,columns);
	for (int i = 0; i < rows; i++) {
		normalGeneTypesCol = (jcharArray) env->GetObjectArrayElement(geneInfo, i);
		jchar *coldata = env->GetCharArrayElements(normalGeneTypesCol, NULL);
		for (int j = 0; j < columns; j++) {
//			printf("%d\n",coldata[j]);
//			fflush(stdout);
			gene[i][j] = coldata[j];
		}
		env->ReleaseCharArrayElements(normalGeneTypesCol,coldata,JNI_ABORT);
	}
//	printf("start\n");
	return gene;
}
float** createDataSet(JNIEnv *env, jobject gepAlgRun,int rowNum,int columNum) {
	jfloatArray dataSetCpCols;
	jobject dataSet = env->GetObjectField(gepAlgRun, dataSetID);
	jobjectArray dataSetArr = (jobjectArray) env->CallObjectMethod(dataSet,toDeepArrayID);
	float** dataSetCp = createDataArray(rowNum,columNum);
	//赋值----------------------------------------------------------
	for (int i = 0; i < rowNum; i++) {
		dataSetCpCols = (jfloatArray)env->GetObjectArrayElement(dataSetArr, i);
		jfloat *coldata = env->GetFloatArrayElements(dataSetCpCols, NULL);
		for (int j = 0; j < columNum; j++) {
//			printf("%f\t ",coldata[j]);
//			fflush(stdout);
			dataSetCp[i][j] = coldata[j];
		}
		env->ReleaseFloatArrayElements(dataSetCpCols,coldata,JNI_ABORT);
	}
	return dataSetCp;
}
char** createNormalGeneType(JNIEnv *env, jobject gepAlgRun,int populationSize,int normalGeneLength,int normalGeneNumber) {
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	jobjectArray normalGeneTypes = (jobjectArray) env->CallObjectMethod(currentPopulation, getNormalGeneTypeID);
	return createGene(env,gepAlgRun,populationSize,normalGeneLength*normalGeneNumber,normalGeneTypes);
}
char** createHomeoticGeneType(JNIEnv *env, jobject gepAlgRun,int populationSize,int homeoticGeneLength,int homeoticGeneNum) {
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	jobjectArray homeoticGeneTypes = (jobjectArray) env->CallObjectMethod(currentPopulation, getHomeoticGeneTypeID);
	return createGene(env,gepAlgRun,populationSize,homeoticGeneLength*homeoticGeneNum,homeoticGeneTypes);
}

char** createNormalGeneIndex(JNIEnv* env, jobject gepAlgRun,int populationSize,int normalGeneLength,int normalGeneNumber) {
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	jobjectArray normalGeneIndex = (jobjectArray) env->CallObjectMethod(currentPopulation, getNormalGeneIndexID);
	return createGene(env,gepAlgRun,populationSize,normalGeneLength*normalGeneNumber,normalGeneIndex);
}

char** createHomeoticGeneIndex(JNIEnv* env, jobject gepAlgRun,int populationSize,int homeoticGeneLength,int homeoticGeneNum) {
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	jobjectArray homeoticGeneIndex = (jobjectArray) env->CallObjectMethod(currentPopulation, getHomeoticGeneIndexID);
	return createGene(env,gepAlgRun,populationSize,homeoticGeneLength*homeoticGeneNum,homeoticGeneIndex);
}
int getPopulationSize(JNIEnv* env, jobject gepAlgRun) {
	jobject individualConfObject = getIndividualConfObject(env, gepAlgRun);
	jobject populationSizeObject = env->GetObjectField(individualConfObject,
			populationSizeID);
	jint size = env->CallIntMethod(populationSizeObject, integerValueMethod);
	return size;
}
int getNormalGeneNum(JNIEnv* env, jobject gepAlgRun) {
	jint num = getIntValueOnGeneConf(env, gepAlgRun, normalGeneNumberID);
	return num;
}
int getHomeoticGeneNum(JNIEnv* env, jobject gepAlgRun) {
	jint num = getIntValueOnGeneConf(env, gepAlgRun, homeoticGeneNumberID);
	return num;
}
int getNormalGeneHeaderLength(JNIEnv* env, jobject gepAlgRun) {
	jint num = getIntValueOnGeneConf(env, gepAlgRun, normalGeneHeaderLengthID);
	return num;
}
int getNormalGeneLength(JNIEnv* env, jobject gepAlgRun) {
	jint num = getIntValueOnGeneConf(env, gepAlgRun, normalGeneLengthID);
	return num;
}
int getHomeoticGeneHeaderLength(JNIEnv* env, jobject gepAlgRun) {
	jint num = getIntValueOnGeneConf(env, gepAlgRun,
			homeoticGeneHeaderLengthID);
	return num;
}
int getHomeoticGeneLength(JNIEnv* env, jobject gepAlgRun) {
	jint num = getIntValueOnGeneConf(env, gepAlgRun, homeoticGeneLengthID);
	return num;
}
int getRowNum(JNIEnv* env, jobject gepAlgRun) {
	jint rowNum = getIntValueOnDataSet(env, gepAlgRun, rowNumID);
	return rowNum;
}
int getColumnNum(JNIEnv* env, jobject gepAlgRun) {
	jint rowNum = getIntValueOnDataSet(env, gepAlgRun, columnNumID);
	return rowNum+1;
}
float getAccuracy(JNIEnv* env, jobject gepAlgRun){
	jobject algConfObject = env->GetObjectField(gepAlgRun,
			gepAlgConfigurationID);
	jobject accuracyObject = env->GetObjectField(algConfObject,
			accuracyID);
	jfloat accuracy=env->CallFloatMethod(accuracyObject, floatValueMethod);;
	return accuracy;
}
float getSelectionRange(JNIEnv* env, jobject gepAlgRun) {
	jobject algConfObject = env->GetObjectField(gepAlgRun,
			gepAlgConfigurationID);
	jobject selectionRangeObject = env->GetObjectField(algConfObject,
			selectionRangeID);
	jfloat num = env->CallFloatMethod(selectionRangeObject, floatValueMethod);
	return num;
}
void toJavaFitness(JNIEnv* env, jobject gepAlgRun,float *fitness) {
	long popuSize = getPopulationSize(env, gepAlgRun);
	jfloatArray fitnessInJava = env->NewFloatArray(popuSize);
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	env->SetFloatArrayRegion(fitnessInJava, 0, popuSize, fitness);
	env->CallVoidMethod(currentPopulation, setFitnessID, fitnessInJava);
	env->DeleteLocalRef(fitnessInJava);
}
void toJavaFittedValue(JNIEnv* env, jobject gepAlgRun,float** fittedvalue) {
	int rowNum = getRowNum(env, gepAlgRun);
	int popuSize = getPopulationSize(env, gepAlgRun);
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	float*temp2DArr = (float*) malloc(rowNum * popuSize * sizeof(float));
	int index = 0;
	for (int i = 0; i < rowNum; i++) {
		for (int j = 0; j < popuSize; j++) {
			temp2DArr[index] = fittedvalue[i][j];
			index++;
		}
	}
	jfloatArray fittedValueInJava = env->NewFloatArray(rowNum * popuSize);
	env->SetFloatArrayRegion(fittedValueInJava, 0, rowNum * popuSize,temp2DArr);
	env->CallVoidMethod(currentPopulation, setFittedValueID,(jobjectArray) fittedValueInJava);
	env->DeleteLocalRef(fittedValueInJava );
}
void toJavaHomeoticGeneIndex(JNIEnv* env, jobject gepAlgRun,char* numofhometic) {
	int numofpopulation = getPopulationSize(env, gepAlgRun);
	jobject currentPopulation = env->CallObjectMethod(gepAlgRun,getCurrentPopulationID);
	//将char*转为JString再转为jcharArray
	jbyteArray bytes = env->NewByteArray(numofpopulation);
	env->SetByteArrayRegion(bytes, 0, numofpopulation,(jbyte*) numofhometic);
	jstring encoding = env->NewStringUTF("utf-8");
	jstring strTemp = (jstring) env->NewObject(strClass, ctorID, bytes,encoding);
	strTemp = (jstring) env->CallObjectMethod(strTemp, subStringID, 0,numofpopulation);
	jcharArray geneNumInJava = (jcharArray) env->CallObjectMethod(strTemp,toCharArrID);
	env->CallVoidMethod(currentPopulation, setGeneNumID, geneNumInJava);
	env->DeleteLocalRef(bytes);
	env->DeleteLocalRef(encoding);
	env->DeleteLocalRef(strTemp);
	env->DeleteLocalRef(geneNumInJava);
}
#endif /* COPYTOGPU_H_ */
