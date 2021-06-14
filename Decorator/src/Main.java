
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;


import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import java.awt.Font;


public class Main {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private static DefaultListModel listModel = new DefaultListModel<>();
	
	public static void main(String[] args) throws Exception {
		
		getPlugins();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public static List getPlugins() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List lista = new ArrayList<>();
		
		File currentDir = new File("./plugins");
	     String []plugins = currentDir.list();
	    
	     
	      URL[] jars = new URL[plugins.length];
	      for (int i = 0; i < plugins.length; i++){
	    	  lista.add(plugins[i].split("\\.")[0]);
	    	  jars[i] = (new File("./plugins/" + plugins[i])).toURL();
	      }
	      URLClassLoader ulc = new URLClassLoader(jars);
	      
	      return lista;
	}
	
	public static List getList() {
		List<String> lista = new ArrayList<String>();
		if(!listModel.isEmpty()) {
			for(int i = 0; i< listModel.size(); i++) {
				lista.add((String) listModel.get(i).toString());
			}
			return lista;
		}
		return null;
	}	
	
	/* Método preparar incompleto */
	public static void preparar() throws Exception {
		List lista = getList();
		PizzaSimples pizza = new PizzaSimples();
		List<PizzaDecorator> listDecorator = new ArrayList<PizzaDecorator>();
		PizzaDecorator  decorator = null;
		if(!lista.isEmpty()) {
			Class<?> cl = Class.forName((String) lista.get(0));
			decorator = (PizzaDecorator) cl.newInstance();
			int i = 0;
			for(i= 0;i<lista.size();i++) {
				 cl = Class.forName((String) lista.get(i));
				decorator.setDecorated((PizzaDecorator) cl.newInstance());

			}

			PizzaDecorator d1  = new AzeitonaDecorator();
			PizzaDecorator d2 = new PepperoniDecorator();
			d1.setDecorated(d2);
			d2.setDecorated(pizza);
			
			
	        d1.preparar();
			
	
		//	System.out.println(lista.toString());
		}
		
	}

	public Main() throws Exception {
		initialize();
	}


	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelDecorator = new JLabel("DECORATOR");
		labelDecorator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelDecorator.setHorizontalAlignment(SwingConstants.CENTER);
		labelDecorator.setBounds(162, 24, 194, 14);
		frame.getContentPane().add(labelDecorator);
		
		DefaultListModel listModel = new DefaultListModel();

		listModel.addAll(getPlugins());
		
		JList list = new JList(listModel);
		
		list.setBounds(102, 100, 135, 143);
		
		frame.getContentPane().add(list);
		
		list.setSelectedIndex(0);
		
		JList list_1 = new JList();
		list_1.setBounds(303, 100, 135, 143);
		frame.getContentPane().add(list_1);
	
		DefaultListModel lista = new DefaultListModel();
		
		JToggleButton buttonInserir = new JToggleButton(">");
		buttonInserir.setBounds(247, 138, 46, 23);
		frame.getContentPane().add(buttonInserir);
		
		
		buttonInserir.addActionListener(
				
				
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
					System.out.println(list.getSelectedValue());
					lista.addElement(list.getSelectedValue() );
					System.out.println(lista.toString());
	
					list_1.setModel(lista);
					Main.listModel = lista;
					list_1.setSelectedIndex(lista.getSize()-1);
				}
		});
		JToggleButton buttonRetirar = new JToggleButton("<");
		buttonRetirar.setBounds(247, 172, 46, 23);
		frame.getContentPane().add(buttonRetirar);
		
		buttonRetirar.addActionListener(
								
				new ActionListener() {
					int index = list_1.getSelectedIndex();

				public void actionPerformed(ActionEvent e) {
					
					if (lista.getSize() == 0) {
						JOptionPane.getDesktopPaneForComponent(buttonRetirar);
						JOptionPane.showMessageDialog(frame, "Vazio");
					}
					if (! list_1.isSelectionEmpty()) {
						list_1.setSelectedIndex(lista.getSize());
					}
					if(list_1.isSelectionEmpty() && lista.getSize() > 0) {
						lista.remove(list_1.getSelectedIndex());
						list_1.setSelectedIndex(lista.getSize()-1);
					}
					if(lista.getSize() > 0 ) {
						lista.remove(list_1.getSelectedIndex());
						list_1.setSelectedIndex(lista.getSize()-1);
					}
				}
		});

		JLabel labelSabores = new JLabel("SABORES DA PIZZA");
		labelSabores.setHorizontalAlignment(SwingConstants.CENTER);
		labelSabores.setBounds(71, 75, 184, 14);
		frame.getContentPane().add(labelSabores);
		
		JToggleButton tglbtnUp = new JToggleButton("Up");
		tglbtnUp.setBounds(465, 116, 76, 23);
		frame.getContentPane().add(tglbtnUp);
		
		tglbtnUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String itemAnterior= "";
				String selected = "";
				int index;
				if(list_1.getSelectedIndex() != 0) {
					
					itemAnterior = (String) lista.getElementAt(list_1.getSelectedIndex() -1);
					 selected = (String) lista.getElementAt(list_1.getSelectedIndex());
					 
					 index = list_1.getSelectedIndex();
					 lista.setElementAt(list_1.getSelectedValue(), list_1.getSelectedIndex() -1);
					 lista.setElementAt(itemAnterior, list_1.getSelectedIndex());
					 list_1.setSelectedIndex(list_1.getSelectedIndex() -1);
					}
			}
		});
		
		JToggleButton tglbtnDown = new JToggleButton("Down");
		tglbtnDown.setBounds(465, 150, 76, 23);
		frame.getContentPane().add(tglbtnDown);
		tglbtnDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String itemPosterior= "";
				String selected = "";
				int index;
				
				if(list_1.getSelectedIndex() < list_1.getLastVisibleIndex()) {
					
					itemPosterior = (String) lista.getElementAt(list_1.getSelectedIndex() +1);
					 selected = (String) lista.getElementAt(list_1.getSelectedIndex());
					 index = list_1.getSelectedIndex();
					 lista.setElementAt(list_1.getSelectedValue(), list_1.getSelectedIndex() +1);
					 lista.setElementAt(itemPosterior, list_1.getSelectedIndex());
					 list_1.setSelectedIndex(list_1.getSelectedIndex() +1);
					}
			}
		});
		
		JButton buttonPreparar = new JButton("Preparar");
		buttonPreparar.setBounds(224, 284, 89, 23);
		frame.getContentPane().add(buttonPreparar);
		
		JLabel labelEscolhidos = new JLabel("ESCOLHIDOS");
		labelEscolhidos.setHorizontalAlignment(SwingConstants.CENTER);
		labelEscolhidos.setBounds(274, 75, 184, 14);
		frame.getContentPane().add(labelEscolhidos);
		
		buttonPreparar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Main.listModel.isEmpty()) {
						return;
					}
					preparar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		frame.setResizable(false);
	}
}
