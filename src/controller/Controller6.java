package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;
import model.DAO1;
import model.DAO3;
import model.Seller;

public class Controller6 {
    @FXML
    private TextField Id;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    private Seller seller;
    @FXML
    private Controller mainController;

    @FXML
    private void initialize() {
    }

    @FXML
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setPerson(Seller seller) {
        this.seller = seller;

        firstNameField.setText(seller.getName());
        lastNameField.setText(seller.getLastName());
        phoneField.setText(seller.getPhone());
        Id.setText(String.valueOf(seller.getId()));
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
            seller.setName(firstNameField.getText());
            seller.setLastName(lastNameField.getText());
            seller.setPhone(phoneField.getText());

            try {
                DAO3 dao3 = new DAO3();
                dao3.update(seller);
                mainController.updateTable4();
                controller.DialogInfo("Данные продавца успешно изменены! ");




            }catch (Exception e){
                controller.DialogError("Не удалось изменить данные продавца!");
                e.printStackTrace();
            }

        }

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
