package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.function.Addition;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;

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
	public int getEffictiveLength(){
		int length=1;
		int arity;
		GenePiece genePiece;
		for(int i=0;i<length;i++){
			genePiece=genePieces.get(i);
			if(genePiece.getGenePieceType()==GenePieceType.Function){
				arity=genePiece.getFunc().getArity();
				length+=arity;
			}					
		}
		return length;
	}
	public int getLastNonTerminate(int efficientLength){
		int genePieceId=-1;
		GenePiece genePiece;
		for(int i=efficientLength-1;i>=0;i--){
			genePieceId=i;
			genePiece=genePieces.get(genePieceId);
			if(genePiece.getGenePieceType()==GenePieceType.Function){
				if(genePiece.isUsed()==false){
					break;
				}
			}
		}
		return genePieceId;
	}
	public List<Boolean> clearFunctionFlag(){
		int geneLength=genePieces.size();
		GenePiece genePiece;
		List<Boolean> flagList=new ArrayList<Boolean>(genePieces.size());
		for(int j=0;j<geneLength;j++)
			if((genePiece=genePieces.get(j)).getGenePieceType()==GenePieceType.Function){
				flagList.add(genePiece.isUsed());
				genePiece.setUsed(false);
			}
		return flagList;
	}
	public String toString(List<Gene> genes){
		int length=getEffictiveLength();
		List<String> stringStack=iniStringStack(length, genes);
		int arity;
		int lastNonTerminate;
		List<Boolean> resultBooleans=clearFunctionFlag();
		while(length>1){
			lastNonTerminate=getLastNonTerminate(length);
			arity=genePieces.get(lastNonTerminate).getFunc().getArity();
			addToStack(lastNonTerminate,arity,stringStack);
			genePieces.get(lastNonTerminate).setUsed(true);
			length-=arity;
		}
		setBackFunctionFlag(resultBooleans);
		return stringStack.toString();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toString(null);
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
		int result = 37;
		result = 37 * result + Float.floatToIntBits(value);
		result = 37 * result + geneType.hashCode();
		result = 37 * result + genePieces.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Gene){
			Gene g = (Gene)o;
			return g.getValue().equals(value)
				&&	g.getGeneType().equals(geneType)
				&&	g.getGenePieces().equals(genePieces);
		}
		else {
			return false;
		}
	}
	private void addToStack(int funcId,int arity,List<String> holder){
		Function func=genePieces.get(funcId).getFunc();
		CharSequence[] params=new String[arity];
		for(int i=0;i<arity;i++)
			params[arity-1-i]=holder.remove(holder.size()-1);
		StringBuilder result=getPieceExpression(func, params, arity);
		holder.set(funcId, result.toString());
	}
	private StringBuilder getPieceExpression(Function func,CharSequence[] params,int arity){
		StringBuilder result=new StringBuilder();
		Class<? extends Function> funClass=func.getClass();
		if(funClass==Addition.class||funClass==Minus.class||funClass==Divide.class||funClass==Multiply.class){
			result.append('(');
			result.append(params[0]);
			result.append(func.getSymbol());
			result.append(params[1]);				
			result.append(')');
		}
		else {
			result.append(func.getSymbol());
			result.append('(');
			result.append(params[0]);
			for(int i=1;i<arity;i++){
				result.append(',');
				result.append(params[i]);
			}
			result.append(')');
		}
		return result;
	}
	private void setBackFunctionFlag(List<Boolean> flags){
		@SuppressWarnings("unused")
		GenePiece genePiece;
		int j=0;
		for(int i=0;i<genePieces.size();i++)
			if((genePiece=genePieces.get(i)).getGenePieceType()==GenePieceType.Function){
				genePieces.get(i).setUsed(flags.get(j));
				j++;
			}
	}
	private List<String> iniStringStack(int length,List<Gene> genes){
		List<String> stringStack=new ArrayList<String>(genePieces.size());
		GenePiece genePiece;
		for(int i=0;i<length;i++)
			if(((genePiece=genePieces.get(i)).getGenePieceType()==GenePieceType.Constant)||genes!=null)
				stringStack.add(genes.get((int) genePiece.getValue().floatValue()).toString());
			else
				stringStack.add(genePieces.get(i).getSymbol());
		return stringStack;
	}
}
