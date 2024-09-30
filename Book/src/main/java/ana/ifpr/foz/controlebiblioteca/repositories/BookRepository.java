package ana.ifpr.foz.controlebiblioteca.repositories;

import ana.ifpr.foz.controlebiblioteca.connection.ConnectionFactory;
import ana.ifpr.foz.controlebiblioteca.exceptions.DatabaseException;
import ana.ifpr.foz.controlebiblioteca.models.*;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookRepository {
    Connection connection;
    public BookRepository() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = ConnectionFactory.getConnection();
    }

    public void create(Book book) {
        String sql = "INSERT INTO book (name,date,status,id_author) VALUES (?,?,?,?);";
        try{
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, book.getName());
            pst.setDate(2,Date.valueOf(book.getDate()));
            pst.setInt(3, Integer.valueOf(book.getStatus().ordinal()));
            pst.setInt(4,book.getAuthor().getId());

            Integer rowInserted = pst.executeUpdate();
            if(rowInserted > 0) {
                ResultSet id = pst.getGeneratedKeys();
                id.next();
                Integer bookId = id.getInt(1);
                book.setId(bookId);
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
    }

    public void delete(Integer id){
        String sql = "DELETE FROM book WHERE id_book = ?";
        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int deleteRow = pst.executeUpdate();
            if(deleteRow > 0){
                System.out.println("Rows deleted "+ deleteRow);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
    }

    public Book findById(Integer id) {
        Book book;
        Author author;
        String sql = "SELECT * FROM book INNER JOIN author ON book.id_author = author.id_author"+
                     " WHERE id_book = ?;";
        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                author = this.instantiateAuthor(rs);
                book = this.instantiateBook(rs,author);
            } else {
                throw new DatabaseException("Livro não encontrado");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return book;
    }

    public void updateStatus(Integer id, Integer status) {
        String sql = "UPDATE book SET status = ? WHERE id_book = ?;";
        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, id);
            int updateRow = pst.executeUpdate();
            if(updateRow > 0){
                System.out.println("Rows updated: "+ updateRow);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionFactory.closeConnection();
        }
    }

    public void update(Book book) {

        String sql = "UPDATE book SET " +
                "name = ?, " +
                "date = ?, " +
                "id_author = ?, " +
                "status = ? " +
                "WHERE id_book = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setDate(2, Date.valueOf(book.getDate()));
            statement.setDouble(3, book.getAuthor().getId());
            statement.setInt(4, book.getStatus().ordinal());
            statement.setInt(5, book.getId());

            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public List<Book> findAll(){

        Statement statement = null;
        ResultSet rs = null;
        Book book;
        Author author;
        List<Book> bookList = new ArrayList<Book>();
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM book " +
                    "INNER JOIN author ON book.id_author = author.id_author ");


            while (rs.next()){

                    author = this.instantiateAuthor(rs);
                    book = this.instantiateBook(rs,author);
                    bookList.add(book);
                }


        rs.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionFactory.closeConnection();
        }
        return bookList;
    }

    public Book instantiateBook(ResultSet resultSet, Author author) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id_book"));
        book.setName(resultSet.getString("name"));
        BookStatus status = BookStatus.values()[resultSet.getInt("status")];
        book.setStatus(status);
        book.setDate(resultSet.getDate("date").toLocalDate());
        book.setAuthor(author);

        return book;
    }

    public Author instantiateAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id_author"));
        author.setName(resultSet.getString("name"));

        return author;
    }

}

