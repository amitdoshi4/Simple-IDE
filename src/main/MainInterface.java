package main;

/**
 * @author Amit
 */
import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import actionControllers.ProjectExplorer;
import base.CompilerOption;
import uiComponents.FileSelectorModel;
import uiComponents.dialogBoxes.AboutDialogBox;
import uiComponents.dialogBoxes.JavaPathDialogBox;
import uiComponents.dialogBoxes.JavaSetArgsDialogBox;
import uiComponents.dialogBoxes.WorkspaceDialogBox;

import javax.swing.Action;

import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainInterface {
	private JFrame frmSimpleJavaIde;
	private JTextField textField;
	private JButton btnSave;
	private JButton btnRun;
	private JButton btnStop;
	public static final int SAVE_BUTTON = 1;
	public static final int RUN_BUTTON = 2;
	public static final int STOP_BUTTON = 3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){ 
		MainInterface window = new MainInterface();
		window.frmSimpleJavaIde.setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public MainInterface() {
		initialize();
	}
	public void disableButton(int buttonType) {
		switch(buttonType) {
		case MainInterface.SAVE_BUTTON:
			btnSave.setEnabled(false);
			break;
		case MainInterface.RUN_BUTTON:
			btnRun.setEnabled(false);
			btnStop.setEnabled(true);
			break;
		case MainInterface.STOP_BUTTON:
			btnStop.setEnabled(false);
			btnRun.setEnabled(true);
			break;
		}
	}
	public void enableButton(int buttonType) {
		switch(buttonType) {
		case MainInterface.SAVE_BUTTON:
			btnSave.setEnabled(true);
			break;
		case MainInterface.RUN_BUTTON:
			btnRun.setEnabled(true);
			btnStop.setEnabled(false);
			break;
		case MainInterface.STOP_BUTTON:
			btnStop.setEnabled(true);
			btnRun.setEnabled(false);
			break;
		}
	}
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
frmSimpleJavaIde = new JFrame();
		
		frmSimpleJavaIde.setTitle("Simple Java IDE");
		// frmSimpleJavaIde.setBounds(100, 100, 524, 319);
		frmSimpleJavaIde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - frmSimpleJavaIde.getWidth()) / 2 - 450;
		final int y = (screenSize.height - frmSimpleJavaIde.getHeight()) / 2 - 350;
		frmSimpleJavaIde.setLocation(x, y);
		JMenuBar menuBar = new JMenuBar();
		frmSimpleJavaIde.setJMenuBar(menuBar);

		
		frmSimpleJavaIde.getContentPane().setLayout(new BorderLayout(0, 0));

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setOneTouchExpandable(true);
		frmSimpleJavaIde.getContentPane().add(splitPane, BorderLayout.CENTER);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//JList jl = new JList(sbl);
		final myEditor.MyEditor textArea = new myEditor.MyEditor();
       

		tabbedPane.addTab("Project Explorer", new JScrollPane());
		tabbedPane.setMinimumSize(new Dimension(200, 0));
		tabbedPane.setPreferredSize(new Dimension(200, 0));
		splitPane.setLeftComponent(tabbedPane);

		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setContinuousLayout(true);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, BorderLayout.CENTER);

		
		 RTextScrollPane scrollPane = new RTextScrollPane(textArea);
		splitPane_1.setLeftComponent(scrollPane);
	
		scrollPane.setPreferredSize(new Dimension(700, 400));
		scrollPane.setViewportView(textArea);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setPreferredSize(new Dimension(700, 150));
		//tabbedPane_1.setMinimumSize(new Dimension(700, 150));
		
		final JTextArea compileOutput = new JTextArea();
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(compileOutput);
		tabbedPane_1.addTab("Output",jsp);
		compileOutput.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent arg0) {
				try {
				JTextArea co = (JTextArea)arg0.getSource();
				if(co.getText().length() != arg0.getDot())
					co.setCaretPosition(co.getText().length());
				}
				catch(Exception e) {
					
				}
			}
		});

		splitPane_1.setRightComponent(tabbedPane_1);

		JPanel panel_1 = new JPanel();
		frmSimpleJavaIde.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 40));
		panel_1.add(rigidArea_3);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar);

		JButton btnNew = new JButton("New");
		ActionListener nal = new actionControllers.New(textArea);
		btnNew.addActionListener(nal);
		toolBar.add(btnNew);

		JButton btnOpen = new JButton("Open");
		ActionListener oal = new actionControllers.Open(frmSimpleJavaIde,textArea,tabbedPane,splitPane);
		btnOpen.addActionListener(oal);
		toolBar.add(btnOpen);

		btnSave = new JButton("Save");
		textArea.initialize(this);
		Action sal = new actionControllers.Save(frmSimpleJavaIde,textArea, tabbedPane, splitPane); 
		btnSave.addActionListener(sal);
		toolBar.add(btnSave);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(10, 40));
		toolBar.add(rigidArea_2);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel_1.add(toolBar_1);
		JButton btnBuild = new JButton("BuildConfig");
		btnBuild.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new JavaPathDialogBox().setVisible(true);
				
			}
		});
		toolBar_1.add(btnBuild);
		JButton btnRunConfig = new JButton("RunConfig");
		btnRunConfig.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new JavaSetArgsDialogBox().setVisible(true);
				
			}
			
		});
		toolBar_1.add(btnRunConfig);
		JButton btnCompile = new JButton("Compile");
		ActionListener cal = new actionControllers.Compile(frmSimpleJavaIde,textArea,compileOutput); 
		btnCompile.addActionListener(cal);
		toolBar_1.add(btnCompile);

		btnRun = new JButton("Run");
		ActionListener execute = new actionControllers.Run(this,textArea,compileOutput);
		btnRun.addActionListener(execute);
		toolBar_1.add(btnRun);
		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.addActionListener(execute);
		toolBar_1.add(btnStop);
		Component rigidArea = Box.createRigidArea(new Dimension(10, 40));
		toolBar_1.add(rigidArea);

		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		panel_1.add(toolBar_2);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		toolBar_2.add(panel_2);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 40));
		toolBar_2.add(rigidArea_1);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(nal);
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(oal);
		mnFile.add(mntmOpen);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(sal);
		mnFile.add(mntmSave);

		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(new actionControllers.SaveAs(frmSimpleJavaIde,textArea));
		mnFile.add(mntmSaveAs);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				frmSimpleJavaIde.setVisible(false);
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cut");
		mnEdit.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(textArea.getActionForKeyStroke(KeyStroke.getKeyStroke("control X")));

		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		mntmCopy.addActionListener(textArea.getActionForKeyStroke(KeyStroke.getKeyStroke("control C")));

		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		mntmPaste.addActionListener(textArea.getActionForKeyStroke(KeyStroke.getKeyStroke("control V")));

		JSeparator separator_3 = new JSeparator();
		mnEdit.add(separator_3);

		


		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		JMenuItem mntmMedium = new JMenuItem("Medium");
		mntmMedium.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				textArea.setFont(new Font(null,Font.PLAIN,14));
				
			}
		});
		JMenuItem mntmSmall = new JMenuItem("Small");
		mntmSmall.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				textArea.setFont(new Font(null,Font.PLAIN,11));
				
			}
		});
		JMenuItem mntmLarge = new JMenuItem("Large");
		mntmLarge.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				textArea.setFont(new Font(null,Font.PLAIN,24));
				
			}
		});
		mnView.add(mntmMedium);
		mnView.add(mntmSmall);
		mnView.add(mntmLarge);
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AboutDialogBox().setVisible(true);
				
			}
		});
		frmSimpleJavaIde.pack();
		
		try{
			FileInputStream fr = new FileInputStream("workspace.plain");
			BufferedReader br = new BufferedReader(new InputStreamReader(fr));
			ProjectExplorer.setFile(new File(br.readLine()));
			final JTree tree = new JTree(new FileSelectorModel(ProjectExplorer.getFile().getAbsolutePath()));
			ProjectExplorer p = new ProjectExplorer(tree,textArea,tabbedPane,splitPane);
			p.setProject();
		}
		 catch (FileNotFoundException e1) {
			
				WorkspaceDialogBox w = new WorkspaceDialogBox(textArea,tabbedPane,splitPane);
				w.setVisible(true);
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file = new File("pathsetting.plain");
		File file2 = new File("classpath.plain");
		try {
			FileInputStream fr = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fr));
			FileInputStream fr2 = new FileInputStream(file2);
			BufferedReader br2 = new BufferedReader(new InputStreamReader(fr2));
			CompilerOption.setCompilerPath(br.readLine());
			CompilerOption.classPath=br2.readLine();
		} catch (FileNotFoundException e1) {
				JavaPathDialogBox jpdb = new JavaPathDialogBox();
				jpdb.setVisible(true);
				jpdb.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent e) {
						File file = new File("pathsetting.plain");
						File file2 = new File("classpath.plain");
						try {
							FileInputStream fr = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(fr));
							FileInputStream fr2 = new FileInputStream(file2);
							BufferedReader br2 = new BufferedReader(new InputStreamReader(fr2));
							CompilerOption.setCompilerPath(br.readLine());
							CompilerOption.classPath=br2.readLine();
						} catch (FileNotFoundException e2) {
							//e2.printStackTrace();
							
						} catch (IOException e1) {
							//e1.printStackTrace();
						}
					}
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
