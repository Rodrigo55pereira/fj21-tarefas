<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/imagens/logo.png" var="imagens" />
<img src="${imagens}" />

<div class="menu">
	<ul>
		<li><a href="novaTarefa">Cria nova tarefa</a></li>
		<li><a href="listaTarefas">Listar Tarefas</a></li>
	</ul>
</div>