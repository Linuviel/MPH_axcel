package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import plugin.IPlugin;
import memento.CareTaker;
import memento.Memento;
import memento.Originator;


public class MainWindow extends JFrame implements ActionListener {
	
	private HashMap<String, Object> plugindt_;
	private static final long 	serialVersionUID = -314171089120047242L;
	private JButton 			button1;
	private JButton 			button2;
	private JButton 			button3;
	private JButton 			button4;
	private JPanel 				mainPanel_;
	private JFileChooser 		filechooser;
	private ImagePanel			currentfile;
	private ImagePanel			currentimg_;
	private JScrollPane			scroll;
	private ProjectUtil			currentproject;
	private int					currentmodindex_;
	private String 				givenName_;
	
	public MainWindow()
	{
		super();
		mainPanel_ = new JPanel(new BorderLayout());
		currentmodindex_ = 0;
		plugindt_ = new HashMap<String, Object>();
		scroll = new JScrollPane();
		scroll.getVerticalScrollBar().setUnitIncrement(3);
		scroll.getHorizontalScrollBar().setUnitIncrement(3);
		build();
		createMenu();
		this.setVisible(true);
	}
	
	private void build()
	{
		setTitle("MyPhotoshop"); // titre fenetre
		setSize(1280,760); // taille fenetre
		setLocationRelativeTo(null); //centrage fenetre
		setResizable(true); //redimensionnement de la fenêtre interdit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fermeture clic sur croix
	}
	
	public void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		JMenu menu2 = new JMenu("Edit");
		JMenu menu3 = new JMenu("Filters");
		
		JMenuItem menuItem1 = new JMenuItem("Open", KeyEvent.VK_0);
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
		menuItem1.addActionListener(this);
		menu1.add(menuItem1);
		
		JMenuItem openproject = new JMenuItem("Open Project",KeyEvent.VK_R);
		openproject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		openproject.addActionListener(this);
		menu1.add(openproject);
		
		JMenuItem menuItem2 = new JMenuItem("Save", KeyEvent.VK_S);
	    menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
	    menuItem2.addActionListener(this);
	    // Ici sérialization automatique de l'objet courant
	    menu1.add(menuItem2);
	    
	    JMenuItem menuItem3 = new JMenuItem("Save Project", KeyEvent.VK_A);
		 menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		 menuItem3.addActionListener(this);
		 // Prendre en gestion les différentes extensions d'images 
		 // ainsi que la sérialization des .myphd
		menu1.add(menuItem3);
		
		JMenuItem menuItem4 = new JMenuItem("Save picture", KeyEvent.VK_P);
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		 // Prendre en gestion les différentes extensions d'images 
		 // ainsi que la sérialization des .myphd
		menu1.add(menuItem4);
		
		JMenuItem menuItem5 = new JMenuItem("New project", KeyEvent.VK_N);
		menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu1.add(menuItem5);
		
		JMenuItem menuItem6 = new JMenuItem("Quit",KeyEvent.VK_Q);
		menuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuItem6.addActionListener(this);
		menu1.add(menuItem6);

		JMenuItem menuItem7 = new JMenuItem("Undo", KeyEvent.VK_U);
		menuItem7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		menuItem7.addActionListener(this);
		menu2.add(menuItem7);
		
