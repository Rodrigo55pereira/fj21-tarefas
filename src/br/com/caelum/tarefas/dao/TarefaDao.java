package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Tarefa;

/*
 *  Para o Spring conseguir criar o JdbcTarefaDao vamos declarar a classe como componente. Aqui o Spring
	possui a anota��o @Repository que deve ser utilizada nas classes como o DAO. Al�m disso, vamos tamb�m
	amarrar a conex�o com @Autowired. Veja o c�digo similar a classe TarefasController:
 */

@Repository
public class TarefaDao {
	
	Connection connection;
	
	// Delegando a dep�ndencia para cima e n�o responsabilizando pela cria��o
	// padr�o de projetos Dependency Injection (DI) (Inje��o de depend�ncias)

	/*
	 * O padr�o de projetos Dependency Injection (DI) (Inje��o de depend�ncias),
	 * procura resolver esses problemas. A ideia � que a classe n�o mais resolva
	 * as suas depend�ncias por conta pr�pria mas apenas declara que depende de
	 * alguma outra classe.
	 */

	/*
	 * Usando uma anota��o do Container IoC (Inversion of Control) ou Container
	 * DI.
	 * 
	 * Para receber o DAO em nosso controlador, usaremos a anota��o @Autowired
	 * acima do construtor, (wire - amarrar), isso indica ao spring que ele
	 * precisa resolver e injetar a depend�ncia.
	 */
	@Autowired // injetando depend�ncia
	public TarefaDao(DataSource dataSource) {
		try{
			this.connection = dataSource.getConnection();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefas (descricao, finalizado) " + "values (?,0)";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> lista() {
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando objeto tarefa
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				if (rs.getDate("dataFinalizacao") != null) {
					// montando data atraves do calendar
					Calendar dataFinalizacao = Calendar.getInstance();
					dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));

					tarefa.setDataFinalizacao(dataFinalizacao);
				}
				// adicionar objeto a lista
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
			System.out.println("Lista gerada coim Sucesso!");
			return tarefas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("delete from tarefas where id = ?");

			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();

			System.out.println("Tarefa Excluida com SUCESSO!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Tarefa buscaPorId(Long id) {

		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// System.out.println(rs.getLong("id")+ " // " + id);
				if (id == rs.getLong("id")) {
					// criando objeto tarefa
					Tarefa tarefa = new Tarefa();
					tarefa.setId(rs.getLong("id"));
					tarefa.setDescricao(rs.getString("descricao"));
					tarefa.setFinalizado(rs.getBoolean("finalizado"));

					if (rs.getDate("dataFinalizacao") != null) {
						// montando data atraves do calendar
						Calendar dataFinalizacao = Calendar.getInstance();
						dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));

						tarefa.setDataFinalizacao(dataFinalizacao);
					}
					System.out.println("retornada tarefa:" + tarefa.getId());
					return tarefa;
				}
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Tarefa tarefa) {
		String sql = "update tarefas set descricao=?, finalizado=?, dataFinalizacao=? where id=?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			if (tarefa.getDataFinalizacao() != null) {
				stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			} else {
				stmt.setDate(3, null);
			}

			stmt.setLong(4, tarefa.getId());

			stmt.execute();
			stmt.close();

			System.out.println("DADOS ALTERADOS COM SUCESSO!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void finaliza(Long id) {

		Tarefa tarefa = buscaPorId(id);

		String sql = "update tarefas set finalizado=?, dataFinalizacao=? where id=?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setBoolean(1, true);

			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));

			stmt.setLong(3, tarefa.getId());

			stmt.execute();
			stmt.close();

			System.out.println("DADOS ALTERADOS COM SUCESSO!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
