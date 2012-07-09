package domain.service.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import domain.core.inputmodel.DataRow;
import domain.core.inputmodel.DataTable;
import domain.iservice.IgepInputService;

public class GepInputService implements IgepInputService {
private File Path;
	
	public GepInputService(){
		
	}
	@Override
	public void setFile(File path) {
		// TODO Auto-generated method stub
		Path=path;
	}

	@Override
	public void read(DataTable input) throws BiffException, IOException {
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
		for (int i = 0; i < Cols; i++) {
			input.addColumn(st.getCell(i, 0).getContents());
			System.out.println(st.getCell(i, 0).getContents());
		}
		
		
		try {
		
			for (int i = 1; i < Rows; i++) {
				DataRow row = input.newRow();
				for(int j=0;j<Cols;j++){
					row.setValue(j, Float.valueOf(st.getCell(j, i).getContents()));
					System.out.print(row.getValue(j) + "		");
					
					
					
				}
					input.addRow(row);
					System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
