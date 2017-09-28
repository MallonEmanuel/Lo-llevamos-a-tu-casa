package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

/**
 *Esta interface define los metodos para realizar operaciones CRUD genericas. 
 * @author emanuel
 *
 * @param <E> Entity
 * @param <K> Key
 */
public interface GenericDao <E,K> {
	
	public K save(E entity);
	
	void update(E entity);
	
	public E find(K key);
	
	public List<E> findAll(); 
		
	public void delete(E entity);

	public void deleteAll();
		
	public boolean exists(E entity);

	public int count();
	
	public boolean bulk(List<E> entities);
}