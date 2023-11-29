package tr.com.ek.fe;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import tr.com.ek.interfaces.FeInterfaces;

public class AnaPencereFe extends JFrame implements FeInterfaces{
	
	public AnaPencereFe() {
		initPencere();
		
	}

	@Override
	public void initPencere() {
		JTabbedPane tabs = initTabs();
		JMenuBar bar = initBar(); 
		
		//add(tabs);
		//setJMenuBar(bar);
		setTitle("Satış Ve Stok Otomasyon Sistemi");	
		pack(); //Textfieldların otomatik olarak "size" olması için yazdık
		setVisible(true); //Sayfa açılsın
		setLocationRelativeTo(null); //Proje merkezde konumlansın
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}


}
