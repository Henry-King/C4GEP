package domain.iservice;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.inputmodel.DataTable;

public interface IgepInputService {
	
	//public void uploadFile (DataTable set,File file) throws IOException, BiffException;
	
	
	/**
	 * ��������·��
	 * @param path ����·��
	 * @author ��ͨ
	 */
	void setFile(File path);
	/**
	 * ��ȡ�ļ�,���������input��
	 * @param input �����������
	 * @author ��ͨ
	 * @throws BiffException 
	 */
	void read(DataTable input) throws IOException, BiffException;

}
