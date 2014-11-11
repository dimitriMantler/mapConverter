import java.io.*;
import java.util.*;

public class MapItUtils{
	
	static Scanner sc;
	static File file;
	static File dir;
	static FileWriter fwriter;
	
	public static void WriteRas(String name, double minY, double minX, double abY, double abX, int rasY, int rasX){
		double y,x,z,maxX,maxY;
		try{fwriter=new FileWriter("RAS\\"+name+".ras");}
		catch(Exception e){dir=new File("RAS");dir.mkdir();try{fwriter=new FileWriter("RAS\\"+name+".ras");}catch(Exception f){}}
		try{
			for(int i=0;i<Tabelle.data.length;i++){
				file= new File(MapIt.jtfDir.getText()+"\\"+Tabelle.data[i][0].toString());
				sc= new Scanner(file);
				while(true){
					if(sc.hasNext()==true){
						y=Double.parseDouble(sc.next());
						x=Double.parseDouble(sc.next());
						z=Double.parseDouble(sc.next());
						maxY=minY+abY; // wenn diese berechnung in der Abfrage steht funktioniert es nicht
						maxX=minX+abX; // dito
						if(y>=minY&&y<=maxY&&x>=minX&&x<=maxX)
							if((y-minY)%rasY==0&&(x-minX)%rasX==0)
								fwriter.write(y+" "+x+" "+z+" \r\n");
							else;
						else;
					}
					else break;
				}
				MapIt.jlProg.setText("Ready");
			}
		}
		catch(Exception e){System.out.print(e.getMessage()+"\n"+e.toString());}
		finally{try{fwriter.close();}catch(Exception e){}}
	}
	
	public static double[] getMinMax(File file){
		double value;
		double minX=Double.MAX_VALUE;
		double minY=Double.MAX_VALUE;
		double minZ=Double.MAX_VALUE;
		double maxX=Double.MIN_VALUE;
		double maxY=Double.MIN_VALUE;
		double maxZ=Double.MIN_VALUE;
		double rasX=0;
		double rasY=0;
		double anzPunkte=0;
		double tempX1=0;
		double tempY1=0;
		double tempX2=0;
		double tempY2=0;
		try{
			sc= new Scanner(file);
		}
		catch(Exception e){
			
		}
		for(;sc.hasNext()==true;){
			value=Double.parseDouble(sc.next());
			if(value<minY){
				minY=value;
			}
			if(value>maxY){
				maxY=value;
			}
			if(tempY1==0){
				tempY1=value;
			}
			else{
				if(tempY2==0){
					tempY2=value;
				}
			}
			if(tempY1!=0&&tempY2!=0){
				rasY=tempY2-tempY1;
				if(rasY==0)tempY2=0;
			}
			
			value=Double.parseDouble(sc.next());  //minX,maxX,rasX
			if(value<minX){
				minX=value;
			}
			if(value>maxX){
				maxX=value;
			}
			
			if(tempX1==0){
				tempX1=value;
			}
			else{
				if(tempX2==0){
					tempX2=value;
					rasX=tempX2-tempX1;
					if(rasX==0)tempX2=0;
				}
			}
			
			if(tempY1!=0&&tempY2!=0){
				rasY=tempY2-tempY1;
				if(rasY==0)tempY2=0;
			}
			
			value=Double.parseDouble(sc.next());
			if(value<minZ){
				minZ=value;
			}
			if(value>maxZ){
				maxZ=value;
			}

			anzPunkte++;
			
		}
		double[] temp={minY,maxY,minX,maxX,minZ,maxZ,rasX,rasY,anzPunkte};
		return temp;
	}

