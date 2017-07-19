package com.jbbwebsolutions.sql;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLFunction <T, U, R> {	
	R execute(T t, U u) throws SQLException;	
}
