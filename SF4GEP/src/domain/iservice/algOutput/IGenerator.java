package domain.iservice.algOutput;

import domain.core.algOutput.GenePiece;

public interface IGenerator {
	public GenePiece nextFunction();
	public GenePiece nextVariable();
	public GenePiece nextNoramlGeneNum();
}
