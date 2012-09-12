package domain.iservice.algOutput;

import domain.core.algOutput.GenePiece;

public interface IMutation {
	public GenePiece mutateToFunction();
	public GenePiece mutateToVariable();
	public GenePiece mutateToNormalGeneNumber();
}
