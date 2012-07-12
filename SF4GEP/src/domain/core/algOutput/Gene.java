package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * �����࣬����Ϊ�����࣬�ṩ�˻����ͨ�õ���Ϣ
 * @author ��Զ
 *
 */
public class Gene implements Serializable,Cloneable {
	private static final long serialVersionUID = 7104627974958183372L;
	private float value;
	private GeneType geneType;
	private List<GenePiece> genePieces;
	public float getValue() {
		return value;
	}
	/**
	 * ���õ�ǰ�����ֵ
	 * @param value ��ǰ�����ֵ
	 */
	public void setValue(float value) {
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
	@Override
	public Gene clone(){
		// TODO Auto-generated method stub
		Gene gene=null;
		try {
			gene=(Gene) super.clone();
			if(genePieces!=null){
				List<GenePiece> copiedGenePieces=new ArrayList<GenePiece>(genePieces.size());
				if(genePieces.size()!=0)
					for(int i=0;i<copiedGenePieces.size();i++)
						copiedGenePieces.add(genePieces.get(i).clone());
				gene.setGenePieces(copiedGenePieces);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gene;
	}
}
