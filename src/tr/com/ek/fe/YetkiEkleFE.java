package tr.com.ek.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.ek.dal.PersonelDAL;
import tr.com.ek.dal.YetkilerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.Yetkiler;

public class YetkiEkleFE extends JDialog implements FeInterfaces{

	public YetkiEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Kayıt Alanı"));
		
		add(panel);
		
		setTitle("Yetki Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		
		JLabel adiLabel = new JLabel("Yetki Adı:",JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Yetkiler contract = new Yetkiler();
				contract.setAdi(adiField.getText());
				
				new YetkilerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdi() +" adlı yetki eklenmiştir.");
				
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
