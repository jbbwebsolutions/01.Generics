package com.jbbwebsolutions;

import java.sql.ResultSet;
import java.util.List;

import com.jbbwebsolutions.model.Customer;
import com.jbbwebsolutions.sql.SQLFunction;
import com.jbbwebsolutions.sql.SQLHelper;

public class DriverMySQL {

	public static void main(String[] args) {
		e5_DELETE_sql();
		e4_Select_SQL();
		e6_insert_SQL();
		e4_Select_SQL();
		e7_update_SQL();
		e4_Select_SQL();
	}
	
	public static void e7_update_SQL() {
		
		  String sql = "UPDATE CUSTOMERS " +
                  "SET SALARY = SALARY * 1.5 "
                  + " WHERE id = 3";		

		int code = SQLHelper.instanceOf().modify(sql);
		System.out.println("code..: " + code);
	}	
	
	public static void e6_insert_SQL() {

		String sql = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) "
				+ " VALUES (1, 'Ramesh', 32, 'Ahmedabad', 2000.00 )";

		int code = SQLHelper.instanceOf().modify(sql);
		System.out.println("code..: " + code);
	}

	public static void e5_DELETE_sql() {

		String sql = "delete from customers where id = 1";
		int code = SQLHelper.instanceOf().modify(sql);
		System.out.println("code..: " + code);

	}

	public static void e4_Select_SQL() {

		String sql = "select * from customers order by 2";

		SQLFunction<ResultSet, Integer, Customer> x = (r, i) -> {
			Customer customer = null;
			String id = r.getString("id");
			String name = r.getString("name");
			String address = r.getString("address");
			float salary = r.getFloat("salary") * 1.10f;
			customer = new Customer(id, name, address, salary);

			return customer;
		};

		List<Customer> list = SQLHelper.instanceOf().getList(sql, x);
		list.forEach(System.out::println);
	}
}