package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.read.biff.BiffException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import data.dao.HibernateDataContext;
import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataRow;
import domain.core.algInputDataProcess.DataSet;
import domain.service.algInputDataProcess.DataInputService;


public class DataInputServiceTest {

	
	private static DataInputService dataInputService = null;
	
	
	
	@Before
	public void setUp() throws Exception {
		HibernateDataContext hibernateDataContext = new HibernateDataContext();
		dataInputService = new DataInputService(hibernateDataContext);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}


	

	@Test
	public void testProcessInputDataSet() {
		DataSet result = new DataSet(10,1,"InputDemo.xls");
		List<DataRow> dataRows = new ArrayList<DataRow>(10);
		List<DataColumn> dataColumns;
		DataRow dataRow;
		DataColumn resultColumn;
		DataColumn normalColumn;
		
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(6.9408f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(50f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(-7.8664f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(7.341f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(-2.7861f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(-4.477f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(-5.0944f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(-2.307f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(9.4895f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(73.494f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(-9.6197f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(17.41f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(-9.4145f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(16.073f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(-0.1432f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(-0.419f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(0.9107f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(3.147f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			
/******************************************************/
			dataRow = new DataRow();
			dataColumns = new ArrayList<DataColumn>(1);
			normalColumn = new DataColumn();
			normalColumn.setColumnName("a");
			normalColumn.setValue(2.1762f);
			dataColumns.add(normalColumn);
			resultColumn = new DataColumn();
			resultColumn.setColumnName("f(a)");
			resultColumn.setValue(8.897f);
			dataRow.setDataColumns(dataColumns);
			dataRow.setResultColumn(resultColumn);
			dataRows.add(dataRow);
/******************************************************/
			result.setDataRows(dataRows);
			
			String inputPath = "d:\\InputDemo.xls";
			File inputFile = new File(inputPath);
			DataSet actualDataSet = null;
			try {
				actualDataSet = dataInputService.processData(inputFile);
			} catch (BiffException e) {} 
			catch (IOException e) {}
			
			assertEquals(result, actualDataSet);
			
			
		
	}

	
	
	
	
	@Ignore("Not yet implemented")
	@Test
	public void testCommit() {
		fail("Not yet implemented");
	}

	@Ignore("Not yet implemented")
	@Test
	public void testGetDataSets() {
		fail("Not yet implemented");
	}

}
