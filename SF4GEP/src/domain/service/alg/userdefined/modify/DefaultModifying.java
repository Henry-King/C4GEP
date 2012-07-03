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
	private int funcNumber;	//������
	private int variableNumber;	//������
	private int constantNumber;	//������
	private int funcntionVariablConstantSum;	//����+����+���� �ܺ�
	private int variableConstantSum;	//����+���� �ܺ�
	private int functionConstantSum;	//����+���� �ܺ�
	public DefaultModifying() {
		super("Ĭ�ϱ��췽ʽ");
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
	 * ����(�ڲ�����δ��װ�汾���������ܴ�RandomҪnew��Σ����ó�static��)
	 * ������� mutateRate
	 * ������Է�����Ⱦɫ���ڵ��κ�λ��
	 * ��ͷ���У��κη��Ŷ����Ա�ɷ��Ż����յ㣻��β���У��յ�ֻ�ܹ�����յ㡣
	 * �����Ϊ��ͨ��������Ա���
	 * @sec DefaultModifying#mutate()
	 * @param population һ�α�������һ����Ⱥ
	 * @throws CloneNotSupportedException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void mutate(Population population){
		
		Population mutatePopulation = population;	//�������Ⱥ
		
		Random mutateRand = new Random();	//ָ����������������
		Random functionRand=new Random();
		Random variableRand=new Random();
		Random typeRandom=new Random();
		int type;

		NormalGeneHeader normalGeneHeader;
		NormalGeneTail normalGeneTail;
		HomeoticGeneHeader homeoticGeneHeader;
		HomeoticGeneTail homeoticGeneTail;
		
		List<GenePiece> genePieces;
		List<Computable> computables;//������������е㵰�ۣ����ҼӸ�s��
		
		List<Variable> variableList=gepAlgorithm.getVariableList();	//��������
		List<Function> functionList=gepAlgorithm.getFunctionList();	//��������
		
		Constant constant;	//����
		Function function;	//����
		Variable variable;	//����
		
		funcNumber = functionList.size();
		variableNumber = variableList.size();
		constantNumber = 1;	//����������ͨ��constantList��ó������������ǲ���ѽ������
		funcntionVariablConstantSum=funcNumber+variableNumber+constantNumber;
		variableConstantSum=variableNumber+constantNumber;
		functionConstantSum=funcNumber+constantNumber;			
		
		for(Individual mutateIndividual:mutatePopulation.getIndividuals()){	//������Ⱥ�е�ÿ��Ⱦɫ��
			
			for(NormalGene mutateNormalGene:mutateIndividual.getNormalGeneList()){	//������ͨȾɫ���е�ÿ������
				
				/*Creator���л�ú����������ĳ��ȵķ�������Modifyingû�У�ÿ�ζ����Ÿ�ֵ*/
		
				
				/*������ͨ����ͷ��*/
				normalGeneHeader = mutateNormalGene.getHeader();
				genePieces = normalGeneHeader.getGenePieces();
				for(int i=0;i<gepAlgorithm.getNormalHeaderLength();i++){
					//genePiece = genePieces.get(i);
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//�����������Floatֵ��0~mutateRate֮��ʱ����������
						type=typeRandom.nextInt(funcntionVariablConstantSum);
						if(type<funcNumber){
							//���캯��
							function = ObjectCopy.newInstance((Function)functionList.get(functionRand.nextInt(functionList.size())));
							genePieces.set(i, function);
						}
						else if(type>=funcNumber&&type<funcNumber+variableNumber){
							//�������
							variable = ObjectCopy.newInstance((Variable) variableList.get(variableRand.nextInt(variableList.size())));
							genePieces.set(i, variable);
						}
						else {
							constant=new Constant(mutateNormalGene.getConstantList(), getConstanListSize());
							genePieces.set(i, constant);
						}
					}//end if
				}
				
				/*������ͨ����β��*/
				normalGeneTail = mutateNormalGene.getTail();
				computables = normalGeneTail.getComputable();
				for(int i=0;i<gepAlgorithm.getNormalTailLength();i++){
					//genePiece = genePieces.get(i);
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//�����������Floatֵ��0~mutateRate֮��ʱ����������
						type=typeRandom.nextInt(variableConstantSum);
						if(type<variableNumber){
							//�������
							variable = ObjectCopy.newInstance((Variable) variableList.get(variableRand.nextInt(variableList.size())));
							computables.set(i, variable);
						}
						else {
							//���쳣��
							constant=new Constant(mutateNormalGene.getConstantList(), getConstanListSize());
							constant.generateConstantIndex();
							computables.set(i, constant);
						}
					}
				}
			}
				
			for(HomeoticGene mutateHomeoticGene:mutateIndividual.getHomeoticGeneList()){	//����ͬԴȾɫ���е�ÿ������
				functionList = gepAlgorithm.getFunctionList();

				/*����ͬԴ����ͷ��*/
				homeoticGeneHeader = mutateHomeoticGene.getHeader();
				genePieces = homeoticGeneHeader.getGenePieces();
				if(mutateRand.nextFloat()<getMutateRate()){
					function = ObjectCopy.newInstance((Function)functionList.get(functionRand.nextInt(functionList.size())));
					genePieces.set(0, function);
				}
				for(int i=1;i<gepAlgorithm.getNormalHeaderLength();i++){
					//genePiece = genePieces.get(i);
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//�����������Floatֵ��0~mutateRate֮��ʱ����������
						/*���ֺ��������������������䣬ʹ��Random���ȡֵ������type����type���ڵ�������������������--���������߱��������߳���*/
						type=typeRandom.nextInt(functionConstantSum);	//ͬԴȾɫ�壬ͷ���ޱ���	
						
						if(type<funcNumber){
							//���캯��
							function = ObjectCopy.newInstance((Function)functionList.get(functionRand.nextInt(functionList.size())));
							genePieces.set(i, function);
						} else {
							//���쳣��
							//���꣬�Ҿ��ó�����һ��д�ĺܱ�Ť��
							//��Щ�����ǲ���Ӧ�÷���constantList������
							constant=new Constant(gepAlgorithm.getNormalGeneNumber());
							genePieces.set(i, constant);
						}

					} //end if
				}
				/*����ͬԴ����β��*/
				homeoticGeneTail = mutateHomeoticGene.getTail();
				computables = homeoticGeneTail.getComputable();
				for(int i=0;i<gepAlgorithm.getNormalTailLength();i++){
					if(mutateRand.nextFloat()<= super.getMutateRate()){	//�����������Floatֵ��0~mutateRate֮��ʱ����������
						//���쳣��
						constant=new Constant(gepAlgorithm.getNormalGeneNumber());
						computables.set(i, constant);
					}
				}
			}//end of for mutatehomeoticGene
		} // end of for mutateIndividual
				
	}

	/** IS ��������Ԫ�ص�ת��
	 * ��ʼλ�����Ǻ������ս��Ķ�Ƭ��ת���������ͷ�г����������λ��
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void isTransport(Population population) throws ClassNotFoundException, IOException{	//��Ⱥ
		Population isPopulation  = population;
		Random isRand = new Random();	//�ж��Ƿ���ISת���ĸ���
		Random isGetPosRand = new Random();	//���ѡ���ȡISת��Ԫ�ص�λ��
		Random isSetPosRand = new Random();	//���ѡ����ISת����λ��
		Random isEleRand = new Random();	//���ѡ��ISԪ�صĳ���
		//Random isNormalOrHomeoticRand = new Random();	//���ѡ����ͨ������ͬԴ����
		Random isGeneRand = new Random();	//�����ȡ�����λ��
		
		super.getIsTransportRate();	//��ȡIS��ת������
		super.getIsElements();	//��ȡISԪ�صĳ����б�
		
		List<GenePiece> isElementList = new ArrayList<GenePiece>();	//������һ��ISԪ�ص��б�
		List<GenePiece> isList;
		
		int isLength;	//����ISת���Ļ���ĳ���
		int isElementLength;
		int isGetPosition;
		int isSetPosition;
		
		NormalGene normalGene;
		NormalGeneHeader normalGeneHeader;
		
		List<NormalGene> normalGeneList;
		/**	
		 * ת�����������ѡȡȾɫ�壬IS Ԫ�أ�Ŀ��λ�ã��Լ�ת���ӵĳ���
		 * IS ��Χ�ڵ���Ⱦɫ����
		 * IS Rate �жϸ�Ⱦɫ���Ƿ���ISת��
		 */
		for(Individual isIndividual:isPopulation.getIndividuals()){		//����Ⱦɫ��
			if(isRand.nextFloat()<super.getIsTransportRate()){	//�����Ⱦɫ�巢��ת��������һ��Ⱦɫ��ֻ����һ��ת��
				isLength = gepAlgorithm.getIndividualLength();
				isList = isIndividual.getContainedGenePieces();
				isElementLength = super.getIsElements().get(isEleRand.nextInt(super.getIsElements().size()));	//��ȡISԪ�صĳ���
				isGetPosition = isGetPosRand.nextInt(isLength);	//��ȡISת��Ԫ�ص�λ��
				
			
				/*��ȡISԪ��*/
				for(int i=0;i<isElementLength&&isGetPosition<isLength;i++){
					isElementList.add(ObjectCopy.newInstance(isList.get(isGetPosition++)));	//�ȴ�Ⱦɫ���б���ѡ����ISת����λ�õĻ���,֮����ӵ�ת�����б���
				}
				
				
				/*��Ⱦɫ���е�ĳ�������м���ISԪ��*/
				normalGeneList = isIndividual.getNormalGeneList();
				normalGene = normalGeneList.get(isGeneRand.nextInt(normalGeneList.size()));	//����ͨ���򼯺������ȡ��һ������

				normalGeneHeader = normalGene.getHeader();
				List<GenePiece> headerGeneList = normalGeneHeader.getGenePieces();
				isSetPosition = 1+isSetPosRand.nextInt(gepAlgorithm.getNormalHeaderLength()-1);	//�ų�������ʼλ��
				
				List<GenePiece> tmpList = new ArrayList<GenePiece>();
				int i;
				for(i=isSetPosition;i<headerGeneList.size();i++){	//��ISת��λ��֮���Ԫ�ر�����tmp������
					tmpList.add( ObjectCopy.newInstance(headerGeneList.get(i)));
				}
				
				for(i=isSetPosition;i<headerGeneList.size()&&isElementList.size()>0;i++){	//��ISԪ���滻��ͷ����
					headerGeneList.set(i, ObjectCopy.newInstance(isElementList.remove(0)));
				}
				
				for(;i<headerGeneList.size();i++){
					headerGeneList.set(i, tmpList.remove(0));
				}
			}
		}
	}
	
	/** RIS 
	 * ��ת��
	 * ��ʼλ�����Ǻ����Ķ�Ƭ��ת��������ĸ���
	 * 
	 * ��һ��������ʼ,ѡ��ͷ�����е�����
	 * ��ͷ����ѡһ�㣬�ػ��������ң�ֱ������һ������Ϊֹ���ú�����ΪRIS Ԫ�ص���ʼλ��
	 * ����Ҳ�����������任�����κβ�����
	 * �������ѡȡȾɫ�壬��Ҫ���εĻ���RIS Ԫ�ؼ��䳤��
	 * һ��أ����Բ���0.1 �ĸ��任���ʺ������鲻ͬ���ȵ�RIS Ԫ�ؼ���
	 * 
	 * 
	 * ����β������������ͨ�Ļ���ͬԴ�ģ����������к�����
	 * ��˿��Դ�ͷ��ʼ��ֱ�ӱ���Ⱦɫ���е�ÿһ������λ��
	 * ֪���ҵ��˷��������Ļ��򣨺�������Ϊֹ
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
		
		super.getRisTransportRate();	//��ȡIS��ת������
		super.getRisElements();	//��ȡISԪ�صĳ����б�
		
		
		List<GenePiece> geneList;
		List<GenePiece> risElementList = new ArrayList<GenePiece>();
		
		int risElementLength;	//����RISת����Ⱦɫ�峤��
		int risLength;	// Ⱦɫ�峤��
		int risSetPosition;
		
		NormalGene normalGene;
		
		NormalGeneHeader normalGeneHeader;
		
		
		List<NormalGene> normalGeneList;		
		
		/**
		 * ��Ⱦɫ����л����ͷ����ʼ����
		 * ����ͨ�����ͬԴ�����ͷ���ж����к�����
		 */
		for(Individual risIndividual:risPopulation.getIndividuals()){	//����Ⱦɫ��
			if(risRand.nextFloat()<super.getRisTransportRate()){	//�жϸ�Ⱦɫ���Ƿ���
				boolean noFind = true;	//����Ƿ��ҵ�����
				geneList = risIndividual.getContainedGenePieces();	
				risLength = gepAlgorithm.getIndividualLength();
				risElementLength = super.getIsElements().get(risRand.nextInt(super.getRisElements().size()));	//��ȡRISԪ�صĳ���
				
				
				/*��ȡRISԪ��*/
				for(int i=0;i<risLength&&noFind;i++){
					if(Function.class.isInstance(geneList.get(i))){	//�ж��Ƿ��Ǻ���
						
						
						for(int j=0;j<risElementLength&&i<risLength&&noFind;j++){
							risElementList.add(ObjectCopy.newInstance(geneList.get(i++)));
						}
						noFind = false;
						
					}
				}
					
				
				/*����RISת��*/
				normalGeneList = risIndividual.getNormalGeneList();
				normalGene = normalGeneList.get(risGeneRand.nextInt(normalGeneList.size()));	//����ͨ���򼯺������ȡ��һ������
				normalGeneHeader = normalGene.getHeader();
				risSetPosition = risSetPosRand.nextInt(gepAlgorithm.getNormalHeaderLength());	//���Է�����Ⱦɫ�������λ��
						
				List<GenePiece> headerGeneList = normalGeneHeader.getGenePieces();
				risSetPosition = risRand.nextInt(gepAlgorithm.getNormalGeneLength());	
				
				List<GenePiece> tmpList = new ArrayList<GenePiece>();
				int i;
				for(i=risSetPosition;i<headerGeneList.size();i++){	//��ISת��λ��֮���Ԫ�ر�����tmp������
					tmpList.add( ObjectCopy.newInstance(headerGeneList.get(i)));
				}
				
				for(i=risSetPosition;i<headerGeneList.size()&&risElementList.size()>0;i++){	//��ISԪ���滻��ͷ����
					headerGeneList.set(i, ObjectCopy.newInstance(risElementList.remove(0)));
				}
				
				for(;i<headerGeneList.size();i++){
					headerGeneList.set(i, tmpList.remove(0));
				}										
			}
				
		}

	}

	
	/** ����ת�� 
	 * ��������ת����Ⱦɫ�����ʼλ��
	 * 
	 * ת��������������
	 * 
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void geneTransport(Population population) throws ClassNotFoundException, IOException{
		Population gtPopulation  = population;
		Random gtRand = new Random();	//�����������ת���ĸ���
		Random gtGeneRand = new Random();	//���ѡ����ʱת���Ļ���		
		
		super.getGeneTransportRate();

		NormalGene gtGene;	
		
		List<NormalGene> normalGeneList;
		
		for(Individual gtIndividual:gtPopulation.getIndividuals()){	//����Ⱦɫ��
			
			if(gtRand.nextFloat()<super.getGeneTransportRate()){	//�жϸ�Ⱦɫ���Ƿ�������ת��
				normalGeneList = gtIndividual.getNormalGeneList();
				gtGene = ObjectCopy.newInstance(normalGeneList.remove(gtGeneRand.nextInt(gepAlgorithm.getNormalGeneNumber())));
				normalGeneList.add(0, ObjectCopy.newInstance(gtGene));		
			}
		}
	}
	
	
	/** �������� 
	 * 
	 * �����ѡ�㣬��������ͬԴȾɫ�������
	 * �����ǽ�ѡ��ĵ�֮������л��򶼽��л�����
	 * ��ˣ����ᷢ�����ַǷ���������
	 * 
	 * ���ﲻ��ȡnew�����µ�Ⱦɫ�壬����ֱ���޸ĸ�Ⱦɫ��
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
		
		if(p1Rand.nextFloat()<super.getOnePointRecombineRate()){	//������������
			individualList = p1Population.getIndividuals();
			
			/*�ӷ��������������Ⱥ���ҵ�����������������ĸ���*/
			while(p1IndividualList.size()<2){
				p1Individual = individualList.get(p1ChsIndRand.nextInt(individualList.size()));
				if(!p1IndividualList.contains(p1Individual)){
					p1IndividualList.add(p1Individual);
				}
			}
			
			individualLength = gepAlgorithm.getIndividualLength();
			
			//p1Position = p1PosRand.nextInt(individualLength);	//��ȡ���������λ��
			p1Position = 70;
			
			Individual indA = p1IndividualList.get(0);
			Individual indB = p1IndividualList.get(1);
			List<GenePiece> aGenePieceList = indA.getContainedGenePieces();
			List<GenePiece> bGenePieceList = indB.getContainedGenePieces();

			
			
			
			/*��a��ָ����ָ����֮��Ļ���ת�Ƶ�tmpList��*/
			for(int i=p1Position;i<individualLength;i++){
				genePiece = aGenePieceList.get(i);
				tmpList.add(ObjectCopy.newInstance(genePiece));
			}
			
			/*��b��ָ����ָ����֮��Ļ���ת�Ƶ�a��*/
			for(int i=p1Position;i<individualLength;i++){
				genePiece = bGenePieceList.get(i);
				aGenePieceList.set(i,ObjectCopy.newInstance(genePiece));
			}
			
			/*��tmpList��ָ����ָ����֮��Ļ���ת�Ƶ�b��*/
			for(int i=p1Position;i<individualLength;i++){
				genePiece = tmpList.remove(0);
				bGenePieceList.set(i,genePiece);
			}
			
			
		}	
		
	}
	
	
	/** �������� 
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
		
		if(p2Rand.nextFloat()<super.getTwoPointRecombineRate()){	//������������
			individualList = p2Population.getIndividuals();
			
			
			while(p2IndividualList.size()<2){
				p2Individual = individualList.get(p2ChsIndRand.nextInt(individualList.size()));
				if(!p2IndividualList.contains(p2Individual)){
					p2IndividualList.add(p2Individual);
				}
			}
			p2PositionA = p2PosRand.nextInt(individualLength);
			if(p2PositionA == individualLength-1){	//��ĩβ
				return;
			}else{
				p2PositionB = p2PositionA + 1 + p2PosRand.nextInt(individualLength-p2PositionA-1);
			}
		
			
			Individual indA = p2IndividualList.get(0);
			Individual indB = p2IndividualList.get(1);
			List<GenePiece> aGenePieceList = indA.getContainedGenePieces();
			List<GenePiece> bGenePieceList = indB.getContainedGenePieces();
			
			/*��a��ָ����ָ����֮��Ļ���ת�Ƶ�tmpList��*/
			for(int i=p2PositionA;i<=p2PositionB;i++){
				genePiece = aGenePieceList.get(i);
				tmpList.add(ObjectCopy.newInstance(genePiece));
			}
			
			/*��b��ָ����ָ����֮��Ļ���ת�Ƶ�a��*/
			for(int i=p2PositionA;i<=p2PositionB;i++){
				genePiece = bGenePieceList.get(i);
				aGenePieceList.set(i,ObjectCopy.newInstance(genePiece));
			}
			
			/*��tmpList��ָ����ָ����֮��Ļ���ת�Ƶ�b��*/
			for(int i=p2PositionA;i<=p2PositionB;i++){
				genePiece = tmpList.remove(0);
				bGenePieceList.set(i,genePiece);
			}	
		}	
	}
	
	
	/** �������� 
	 * 
	 * @param population
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void geneRecombine(Population population) throws ClassNotFoundException, IOException{
		Population grPopulation = population;
		
		Random grRand = new Random();
		Random grChsIndRand = new Random();	//���ѡ������Ⱦɫ��
		Random grPosRand = new Random();
		Random grNorOrHom = new Random();	//Ϊ�����ܣ���newһ��list����ʹ��Random����ж�ͬԴ��������ͨ����
		
		List<Individual> grIndividualList = new ArrayList<Individual>(2);
		List<Individual> individualList;
		Gene tmpGeneA;
		Gene tmpGeneB;	
		
		Individual grIndividual = null;
		
		int grGenePosition;
		
		if(grRand.nextFloat()<super.getGeneRecombineRate()){	//������������
			individualList = grPopulation.getIndividuals();
	
			/*�ӷ��������������Ⱥ���ҵ�������������ĸ���*/
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

			if (grNorOrHom.nextInt(2) == 0) {	//��ͨ����
				grGenePosition = grPosRand.nextInt(gepAlgorithm.getNormalGeneNumber());

				tmpGeneA = ObjectCopy.newInstance(indA.getNormalGeneList().remove(grGenePosition));
				tmpGeneB = ObjectCopy.newInstance(indB.getNormalGeneList().remove(grGenePosition));
				
				indA.getNormalGeneList().add(grGenePosition,(NormalGene)tmpGeneB);
				indB.getNormalGeneList().add(grGenePosition,(NormalGene)tmpGeneA);
				
			} else {	//ͬԴ����
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