package actionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import myEditor.MyEditor;

public class New implements ActionListener {
	private RSyntaxTextArea textArea;

	public New(RSyntaxTextArea t) {
		textArea = t;
	}
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("");
		((MyEditor) textArea).contentSavedTo(null);
		((MyEditor) textArea).contentChanged();
	}

}
