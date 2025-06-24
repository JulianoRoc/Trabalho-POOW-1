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

        <main class="container mt-5 pt-3">
            <h1 class="mb-4 text-center">Dashbord</h1>
            <ul class="list-group w-50 mx-auto">
                <li class="list-group-item bg-secondary text-light mb-2 rounded">
                    <a href="funcionario" class="text-light text-decoration-none">Cadastro de Funcionários</a>
                </li>
                <li class="list-group-item bg-secondary text-light mb-2 rounded">
                    <a href="livro" class="text-light text-decoration-none">Cadastro de Livros</a>
                </li>
                <li class="list-group-item bg-secondary text-light mb-2 rounded">
                    <a href="cliente" class="text-light text-decoration-none">Cadastro de Clientes</a>
                </li>
                <li class="list-group-item bg-secondary text-light mb-2 rounded">
                    <a href="emprestimos" class="text-light text-decoration-none">Cadastro de Emprestimos</a>
                </li>
            </ul>
        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    </body>

    </html>