<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adiciona Tarefa MVC</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="conteudo">
		<c:import url="cabecalho.jsp"/>
			<h3>Adicionar Tarefas</h3>
			<form:errors path="tafera.descricao" cssStyle="color:red" />
			<form action="adicionaTarefa" method="post">
				Descrição: <br />
				<input type="text" name="descricao" />
				<br />
				<br />
				<input type="submit" value="Adicionar"/>
			</form>
		<c:import url="rodape.jsp" />	
	</div>
</body>
</html>