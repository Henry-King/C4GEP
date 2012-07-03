package domain.service.input;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;



import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import domain.core.algmodel.genecomponent.Variable;
import domain.core.inputmodel.FieldRow;
import domain.core.inputmodel.InputSet;
import domain.core.inputmodel.ResultFieldItem;
import domain.core.inputmodel.VariableFiledItem;
import domain.core.inputmodel.VariableRow;

public class DefaultGepInput implements IgepInput {

	private File Path;
	
	public DefaultGepInput(){
		
	}
	@Override
	public void setFile(File path) {
		// TODO Auto-generated method stub
		Path=path;
	}

	@Override
	public void read(InputSet input) throws BiffException, IOException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(Path);

		// 声名一个工作薄
		Workbook rwb = Workbook.getWorkbook(is);

		// 获得工作薄的个数
		rwb.getNumberOfSheets();

		// 在Excel文档中，第一张工作表的缺省索引是0
		Sheet st = rwb.getSheet(0);

		// 通用的获取cell值的方式,getCell(int column, int row) 行和列
		int Rows = st.getRows();
		int Cols = st.getColumns();
		VariableRow vRow=new VariableRow(Cols);
		Variable variable;
		for (int i = 0; i < Cols; i++) {
			variable = new Variable();
			variable.setName(st.getCell(i, 0).getContents());
			vRow.add(variable);
		}
		input.setVariableRow(vRow);
		
		FieldRow fRow;
		VariableFiledItem vFiledItem;
		ResultFieldItem rFieldItem;
		for (int i = 1; i < Rows; i++) {
			fRow=new FieldRow(Cols);
			for (int j = 0; j < Cols-1; j++) {
				vFiledItem=new VariableFiledItem(Float.valueOf(st.getCell(j, i).getContents()));
				vFiledItem.setName(vRow.getVariableList().get(j).getName());
				fRow.addItem(vFiledItem);
			}
			rFieldItem=new ResultFieldItem(Float.valueOf(st.getCell(Cols-1 ,i).getContents()));
			rFieldItem.setName(vRow.getVariableList().get(Cols-1).getName());
			fRow.addItem(rFieldItem);
			input.getFieldRowList().add(fRow);
		}
	}
}
