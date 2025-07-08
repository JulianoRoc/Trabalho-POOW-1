<%@page contentType="text/html; ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body class="bg-dark text-light">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <form action="login" method="post" class="bg-secondary p-4 rounded" style="width: 300px;">
        <h2 class="text-center mb-4">Logar no Sistema</h2>
        <div class="mb-3">
            <label for="email" class="form-label"><b>Login</b></label>
            <input type="email" class="form-control" placeholder="E-mail" name="email" required>
        </div>
        <div class="mb-3">
            <label for="senha" class="form-label"><b>Senha</b></label>
            <input type="password" class="form-control" placeholder="Senha" name="senha" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Logar</button>
        <c:if test="${not empty msg}">
            <div class="alert alert-danger mt-3" role="alert">
                    ${msg}
            </div>
        </c:if>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>