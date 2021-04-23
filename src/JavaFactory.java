import Interfaces.IBuilder;
import Interfaces.IFactory;

public class JavaFactory implements IFactory {
	public static IFactory instance = null;
	
	private JavaFactory(){}
	
	public static IFactory getInstance() {
	
		if(instance == null) {
			instance = new JavaFactory();
		}

		return instance;
	}

	public String suportedExtension() {
		return "java";
	}

	public IBuilder createBuilder() {
		return new JavaBuilder();
	}

	public JavaSyntaxHighlighter createSyntaxHighlither(String text) {
		return new JavaSyntaxHighlighter(text);
	}
}
