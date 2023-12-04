package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import tr.com.ek.complex.types.StokContractComplex;
import tr.com.ek.dal.KategoriDAL;
import tr.com.ek.dal.StokDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.KategoriContract;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.StokContract;
import tr.com.ek.types.UrunlerContract;

public class KategoriDuzenleFE extends JDialog implements FeInterfaces {

	public KategoriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {

		JPanel panel = initPanel();

		add(panel);

		setTitle("Kategori Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// Üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(3, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
		JLabel adiDuzenleLabel = new JLabel("Yeni Kategori Adı:", JLabel.RIGHT);
		ustPanel.add(adiDuzenleLabel);
		JTextField adiDuzenleField = new JTextField(10);
		ustPanel.add(adiDuzenleField);
		JLabel kategoriAraLabel = new JLabel("Kategori Ara:", JLabel.RIGHT);
		ustPanel.add(kategoriAraLabel);
		JTextField kategoriAraField = new JTextField(10);
		ustPanel.add(kategoriAraField);

		JButton kategoriGuncelleButton = new JButton("Güncelle");
		ustPanel.add(kategoriGuncelleButton);
		JButton kategoriİptalButton = new JButton("İptal");
		ustPanel.add(kategoriİptalButton);

		JList kategoriList = new JList();
		kategoriList.setListData(new KategoriDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(kategoriList);
		pane.setBorder(BorderFactory.createTitledBorder("Kategori Listesi"));
		kategoriList.setSelectedIndex(0);

		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);

		kategoriGuncelleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Seçilen kategori bilgisini al
				Object selectedObject = kategoriList.getSelectedValue();

				if (selectedObject instanceof KategoriContract) {
					KategoriContract selectedKategori = (KategoriContract) selectedObject;

					// Yeni adı al
					String yeniAd = adiDuzenleField.getText();

					// Yeni ad boş değilse ve eski ad ile farklı ise güncelleme yap
					if (!yeniAd.isEmpty() && !yeniAd.equals(selectedKategori.getAdi())) {
						selectedKategori.setAdi(yeniAd);
						new KategoriDAL().Update(selectedKategori);
						JOptionPane.showMessageDialog(null,
								selectedKategori.getAdi() + " adlı kategori güncellenmiştir.");
					} else {
						JOptionPane.showMessageDialog(null, "Lütfen geçerli bir kategori adı girin.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir kategori seçin.");
				}
			}
		});

		kategoriİptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		kategoriList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					List<String> stringSelectedList = new ArrayList<>();

					for (Object selectedItem : kategoriList.getSelectedValues()) {
						if (selectedItem instanceof KategoriContract) {
							stringSelectedList.add(((KategoriContract) selectedItem).toString());
						}
					}

					String selectedValues = String.join(", ", stringSelectedList);

					adiDuzenleField.setText(selectedValues);
				}
			}
		});

		kategoriAraField.addKeyListener(new KeyListener() {

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
				kategoriList.setListData(new KategoriDAL().GetSearchKategori(kategoriAraField.getText()).toArray());
				kategoriList.setSelectedIndex(0);
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
