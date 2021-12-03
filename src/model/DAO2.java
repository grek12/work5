package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO2 {
    private Connection connection;

    public DAO2(){

        connection = new ConnectionFactory().getConnection();
    }

    public void insertContract(Contract contract){
        String sql = "insert into contract (idseller, idclient , date, amount ) values (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, contract.getIdSeller());
            statement.setInt(2, contract.getIdClient());
            statement.setDate(3,contract.getDate());
            statement.setDouble(4, contract.getAmount());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }



    public void deleteContract(Integer idContract){

        String sql = "delete from contract where idcontract = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,idContract);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public ObservableList<Contract> selectContract(){

        ObservableList<Contract> contracts = FXCollections.observableArrayList();

        String sql = "select * from contract ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Contract contract = new Contract();

                contract.setIdContract(resultSet.getInt("idcontract"));
                contract.setDate(resultSet.getDate("date"));
                contract.setAmount(resultSet.getDouble("amount"));
                contract.setIdSeller(resultSet.getInt("idseller"));
                contract.setIdClient(resultSet.getInt("idclient"));

                contracts.add(contract);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return  contracts;
    }
    public ObservableList<Contract2> selectContract2(){

        ObservableList<Contract2> contracts = FXCollections.observableArrayList();

        String sql = "SELECT contract.idcontract, client.lastname as clientname, seller.lastname as sellername, contract.date, contract.amount\n" +
                "                FROM contract JOIN client ON contract.idclient = client.idclient\n" +
                "                JOIN seller  ON contract.idseller = seller.idseller";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Contract2 contract2 = new Contract2();

                contract2.setIdContract(resultSet.getInt("idcontract"));
                contract2.setDate(resultSet.getDate("date"));
                contract2.setAmount(resultSet.getDouble("amount"));
                contract2.setSeller(resultSet.getString("clientname"));
                contract2.setClient(resultSet.getString("sellername"));

                contracts.add(contract2);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return  contracts;
    }



}
