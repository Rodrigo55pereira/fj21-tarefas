package br.com.caelum.tarefas.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

/* Mapeando a classe tareafa para o banco de dado 
 * Usando framework HIBERNATE de ORM(mapeamento objeto-relacional) 
 * e com a API JPA (Java Persistence API).
 */

/*
 * Para mapear a classe Tarefa, basta adicionar algumas poucas anotações em nosso código. Anotação é um
   recurso do Java que permite inserir metadados em relação a nossa classe, atributos e métodos. Essas anotações
   depois poderão ser lidas por frameworks e bibliotecas, para que eles tomem decisões baseadas nessas
   pequenas congurações.
   
   Metadados são informações que acrescem aos dados e que têm como objectivo
   informar-nos sobre eles para tornar mais fácil a sua organização
 */

@Entity // indica que objetos dessa classe se tornem "persistivél" ao banco de dados
@Table(name="tarefas") // cria a tabela com o nome tarefas
public class Tarefa {
	
	@Id // indica que o atributo id é nossa chave primaria (é necessário ter uma chave primária em toda entidade)
	@GeneratedValue // diz que queremos esta chave seja populada no banco ou seja (auto_increment)
	private Long id;
	@NotNull
	@Size(min = 5)
	private String descricao;
	@Column(name = "finalizado", nullable = true)
	private boolean finalizado;
	// convertendo a data para o formato Brasileiro
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE) // configura como mapear um Calendar para o banco, aqui usamos apenas data (sem hora) 
	@Column(name = "dataFinalizacao", nullable = true) // mapeando o atributo dataFinalizacao, @Column para criar no banco
	private Calendar dataFinalizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Calendar getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Calendar dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

}
