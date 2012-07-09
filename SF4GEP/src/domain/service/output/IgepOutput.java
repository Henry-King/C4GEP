package domain.service.output;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;

import jxl.write.WriteException;

import domain.core.algmodel.configuration.Individual;
import domain.core.outputmodel.AlgInstance;
import domain.service.alg.baseclass.Calculator;



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
	 * 画拟合曲线图
	 * @param output
	 * @return MathCanvas对象
	 */
	public MathCanvas drawImageA(Calculator calculator, Individual individual,KernelLink ml);
	
	/**
	 * 画每代最佳个体、最差个体的演化曲线图
	 * @param output
	 * @return MathCanvas对象
	 */
	public MathCanvas drawImageB(AlgInstance output,KernelLink ml);
	
	
}
