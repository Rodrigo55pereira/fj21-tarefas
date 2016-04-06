package br.com.caelum.tarefas.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.tarefas.modelo.Tarefa;

public class CarregaTarefa {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("fj21");
		EntityManager manager = factory.createEntityManager();
		
		// buscando tarefa pelo id, usando o m�todo find que buscar� o objeto
		Tarefa encontra = manager.find(Tarefa.class, 1l);
		System.out.println(encontra.getDescricao());
		
		manager.close();
	}

}
