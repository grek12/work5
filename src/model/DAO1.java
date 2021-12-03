package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DAO1 {
    private Connection connection;

    public DAO1(){

        connection = new ConnectionFactory().getConnection();
    }

    public void insertClient(Client client){
        String sql = "insert into client (firstname,lastname,phone) values (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,client.getName());
            statement.setString(2,client.getLastName());
            statement.setString(3,client.getPhone());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void updateClient(Client client){

        String sql = "update client set firstname=?, lastname=?, phone=? where idclient=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,client.getName());
            statement.setString(2,client.getLastName());
            statement.setString(3,client.getPhone());
            statement.setInt(4, client.getId());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void deleteClient(Integer idClient){

        String sql = "delete from client where idclient = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,idClient);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public ObservableList<Client> selectClient(){

        ObservableList<Client> clients = FXCollections.observableArrayList();

        String sql = "select * from client ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Client client = new Client();

                client.setId(resultSet.getInt("idclient"));
                client.setName(resultSet.getString("firstname"));
                client.setLastName(resultSet.getString("lastname"));
                client.setPhone(resultSet.getString("phone"));


                clients.add(client);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return  clients;
    }
    public ObservableList<Client2> select2(){

        ObservableList<Client2> clients = FXCollections.observableArrayList();

        String sql = "SELECT client.lastname, sum(contract.amount)\n" +
                "                FROM contract JOIN client ON contract.idclient = client.idclient\n" +
                "GROUP BY client.lastname;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Client2 client2 = new Client2();

                client2.setClientFam(resultSet.getString("lastname"));
                client2.setAmountAll(resultSet.getDouble("sum"));



                clients.add(client2 );
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return  clients;
    }
}
