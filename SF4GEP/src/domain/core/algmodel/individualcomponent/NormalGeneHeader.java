package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

/**
 * ��ͨ����ͷ����ʵ����
 * @author ��Զ
 *
 */
public class NormalGeneHeader extends Header {
	private static final long serialVersionUID = 6872200051330701939L;

	/**
	 * ��������ͷ���Ĺ��캯�����û���Ҫ�ṩ�������߻����еĻ���Ƭ��List����ȷ��ͷ�����ĸ���ַ��ʼ�����ĸ���ַ����
	 * @param genePieces ����Ƭ��List
	 * @param start ����ͷ����ʼ�ĵ�ַ������
	 * @param end ����ͷ�������ĵ�ַ��������
	 */
	public NormalGeneHeader(List<GenePiece> genePieces,int start,int end) {
		super(genePieces,start,end);
		// TODO Auto-generated constructor stub
	}
}
