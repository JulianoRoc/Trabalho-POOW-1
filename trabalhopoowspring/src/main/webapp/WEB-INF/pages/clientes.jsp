<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Cadastro de Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-dark text-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/dashbord">Dashbord</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/funcionario">Funcionários</a></li>
                <li class="nav-item"><a class="nav-link" href="/livro">Livros</a></li>
                <li class="nav-item"><a class="nav-link active" href="/cliente">Clientes</a></li>
                <li class="nav-item"><a class="nav-link" href="/emprestimos">Empréstimos</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-4">Cadastro de Clientes</h1>

    <form:form action="${pageContext.request.contextPath}/cliente" method="post" modelAttribute="cliente" cssClass="bg-secondary p-4 rounded">
        <form:hidden path="id" />

        <c:choose>
            <c:when test="${cliente.id != 0}">
                <h2>Editar Cliente</h2>
            </c:when>
            <c:otherwise>
                <h2>Adicionar Cliente</h2>
            </c:otherwise>
        </c:choose>

        <div class="mb-3">
            <form:label path="nome" cssClass="form-label">Nome</form:label>
            <form:input path="nome" cssClass="form-control" placeholder="Nome" required="true" />
        </div>

        <div class="mb-3">
            <form:label path="cpf" cssClass="form-label">CPF</form:label>
            <form:input path="cpf" cssClass="form-control" placeholder="CPF" required="true" />
        </div>

        <div class="mb-3">
            <form:label path="telefone" cssClass="form-label">Telefone</form:label>
            <form:input path="telefone" cssClass="form-control" placeholder="Telefone" required="true" />
        </div>

        <div class="mb-3">
            <form:label path="endereco" cssClass="form-label">Endereço</form:label>
            <form:input path="endereco" cssClass="form-control" placeholder="Endereço" required="true" />
        </div>

        <c:choose>
            <c:when test="${cliente.id != 0}">
                <button type="submit" class="btn btn-warning w-100">Alterar</button>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
            </c:otherwise>
        </c:choose>
    </form:form>

    <c:if test="${not empty msg}">
        <div class="alert alert-${tipoMsg != null ? tipoMsg : 'danger'} mt-3" role="alert">
                ${msg}
        </div>
    </c:if>

    <h2 class="mt-5 text-center">Lista de Clientes</h2>
    <table class="table table-dark table-striped mt-3">
        <thead>
        <tr>
            <th>Nome</th>
            <th>CPF</th>
            <th>Telefone</th>
            <th>Endereço</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${clientes}">
            <tr>
                <td>${c.nome}</td>
                <td>${c.cpf}</td>
                <td>${c.telefone}</td>
                <td>${c.endereco}</td>
                <td>
                    <a href="cliente/editar/${c.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="cliente/excluir/${c.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Deseja realmente excluir este cliente?');">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>