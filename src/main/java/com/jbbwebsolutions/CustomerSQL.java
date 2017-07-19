package com.jbbwebsolutions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiFunction;

import com.jbbwebsolutions.model.Customer;

public class CustomerSQL implements BiFunction<ResultSet, Integer, Customer> {

	@Override
	public Customer apply(ResultSet r, Integer u){
		Customer customer = null;
		try {			
			
			String id =  r.getString("id");
			String name =  r.getString("name");
			String address =  r.getString("address");				
			float salary = r.getFloat("salary");
			customer = new Customer(id, name, address, salary );				
			
		} catch (SQLException e) {				
			e.printStackTrace();
		}
		return customer;
	}
}