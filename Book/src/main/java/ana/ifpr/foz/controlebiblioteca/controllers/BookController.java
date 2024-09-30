package ana.ifpr.foz.controlebiblioteca.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ana.ifpr.foz.controlebiblioteca.models.Book;
import ana.ifpr.foz.controlebiblioteca.repositories.BookRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"","/books"})
public class BookController extends HttpServlet {

    private BookRepository bookRepository;
    public BookController() {
        bookRepository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> bookList = bookRepository.findAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/books.jsp");
        req.setAttribute("books", bookList);
        dispatcher.forward(req, resp);
    }
}
