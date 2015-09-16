package actionControllers;

/**
 * @author Amit
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import base.CompilerOption;

import myEditor.MyEditor;

public final class Compile implements ActionListener {
	private JFrame __frame;
	private myEditor.MyEditor textArea;
	private JTextArea outputArea;
	public Compile(JFrame f,MyEditor t, JTextArea o) {
		__frame = f;
		textArea = t;
		outputArea = o;
		outputArea.setEditable(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		File file = textArea.getSavedFile();
		if (file == null) {
			if (jfc.showSaveDialog(__frame) == JFileChooser.APPROVE_OPTION) {
				file = jfc.getSelectedFile();
				textArea.contentSavedTo(file);
			}
		}
		if (file != null) {
			OutputStream os = null;
			try {
				os = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			PrintStream ps = new PrintStream(os);
			ps.print(textArea.getText());
			try {
				os.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			textArea.ContentSaved();
			outputArea.setText("");
			try {
			    String line;
			    System.out.println(CompilerOption.getCompilerPath());
			    File temp = file;
			    int count = 0;
		while(!temp.getParentFile().getName().equals("src")){
			count++;
			temp = temp.getParentFile();
			if(count>10)
				break;
		}
		System.out.println(temp.getAbsolutePath());
		String path = temp.getParent();
		if(CompilerOption.classPath!=null)
			path = path+CompilerOption.classPath;
			    Process p = new ProcessBuilder(CompilerOption.getCompilerPath(),"-classpath",path,file.getAbsolutePath()).start();
			    BufferedReader input =
			    new BufferedReader
			    (new InputStreamReader(p.getErrorStream()));
			    boolean flag=false;
			    while ((line = input.readLine()) != null) {
			    	flag = true;
			        outputArea.append(line +"\n");
			    }
			    if(!flag) {
			    	outputArea.append("Compilation Successful\n");
			    }
			    else
			    	textArea.contentChanged();
			    input.close();
			} catch (Exception err) {
			    err.printStackTrace();
			}
		}
	}
}
