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
import tr.com.ek.types.AccountsContract;
import tr.com.ek.types.MusteriContract;

public class AccountDAL extends ObjectHelper implements DALInterfaces<AccountsContract> {

	@Override
	public void Insert(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Accounts (YetkiId, PersonelId, Sifre) VALUES(" + entity.getYetkiId()
					+ "," + entity.getPersonelId() + ",'" + entity.getSifre() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public AccountsContract GetPersonelIdVeSifre(int personelId, String sifre) {

		AccountsContract contract = new AccountsContract();
		List<AccountsContract> listele = new ArrayList<AccountsContract>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery(
					"SELECT * From Accounts WHERE PersonelId = " + personelId + " AND Sifre = '" + sifre.trim() + "' ");

			/*
			 * "SELECT p.Email, y.Adi, a.Sifre From Accounts a " +
			 * "LEFT JOIN personel p on a.PersonelId = p.Id " +
			 * "LEFT JOIN yetkiler y on a.YetkiId = y.Id " + "WHERE a.PersonelId = " +
			 * personelId + " AND a.Sifre = '" + sifre.trim() + "' "
			 */

			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return contract;
	}

	public AccountsContract GetYetkiId(int personelId) {

		AccountsContract contract = new AccountsContract();
		List<AccountsContract> listele = new ArrayList<AccountsContract>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * From Accounts WHERE PersonelId = " + personelId + " ");

			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return contract;
	}

	@Override
	public List<AccountsContract> GetAll() {
		List<AccountsContract> datacontract = new ArrayList<AccountsContract>();
		Connection connection = getConnection();
		AccountsContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Accounts");
			while (resultSet.next()) {
				contract = new AccountsContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelId(resultSet.getInt("PersonelId"));
				contract.setYetkiId(resultSet.getInt("YetkiId"));
				contract.setSifre(resultSet.getString("Sifre"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}
	
	public List<AccountsContract> GetSearchMusteri(String personelId) {

		List<AccountsContract> datacontract = new ArrayList<AccountsContract>();
		Connection connection = getConnection();
		AccountsContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Accounts Where PersonelId Like '" + "%" + personelId + "%" + "' ");
			while (resultSet.next()) {
				contract = new AccountsContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelId(resultSet.getInt("PersonelId"));
				contract.setYetkiId(resultSet.getInt("YetkiId"));
				contract.setSifre(resultSet.getString("Sifre"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}
	
	@Override
	public AccountsContract Delete(AccountsContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsContract Entity) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE Accounts SET YetkiId = ?, Sifre = ? WHERE Id = ?")) {

			preparedStatement.setInt(1, Entity.getYetkiId());
			preparedStatement.setString(2, Entity.getSifre());
			preparedStatement.setInt(3, Entity.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<AccountsContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
