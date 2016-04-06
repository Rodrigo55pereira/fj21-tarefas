<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Tarefa</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="conteudo">
		<c:import url="cabecalho.jsp"/>
			<h3>Alterar tarefa - ${tarefa.id}</h3>
			<form action="alteraTarefa" method="post">
				<input type="hidden" name="id" value="${tarefa.id}" />
				Descricao: <br />
				<input type="text" name="descricao" value="${tarefa.descricao}" />
				<br />
				Finalizado ? <input type="checkbox" name="finalizado"
					value="true" ${tarefa.finalizado ? 'checked' : ''}/>
				<br />	
				Data de finalizacao: 
				<input type="text" name="dataFinalizacao" 
				value="<fmt:formatDate value='${tarefa.dataFinalizacao.time}' pattern='dd/MM/YYY'/>"/>
				<br />
				<input type="submit" value="Alterar"/> 
			</form>
		<c:import url="rodape.jsp" />
	</div>
</body>
</html>