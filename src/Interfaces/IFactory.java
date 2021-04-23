package Interfaces;
import javax.swing.JFrame;

public interface IFactory {
	public static IFactory instance = null;
	public String suportedExtension();
	public IBuilder createBuilder();
	public JFrame createSyntaxHighlither(String text);
    public static IFactory getInstance() {
		return null;
	}
}
