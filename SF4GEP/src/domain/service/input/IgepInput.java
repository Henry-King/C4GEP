package domain.service.input;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.inputmodel.InputSet;

public interface IgepInput {
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
	void read(InputSet input) throws IOException, BiffException;
}
