package com.puri.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.puri.springdemo.entity.Customer;

@Repository
public class CustomerDAOImlp implements CustomerDAO{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		// Get Current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query   ..sort by last name
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		// return the lists
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer to database
		//currentSession.save(customer);
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		//Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from database using primary key
		Customer customer = currentSession.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		//Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete customer from using primary key
		Query query = currentSession.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		
		query.executeUpdate();
		
	}

}
