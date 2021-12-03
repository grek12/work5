package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO3 {
    private Connection connection;

    public DAO3(){

        connection = new ConnectionFactory().getConnection();
    }

    public void insert(Seller seller){
        String sql = "insert into seller (firstname,lastname,phone) values (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,seller.getName());
            statement.setString(2,seller.getLastName());
            statement.setString(3,seller.getPhone());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void update(Seller seller){

        String sql = "update seller set firstname=?, lastname=?, phone=? where idseller=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,seller.getName());
            statement.setString(2,seller.getLastName());
            statement.setString(3,seller.getPhone());
            statement.setInt(4, seller.getId());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void deleteClient(Integer idSeller){

        String sql = "delete from seller where idseller = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,idSeller);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public ObservableList<Seller> select(){

        ObservableList<Seller> sellers = FXCollections.observableArrayList();

        String sql = "select * from seller ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Seller seller = new Seller();

                seller.setId(resultSet.getInt("idseller"));
                seller.setName(resultSet.getString("firstname"));
                seller.setLastName(resultSet.getString("lastname"));
                seller.setPhone(resultSet.getString("phone"));


                sellers.add(seller);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return  sellers;
    }
}
