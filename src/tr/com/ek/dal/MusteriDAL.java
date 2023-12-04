package tr.com.ek.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.ek.core.ObjectHelper;
import tr.com.ek.interfaces.DALInterfaces;
import tr.com.ek.types.MusteriContract;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.UrunlerContract;

public class MusteriDAL extends ObjectHelper implements DALInterfaces<MusteriContract> {

	@Override
	public void Insert(MusteriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Musteri (AdiSoyadi, Telefon, Adres) VALUES('" + entity.getAdiSoyadi()
					+ "','" + entity.getTelefon() + "', '" + entity.getAdres() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<MusteriContract> GetAll() {
		List<MusteriContract> datacontract = new ArrayList<MusteriContract>();
		Connection connection = getConnection();
		MusteriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Musteri");
			while (resultSet.next()) {
				contract = new MusteriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				contract.setTelefon(resultSet.getString("Telefon"));
				contract.setAdres(resultSet.getString("Adres"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	public List<MusteriContract> GetSearchMusteri(String musteriAdi) {

		List<MusteriContract> datacontract = new ArrayList<MusteriContract>();
		Connection connection = getConnection();
		MusteriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Musteri Where AdiSoyadi Like '" + "%" + musteriAdi + "%" + "' ");
			while (resultSet.next()) {
				contract = new MusteriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				contract.setTelefon(resultSet.getString("Telefon"));
				contract.setAdres(resultSet.getString("Adres"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public MusteriContract Delete(MusteriContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(MusteriContract Entity) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE Musteri SET AdiSoyadi = ?, Telefon = ?, Adres = ? WHERE Id = ?")) {

			preparedStatement.setString(1, Entity.getAdiSoyadi());
			preparedStatement.setString(2, Entity.getTelefon());
			preparedStatement.setString(3, Entity.getAdres());
			preparedStatement.setInt(4, Entity.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<MusteriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
