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
	 * ���������������start����end���Ľ��,�������ֻӰ��дexcel�ļ�����Ӱ�컭����ͼ����д���ݿ�
	 * @param start ��ʼ����Ĵ���������
	 * @param end ��������Ĵ�����������
	 * @author �ſ���
	 */
	void setRange(long start,long end);
	/**
	 * �������������������end�����������ֻӰ��дexcel�ļ�����Ӱ�컭����ͼ����д���ݿ�
	 * @param end ������end��
	 * @author �ſ���
	 */
	void setRange(long end);
	/**
	 * ����������������д��excel��
	 * @param path ����ļ�·��
	 * @param output ������Ľ�����
	 * @author �ſ���
	 * @throws WriteException 
	 */
	void writeExcel(File path,AlgInstance output) throws IOException, WriteException;
	
	
	
	/**
	 * ���������ͼ
	 * @param output
	 * @return MathCanvas����
	 */
	public MathCanvas drawImageA(Calculator calculator, Individual individual,KernelLink ml);
	
	/**
	 * ��ÿ����Ѹ��塢��������ݻ�����ͼ
	 * @param output
	 * @return MathCanvas����
	 */
	public MathCanvas drawImageB(AlgInstance output,KernelLink ml);
	
	
}
