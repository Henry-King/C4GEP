package domain.service.alg.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jxl.read.biff.BiffException;

import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Function;
import domain.core.inputmodel.InputSet;
import domain.core.outputmodel.AlgInstance;
import domain.core.outputmodel.GepConfiguration;
import domain.core.outputmodel.OutputIndividual;
import domain.core.outputmodel.OutputPopulation;
import domain.iservice.IgepAlgService;
import domain.service.input.DefaultGepInput;
import domain.service.input.IgepInput;
import exception.Duplicated;

/**
 * 这个参数配置一个方法就可以完成了，非常简洁了
 */
public class GepAlgService implements IgepAlgService {
	private Calculator myCalculator;
	private Selector mySelector;
	private Creator myCreator;
	private Modifying myModifying;
	private GepAlgorithm myGepAlgorithm=new GepAlgorithm();
	private GepConfiguration myConfiguration;
	private IgepInput igepInput=new DefaultGepInput();
	private AlgInstance myAlgInstance;
	@Override
	public void saveArgumentsToDb(GepConfiguration myConfiguration) throws Duplicated {
		// TODO Auto-generated method stub
		try {
			IHibernateDataContext dbAccess=new HibernateDataContext();
			dbAccess.save(myConfiguration);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Duplicated newDuplicated=new Duplicated();
			newDuplicated.initCause(e);
			throw newDuplicated;
		}
	}

	@Override
	public List<GepConfiguration> readArgumentsFromDb() {
		// TODO Auto-generated method stub
		IHibernateDataContext dbAccess=new HibernateDataContext();
		return dbAccess.findAll(GepConfiguration.class);
	}
	@Override
	public void setParameters(GepConfiguration myConfiguration) throws InstantiationException, IllegalAccessException, ClassNotFoundException, BiffException, IOException {
		// TODO Auto-generated method stub
		this.myConfiguration=myConfiguration;
		myCreator=(Creator) Class.forName(myConfiguration.getCreator()).newInstance();
		myCreator.setConstanListSize(Integer.parseInt(myConfiguration.getConstantListSize()));
		myCreator.setRandomConstantRange(Float.parseFloat(myConfiguration.getRandomConstantStart()), Float.parseFloat(myConfiguration.getRandomConstantEnd()));
		myCreator.setSelectedFunctions(getFunctionList(myConfiguration.getFunctionList().split(",")));
		
		myCalculator=(Calculator) Class.forName(myConfiguration.getCalculator()).newInstance();
		myCalculator.setAccuray(Float.parseFloat(myConfiguration.getAccuray()));
		myCalculator.setSelectionRange(Float.parseFloat(myConfiguration.getSelectionRange()));

		mySelector=(Selector) Class.forName(myConfiguration.getSelector()).newInstance();
		myModifying=(Modifying) Class.forName(myConfiguration.getModify()).newInstance();
		
		myModifying.setStart(Float.parseFloat(myConfiguration.getRandomConstantEnd()));
		myModifying.setEnd(Float.parseFloat(myConfiguration.getRandomConstantEnd()));
		myModifying.setConstanListSize(Integer.parseInt(myConfiguration.getConstantListSize()));
		myModifying.setGeneRecombineRate(Float.parseFloat(myConfiguration.getGeneRecombineRate()));
		myModifying.setGeneTransportRate(Float.parseFloat(myConfiguration.getGeneTransportRate()));
		myModifying.setIsElements(stringArrayToIntegerList(myConfiguration.getIsElement().split(",")));
		myModifying.setIsTransportRate(Float.parseFloat(myConfiguration.getIsTransportRate()));
		myModifying.setMutateRate(Float.parseFloat(myConfiguration.getMutateRate()));
		myModifying.setOnePointRecombineRate(Float.parseFloat(myConfiguration.getOnePointRecombineRate()));
		myModifying.setRisElements(stringArrayToIntegerList(myConfiguration.getRisElement().split(",")));
		myModifying.setRisTransportRate(Float.parseFloat(myConfiguration.getRisTransportRate()));
		myModifying.setTwoPointRecombineRate(Float.parseFloat(myConfiguration.getTwoPointRecombineRate()));
		
		myGepAlgorithm.setFunctionList(getFunctionList(myConfiguration.getFunctionList().split(",")));
		myGepAlgorithm.setMaxGeneration(Long.parseLong(myConfiguration.getMaxGeneration()));
		myGepAlgorithm.setNormalGeneNumber(Integer.parseInt(myConfiguration.getNormalGeneNumber()));
		myGepAlgorithm.setHomeoticGeneNumber(Integer.parseInt(myConfiguration.getHomeoticGeneNumber()));
		myGepAlgorithm.setNormalHeaderLength(Integer.parseInt(myConfiguration.getNormalHeaderLength()));
		myGepAlgorithm.setHomeoticHeaderLength(Integer.parseInt(myConfiguration.getHomeoticHeaderLength()));
		myGepAlgorithm.setPopulationSize(Integer.parseInt(myConfiguration.getPopulationSize()));

		int maxArity=getMaxFunctionArity(myCreator.getSelectedFunctions());
		int normalTailLength=myGepAlgorithm.getNormalHeaderLength()*(maxArity-1)+1;
		int homeoticTailLength=myGepAlgorithm.getHomeoticHeaderLength()*(maxArity-1)+1;
		myGepAlgorithm.setNormalTailLength(normalTailLength);
		myGepAlgorithm.setHomeoticTailLength(homeoticTailLength);
		myGepAlgorithm.setIndividualLength(myGepAlgorithm.getHomeoticGeneLength()*myGepAlgorithm.getHomeoticGeneNumber()+myGepAlgorithm.getNormalGeneLength()*myGepAlgorithm.getNormalGeneNumber());
		
		igepInput.setFile(new File(myConfiguration.getInputFile()));
		InputSet is=new InputSet();
		igepInput.read(is);
		setInputSet(is);
	}

