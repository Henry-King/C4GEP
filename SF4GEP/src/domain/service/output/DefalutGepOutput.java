package domain.service.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import ui.input.NewFrame;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import domain.core.algmodel.configuration.Individual;
import domain.core.outputmodel.AlgInstance;
import domain.core.outputmodel.OutputIndividual;
import domain.core.outputmodel.OutputPopulation;
import domain.service.alg.configuration.Calculator;

public class DefalutGepOutput implements IgepOutput{
	
	private long start=-1;
	private long end=5;
	@Override
	public void setRange(long start, long end) {
		// TODO Auto-generated method stub
		this.start=start;
		this.end=end;
	}

	@Override
	public void setRange(long end) {
		// TODO Auto-generated method stub
		this.end=end;
	}

	@Override
	public void writeExcel(File path, AlgInstance output) throws IOException,
			WriteException {
		// TODO Auto-generated method stub
			//String filepath="OutputDemo.xls";
			String[] title ={"ID:","runTimes:","totalGeneration:","headerLength","normalgeneNums:","代数"};
			WritableWorkbook wwb;
			OutputStream os=new FileOutputStream(path);
			wwb=Workbook.createWorkbook(os);
			WritableSheet sheet=wwb.createSheet("子代详细信息", 0);
			sheet.setColumnView(0, 20); // 设置列的宽度
			sheet.setColumnView(1, 40);
			WritableCellFormat wc = new WritableCellFormat(); // 单元格定义
			wc.setAlignment(jxl.format.Alignment.RIGHT); // 设置对齐方式
			wc.setWrap(true);
			Label label;
			for(int i=0;i<title.length;i++){
				label=new Label(0,i,title[i]);
				sheet.addCell(label);
			}
			jxl.write.Number numberid=new jxl.write.Number(1,0,output.getGepConfiguration().getId());
			sheet.addCell(numberid);
			jxl.write.Number numbertimes=new jxl.write.Number(1,1,output.getRunTimes());
			sheet.addCell(numbertimes);
			jxl.write.Number numbergene=new jxl.write.Number(1,2,output.getTotalGeneration());
			sheet.addCell(numbergene);
			jxl.write.Number numberlen=new jxl.write.Number(1,3,Double.parseDouble(output.getGepConfiguration().getNormalHeaderLength()));
			sheet.addCell(numberlen);
			jxl.write.Number numbergenenums=new jxl.write.Number(1,4,Integer.parseInt(output.getGepConfiguration().getNormalGeneNumber()));
			sheet.addCell(numbergenenums);
			
			label=new Label(1,6,"最佳个体");
			sheet.addCell(label);
			label=new Label(2,6,"适应值");
			sheet.addCell(label);
			int i=7;
			int j=0;
			for(OutputPopulation p:output.getPopulationSet()){
				if(this.start!=-1&&this.end!=-1){
					if(j>=this.start&&j<this.end){
						jxl.write.Number daishu=new jxl.write.Number(0,i,p.getGeneration());
						sheet.addCell(daishu);
						OutputIndividual bestIndividual=p.getIndvididualSet().iterator().next();
						for(OutputIndividual individual:p.getIndvididualSet())
							if(bestIndividual.getFitness()<individual.getFitness())
								bestIndividual=individual;
						label=new Label(1,i,bestIndividual.getExpression(),wc);
						sheet.addCell(label);
						WritableCellFormat floatFormat = new WritableCellFormat (NumberFormats.FLOAT);			 
						//Number number3 = new Number(1, 4, 3.141519, floatFormat);
					    // 填充产品价格
					    jxl.write.Number nb = new jxl.write.Number(2, i,bestIndividual.getFitness(),floatFormat);
					    sheet.addCell(nb);
					    i++;
					}
				}
				
				if(this.start==-1&&this.end!=-1){
					if(j>output.getTotalGeneration()-this.end){
						jxl.write.Number daishu=new jxl.write.Number(0,i,p.getGeneration());
						sheet.addCell(daishu);
						OutputIndividual bestIndividual=p.getIndvididualSet().iterator().next();
						for(OutputIndividual individual:p.getIndvididualSet())
							if(bestIndividual.getFitness()<individual.getFitness())
								bestIndividual=individual;
						label=new Label(1,i,bestIndividual.getExpression(),wc);
						sheet.addCell(label);
						WritableCellFormat floatFormat = new WritableCellFormat (NumberFormats.FLOAT);			 
						//Number number3 = new Number(1, 4, 3.141519, floatFormat);
					    // 填充产品价格
					    jxl.write.Number nb = new jxl.write.Number(2, i,bestIndividual.getFitness(),floatFormat);
					    sheet.addCell(nb);
					    i++;
					}
				}
				j++;
			}
			
			 wwb.write();
		      // 关闭文件
		      wwb.close();
		}

	@Override
	/**
	 * 最佳个体的拟合曲线图
	 * @author 滕凌哲
	 */
	public MathCanvas drawImageA(Calculator calculator, Individual individual) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
		
		
		return null;
	}

	
	
	@Override
	/**
	 * 每代最佳个体、最差个体的演化曲线图
	 * @author 滕凌哲
	 */
	public MathCanvas drawImageB(AlgInstance output,KernelLink ml) {
		MathCanvas mathCanvasB = new MathCanvas(ml);
		
		Set<OutputPopulation> popSet = output.getPopulationSet();
		StringBuffer generationStr = new StringBuffer();
		StringBuffer bestStr = new StringBuffer();
		StringBuffer worseStr = new StringBuffer();
		
		generationStr.append("x={");
		bestStr.append("yb={");
		worseStr.append("yw={");
		
		Iterator<OutputPopulation> iterator = popSet.iterator();
		while (iterator.hasNext()) {
			OutputPopulation outputPopulation = (OutputPopulation) iterator.next();
			
			generationStr.append(outputPopulation.getGeneration() + ",");
			bestStr.append(outputPopulation.getBestOutputIndividual().getFitness() + ",");
			worseStr.append(outputPopulation.getWorstOutputIndividual().getFitness() + ",");	
		}
		generationStr.delete(generationStr.length()-2, generationStr.length());
		bestStr.delete(bestStr.length()-2, bestStr.length());
		worseStr.delete(worseStr.length()-2, worseStr.length());
		
		generationStr.append("};");
		bestStr.append("};");
		worseStr.append("};");
		
		String str = "MultipleListPlot[yb, yw, PlotJoined -> True,AxesLabel -> {代数, 适应值}, GridLines -> Automatic, PlotLabel -> Style[Framed[每代最佳个体、最差个体的演化曲线图], 16, Blue, Background -> Lighter[Yellow]]]";
		
		mathCanvasB.setMathCommand("<< Graphics`MultipleListPlot`");
		mathCanvasB.setMathCommand(generationStr.toString()+bestStr.toString()+worseStr.toString()+str);
		
		
		
		return mathCanvasB;
	}


	
}
