package repositories;

import data.interfaces.JB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final JB database;

    public UserRepository(JB database) {
        this.database = database;
    }

    @Override
    public boolean createUser(User user) {
        try {
            Connection connection = database.getConnection();
            String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(int id, String username, String password, String email, String role) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        try {
            Connection connection = database.getConnection();
            String sql= "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement= connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet result=statement.executeQuery();
            if (result.next()){
                return new User(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role"));

            }


        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public User getUserByRole(String role) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (result.next()) {
                User user = new User(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}