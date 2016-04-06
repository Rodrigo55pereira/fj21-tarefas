package br.com.caelum.tarefas.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.tarefas.jdbc.ConnectionFactory;

public class TesteConexao {
	public static void main(String[] args) {
		try {
			Connection con = new ConnectionFactory().getConnection();
			System.out.println("Conexão Aberta !");
			con.close();
		} catch (RuntimeException e) {
			System.err.println("Erro em conectar: " + e);
		}
		catch(SQLException e){
			System.err.println("Erro ao conectar: " + e);
		}
		finally {
			System.out.println("fim da conexão");
		}
	}

}
