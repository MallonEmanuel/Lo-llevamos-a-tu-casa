package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import unpsjb.labprog.tp2.springmvc.dao.CityDao;
import unpsjb.labprog.tp2.springmvc.dao.CountryDao;
import unpsjb.labprog.tp2.springmvc.dao.ProvinceDao;
import unpsjb.labprog.tp2.springmvc.model.City;
import unpsjb.labprog.tp2.springmvc.model.Country;
import unpsjb.labprog.tp2.springmvc.model.Province;

public class JUnitCityDao extends JUnitDao {

	@Autowired
	protected CityDao cityDao;
	@Autowired
	protected ProvinceDao provinceDao;
	@Autowired
	protected CountryDao countryDao;
	
	protected int cityCount;

	private String CHUBUT = "CHUBUT_";
	private String PUERTO_MADRYN = "PUERTO_MADRYN_";
	private String TRELEW = "TRELEW_";
	private String TRELEW2 = "TRELEW_2";
	private String ARGENTINA = "ARGENTINA_";
	
	@Before
	public void before() {
		cityCount = cityDao.count();
		countryDao.save(new Country(ARGENTINA));
		Country country= countryDao.findByName(ARGENTINA);
		provinceDao.save(new Province(CHUBUT,country));
		Province province = provinceDao.findByName(CHUBUT);
		cityDao.save(new City(PUERTO_MADRYN, province));
		cityDao.save(new City(TRELEW, province));
	}

	@After
	public void after() {
		cityDao.deleteIfExists(PUERTO_MADRYN);
		cityDao.deleteIfExists(TRELEW);
		cityDao.deleteIfExists(TRELEW2);
		provinceDao.deleteIfExists(CHUBUT);
		countryDao.deleteIfExists(ARGENTINA);
	}

	
	@Test
	public void testFindByName() {
		assertTrue(cityDao.findByName(PUERTO_MADRYN).getName().equals(PUERTO_MADRYN));
	}
	@Test
	public void testSave() {
		City city = cityDao.findByName(PUERTO_MADRYN);
		assertTrue(cityDao.exists(city));
	}

	@Test
	public void testDelete() {
		City city = cityDao.findByName(TRELEW);
		cityDao.delete(city);
		assertNull(cityDao.findByName(TRELEW));
	}

	@Test
	public void testUpdate() {
		City city = cityDao.findByName(TRELEW);
		long id = city.getId();
		city.setName(String.valueOf(TRELEW2));
		cityDao.update(city);
		assertTrue(cityDao.find(id).getName().equals(TRELEW2));
	}

	@Test
	public void testFindAllUser() {
		assertEquals(cityCount + 2, cityDao.findAll().size());
	}

}
