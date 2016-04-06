package br.com.caelum.tarefas.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.tarefas.modelo.Tarefa;

public class BuscaTarefas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("fj21");
		
		EntityManager manager = factory.createEntityManager();
		
		// cuidado, use o import javax.persistence.Query
		Query query	= manager.createQuery("select t from Tarefas as t " +
						"Where t.finalizado = :paramFinalizado");
		query.setParameter("paramFinalizado", true);
		
		// Retornando a consulta � uma lista
		@SuppressWarnings("unchecked")
		List<Tarefa> lista = query.getResultList();
		
		// for para imprimir as tarefas
		for(Tarefa t: lista){
			System.out.println(t.getDescricao() + "\n");
		}
		manager.close();
	}

}
