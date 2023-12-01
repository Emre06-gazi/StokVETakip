package tr.com.ek.fe;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.utilities.MenulerCom;

public class AnaPencereFe extends JFrame implements FeInterfaces{
	
	public AnaPencereFe() {
		initPencere();
		
	}

	@Override
	public void initPencere() {
		//JTabbedPane tabs = initTabs();
		JMenuBar bar = initBar(); 
		
		//add(tabs);
		setJMenuBar(bar);
		setTitle("Satış Ve Stok Otomasyon Sistemi");	
		//pack(); //Textfieldların otomatik olarak "size" olması için yazdık
		setSize(600, 250);
		setVisible(true); //Sayfa açılsın
		setLocationRelativeTo(null); //Proje merkezde konumlansın
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel();
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}


}
