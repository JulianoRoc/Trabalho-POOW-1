<%@page contentType="text/html; charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page isELIgnored="false" %>

            <!DOCTYPE html>
            <html lang="pt-BR">

            <head>
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Cadastro de Funcionários</title>
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
                            </ul>
                        </div>
                    </div>
                </nav>
                <div class="container mt-5">
                    <h1 class="text-center mb-4">Cadastro de Funcionários</h1>
                    <form action="funcionario" method="post" class="bg-secondary p-4 rounded">
                        <c:choose>
                            <c:when test="${funcionario.id != null}">
                                <h2>Editar Funcionário</h2>
                                <input type="hidden" name="idfuncionario" value="${funcionario.id}">
                            </c:when>
                            <c:otherwise>
                                <h2>Adicionar Funcionários</h2>
                                <input type="hidden" name="idfuncionario" value="0">
                            </c:otherwise>
                        </c:choose>

                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" placeholder="Nome" name="nome" required value="${funcionario.nome}">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" placeholder="Email" name="email" required value="${funcionario.email}">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" class="form-control" placeholder="Senha" name="senha" required value="${funcionario.senha}">
                        </div>
                        <div class="mb-3">
                            <label for="ativo" class="form-label">Ativo</label>
                            <select name="ativo" class="form-select" required>
        <option value="true" <c:if test="${funcionario.ativo}">selected</c:if>>Sim</option>
        <option value="false" <c:if test="${!funcionario.ativo}">selected</c:if>>Não</option>
      </select>
                        </div>

                        <c:choose>
                            <c:when test="${funcionario.id != null}">
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

                    <h2 class="mt-5 text-center">Lista de Funcionários</h2>
                    <table class="table table-dark table-striped mt-3">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>Ativo</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="funcionario" items="${funcionarios}">
                                <tr>
                                    <td>${funcionario.nome}</td>
                                    <td>${funcionario.email}</td>
                                    <td>${funcionario.ativo ? 'Sim' : 'Não'}</td>
                                    <td>
                                        <a href="funcionario?opcao=editar&&info=${funcionario.id}" class="btn btn-warning btn-sm">Editar</a>
                                        <a href="funcionario?opcao=excluir&&info=${funcionario.id}" class="btn btn-danger btn-sm">Excluir</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
            </body>

            </html>