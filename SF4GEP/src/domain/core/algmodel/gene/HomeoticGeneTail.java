package domain.core.algmodel.gene;

import java.util.List;

import domain.core.algmodel.genepiece.GenePiece;

/**
 * ͬԴ����β��ʵ����
 * @author ��Զ
 *
 */
public class HomeoticGeneTail extends Tail{

	private static final long serialVersionUID = -3919750383832279100L;
	/**
	 * ��������β���Ĺ��캯�����û���Ҫ�ṩ�������߻����еĻ���Ƭ��List����ȷ��ͷ�����ĸ���ַ��ʼ�����ĸ���ַ����
	 * @param genePieces ����Ƭ��List
	 * @param start ����ͷ����ʼ�ĵ�ַ������
	 * @param end ����ͷ�������ĵ�ַ��������
	 */
	public HomeoticGeneTail(List<GenePiece> genePieces,int start,int end) {
		super(genePieces,start,end);
		// TODO Auto-generated constructor stub
	}

}
