package demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.core.algconfiguration.function.Addition;
import domain.core.algconfiguration.function.Cos;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Exp;
import domain.core.algconfiguration.function.Ln;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;
import domain.core.algconfiguration.function.Sin;
import domain.core.algconfiguration.function.Sqrt;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.iservice.algOutput.IAlgOutputService;
import domain.iservice.algOutput.IAlgRunStep;
import domain.service.algConfiguration.GepConfigurationService;
import domain.service.algInputDataProcess.DataInputService;
import domain.service.algOutput.AlgCpuRunStep;
import domain.service.algOutput.LabService;

public class Lab2 {

	private static float rate=0;
	private static float step=(float) (100/1200.0);
	/**
	 * @param args
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static void main(String[] args) throws BiffException, IOException, RowsExceededException, WriteException {
		// TODO Auto-generated method stub
		IHibernateDataContext hibernateDataContext=GepConfigurationService.initSystem();
		IDataInputService dataInputService=new DataInputService(hibernateDataContext);
		DataSet dataSet=dataInputService.processData(new File("Coal.xls"));
		GepAlgConfiguration gepAlgConfiguration=new GepAlgConfiguration();
		gepAlgConfiguration.setAccuracy((float) 0.6);
		gepAlgConfiguration.setSelectionRange((float) 10);
		gepAlgConfiguration.setName("Lab");
		gepAlgConfiguration.setMaxGeneration((long) 1000000);
		IndividualConfiguration individualConfiguration=new IndividualConfiguration();
		individualConfiguration.setIndividualNumber(80);
		GeneConfiguration geneConfiguration=new GeneConfiguration();
		geneConfiguration.setUseHomeoticGene(false);
		geneConfiguration.setConnectionFunction(new Addition());
		geneConfiguration.setNormalGeneHeaderLength(15);
		geneConfiguration.setNormalGeneNumber(1);
		geneConfiguration.setFunctionUsed(Arrays.asList(new Addition(),new Minus(),new Multiply(),new Divide(),new Sqrt(),new Ln(),new Exp(),new Sin(),new Cos()));
		individualConfiguration.setGeneConfiguration(geneConfiguration);
		gepAlgConfiguration.setIndividualConfiguration(individualConfiguration);	
		OperatorConfiguration operatorConfiguration=new OperatorConfiguration();
		
		operatorConfiguration.setIsTransportRate((float) 0.1);
		operatorConfiguration.setMutateRate((float) 0.04);
		
		operatorConfiguration.setIsElement(new Integer[]{1,2,3});
		operatorConfiguration.setGeneRecombineRate((float) 0.1);
		operatorConfiguration.setGeneTransportRate((float) 0.1);
		operatorConfiguration.setRisElement(new Integer[]{1,2,3});
		operatorConfiguration.setRisTransportRate((float) 0.1);
		
		gepAlgConfiguration.setOperatorConfiguration(operatorConfiguration);
		IgepConfigurationService gepConfigurationService=new GepConfigurationService(hibernateDataContext);
		gepAlgConfiguration=gepConfigurationService.processConf(gepAlgConfiguration, dataSet);
		
		long[][][] one=new long[5][][];
		one[0]=setAndRun(0.05,-1, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		one[1]=setAndRun(0.1,-1, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		one[2]=setAndRun(0.2,-1, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		one[3]=setAndRun(0.3,-1, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		one[4]=setAndRun(0.4,-1, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		
		long[][][] two=new long[5][][];
		two[0]=setAndRun(-1,0.05, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		two[1]=setAndRun(-1,0.1, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		two[2]=setAndRun(-1,0.2, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		two[3]=setAndRun(-1,0.3, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		two[4]=setAndRun(-1,0.4, gepConfigurationService,operatorConfiguration, gepAlgConfiguration, dataSet, hibernateDataContext);
		
		System.out.println(Arrays.deepToString(one));
		System.out.println(Arrays.deepToString(two));
		writeFile(one, two);
	}
	private static void writeFile(long[][][] one,long two[][][]) throws FileNotFoundException, IOException, RowsExceededException, WriteException{
		WritableWorkbook writer=Workbook.createWorkbook(new BufferedOutputStream(new FileOutputStream("统计结果.xls")));
		WritableSheet oneTime=writer.createSheet("单点重组 时间", 0);
		WritableSheet oneGeneration=writer.createSheet("单点重组 代数", 1);
		WritableSheet twoTime=writer.createSheet("两点重组 时间", 2);
		WritableSheet twoGeneration=writer.createSheet("两点重组 代数", 3);
		
		setExcelHead(oneTime, Arrays.asList(0.05,0.1,0.2,0.3,0.4),"单点重组","时间(毫秒)");
		setExcelHead(oneGeneration, Arrays.asList(0.05,0.1,0.2,0.3,0.4),"单点重组","演化代数");
		setExcelHead(twoTime, Arrays.asList(0.05,0.1,0.2,0.3,0.4),"两点重组","时间(毫秒)");
		setExcelHead(twoGeneration, Arrays.asList(0.05,0.1,0.2,0.3,0.4),"两点重组","演化代数");
		
		setExcelData(oneTime,oneGeneration, one);
		setExcelData(twoTime,twoGeneration, two);
		
		writer.write();
		writer.close();
	}
	private static void setExcelHead(WritableSheet sheet,List<Double> data,String title,String context) throws RowsExceededException, WriteException{
		setExcelTitle(sheet, title,context);
		Number tempNumber;
		for(int i=0;i<data.size();i++){
			tempNumber=new Number(i+1, 0, data.get(i));
			sheet.addCell(tempNumber);
		}
	}
	private static void setExcelTitle(WritableSheet sheet,String title,String context) throws RowsExceededException, WriteException{
		Label label;
		label=new Label(0, 0, title);
		sheet.addCell(label);
		label=new Label(0,1,context);
		sheet.addCell(label);
		sheet.mergeCells(0, 1, 0, 100);
	}
	private static void setExcelData(WritableSheet time,WritableSheet generation,long[][][]datas) throws RowsExceededException, WriteException{
		Number tempNumber;
		for(int i=0;i<datas.length;i++){
			for(int j=0;j<datas[0].length;j++){
				tempNumber=new Number(i+1, j+1, datas[i][j][0]);
				time.addCell(tempNumber);
				tempNumber=new Number(i+1, j+1, datas[i][j][1]);
				generation.addCell(tempNumber);
			}
		}
	}
	private static long[][] setAndRun(double onePoint,double twoPoint,IgepConfigurationService gepConfigurationService,
			OperatorConfiguration operatorConfiguration,GepAlgConfiguration gepAlgConfiguration,DataSet dataSet,IHibernateDataContext hibernateDataContext){
		operatorConfiguration.setOnePointRecombineRate((float) onePoint);
		operatorConfiguration.setTwoPointRecombineRate((float) twoPoint);
		gepConfigurationService.save(gepAlgConfiguration);
		long result[][]=new long[100][];
		for(int i=0;i<100;i++){
			result[i]=run(gepAlgConfiguration,dataSet,hibernateDataContext);
			rate+=step;
			System.out.println(rate+" %");
		}
		return result;
	}
	private static long[] run(GepAlgConfiguration gepAlgConfiguration,DataSet dataSet,IHibernateDataContext hibernateDataContext){
		IAlgOutputService algOutputService=new LabService(hibernateDataContext);
		algOutputService.setWriteToDB(false);
		IAlgRunStep runStep=new AlgCpuRunStep();
		long start=System.nanoTime();
		Future<GepAlgRun> resultRun=algOutputService.run(gepAlgConfiguration, runStep, dataSet);
		GepAlgRun gepAlgRun=null;
		try {
			gepAlgRun = resultRun.get();
			algOutputService.shutdownAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end=System.nanoTime();
		long time=TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS);
		if(Long.compare(gepAlgRun.getCurrentPopulation().getGenerationNum(),gepAlgRun.getGepAlgConfiguration().getMaxGeneration()-2)>=0)
			time=-1;
		long result[]=new long[2];
		result[0]=time;
		result[1]=gepAlgRun.getCurrentPopulation().getGenerationNum();
		return result;
	}
}
