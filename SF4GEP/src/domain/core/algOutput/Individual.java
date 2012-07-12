package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 个体类,种群中保存着个体的List
 * @author 个体类
 *
 */
public class Individual implements Comparable<Individual>,Serializable,Cloneable{
	private static final long serialVersionUID = -7942680950588488680L;
	private List<Gene> genes;
	private List<FittedValue> fittedValues;
	private Float fitness;
	private int selectedHomeoticGeneNumber=-1;
	/**
	 * 覆盖了Object的toString方法，本方法将以字符串的形式输出个体中所包含的全部Genepiece信息。
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		boolean isNormalGene=true;
		StringBuffer result=new StringBuffer();
		result.append("正常基因:");
		for(Gene gene:genes){
			if(gene.getGeneType()==GeneType.HomeoticGene&&isNormalGene){
				isNormalGene=false;
				result.append("\n同源基因：");
			}
			for(GenePiece genePiece:gene.getGenePieces())
				result.append(genePiece.getSymbol()+" ");
		}
		return result.toString();
	}
	/**
	 * 比较当前个体的适应值和用户指定的某个个体的适应值，并将此结果返回
	 * @param　用户提供的待比较的个体
	 * @return 如果当前个体适应值小于参数适应值，返回负数，如果当前个体适应值等于参数个体适应值，返回0，如果当前个体适应值大于参数个体适应值，返回正数
	 */
	@Override
	public int compareTo(Individual o) {
		// TODO Auto-generated method stub
		return Float.compare(fitness, o.getFitness());
	}
	public List<Gene> getGenes() {
		return genes;
	}
	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}
	public List<FittedValue> getFittedValues() {
		return fittedValues;
	}
	public void setFittedValues(List<FittedValue> fittedValues) {
		this.fittedValues = fittedValues;
	}
	/**
	 * 返回当前个体的适应值
	 * @return 当前个体的适应值
	 */
	public Float getFitness(){
		return fitness;
	}
	/**
	 * 设置当前个体的适应值
	 * @param fitness 当前个体的适应值
	 */
	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}
	/**
	 * 一个个体可能具有多个同源基因，一个个体内的多个同源基因可能产生多个不同的表达式，这个方法将返回在对某个确定的输入集求解的时候使用哪个同源基因翻译个体
	 * @return 被选择的同源基因的编号，从0开始，最大值为普通基因数量－1
	 */
	public int getSelectedHomeoticGeneNumber() {
		return selectedHomeoticGeneNumber;
	}
	/**
	 * 设置在求解输入集的时候将使用哪个同源基因上传个体。
	 * @param selectedHomeoticGeneNumber 同源基因编号
	 */
	public void setSelectedHomeoticGeneNumber(int selectedHomeoticGeneNumber) {
		this.selectedHomeoticGeneNumber = selectedHomeoticGeneNumber;
	}
	@Override
	public Individual clone(){
		// TODO Auto-generated method stub
		Individual individual=null;
		try {
			individual=(Individual) super.clone();
			if (genes!=null) {
				List<Gene> copiedGenes=new ArrayList<Gene>(genes.size());
				if(genes.size()!=0)
					for(int i=0;i<genes.size();i++)
						copiedGenes.add(genes.get(i).clone());
				individual.setGenes(copiedGenes);
			}
			if(fittedValues!=null){
				List<FittedValue> copiedFittedValues=new ArrayList<FittedValue>(fittedValues.size());
				if(fittedValues.size()!=0)
					for(int i=0;i<fittedValues.size();i++)
						fittedValues.add(copiedFittedValues.get(i).clone());
				individual.setFittedValues(copiedFittedValues);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return individual;
	}
}
