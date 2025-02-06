package repositories;

import data.interfaces.JB;
import models.User;
import repositories.interfaces.IAdminRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements IAdminRepository {

    private final JB database;

    public AdminRepository(JB database) {
        this.database = database;
    }


    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM users WHERE role = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, role);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("role").trim(),
                        result.getInt("level")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean deleteAnUser(int id) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteACourse(Long id) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM courses WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
}