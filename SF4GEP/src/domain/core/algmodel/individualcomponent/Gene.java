package domain.core.algmodel.individualcomponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

public abstract class Gene implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7104627974958183372L;
	/**
	 * 
	 */

	private float value;
	private int start;
	private int end;
	private List<GenePiece> originalGenePiece;
	protected List<GenePiece> containedGenePieces;
	protected Header header;
	protected Tail tail;
	public Gene(List<GenePiece> origGenePieces,int start,int end){
		this.originalGenePiece=origGenePieces;
		this.start=start;
		this.end=end;
		containedGenePieces=origGenePieces.subList(start, end);
	}
	public abstract Header getHeader();
	public abstract Tail getTail();
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			value=in.readFloat();
			start=in.readInt();
			end=in.readInt();
			originalGenePiece=(List<GenePiece>) in.readObject();
			containedGenePieces=originalGenePiece.subList(start, end);
			header=(Header) in.readObject();
			tail=(Tail) in.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeObject(ObjectOutputStream out){
		try {
			out.writeFloat(value);
			out.writeInt(start);
			out.writeInt(end);
			out.writeObject(originalGenePiece);
			out.writeObject(header);
			out.writeObject(tail);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
