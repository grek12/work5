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

public class Controller5 {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;


    @FXML
    private Controller mainController;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleCancel(javafx.event.ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    @FXML
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
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
        Seller seller = new Seller();
        if (isInputValid()) {
            seller.setName(firstNameField.getText());
            seller.setLastName(lastNameField.getText());
            seller.setPhone(phoneField.getText());

            try {
                DAO3 dao3 = new DAO3();
                dao3.insert(seller);
                mainController.updateTable4();
                controller.DialogInfo("Продавец успешно добавлен! ");



            }catch (Exception e){
                controller.DialogError("Не удалось добавить продавца!");

                e.printStackTrace();
            }

        }

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
