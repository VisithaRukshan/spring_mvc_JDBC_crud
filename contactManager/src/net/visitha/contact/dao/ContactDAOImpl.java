package net.visitha.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.visitha.contact.model.Contact;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;

	public ContactDAOImpl(DataSource dataSource) {
		System.out.println("in the ContactDAOImpl");
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Contact c) {
		System.out.println("Save function");
		String sql = "INSERT INTO contact (name, email, address, telephone) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getTelephone());

	}

	@Override
	public int update(Contact c) {
		System.out.println("update function");
		String sql = "UPDATE contact SET name=?, email=?, address=?,telephone=? WHERE contact_id=?";
		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getTelephone(), c.getId());

	}

	@Override
	public int delete(Integer id) {
		System.out.println("delete function");
		String sql = "DELETE FROM contact WHERE contact_id =" + id;
		return jdbcTemplate.update(sql);
	}

	@Override
	public Contact get(Integer id) {
		System.out.println(" get view function");
		String sql = "SELECT * FROM contact WHERE contact_id =" + id;

		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String telephone = rs.getString("telephone");

					return new Contact(id, name, email, address, telephone);
				}
				return null;
			}

		};

		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<Contact> list() {

		System.out.println("  view all function");
		String sql = "SELECT * FROM contact";

		RowMapper<Contact> rowMapper = new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("contact_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");

				return new Contact(id, name, email, address, telephone);
			}

		};

		return jdbcTemplate.query(sql, rowMapper);

	}
}
