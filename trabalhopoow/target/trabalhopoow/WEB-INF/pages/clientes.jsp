<%@page contentType="text/html; charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page isELIgnored="false" %>

            <!DOCTYPE html>
            <html lang="pt-BR">

            <head>
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Cadastro de Clientes</title>
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
                                    <a class="nav-link active" aria-current="page" href="#">Clientes</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="emprestimos">Emprestimos</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <div class="container mt-5">
                    <h1 class="text-center mb-4">Cadastro de Clientes</h1>
                    <form action="cliente" method="post" class="bg-secondary p-4 rounded">
                        <c:choose>
                            <c:when test="${cliente.id != null}">
                                <h2>Editar Cliente</h2>
                                <input type="hidden" name="idcliente" value="${cliente.id}">
                            </c:when>
                            <c:otherwise>
                                <h2>Adicionar Cliente</h2>
                                <input type="hidden" name="idcliente" value="0">
                            </c:otherwise>
                        </c:choose>

                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" placeholder="Nome" name="nome" required value="${cliente.nome}">
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" placeholder="CPF" name="cpf" required value="${cliente.cpf}">
                        </div>
                        <div class="mb-3">
                            <label for="telefone" class="form-label">Telefone</label>
                            <input type="text" class="form-control" placeholder="Telefone" name="telefone" required value="${cliente.telefone}">
                        </div>
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" class="form-control" placeholder="Endereço" name="endereco" required value="${cliente.endereco}">
                        </div>

                        <c:choose>
                            <c:when test="${cliente.id != null}">
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
                            <c:forEach var="cliente" items="${clientes}">
                                <tr>
                                    <td>${cliente.nome}</td>
                                    <td>${cliente.cpf}</td>
                                    <td>${cliente.telefone}</td>
                                    <td>${cliente.endereco}</td>
                                    <td>
                                        <a href="cliente?opcao=editar&&info=${cliente.id}" class="btn btn-warning btn-sm">Editar</a>
                                        <a href="cliente?opcao=excluir&&info=${cliente.id}" class="btn btn-danger btn-sm">Excluir</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
            </body>

            </html>