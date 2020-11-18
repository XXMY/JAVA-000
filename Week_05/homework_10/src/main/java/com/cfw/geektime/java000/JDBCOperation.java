package com.cfw.geektime.java000;

import java.sql.*;

public class JDBCOperation {

    public void insert(Statement statement) throws SQLException {
        StringBuilder insertSqlBuilder = new StringBuilder();
        insertSqlBuilder.append("INSERT INTO student(`number`,`name`,`age`,`class`,`department`) VALUES ");
        for (int i=0;i<10;i++){
            insertSqlBuilder.append("(");
            insertSqlBuilder.append("'202001010");
            insertSqlBuilder.append(i);
            insertSqlBuilder.append("',");
            insertSqlBuilder.append("'name0");
            insertSqlBuilder.append(i);
            insertSqlBuilder.append("',");
            insertSqlBuilder.append("2");
            insertSqlBuilder.append(i);
            insertSqlBuilder.append(",");
            insertSqlBuilder.append(i);
            insertSqlBuilder.append(",");
            insertSqlBuilder.append("'department0");
            insertSqlBuilder.append(i);
            insertSqlBuilder.append("'),");
        }

        insertSqlBuilder.deleteCharAt(insertSqlBuilder.length()-1);

        statement.execute(insertSqlBuilder.toString());
    }

    public void select(Statement statement) throws SQLException {
        String selectSql = "SELECT name,age FROM student";
        ResultSet resultSet = statement.executeQuery(selectSql);
        if (resultSet == null) {
            return ;
        }
        System.out.println("查询的数据为：");
        String format = "name:%s,age:%s";
        while(resultSet.next()){
            System.out.println(String.format(format,resultSet.getString("name"),resultSet.getString("age")));
        }
        resultSet.close();
    }

    public void update(Statement statement) throws SQLException {
        String updateSql = "UPDATE student SET age = age + 10 WHERE age < 25";
        statement.execute(updateSql);
    }

    public void delete(Statement statement) throws SQLException {
        String deleteSql = "DELETE FROM student WHERE id > 5";
        statement.execute(deleteSql);
    }

    public void preparedStatementQuery(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name,age FROM student WHERE age > ?");
        preparedStatement.setInt(1,25);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet == null) {
            return ;
        }

        String format = "name:%s,age:%s";
        while(resultSet.next()){
            System.out.println(String.format(format,resultSet.getString("name"),resultSet.getString("age")));
        }

        resultSet.close();
        preparedStatement.addBatch();
        preparedStatement.close();
    }

    public void preparedStatementBathInsert(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student(`number`,`name`,`age`,`class`,`department`) VALUES(?,?,?,?,?)");
        for (int i=30;i<50;i++){
            preparedStatement.setString(1,"20200101"+i);
            preparedStatement.setString(2,"name"+i);
            preparedStatement.setInt(3,i);
            preparedStatement.setInt(4,i);
            preparedStatement.setString(5, "department"+i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        connection.commit();
//        connection.rollback();

        preparedStatement.close();
    }
}
