package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tr.com.ek.dal.PersonelDAL;
import tr.com.ek.dal.UrunlerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.UrunlerContract;

public class PersonelDuzenleFE extends JDialog implements FeInterfaces{

	public PersonelDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);

		setTitle("Personel Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// Üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Personel Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(4, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Personel Düzenle"));
		JLabel adiDuzenleLabel = new JLabel("Personel Yeni Adı:", JLabel.RIGHT);
		ustPanel.add(adiDuzenleLabel);
		JTextField adiDuzenleField = new JTextField(20);
		ustPanel.add(adiDuzenleField);
		JLabel duzenleMailLabel = new JLabel("Personel Yeni Email Adresi:", JLabel.RIGHT);
		ustPanel.add(duzenleMailLabel);
		JTextField duzenleMailField = new JTextField(20);
		ustPanel.add(duzenleMailField);
		JLabel personAraLabel = new JLabel("Personel Ara:", JLabel.RIGHT);
		ustPanel.add(personAraLabel);
		JTextField personAraField = new JTextField(15);
		ustPanel.add(personAraField);

		JButton guncelleButton = new JButton("Güncelle");
		ustPanel.add(guncelleButton);
		JButton iptalButton = new JButton("İptal");
		ustPanel.add(iptalButton);

		JList personList = new JList();
		personList.setListData(new PersonelDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(personList);
		pane.setBorder(BorderFactory.createTitledBorder("Personel Listesi"));
		personList.setSelectedIndex(0);

		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);

		guncelleButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Seçilen personel bilgisini al
		        Object selectedObject = personList.getSelectedValue();

		        if (selectedObject instanceof PersonelContract) {
		        	PersonelContract selectedPerson = (PersonelContract) selectedObject;

		            // Yeni ad ve maili al
		            String yeniAd = adiDuzenleField.getText();
		            String yeniMail = duzenleMailField.getText();

		            // Yeni ad ve mail boş değilse ve eski ad veya mail ile farklı ise güncelleme yap
		            if (!yeniAd.isEmpty() && !yeniAd.equals(selectedPerson.getAdiSoyadi())) {
		            	selectedPerson.setAdiSoyadi(yeniAd);
		            }

		            if (!yeniMail.isEmpty() && !yeniMail.equals(selectedPerson.getEmail())) {
		            	selectedPerson.setEmail(yeniMail);
		            }

		            // Güncelleme yap
		            new PersonelDAL().Update(selectedPerson);
		            JOptionPane.showMessageDialog(null, selectedPerson.getAdiSoyadi() + " adlı personel bilgileri güncellenmiştir.");
		        } else {
		            JOptionPane.showMessageDialog(null, "Lütfen bir personel seçin.");
		        }	
		    }
		});

		personList.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting() == false) {
		            Object selectedObject = personList.getSelectedValue();

		            if (selectedObject instanceof PersonelContract) {
		            	PersonelContract selectedPerson = (PersonelContract) selectedObject;

		                // Seçilen personelın adını ve mailini ilgili alanlara yazdır
		                adiDuzenleField.setText(selectedPerson.getAdiSoyadi());
		                duzenleMailField.setText(selectedPerson.getEmail());

		            }
		        }
		    }
		});

		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		personAraField.addKeyListener(new KeyListener() {
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
		        String arananKelime = personAraField.getText().trim();
		        
		        // PersonelDAL sınıfında GetSearchPerson metodu ile arama yapılır
		        List<PersonelContract> aramaSonucu = new PersonelDAL().GetSearchPerson(arananKelime);
		        
		        // Arama sonuçlarını PERSONListe set edilir
		        personList.setListData(aramaSonucu.toArray());
		        
		        if (!aramaSonucu.isEmpty()) {
		            // Eğer arama sonuçları varsa, ilk personeli seçili hale getir ve bilgileri göster
		        	personList.setSelectedIndex(0);
		        	PersonelContract selectedPerson = aramaSonucu.get(0);
		            adiDuzenleField.setText(selectedPerson.getAdiSoyadi());
		            duzenleMailField.setText(selectedPerson.getEmail());
		        } else {
		            // Eğer arama sonuçları yoksa, alanları temizle
		            adiDuzenleField.setText("");
		            duzenleMailField.setText("");
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
