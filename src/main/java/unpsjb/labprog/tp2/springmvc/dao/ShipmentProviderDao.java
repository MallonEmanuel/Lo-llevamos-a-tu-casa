package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.ShipmentProvider;
import unpsjb.labprog.tp2.springmvc.util.FilterProvider;

public interface ShipmentProviderDao extends GenericDao<ShipmentProvider, Long> {

	public List<ShipmentProvider> filter(FilterProvider filterProvider);

}
