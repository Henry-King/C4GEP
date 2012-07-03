package domain.service.alg.userdefined.modify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.ObjectCopy;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Computable;
import domain.core.algmodel.genecomponent.Constant;
import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.genecomponent.Variable;
import domain.core.algmodel.individualcomponent.Gene;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.HomeoticGeneHeader;
import domain.core.algmodel.individualcomponent.HomeoticGeneTail;
import domain.core.algmodel.individualcomponent.NormalGene;
import domain.core.algmodel.individualcomponent.NormalGeneHeader;
import domain.core.algmodel.individualcomponent.NormalGeneTail;
import domain.service.alg.configuration.Modifying;

public class DefaultModifying extends Modifying{
	private GepAlgorithm gepAlgorithm;
	private int funcNumber;	//方法数
	private int variableNumber;	//变量数
	private int constantNumber;	//常量数
	private int funcntionVariablConstantSum;	//方法+变量+常量 总和
	private int variableConstantSum;	//变量+常量 总和
	private int functionConstantSum;	//方法+常量 总和
	public DefaultModifying() {
		super("默认变异方式");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		gepAlgorithm=myGepAlgorithm;
		Population population=gepAlgorithm.getPopulationQueue().getLast();
		
		try {
			mutate(population);
			isTransport(population);
			risTransport(population);
			geneTransport(population);
			onePointRecombine(population);
			twoPointRecombine(population);
			geneRecombine(population);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 变异(内部方法未封装版本，看起来很大，Random要new多次，设置成static吧)
	 * 变异概率 mutateRate
	 * 变异可以发生在染色体内的任何位置
	 * 在头部中，任何符号都可以变成符号或者终点；在尾部中，终点只能够变成终点。
	 * 变异分为普通变异和中性变异
	 * @sec DefaultModifying#mutate()
	 * @param population 一次变异修饰一个种群
	 * @throws CloneNotSupportedException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void mutate(Population population){
		
		Population mutatePopulation = population;	//变异的种群
		
		Random mutateRand = new Random();	//指定触发变异的随机数
		Random functionRand=new Random();
		Random variableRand=new Random();
		Random typeRandom=new Random();
		int type;

		NormalGeneHeader normalGeneHeader;
		NormalGeneTail normalGeneTail;
		HomeoticGeneHeader homeoticGeneHeader;
		HomeoticGeneTail homeoticGeneTail;
		
		List<GenePiece> genePieces;
		List<Computable> computables;//这个变量命名有点蛋疼，姑且加个s了
		
		List<Variable> variableList=gepAlgorithm.getVariableList();	//变量集合
		List<Function> functionList=gepAlgorithm.getFunctionList();	//函数集合
		
		Constant constant;	//常量
		Function function;	//函数
		Variable variable;	//变量
		
		funcNumber = functionList.size();
		variableNumber = variableList.size();
		constantNumber = 1;	//我想在这里通过constantList获得常量数，可以是不以呀不可以
		funcntionVariablConstantSum=funcNumber+variableNumber+constantNumber;
		variableConstantSum=variableNumber+constantNumber;
		functionConstantSum=funcNumber+constantNumber;			
		
		for(Individual mutateIndividual:mutatePopulation.getIndividuals()){	//遍历种群中的每个染色体
			
			for(NormalGene mutateNormalGene:mutateIndividual.getNormalGeneList()){	//遍历普通染色体中的每个基因
				
				/*Creator中有获得函数、变量的长度的方法，而Modifying没有，每次都有着赋值*/
		
				
				/*遍历普通基因头部*/
				normalGeneHeader = mutateNormalGene.getHeader();
				genePieces = normalGeneHeader.getGenePieces();
				for(int i=0;i<gepAlgorithm.getNormalHeaderLength();i++){
					//genePiece = genePieces.get(i);
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//当随机产生的Float值在0~mutateRate之间时，触发变异
						type=typeRandom.nextInt(funcntionVariablConstantSum);
						if(type<funcNumber){
							//变异函数
							function = ObjectCopy.newInstance((Function)functionList.get(functionRand.nextInt(functionList.size())));
							genePieces.set(i, function);
						}
						else if(type>=funcNumber&&type<funcNumber+variableNumber){
							//变异变量
							variable = ObjectCopy.newInstance((Variable) variableList.get(variableRand.nextInt(variableList.size())));
							genePieces.set(i, variable);
						}
						else {
							constant=new Constant(mutateNormalGene.getConstantList(), getConstanListSize());
							genePieces.set(i, constant);
						}
					}//end if
				}
				
				/*遍历普通基因尾部*/
				normalGeneTail = mutateNormalGene.getTail();
				computables = normalGeneTail.getComputable();
				for(int i=0;i<gepAlgorithm.getNormalTailLength();i++){
					//genePiece = genePieces.get(i);
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//当随机产生的Float值在0~mutateRate之间时，触发变异
						type=typeRandom.nextInt(variableConstantSum);
						if(type<variableNumber){
							//变异变量
							variable = ObjectCopy.newInstance((Variable) variableList.get(variableRand.nextInt(variableList.size())));
							computables.set(i, variable);
						}
						else {
							//变异常量
							constant=new Constant(mutateNormalGene.getConstantList(), getConstanListSize());
							constant.generateConstantIndex();
							computables.set(i, constant);
						}
					}
				}
			}
				
			for(HomeoticGene mutateHomeoticGene:mutateIndividual.getHomeoticGeneList()){	//遍历同源染色体中的每个基因
				functionList = gepAlgorithm.getFunctionList();

				/*遍历同源基因头部*/
				homeoticGeneHeader = mutateHomeoticGene.getHeader();
				genePieces = homeoticGeneHeader.getGenePieces();
				if(mutateRand.nextFloat()<getMutateRate()){
					function = ObjectCopy.newInstance((Function)functionList.get(functionRand.nextInt(functionList.size())));
					genePieces.set(0, function);
				}
				for(int i=1;i<gepAlgorithm.getNormalHeaderLength();i++){
					//genePiece = genePieces.get(i);
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//当随机产生的Float值在0~mutateRate之间时，触发变异
						/*划分函数、变量、常量的区间，使用Random随机取值，赋给type，由type落在的区间决定随机产生的是--函数、或者变量、或者常量*/
						type=typeRandom.nextInt(functionConstantSum);	//同源染色体，头部无变量	
						
						if(type<funcNumber){
							//变异函数
							function = ObjectCopy.newInstance((Function)functionList.get(functionRand.nextInt(functionList.size())));
							genePieces.set(i, function);
						} else {
							//变异常量
							//申申，我觉得常量这一块写的很别扭。
							//有些操作是不是应该放在constantList里面呢
							constant=new Constant(gepAlgorithm.getNormalGeneNumber());
							genePieces.set(i, constant);
						}

					} //end if
				}
				/*遍历同源基因尾部*/
				homeoticGeneTail = mutateHomeoticGene.getTail();
				computables = homeoticGeneTail.getComputable();
				for(int i=0;i<gepAlgorithm.getNormalTailLength();i++){
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//当随机产生的Float值在0~mutateRate之间时，触发变异
						//变异常量
						constant=new Constant(gepAlgorithm.getNormalGeneNumber());
						computables.set(i, constant);
					}
				}
			}//end of for mutatehomeoticGene
		} // end of for mutateIndividual
				
	}

	/** IS 插入序列元素的转座
	 * 起始位置上是函数或终结点的短片段转座到基因的头中除根部以外的位置
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void isTransport(Population population) throws ClassNotFoundException, IOException{	//种群
		Population isPopulation  = population;
		Random isRand = new Random();	//判断是否发生IS转座的概率
		Random isGetPosRand = new Random();	//随机选择获取IS转座元素的位置
		Random isSetPosRand = new Random();	//随机选择发生IS转座的位置
		Random isEleRand = new Random();	//随机选择IS元素的长度
		//Random isNormalOrHomeoticRand = new Random();	//随机选择普通基因还是同源基因
		Random isGeneRand = new Random();	//随机获取基因的位置
		
		super.getIsTransportRate();	//获取IS的转座概率
		super.getIsElements();	//获取IS元素的长度列表
		
		List<GenePiece> isElementList = new ArrayList<GenePiece>();	//另外存放一份IS元素的列表
		List<GenePiece> isList;
		
		int isLength;	//发生IS转座的基因的长度
		int isElementLength;
		int isGetPosition;
		int isSetPosition;
		
		NormalGene normalGene;
		NormalGeneHeader normalGeneHeader;
		
		List<NormalGene> normalGeneList;
		/**	
		 * 转座算子随机地选取染色体，IS 元素，目标位置，以及转座子的长度
		 * IS 范围在单个染色体内
		 * IS Rate 判断该染色体是否发生IS转座
		 */
		for(Individual isIndividual:isPopulation.getIndividuals()){		//遍历染色体
			if(isRand.nextFloat()<super.getIsTransportRate()){	//如果该染色体发生转座，这里一个染色体只发生一次转座
				isLength = gepAlgorithm.getIndividualLength();
				isList = isIndividual.getContainedGenePieces();
				isElementLength = super.getIsElements().get(isEleRand.nextInt(super.getIsElements().size()));	//获取IS元素的长度
				isGetPosition = isGetPosRand.nextInt(isLength);	//获取IS转座元素的位置
				
			
				/*获取IS元素*/
				for(int i=0;i<isElementLength&&isGetPosition<isLength;i++){
					isElementList.add(ObjectCopy.newInstance(isList.get(isGetPosition++)));	//先从染色体列表中选择发生IS转座的位置的基因,之后添加到转座子列表中
				}
				
				
				/*向染色体中的某个基因中加入IS元素*/
				normalGeneList = isIndividual.getNormalGeneList();
				normalGene = normalGeneList.get(isGeneRand.nextInt(normalGeneList.size()));	//从普通基因集合中随机取得一个基因

				normalGeneHeader = normalGene.getHeader();
				List<GenePiece> headerGeneList = normalGeneHeader.getGenePieces();
				isSetPosition = 1+isSetPosRand.nextInt(gepAlgorithm.getNormalHeaderLength()-1);	//排除基因起始位置
				
				List<GenePiece> tmpList = new ArrayList<GenePiece>();
				int i;
				for(i=isSetPosition;i<headerGeneList.size();i++){	//将IS转座位置之后的元素保存在tmp队列中
					tmpList.add( ObjectCopy.newInstance(headerGeneList.get(i)));
				}
				
				for(i=isSetPosition;i<headerGeneList.size()&&isElementList.size()>0;i++){	//将IS元素替换到头部中
					headerGeneList.set(i, ObjectCopy.newInstance(isElementList.remove(0)));
				}
				
				for(;i<headerGeneList.size();i++){
					headerGeneList.set(i, tmpList.remove(0));
				}
			}
		}
	}
	
	/** RIS 
	 * 根转座
	 * 起始位置上是函数的短片段转座到基因的根部
	 * 
	 * 从一个函数开始,选自头部分中的序列
	 * 在头中任选一点，沿基因向后查找，直到发现一个函数为止。该函数成为RIS 元素的起始位置
	 * 如果找不到函数，则变换不作任何操作。
	 * 算子随机选取染色体，需要修饰的基因，RIS 元素及其长度
	 * 一般地，可以采用0.1 的根变换概率和由三组不同长度的RIS 元素集合
	 * 
	 * 
	 * 由于尾部（不论是普通的还是同源的），都不含有函数，
	 * 因此可以从头开始，直接遍历染色体中的每一个基因位，
	 * 知道找到了符合条件的基因（函数基因）为止
	 * 
	 * 
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void risTransport(Population population) throws ClassNotFoundException, IOException{
		
		Population risPopulation  = population;
		Random risRand = new Random();
		Random risGeneRand = new Random();
		Random risSetPosRand = new Random();
		
		super.getRisTransportRate();	//获取IS的转座概率
		super.getRisElements();	//获取IS元素的长度列表
		
		
		List<GenePiece> geneList;
		List<GenePiece> risElementList = new ArrayList<GenePiece>();
		
		int risElementLength;	//发生RIS转座的染色体长度
		int risLength;	// 染色体长度
		int risSetPosition;
		
		NormalGene normalGene;
		
		NormalGeneHeader normalGeneHeader;
		
		
		List<NormalGene> normalGeneList;		
		
		/**
		 * 从染色体的中基因的头部开始查找
		 * （普通基因和同源基因的头部中都含有函数）
		 */
		for(Individual risIndividual:risPopulation.getIndividuals()){	//遍历染色体
			if(risRand.nextFloat()<super.getRisTransportRate()){	//判断该染色体是否发生
				boolean noFind = true;	//标记是否找到函数
				geneList = risIndividual.getContainedGenePieces();	
				risLength = gepAlgorithm.getIndividualLength();
				risElementLength = super.getIsElements().get(risRand.nextInt(super.getRisElements().size()));	//获取RIS元素的长度
				
				
				/*获取RIS元素*/
				for(int i=0;i<risLength&&noFind;i++){
					if(Function.class.isInstance(geneList.get(i))){	//判断是否是函数
						
						
						for(int j=0;j<risElementLength&&i<risLength&&noFind;j++){
							risElementList.add(ObjectCopy.newInstance(geneList.get(i++)));
						}
						noFind = false;
						
					}
				}
					
				
				/*发生RIS转座*/
				normalGeneList = risIndividual.getNormalGeneList();
				normalGene = normalGeneList.get(risGeneRand.nextInt(normalGeneList.size()));	//从普通基因集合中随机取得一个基因
				normalGeneHeader = normalGene.getHeader();
				risSetPosition = risSetPosRand.nextInt(gepAlgorithm.getNormalHeaderLength());	//可以发生在染色体的任意位置
						
				List<GenePiece> headerGeneList = normalGeneHeader.getGenePieces();
				risSetPosition = risRand.nextInt(gepAlgorithm.getNormalGeneLength());	
				
				List<GenePiece> tmpList = new ArrayList<GenePiece>();
				int i;
				for(i=risSetPosition;i<headerGeneList.size();i++){	//将IS转座位置之后的元素保存在tmp队列中
					tmpList.add( ObjectCopy.newInstance(headerGeneList.get(i)));
				}
				
				for(i=risSetPosition;i<headerGeneList.size()&&risElementList.size()>0;i++){	//将IS元素替换到头部中
					headerGeneList.set(i, ObjectCopy.newInstance(risElementList.remove(0)));
				}
				
				for(;i<headerGeneList.size();i++){
					headerGeneList.set(i, tmpList.remove(0));
				}										
			}
				
		}

	}

	
	/** 基因转座 
	 * 整个基因转座到染色体的起始位置
	 * 
	 * 转座子是整个基因
	 * 
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void geneTransport(Population population) throws ClassNotFoundException, IOException{
		Population gtPopulation  = population;
		Random gtRand = new Random();	//随机发生基因转座的概率
		Random gtGeneRand = new Random();	//随机选择发生时转座的基因		
		
		super.getGeneTransportRate();

		NormalGene gtGene;	
		
		List<NormalGene> normalGeneList;
		
		for(Individual gtIndividual:gtPopulation.getIndividuals()){	//遍历染色体
			
			if(gtRand.nextFloat()<super.getGeneTransportRate()){	//判断该染色体是否发生基因转座
				normalGeneList = gtIndividual.getNormalGeneList();
				gtGene = ObjectCopy.newInstance(normalGeneList.remove(gtGeneRand.nextInt(gepAlgorithm.getNormalGeneNumber())));
				normalGeneList.add(0, ObjectCopy.newInstance(gtGene));		
			}
		}
	}
	
	
	/** 单点重组 
	 * 
	 * 这里的选点，包含了有同源染色的情况，
	 * 由于是将选择的点之后的所有基因都进行互换，
	 * 因此，不会发生出现非法基因的情况
	 * 
	 * 这里不采取new两个新的染色体，而是直接修改父染色体
	 * 
	 * @param population
	 */
	private void onePointRecombine(Population population){
		Population p1Population = population;
		
		Random p1Rand = new Random();
		Random p1ChsIndRand = new Random();
		
		List<Individual> p1IndividualList = new ArrayList<Individual>(2);
		List<Individual> individualList;
		List<GenePiece> tmpList = new ArrayList<GenePiece>();
		
		Individual p1Individual = null;
		GenePiece genePiece;
		
		int p1Position;
		int individualLength = gepAlgorithm.getNormalGeneLength()*gepAlgorithm.getNormalGeneNumber()+
								gepAlgorithm.getHomeoticGeneLength()*gepAlgorithm.getHomeoticGeneNumber();
		
		if(p1Rand.nextFloat()<super.getOnePointRecombineRate()){	//发生单点重组
			individualList = p1Population.getIndividuals();
			
			/*从发生单点重组的种群中找到两个发生单点重组的个体*/
			while(p1IndividualList.size()<2){
				p1Individual = individualList.get(p1ChsIndRand.nextInt(individualList.size()));
				if(!p1IndividualList.contains(p1Individual)){
					p1IndividualList.add(p1Individual);
				}
			}
			
			individualLength = gepAlgorithm.getIndividualLength();
			
			//p1Position = p1PosRand.nextInt(individualLength);	//获取单点重组的位置
			p1Position = 70;
			
			Individual indA = p1IndividualList.get(0);
			Individual indB = p1IndividualList.get(1);
			List<GenePiece> aGenePieceList = indA.getContainedGenePieces();
			List<GenePiece> bGenePieceList = indB.getContainedGenePieces();

			
			
			
			/*将a中指定中指定点之后的基因都转移到tmpList中*/
			for(int i=p1Position;i<individualLength;i++){
				genePiece = aGenePieceList.get(i);
				tmpList.add(ObjectCopy.newInstance(genePiece));
			}
			
			/*将b中指定中指定点之后的基因都转移到a中*/
			for(int i=p1Position;i<individualLength;i++){
				genePiece = bGenePieceList.get(i);
				aGenePieceList.set(i,ObjectCopy.newInstance(genePiece));
			}
			
			/*将tmpList中指定中指定点之后的基因都转移到b中*/
			for(int i=p1Position;i<individualLength;i++){
				genePiece = tmpList.remove(0);
				bGenePieceList.set(i,genePiece);
			}
			
			
		}	
		
	}
	
	
	/** 两点重组 
	 * 
	 * @param population
	 */
	private void twoPointRecombine(Population population){
		Population p2Population = population;
		
		Random p2Rand = new Random();
		Random p2ChsIndRand = new Random();
		Random p2PosRand = new Random();
		
		List<Individual> p2IndividualList = new ArrayList<Individual>(2);
		List<Individual> individualList;
		List<GenePiece> tmpList = new ArrayList<GenePiece>();
		
		
		Individual p2Individual = null;
		GenePiece genePiece;
		
		int p2PositionA;
		int p2PositionB;
		
		int individualLength = gepAlgorithm.getIndividualLength();
		
		if(p2Rand.nextFloat()<super.getTwoPointRecombineRate()){	//发生两点重组
			individualList = p2Population.getIndividuals();
			
			
			while(p2IndividualList.size()<2){
				p2Individual = individualList.get(p2ChsIndRand.nextInt(individualList.size()));
				if(!p2IndividualList.contains(p2Individual)){
					p2IndividualList.add(p2Individual);
				}
			}
			p2PositionA = p2PosRand.nextInt(individualLength);
			if(p2PositionA == individualLength-1){	//在末尾
				return;
			}else{
				p2PositionB = p2PositionA + 1 + p2PosRand.nextInt(individualLength-p2PositionA-1);
			}
		
			
			Individual indA = p2IndividualList.get(0);
			Individual indB = p2IndividualList.get(1);
			List<GenePiece> aGenePieceList = indA.getContainedGenePieces();
			List<GenePiece> bGenePieceList = indB.getContainedGenePieces();
			
			/*将a中指定中指定点之后的基因都转移到tmpList中*/
			for(int i=p2PositionA;i<=p2PositionB;i++){
				genePiece = aGenePieceList.get(i);
				tmpList.add(ObjectCopy.newInstance(genePiece));
			}
			
			/*将b中指定中指定点之后的基因都转移到a中*/
			for(int i=p2PositionA;i<=p2PositionB;i++){
				genePiece = bGenePieceList.get(i);
				aGenePieceList.set(i,ObjectCopy.newInstance(genePiece));
			}
			
			/*将tmpList中指定中指定点之后的基因都转移到b中*/
			for(int i=p2PositionA;i<=p2PositionB;i++){
				genePiece = tmpList.remove(0);
				bGenePieceList.set(i,genePiece);
			}	
		}	
	}
	
	
	/** 基因重组 
	 * 
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void geneRecombine(Population population) throws ClassNotFoundException, IOException{
		Population grPopulation = population;
		
		Random grRand = new Random();
		Random grChsIndRand = new Random();	//随机选择两个染色体
		Random grPosRand = new Random();
		Random grNorOrHom = new Random();	//为了性能，不new一个list而是使用Random随机判断同源基因还是普通基因
		
		List<Individual> grIndividualList = new ArrayList<Individual>(2);
		List<Individual> individualList;
		Gene tmpGeneA;
		Gene tmpGeneB;	
		
		Individual grIndividual = null;
		
		int grGenePosition;
		
		if(grRand.nextFloat()<super.getGeneRecombineRate()){	//发生基因重组
			individualList = grPopulation.getIndividuals();
	
			/*从发生单点重组的种群中找到两个发生重组的个体*/
			while(grIndividualList.size()<=2){
				grIndividual = individualList.get(grChsIndRand.nextInt(individualList.size()));
				if(!grIndividualList.contains(grIndividual)){
					grIndividualList.add(grIndividual);
				}
			}
	
			
			Individual indA = grIndividualList.get(0);
			Individual indB = grIndividualList.get(1);
			System.out.println(indA);
			System.out.println(indB);

			if (grNorOrHom.nextInt(2) == 0) {	//普通基因
				grGenePosition = grPosRand.nextInt(gepAlgorithm.getNormalGeneNumber());

				tmpGeneA = ObjectCopy.newInstance(indA.getNormalGeneList().remove(grGenePosition));
				tmpGeneB = ObjectCopy.newInstance(indB.getNormalGeneList().remove(grGenePosition));
				
				indA.getNormalGeneList().add(grGenePosition,(NormalGene)tmpGeneB);
				indB.getNormalGeneList().add(grGenePosition,(NormalGene)tmpGeneA);
				
			} else {	//同源基因
				grGenePosition = grPosRand.nextInt(gepAlgorithm.getHomeoticGeneNumber());
				
				tmpGeneA = ObjectCopy.newInstance(indA.getHomeoticGeneList().remove(grGenePosition));
				tmpGeneB = ObjectCopy.newInstance(indB.getHomeoticGeneList().remove(grGenePosition));
				
				indA.getHomeoticGeneList().add(grGenePosition,(HomeoticGene)tmpGeneB);
				indB.getHomeoticGeneList().add(grGenePosition,(HomeoticGene)tmpGeneA);
			}
			System.out.println(indA);
			System.out.println(indB);
		}
	}
}