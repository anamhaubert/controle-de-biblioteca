package ana.ifpr.foz.controlebiblioteca.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ana.ifpr.foz.controlebiblioteca.models.Author;
import ana.ifpr.foz.controlebiblioteca.models.Book;
import ana.ifpr.foz.controlebiblioteca.models.BookStatus;
import ana.ifpr.foz.controlebiblioteca.repositories.AuthorRepository;
import ana.ifpr.foz.controlebiblioteca.repositories.BookRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet("/books/update")

public class BookUpdateController extends HttpServlet {
    BookRepository bookRepository = new BookRepository();
    AuthorRepository authorRepository = new AuthorRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update.jsp");
        List<Author> authorList = authorRepository.getAll();
        List<BookStatus> status = Arrays.asList(BookStatus.values());
        Integer id = Integer.parseInt(req.getParameter("id"));
        Book book = bookRepository.findById(id);
        req.setAttribute("book", book);
        req.setAttribute("authors", authorList);
        req.setAttribute("status", status);

        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        Integer idAuthor = Integer.valueOf(req.getParameter("field_author"));
        Integer idBook = Integer.valueOf(req.getParameter("field_id"));
        String name = req.getParameter("field_name");
        LocalDate date = LocalDate.parse(req.getParameter("field_date"));

        BookStatus status = BookStatus.values()[Integer.parseInt(req.getParameter("field_status"))];
        book.setId(idBook);
        book.setName(name);
        book.setStatus(status);
        book.setDate(date);

        Author author = authorRepository.findById(idAuthor);
        book.setAuthor(author);
        bookRepository.update(book);


        resp.sendRedirect("http://localhost:9090/controle_biblioteca_war_exploded/");
    }
}
