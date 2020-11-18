package com.cfw.geektime.java000;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/java_000?useUnicode=true&characterEncoding=UTF8";
        String username = "root";
        String password = "admin";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,username,password);

        Statement statement = connection.createStatement();

//        1. 使用 JDBC 原生接口，实现数据库的增删改查操作。
        operationWithoutTX(statement);

//        2. 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
        operationWithTX(connection,statement);
        statement.close();
        operationWithPreparedStatement(connection);
        operationWitchPreparedStatementBathTX(connection);
        connection.close();

//        3. 配置 Hikari 连接池，改进上述操作
        HikariPoolDatasource hikariPoolDatasource = new HikariPoolDatasource();
        HikariDataSource hikariDataSource = hikariPoolDatasource.dataSource(url,username,password);
        connection =  hikariDataSource.getConnection();
        operationWithPreparedStatement(connection);
    }

    private static void operationWithTX(Connection connection, Statement statement) throws SQLException {
        connection.setAutoCommit(false);
        JDBCOperation jdbcOperation = new JDBCOperation();
        jdbcOperation.insert(statement);
        connection.commit();
        jdbcOperation.delete(statement);
        jdbcOperation.select(statement);
        connection.rollback();
        jdbcOperation.select(statement);
    }
    private static void operationWithoutTX(Statement statement) throws SQLException {
        JDBCOperation jdbcOperation = new JDBCOperation();
        jdbcOperation.insert(statement);
        jdbcOperation.delete(statement);
        jdbcOperation.update(statement);
        jdbcOperation.select(statement);
    }

    private static void operationWithPreparedStatement(Connection connection) throws SQLException {
        JDBCOperation jdbcOperation = new JDBCOperation();
        jdbcOperation.preparedStatementQuery(connection);
    }

    private static void operationWitchPreparedStatementBathTX(Connection connection) throws SQLException {
        JDBCOperation jdbcOperation = new JDBCOperation();
        jdbcOperation.preparedStatementBathInsert(connection);
    }
}
