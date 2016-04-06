package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.UsuarioDao;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class LoginController {
	
	@RequestMapping("loginForm")
	public String loginForm(){
		return "tarefa/formulario-login";
	}
	
	@RequestMapping("efetuaLogin")// HttpSession session recebe a session que é estartada
	public String efetuaLogin(Usuario usuario, HttpSession session){
		/*  pasa o usuario para a ação efetuaLogin
		 	verifica se o usuário existe no banco
		 	e seta o usuário em um sessão para pode 
		 	recuperar depois
		 */
		if(new UsuarioDao().existeUsuario(usuario)){
			session.setAttribute("usuarioLogado", usuario);
			System.out.println("Usuario logado com Sucesso");
			return "redirect:listaTarefas";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		System.out.println("Logout efetuado com sucesso!");
		return "redirect:loginForm";
	}
}
