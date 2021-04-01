import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import javax.swing.JFrame;
import org.apache.commons.io.FilenameUtils;


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
		
		if(isSuported(factory,fileExtension)) {
			builder = factory.createBuilder();
			jframe = factory.createSyntaxHighlither(textFile);
			jframe.setVisible(true);
			builder.compile(fileInput);

		}
		else {
		//	System.out.println(fileExtension);
			
			System.out.println("Not Supported Extension");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		//String path = new File("src").getCanonicalPath() + "\\ArquivoHighlighter.java";
		String path = new File("src").getCanonicalPath() + "\\Main.cpp";
		
		int op;
		
		    do
		    {
		      File currentDir = new File("./Plugins");
		      String []plugins = currentDir.list();
		      int i;
		      URL[] jars = new URL[plugins.length];
		      for (i = 0; i < plugins.length; i++)
		      {
			System.out.println(i+1 + " - " + plugins[i].split("\\.")[0]);
			jars[i] = (new File("./Plugins/" + plugins[i])).toURL();
		      }
		      URLClassLoader ulc = new URLClassLoader(jars);
		      System.out.println(i+1 + " - Plugin refresh");
		      System.out.println("Escolha sua opção ou 0 para sair: ");
		      Scanner sc = new Scanner(System.in);
		      op = sc.nextInt();
		      if (op != 0 && op != i+1)
		      {
			String factoryName = plugins[op-1].split("\\.")[0];
			IFactory factory = (IFactory) Class.forName(factoryName, true, ulc).newInstance();
			start(path,factory);
		      }
		    } while (op != 0);
	}
}
