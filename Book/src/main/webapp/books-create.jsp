
<%@ page import="java.util.List" %>
<%@ page import="ana.ifpr.foz.controlebiblioteca.models.Book" %>
<%@ page import="ana.ifpr.foz.controlebiblioteca.models.Author" %>
<%@ page import="ana.ifpr.foz.controlebiblioteca.models.BookStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% List<Book> books = (List<Book>) request.getAttribute("books"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerencimento de Livros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">
</head>
<body class="bg-light">

<!-- Menu superior -->
<nav class="navbar navbar-expand-lg shadow-sm navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"> <img src="images/logo.png" alt="">Biblioteca</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Voltar</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <!-- Conteúdo principal -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Livros Cadastrados</h1>
            </div>

            <form action="<%= request.getContextPath()%>/books/create" method="post">

                <div class="mb-3">
                    <label for="name" class="form-label">Nome: </label>
                    <input type="text" class="form-control" id="name" name="field_name">
                </div>

                <div class="mb-3">
                    <label for="date" class="form-label">Data de Lançamento: </label>
                    <input type="date" class="form-control" id="date" name="field_date">
                </div>

                <div class="mb-3">
                    <label for="status" class="form-label">Autor: </label>
                    <select class="form-select" name="field_status" id="status">
                        <% for(BookStatus s : (List<BookStatus>) request.getAttribute("status")) {%>
                        <option value=<%= s.ordinal() %> ><%= s.name()%></option>
                        <% }%>
                    </select>
                </div>


                <div class="mb-3">
                    <label for="author" class="form-label">Autor: </label>
                    <select class="form-select" name="field_author" id="author">
                        <option selected>Selecione um Autor...</option>
                        <% for(Author author : (List<Author>) request.getAttribute("authors")) {%>
                            <option value=<%= author.getId() %> ><%= author.getName()%></option>
                        <% }%>

                    </select>
                </div>

                <div class="col-12">
                    <button class="btn btn-primary btn-sm px-5" type="submit">cadastrar</button>
                </div>


            </form>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>