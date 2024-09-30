<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="ana.ifpr.foz.controlebiblioteca.models.Book" %>
<%@ page contentType="text/html; charset-UTF-8"  pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administração de Livros</title>
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
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Livros Cadastrados</h1>


                <a class="btn btn-info" href="<%= request.getContextPath()%>/books/create">Cadastrar</a>
            </div>


            <!-- Tabela de vendedores -->
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Data_de_Publicação</th>
                        <th>Autor</th>
                        <th>Status</th>
                        <th><Ações></Ações></th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for(Book b : (List<Book>) request.getAttribute("books")) {%>


                    <tr>
                        <td><%= b.getId()%></td>
                        <td><%= b.getName()%></td>
                        <td><%= b.getDate().toString() %></td>
                        <td><%= b.getAuthor().getName()%></td>
                        <td><%= b.getStatus()%></td>
                        <td><a href="<%= request.getContextPath()%>/books/delete?id=<%= b.getId()%>" class="btn btn-sm btn-danger">Excluir</a></td>
                        <td><a href="<%= request.getContextPath()%>/books/update?id=<%= b.getId()%>" class="btn btn-sm btn-primary">Editar</a></td>
                    </tr>
                    <% }%>

                    <!-- Adicione mais vendedores conforme necessário -->
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

