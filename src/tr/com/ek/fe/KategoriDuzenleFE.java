package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.ek.dal.KategoriDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.KategoriContract;

public class KategoriDuzenleFE extends JDialog implements FeInterfaces {

	public KategoriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {

		JPanel panel = initPanel();

		add(panel);

		setTitle("Kategori Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(2, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
		JLabel adiLabel = new JLabel("Kategori Adı:", JLabel.RIGHT);
		ustPanel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		ustPanel.add(adiField);
		
		JLabel kategoriLabel = new JLabel("Üst Kategori Seç:", JLabel.RIGHT);
		ustPanel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
		ustPanel.add(kategoriBox);
		kategoriBox.insertItemAt("-Üst Kategori Seçiniz-", 0);
		kategoriBox.setSelectedIndex(0); 
		
		JList kategoriList = new JList();
		kategoriList.setListData(new KategoriDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(kategoriList);	
		pane.setBorder(BorderFactory.createTitledBorder("Düzenlenecek Liste"));
		kategoriList.setSelectedIndex(0);
		panel.add(ustPanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);

		adiField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				kategoriList.setListData(new KategoriDAL().GetSearchKategori(adiField.getText()).toArray());
				kategoriList.setSelectedIndex(0);
			}
		});

		return panel;
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
