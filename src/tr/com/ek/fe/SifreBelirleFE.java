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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.ek.dal.AccountDAL;
import tr.com.ek.dal.KategoriDAL;
import tr.com.ek.dal.PersonelDAL;
import tr.com.ek.dal.YetkilerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.AccountsContract;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.Yetkiler;

public class SifreBelirleFE extends JDialog implements FeInterfaces {

	public SifreBelirleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Şifre İşlemleri Alanı"));

		add(panel);

		setTitle("Şifre İşlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel personelLabel = new JLabel("Personel Seç:", JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelBox);
		JLabel yetkiLabel = new JLabel("Yetki Seç:", JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkiBox);
		JLabel sifreLabel = new JLabel("Şifre Belirle:", JLabel.RIGHT);
		panel.add(sifreLabel);
		JPasswordField sifreField = new JPasswordField(10);
		panel.add(sifreField);
		JLabel sifreTekrarLabel = new JLabel("Şifre Tekrar:", JLabel.RIGHT);
		panel.add(sifreTekrarLabel);
		JPasswordField sifreTekrarField = new JPasswordField(10);
		panel.add(sifreTekrarField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);

		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsContract contract = new AccountsContract();
				PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
				Yetkiler yContract = (Yetkiler) yetkiBox.getSelectedItem();

				if (sifreField.getText().equals(sifreTekrarField.getText())) {

					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(sifreField.getText());

					new AccountDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,
							pContract.getAdiSoyadi() + " isimli personel şifresi eklenmiştir.");
				}

				else {
					JOptionPane.showMessageDialog(null, "Şifreler uyuşmuyor, tekrar deneyiniz!");
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
