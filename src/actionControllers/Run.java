package actionControllers;

import java.awt.event.ActionEvent;

import main.MainInterface;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JTextArea;

import base.CompilerOption;
import myEditor.MyEditor;

public class Run implements ActionListener,Runnable {
	private myEditor.MyEditor textArea;
	private JTextArea outputArea;
	private MainInterface __mainInterface;
	private String __temp="";
	private BufferedWriter bw;
	private Process p=null;
	public static String args = null;
	public static String fileName = null;

	public static String getFileName() {
		return fileName;
	}
	public static void setFileName(String fileName) {
		Run.fileName = fileName;
	}
	public Run(MainInterface m,MyEditor t, JTextArea o) {
		textArea = t;
		outputArea = o;
		__mainInterface = m;
		args="";
	}
	public void actionPerformed(ActionEvent arg0) {
		new Thread(this).start();
	}
	public void run() {
		File file = textArea.getSavedFile();
		if(p!=null) {
			p.destroy();
			p=null;
			return;
		}
		if (file != null) {
			__mainInterface.disableButton(MainInterface.RUN_BUTTON);
			try {
				String line, errorLine = null;
				ArrayList<String> param = new ArrayList<String>();
				outputArea.setText("");
				String [] args1 = null; 
				args1 = args.split(" ");
			    File temp = file;
			    String s1 = "";
		while(!temp.getParentFile().getName().equals("src")){
			s1 = s1+temp.getParentFile().getName()+"/";
			temp = temp.getParentFile();
		}
				System.out.println(CompilerOption.getInterpretorPath());
				param.add(CompilerOption.getInterpretorPath());
				param.add("-classpath");
				String path = temp.getParent();
				if(CompilerOption.classPath!=null)
					path = path+CompilerOption.classPath;
				param.add(path);
				String fileTemp =null;
				System.out.println(s1);
				if(fileName==null)
					fileTemp=file.getName().split("\\.")[0];
				else
					fileTemp=fileName;
				param.add(s1+fileTemp);
				for(String s: args1)
				param.add(s);
				p = new ProcessBuilder(param).start();
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				BufferedReader error = new BufferedReader(
						new InputStreamReader(p.getErrorStream()));
				OutputStream ostream = p.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(ostream));

				while ((line = input.readLine()) != null
						|| (errorLine = error.readLine()) != null) {
					if (line != null)
						outputArea.append(line + "\n");
					if (errorLine != null)
						outputArea.append(errorLine + "\n");
				}
				input.close();
			} catch (Exception err) {
				//err.printStackTrace();
			}
			finally {
				__mainInterface.enableButton(MainInterface.RUN_BUTTON);
			}
		}
	}

}
