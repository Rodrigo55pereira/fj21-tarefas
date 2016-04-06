package br.com.caelum.tarefas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.caelum.tarefas.dao.TarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

/*
 * A anota��o do @Controller vai alem de definiar a classe como um controller
 * ele define tb que queremos que o spring controle o objeto
 * Spring � no fundo um container que d� new para n�s e tb sabe resolver e ligar as 
 * depend�ncias, sendo chamdo de Container IoC (Inverson of Control) ou Container DI
 */

@Controller
public class TarefasController {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ApplicationContext appContext;

	/*
	 * Dizemos que a classe TarefasController tem uma depend�ncia com o
	 * TarefaDao /* V�rios m�todos dela dependem do TarefaDao
	 * 
	 */
	private final TarefaDao dao;

	// Delegando a dep�ndencia para cima e n�o responsabilizando pela cria��o
	// Padr�o de projetos Dependency Injection (DI) (Inje��o de depend�ncias)

	/*
	 * O padr�o de projetos Dependency Injection (DI) (Inje��o de depend�ncias),
	 * procura resolver esses problemas. A ideia � que a classe n�o mais resolva
	 * as suas depend�ncias por conta pr�pria mas apenas declara que depende de
	 * alguma outra classe.
	 */
	
	/* Usando uma anota��o do Container IoC (Inversion of Control) ou Container DI.
	 * 
	 * Para receber o DAO em nosso controlador, usaremos a anota��o @Autowired acima do construtor,
	 * (wire - amarrar), isso indica ao spring que ele precisa resolver e injetar a depend�ncia.
	 */
	@Autowired // Inje��o de Depend�ncia
	public TarefasController(TarefaDao dao) {
		this.dao = dao;
	}

	// acessando formul�rio tarefa
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	// o spring popul� o nosso objeto atrav�s do parametro que � passado dentro
	// do m�todo
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		// o Spring MVC pode guardar o resultado (erros de valida��o) em um
		// objeto do tipo BindingResult

		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}

		// TarefaDao dao = new TarefaDao();
		dao.adiciona(tarefa);
		return "redirect:listaTarefas";
	}

	/*
	 * 
	 * M�todo de a��o usando o retorno ModelAndView
	 * 
	 * @RequestMapping("listaTarefas") public ModelAndView lista(){ TarefaDao
	 * dao = new TarefaDao(); List<Tarefa> tarefas = dao.lista();
	 * 
	 * 
	 * // -- Devolvendo a lista para a view jsp lista (encapsulando pelo spring)
	 * --
	 * 
	 * // Os dados para a exibi��o na tela e o nome da p�gina JSP foram
	 * encapsulados pelo Spring // MVC em uma classe especial que se chama
	 * ModelAndView
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView("tarefa/lista");
	 * mv.addObject("tarefas", tarefas);
	 * 
	 * return mv;
	 * 
	 * }
	 */

	/*
	 * M�todo de a��o usando Model Como par�metro
	 */

	@RequestMapping("listaTarefas")
	public String lista(Model model) {

		// TarefaDao dao = new TarefaDao();
		List<Tarefa> tarefas = dao.lista();
		model.addAttribute("tarefas", tarefas);

		return "tarefa/lista";
	}

	/*
	 * m�todo de a��o para remover uma tarefa
	 */
	@RequestMapping("removerTarefa")
	public void remove(Tarefa tarefa, HttpServletResponse response) {
		// TarefaDao dao = new TarefaDao();
		dao.remove(tarefa);
		response.setStatus(200);
	}

	// metodo de acao para busca uma tarefa
	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		// TarefaDao dao = new TarefaDao();
		Tarefa tarefa = dao.buscaPorId(id);

		model.addAttribute("tarefa", tarefa);

		return "tarefa/mostra";
	}

	// metodo de acao para alterar tarefa
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		// TarefaDao dao = new TarefaDao();
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

	/*
	 * Retornar uma JSP j� nos traz o benef�cio do status 200, necess�rio para
	 * nossa fun��o de callback no jQuery. Sabendo disso usaremos uma JSP para
	 * renderizar a data formatada. Mas antes � preciso passar essa data a nossa
	 * JSP, ou simplesmente passar uma Tarefa para ela e depois fazer com que a
	 * Action retorne a String referente a JSP.
	 */
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {
		// TarefaDao dao = new TarefaDao();
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		// retorna a dataFinalizada para o arquivo DataFinalizada formatar e
		// consequentimente
		// retorna o jsp para o m�todo $.post() do jQuery recupere a resposta
		return "tarefa/finalizada";
	}
	
	@RequestMapping("pdf")
	public ModelAndView generatePdfReport() {
		final JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:report.jrxml");
		view.setJdbcDataSource(dataSource);
		view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<>();
		return new ModelAndView(view, params);
	}
}
