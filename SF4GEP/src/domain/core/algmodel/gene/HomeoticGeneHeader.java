package domain.core.algmodel.gene;

import java.util.List;

import domain.core.algmodel.genepiece.GenePiece;

/**
 * ͬԴ����ͷ����ʵ����
 * @author ��Զ
 *
 */
public class HomeoticGeneHeader extends Header{
	private static final long serialVersionUID = 5276198147420739676L;
	/**
	 * ��������ͷ���Ĺ��캯�����û���Ҫ�ṩ�������߻����еĻ���Ƭ��List����ȷ��ͷ�����ĸ���ַ��ʼ�����ĸ���ַ����
	 * @param genePieces ����Ƭ��List
	 * @param start ����ͷ����ʼ�ĵ�ַ������
	 * @param end ����ͷ�������ĵ�ַ��������
	 */
	public HomeoticGeneHeader(List<GenePiece> genePieces,int start,int end) {
		// TODO Auto-generated constructor stub
		super(genePieces,start,end);
	}
	
}
