package tr.com.ek.dal;

import tr.com.ek.interfaces.DALInterfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tr.com.ek.core.ObjectHelper;
import tr.com.ek.types.UrunlerContract;

public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerContract>{

	@Override
	public void Insert(UrunlerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler (Adi, KategoriId, Tarih, Fiyat) Values ('"+ entity.getAdi() +"', "+ entity.getId() +",'"+ entity.getTarih()+ "', "+ entity.getFiyat()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UrunlerContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrunlerContract Delete(UrunlerContract Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(UrunlerContract Entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
