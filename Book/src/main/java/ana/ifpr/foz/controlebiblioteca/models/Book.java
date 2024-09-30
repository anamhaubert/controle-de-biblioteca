package ana.ifpr.foz.controlebiblioteca.models;

import java.time.LocalDate;

public class Book {
    private int id;
    private BookStatus status;
    private String name;
    private LocalDate date;
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", author=" + author +
                '}';
    }
}
