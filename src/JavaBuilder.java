import java.io.File;
import java.io.IOException;

public class JavaBuilder implements IBuilder {

	@Override
	public void compile(File file) {
		String path;
		Runtime rt = Runtime.getRuntime();
		path = file.getAbsolutePath();
		Process pr=null;
		
		try {
			System.out.println("Compilando arquivo .java");
			//System.out.println(rt.exec("javac " + path));
			pr = rt.exec("javac " + path);
			System.out.println(pr);
		} catch (IOException e) {
			System.out.println("Erro ao compilar o arquivo java");
			//e.printStackTrace();
		}
	}
}
