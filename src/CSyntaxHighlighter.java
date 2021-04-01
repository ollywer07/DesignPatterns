import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class CSyntaxHighlighter extends JFrame{
	public CSyntaxHighlighter(String text) {
		
		JPanel cp = new JPanel(new BorderLayout());
	      
	      RSyntaxTextArea textArea = new RSyntaxTextArea(40, 120);
	      textArea.setText(text);
	      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
	      textArea.setCodeFoldingEnabled(true);
	      RTextScrollPane sp = new RTextScrollPane(textArea);
	      cp.add(sp);
	      setContentPane(cp);
	      setTitle("Text Editor C++");
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	      pack();
	      setLocationRelativeTo(null);
	}
}
