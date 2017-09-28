package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import unpsjb.labprog.tp2.springmvc.dao.CountryDao;
import unpsjb.labprog.tp2.springmvc.model.Country;

public class JUnitCountryDao extends JUnitDao {

	@Autowired
	protected CountryDao countryDao;
	protected int countryCount;

	private String MEXICO = "MEXICO_";
	private String JAPON = "JAPON_";
	private String JAPON2 = "JAPON_2";
	@Before
	public void before() {
		countryCount = countryDao.count();
		countryDao.save(new Country(MEXICO));
		countryDao.save(new Country(JAPON));
	}

	@After
	public void after() {
		countryDao.deleteIfExists(MEXICO);
		countryDao.deleteIfExists(JAPON);
		countryDao.deleteIfExists(JAPON2);
	}

	@Test
	public void testFindByName() {
		assertTrue(countryDao.findByName(MEXICO).getName().equals(MEXICO));
	}

	@Test
	public void testSave() {
		Country country = countryDao.findByName(MEXICO);
		assertTrue(countryDao.exists(country));
	}

	@Test
	public void testDelete() {
		Country country = countryDao.findByName(JAPON);
		countryDao.delete(country);
		assertNull(countryDao.findByName(JAPON));
	}

	@Test
	public void testUpdate() {
		Country country = countryDao.findByName(JAPON);
		long id = country.getId();
		country.setName(String.valueOf(JAPON2));
		countryDao.update(country);
		assertTrue(countryDao.find(id).getName().equals(JAPON2));
	}

	@Test
	public void testFindAllUser() {
		assertEquals(countryCount + 2, countryDao.findAll().size());
	}

}
