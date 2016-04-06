<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Tarefas</title>
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript">
	function finalizaAgora(id){
		
		// recupera a resposta e modifica o html
		
		/*
			function resposta(dadosDeResposta){
			// seleciona o elemento html atraves do
			// id e altera o html dele
			$("#tarefa_" + id).html("Finalizado");
		}
		*/
		// (url da requisição, {dados passados pela requisição}, e por fim o método de resposta que mudará o html)
		
		$.post("finalizaTarefa", {'id' : id}, function(resposta){
			// seleciona o elemento html através do
			// id e alterando o html dele
			$("#tarefa_" + id).html(resposta);
			
		});
	} 
	
	// função remover tarefas
	function removerTarefasDinamic(id){
		
		$.post("removerTarefa", {'id': id}, function resposta(){
			// esconde a linha de onde vem o click
			$(elementoHtml).closest("tr").hide();
		});
	}
</script>

</head>
<body>
	<div class="conteudo">
		<c:import url="cabecalho.jsp" />
			<h3>Lista de Tarefas, bem vindo ${usuarioLogado.usuario} - <a href="logout">Sair</a><br /><a href="pdf">Tarefas não Concluidas</a></h3>
			
		<hr />
		<table>
			<tr class="tr-titulo">
				<td>Id</td>
				<td>Descrição</td>
				<td>Finalizado ?</td>
				<td>Data de Finalização</td>
				<td>Alterar</td>
				<td>Remover</td>
			</tr>
			<c:forEach items="${tarefas}" var="tarefa">
				<tr id="tarefa_${tarefa.id}" bgcolor="#${tarefa.id % 2 == 0 ? 'ffffff' : 'F0F8FF'}">
					<td>${tarefa.id}</td>
					<td>${tarefa.descricao}</td>
					<!-- eq igual -->
					<c:if test="${tarefa.finalizado eq false}">
						<td><a href="#" onclick="finalizaAgora(${tarefa.id})">Finalizar agora!</a></td>
					</c:if>
					<c:if test="${tarefa.finalizado eq true}">
						<td>Finalizado</td>
					</c:if>
					<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
							pattern="dd/MM/yyyy" /></td>
					<td><a href="mostraTarefa?id=${tarefa.id}">Altera</a></td>
					<td><a href="#" onclick="removerTarefasDinamic(${tarefa.id})">Remover</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:import url="rodape.jsp" />
	</div>
</body>
</html>