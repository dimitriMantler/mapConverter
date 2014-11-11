import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class MapIt extends JFrame{
	private static final long serialVersionUID = 1L; // Muss einfach drin sein
	
	Tabelle      tabframe;
	Help         helpframe  = new Help();
	JTabbedPane  jtp = new JTabbedPane();
	Boolean      activ=false;
	String[]     ext={".ras"};
	File         f;
	File         f2;
	File         dir;
	File         convert;
	File[]       files;
	File[]       chosen;
	JPanel		 jpMenu  = new JPanel();
	JPanel		 content = new JPanel();
	JPanel       jpconvert=new JPanel();
	JPanel       jphelp  = new JPanel();
	JLabel       jlminX  = new JLabel("minX");
	JLabel       jlminY  = new JLabel("minY");
	JLabel       jlabX   = new JLabel("abstandX");
	JLabel       jlabY   = new JLabel("abstandY");
	JLabel       jlrasY  = new JLabel("rasY");
	JLabel       jlrasX  = new JLabel("rasX");
	JLabel       jlExt   = new JLabel("Extension");
	JLabel       jlras   = new JLabel("Rasterdatei angeben:");
	static JLabel jlProg  = new JLabel();
	JTextField 	 jtfminX = new JTextField();
	JTextField 	 jtfminY = new JTextField();
	JTextField 	 jtfabX  = new JTextField();
	JTextField 	 jtfabY  = new JTextField();
	JTextField 	 jtfrasX = new JTextField();
	JTextField 	 jtfrasY = new JTextField();
	static JTextField jtfDir= new JTextField();
	static JTextField jtfDir2= new JTextField();
	JButton      jbhelp  = new JButton("Help");
	JButton		 jbGo    = new JButton("Go");
	JButton		 jbBrowse= new JButton("Browse");
	JButton		 jbBrowse2=new JButton("Browse");
	JButton      jbGhost = new JButton();
	JButton      jbGhost2= new JButton();
	JButton      jbGhost3= new JButton();
	JButton      jbGhost4= new JButton();
	JButton      jbGhost5= new JButton();
	JButton      jbwrl   = new JButton("to *.wrl");
	JButton      jbscr   = new JButton("to *.scr");
	JFileChooser jfcCon  = new JFileChooser();
	JFileChooser jfc     = new JFileChooser();
	JComboBox    jcbExt  = new JComboBox(ext);
	
	
	//Eingaben ermöglichen createRAS
	public void activate(Boolean activ){
	        jtfminX.setEditable(activ);
	        jtfminY.setEditable(activ);
	        jtfabX.setEditable(activ);
	        jtfabY.setEditable(activ);
	        jtfrasX.setEditable(activ);
	        jtfrasY.setEditable(activ);
	        jbGo.setEnabled(activ);
	 }
	
	//Eingaben ermöglichen convert
	public void activate2(Boolean activ2){
        jbwrl.setEnabled(activ2);
        jbscr.setEnabled(activ2);
 }

	public int Size(){
		 return files.length;
	 }
	 
	public int lokx(){
		return this.getX();
	}
	
	public int loky(){
		return this.getY();
	}
	 
	public MapIt(){

		this.setDefaultCloseOperation(Tabelle.EXIT_ON_CLOSE);  //Schließt die Tabelle wenn das Programm beendet wird
		this.setLayout(new BorderLayout());
		
		add(jtp,BorderLayout.CENTER);
		jtp.addTab("createRAS", content);
		jtp.setMnemonicAt(0, KeyEvent.VK_1);
		jtp.addTab("convert", jpconvert);
		jtp.setMnemonicAt(0, KeyEvent.VK_2);
		jtp.addTab("?", jphelp);
		jtp.setMnemonicAt(0, KeyEvent.VK_3);
		
		content.setLayout(new GridLayout(9,2));
		content.add(jcbExt);
		content.add(jlExt);
		content.add(jtfDir);
		content.add(jbBrowse);
		content.add(jtfminX);
		content.add(jlminX);
		content.add(jtfminY);
		content.add(jlminY);
		content.add(jtfabX);
		content.add(jlabX);
		content.add(jtfabY);
		content.add(jlabY);
		content.add(jtfrasX);
		content.add(jlrasX);
		content.add(jtfrasY);
		content.add(jlrasY);
		content.add(jbGhost);
		content.add(jbGo);
	
		add(jlProg,BorderLayout.SOUTH);
		
		jpconvert.setLayout(new GridLayout(6,1));
		jpconvert.add(jlras);
		jpconvert.add(jtfDir2);
		jpconvert.add(jbBrowse2);
		jpconvert.add(jbGhost3);
		jpconvert.add(jbscr);
		jpconvert.add(jbwrl);
		jphelp.add(jbhelp);
		
		//Setzen der Eigenschaften der Elemente
		jlProg.setText("Ready");
        jbGhost.setVisible(false);
        jbGhost2.setVisible(false);
        jbGhost3.setVisible(false);
        jtfDir.setEditable(false);
        jtfDir2.setEditable(false);
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        activate(false);
        activate2(false);
        
        jbBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jlProg.setText("Please wait...");
				if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					if(activ==true)tabframe.dispose();
					jtfDir.setText(jfc.getSelectedFile().getAbsolutePath());
					dir  = jfc.getSelectedFile();
					files= dir.listFiles();
					int j=0;
					for(int i=0;i<files.length;i++){
						if(files[i].getName().toLowerCase().endsWith(ext[jcbExt.getSelectedIndex()])){
							j++;
						}
					}
					chosen= new File[j];
					int k=0;
					for(int i=0;i<files.length;i++){
						if(files[i].getName().toLowerCase().endsWith(ext[jcbExt.getSelectedIndex()])){
							chosen[k]=files[i];
							k++;
						}
					}
					try{
						if(chosen[0]!=null)
							activate(true);
					}
					catch(Exception e){
						activate(false);
					}
					activ=true;
					tabframe  = new Tabelle(chosen);
					tabframe.setVisible(true);
					tabframe.setLocation(lokx()+200,loky());
					jlProg.setText("Ready");
				}
				else jlProg.setText("Ready");
			}
        });
        
		jbGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String name="";
					double[] temp=tabframe.minmaxvalue();
					double minY=Double.parseDouble(jtfminY.getText());
					double abY=Double.parseDouble(jtfabY.getText());
					int rasY=Integer.parseInt(jtfrasY.getText());
					double minX=Double.parseDouble(jtfminX.getText());
					double abX=Double.parseDouble(jtfabX.getText());
					int rasX=Integer.parseInt(jtfrasX.getText());
					if(temp[0]<=minY&&temp[2]<=minX&&temp[1]>=minY+abY&&temp[3]>=minX+abX){
						name=JOptionPane.showInputDialog("Bitte Namen der Datei eingeben");
						if(name.equals("null"))throw new IOException();
						jlProg.setText("Please wait...creating "+name+".ras");
					    MapItUtils.WriteRas(name,minY,minX,abY,abX,rasY,rasX);
						JOptionPane.showMessageDialog(null, "Die Datei \""+name+".ras\" wurde erfolgreich erstellt!","", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else JOptionPane.showMessageDialog(null, "Die Werte existieren nicht in den Dateien", "Fehler!", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException e){}
				catch(NullPointerException e){}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Felder nicht richtig ausgefüllt", "Fehler!", JOptionPane.ERROR_MESSAGE);}
				}	
				
				
		});

		jbhelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				helpframe.setVisible(true);
				helpframe.setLocation(lokx()+200,loky());
			}
			
		});
		
		jbBrowse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jlProg.setText("Please wait...");
				if(jfcCon.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					f2  = jfcCon.getSelectedFile();
					jtfDir2.setText(f2.getPath());
					if(f2.getName().endsWith(".ras")){
						activate2(true);
						jlProg.setText("Ready");
					}
					else {
						activate2(false);
						jlProg.setText("Ready");
						JOptionPane.showMessageDialog(null, "Bitte geben sie eine Rasterdatei an!", "Fehler!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else jlProg.setText("Ready");
			}
			
		});
		
		jbwrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					jlProg.setText("Please wait...create wrl");
					String name=JOptionPane.showInputDialog("Bitte Namen der Datei eingeben");
					if(name.equals("null"))throw new IOException();
					MapItUtils.convertToWrl(f2,name);
					JOptionPane.showMessageDialog(null, "Die Datei \""+name+".wrl\" wurde erfolgreich erstellt!","", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e){jlProg.setText("Ready");}
			}
		});
		
		jbscr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					jlProg.setText("Please wait...create scr");
					String name=JOptionPane.showInputDialog("Bitte Namen der Datei eingeben");
					if(name.equals("null"))throw new IOException();
					MapItUtils.convertToScr(f2,name);
					JOptionPane.showMessageDialog(null, "Die Datei \""+name+".scr\" wurde erfolgreich erstellt!","", JOptionPane.INFORMATION_MESSAGE);	
					jlProg.setText("Ready");
				}
				catch(Exception e){jlProg.setText("Ready");}
				
			}
		});
		
		this.pack();
	}	
		  
}




