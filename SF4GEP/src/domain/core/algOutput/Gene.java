package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.core.algconfiguration.GepAlgConfiguration;

/**
 * 基因类，此类为抽象类，提供了基因的通用的信息
 * @author 申远
 *
 */
public class Gene implements Serializable,Cloneable {
	private static final long serialVersionUID = 7104627974958183372L;
	private Integer id;
	private Float value;
	private GeneType geneType;
	private List<GenePiece> genePieces;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getValue() {
		return value;
	}
	/**
	 * 设置当前基因的值
	 * @param value 当前基因的值
	 */
	public void setValue(Float value) {
		this.value = value;
	}
	public GeneType getGeneType() {
		return geneType;
	}
	public void setGeneType(GeneType geneType) {
		this.geneType = geneType;
	}
	public List<GenePiece> getGenePieces() {
		return genePieces;
	}
	public void setGenePieces(List<GenePiece> genePieces) {
		this.genePieces = genePieces;
	}
	/**
	 * Hibernate专用入口,其他函数请勿调用
	 * @return
	 */
	public String getGeneTypeString(){
		return geneType.toString();
	}
	/**
	 * Hibernate专用入口,其他函数请勿调用
	 */
	public void setGeneTypeString(String geneTypeString){
		geneType=GeneType.valueOf(geneTypeString);
	}
	@Override
	public Gene clone(){
		// TODO Auto-generated method stub
		Gene gene=null;
		try {
			gene=(Gene) super.clone();
			if(genePieces!=null){
				List<GenePiece> copiedGenePieces=new ArrayList<GenePiece>(genePieces.size());
				if(genePieces.size()!=0)
					for(int i=0;i<genePieces.size();i++)
						copiedGenePieces.add(genePieces.get(i).clone());
				gene.setGenePieces(copiedGenePieces);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gene;
	}
	
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (int)id;
		result = 37 * result + Float.floatToIntBits(value);
		result = 37 * result + geneType.hashCode();
		result = 37 * result + genePieces.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Gene){
			Gene g = (Gene)o;
			return g.getId().equals(id)
				&&	g.getValue().equals(value)
				&&	g.getGeneType().equals(geneType)
				&&	g.getGenePieces().equals(genePieces);
		}
		else {
			return false;
		}
	}
}
