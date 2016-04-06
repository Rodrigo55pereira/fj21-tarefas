package br.com.caelum.tarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/fj21_tarefas", "root", "rodrigo@2016");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
