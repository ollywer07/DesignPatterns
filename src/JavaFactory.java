import Interfaces.IBuilder;
import Interfaces.IFactory;

public class JavaFactory implements IFactory {

	@Override
	public String suportedExtension() {
		return "java";
	}

	@Override
	public IBuilder createBuilder() {
		return new JavaBuilder();
	}

	@Override
	public JavaSyntaxHighlighter createSyntaxHighlither(String text) {
		return new JavaSyntaxHighlighter(text);
	}
}
