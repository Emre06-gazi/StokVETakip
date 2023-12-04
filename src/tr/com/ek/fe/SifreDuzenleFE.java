package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tr.com.ek.dal.AccountDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.AccountsContract;

public class SifreDuzenleFE extends JDialog implements FeInterfaces {

	public SifreDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);

		setTitle("Şifre Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// Üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Şifre Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(4, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Şifre Düzenle"));
		JLabel yetkiLabel = new JLabel("Yeni Yetki:", JLabel.RIGHT);
		ustPanel.add(yetkiLabel);
		JTextField yetkiAdiField = new JTextField(10);
		ustPanel.add(yetkiAdiField);

		JLabel sifreLabel = new JLabel("Yeni Şifre Belirle:", JLabel.RIGHT);
		ustPanel.add(sifreLabel);
		JPasswordField sifreField = new JPasswordField(10);
		ustPanel.add(sifreField);

		JLabel sifreTekrarLabel = new JLabel("Yeni Şifre Tekrar:", JLabel.RIGHT);
		ustPanel.add(sifreTekrarLabel);
		JPasswordField sifreTekrarField = new JPasswordField(10);
		ustPanel.add(sifreTekrarField);

		JButton guncelleButton = new JButton("Güncelle");
		ustPanel.add(guncelleButton);
		JButton iptalButton = new JButton("İptal");
		ustPanel.add(iptalButton);

		JList sifreList = new JList();
		sifreList.setListData(new AccountDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(sifreList);
		pane.setBorder(BorderFactory.createTitledBorder("Personellerin Şifre Listesi"));
		sifreList.setSelectedIndex(0);

		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);

		guncelleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Seçilen kategori bilgisini al
				Object selectedObject = sifreList.getSelectedValue();

				if (selectedObject instanceof AccountsContract) {
					AccountsContract selectedSifre = (AccountsContract) selectedObject;

					// Yeni adı al
					String yeniYetki = yetkiAdiField.getText();
					String yeniSifre = sifreField.getText();

					if (!yeniYetki.isEmpty() && !yeniYetki.equals(selectedSifre.getYetkiId())) {
						selectedSifre.getYetkiId();
					}

					if (!yeniSifre.isEmpty() && !yeniSifre.equals(selectedSifre.getSifre())) {
						selectedSifre.setSifre(yeniSifre);
					}

					new AccountDAL().Update(selectedSifre);
					JOptionPane.showMessageDialog(null,
							selectedSifre.getPersonelId() + " Personel Idli, personelin bilgileri güncellenmiştir.");
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir personel seçin.");
				}
			}

		});

		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		sifreList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					Object selectedObject = sifreList.getSelectedValue();

					if (selectedObject instanceof AccountsContract) {
						AccountsContract selectedPerson = (AccountsContract) selectedObject;

						yetkiAdiField.setText(Integer.toString(selectedPerson.getYetkiId()));
						sifreTekrarField.setText(selectedPerson.getSifre());
						sifreField.setText(selectedPerson.getSifre());

					}
				}
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
