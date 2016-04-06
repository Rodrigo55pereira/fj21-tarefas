package br.com.caelum.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // que irá gerar ações 
public class OlaMundoController {
	
	// Podemos criar várias ações dentro desse controle que são chamados pelas anotações

	@RequestMapping("/olaMundoSpring") // anotação que mapeia o método
	public String execute(){
		System.out.println("Executando a lógica com Spring MVC");
		return "ok";
	}
}
	