package br.com.caelum.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // que ir� gerar a��es 
public class OlaMundoController {
	
	// Podemos criar v�rias a��es dentro desse controle que s�o chamados pelas anota��es

	@RequestMapping("/olaMundoSpring") // anota��o que mapeia o m�todo
	public String execute(){
		System.out.println("Executando a l�gica com Spring MVC");
		return "ok";
	}
}
	