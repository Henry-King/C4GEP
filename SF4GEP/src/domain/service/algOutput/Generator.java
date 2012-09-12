package domain.service.algOutput;

import java.util.List;
import java.util.Random;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algOutput.GenePiece;
import domain.core.algOutput.GenePieceType;
import domain.core.algconfiguration.Function;
import domain.iservice.algOutput.IGenerator;

public class Generator implements IGenerator{
	private Random variableRandom=new Random();
	private Random functionRandom=new Random();
	private Random constantRandom=new Random();
	private List<Function> functionList;
	private List<DataColumn> dataColumns;
	private int normalGeneNumber;
	public Generator(List<Function> functionList,List<DataColumn> dataColumns,int normalGeneNumber) {
		// TODO Auto-generated constructor stub
		this.functionList=functionList;
		this.dataColumns=dataColumns;
		this.normalGeneNumber=normalGeneNumber;
	}
	@Override
	public GenePiece nextFunction() {
		// TODO Auto-generated method stub
		GenePiece result=new GenePiece();
		result.setGenePieceType(GenePieceType.Function);
		Function function=functionList.get(functionRandom.nextInt(functionList.size()));
		result.setFunc(function);
		result.setName(function.getName());
		result.setSymbol(function.getSymbol());
		return result;
	}

	@Override
	public GenePiece nextVariable() {
		// TODO Auto-generated method stub
		GenePiece result=new GenePiece();
		int variableIndex=variableRandom.nextInt(dataColumns.size());
		DataColumn dataColumn=dataColumns.get(variableIndex);
		result.setGenePieceType(GenePieceType.Variable);
		result.setName(dataColumn.getColumnName());
		result.setSymbol(dataColumn.getColumnName());
		result.setVariableIndex(variableIndex);
		return result;
	}

	@Override
	public GenePiece nextNoramlGeneNum() {
		// TODO Auto-generated method stub
		GenePiece result=new GenePiece();
		result.setGenePieceType(GenePieceType.Constant);
		result.setValue((float) constantRandom.nextInt(normalGeneNumber));
		result.setName("");
		result.setSymbol(result.getValue().toString());
		return result;
	}
	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
	public void setDataColumns(List<DataColumn> dataColumns) {
		this.dataColumns = dataColumns;
	}
	public void setNormalGeneNumber(int normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
}
