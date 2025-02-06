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
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO users (username, password, email, role" +
                    (user.getRole().equals("STUDENT") ? ", level" : "") + ") VALUES (?, ?, ?, ?" +
                    (user.getRole().equals("STUDENT") ? ", ?" : "") + ")";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            if (user.getRole().equals("STUDENT")) {
                stmt.setInt(5, user.getLevel());
            }
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role"),
                        result.getInt("level")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getUserByRole(String role) {
        List<User> users = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE role = ?")) {
            stmt.setString(1, role);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                users.add(new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role"),
                        result.getInt("level")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getAllStudents() {
        return List.of();
    }

    @Override
    public User getStudentWithCourses(int id) {
        return null;
    }

    @Override
    public boolean updateUser(int id, String username, String password, String email, String role, int level) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int level = result.getString("role").equals("STUDENT") ? result.getInt("level") : 0;
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role"),
                        level
                );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery("SELECT * FROM users")) {
            while (result.next()) {
                users.add(new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role"),
                        result.getInt("level")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
