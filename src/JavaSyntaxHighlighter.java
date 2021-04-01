import javax.swing.*;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Scanner;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class JavaSyntaxHighlighter extends JFrame {

   public JavaSyntaxHighlighter(String text){

      JPanel tela = new JPanel(new BorderLayout());
      
      RSyntaxTextArea textArea = new RSyntaxTextArea(40, 120);
      textArea.setText(text);
      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane scrollPane = new RTextScrollPane(textArea);
      tela.add(scrollPane);
      setContentPane(tela);
      setTitle("Text Editor Java");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);
   }
}