package net.visitha.contact.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.visitha.contact.model.Contact;

class ContactDAOTest {

	private DriverManagerDataSource datasource;

	private ContactDAO dao;

	@BeforeEach
	void setupBeforeEach() {
		datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb?useSSL=false");
		datasource.setUsername("root");
		datasource.setPassword("root");

		dao = new ContactDAOImpl(datasource);
	}

	@Test
	void testSave() {

		Contact contact = new Contact();
		int result = dao.save(contact);

		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Contact contact = new Contact();
		int result = dao.update(contact);

		assertTrue(result > 0);
	}

	@Test
	void testDelete() {
		Integer id = 2;
		
		int result = dao.delete(id);
		
		assertTrue(result > 0);
		}

	@Test
	void testGet() {
		Integer id = 2;
		Contact contact = dao.get(id);

		if (contact != null) {
			System.out.println(contact);
		}

		assertNotNull(contact);

	}

	@Test
	void testList() {
	
		List<Contact> listContacts = dao.list();
		
		for(Contact aContact : listContacts) {
			System.out.println(aContact);
		}
		
		assertTrue(!listContacts.isEmpty());
		
	}

}
