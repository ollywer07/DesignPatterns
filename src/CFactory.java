import javax.swing.JFrame;

import Interfaces.IBuilder;
import Interfaces.IFactory;

public class CFactory  implements IFactory{
	public static IFactory instance = null;
	
	private CFactory(){}

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

	public static IFactory getInstance() {

		if(instance == null) {
			instance = new CFactory();
		}
		return instance;
	}
}
