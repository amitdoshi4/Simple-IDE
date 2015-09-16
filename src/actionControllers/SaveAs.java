package actionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import myEditor.MyEditor;


public class SaveAs implements ActionListener{
	private JFrame __frame;
	private myEditor.MyEditor textArea;
	public SaveAs(JFrame f,MyEditor t) {
		__frame = f;
		textArea = t;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(ProjectExplorer.getFile());
		File file=null;
		if (jfc.showSaveDialog(__frame) == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
			textArea.contentSavedTo(file);
			
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

		}
		
		
	}
	
}
