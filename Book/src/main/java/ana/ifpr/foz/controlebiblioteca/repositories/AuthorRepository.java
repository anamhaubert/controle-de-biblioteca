package ana.ifpr.foz.controlebiblioteca.repositories;

import ana.ifpr.foz.controlebiblioteca.connection.ConnectionFactory;
import ana.ifpr.foz.controlebiblioteca.exceptions.DatabaseException;
import ana.ifpr.foz.controlebiblioteca.models.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {
    Connection connection;

    public AuthorRepository(){
        connection = ConnectionFactory.getConnection();
    }


    public void delete(int id){
        String sql = "DELETE FROM author WHERE id_author = ?";
        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }

    }

    public void create(Author author){
        String sql = "INSERT INTO author (name) VALUES (?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,author.getName());
            int rowInserted = statement.executeUpdate();

            if(rowInserted > 0){
                ResultSet id = statement.getGeneratedKeys();
                System.out.println("Row Inserted : " + rowInserted);
                id.next();
                author.setId(id.getInt(1));
            }
        }catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
    }

    public void update(Integer authorId, String name){
         String sql = "UPDATE author SET name = ? WHERE id_author = ?";
         try{
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setString(1,name);
             statement.setInt(2,authorId);

             int updateRow = statement.executeUpdate();
             if(updateRow > 0){
                 System.out.println("Row Updated : " + updateRow);
             }

         }catch(Exception e){
             throw  new RuntimeException(e.getMessage());
         }finally {
             ConnectionFactory.closeConnection();
         }
    }

    public List<Author> getAll(){
        List<Author> authorList = new ArrayList<Author>();
        ResultSet resultSet;
        Author author;
        try{

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM author");
            while(resultSet.next()){
                author = new Author();
                author.setId(resultSet.getInt("id_author"));
                author.setName(resultSet.getString("name"));
                authorList.add(author);
            }
            resultSet.close();
            return authorList;

        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }

    }

    public Author findById(int id){
        Author author;
        String sql= "SELECT * FROM author WHERE id_author = ? ";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                author = instantiateDepartment(resultSet);
            }else{
                throw new DatabaseException("Departamento nao Encontrado");
            }

        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return author;
    }

    private static Author instantiateDepartment(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setName(resultSet.getString("name"));
        author.setId(resultSet.getInt("id_author"));

        return author;
    }

}
