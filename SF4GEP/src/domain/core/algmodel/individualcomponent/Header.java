package domain.core.algmodel.individualcomponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.genecomponent.GenePiece;

/**
 * ����ͷ��ʵ��Ļ��࣬�Ǹ�������
 * @author ��Զ
 *
 */
public abstract class Header implements Serializable,ICopy<Header>{
	private static final long serialVersionUID = -4787817859125343269L;
	protected List<GenePiece> containedGenePieces;
	private List<GenePiece> genePieces;
	private int start;
	private int end;
	/**
	 * ��������ͷ���Ĺ��캯�����û���Ҫ�ṩ�������߻����еĻ���Ƭ��List����ȷ��ͷ�����ĸ���ַ��ʼ�����ĸ���ַ����
	 * @param genePieces ����Ƭ��List
	 * @param start ����ͷ����ʼ�ĵ�ַ������
	 * @param end ����ͷ�������ĵ�ַ��������
	 */
	public Header(List<GenePiece> genePieces,int start,int end){
		this.genePieces=genePieces;
		this.start=start;
		this.end=end;
		this.containedGenePieces=genePieces.subList(start, end);
	}
	/**
	 * ����ͷ���������Ļ���Ƭ�Ρ�
	 * @return ����Ƭ��List
	 */
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			genePieces=(List<GenePiece>) in.readObject();
			start=in.readInt();
			end=in.readInt();
			containedGenePieces=genePieces.subList(start, end);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeObject(ObjectOutputStream out){
		try {
			out.writeObject(genePieces);
			out.writeInt(start);
			out.writeInt(end);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Header copy() {
		// TODO Auto-generated method stub
		Header header=null;
		if(this instanceof NormalGeneHeader){
			header=new NormalGeneHeader(genePieces, start, end);
		}
		else if(this instanceof HomeoticGeneHeader) {
			header=new HomeoticGeneHeader(genePieces, start, end);
		}
		return header;
	}
}
