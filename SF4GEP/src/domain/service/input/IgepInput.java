package domain.service.input;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.inputmodel.InputSet;

public interface IgepInput {
	/**
	 * 设置输入路径
	 * @param path 输入路径
	 * @author 陈通
	 */
	void setFile(File path);
	/**
	 * 读取文件,将结果放入input中
	 * @param input 结果放入这里
	 * @author 陈通
	 * @throws BiffException 
	 */
	void read(InputSet input) throws IOException, BiffException;
}
