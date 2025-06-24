<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Emprestimos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>

<body class="bg-dark text-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="dashbord">Dashbord</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Alternar navegação">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="funcionario">Funcionários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="livro">Livros</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cliente">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="emprestimos">Emprestimos</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5">
    <div class="container mt-5">
        <h1 class="text-center mb-4">Cadastro de Emprestimos</h1>
        <form action="emprestimos" method="post" class="bg-secondary p-4 rounded">
            <c:choose>
                <c:when test="${emprestimo.id != null}">
                    <h2>Editar Emprestimo</h2>
                    <input type="hidden" name="idemprestimo" value="${emprestimo.id}">
                </c:when>
                <c:otherwise>
                    <h2>Adicionar Emprestimo</h2>
                    <input type="hidden" name="idemprestimo" value="0">
                </c:otherwise>
            </c:choose>

            <div class="mb-3">
                <label for="nome" class="form-label">Funcionário</label>
                <select class="form-control" name="idfuncionario">
                    <c:forEach var="funcionario" items="${funcionarios}">
                        <option value="${funcionario.id}" <c:if test="${emprestimo.funcionario.id == funcionario.id}">selected</c:if>>${funcionario.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="nome" class="form-label">Cliente</label>
                <select class="form-control" name="idcliente">
                    <c:forEach var="cliente" items="${clientes}">
                        <option value="${cliente.id}" <c:if test="${emprestimo.cliente.id == cliente.id}">selected</c:if>>${cliente.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="nome" class="form-label">Livro</label>
                <select class="form-control" name="idlivro">
                    <c:forEach var="livro" items="${livros}">
                        <option value="${livro.id}" <c:if test="${emprestimo.livro.id == livro.id}">selected</c:if>>${livro.titulo}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="dataemprestimo" class="form-label">Data Emprestimo</label>
                <input type="date" class="form-control" name="dataemprestimo" required value="${emprestimo.dataEmprestimo}">
            </div>

            <c:choose>
                <c:when test="${emprestimo.id != null}">
                    <div class="mb-3">
                        <label for="datadevolucao" class="form-label">Data Devolução</label>
                        <input type="date" class="form-control" name="datadevolucao" required value="${emprestimo.dataDevolucao}">
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="mb-3">
                        <label for="datadevolucao" class="form-label">Data Devolução</label>
                        <input type="date" class="form-control" name="datadevolucao" value="${emprestimo.dataDevolucao}">
                    </div>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${emprestimo.id != null}">
                    <button type="submit" class="btn btn-warning w-100" name="gravar">Alterar</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-primary w-100" name="gravar">Cadastrar</button>
                </c:otherwise>
            </c:choose>
        </form>

        <c:if test="${not empty msg}">
            <div class="alert alert-danger mt-3" role="alert">
                    ${msg}
            </div>
        </c:if>
    </div>

    <h1 class="text-center mb-4">Lista de Emprestimos</h1>

<%--    <div class="text-center">--%>
<%--        <a href="emprestimos" class="btn bg-primary btn-lg btn-emprestimo rounded-pill px-4"> Cadastro de Empréstimos </a>--%>
<%--    </div>--%>

    <c:if test="${not empty msg}">
        <div class="alert alert-danger mt-3" role="alert">
                ${msg}
        </div>
    </c:if>

    <table class="table table-dark table-striped mt-3">
        <thead>
        <tr>
            <th>Data Emprestimo</th>
            <th>Data Devolução</th>
            <th>Funcionário</th>
            <th>Cliente</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emprestimo" items="${emprestimos}">
            <tr>
                <td>${emprestimo.dataEmprestimo}</td>
                <td>${emprestimo.dataDevolucao}</td>
                <td>${emprestimo.funcionario.nome}</td>
                <td>${emprestimo.cliente.nome}</td>
                <td>
                    <a href="emprestimos?opcao=editar&&info=${emprestimo.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="emprestimos?opcao=excluir&&info=${emprestimo.id}" class="btn btn-danger btn-sm">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>

</html>