package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.ek.complex.types.SatisContractComplex;
import tr.com.ek.complex.types.StokContractComplex;
import tr.com.ek.complex.types.StokContractTotalComplex;
import tr.com.ek.dal.AccountDAL;
import tr.com.ek.dal.MusteriDAL;
import tr.com.ek.dal.SatisDAL;
import tr.com.ek.dal.StokDAL;
import tr.com.ek.dal.UrunlerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.MusteriContract;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.SatisContract;
import tr.com.ek.types.StokContract;
import tr.com.ek.types.UrunlerContract;
import tr.com.ek.utilities.MenulerCom;

public class AnaPencereFe extends JFrame implements FeInterfaces {

	public AnaPencereFe() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Satış Ve Stok Otomasyon Sistemi");
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/Fatcow-Farm-Fresh-Application-view-icons.32.png");
		ImageIcon icon2 = new ImageIcon("icons/Ilicon-The-Circus-IliCon-The-Circus-Icons.32.png");

		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());

		/* STOK PANELİ */
		JPanel stokSolPanel = new JPanel(new GridLayout(5, 2));
		Object[] stokKolonlar = { "Id", "Ürün Adı", "Personel Adı", "Tarihi", "Adeti" };
		DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
		JTable table = new JTable(model);

		JScrollPane stokTablePane = new JScrollPane(table);

		for (StokContractComplex contract : new StokDAL().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}

		JLabel stokUrunAdiLabel = new JLabel("Ürün Adi:", JLabel.RIGHT);
		stokSolPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdıBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stokSolPanel.add(stokUrunAdıBox);
		JLabel stokAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		stokSolPanel.add(stokAdetLabel);
		JTextField adetField = new JTextField(15);
		stokSolPanel.add(adetField);
		JLabel tarihLabel = new JLabel("Stok Tarihi:", JLabel.RIGHT);
		stokSolPanel.add(tarihLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolPanel.add(stokTarihi);

		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolPanel.add(stokEkleButton);
		JButton stokGuncelleButton = new JButton("Güncelle");
		stokSolPanel.add(stokGuncelleButton);
		JButton stokToplamButton = new JButton("Toplam Stok");
		stokSolPanel.add(stokToplamButton);
		JButton stokSilButton = new JButton("Sil");
		stokSolPanel.add(stokSilButton);

		stokSilButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int stokId = (int) table.getValueAt(selectedRow, 0);
					new StokDAL().Delete(stokId);

					model.removeRow(selectedRow);

					JOptionPane.showMessageDialog(null, "Stok kaydı silindi.");
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir stok kaydı seçin.");
				}
			}
		});

		stokEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				UrunlerContract uContract = (UrunlerContract) stokUrunAdıBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFe.emailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				String date = format.format(stokTarihi.getDate());

				contract.setPersonalId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(adetField.getText()));

				new StokDAL().Insert(contract);

				JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlı ürün eklenmiştir.");

				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0); // Tabloyu silip tekrar yüklemek için
				}
				for (StokContractComplex stokContract : new StokDAL().GetAllStok()) {
					model.addRow(stokContract.getVeriler());
				}
			}
		});

		stokToplamButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0); // Tabloyu silip tekrar yüklemek için
				}
				for (StokContractTotalComplex stokTContract : new StokDAL().GetTotalStok()) {
					model.addRow(stokTContract.getVeriler());
				}
			}
		});

		stokGuncelleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int stokId = (int) table.getValueAt(selectedRow, 0);

					// Retrieve the selected values from the table
					UrunlerContract selectedUrun = (UrunlerContract) stokUrunAdıBox.getSelectedItem();
					int adet = Integer.parseInt(adetField.getText());

					// Check if the date is not null
					Date selectedDate = stokTarihi.getDate();
					if (selectedDate != null) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String tarih = format.format(selectedDate);

						// Create a StokContract object with the updated values
						StokContract updatedStok = new StokContract();
						updatedStok.setId(stokId);
						updatedStok.setUrunId(selectedUrun.getId());
						updatedStok.setAdet(adet);
						updatedStok.setTarih(tarih);

						// Update the Stok record in the database
						new StokDAL().Update(updatedStok);

						// Update the table model with the new data
						model.setValueAt(stokId, selectedRow, 0);
						model.setValueAt(selectedUrun.getAdi(), selectedRow, 1);
						model.setValueAt(adet, selectedRow, 4); // Update the adet column

						JOptionPane.showMessageDialog(null, "Stok kaydı güncellendi.");
					} else {
						JOptionPane.showMessageDialog(null, "Lütfen geçerli bir tarih seçin.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir stok kaydı seçin.");
				}
			}
		});



		/* Satış Paneli */

	
		JPanel satisSagUstPanel = new JPanel(new GridLayout(6, 2));


		Object[] satisKolonlar = { "Id", "Müşteri Adı", "Personel Adı", "Ürün Adı", "Tarih", "Stok Adeti" };
		DefaultTableModel modelSatis = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(modelSatis);

		JScrollPane SatisTablePane = new JScrollPane(satisTable);

		for (SatisContractComplex satisContract : new SatisDAL().GetAllSatis()) {
			modelSatis.addRow(satisContract.getVeriler());
		}
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adi:", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdıBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdıBox);
		JLabel satisMusteriLabel = new JLabel("Müşteri Adi:", JLabel.RIGHT);
		satisSagUstPanel.add(satisMusteriLabel);
		JComboBox satisMusteriAdıBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		satisSagUstPanel.add(satisMusteriAdıBox);
		JLabel satisAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisField = new JTextField(15);
		satisSagUstPanel.add(satisField);
		JLabel tarihSatisLabel = new JLabel("Satış Tarihi:", JLabel.RIGHT);
		satisSagUstPanel.add(tarihSatisLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Satış Ekle");
		satisSagUstPanel.add(satisEkleButton);
		JButton satisGuncelleButton = new JButton("Güncelle");
		satisSagUstPanel.add(satisGuncelleButton);
		JButton satisSilButton = new JButton("Sil");
		satisSagUstPanel.add(satisSilButton);

		satisSilButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = satisTable.getSelectedRow();
				if (selectedRow != -1) {
					int satisId = (int) satisTable.getValueAt(selectedRow, 0);
					new SatisDAL().Delete(satisId);

					modelSatis.removeRow(selectedRow);

					JOptionPane.showMessageDialog(null, "Satış kaydı silindi.");
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir satış kaydı seçin.");
				}
			}
		});

		satisEkleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SatisContract contract = new SatisContract();
				StokContract stokContract = new StokContract(); // Eklenen kısım
				UrunlerContract uContract = (UrunlerContract) satisUrunAdıBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFe.emailBox.getSelectedItem();
				MusteriContract mContract = (MusteriContract) satisMusteriAdıBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				String date = format.format(satisTarihi.getDate());

				// Seçilen ürünün stok adedini kontrol et
				stokContract = new StokDAL().GetByUrunId(uContract.getId());

				// Stok adeti yeterliyse satış işlemi gerçekleştir
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setMusteriId(mContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(satisField.getText()));

				new SatisDAL().Insert(contract);

				// Stok adedini güncelle
				stokContract.setUrunId(uContract.getId());
				stokContract.setPersonalId(pContract.getId());
				stokContract.setTarih(date.toString());
				stokContract.setAdet(-Integer.parseInt(satisField.getText()));

				new StokDAL().Update(stokContract);

				JOptionPane.showMessageDialog(null, "Ürün satış kaydı tamamlandı. Stoklarda " + uContract.getAdi()
						+ " ürününden " + contract.getAdet() + " adet azalmıştır.");

				int satir = modelSatis.getRowCount();
				for (int i = 0; i < satir; i++) {
					modelSatis.removeRow(0); // Tabloyu silip tekrar yüklemek için
				}
				for (SatisContractComplex satisContract : new SatisDAL().GetAllSatis()) {
					modelSatis.addRow(satisContract.getVeriler());
				}
			}
		});

		satisGuncelleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = satisTable.getSelectedRow();
				if (selectedRow != -1) {
					int satisId = (int) satisTable.getValueAt(selectedRow, 0);

					// Retrieve the selected values from the table
					UrunlerContract selectedUrun = (UrunlerContract) satisUrunAdıBox.getSelectedItem();
					MusteriContract selectedMusteri = (MusteriContract) satisMusteriAdıBox.getSelectedItem();
					int adet = Integer.parseInt(satisField.getText());

					// Check if the date is not null
					Date selectedDate = satisTarihi.getDate();
					if (selectedDate != null) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String tarih = format.format(selectedDate);

						// Create a SatisContract object with the updated values
						SatisContract updatedSatis = new SatisContract();
						updatedSatis.setId(satisId);
						updatedSatis.setUrunId(selectedUrun.getId());
						updatedSatis.setMusteriId(selectedMusteri.getId());
						updatedSatis.setAdet(adet);
						updatedSatis.setTarih(tarih);

						// Update the Satis record in the database
						new SatisDAL().Update(updatedSatis);

						// Update the table model with the new data
						modelSatis.setValueAt(satisId, selectedRow, 0);
						modelSatis.setValueAt(selectedMusteri.getAdiSoyadi(), selectedRow, 1);
						modelSatis.setValueAt(selectedUrun.getAdi(), selectedRow, 2);
						modelSatis.setValueAt(adet, selectedRow, 5); // Update the adet column

						JOptionPane.showMessageDialog(null, "Satış kaydı güncellendi.");
					} else {
						JOptionPane.showMessageDialog(null, "Lütfen geçerli bir tarih seçin.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir satış kaydı seçin.");
				}
			}
		});

		stokPanel.add(stokSolPanel, BorderLayout.EAST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);

		satisPanel.add(satisSagUstPanel, BorderLayout.EAST);
		satisPanel.add(SatisTablePane, BorderLayout.CENTER);

		pane.addTab("Stoklar", icon, stokPanel, "Does Nothing");
		pane.addTab("Satışlar", icon2, satisPanel, "Does Nothingg");

		return pane;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new AnaPencereFe());
	}
}
