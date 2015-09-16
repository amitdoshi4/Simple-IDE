package uiComponents.dialogBoxes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import uiComponents.FileSelectorModel;
import actionControllers.ProjectExplorer;
import myEditor.MyEditor;

public class WorkspaceDialogBox extends JDialog {
	private File file=null;
	private JDialog dialog;
    final MyEditor textArea;
	private JTabbedPane tp;
	private JSplitPane sp;

	public WorkspaceDialogBox(MyEditor textArea,
			JTabbedPane tabbedPane, JSplitPane splitPane) {
		this.textArea = textArea;
		this.tp = tabbedPane;
		this.sp = splitPane;
		// TODO Auto-generated constructor stub
		initialize();
	}
	private void initialize() {
		dialog = this;
		//this.setPreferredSize(new Dimension(300,100));
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2 - 200;
		final int y = (screenSize.height - this.getHeight()) / 2 - 100;
		this.setLocation(x, y);
		
		this.getContentPane().setLayout(new BorderLayout());
		JLabel label = new JLabel("Select path to workspace.");
		this.getContentPane().add(label,BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
		final JTextField jta = new JTextField(30);
		panel.add(jta);
		
		JButton jbBrowse = new JButton("Browse");
		jbBrowse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dialog.setAlwaysOnTop(false);
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if(jfc.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
					file = jfc.getSelectedFile();
					jta.setText(file.getAbsolutePath());
					
				}
				
			}
		});
		panel.add(jbBrowse);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		this.getContentPane().add(panel1,BorderLayout.SOUTH);
		
		JButton jbCancel = new JButton("Cancel");
		jbCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		panel1.add(jbCancel);
		JButton jbOk = new JButton("Ok");
		jbOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getActionCommand() == "Ok") {
					if(file == null)
						return;
					File file1 = new File("workspace.plain");
					final JTree tree = new JTree(new FileSelectorModel(file.getAbsolutePath()));
					ProjectExplorer p = new ProjectExplorer(tree,textArea,tp,sp);
					ProjectExplorer.setFile(file);
					 p.setProject();
					
					FileWriter fw;
					try {
						fw = new FileWriter(file1);
						fw.write(file.getAbsolutePath());
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				setVisible(false);
			}
		});
		panel1.add(jbOk);
		this.pack();
		
	}
}
