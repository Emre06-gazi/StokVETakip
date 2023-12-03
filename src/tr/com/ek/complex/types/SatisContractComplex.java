package tr.com.ek.complex.types;

import java.sql.Date;

public class SatisContractComplex {

	private int id;
	private String musteriAdi;
	private String personelAdi;
	private String urunAdi;
	private String tarih;
	private int toplamAdet;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMusteriAdi() {
		return musteriAdi;
	}

	public void setMusteriAdi(String musteriAdi) {
		this.musteriAdi = musteriAdi;
	}

	public String getPersonelAdi() {
		return personelAdi;
	}

	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}

	public int getToplamAdet() {
		return toplamAdet;
	}

	public void setToplamAdet(int toplamAdet) {
		this.toplamAdet = toplamAdet;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	
	public Object[] getVeriler() {
		Object[] veriler = { id, musteriAdi, personelAdi, urunAdi, tarih, toplamAdet};
		return veriler;
	}
	
	@Override
	public String toString() {
		return musteriAdi + " " + personelAdi + " " + urunAdi;

	}
}
