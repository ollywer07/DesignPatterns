import java.io.File;
import java.io.IOException;

import Interfaces.IBuilder;

public class CBuilder implements IBuilder{

	@Override
	public void compile(File file) {
		String path;
		Runtime rt = Runtime.getRuntime();
		path = file.getAbsolutePath();
		Process pr = null;
		
		try {
			System.out.println("Compilando arquivo .cpp \n");
			//System.out.println(rt.exec("gcc -o arquivo " + path));
			pr = rt.exec("gcc -o arquivo " + path);
			System.out.println(pr);
		} catch (IOException e) {
			System.out.println("Erro ao compilar o arquivo cpp");
			//e.printStackTrace();
		}
	}
}
