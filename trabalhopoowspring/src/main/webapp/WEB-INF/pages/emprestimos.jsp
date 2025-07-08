<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Cadastro de Empréstimos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
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
                <li class="nav-item"><a class="nav-link" href="/cliente">Clientes</a></li>
                <li class="nav-item"><a class="nav-link" href="/emprestimos">Empréstimos</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-4">Cadastro de Empréstimos</h1>

    <form:form action="${pageContext.request.contextPath}/emprestimos" method="post" modelAttribute="emprestimo" cssClass="bg-secondary p-4 rounded">
        <form:hidden path="id" />

        <c:choose>
            <c:when test="${emprestimo.id != 0}">
                <h2>Editar Empréstimo</h2>
            </c:when>
            <c:otherwise>
                <h2>Adicionar Empréstimo</h2>
            </c:otherwise>
        </c:choose>

        <div class="mb-3">
            <form:label path="funcionario.id" cssClass="form-label">Funcionário</form:label>
            <form:select path="funcionario.id" cssClass="form-control">
                <form:options items="${funcionarios}" itemValue="id" itemLabel="nome" />
            </form:select>
        </div>

        <div class="mb-3">
            <form:label path="cliente.id" cssClass="form-label">Cliente</form:label>
            <form:select path="cliente.id" cssClass="form-control">
                <form:options items="${clientes}" itemValue="id" itemLabel="nome" />
            </form:select>
        </div>

        <div class="mb-3">
            <form:label path="livro.id" cssClass="form-label">Livro</form:label>
            <form:select path="livro.id" cssClass="form-control">
                <form:options items="${livros}" itemValue="id" itemLabel="titulo" />
            </form:select>
        </div>

        <div class="mb-3">
            <form:label path="dataEmprestimo" cssClass="form-label">Data de Empréstimo</form:label>
            <form:input path="dataEmprestimo" type="date" cssClass="form-control" required="true" />
        </div>

        <div class="mb-3">
            <form:label path="dataDevolucao" cssClass="form-label">Data de Devolução</form:label>
            <c:choose>
                <c:when test="${emprestimo.id != 0}">
                    <form:input path="dataDevolucao" type="date" cssClass="form-control" required="true" />
                </c:when>
                <c:otherwise>
                    <form:input path="dataDevolucao" type="date" cssClass="form-control" />
                </c:otherwise>
            </c:choose>
        </div>

        <c:choose>
            <c:when test="${emprestimo.id != 0}">
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

    <h2 class="mt-5 text-center">Lista de Empréstimos</h2>
    <table class="table table-dark table-striped mt-3">
        <thead>
        <tr>
            <th>Data Empréstimo</th>
            <th>Data Devolução</th>
            <th>Funcionário</th>
            <th>Cliente</th>
            <th>Livro</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${emprestimos}">
            <tr>
                <td>${e.dataEmprestimo}</td>
                <td>${e.dataDevolucao}</td>
                <td>${e.funcionario.nome}</td>
                <td>${e.cliente.nome}</td>
                <td>${e.livro.titulo}</td>
                <td>
                    <a href="emprestimos/editar/${e.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="emprestimos/excluir/${e.id}" class="btn btn-danger btn-sm">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>