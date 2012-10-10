package domain.iservice.algOutput;

import domain.core.algOutput.GenePiece;
import domain.core.algconfiguration.Function;

public interface IGenerator {
	public GenePiece nextFunction();
	public GenePiece nextFunction(Function function);
	public GenePiece nextVariable();
	public GenePiece nextNoramlGeneNum();
	public GenePiece nextNoramlGeneNum(int num);
}
