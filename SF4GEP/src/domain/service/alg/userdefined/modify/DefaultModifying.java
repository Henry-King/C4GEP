package domain.service.alg.userdefined.modify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.ObjectCopy;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Constant;
import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.individualcomponent.Gene;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.NormalGene;
import domain.service.alg.configuration.Modifying;

public class DefaultModifying extends Modifying {
	enum TransportEnum{
		IS,RIS,GENE;
		float rate;
		List<Integer> transportElement;
		public void setRate(float rate){
			this.rate=rate;
		}
		public float getRate(){
			return rate;
		}
		public void setTransportElement(List<Integer> transportElement){
			this.transportElement=transportElement;
		}
		public List<Integer> getTransportElement(){
			return transportElement;
		}
	}
	enum Recombine{
		OnePoint,TwoPoint,GENE;
		float rate;
		public void setRate(float rate){
			this.rate=rate;
		}
		public float getRate(){
			return rate;
		}
	}
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
						if(type<myGepAlgorithm.getFunctionList().size()){
							mutatingHomeoticGene.getHeader().getContainedGenePieces().set(i, 
									ObjectCopy.newInstance(myGepAlgorithm.getFunctionList().get(functionRandom.nextInt(myGepAlgorithm.getFunctionList().size()))));
						}
						else {
							mutatingHomeoticGene.getHeader().getContainedGenePieces().set(i, new Constant(myGepAlgorithm.getNormalGeneNumber()));
						}
					}
				}
				for(int i=0;i<mutatingHomeoticGene.getTail().getContainedGenePieces().size();i++){
					if(mutateRandom.nextFloat()<getMutateRate()){
						mutatingHomeoticGene.getTail().getContainedGenePieces().set(i, new Constant(myGepAlgorithm.getNormalGeneNumber()));
					}
				}
			}
		}
	}

	@Override
	public void isTransport(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		TransportEnum isTransportEnum=TransportEnum.IS;
		isTransportEnum.setRate(getIsTransportRate());
		isTransportEnum.setTransportElement(getIsElements());
		transportIterateInGene(myGepAlgorithm, isTransportEnum);
	}

	@Override
	public void risTransport(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		TransportEnum risTransportEnum=TransportEnum.RIS;
		risTransportEnum.setRate(getRisTransportRate());
		risTransportEnum.setTransportElement(getRisElements());
		transportIterateInGene(myGepAlgorithm,risTransportEnum);
	}

	@Override
	public void geneTransport(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		TransportEnum geneTransportEnum=TransportEnum.GENE;
		geneTransportEnum.setRate(getGeneTransportRate());
		transportIterateInGene(myGepAlgorithm, geneTransportEnum);
	}

	@Override
	public void onePointRecombine(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		Recombine recombine=Recombine.OnePoint;
		recombine.setRate(getOnePointRecombineRate());
		recombineIterate(myGepAlgorithm, recombine);
	}

	@Override
	public void twoPointRecombine(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		Recombine recombine=Recombine.TwoPoint;
		recombine.setRate(getTwoPointRecombineRate());
		recombineIterate(myGepAlgorithm, recombine);
	}

	@Override
	public void geneRecombine(GepAlgorithm myGepAlgorithm) {
		// TODO Auto-generated method stub
		Recombine recombine=Recombine.GENE;
		recombine.setRate(getGeneRecombineRate());
		recombineIterate(myGepAlgorithm, recombine);
	}
	private void transportIterateInGene(GepAlgorithm myGepAlgorithm,TransportEnum transportEnum){
		Population population=myGepAlgorithm.getPopulationQueue().getLast();
		Random transportRandom=new Random();
		for(Individual individual:population.getIndividuals()){
			for(NormalGene normalGene:individual.getNormalGeneList()){
				if(transportRandom.nextFloat()<transportEnum.getRate()){
					transportParaDetermination(normalGene, transportEnum, myGepAlgorithm.getNormalHeaderLength(),myGepAlgorithm.getNormalTailLength(),myGepAlgorithm.getNormalGeneNumber(),individual.getNormalGenePieces());
				}
			}
			for(HomeoticGene homeoticGene:individual.getHomeoticGeneList()){
				if(transportRandom.nextFloat()<transportEnum.getRate()){
					transportParaDetermination(homeoticGene, transportEnum ,myGepAlgorithm.getHomeoticHeaderLength(),myGepAlgorithm.getHomeoticTailLength(),myGepAlgorithm.getHomeoticGeneNumber(),individual.getHomeoticGenePieces());
				}
			}
		}
	}
	private void transportParaDetermination(Gene gene,TransportEnum transportEnum,int headerLength,int tailLength,int geneNum,List<GenePiece> genePieces){
		Random sourceLocRandom=new Random();
		Random destLocRandom=new Random();
		Random elementLengthRandom=new Random();
		int elementLength=0;
		int sourceLoc=0;
		int destLoc=0;
		switch (transportEnum) {
		case IS:
			elementLength=transportEnum.transportElement.get(elementLengthRandom.nextInt(transportEnum.transportElement.size()));
			sourceLoc=sourceLocRandom.nextInt(headerLength+tailLength-elementLength);
			destLoc=destLocRandom.nextInt(headerLength-elementLength-1)+1;
			break;
		case RIS:
			elementLength=transportEnum.transportElement.get(elementLengthRandom.nextInt(transportEnum.transportElement.size()));
			sourceLoc=searchFunction(gene, destLocRandom.nextInt(headerLength));
			destLoc=0;
			break;
		case GENE:
			elementLength=headerLength+tailLength;
			sourceLoc=sourceLocRandom.nextInt(geneNum)*(headerLength+tailLength);
			destLoc=destLocRandom.nextInt(geneNum)*(headerLength+tailLength);
			break;
		}
		if(sourceLoc!=-1){
			if(transportEnum==TransportEnum.GENE){
				transportBegin(genePieces, sourceLoc, destLoc, elementLength, headerLength, transportEnum);
			}
			else {
				transportBegin(gene.getContainedGenePieces(), sourceLoc, destLoc, elementLength, headerLength, transportEnum);
			}
		}
	}
	private int searchFunction(Gene gene,int index){
		int result=-1;
		for(int i=index;i<gene.getContainedGenePieces().size();i++){
			if(gene.getContainedGenePieces().get(i) instanceof Function){
				result=i;
				break;
			}
		}
		return result;
	}
	private void transportBegin(List<GenePiece> genePieces,int source,int dest,int length,int headerLength,TransportEnum transportEnum){
		List<GenePiece> copiedSource=new ArrayList<GenePiece>(length);
		for(int i=0;i<length;i++)
			copiedSource.add(ObjectCopy.newInstance(genePieces.get(i+source)));
		if(transportEnum==TransportEnum.GENE){
			for(int i=0;i<length;i++){
				genePieces.set(i+source, genePieces.get(i+dest));
			}
			for(int i=0;i<length;i++){
				genePieces.set(i+dest, copiedSource.get(i));
			}
		}
		else {
			int forLength=headerLength-dest-length;
			for(int i=0;i<forLength;i++){
				genePieces.set(headerLength-i-1, genePieces.get(headerLength-i-1-length));
			}
			for(int i=0;i<length;i++){
				genePieces.set(dest+i, copiedSource.get(i));
			}
		}
	}
	private void recombineIterate(GepAlgorithm gepAlgorithm,Recombine recombine){
		Population population=gepAlgorithm.getPopulationQueue().getLast();
		Random recombineRandom=new Random();
		for(int i=0;i<population.getIndividuals().size()-1;i++){
			if(recombineRandom.nextFloat()<recombine.getRate()){
				for(int j=i+1;j<population.getIndividuals().size();j++){
					if(recombineRandom.nextFloat()<recombine.getRate()){
						recombineParaDetermination(population.getIndividuals().get(i).getContainedGenePieces(), population.getIndividuals().get(j).getContainedGenePieces(), recombine, gepAlgorithm);
					}
				}
			}
		}
	}
	private void recombineParaDetermination(List<GenePiece> a,List<GenePiece> b,Recombine recombine,GepAlgorithm gepAlgorithm){
		Random startRandom=new Random();
		Random endRandom=new Random();
		int start=-1;
		int end=-1;
		switch (recombine) {
		case OnePoint:
			end=gepAlgorithm.getHomeoticGeneLength()+gepAlgorithm.getNormalGeneLength();
			start=startRandom.nextInt(end);
			break;
		case TwoPoint:
			start=startRandom.nextInt(gepAlgorithm.getHomeoticGeneLength()+gepAlgorithm.getNormalGeneLength());
			end=endRandom.nextInt(gepAlgorithm.getHomeoticGeneLength()+gepAlgorithm.getNormalGeneLength());
			if(start>end){
				int temp=start;
				start=end;
				end=temp;
			}
			break;
		case GENE:
			int geneNo=startRandom.nextInt(gepAlgorithm.getHomeoticGeneNumber()+gepAlgorithm.getNormalGeneNumber());
			if(geneNo<gepAlgorithm.getNormalGeneNumber()){
				start=geneNo*gepAlgorithm.getNormalGeneLength();
				end=start+gepAlgorithm.getNormalGeneLength();
			}
			else {
				start=geneNo*gepAlgorithm.getHomeoticGeneLength();
				end=start+gepAlgorithm.getHomeoticGeneLength();
			}
			break;
		}
		beginRecombine(start, end, a, b);
	}
	private void beginRecombine(int start,int end,List<GenePiece> a,List<GenePiece> b){
		for(int i=start;i<end;i++){
			GenePiece aPiece=a.get(i);
			a.set(i, b.get(i));
			b.set(i, aPiece);
		}
	}
}
