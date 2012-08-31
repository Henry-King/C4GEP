package domain.service.algInputDataProcess;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataRow;
import domain.core.algInputDataProcess.DataSet;
import domain.iservice.algInputDataProcess.IDataInputService;

public class DataInputService implements IDataInputService {
	private IHibernateDataContext hibernateDataContext;
	public DataInputService(IHibernateDataContext hibernateDataContext) {
		// TODO Auto-generated constructor stub
		this.hibernateDataContext=hibernateDataContext;
	}
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
		dataSet = new DataSet(rows-1, columns-1, path.getName());
		List<DataRow> sheetRows = new ArrayList<DataRow>(rows - 1);
		NumberCell numc;
		for (int i = 1; i < rows; i++) {
			sheetRow = new DataRow();
			sheetColumns = new ArrayList<DataColumn>(columns - 1);
			for (int j = 0; j < columns - 1; j++) {
				normalColumn = new DataColumn();
				normalColumn.setColumnName(sheet.getCell(j, 0).getContents());
				if(sheet.getCell(j, i).getType()==CellType.NUMBER){
					numc=(NumberCell) sheet.getCell(j, i);
					normalColumn.setValue((float) numc.getValue());
				}
				else	
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
		dataSet.setDataRows(sheetRows);
		return dataSet;
	}
	@Override
	public DataSet commit(DataSet dataSet) {
		// TODO Auto-generated method stub
		List<? extends DataSet> dataSets=hibernateDataContext.findAll(DataSet.class);
		int dataSetIndex=dataSets.indexOf(dataSet);
		if(dataSetIndex!=-1)
			return dataSets.get(dataSetIndex);
		else {
			try {
				hibernateDataContext.save(dataSet);
				return dataSet;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
				String append=dataSet.getName()+"-"+simpleDateFormat.format(new Date());
				dataSet.setName(append);
				hibernateDataContext.save(dataSet);
				return dataSet;
			}		
		}
	}
	@Override
	public List<DataSet> getDataSets() {
		// TODO Auto-generated method stub
		return hibernateDataContext.findAll(DataSet.class);
	}
	@Override
	public boolean update(DataSet dataSet) {
		// TODO Auto-generated method stub
		boolean result=true;
		try {
			hibernateDataContext.update(dataSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		return result;
	}
}
