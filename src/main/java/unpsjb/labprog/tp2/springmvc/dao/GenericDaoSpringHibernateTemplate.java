package unpsjb.labprog.tp2.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
public class GenericDaoSpringHibernateTemplate<E, K extends Serializable> implements GenericDao<E, K> {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	protected Class<E> type;

	public GenericDaoSpringHibernateTemplate(Class<E> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public K save(E entity) {
		return (K) hibernateTemplate.save(entity);
	}
	// SELECT * FROM pg_stat_activity;

	@Transactional
	@Override
	public void update(E entity) {
		hibernateTemplate.merge(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public E find(K key) {
		List<E> entities = (List<E>) hibernateTemplate
				.find("select e from " + type.getSimpleName() + " e where e.id=" + key);
		return !entities.isEmpty() ? entities.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		return (List<E>) hibernateTemplate.find("from " + type.getName());
	}

	@Transactional
	@Override
	public void delete(E entity) {
		hibernateTemplate.delete(entity);
	}

	@Transactional
	@Override
	public void deleteAll() {
		hibernateTemplate.bulkUpdate("delete from " + type.getSimpleName());
	}

	@Override
	public boolean exists(E entity) {
		List<E> found = (List<E>) hibernateTemplate.findByExample(entity);
		return found != null && !found.isEmpty();
	}

	@Override
	public int count() {
		return findAll().size();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNameQuery(String nameQuery, String[] params, Object[] name) {
		return (List<E>) hibernateTemplate.findByNamedQueryAndNamedParam(nameQuery, params, name);
	}

	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate
	 *            the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * @return the type
	 */
	public Class<E> getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Class<E> type) {
		this.type = type;
	}

	// Testing

	@Value("${hibernate.jdbc.batch_size}")
	private int batchSize;
//
//	@SuppressWarnings("unchecked")
//	@Transactional
//	@Override
//	public List<E> bulkSave(List<E> entities) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//
//		final List<E> savedEntities = new ArrayList<E>(entities.size());
//		int i = 0;
//		for (E e : entities) {
//			savedEntities.add((E) session.save(e));
//			i++;
//			if (i % batchSize == 0) {
//				// Flush a batch of inserts and release memory.
//				session.flush();
//				session.clear();
//			}
//		}
//		transaction.commit();
//		return savedEntities;
//	}

	
	@Transactional
	@Override
	public boolean bulk(List<E> entities) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		int i = 0;
		for (E e : entities) {
			session.saveOrUpdate(e);
			i++;
			if (i % batchSize == 0) {
				// Flush a batch of inserts and release memory.
				session.flush();
				session.clear();
			}
		}
		transaction.commit();
		return true;
	}
	
}
