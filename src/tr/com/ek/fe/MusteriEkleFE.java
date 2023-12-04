package tr.com.ek.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.ek.dal.AccountDAL;
import tr.com.ek.dal.MusteriDAL;
import tr.com.ek.dal.PersonelDAL;
import tr.com.ek.dal.YetkilerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.AccountsContract;
import tr.com.ek.types.MusteriContract;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.Yetkiler;

public class MusteriEkleFE extends JDialog implements FeInterfaces{

	public MusteriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekleme Alanı"));
		
		add(panel);
		
		setTitle("Müşteri Ekleme İşlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(2, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		
		JLabel adiLabel = new JLabel("Müşteri Adı Soyadı:",JLabel.RIGHT);
		fieldPanel.add(adiLabel);
		JTextField adiField = new JTextField(15);
		fieldPanel.add(adiField);
		JLabel telefonLabel = new JLabel("Müşteri Telefonu:",JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		
		JTextArea adresArea = new JTextArea(7,2);
		JScrollPane pane = new JScrollPane(adresArea);
		
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract = new MusteriContract();
				contract.setAdiSoyadi(adiField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setAdres(adresArea.getText());
				
				new MusteriDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi() + " adlı müşteri eklenmiştir.");

			}

		});
		
		JButton iptalButton = new JButton("İptal");
		buttonPanel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 dispose();
			}

		});
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);

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
