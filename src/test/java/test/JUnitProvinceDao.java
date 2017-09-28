package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import unpsjb.labprog.tp2.springmvc.dao.CountryDao;
import unpsjb.labprog.tp2.springmvc.dao.ProvinceDao;
import unpsjb.labprog.tp2.springmvc.model.Country;
import unpsjb.labprog.tp2.springmvc.model.Province;

public class JUnitProvinceDao extends JUnitDao {

	@Autowired
	protected ProvinceDao provinceDao;
	@Autowired
	protected CountryDao countryDao;
	
	protected int provinceCount;

	private String CHUBUT = "CHUBUT_";
	private String MENDOZA = "MENDOZA_";
	private String MENDOZA2 = "MENDOZA_2";
	private String ARGENTINA = "ARGENTINA_";
	
	@Before
	public void before() {
		provinceCount = provinceDao.count();
		countryDao.save(new Country(ARGENTINA));
		Country country= countryDao.findByName(ARGENTINA);
		provinceDao.save(new Province(CHUBUT,country));
		provinceDao.save(new Province(MENDOZA,country));
	}

	@After
	public void after() {
		provinceDao.deleteIfExists(CHUBUT);
		provinceDao.deleteIfExists(MENDOZA);
		provinceDao.deleteIfExists(MENDOZA2);
		countryDao.deleteIfExists(ARGENTINA);
	}

	
	@Test
	public void testFindByName() {
		assertTrue(provinceDao.findByName(CHUBUT).getName().equals(CHUBUT));
	}
	@Test
	public void testSave() {
		Province province = provinceDao.findByName(CHUBUT);
		assertTrue(provinceDao.exists(province));
	}

	@Test
	public void testDelete() {
		Province province = provinceDao.findByName(MENDOZA);
		provinceDao.delete(province);
		assertNull(provinceDao.findByName(MENDOZA));
	}

	@Test
	public void testUpdate() {
		Province province = provinceDao.findByName(MENDOZA);
		long id = province.getId();
		province.setName(String.valueOf(MENDOZA2));
		provinceDao.update(province);
		assertTrue(provinceDao.find(id).getName().equals(MENDOZA2));
	}

	@Test
	public void testFindAllUser() {
		assertEquals(provinceCount + 2, provinceDao.findAll().size());
	}

}
