package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.utilities.MenulerCom;

public class AnaPencereFe extends JFrame implements FeInterfaces{
	
	public AnaPencereFe() {
		initPencere();
		
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar(); 
		
		add(panel);
		setJMenuBar(bar);
		setTitle("Satış Ve Stok Otomasyon Sistemi");	
		//pack(); //Textfieldların otomatik olarak "size" olması için yazdık
		setSize(800, 600);
		setVisible(true); //Sayfa açılsın
		setLocationRelativeTo(null); //Proje merkezde konumlansın
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/Fatcow-Farm-Fresh-Application-view-icons.32.png");
		ImageIcon icon2	 = new ImageIcon("icons/Ilicon-The-Circus-IliCon-The-Circus-Icons.32.png");
		
		JPanel stokPanel = new JPanel();
		JPanel satisPanel = new JPanel();
		
		/*STOK PANELİ*/
		JPanel stokSolPanel = new JPanel (new GridLayout(5,2));
		Object [] stokKolonlar = {"Id", "Personel Adı", "Ürün Adı", "Adeti", "Tarihi"};
		DefaultTableModel model = new DefaultTableModel(stokKolonlar,0);
		JTable table = new JTable(model);
		
		JScrollPane stokTablePane = new JScrollPane(table);
		
		JLabel stokUrunAdiLabel = new JLabel ("Ürün Adi:", JLabel.RIGHT);
		stokSolPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdıBox = new JComboBox();
		stokSolPanel.add(stokUrunAdıBox);
		JLabel stokAdetLabel = new JLabel ("Adet:", JLabel.RIGHT);
		stokSolPanel.add(stokAdetLabel);
		JTextField adetField = new JTextField(15);
		stokSolPanel.add(adetField);
		JLabel tarihLabel = new JLabel ("Stok Tarihi:", JLabel.RIGHT);
		stokSolPanel.add(tarihLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolPanel.add(stokTarihi);
		
		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Yenile");
		stokSolPanel.add(stokYenileButton);
		
		stokPanel.add(stokSolPanel, BorderLayout.EAST);	
		stokPanel.add(stokTablePane, BorderLayout.CENTER);	
		
		/*Satış Paneli*/
		
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(4,2));
		JPanel satisSagAltPanel = new JPanel();
		
		Object [] satisKolonlar = {"Id", "Personel Adı", "Müşteri Adı", "Ürün Adı", "Adeti", "Tarihi"};
		DefaultTableModel modelSatis = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(modelSatis);
		
		JScrollPane SatisTablePane = new JScrollPane(satisTable);
		
		JLabel satisUrunAdiLabel = new JLabel ("Ürün Adi:", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdıBox = new JComboBox();
		satisSagUstPanel.add(satisUrunAdıBox);
		JLabel satisAdetLabel = new JLabel ("Adet:", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisField = new JTextField(15);
		satisSagUstPanel.add(satisField);
		JLabel tarihSatisLabel = new JLabel ("Satış Tarihi:", JLabel.RIGHT);
		satisSagUstPanel.add(tarihSatisLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);
		
		JButton satisEkleButton = new JButton("Satış Ekle");
		satisSagUstPanel.add(satisEkleButton);
		JButton satışYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satışYenileButton);
		
		stokPanel.add(stokSolPanel, BorderLayout.EAST);	
		stokPanel.add(stokTablePane, BorderLayout.CENTER);	
		
		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(SatisTablePane, BorderLayout.CENTER);	
		
		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);
		
		pane.addTab("Stoklar", icon, stokPanel, "Does Nothing");
		pane.addTab("Satışlar", icon2, satisPanel, "Does Nothingg");

		return pane;
	}
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnaPencereFe());
    }

}
