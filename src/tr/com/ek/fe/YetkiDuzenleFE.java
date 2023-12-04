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
import tr.com.ek.dal.YetkilerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.Yetkiler;

public class YetkiDuzenleFE extends JDialog implements FeInterfaces {

	public YetkiDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);

		setTitle("Yetki Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// Üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(3, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenle"));
		JLabel adiDuzenleLabel = new JLabel("Yetki Yeni Adı:", JLabel.RIGHT);
		ustPanel.add(adiDuzenleLabel);
		JTextField adiDuzenleField = new JTextField(20);
		ustPanel.add(adiDuzenleField);
		JLabel yetkiAraLabel = new JLabel("Yetki Ara:", JLabel.RIGHT);
		ustPanel.add(yetkiAraLabel);
		JTextField yetkiAraField = new JTextField(15);
		ustPanel.add(yetkiAraField);

		JButton guncelleButton = new JButton("Güncelle");
		ustPanel.add(guncelleButton);
		JButton iptalButton = new JButton("İptal");
		ustPanel.add(iptalButton);

		JList yetkiList = new JList();
		yetkiList.setListData(new YetkilerDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(yetkiList);
		pane.setBorder(BorderFactory.createTitledBorder("Yetki Listesi"));
		yetkiList.setSelectedIndex(0);

		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);

		guncelleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedObject = yetkiList.getSelectedValue();

				if (selectedObject instanceof Yetkiler) {
					Yetkiler selectedYetki = (Yetkiler) selectedObject;

					String yeniAd = adiDuzenleField.getText();

					if (!yeniAd.isEmpty() && !yeniAd.equals(selectedYetki.getAdi())) {
						selectedYetki.setAdi(yeniAd);
					}

					// Güncelleme yap
					new YetkilerDAL().Update(selectedYetki);
					JOptionPane.showMessageDialog(null,
							selectedYetki.getAdi() + " adlı yetki bilgileri güncellenmiştir.");
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir yetki seçin.");
				}
			}
		});

		yetkiList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					Object selectedObject = yetkiList.getSelectedValue();

					if (selectedObject instanceof Yetkiler) {
						Yetkiler selectedYetki = (Yetkiler) selectedObject;

						adiDuzenleField.setText(selectedYetki.getAdi());
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

		yetkiAraField.addKeyListener(new KeyListener() {
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
				String arananKelime = yetkiAraField.getText().trim();

				List<Yetkiler> aramaSonucu = new YetkilerDAL().GetSearchYetki(arananKelime);

				yetkiList.setListData(aramaSonucu.toArray());

				if (!aramaSonucu.isEmpty()) {
					yetkiList.setSelectedIndex(0);
					Yetkiler selectedYetki = aramaSonucu.get(0);
					adiDuzenleField.setText(selectedYetki.getAdi());

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
