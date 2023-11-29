package tr.com.ek.interfaces;

import java.util.List;

public interface DALInterfaces<T> {
	 void Insert(T entity);
	 List<T> GetAll();
	 T Delete(T Entity);
	 void Update(T Entity);
	 List<T> GetById(int id);
}
