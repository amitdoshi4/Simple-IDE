package myEditor;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import main.MainInterface;

public class MyEditor extends RSyntaxTextArea {
	private static final long serialVersionUID = -464515284669232442L;
	private boolean __contentChanged = false;
	private boolean __textColored = true;
	private File file = null;
	private MainInterface __mainInterface;
	private boolean __initialized = false;

	public MyEditor() {
	}

	public MyEditor(MainInterface m) {
		initialize(m);
	}

	public void initialize(MainInterface m) {
		__mainInterface = m;
		__initialized = true;
		//this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		//this.addKeyListener(new EditorKeyEventListener(this));
		//new Thread(new ColorifyOnTime(this)).start();
		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAntiAliasingEnabled(true);
	}

	public boolean isContentChanged() {
		return __contentChanged;
	}
	public boolean istextColored() {
		return __textColored;
	}
	public void contentChanged() {
		__contentChanged = true;
		__textColored = false;
		__mainInterface.enableButton(MainInterface.SAVE_BUTTON);
	}


	public void ContentSaved() {
		__contentChanged = false;
		//__mainInterface.disableButton(MainInterface.SAVE_BUTTON);

	}

	public void contentSavedTo(File f) {
		file = f;
	}

	public File getSavedFile() {
		return file;
	}

	public boolean isInitialised() {
		return __initialized;
	}



	}



