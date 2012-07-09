package domain.core.algmodel.gene;

import java.util.List;

import domain.core.algmodel.genepiece.GenePiece;

/**
 * ͬԴ����ʵ����
 * @author ��Զ
 * 
 */
public class HomeoticGene extends Gene{
	private static final long serialVersionUID = 4297205056477494966L;
	/**
	 * ����ͬԴ����Ĺ��캯�����û���Ҫ�ڵ�ǰ�����Ӧ�ĸ����ȫ������Ƭ����ָ����ǰ����ӻ���Ƭ�ε����￪ʼ�����������
	 * @param headerLength ͬԴ�����ͷ������
	 * @param tailLength ͬԴ�����β������
	 * @param genePieces ��������������ȫ������Ƭ��
	 * @param start ��ǰ�����ڸ������Ƭ���п�ʼ�ĵط������������ַ
	 * @param end ��ǰ�����ڸ������Ƭ���н����ĵط��������������ַ
	 */
	public HomeoticGene(int headerLength,int tailLength,List<GenePiece> genePieces,int start,int end){
		super(genePieces,start,end);
		header=new HomeoticGeneHeader(genePieces,start,start+headerLength);
		tail=new HomeoticGeneTail(genePieces,start+headerLength, end);
	}
	@Override
	public HomeoticGeneHeader getHeader() {
		// TODO Auto-generated method stub
		return (HomeoticGeneHeader) header;
	}
	@Override
	public HomeoticGeneTail getTail() {
		// TODO Auto-generated method stub
		return (HomeoticGeneTail) tail;
	}


}
