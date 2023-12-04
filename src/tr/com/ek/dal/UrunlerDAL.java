package tr.com.ek.dal;

import tr.com.ek.interfaces.DALInterfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.ek.core.ObjectHelper;
import tr.com.ek.types.KategoriContract;
import tr.com.ek.types.UrunlerContract;

public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler (Adi, KategoriId, Tarih, Fiyat) Values ('" + entity.getAdi()
					+ "', " + entity.getId() + ",'" + entity.getTarih() + "', " + entity.getFiyat() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<UrunlerContract> GetAll() {
		List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
			while (resultSet.next()) {
				contract = new UrunlerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setKategoriId(resultSet.getInt("KategoriId"));
				contract.setTarih(resultSet.getString("Tarih"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public UrunlerContract Delete(UrunlerContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(UrunlerContract Entity) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE Urunler SET Adi = ?, Fiyat = ? WHERE Id = ?")) {

			preparedStatement.setString(1, Entity.getAdi());
			preparedStatement.setFloat(2, Entity.getFiyat());
			preparedStatement.setInt(3, Entity.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public List<UrunlerContract> GetSearchUrun(String urunAdi) {

		List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Urunler Where Adi Like '" + "%" + urunAdi + "%" + "' ");
			while (resultSet.next()) {
				contract = new UrunlerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setFiyat(resultSet.getFloat("Fiyat"));;

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}
	
	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
