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
import unpsjb.labprog.tp2.springmvc.dao.CustomerDao;
import unpsjb.labprog.tp2.springmvc.dao.ProvinceDao;
import unpsjb.labprog.tp2.springmvc.model.Address;
import unpsjb.labprog.tp2.springmvc.model.City;
import unpsjb.labprog.tp2.springmvc.model.Country;
import unpsjb.labprog.tp2.springmvc.model.Customer;
import unpsjb.labprog.tp2.springmvc.model.Province;

public class JUnitCustomerDao extends JUnitDao {
	@Autowired
	protected CityDao cityDao;
	@Autowired
	protected ProvinceDao provinceDao;
	@Autowired
	protected CountryDao countryDao;
	

//	@Autowired
//	protected AddressDao addressDao; 
	@Autowired
	protected CustomerDao customerDao;
	
//	"30-\d{8}-\d"
	protected int customerCount;

	private String CHUBUT = "CHUBUT_";
	private String PUERTO_MADRYN = "PUERTO_MADRYN_";
	private String TRELEW = "TRELEW_";
	private String ARGENTINA = "ARGENTINA_";

	private String JORGE = "JORGE_";
	private String CUIT_JORGE = "XX.22222222.X";
	private String CUIT_JORGE_2 = "XX.3333333.X";
	
	private String EMANUEL = "EMANUEL_";	
	private String CUIT_EMANUEL = "XX.37730871.X";
	private String EMANUEL_DIR = "ALEM_3100";
	@SuppressWarnings("unused")
	private String EMANUEL_DIR_2 = "ESPAÑA_1000";
//	private String EMANUEL_DIR_3 = "VILLARINO_850";
	
	
	
	
	@Before
	public void before() {
		customerCount = customerDao.count();
//		Creando Pais
		countryDao.save(new Country(ARGENTINA));
		Country country= countryDao.findByName(ARGENTINA);
//		Creando Provincia
		provinceDao.save(new Province(CHUBUT,country));
		Province province = provinceDao.findByName(CHUBUT);
//		Creando Ciudades
		cityDao.save(new City(PUERTO_MADRYN, province));
		cityDao.save(new City(TRELEW, province));
//		Creando clientes
		customerDao.save(new Customer(EMANUEL,"", CUIT_EMANUEL));
		customerDao.save(new Customer(JORGE,"", CUIT_JORGE));
	}

	@After
	public void after() {
		customerDao.deleteIfExists(EMANUEL);
		customerDao.deleteIfExists(JORGE);
//		Borrando ciudades
		cityDao.deleteIfExists(PUERTO_MADRYN);
		cityDao.deleteIfExists(TRELEW);
//		Borrando Provincia
		provinceDao.deleteIfExists(CHUBUT);
//		Borrando Pais
		countryDao.deleteIfExists(ARGENTINA);
//		Borrando clientes
	}
	@Test
	public void testFindByName() {
		assertTrue(customerDao.findByName(EMANUEL).getName().equals(EMANUEL));
	}
	
	@Test
	public void testSave() {
		Customer customer = customerDao.findByName(EMANUEL);
		assertTrue(customerDao.exists(customer));
	}

	@Test
	public void testDelete() {
		Customer customer = customerDao.findByName(JORGE);
		customerDao.delete(customer);
		assertNull(customerDao.findByName(JORGE));
	}

	@Test
	public void testUpdate() {
		Customer customer = customerDao.findByName(JORGE);
		long id = customer.getId();
		customer.setCuit(CUIT_JORGE_2);
		customerDao.update(customer);
		assertTrue(customerDao.find(id).getCuit().equals(CUIT_JORGE_2));
	}

	@Test
	public void testFindAllUser() {
		assertEquals(customerCount + 2, customerDao.findAll().size());
	}
	
	@Test
	public void testAddAddress() {
		City city = cityDao.findByName(PUERTO_MADRYN);
		Customer customer = customerDao.findByName(EMANUEL);
		customer.getAddress().add(new Address(EMANUEL_DIR, city));
		customerDao.update(customer);
		assertEquals(1,customerDao.findByName(EMANUEL).getAddress().size());
		
	}

}