	public static void convertToWrl(File f,String name){
		int j=0;
		double progress=0;
		int prog;
		int progAlt=Integer.MAX_VALUE;
		try{fwriter=new FileWriter("WRL\\"+name+".wrl");}
		catch(Exception e){dir=new File("WRL");dir.mkdir();try{fwriter=new FileWriter("WRL\\"+name+".wrl");}catch(Exception ex){}}
		  							  //  0    1   2     3    4    5    6    7     8
		double[] werte=getMinMax(f);  //minY,maxY,minX,maxX,minZ,maxZ,rasX,rasY,anzPunkte
		double[] loc=new double[3];
		double x = werte[3];
		double y = werte[0];
		try{
			fwriter.write("#VRML V2.0 utf8\r\nWorldInfo {\r\ntitle \""+name+"\"\r\ninfo [\r\n\"Transformiert aus "+f.getName()+"\"\r\n]\r\n}\r\nTransform { translation 0.000 0.0 -1000.000\r\nchildren[\r\nShape{\r\ngeometry DEF Terrain ElevationGrid {\r\n" +
				"xDimension "+(int)((werte[1]-werte[0])/werte[7])+"\r\n" +
				"xSpacing "+(werte[7])+"\r\n"+
				"zDimension "+(int)((werte[3]-werte[2])/werte[6])+"\r\n" +
				"zSpacing "+(werte[6])+"\r\n"+
				"height [\r\n");
			
			while(progress!=werte[8]/*x>=werte[2]&&y<=werte[1]*/){
				sc=new Scanner(f);
				while(sc.hasNext()){
					loc[0]=Double.parseDouble(sc.next());     // y
					loc[1]=Double.parseDouble(sc.next());     // x
					loc[2]=Double.parseDouble(sc.next());     // z
					if(loc[1]==x&&loc[0]==y){
						fwriter.write(loc[2]+" ");
//						System.out.println("y= "+y+" x= "+x+"\n");
						if(progress%100==0){
							prog=Math.round((float)(progress/(werte[8]/100)));
							
							if(prog!=progAlt)
								System.out.println(prog+"%");
							progAlt=prog;
						}
						progress++;
						j++;
						if(y!=werte[1])
							y=y+werte[7];
						else{
							x=x-werte[6];
							y=werte[0];		
						}
						if(j>=15){
							fwriter.write("\r\n");
							j=0;
						}
					}
				}

			}
			fwriter.write("\r\n]\r\ncreaseAngle 1.57\r\n}\r\nappearance Appearance {\r\nmaterial DEF M Material {diffuseColor 1 1 1}\r\n#texture DEF IT ImageTexture {url \"bitmap.jpg\"}\r\n#textureTransform TextureTransform{scale 1 1}\r\n} # end appearance\r\n} # end shape\r\n] # end children\r\n} # end transform");
		}
		catch(Exception e){System.out.println(e.getMessage()+"\n"+e.toString());}
		finally{try{fwriter.close();f.delete();}catch(Exception e){}}
	}
	
	public static void convertToScr(File f,String name){
		double[] loc=new double[3];
		double[] minmax=getMinMax(f); //minY,maxY,minX,maxX,minZ,maxZ,rasX,rasY,anzPunkte
		double progress=0;
		int prog;
		int progAlt=Integer.MAX_VALUE;
		int i=0;
		try{fwriter=new FileWriter("SCR\\"+name+".scr");}
		catch(Exception e){dir=new File("SCR");dir.mkdir();try{fwriter=new FileWriter("SCR\\"+name+".scr");}catch(Exception ex){}}
		try{
			double x = minmax[2];
			double y = minmax[0];
			fwriter.write("_3dmesh\r\n101\r\n101\r\n");
			while(x<=minmax[3]&&y<=minmax[1]){
				sc=new Scanner(f);
				while(sc.hasNext()){
					loc[0]=Double.parseDouble(sc.next());
					loc[1]=Double.parseDouble(sc.next());
					loc[2]=Double.parseDouble(sc.next());
					if(loc[1]==x&&loc[0]==y){
						if(loc[0]!=0&&loc[1]!=0)
						fwriter.write((loc[1]-minmax[2])+","+(loc[0]-minmax[0])+","+loc[2]+" \r\n");
							if(progress%100==0){
								prog=Math.round((float)(progress/(minmax[8]/100)));
								
								if(prog!=progAlt)
									System.out.println(prog+"%");
								progAlt=prog;
							}
						progress++;
						i++;
						if(y!=minmax[1])
							y=y+minmax[7];
						else{
							x=x+minmax[6];
							if(x!=minmax[3])
								y=minmax[0];
						}	
					}
				}		
			}
		}
		catch(Exception e){}
		finally{try{fwriter.close();}catch(Exception e){}}
	
	}
}
