package actionControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import myEditor.MyEditor;
import uiComponents.FileNode;
import uiComponents.FileSelectorModel;

public class ProjectExplorer {
	final JTree tree;
    final MyEditor textArea;
	private JTabbedPane tp;
	private JSplitPane sp;
	static File file;
	
	public static File getFile() {
		return file;
	}



	public static void setFile(File f) {
	file = f;
	}



	public ProjectExplorer(JTree tree, MyEditor textArea, JTabbedPane tp,
			JSplitPane sp) {
		super();
		this.tree = tree;
		this.textArea = textArea;
		this.tp = tp;
		this.sp = sp;
	}


	
	public void setProject(){
		 tree.addTreeSelectionListener(new TreeSelectionListener() {

	            public void valueChanged(TreeSelectionEvent e) {
	                FileNode selectedNode = (FileNode) tree.getLastSelectedPathComponent();
	                
	                if (selectedNode!=null&&selectedNode.isFile()) {
	            		if (textArea.getSavedFile() != null) {
	            			OutputStream os = null;
	            			try {
	            				os = new FileOutputStream(textArea.getSavedFile());
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
	                	textArea.contentSavedTo(selectedNode);
	                    textArea.setText(null);
	                    try {
	                        BufferedReader br = new BufferedReader(new FileReader(selectedNode.getAbsolutePath()));
	                        String line = "";
	                        while ((line = br.readLine()) != null) {
	                        	textArea.append(line);
	                        	textArea.append(System.getProperty("line.separator"));
	                        }
	                    } catch (Exception exc) {
	                        exc.printStackTrace();
	                    }
	                }
	            }
	        });
		tp.remove(0);
		tp.addTab("Project Explorer", new JScrollPane(tree));
		sp.setLeftComponent(tp);
		
	}
}
