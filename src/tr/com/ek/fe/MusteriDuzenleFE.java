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

import tr.com.ek.dal.MusteriDAL;
import tr.com.ek.dal.YetkilerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.MusteriContract;
import tr.com.ek.types.Yetkiler;

public class MusteriDuzenleFE extends JDialog implements FeInterfaces{

	public MusteriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);

		setTitle("Müşteri Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// Üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(7, 3));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Müşteri Düzenle"));
		JLabel adiDuzenleLabel = new JLabel("Müşteri Yeni Adı:", JLabel.RIGHT);
		ustPanel.add(adiDuzenleLabel);
		JTextField adiDuzenleField = new JTextField(20);
		ustPanel.add(adiDuzenleField);
		JLabel telDuzenleLabel = new JLabel("Müşteri Yeni Telefonu:", JLabel.RIGHT);
		ustPanel.add(telDuzenleLabel);
		JTextField telDuzenleField = new JTextField(20);
		ustPanel.add(telDuzenleField);
		JLabel adresDuzenleLabel = new JLabel("Müşteri Yeni Adresi:", JLabel.RIGHT);
		ustPanel.add(adresDuzenleLabel);
		JTextField adresDuzenleField = new JTextField(20);
		ustPanel.add(adresDuzenleField);
		JLabel musteriAraLabel = new JLabel("Müşteri Ara:", JLabel.RIGHT);
		ustPanel.add(musteriAraLabel);
		JTextField musteriAraField = new JTextField(15);
		ustPanel.add(musteriAraField);

		JButton guncelleButton = new JButton("Güncelle");
		ustPanel.add(guncelleButton);
		JButton iptalButton = new JButton("İptal");
		ustPanel.add(iptalButton);

		JList musteriList = new JList();
		musteriList.setListData(new MusteriDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(musteriList);
		pane.setBorder(BorderFactory.createTitledBorder("Müşteriler Listesi"));
		musteriList.setSelectedIndex(0);

		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);

		guncelleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedObject = musteriList.getSelectedValue();

				if (selectedObject instanceof MusteriContract) {
					MusteriContract selectedMusteri = (MusteriContract) selectedObject;

					String yeniAd = adiDuzenleField.getText();
					String yeniTel = telDuzenleField.getText();
					String yeniAdres = adresDuzenleField.getText();

					if (!yeniAd.isEmpty() && !yeniAd.equals(selectedMusteri.getAdiSoyadi())) {
						selectedMusteri.setAdiSoyadi(yeniAd);
					}
					
					if (!yeniTel.isEmpty() && !yeniTel.equals(selectedMusteri.getTelefon())) {
						selectedMusteri.setTelefon(yeniTel);
					}
					
					if (!yeniAdres.isEmpty() && !yeniAdres.equals(selectedMusteri.getAdres())) {
						selectedMusteri.setAdres(yeniAdres);			
					}

					// Güncelleme yap
					new MusteriDAL().Update(selectedMusteri);
					JOptionPane.showMessageDialog(null,
							selectedMusteri.getAdiSoyadi() + " adlı müşteri bilgileri güncellenmiştir.");
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir müşteri seçin.");
				}
			}
		});

		musteriList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					Object selectedObject = musteriList.getSelectedValue();

					if (selectedObject instanceof MusteriContract) {
						MusteriContract selectedMusteri = (MusteriContract) selectedObject;

						adiDuzenleField.setText(selectedMusteri.getAdiSoyadi());
						telDuzenleField.setText(selectedMusteri.getTelefon());
						adresDuzenleField.setText(selectedMusteri.getAdres());
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

		musteriAraField.addKeyListener(new KeyListener() {
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
				String arananKelime = musteriAraField.getText().trim();

				List<MusteriContract> aramaSonucu = new MusteriDAL().GetSearchMusteri(arananKelime);

				musteriList.setListData(aramaSonucu.toArray());

				if (!aramaSonucu.isEmpty()) {
					musteriList.setSelectedIndex(0);
					MusteriContract selectedMusteri = aramaSonucu.get(0);
					adiDuzenleField.setText(selectedMusteri.getAdiSoyadi());

				} else {
					adiDuzenleField.setText("");

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
