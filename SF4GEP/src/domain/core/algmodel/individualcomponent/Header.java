package domain.core.algmodel.individualcomponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

public abstract class Header implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817859125343269L;
	protected List<GenePiece> containedGenePieces;
	private List<GenePiece> genePieces;
	private int start;
	private int end;
	public Header(List<GenePiece> genePieces,int start,int end){
		this.genePieces=genePieces;
		this.start=start;
		this.end=end;
		this.containedGenePieces=genePieces.subList(start, end);
	}
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
}
