<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Dashbord</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body class="bg-dark text-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/dashbord">Dashbord</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Sair</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="container mt-5 pt-3">
    <h1 class="mb-4 text-center">Opções de Cadastros:</h1>
    <ul class="list-group w-50 mx-auto">
        <li class="list-group-item bg-secondary text-light mb-2 rounded">
            <a href="/funcionario" class="text-light text-decoration-none">Cadastro de Funcionários</a>
        </li>
        <li class="list-group-item bg-secondary text-light mb-2 rounded">
            <a href="/livro" class="text-light text-decoration-none">Cadastro de Livros</a>
        </li>
        <li class="list-group-item bg-secondary text-light mb-2 rounded">
            <a href="/cliente" class="text-light text-decoration-none">Cadastro de Clientes</a>
        </li>
        <li class="list-group-item bg-secondary text-light mb-2 rounded">
            <a href="/emprestimos" class="text-light text-decoration-none">Cadastro de Empréstimos</a>
        </li>
    </ul>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>