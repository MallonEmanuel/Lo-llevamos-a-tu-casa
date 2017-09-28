package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.Shipment;

public interface ShipmentDao extends GenericDao<Shipment, Long> {
	public List<Shipment> findAllWithOutDeliver();
}
