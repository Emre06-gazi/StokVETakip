package tr.com.ek.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import tr.com.ek.dal.KategoriDAL;
import tr.com.ek.dal.UrunlerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.KategoriContract;
import tr.com.ek.types.UrunlerContract;

public class UrunEkleFE extends JDialog implements FeInterfaces{
	
	public UrunEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));
		
		add(panel);
		
		setTitle("Ürün Ekleyin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel adiLabel = new JLabel("Ürün Adı:",JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		JLabel kategoriLabel = new JLabel("Kategori Seç:",JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
		panel.add(kategoriBox);
		JLabel tarihLabel = new JLabel("Tarih Seç:",JLabel.RIGHT);
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);
		JLabel fiyatLabel = new JLabel("Fiyat Gir:",JLabel.RIGHT);
		panel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();
				
				KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(tarihDate.getDate());
				contract.setAdi(adiField.getText());
				contract.setKategoriId(casContract.getId());
				contract.setTarih(date);
				contract.setFiyat(Float.parseFloat(fiyatField.getText()));
				
				new UrunlerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdi() +" adlı ürün eklenmiştir.");
				
			}
			
		});
		
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
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
