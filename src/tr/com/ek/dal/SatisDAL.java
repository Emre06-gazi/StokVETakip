package tr.com.ek.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.ek.complex.types.SatisContractComplex;
import tr.com.ek.complex.types.StokContractTotalComplex;
import tr.com.ek.core.ObjectHelper;
import tr.com.ek.interfaces.DALInterfaces;
import tr.com.ek.types.SatisContract;

public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisContract>{

	@Override
	public void Insert(SatisContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Satis (PersonelId, UrunId,Tarih, Adet, MusteriId) VALUES(" + entity.getPersonelId()
							+ ", " + entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ", " + entity.getMusteriId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<SatisContractComplex> GetAllSatis() {
		List<SatisContractComplex> datacontract = new ArrayList<SatisContractComplex>();
		Connection connection = getConnection();
		SatisContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT Satis.Adet as Adet, satis.Id, personel.AdiSoyadi as personelAdSoyad, musteri.AdiSoyadi as musteriAdSoyad, Adi, Adet, satis.Tarih FROM satis "
					+ "LEFT JOIN urunler on satis.UrunId = urunler.Id "
					+ "LEFT JOIN personel on satis.PersonelId = personel.Id "
					+ "LEFT JOIN musteri on satis.MusteriId = musteri.Id");
			while (resultSet.next()) {
				contract = new SatisContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("personelAdSoyad"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setTarih(resultSet.getString("satis.Tarih"));
				contract.setToplamAdet(resultSet.getInt("Adet"));
				contract.setMusteriAdi(resultSet.getString("musteriAdSoyad"));
				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}
	
	@Override
	public List<SatisContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisContract Delete(SatisContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SatisContract entity) {
	    Connection connection = getConnection();
	    try {
	        Statement statement = connection.createStatement();

	        statement.executeUpdate("UPDATE Satis SET " +
	                "PersonelId = " + entity.getPersonelId() + ", " +
	                "UrunId = " + entity.getUrunId() + ", " +
	                "MusteriId = " + entity.getMusteriId() + ", " +
	                "Tarih = '" + entity.getTarih() + "', " +
	                "Adet = " + entity.getAdet() + " " +
	                "WHERE Id = " + entity.getId());

	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void Delete(int satisId) {
	    Connection connection = getConnection();
	    try {
	        Statement statement = connection.createStatement();
	        statement.executeUpdate("DELETE FROM Satis WHERE Id = " + satisId);
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
