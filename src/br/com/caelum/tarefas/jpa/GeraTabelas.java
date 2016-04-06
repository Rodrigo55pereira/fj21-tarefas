package br.com.caelum.tarefas.jpa;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.tarefas.modelo.Tarefa;

public class GeraTabelas {
	public static void main(String[] args) {
		// F�brica de criar entidades, persistindo no banco
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("fj21");
		/* Para se comunicar com o JPA, precisamos de uma inst�ncia de um objeto do tipo EntityManager.
		   Adquirimos uma EntityManager atrav�s da f�brica ja conhecida: EntityManagerFactory
		*/
		EntityManager manager = factory.createEntityManager();
		
		// Persistindo novos objetos
		Tarefa t = new Tarefa();
		//t.setId(1l);
		t.setDataFinalizacao(Calendar.getInstance());
		t.setFinalizado(true);
		t.setDescricao("Teste de persistencia");
		
		manager.getTransaction().begin();
		// m�todo persist() : grava um registro no banco
		// j� o m�todo merge() : atualiza um regristro no banco
		manager.persist(t);
		manager.getTransaction().commit();
		
		//System.out.println("ID da tarefa: " + t.getId());
		
		manager.close();
		factory.close();
		
	}
}
