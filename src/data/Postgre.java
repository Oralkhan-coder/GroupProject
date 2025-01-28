package data;

import data.interfaces.JB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Postgre implements JB {
 private String host;
 private String username;
 private String password;
 private String dbName;

 private Connection connection;
 public Postgre(String host, String username, String password, String dbName){
 setHost(host);
 setUsername(username);
 setPassword(password);
 setDbName(dbName);
 }


 public String getHost() {
  return host;
 }

 public void setHost(String host) {
  this.host = host;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getDbName() {
  return dbName;
 }

 public void setDbName(String dbName) {
  this.dbName = dbName;
 }
 @Override
 public Connection getConnection() {
  String connectionUrl=host + "/" +dbName;
  try {
   if (connection!=null && !connection.isClosed()){
    return connection;
   }
   Class.forName("org.postgresql.Driver");
   connection= DriverManager.getConnection(connectionUrl,username,password);
   return connection;
  } catch (Exception e){
   System.out.println("Failed: "+ e.getMessage());
  }
  return null;
 }

 @Override
 public void close() {
  if (connection!=null){
   try {
    connection.close();
   } catch (SQLException e) {
    System.out.println("Failed: "+getConnection());
   }
  }

 }
}
