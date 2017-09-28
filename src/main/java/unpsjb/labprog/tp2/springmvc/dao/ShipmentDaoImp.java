package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.Shipment;

@Repository
public class ShipmentDaoImp extends GenericDaoSpringHibernateTemplate<Shipment, Long> implements ShipmentDao {

	public ShipmentDaoImp() {
		super(Shipment.class);
	}
	@Override
	public List<Shipment> findAllWithOutDeliver(){
		String[] params = {};
		Object[] values = {};
		return super.findByNameQuery("Shipment.findAllWithOutDeliver", params, values);				
	}
	
}
