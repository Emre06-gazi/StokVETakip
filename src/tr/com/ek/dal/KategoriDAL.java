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
import tr.com.ek.types.KategoriContract;

public class KategoriDAL extends ObjectHelper implements DALInterfaces<KategoriContract> {

	@Override
	public void Insert(KategoriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Kategori (Adi, ParentId) VALUES('" + entity.getAdi() + "',"
					+ entity.getParentId() + ")"); // Burada "" içinde '' kulanımı değer string oldugu için, ""
													// kullanımı ise değer integer oldugu ıcın
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetAll() {

		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	public List<KategoriContract> GetAllParentId() {

		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori Where parentId = 0");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	public List<KategoriContract> GetSearchKategori(String kategoriAdi) {

		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Kategori Where Adi Like '" + "%" + kategoriAdi + "%" + "' ");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public KategoriContract Delete(KategoriContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(KategoriContract Entity) {
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "UPDATE Kategori SET Adi = ?, ParentId = ? WHERE Id = ?")) {

	        preparedStatement.setString(1, Entity.getAdi());
	        preparedStatement.setInt(2, Entity.getParentId());
	        preparedStatement.setInt(3, Entity.getId());

	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public List<KategoriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
