package data.interfaces;

import java.sql.Connection;

public interface JB {
    Connection  getConnection();
    void close();
 }
