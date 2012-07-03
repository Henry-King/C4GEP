package domain.service.output;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import jxl.write.WriteException;

import domain.core.outputmodel.AlgInstance;



public interface IgepOutput {
	/**
	 * 设置输出代数，从start代到end代的结果,这个方法只影响写excel文件，不影响画函数图像与写数据库
	 * @param start 开始输出的代数，包括
	 * @param end 结束输出的代数，不包括
	 * @author 张开活
	 */
	void setRange(long start,long end);
	/**
	 * 设置输出代数，输出最后end代，这个方法只影响写excel文件，不影响画函数图像与写数据库
	 * @param end 输出最后end代
	 * @author 张开活
	 */
	void setRange(long end);
	/**
	 * 根据输出代数将结果写到excel中
	 * @param path 输出文件路径
	 * @param output 待输出的结果结合
	 * @author 张开活
	 * @throws WriteException 
	 */
	void writeExcel(File path,AlgInstance output) throws IOException, WriteException;
	/**
	 * 画函数图像(全部数据)
	 * @param parameter 画图所需要的参数，我不知道到底要输出什么类型，所以参数为Object，你确定后我再改参数类型
	 * @author 滕凌哲
	 */
	ImageIcon drawImage(AlgInstance output);
}
