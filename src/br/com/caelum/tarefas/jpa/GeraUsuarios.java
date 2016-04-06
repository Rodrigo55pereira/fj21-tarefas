package br.com.caelum.tarefas.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.tarefas.modelo.Usuario;

public class GeraUsuarios {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("fj21");
		EntityManager manager = factory.createEntityManager();
		
		Usuario usuario = new Usuario();
		usuario.setSenha("123");
		usuario.setUsuario("rodrigo");
	
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
}
