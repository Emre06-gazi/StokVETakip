package tr.com.ek.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tr.com.ek.core.ObjectHelper;
import tr.com.ek.interfaces.DALInterfaces;
import tr.com.ek.types.MusteriContract;

public class MusteriDAL extends ObjectHelper implements DALInterfaces<MusteriContract>{

	@Override
	public void Insert(MusteriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("INSERT INTO Musteri (AdiSoyadi, Telefon, Adres	, SehirId) VALUES('"+entity.getAdiSoyadi()+"','"+entity.getTelefon()+"', '"+entity.getAdres()+"', "+entity.getSehirId()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MusteriContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MusteriContract Delete(MusteriContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(MusteriContract Entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MusteriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
