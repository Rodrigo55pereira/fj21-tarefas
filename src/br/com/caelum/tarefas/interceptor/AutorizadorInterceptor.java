package br.com.caelum.tarefas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	/*
	 * O m�todo preHandler recebe a requisi��o e a resposta, al�m do controlador
	 * quem est� sendo interceptado e retorna um booleano que indica se queremos
	 * continuar com a requisi��o ou n�o.
	 * 
	 * Portanto, a classe AutorizadorInterceptor s� deve devolver true se o
	 * usu�rio est� logado. Caso o usu�rio n�o esteja autorizado vamos
	 * redirecionar para o formul�rio de login.
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		
		// pega a URI passado pelo request que ele est� interceptando e guarda em uma String 
		String uri = request.getRequestURI();

		// verifica se a string que ele passou termina com os seguintes atributos 
		// o preHandle retorna um true, permitindo que acessar essas a��es 
		if (uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.contains("resources"))
			return true;
		
		// verifica o atributo criado 'usuarioLogado' exista na sess�o e se o usu�rio ja est� logado
		if (request.getSession().getAttribute("usuarioLogado") != null)
			return true;

		response.sendRedirect("loginForm");
		return false;
	}
}
