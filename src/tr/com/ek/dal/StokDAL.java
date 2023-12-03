package tr.com.ek.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.ek.complex.types.StokContractComplex;
import tr.com.ek.complex.types.StokContractTotalComplex;
import tr.com.ek.core.ObjectHelper;
import tr.com.ek.interfaces.DALInterfaces;
import tr.com.ek.types.KategoriContract;
import tr.com.ek.types.StokContract;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Stok (PersonelId, UrunId,Tarih, Adet) VALUES(" + entity.getPersonalId()
							+ ", " + entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<StokContractComplex> GetAllStok() {
		
		List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM Stok "
					+ "LEFT JOIN urunler on stok.UrunId = urunler.Id "
					+ "LEFT JOIN personel on stok.PersonelId = personel.Id");
			while(resultSet.next()) {
				contract = new StokContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("Tarih"));
				
				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}
	
	 public StokContract GetByUrunId(int urunId) {
	        Connection connection = getConnection();
	        StokContract stokContract = null;
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(
	                    "SELECT * FROM Stok WHERE UrunId = " + urunId);

	            if (resultSet.next()) {
	                stokContract = new StokContract();
	                stokContract.setId(resultSet.getInt("Id"));
	                stokContract.setPersonalId(resultSet.getInt("PersonelId"));
	                stokContract.setUrunId(resultSet.getInt("UrunId"));
	                stokContract.setTarih(resultSet.getString("Tarih"));
	                stokContract.setAdet(resultSet.getInt("Adet"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return stokContract;
	    }
	
	public List<StokContractTotalComplex> GetTotalStok() {

		List<StokContractTotalComplex> datacontract = new ArrayList<StokContractTotalComplex>();
		Connection connection = getConnection();
		StokContractTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(Adet) as toplam, stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM stok "
					+ "LEFT JOIN urunler on stok.UrunId = urunler.Id"
					+ " LEFT JOIN personel on stok.PersonelId = personel.Id "
					+ "GROUP BY UrunId ORDER BY toplam desc");
			while (resultSet.next()) {
				contract = new StokContractTotalComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setTarih(resultSet.getString("stok.Tarih"));
				contract.setAdet(resultSet.getInt("toplam"));
				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}


	
	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract Entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
