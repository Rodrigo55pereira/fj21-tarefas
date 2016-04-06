package br.com.caelum.tarefas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	/*
	 * O método preHandler recebe a requisição e a resposta, além do controlador
	 * quem está sendo interceptado e retorna um booleano que indica se queremos
	 * continuar com a requisição ou não.
	 * 
	 * Portanto, a classe AutorizadorInterceptor só deve devolver true se o
	 * usuário está logado. Caso o usuário não esteja autorizado vamos
	 * redirecionar para o formulário de login.
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		
		// pega a URI passado pelo request que ele está interceptando e guarda em uma String 
		String uri = request.getRequestURI();

		// verifica se a string que ele passou termina com os seguintes atributos 
		// o preHandle retorna um true, permitindo que acessar essas ações 
		if (uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.contains("resources"))
			return true;
		
		// verifica o atributo criado 'usuarioLogado' exista na sessão e se o usuário ja está logado
		if (request.getSession().getAttribute("usuarioLogado") != null)
			return true;

		response.sendRedirect("loginForm");
		return false;
	}
}
