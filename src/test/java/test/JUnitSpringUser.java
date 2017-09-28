package test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import unpsjb.labprog.tp2.springmvc.dao.UserDaoOld;
import unpsjb.labprog.tp2.springmvc.model.AppUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class JUnitSpringUser {

	@Autowired
	private UserDaoOld userDao;
	
	@BeforeClass
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	/**
	 * Before se ejecuta antes de cada Test
	 */
	@Before
	public void before(){
		userDao.saveUser(new AppUser("Emanuel"));
		userDao.saveUser(new AppUser("Juan"));
		userDao.saveUser(new AppUser("Carlos"));
		userDao.saveUser(new AppUser("Cristian"));
	}
	/**
	 * After se ejecuta despues de cada Test
	 */
	@After
	public void after(){
		userDao.deleteAllUsers();
	}
	
	
	@Test
	public void testFindAllUser() {
		assertEquals(4,userDao.findAllUsers().size());
	}

	@Test
	public void testFindByName() {
		assertEquals("Juan",userDao.findByName("Juan").getUsername());
	}

	@Test
	public void testFindById() {
		AppUser user = userDao.findByName("Cristian");
		long id = (long) user.getId();
		assertEquals("Cristian",userDao.findById(id).getUsername());
	}
	
	@Test
	public void testDeleteUserById() {
		AppUser user=userDao.findByName("Carlos");
		userDao.deleteUserById(user.getId());
		assertFalse(userDao.isUserExist(user));
	}
	
	@Test
	public void testUpdateUser(){
		AppUser user = userDao.findByName("Cristian");
		user.setUsername("Cristina");
		userDao.updateUser(user);
		assertEquals("Cristina",userDao.findByName("Cristina").getUsername());
	}

	@AfterClass
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}