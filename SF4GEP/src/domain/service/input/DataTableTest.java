package domain.service.input;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import domain.core.algmodel.genecomponent.Variable;
import domain.core.inputmodel.FieldRow;
import domain.core.inputmodel.InputSet;
import domain.core.inputmodel.ResultFieldItem;
import domain.core.inputmodel.VariableFiledItem;
import domain.core.inputmodel.VariableRow;

public class DataTableTest {
	static VariableRow vRow;
	static Variable variable;
	static FieldRow fRow;
	static VariableFiledItem vFiledItem;
	static ResultFieldItem rFieldItem;
	static int Rows;
	static int Cols;
	static InputSet input=new InputSet();
	public static void main(String args[]) {
		 DataTable table = new DataTable(); //新建一个数据表对象，不带构造函数参数

		 fillTable(table);//填充数据
		 printTable(table);
		 System.out.print("------------------------------------------------------------------------------------------------------------------------\n");
		 table = new DataTable("TestTable");//新建一个数据表对象，带表名为参数
		 //测试添加数据
		
		 fillTable(table);//填充数据
		
		 printTable(table);
		 System.out.print(table.getValue(0,0));
		 System.out.println(table.getColumnName(1));
		 System.out.println(table.getColumnName(2.176));
		 
	     }
	private static void fillTable(DataTable table) {
        try {
        	File file=new File("InputDemo.xls");
        	read(file);
         //填充20列数据
		      for (int i = 0; i <vRow.getVariableList().size(); i++) {
		    	 table.addColumn(vRow.getVariableList().get(i).getName());
		       }
		 //填充100行数据
		  for (int i = 0; i < Rows-1; i++) {
		     DataRow row = table.newRow();
		       for (int j = 0; j<Cols; j++) {
		                       //循环每列加入数据
		              row.setValue(j,input.getFieldRowList().get(i).getFieldItemList().get(j).getValue());
		     }
		      table.addRow(row);
		   }
		 } catch (Exception e) {
             e.printStackTrace();
		 }
   }
	private static void printTable(DataTable table) {
		   
		  for (int i = 0; i < table.getTotalCount(); i++) {
	           for (int j = 0; j < table.getColumns().size(); j++) {
		             System.out.print(table.getValue(i, j));
		                   if (j < 19)
		                     System.out.print(" , ");
		        }
	         System.out.print("\n");
	         }
	}
	public static  void read(File path) throws BiffException, IOException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(path);

		// 声名一个工作薄
		Workbook rwb = Workbook.getWorkbook(is);

		// 获得工作薄的个数
		rwb.getNumberOfSheets();

		// 在Excel文档中，第一张工作表的缺省索引是0
		Sheet st = rwb.getSheet(0);

		// 通用的获取cell值的方式,getCell(int column, int row) 行和列
	    Rows = st.getRows();
	    Cols = st.getColumns();
		vRow=new VariableRow(Cols);
		
		for (int i = 0; i < Cols; i++) {
			variable = new Variable();
			variable.setName(st.getCell(i, 0).getContents());
			vRow.add(variable);
		}
		//input.setVariableRow(vRow);
		
		
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
