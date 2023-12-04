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
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.PersonelContract;

public class LoginFe extends JDialog implements FeInterfaces {

	public static JComboBox emailBox;

	public LoginFe() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Giriş Sayfası"));

		add(panel);

		setTitle("Giriş Yap");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel emailLabel = new JLabel("Email Adres:", JLabel.RIGHT);
		panel.add(emailLabel);
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		JLabel sifreLabel = new JLabel("Şifre:", JLabel.RIGHT);
		panel.add(sifreLabel);
		JPasswordField passwordField = new JPasswordField(10);
		panel.add(passwordField);

		JButton girişButton = new JButton("Giriş");
		panel.add(girişButton);

		girişButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();

				String sifre = passwordField.getText();

				if (new AccountDAL().GetPersonelIdVeSifre(contract.getId(), sifre).getId() != 0) {

					new AnaPencereFe();

				} else {
					JOptionPane.showMessageDialog(null, "Giriş başarısız!");
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