	@Override
	public List<Function> getAvailableFunctions() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		String binaryPath="domain.service.alg.userdefined.function";
		return getClassBinaryName(binaryPath, Function.class);
	}

	@Override
	public List<Selector> getAvailableSelector() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		String binaryPath="domain.service.alg.userdefined.selector";
		return getClassBinaryName(binaryPath, Selector.class);
	}

	@Override
	public List<Calculator> getAvailableCalculator() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		String binaryPath="domain.service.alg.userdefined.calculator";
		return getClassBinaryName(binaryPath, Calculator.class);
	}

	@Override
	public List<Creator> getAvailableCreator() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		String binaryPath="domain.service.alg.userdefined.creator";
		return getClassBinaryName(binaryPath, Creator.class);
	}

	@Override
	public List<Modifying> getAvailableModifyings() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		String binaryPath="domain.service.alg.userdefined.modify";
		return getClassBinaryName(binaryPath, Modifying.class);
	}
	
	/**请运行完所有set方法后再运行此方法。
	 * 在UI层要保证如下要求
	 * 若用户执行了setParameters方法，然后在这个类外部修改了为set方法传进来的GepConfiguration，请让用户为配置文件重新命名，否则可能无法正确保存信息到数据库，乃至抛出异常，切记。
	 * (non-Javadoc)
	 * @see domain.iservice.IgepAlgService#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Set<OutputPopulation> outputPopulations=new LinkedHashSet<OutputPopulation>();
		myCreator.create(myGepAlgorithm);
		for(int i=0;i<myGepAlgorithm.getMaxGeneration();i++){
			myCalculator.calculateFitness(myGepAlgorithm);
			
			outputPopulations.add(generateOutputPopulation(myGepAlgorithm.getPopulationQueue().getLast()));
				
			System.out.println("-----------------------------\n代数："+i);
//			System.out.println("选择之前");
			System.out.println("最大适应值: "+myGepAlgorithm.getPopulationQueue().getLast().getBestIndividual().getFitness());
			for(Individual individual:myGepAlgorithm.getPopulationQueue().getLast().getIndividuals())
				System.out.println(individual.getFitness());

			Population newPopulation=null;
			
			newPopulation = mySelector.select(copyPopulations(myGepAlgorithm.getPopulationQueue()));
			myGepAlgorithm.addPopulation(newPopulation);
			
			/*
			System.out.println("*******************\n选择之后");
			System.out.println("最大适应值: "+myGepAlgorithm.getPopulationQueue().getLast().getBestIndividual().getFitness());
			for(Individual individual:myGepAlgorithm.getPopulationQueue().getLast().getIndividuals())
				System.out.println(individual.getFitness());
			*/

			float maxFitness=myGepAlgorithm.getPopulationQueue().getLast().getBestIndividual().getFitness();
			if(Math.abs(maxFitness-myGepAlgorithm.getMaxFitness())<myCalculator.getAccuray())
				return;
				/*
				System.out.println(i+"\n-----------------------------");
				for(Individual individual:newPopulation.getIndividuals())
					System.out.println(individual.getFitness()+"\n"+individual);
				*/
			newPopulation.setGeneration(i+1);
			myModifying.run(myGepAlgorithm);	
		}
		writeDataToDB(outputPopulations);
	}
	@Override
	public AlgInstance getMyAlgInstance(){
		return myAlgInstance;
	}
	private void setInputSet(InputSet inputSet) {
		// TODO Auto-generated method stub
		myCreator.setVariables(inputSet.getVariableRow().getVariableList().subList(0, inputSet.getVariableRow().getColumns()-1));
		myCalculator.setInputSet(inputSet);
		myGepAlgorithm.setMaxFitness(myCalculator.getSelectionRange()*myCalculator.getInputSet().getFieldRowList().size());
		myGepAlgorithm.setVariableList(inputSet.getVariableRow().getVariableList().subList(0, inputSet.getVariableRow().getColumns()-1));
	}
	
	private <T> List<T> getClassBinaryName(String binaryPath,Class<T> typeClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		String filePathString=".\\bin\\"+binaryPath.replace(".", "\\");
		File classfiles=new File(filePathString);
		List<T> resultList=new ArrayList<T>(classfiles.list().length);
		for(String string:classfiles.list()){
			if(!string.contains("$")){
				Class<?> myClass=Class.forName(binaryPath+"."+string.subSequence(0, string.length()-6));
				T newInstance=typeClass.cast(myClass.newInstance());
				resultList.add(newInstance);				
			}
		}
		return resultList;
	}
	private int getMaxFunctionArity(List<Function> functionlist){
		int max=0;
		for(Function function:functionlist)
			if(function.getArity()>max)
				max=function.getArity();
		return max;
	}
	private List<Function> getFunctionList(String [] classNames) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Function> result=new ArrayList<Function>();
		for(int i=0;i<classNames.length;i++)
			result.add((Function) Class.forName(classNames[i]).newInstance());
		return result;
	}
	private List<Integer> stringArrayToIntegerList(String[] array){
		List<Integer> result=new ArrayList<Integer>(array.length);
		for(int i=0;i<array.length;i++)
			result.add(Integer.parseInt(array[i]));
		return result;
	}
	private void writeDataToDB(Set<OutputPopulation> outputPopulations){
		IHibernateDataContext dbAccess=new HibernateDataContext();
		myAlgInstance=generateAlgInstance(myGepAlgorithm);
		myAlgInstance.setPopulationSet(outputPopulations);
		List<GepConfiguration> list=readArgumentsFromDb();
		int i=0;
		for(;i<list.size();i++)
			if(list.get(i).getName().equals(myAlgInstance.getGepConfiguration().getName())){
				myAlgInstance.setGepConfiguration(list.get(i));
				break;
			}
		if(i==list.size()){
			try {
				saveArgumentsToDb(myConfiguration);
			} catch (Duplicated e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myAlgInstance.setGepConfiguration(myConfiguration);
		}
		dbAccess.save(myAlgInstance);
	}
	private AlgInstance generateAlgInstance(GepAlgorithm algorithm){
		AlgInstance result=new AlgInstance();
		result.setGepConfiguration(myConfiguration);
		result.setRunTimes((long) 1);
		result.setTotalGeneration(algorithm.getPopulationQueue().getLast().getGeneration());
		return result;
	}
	public OutputPopulation generateOutputPopulation(Population population) {
		// TODO Auto-generated method stub
		OutputPopulation result=new OutputPopulation();
		result.setGeneration(population.getGeneration());
		Set<OutputIndividual> individuals=new LinkedHashSet<OutputIndividual>();
		for(Individual individual:population.getIndividuals())
			individuals.add(generateOutputIndividual(individual));
		result.setIndvididualSet(individuals);
		return result;
	}
	private OutputIndividual generateOutputIndividual(Individual individual){
		OutputIndividual result=new OutputIndividual();
		result.setExpression(individual.toString());
		result.setFitness(individual.getFitness());
		return result;
	}
	//未完成
	private Deque<Population> copyPopulations(Deque<Population> populations){
		Deque<Population> result=new ArrayDeque<Population>(2);
		result.add(populations.getFirst().copy());
		result.add(populations.getLast().copy());
		return result;
	}
}
