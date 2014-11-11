import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Tabelle extends JFrame{
	private static final long serialVersionUID = 1L;
	
	String[] content={"Datei","minY","maxY","minX","maxX","minZ","maxZ","rasX","rasY","anzPunkte"};
	static Object[][] data;
	double[] minmax=new double[4];
	static Scanner sc;

    JTable table;
    JScrollPane scrollpane = new JScrollPane();
	
	public Tabelle(File[] files) {
		data = new Object[files.length][10];
		for(int i=0;i<files.length;i++){
				data[i][0]=files[i].getName();
				minmax=MapItUtils.getMinMax(files[i]);
				data[i][1]=minmax[0];
				data[i][2]=minmax[1];
				data[i][3]=minmax[2];
				data[i][4]=minmax[3];
				data[i][5]=minmax[4];
				data[i][6]=minmax[5];
				data[i][7]=(int)minmax[6];
				data[i][8]=(int)minmax[7];
				data[i][9]=(int)minmax[8];
		}
		sort();
		table = new JTable (data,content);
		this.setPreferredSize(new Dimension(700,250));
		this.setTitle("Tabelle");
		this.setLayout(new BorderLayout());
        add(scrollpane);
        table.setEnabled(false);
		table.getTableHeader();
		table.setFillsViewportHeight(true);
		scrollpane.setViewportView(table);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		this.pack();
	}
	
	public Tabelle() {
		
	}
	
	

	public double[] minmaxvalue(){
		double[] minmax=new double[4];
		minmax[0]=Double.parseDouble(data[0][1].toString());
		minmax[1]=Double.parseDouble(data[0][2].toString());
		minmax[2]=Double.parseDouble(data[0][3].toString());
		minmax[3]=Double.parseDouble(data[0][4].toString());
			for(int i=0;i<data.length-1;i++){
				if (Double.parseDouble(data[i+1][1].toString())<Double.parseDouble(data[i][1].toString()))
					minmax[0]=Double.parseDouble(data[i+1][1].toString());
				if (Double.parseDouble(data[i+1][2].toString())>Double.parseDouble(data[i][2].toString()))
					minmax[1]=Double.parseDouble(data[i+1][2].toString());
				if (Double.parseDouble(data[i+1][3].toString())<Double.parseDouble(data[i][3].toString()))
					minmax[2]=Double.parseDouble(data[i+1][3].toString());
				if (Double.parseDouble(data[i+1][4].toString())>Double.parseDouble(data[i][4].toString()))
					minmax[3]=Double.parseDouble(data[i+1][4].toString());
			}	
		return minmax;
	}
	
	public void sort(){
		Object[] save=new Object[10];
		for(int j=0;j<data.length;j++){
			for(int i=0;i<data.length-1;i++){
				 if(Double.parseDouble(data[i][1].toString())>Double.parseDouble(data[i+1][1].toString())){
						save=data[i];
						data[i]=data[i+1];
						data[i+1]=save;
				 }
				else if(data[i][1].equals(data[i+1][1]))
					if(Double.parseDouble(data[i][3].toString())>Double.parseDouble(data[i+1][3].toString())){
					save=data[i];
					data[i]=data[i+1];
					data[i+1]=save;
					}		
			}
		}
	}
}
