package tr.com.ek.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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

import tr.com.ek.dal.KategoriDAL;
import tr.com.ek.dal.UrunlerDAL;
import tr.com.ek.interfaces.FeInterfaces;
import tr.com.ek.types.KategoriContract;
import tr.com.ek.types.UrunlerContract;

public class UrunDuzenleFE extends JDialog implements FeInterfaces{

	public UrunDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);

		setTitle("Ürün Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);// Üst üste açılan pencerelerde arkadaki pencereye tıklanmasını önler.
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Düzenleme Alanı"));
		JPanel ustPanel = new JPanel(new GridLayout(4, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Ürün Düzenle"));
		JLabel adiDuzenleLabel = new JLabel("Yeni Ürün Adı:", JLabel.RIGHT);
		ustPanel.add(adiDuzenleLabel);
		JTextField adiDuzenleField = new JTextField(20);
		ustPanel.add(adiDuzenleField);
		JLabel duzenleFiyatLabel = new JLabel("Yeni Ürün Fiyatı:", JLabel.RIGHT);
		ustPanel.add(duzenleFiyatLabel);
		JTextField duzenleFiyatField = new JTextField(20);
		ustPanel.add(duzenleFiyatField);
		JLabel urunAraLabel = new JLabel("Ürün Adına Göre Ara:", JLabel.RIGHT);
		ustPanel.add(urunAraLabel);
		JTextField urunAraField = new JTextField(10);
		ustPanel.add(urunAraField);

		JButton urunGuncelleButton = new JButton("Güncelle");
		ustPanel.add(urunGuncelleButton);
		JButton urunİptalButton = new JButton("İptal");
		ustPanel.add(urunİptalButton);

		JList urunList = new JList();
		urunList.setListData(new UrunlerDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(urunList);
		pane.setBorder(BorderFactory.createTitledBorder("Ürün Listesi"));
		urunList.setSelectedIndex(0);

		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);

		urunGuncelleButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Seçilen ürün bilgisini al
		        Object selectedObject = urunList.getSelectedValue();

		        if (selectedObject instanceof UrunlerContract) {
		            UrunlerContract selectedUrun = (UrunlerContract) selectedObject;

		            // Yeni ad ve fiyatı al
		            String yeniAd = adiDuzenleField.getText();
		            String yeniFiyat = duzenleFiyatField.getText();

		            // Yeni ad ve fiyat boş değilse ve eski ad veya fiyat ile farklı ise güncelleme yap
		            if (!yeniAd.isEmpty() && !yeniAd.equals(selectedUrun.getAdi())) {
		                selectedUrun.setAdi(yeniAd);
		            }

		            if (!yeniFiyat.isEmpty() && !yeniFiyat.equals(selectedUrun.getFiyat())) {
		            	selectedUrun.setFiyat(Float.parseFloat(yeniFiyat));
		            }

		            // Güncelleme yap
		            new UrunlerDAL().Update(selectedUrun);
		            JOptionPane.showMessageDialog(null, selectedUrun.getAdi() + " adlı ürün güncellenmiştir.");
		        } else {
		            JOptionPane.showMessageDialog(null, "Lütfen bir ürün seçin.");
		        }	
		    }
		});

		urunList.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting() == false) {
		            Object selectedObject = urunList.getSelectedValue();

		            if (selectedObject instanceof UrunlerContract) {
		                UrunlerContract selectedUrun = (UrunlerContract) selectedObject;

		                // Seçilen ürünün adını ve fiyatını ilgili alanlara yazdır
		                adiDuzenleField.setText(selectedUrun.getAdi());
		                duzenleFiyatField.setText(String.valueOf(selectedUrun.getFiyat()));

		            }
		        }
		    }
		});



		urunİptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		urunAraField.addKeyListener(new KeyListener() {
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
		        String arananKelime = urunAraField.getText().trim();
		        
		        // UrunlerDAL sınıfında GetSearchUrun metodu ile arama yapılır
		        List<UrunlerContract> aramaSonucu = new UrunlerDAL().GetSearchUrun(arananKelime);
		        
		        // Arama sonuçlarını urunList'e set edilir
		        urunList.setListData(aramaSonucu.toArray());
		        
		        if (!aramaSonucu.isEmpty()) {
		            // Eğer arama sonuçları varsa, ilk ürünü seçili hale getir ve bilgileri göster
		            urunList.setSelectedIndex(0);
		            UrunlerContract selectedUrun = aramaSonucu.get(0);
		            adiDuzenleField.setText(selectedUrun.getAdi());
		            duzenleFiyatField.setText(String.valueOf(selectedUrun.getFiyat()));
		        } else {
		            // Eğer arama sonuçları yoksa, alanları temizle
		            adiDuzenleField.setText("");
		            duzenleFiyatField.setText("");
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
