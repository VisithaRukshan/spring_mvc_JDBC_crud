package net.visitha.contact.dao;

import java.util.List;

import net.visitha.contact.model.Contact;

public interface ContactDAO {
	
	public int save(Contact contact);

	public int update(Contact contact);

	public int delete(Integer id);

	public Contact get(Integer id);

	public List<Contact> list();

}
