package tr.com.ek.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.KategoriContract;

public class KategoriEkleFE extends JDialog implements FeInterfaces{

	public KategoriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Kayıt Alanı"));
		
		add(panel);
		
		setTitle("Kategori Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		
		JLabel adiLabel = new JLabel("Kategori Adı:",JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		JLabel kategoriLabel = new JLabel("Kategori Seç:",JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("-Kategori Seçiniz-", 0);
		kategoriBox.setSelectedIndex(0); //"-Kategori Seçiniz-" değeri varsayılan olarak seçili gelmesi için yazılmıştır
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract = new KategoriContract();
				
				if(kategoriBox.getSelectedIndex()!=0) {
					KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
					contract.setAdi(adiField.getText());
					contract.setParentId(casContract.getId());
					
					new KategoriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null, contract.getAdi() +" adlı kategori eklenmiştir.");
				} else {
					contract.setAdi(adiField.getText());
					contract.setParentId(kategoriBox.getSelectedIndex());
					
					new KategoriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null, contract.getAdi() +" adlı kategori eklenmiştir.");
				}
			}
			
		});
		
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 dispose();
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
