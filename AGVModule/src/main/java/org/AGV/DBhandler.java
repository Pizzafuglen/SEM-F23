package org.AGV;

import java.sql.*;

public class DBhandler {
    public static Connection c;

    public static void main(String[] args) {
        getConnection("jdbc:mysql://localhost:3306/", "root", "Vithe!098");
        initialiseDatabase();
    }

    //DB connection
    public static Connection getConnection(String url, String user, String password) {
        try {
            if (c == null) {
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // Laver databasen
// Tjekker om databasen findes, hvis ja så brug samme database, hvis nej så lav en database med det navn
    public static void initialiseDatabase() {
        Connection connection = getConnection("jdbc:mysql://localhost:3306/", "***", "***");

        String checkDatabase = "SHOW DATABASES LIKE 'ProductionLine';";
        String useDatabase = "USE backlog";
        String initializeDatabase = "CREATE DATABASE 'ProductionLine'";
        try {
            if (connection != null) { // check if connection is not null
                ResultSet resultSet = connection.prepareStatement(checkDatabase).executeQuery();
                if (resultSet.next()) {
                    try {
                        connection.prepareStatement(useDatabase).executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        connection.prepareStatement(initializeDatabase).executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Laver tabel
    public static void initialiseTable() {
        String initializerTable = "CREATE TABLE AGV_table (id int NOT NULL, time_started TIMESTAMP DEFAULT CURRENT_TIMESTAMP, program varchar NOT NULL, battery int NOT NULL, state int NOT NULL);";
        try {
            DBhandler.getConnection("jdbc:mysql://localhost:3306/ProductionLine", "***", "***").prepareStatement(initializerTable).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Send data TO Database
    public static void setData(int id, int program, int C_state, int battery){
        String statement = "INSERT INTO AGV:table (id, program, C_state, battery) VALUES ('" + id + "', '" + program + "', '" + C_state + "', '" + battery + "');";
        try {
            DBhandler.getConnection("jdbc:mysql://localhost:3306/ProductionLine", "***", "***").createStatement().executeUpdate(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void resetDatabase(){
        String statement = "DROP TABLE brewer_backlog;";
        try {
            DBhandler.getConnection("jdbc:mysql://localhost:3306/ProductionLine", "***", "***").createStatement().executeUpdate(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Her skal GUI tage fra
    /*
    public static String getData() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        String statement = "SELECT * FROM brewer_backlog ORDER BY time_started ASC LIMIT 10;";

        try {
            ps = getConnection("jdbc:mysql://localhost:3306/backlog", "***", "***").prepareStatement(statement);
            rs = ps.executeQuery();
            while(rs.next()){
                sb.append(rs.getString(1)).append(", ").append(rs.getString(2)).append(", ").append(rs.getString(3)).append(", ").append(rs.getString(4)).append(", ").append(rs.getString(5)).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }*/
}
