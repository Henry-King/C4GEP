package domain.service.alg.userdefined.modify;

import java.util.Random;

import common.ObjectCopy;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.NormalGene;
import domain.service.alg.configuration.Modifying;

public class DefaultModifying extends Modifying {
	public DefaultModifying() {
		super("默认变异方式");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		mutate(myGepAlgorithm);
		isTransport(myGepAlgorithm);
		risTransport(myGepAlgorithm);
		geneTransport(myGepAlgorithm);
		onePointRecombine(myGepAlgorithm);
		twoPointRecombine(myGepAlgorithm);
		geneRecombine(myGepAlgorithm);
	}
	
	@Override
	public void mutate(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		Random mutateRandom=new Random();
		Random funcOrVarRandom=new Random();
		Random functionRandom=new Random();
		Random variableRandom=new Random();
		Random funcOrConsRandom=new Random();
		int type;
		Population population=myGepAlgorithm.getPopulationQueue().getLast();
		for(Individual mutatingIndividual:population.getIndividuals()){
			for(NormalGene mutatingNormalGene:mutatingIndividual.getNormalGeneList()){
				for(int i=0;i<mutatingNormalGene.getHeader().getContainedGenePieces().size();i++){
					if(mutateRandom.nextFloat()<getMutateRate()){
						type=funcOrVarRandom.nextInt(myGepAlgorithm.getFunctionList().size()+myGepAlgorithm.getVariableList().size());
						if(type<myGepAlgorithm.getFunctionList().size()){
							mutatingNormalGene.getHeader().getContainedGenePieces().set(i, 
									ObjectCopy.newInstance(myGepAlgorithm.getFunctionList().get(functionRandom.nextInt(myGepAlgorithm.getFunctionList().size()))));
						}
						else {
							mutatingNormalGene.getHeader().getContainedGenePieces().set(i, 
							ObjectCopy.newInstance(myGepAlgorithm.getVariableList().get(variableRandom.nextInt(myGepAlgorithm.getVariableList().size()))));
						}
					}
				}
				for(int i=0;i<mutatingNormalGene.getTail().getContainedGenePieces().size();i++){
					if(mutateRandom.nextFloat()<getMutateRate()){
						mutatingNormalGene.getTail().getContainedGenePieces().set(i, 
						ObjectCopy.newInstance(myGepAlgorithm.getVariableList().get(variableRandom.nextInt(myGepAlgorithm.getVariableList().size()))));
					}
				}
			}
			for(HomeoticGene mutatingHomeoticGene:mutatingIndividual.getHomeoticGeneList()){
				if(mutateRandom.nextFloat()<getMutateRate()){
					mutatingHomeoticGene.getContainedGenePieces().set(0, 
							ObjectCopy.newInstance(myGepAlgorithm.getFunctionList().get(functionRandom.nextInt(myGepAlgorithm.getFunctionList().size()))));
				}
				for(int i=1;i<mutatingHomeoticGene.getHeader().getContainedGenePieces().size();i++){
					if(mutateRandom.nextFloat()<getMutateRate()){
						type=funcOrConsRandom.nextInt(myGepAlgorithm.getFunctionList().size()+1);
					}
				}
				for(int i=0;i<mutatingHomeoticGene.getTail().getContainedGenePieces().size();i++){
					if(mutateRandom.nextFloat()<getMutateRate()){
						
					}
				}
			}
		}
	}

	@Override
	public void isTransport(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void risTransport(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void geneTransport(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onePointRecombine(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void twoPointRecombine(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void geneRecombine(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		
	}

}
