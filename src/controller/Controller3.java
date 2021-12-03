package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;
import model.DAO1;



public class Controller3  {

    @FXML
    private TextField Id;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    private Client client;
    @FXML
    private Controller mainController;

    @FXML
    private void initialize() {
    }

    @FXML
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setPerson(Client client) {
        this.client = client;

        firstNameField.setText(client.getName());
        lastNameField.setText(client.getLastName());
        phoneField.setText(client.getPhone());
        Id.setText(String.valueOf(client.getId()));
    }




    @FXML
    private void handleCancel(javafx.event.ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Заполните поле имени!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Заполните поле фамилии!\n";
        }
        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "Заполните поле номера телефона!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Поля ввода не заполнены!");
            alert.setHeaderText("Заполните все поля ввода!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void handleOk(javafx.event.ActionEvent event) {
        Controller controller = new Controller();
        if (isInputValid()) {
            client.setName(firstNameField.getText());
            client.setLastName(lastNameField.getText());
            client.setPhone(phoneField.getText());

            try {
                DAO1 dao = new DAO1();
                dao.updateClient(client);
                mainController.updateTable();
                controller.DialogInfo("Данные клиента успешно изменены! ");




            }catch (Exception e){
                controller.DialogError("Не удалось изменить данные клиента!");
                e.printStackTrace();
            }

        }

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


}