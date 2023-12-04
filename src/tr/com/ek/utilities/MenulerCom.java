package tr.com.ek.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import tr.com.ek.dal.AccountDAL;
import tr.com.ek.fe.KategoriDuzenleFE;
import tr.com.ek.fe.KategoriEkleFE;
import tr.com.ek.fe.LoginFe;
import tr.com.ek.fe.MusteriDuzenleFE;
import tr.com.ek.fe.MusteriEkleFE;
import tr.com.ek.fe.PersonelDuzenleFE;
import tr.com.ek.fe.PersonelEkleFE;
import tr.com.ek.fe.SifreBelirleFE;
import tr.com.ek.fe.SifreDuzenleFE;
import tr.com.ek.fe.UrunDuzenleFE;
import tr.com.ek.fe.UrunEkleFE;
import tr.com.ek.fe.YetkiDuzenleFE;
import tr.com.ek.fe.YetkiEkleFE;
import tr.com.ek.types.PersonelContract;

public class MenulerCom {

	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cikisItem = new JMenuItem("Çıkış");
		dosyaMenu.add(cikisItem);

		/* Ürün Bar */
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem); // Bar değil urunlerMenu'ye ekledik.
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator(); // Menülerde bir ayırıcı çizgi (separator) eklemek için kullanılır.
		JMenuItem urunDuzenleItem = new JMenuItem("Ürün Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategori Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);

		/* Personel Bar */
		JMenu personellerMenu = new JMenu("Personeller");
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem); // Bar değil personelMenu'ye ekledik.
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem = new JMenuItem("Şifre Belirleme");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator(); // Menülerde bir ayırıcı çizgi (separator) eklemek için kullanılır.
		JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
		personellerMenu.add(personelDuzenleItem);
		JMenuItem yetkiDuzenle = new JMenuItem("Yetki Düzenle");
		personellerMenu.add(yetkiDuzenle);
		JMenuItem sifreDuzenleme = new JMenuItem("Kullanıcı Şifre ve Yetkisi Düzenle");
		personellerMenu.add(sifreDuzenleme);

		/* Musteri Bar */
		JMenu musteriMenu = new JMenu("Müşteriler");
		bar.add(musteriMenu);
		JMenuItem musteriEkleItem = new JMenuItem("Müşteri Ekle");
		musteriMenu.add(musteriEkleItem);
		musteriMenu.addSeparator(); // Menülerde bir ayırıcı çizgi (separator) eklemek için kullanılır.
		JMenuItem musteriDuzenleItem = new JMenuItem("Müşteri Düzenle");
		musteriMenu.add(musteriDuzenleItem);

		/* YETKİ İŞLEMLERİ */
		PersonelContract contract = (PersonelContract) LoginFe.emailBox.getSelectedItem();

		if (new AccountDAL().GetYetkiId(contract.getId()).getYetkiId() == 2) {
			personellerMenu.hide();// Burada yetkiId'si 2 olan kullancının personeller menüyü görmesini "hide"
									// metodu ile engelledik.
		} else if (new AccountDAL().GetYetkiId(contract.getId()).getYetkiId() == 3) {
			musteriMenu.hide();
			personellerMenu.hide();
			urunlerMenu.hide();
		}
		
		cikisItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
			
		});

		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new UrunEkleFE();
					}
				});
			}
		});
		
		urunDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UrunDuzenleFE();
			}

		});
		
		kategoriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFE();
			}

		});

		kategoriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriDuzenleFE();
			}

		});

		personelEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new PersonelEkleFE();
					}
				});
			}
		});
		
		personelDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new PersonelDuzenleFE();
					}
				});
			}
		});
		
		yetkiEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new YetkiEkleFE();
					}
				});
			}
		});
		
		yetkiDuzenle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new YetkiDuzenleFE();
					}
				});
			}
		});

		sifreBelirleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SifreBelirleFE();
			}

		});
		
		sifreDuzenleme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SifreDuzenleFE();
			}

		});
		
		musteriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new MusteriEkleFE();
					}
				});
			}
		});
		
		musteriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new MusteriDuzenleFE();
					}
				});
			}
		});

		return bar;
	}

}
