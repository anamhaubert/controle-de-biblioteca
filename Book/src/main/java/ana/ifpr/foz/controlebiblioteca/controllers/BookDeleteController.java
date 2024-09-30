package ana.ifpr.foz.controlebiblioteca.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ana.ifpr.foz.controlebiblioteca.repositories.BookRepository;

import java.io.IOException;

@WebServlet("/books/delete")
public class BookDeleteController extends HttpServlet {
    BookRepository bookRepository = new BookRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer id = Integer.parseInt(req.getParameter("id"));
        bookRepository.delete(id);

        resp.sendRedirect("http://localhost:9090/controle_biblioteca_war_exploded/");
    }
}
