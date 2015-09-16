package actionControllers;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import uiComponents.FileNode;
import uiComponents.FileSelectorModel;
import myEditor.MyEditor;

public class Save extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -965327735052681034L;
	private JFrame __frame;
	private myEditor.MyEditor textArea;
	private JTabbedPane tp;
	private JSplitPane sp;
	public Save(JFrame f,  MyEditor t,
			JTabbedPane tabbedPane, JSplitPane splitPane) {
		__frame = f;
		textArea = t;
		tp=tabbedPane;
		sp=splitPane;

	}
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(ProjectExplorer.getFile());
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
		}
		if(file!=null){
		final JTree tree = new JTree(new FileSelectorModel(file.getParent()));
		ProjectExplorer p = new ProjectExplorer(tree,textArea,tp,sp);
		ProjectExplorer.setFile(file);
		 p.setProject();
		}
		textArea.contentChanged();

		
	}
	

}
