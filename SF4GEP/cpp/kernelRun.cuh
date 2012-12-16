/*
 * cudaKernel.cuh
 *
 *  Created on: 2012-10-6
 *      Author: Alexander
 */

#ifndef CUDAKERNEL_CUH_
#define CUDAKERNEL_CUH_
#include "device_launch_parameters.h"
extern __shared__ float shared[];
__device__ void calc_gene(char*,char*,float*,float*,int,size_t,int type);
__device__ int get_fun_arity(int i);
__device__ void exec_fun(float* data,float* result,char* flag,char* index,char* type,int loc,int row_width,int current_column,int e_length,int command);
__device__ float get_fun_result(int op,float a,float b);
__device__ int get_effictive_len(char *type,char *index);
__device__ int get_last_func(char *type,char *index,char *flag,int length);
__device__ float assign(char* type,char* index,float* data,float* result,int row_width,int current_column,int e_length);
__device__ float calc_gene_fitness(float* value,float* data_set,size_t pitchDT,float M,float accuracy,int row,int column);
__device__ int calc_final_fitness(float* value,int size,float* result);
__global__ void kernel(char *normal_gene_types,char *normal_gene_indexs,char *homeotic_gene_types,char *homeotic_gene_indexs,float *data,
	size_t pitchNG,size_t pitchNI,size_t pitchHG,size_t pitchHI,size_t pitchDT,int row_size,int col_size,float *cpu_fit,
	char *re_hometic_gene_num,float *fitted_value,size_t pitchFV,int normal_gene_length,int normal_gene_num,
	int homeotic_gene_length,int homeotic_gene_num,float M,float accuracy)
{
	int i;
	int bid=blockIdx.x;//个体
	int tid=threadIdx.x;//基因
	int normal_indiviudal_type=bid*pitchNG;
	int normal_individual_index=bid*pitchNI;
	int homeotic_individual_type=bid*pitchHG;
	int homeotic_individual_index=bid*pitchHI;
	int normal_gene_ptr=tid*normal_gene_length;
	int homeotic_gene_ptr=tid*homeotic_gene_length;
	char *thread_normal_gene_type=normal_gene_types+normal_indiviudal_type+normal_gene_ptr;
	char *thread_normal_gene_index=normal_gene_indexs+normal_individual_index+normal_gene_ptr;
	char *thread_hometic_gene_type=homeotic_gene_types+homeotic_individual_type+homeotic_gene_ptr;
	char *thread_hometic_gene_index=homeotic_gene_indexs+homeotic_individual_index+homeotic_gene_ptr;
	float* normal_gene_value=(float*)(shared+tid*row_size);
	float* homeotic_gene_value=(float*)(shared+normal_gene_num*row_size+tid*row_size);
	float* fitness_value=(float*)(shared+normal_gene_num*row_size+homeotic_gene_num*row_size+tid);
	if(tid<normal_gene_num){
		calc_gene(thread_normal_gene_type,thread_normal_gene_index,normal_gene_value,data,row_size,pitchDT,0);
	}
	__syncthreads();
	if(tid<homeotic_gene_num){
		calc_gene(thread_hometic_gene_type,thread_hometic_gene_index,homeotic_gene_value,shared,row_size,pitchDT,1);
		*(fitness_value)=calc_gene_fitness(homeotic_gene_value,data,pitchDT,M,accuracy,row_size,col_size);
	}
	__syncthreads();
		if(tid==0){
			*(re_hometic_gene_num+bid)=(char)calc_final_fitness(fitness_value-tid,homeotic_gene_num,cpu_fit+bid);
			float* fitness_start=(float*)(shared+normal_gene_num*row_size+*(re_hometic_gene_num+bid)*row_size);
			for(i=0;i<row_size;i++)
				*(fitted_value+bid+i)=fitness_start[i];
//			printf("%f\n",*(cpu_fit+bid));
		}
	__syncthreads();
}
__device__ void calc_gene(char *type,char *index,float* fitness,float* data,int row_size,size_t pitchDT,int command){
	int length=get_effictive_len(type,index);
	int i,j,loc,arity,back_length;
	float* data_row;
	back_length=length;
	for(int i=0;i<row_size;i++){
		float *result=new float[length];
		char *flag=new char[length];
		for(j=0;j<length;j++)
			flag[j]=0;
		if(command==0)
			data_row=(float*)((char*)data+pitchDT*i);
		while(length>1){
			loc=get_last_func(type,index,flag,length);
			arity=get_fun_arity(index[loc]);
			if(command==0)
				exec_fun(data_row,result,flag,index,type,loc,row_size,i,length,command);
			else if(command==1)
				exec_fun(data,result,flag,index,type,loc,row_size,i,length,command);
			else
				printf("error\n");
			length-=arity;
		}
		fitness[i]=result[0];
		length=back_length;
		delete flag;
		delete result;
	}
}
__device__ float calc_gene_fitness(float* value,float* data_set,size_t pitchDT,float M,float accuracy,int row,int column){
	int i;
	float sum,temp;
	float* f;
	sum=0;
	for(i=0;i<row;i++){
		if(isnan(value[i])||isinf(value[i]))
			return 0;
		f=(float*)((char*)data_set+pitchDT*i);
		temp=fabs(value[i]-f[column-1]);
		if(temp<accuracy)
			temp=0;
		sum+=M-temp;
	}
	if(sum<=0)
		sum=0;
	return sum;
}
__device__ int calc_final_fitness(float* value,int size,float* result){
	int i,index;
	int max=-1;
	for(i=0;i<size;i++)
		if(value[i]>=max){
			max=value[i];
			index=i;
		}
	*result=max;
	return index;
}
__device__ int get_effictive_len(char *type,char *index)
{
	int length=1;
	int arity;
	for(int i=0;i<length;i++){
		if(type[i]==1){
			arity=get_fun_arity(index[i]);
			length+=arity;
		}
	}
	return length;
}
__device__ int get_last_func(char *type,char *index,char *flag,int length){
	int ptr,i;
	ptr=-1;
	for(i=length-1;i>=0;i--){
		ptr=i;
		if(type[ptr]==1){
			if(flag[ptr]==0)
				break;
		}
	}
	return ptr;
}
__device__ int get_fun_arity(int i)
{
	int x;
	switch(i)
	{
	case 0://'+'
		x=2;break;
	case 1://'-'
		x=2;break;
	case 2://'*'
		x=2;break;
	case 3://'/'
		x=2;break;
	default:
		x=0;
		printf("error\n");
		break;
	}
	return x;
}
__device__ void exec_fun(float* data,float* result,char* flag,char* index,char* type,int loc,int row_width,int current_column,int e_length,int command){
	char func=index[loc];
	int arity=get_fun_arity(func);
	float para_a,para_b,value;
	if(arity==2){
		para_a=assign(type,index,data,result,row_width,current_column,e_length-2);
		para_b=assign(type,index,data,result,row_width,current_column,e_length-1);
	}
	else
		printf("error\n");
	value=get_fun_result(func,para_a,para_b);
	result[loc]=value;
	flag[loc]=1;
}
__device__ float assign(char* type,char* index,float* data,float* result,int row_width,int current_column,int e_length){
	if(type[e_length]==0){
		return data[index[e_length]];
	}
	else if(type[e_length]==1){
		return result[e_length];
	}
	else if(type[e_length]==2){
		return data[index[e_length]*row_width+current_column];
	}
	else
		printf("error");
}
__device__ float get_fun_result(int op,float a,float b)
{
	float result=0;
	switch(op)
	{
	case 0:
		result=a+b;break;
	case 1:
		result=a-b;break;
	case 2:
		result=a*b;break;
	case 3:
		result=a/b;break;//处理b为0的情况
	default:
		printf("undefine\n");
	}
	return result;
}

#endif /* CUDAKERNEL_CUH_ */
