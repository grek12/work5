package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;

public class Controller implements Initializable {



    @FXML
    private TableColumn<Contract2, Double> amount;

    @FXML
    private TableColumn<Client2, Double> amountAll;

    @FXML
    private TableColumn<Contract2, String> clientFam;

    @FXML
    private TableColumn<Client2, String> clientName;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableView<Contract2> contractTable1;

    @FXML
    private TableView<Client2> contractTable2;

    @FXML
    private TableColumn<Contract2, LocalDate> date;

    @FXML
    private TableColumn<Client, Integer> idClient;

    @FXML
    private TableColumn<Contract2, Integer> idContract1;

    @FXML
    private TableColumn<Seller, Integer> idSeller;

    @FXML
    private TableColumn<Client, String> lastName;

    @FXML
    private TableColumn<Seller, String> lastName2;

    @FXML
    private TableColumn<Client, String> name;

    @FXML
    private TableColumn<Seller, String> name2;

    @FXML
    private TableColumn<Client, String> phone;

    @FXML
    private TableColumn<Seller, String> phone2;

    @FXML
    private TableColumn<Contract2, String> sellerFam;

    @FXML
    private TableView<Seller> sellerTable;

        public DAO1 dao;
        public DAO2 dao2;
    public DAO3 dao3;

        public void initialize(URL url, ResourceBundle resourceBundle) {
            dao = new DAO1();
            clientTable.setItems(dao.selectClient());
            dao2 = new DAO2();
            dao3 = new DAO3();
            sellerTable.setItems(dao3.select());
            contractTable1.setItems(dao2.selectContract2());
            contractTable2.setItems(dao.select2());
            idClient.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            idSeller.setCellValueFactory(new PropertyValueFactory<>("id"));
            name2.setCellValueFactory(new PropertyValueFactory<>("name"));
            lastName2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phone2.setCellValueFactory(new PropertyValueFactory<>("phone"));
            idContract1.setCellValueFactory(new PropertyValueFactory<>("idContract"));
            clientFam.setCellValueFactory(new PropertyValueFactory<>("Client"));
            sellerFam.setCellValueFactory(new PropertyValueFactory<>("Seller"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            clientName.setCellValueFactory(new PropertyValueFactory<>("clientFam"));
            amountAll.setCellValueFactory(new PropertyValueFactory<>("amountAll"));
        }



        @FXML
        private void addClient() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/addClient.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 410, 150);
            Stage stage = new Stage();
            stage.setTitle("Добавление нового клиента");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Controller2 controller = fxmlLoader.getController();
            controller.setMainController(this);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Не удалось создать новое окно.", e);

        }

        }
    @FXML
    private void addSeller() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/addSeller.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 410, 150);
            Stage stage = new Stage();
            stage.setTitle("Добавление нового продавца");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Controller5 controller = fxmlLoader.getController();
            controller.setMainController(this);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Не удалось создать новое окно.", e);

        }

    }

    @FXML
    private void updateSeller(Seller seller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/updateSeller.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 410, 180);
            Stage stage = new Stage();
            stage.setTitle("Изменение данных продавца");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Controller6 controller = fxmlLoader.getController();
            controller.setMainController(this);
            controller.setPerson(seller);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Не удалось создать новое окно.", e);

        }

    }

        @FXML
        private void updateClient(Client client) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/updateClient.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 410, 180);
                Stage stage = new Stage();
                stage.setTitle("Изменение данных клиента");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                Controller3 controller = fxmlLoader.getController();
                controller.setMainController(this);
                controller.setPerson(client);
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Не удалось создать новое окно.", e);

            }

        }


        @FXML
        private void deleteClient() {
            Client client = clientTable.getSelectionModel().getSelectedItem();
            if (client == null) {
                DialogError("Удаление невозможно, список клиентов пуст!");
            } else {
                if (DialogConf("Подтверждаете удаление клиента?")) {

                    try{

                        dao.deleteClient(Integer.valueOf(client.getId()));
                        DialogInfo("Клиент успешно удален!");
                        updateTable();

                    }catch(Exception e){
                        DialogError("Ошибка при удалении");
                        e.printStackTrace();
                    }
                }
            }
            }
    @FXML
    private void deleteSeller() {
        Seller seller = sellerTable.getSelectionModel().getSelectedItem();
        if (seller == null) {
            DialogError("Удаление невозможно, список продавцов пуст!");
        } else {
            if (DialogConf("Подтверждаете удаление продавца?")) {

                try{

                    dao3.deleteClient(Integer.valueOf(seller.getId()));
                    DialogInfo("Продавец успешно удален!");
                    updateTable4();

                }catch(Exception e){
                    DialogError("Ошибка при удалении");
                    e.printStackTrace();
                }
            }
        }
    }
            @FXML
            public void updateTable(){
                clientTable.setItems(dao.selectClient());
            }

    @FXML
    public void updateTable2(){
        contractTable1.setItems(dao2.selectContract2());
    }

    @FXML
    public void updateTable3(){
        contractTable2.setItems(dao.select2());
    }
    @FXML
    public void updateTable4(){
        sellerTable.setItems(dao3.select());
    }
            public void DialogError(String error){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(error);
                alert.showAndWait();
            }


        @FXML
        private void addContract( ) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/addContract.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 410, 180);
                Stage stage = new Stage();
                stage.setTitle("Добавление нового контракта");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                Controller4 controller4 = fxmlLoader.getController();
                controller4.setMainController(this);

            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Не удалось создать новое окно.", e);

            }

        }


        public void DialogInfo(String info){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText(info);

            alert.showAndWait();
        }

        private boolean DialogConf(String conf){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText(null);
            alert.setContentText(conf);

            Optional<ButtonType> opcao = alert.showAndWait();

            if (opcao.get() == ButtonType.OK){
                return true;
            }
            return false;
        }


        public void updateClient(ActionEvent event) {
           Client client = clientTable.getSelectionModel().getSelectedItem();
            if (client != null) {
           updateClient(client);
            }else {
                DialogError("Клиент не выбран");
            }
            }

    public void updateSeller(ActionEvent event) {
        Seller seller = sellerTable.getSelectionModel().getSelectedItem();
        if (seller != null) {
            updateSeller(seller);
        }else {
            DialogError("Продавец не выбран");
        }
    }

    }



