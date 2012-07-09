package domain.core.algmodel.gene;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.genepiece.Computable;
import domain.core.algmodel.genepiece.GenePiece;

/**
 * ����β��ʵ��Ļ��࣬��һ��������
 * @author ��Զ
 *
 */
public abstract class Tail implements Serializable,ICopy<Tail>{
	private static final long serialVersionUID = -4667068182615591355L;
	protected List<Computable> computable;
	private List<GenePiece> genePieces;
	private int start;
	private int end;

	/**
	 * ��������β���Ĺ��캯�����û���Ҫ�ṩ�������߻����еĻ���Ƭ��List����ȷ��ͷ�����ĸ���ַ��ʼ�����ĸ���ַ����
	 * @param genePieces ����Ƭ��List
	 * @param start ����ͷ����ʼ�ĵ�ַ������
	 * @param end ����ͷ�������ĵ�ַ��������
	 */
	@SuppressWarnings("unchecked")
	public Tail(List<GenePiece> genePieces,int start,int end){
		this.genePieces=genePieces;
		this.start=start;
		this.end=end;
		this.computable=(List<Computable>)(Object)genePieces.subList(start, end);
	}
	/**
	 * ����β����������ȫ������Ƭ��
	 * @return ������β������Ƭ�ε�List
	 */
	public List<Computable> getContainedGenePieces(){
		return computable;
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			genePieces=(List<GenePiece>) in.readObject();
			start=in.readInt();
			end=in.readInt();
			computable=(List<Computable>)(Object)genePieces.subList(start, end);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e){
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
	public Tail copy() {
		// TODO Auto-generated method stub
		Tail tail=null;
		if(this instanceof NormalGeneTail){
			tail=new NormalGeneTail(genePieces, start, end);
		}
		else if (this instanceof HomeoticGeneTail) {
			tail=new HomeoticGeneTail(genePieces, start, end);
		}
		return tail;
	}
}
