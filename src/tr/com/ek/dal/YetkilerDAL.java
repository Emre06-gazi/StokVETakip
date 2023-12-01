package tr.com.ek.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.ek.core.ObjectHelper;
import tr.com.ek.interfaces.DALInterfaces;
import tr.com.ek.types.PersonelContract;
import tr.com.ek.types.Yetkiler;

public class YetkilerDAL extends ObjectHelper implements DALInterfaces<Yetkiler>{

	@Override
	public void Insert(Yetkiler entity) { 
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES('"+entity.getAdi()+"')"); 
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Yetkiler> GetAll() {
		List<Yetkiler> datacontract = new ArrayList<Yetkiler>();
		Connection connection = getConnection();
		Yetkiler contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Yetkiler");
			while(resultSet.next()) {
				contract = new Yetkiler();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				
				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public Yetkiler Delete(Yetkiler Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Yetkiler Entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Yetkiler> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
