package actionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

public class Open implements ActionListener {
	private JFrame __frame;
	private myEditor.MyEditor textArea;
	private JTabbedPane tp;
	private JSplitPane sp;

	public Open(JFrame f,MyEditor t, JTree tree) {

		
	}

	public Open(JFrame f, MyEditor t,
			JTabbedPane tabbedPane, JSplitPane splitPane) {
		__frame = f;
		textArea = t;
		tp=tabbedPane;
		sp=splitPane;

	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(ProjectExplorer.getFile());
		if (jfc.showOpenDialog(__frame) == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				final JTree tree = new JTree(new FileSelectorModel(file.getParent()));
				ProjectExplorer p = new ProjectExplorer(tree,textArea,tp,sp);
				ProjectExplorer.setFile(file);
				 p.setProject();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String tmp = "";
			try {
				String tmp1;
				while ((tmp1 = bf.readLine()) != null) {
					tmp = tmp + tmp1 + "\n";
				}
				textArea.setText(tmp);
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			textArea.contentSavedTo(file);
		}

	}

}
