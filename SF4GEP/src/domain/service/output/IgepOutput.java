package domain.service.output;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import jxl.write.WriteException;

import domain.core.outputmodel.AlgInstance;



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
	 * ������ͼ��(ȫ������)
	 * @param parameter ��ͼ����Ҫ�Ĳ������Ҳ�֪������Ҫ���ʲô���ͣ����Բ���ΪObject����ȷ�������ٸĲ�������
	 * @author ������
	 */
	ImageIcon drawImage(AlgInstance output);
}