		JMenuItem menuItem8 = new JMenuItem("Redo", KeyEvent.VK_R);
		menuItem8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		menuItem8.addActionListener(this);
		menu2.add(menuItem8);
		
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		setJMenuBar(menuBar);
		createButton();

	}
	
	public void createButton()
	{
		JPanel panel = new JPanel(new FlowLayout());
		
		button1 = new JButton("Undo");
		button1.setSize(30, 30);
		panel.add(button1);
		
		button2 = new JButton("Redo");
		button2.setSize(30, 30);
		panel.add(button2);
		
		button3 = new JButton("Save");
		button3.setSize(30, 30);
		panel.add(button3);
		
		button4 = new JButton("Open");
		button4.setSize(30, 30);
		panel.add(button4);
		
		button1.setEnabled(false);
		button2.setEnabled(false);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		mainPanel_.add(panel, BorderLayout.NORTH);
		this.setContentPane(mainPanel_);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().contentEquals("Open"))
		{
			filechooser = new JFileChooser();
			File f;
			try 
			{
				f = new File(new File(".").getCanonicalPath());
				filechooser.setCurrentDirectory(f);
				filechooser.setCurrentDirectory(null);
				filechooser.showOpenDialog(null);

				currentfile = new ImagePanel(filechooser.getSelectedFile());
				currentfile.setPreferredSize(new Dimension(currentfile.getWidth(), currentfile.getHeight()));
				currentfile.setMinimumSize(new Dimension(currentfile.getWidth(), currentfile.getHeight()));
				currentfile.memento_ = new Memento(currentfile.image);
				currentfile.caretaker_.add(currentfile.memento_);
				currentfile.caretaker_.add(currentfile.pixels);
				currentproject = new ProjectUtil();
				currentproject.setImgP(currentfile);
				currentproject.setCareTaker(currentfile.caretaker_);
				currentproject.setMemento(currentfile.memento_);
				currentimg_ = currentfile;

				scroll.getViewport().removeAll();
				scroll.getViewport().add(currentfile);
				scroll.repaint();
				mainPanel_.add(scroll);
				this.setContentPane(mainPanel_);
				mainPanel_.repaint();

				currentmodindex_ = currentimg_.caretaker_.getIndex();
				button1.setEnabled(false);
				button2.setEnabled(false);
			} catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().contains("Open Project"))
		{
			File f;
			try 
			{
				f = new File(new File(".").getCanonicalPath());
				filechooser.setCurrentDirectory(f);
				filechooser.setCurrentDirectory(null);
				filechooser.showOpenDialog(null);
				FileInputStream fis;
				if (filechooser.getSelectedFile().getName().endsWith(".myPSD"))
				{
					fis = new FileInputStream(filechooser.getSelectedFile());
					ObjectInputStream ois= new ObjectInputStream(fis);
					currentfile =  (ImagePanel) ois.readObject();
					scroll.getViewport().removeAll();
					scroll.setViewportView(currentfile);
					scroll.repaint();
					mainPanel_.repaint();

					if (currentfile.caretaker_.getIndex() != 0)
					{
						button1.setEnabled(true);
						button2.setEnabled(true);
					}
					ois.close();
					fis.close();
				}
			}
					
				 catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				givenName_ = currentimg_.shortName;
			}
		
		
		if (e.getActionCommand().contentEquals("Save"))
		{
			File f;
			try 
			{
				f = new File(new File(".").getCanonicalPath());
				filechooser.setCurrentDirectory(f);
				filechooser.setCurrentDirectory(null);
				int x = filechooser.showSaveDialog(null);
				if (x == JFileChooser.APPROVE_OPTION)
				{
					FileOutputStream fos = new FileOutputStream(filechooser.getSelectedFile());
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(currentfile);
					oos.flush();
					oos.close();
					fos.close();
				}
			} catch (FileNotFoundException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().contains("Save Project"))
		{
			File f;
			try 
			{
				f = new File(new File(".").getCanonicalPath());
				filechooser.setCurrentDirectory(f);
				filechooser.setCurrentDirectory(null);
				int x = filechooser.showSaveDialog(null);
				if (x == JFileChooser.APPROVE_OPTION)
				{
					FileOutputStream fos = new FileOutputStream(filechooser.getSelectedFile()+ ".myPSD");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(currentproject);
					oos.flush();
					oos.close();
					fos.close();
				}
			} catch (FileNotFoundException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().contains("Save picture"))
		{
			File f;//FAUX
			try 
			{
				f = new File(new File(".").getCanonicalPath());
				filechooser.setCurrentDirectory(f);
				filechooser.setCurrentDirectory(null);
				int x = filechooser.showSaveDialog(null);
				if (x == JFileChooser.APPROVE_OPTION)
				{
					FileOutputStream fos = new FileOutputStream(filechooser.getSelectedFile());
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(currentfile.caretaker_);
					oos.flush();
					oos.close();
					fos.close();
				}
				else
				{
					
				}
			}
				catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().contains("Quit"))
		{
			System.exit(0);
		}
		
		if (e.getActionCommand().contains("Undo"))
		{
			currentfile.caretaker_.undo();
			currentfile.setImage(currentfile.caretaker_.get(currentfile.caretaker_.getIndex()).getState());
			currentfile.repaint();
			if (currentfile.caretaker_.getIndex() == 0)
			{
				button1.setEnabled(false);
			}
			if (currentfile.caretaker_.maxEl() > 1)
			{
				button2.setEnabled(true);
			}
		}
		
		if (e.getActionCommand().contains("Redo"))
		{
			currentfile.caretaker_.redo();
			currentfile.setImage(currentfile.caretaker_.get(currentfile.caretaker_.getIndex()).getState());
			currentfile.repaint();
			if (currentfile.caretaker_.getIndex() + 1  == currentfile.caretaker_.maxEl())
			{
				button2.setEnabled(false);
			}
			if (currentfile.caretaker_.getIndex() >= 0)
			{
				button1.setEnabled(true);
			}
		}
		
		/*if (plugindt_.containsKey(e.getActionCommand()))
		{
			Workingfilter wf = new Workingfilter(currentfile, (IPlugin) plugindt_.get(e.getActionCommand()));
			wf.execute();

			currentfile.caretaker_.redo();
			currentfile.memento_ = new Memento(currentfile.image);
			currentfile.caretaker_.add(currentfile.memento_);
			try { doc.insertString(doc.getLength(),e.getActionCommand() + System.getProperty("line.separator"),style); }
			catch (BadLocationException e1){}
			button1.setEnabled(true);
		}*/
	}

}
