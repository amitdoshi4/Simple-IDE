package uiComponents.dialogBoxes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actionControllers.*;

public class JavaSetArgsDialogBox extends JDialog {
	private JDialog dialog;
	public JavaSetArgsDialogBox() {
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
		
		this.getContentPane().setLayout(new GridLayout(0,1));

		JLabel label1 = new JLabel("MainClass");
		this.getContentPane().add(label1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		this.getContentPane().add(panel2);
		final JTextField jta1 = new JTextField(30);
		panel2.add(jta1);
		JLabel label = new JLabel("Set the run time arguments.");
		this.getContentPane().add(label);
				
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.getContentPane().add(panel);
		
		final JTextField jta = new JTextField(30);
		panel.add(jta);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		this.getContentPane().add(panel1);
		
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
				Run.args=jta.getText();
				Run.fileName = jta1.getText();
				setVisible(false);
			}
		});
		panel1.add(jbOk);
		this.pack();
		
		
		
		
	}
}
