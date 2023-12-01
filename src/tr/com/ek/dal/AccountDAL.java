package tr.com.ek.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tr.com.ek.core.ObjectHelper;
import tr.com.ek.interfaces.DALInterfaces;
import tr.com.ek.types.AccountsContract;

public class AccountDAL extends ObjectHelper implements DALInterfaces<AccountsContract>{

	@Override
	public void Insert(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("INSERT INTO Accounts (YetkiId, PersonelId, Sifre) VALUES("+entity.getYetkiId()+","+entity.getPersonelId()+",'"+entity.getSifre()+"')"); 
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<AccountsContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountsContract Delete(AccountsContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsContract Entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountsContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
