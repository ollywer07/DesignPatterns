import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import javax.swing.JFrame;
import org.apache.commons.io.FilenameUtils;

import Interfaces.IBuilder;
import Interfaces.IFactory;


public class Main {
	
	//Obtém o texto do arquivo passado
	private static String getContentFile(String path) throws FileNotFoundException {
		File fileInput = new File(path);
		String line = "";
		Scanner scanner = new Scanner(fileInput);
	      while (scanner.hasNextLine()) {
	           line = line + '\n' + scanner.nextLine();
	      }
	      return line;
	}
	//Verifica se a extensão do arquivo é compatível com o que a fábrica suporta
	private static boolean isSuported(IFactory factory, String extension) {
		if(factory.suportedExtension().equals(extension)) {
			return true;
		}
		return false;	
	}
	
	
	private static void start(String path, IFactory iFactory) throws IOException {
		File fileInput;
		fileInput = new File(path);
		String textFile = getContentFile(path);
		JFrame jframe = null;
		IBuilder builder = null;
		IFactory factory = iFactory;
		
		String fileExtension = FilenameUtils.getExtension(path);
			builder = factory.createBuilder();
			jframe = factory.createSyntaxHighlither(textFile);
			jframe.setVisible(true);
			builder.compile(fileInput);

	}
	
	public static void AppExec() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
				String path = new File("src").getCanonicalPath() + "\\Main.cpp";
				String fileExtension = FilenameUtils.getExtension(path);
				int i;
				 File currentDir = new File("./Plugins");
			      String []plugins = currentDir.list();
			     
			      URL[] jars = new URL[plugins.length];
			      for (i = 0; i < plugins.length; i++){
			    	  jars[i] = (new File("./Plugins/" + plugins[i])).toURL();
			      }
			      URLClassLoader ulc = new URLClassLoader(jars);
			      for (i = 0; i < plugins.length; i++){
			    	  String factoryName = plugins[i].split("\\.")[0];
			    	  IFactory factory = (IFactory) Class.forName(factoryName, true, ulc).newInstance();
			  		  if(isSuported(factory, fileExtension)) {
			  			  start(path, factory);
			  			  return;
			  		  }
			      }
				 System.out.println("Não há suporte a essa extensao");
	}
	
	public static void main(String[] args) throws Exception {
		AppExec();	
	}
}
