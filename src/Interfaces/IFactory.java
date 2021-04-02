package Interfaces;
import javax.swing.JFrame;

public interface IFactory {
	
	public String suportedExtension();
	public IBuilder createBuilder();
	public JFrame createSyntaxHighlither(String text);
	
}
