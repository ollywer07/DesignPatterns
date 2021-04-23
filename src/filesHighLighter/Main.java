import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import javax.swing.JFrame;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.*;

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
	
	public static void appExec() throws Exception {
		
				 JSONObject jsonObject;
				 JSONParser parser = new JSONParser();
				 int i,op;
				 
				 do
				    {
					 File currentDir = new File("./Plugins");
				     String []plugins = currentDir.list();
				     
				      URL[] jars = new URL[plugins.length];
				      for (i = 0; i < plugins.length; i++){
				    	  System.out.println(i+1 + " - " + plugins[i].split("\\.")[0]);
				    	  jars[i] = (new File("./Plugins/" + plugins[i])).toURL();
				      }
				      URLClassLoader ulc = new URLClassLoader(jars);
				      System.out.println(i+1 + " - Plugin refresh");
				      System.out.println("Escolha sua opção ou 0 para sair: ");
				      Scanner sc = new Scanner(System.in);
				      op = sc.nextInt();
				      
				      IFactory factory;


				      if (op != 0 && op != i+1) {
				    	  jsonObject = (JSONObject) parser.parse(new FileReader(
									 new File("src").getCanonicalPath() +"\\caminho.json"));

						  String path = (String) jsonObject.get("caminhoArquivo");
						  String fileExtension = FilenameUtils.getExtension(path);
							 
					      String factoryName = plugins[op-1].split("\\.")[0];
					      Class metaSingleton = Class.forName(factoryName);
					      Method getInstance = metaSingleton.getDeclaredMethod("getInstance");
					 	  factory = (IFactory) getInstance.invoke(factoryName + ".getInstance()");
					 	  System.out.println("Instancia: " + factory);
					 	 // System.out.println(factory + ".getInstance()");
//					 	  IFactory factory2 = JavaFactory.getInstance();
//					 	  System.out.println(factory + "  " + factory2);
					 	  	//System.out.println(factory.instance);
					  	  if(isSuported(factory, fileExtension)) {
					  		  start(path, factory);
					  	  }
					  	  else {
					  		  System.out.println("A Fábrica " + factory.toString() + " não suporta essa extensão" );
					  	  } 
				      }
				    } while (op != 0);
	}
	
	public static void main(String[] args) throws Exception {
		appExec();	
		
	}
}
