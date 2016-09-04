
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class note {

	public void ui(){
		
		JFrame frame=new JFrame("Notepad");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		final JTextArea ta=new JTextArea();
		frame.add(ta);
		
		JMenuBar mb=new JMenuBar();
		frame.setJMenuBar(mb);
		
		
		
		JMenuItem save=new JMenuItem("Save");
		
		KeyStroke ss = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
		save.setAccelerator(ss);
		
		mb.add(save);	
                
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JFileChooser savefile=new JFileChooser();
				int savereturn=savefile.showSaveDialog(null);
				
				if (savereturn==0){
					
					try {
						
						File sfile=savefile.getSelectedFile();
						
						BufferedWriter outbuffer = new BufferedWriter(new FileWriter(sfile.getPath()));
						
                                                String updatedText = ta.getText().replaceAll("\n", System.lineSeparator());
						outbuffer.write(updatedText);
						
						outbuffer.close();
						
					} catch (Exception eex) {
                                            if(eex.getMessage()!=null)
                                              JOptionPane.showMessageDialog(null,eex.getMessage(),"Error while saving",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
		});
		
		
		JMenuItem open=new JMenuItem("Open");
		
		KeyStroke oo = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
		open.setAccelerator(oo);
		
		mb.add(open);
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JFileChooser op=new JFileChooser();		
                            	int isopend=op.showOpenDialog(null);

				if(isopend==0)
                                    ta.setText("");
			
				File fc=op.getSelectedFile(); 		
				 
				try {
			
				 Scanner scan = new Scanner(new FileReader(fc.getPath()));
				 while (scan.hasNext()) 
					ta.append(scan.nextLine() + "\n"); 
                                     } 
				
				catch (Exception ex) { 
				  
				if(ex.getMessage()!=null)
					JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		
		
				
		JMenuItem exit=new JMenuItem("Exit");
		
		KeyStroke ee = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK);
		exit.setAccelerator(ee);
		
		mb.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
											
				int ch = JOptionPane.showConfirmDialog (null, "Do you want to exit", "Exit", JOptionPane.YES_NO_OPTION);
				if(ch==0)
					System.exit(0);
			}
		});
		
		frame.add(ta);
		frame.setSize(800,600);
		frame.setVisible(true);
	
	}
	

	
	public static void main(String[] args) {
		
		note n=new note();
		n.ui();
	}


}
