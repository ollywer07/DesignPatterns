import javax.swing.JFrame;

public class CFactory implements IFactory{

	@Override
	public String suportedExtension() {
		return "cpp";
	}
	@Override
	public IBuilder createBuilder() {
		return new CBuilder();
	}
	@Override
	public JFrame createSyntaxHighlither(String text) {
		return new CSyntaxHighlighter(text);
	}
}
