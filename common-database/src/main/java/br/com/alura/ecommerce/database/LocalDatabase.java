package br.com.alura.ecommerce.database;

import java.sql.*;

public class LocalDatabase {

    private final Connection connection;

    public LocalDatabase(String name) throws SQLException {
        String url = "jdbc:sqlite:target/" + name + ".db";
        connection = DriverManager.getConnection(url);

    }

    public void createIfNotExists(String sql){
        try {
            connection.createStatement().execute(sql);
        } catch(SQLException ex) {
            // be careful, the sql could be wrong, be reallllly careful
            ex.printStackTrace();
        }
    }

    public boolean update(String statment, String ... params) throws SQLException {
        return prepare(statment, params).execute();

    }

    private PreparedStatement prepare(String statment, String[] params) throws SQLException {
        var preparedStatment = connection.prepareStatement(statment);
        for(int i = 0; i < params.length; i++) {
            preparedStatment.setString(i+1, params[i]);
        }
        return preparedStatment;
    }

    public ResultSet query(String query, String ... params) throws SQLException {
        return prepare(query, params).executeQuery();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
