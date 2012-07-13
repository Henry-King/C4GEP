package domain.service.algInputDataProcess;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataRow;
import domain.core.algInputDataProcess.DataSet;
import domain.iservice.algInputDataProcess.IDataInputService;

public class DataInputService implements IDataInputService {

	@Override
	public DataSet processInputDataSet(File path) throws BiffException, IOException {
		// TODO Auto-generated method stub
		DataRow sheetRow;
		List<DataColumn> sheetColumns;
		DataColumn resultColumn;
		DataColumn normalColumn;
		DataSet dataSet = null;
		Workbook readFile = Workbook.getWorkbook(new BufferedInputStream(
				new FileInputStream(path)));
		Sheet sheet = readFile.getSheet(0);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		dataSet = new DataSet(rows, columns, path.getName());
		List<DataRow> sheetRows = new ArrayList<DataRow>(rows - 1);
		for (int i = 1; i < rows; i++) {
			sheetRow = new DataRow();
			sheetColumns = new ArrayList<DataColumn>(columns - 1);
			for (int j = 0; j < columns - 1; j++) {
				normalColumn = new DataColumn();
				normalColumn.setColumnName(sheet.getCell(j, 0).getContents());
				normalColumn.setValue(Float.valueOf(sheet.getCell(j, i).getContents()));
				sheetColumns.add(normalColumn);
			}
			resultColumn = new DataColumn();
			resultColumn.setColumnName(sheet.getCell(columns - 1,0).getContents());
			resultColumn.setValue(Float.valueOf(sheet.getCell(columns - 1,i).getContents()));
			sheetRow.setDataColumns(sheetColumns);
			sheetRow.setResultColumn(resultColumn);
			sheetRows.add(sheetRow);
		}
		dataSet.setDataRow(sheetRows);
		return dataSet;
	}
	@Override
	public boolean commit(DataSet dataSet) {
		// TODO Auto-generated method stub
		return false;
	}

}
