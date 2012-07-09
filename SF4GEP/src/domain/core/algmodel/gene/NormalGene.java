package domain.core.algmodel.gene;

import java.util.List;

import domain.core.algmodel.genepiece.GenePiece;

/**
 * ��ͨ����ʵ����
 * @author ��Զ
 *
 */
public class NormalGene extends Gene{
	private static final long serialVersionUID = -4798878563976876499L;
	/**
	 * ������ͨ����Ĺ��캯�����û���Ҫ�ڵ�ǰ�����Ӧ�ĸ����ȫ������Ƭ����ָ����ǰ����ӻ���Ƭ�ε����￪ʼ�����������
	 * @param headerLength ��ͨ�����ͷ������
	 * @param tailLength ��ͨ�����β������
	 * @param genePieces ��������������ȫ������Ƭ��
	 * @param start ��ǰ�����ڸ������Ƭ���п�ʼ�ĵط������������ַ
	 * @param end ��ǰ�����ڸ������Ƭ���н����ĵط��������������ַ
	 */
	public NormalGene(int headerLength,int tailLength,List<GenePiece> genePieces,int start,int end){
		super(genePieces,start,end);
		header=new NormalGeneHeader(genePieces,start,start+headerLength);
		tail=new NormalGeneTail(genePieces,start+headerLength, end);
	}

	@Override
	public NormalGeneHeader getHeader() {
		// TODO Auto-generated method stub
		return (NormalGeneHeader) header;
	}
	@Override
	public NormalGeneTail getTail() {
		// TODO Auto-generated method stub
		return (NormalGeneTail) tail;
	}

}
