package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Controller4  {

    @FXML
    private ComboBox<Client> boxClient;

    @FXML
    private ComboBox<Seller> boxSeller;

    @FXML
    private DatePicker datePick;

    @FXML
    private TextField sumField;
    @FXML
    private Controller mainController;
    private DAO1 dao1;
    private DAO3 dao3;
    @FXML
    private void initialize() {
        DAO1 dao1 = new DAO1();
        DAO3 dao3 = new DAO3();
        boxSeller.setItems(dao3.select());
        boxClient.setItems(dao1.selectClient());
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }

    };
        datePick.setConverter(converter);
        datePick.setPromptText("yyyy-MM-dd");
    }
    @FXML
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void handleCancel(javafx.event.ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }



    private boolean isInputValid() {
        String errorMessage = "";


        if (sumField.getText() == null || sumField.getText().length() == 0) {
            errorMessage += "Заполните поле суммы!\n";
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
        Contract contract = new Contract();
        if (isInputValid()) {
            contract.setDate(Date.valueOf(datePick.getValue()));
            contract.setAmount(Float.parseFloat(sumField.getText()));
            contract.setIdClient(boxClient.getSelectionModel().getSelectedItem().getId());
            contract.setIdSeller(boxSeller.getSelectionModel().getSelectedItem().getId());


            try {
                DAO2 dao2 = new DAO2();
                dao2.insertContract(contract);
                mainController.updateTable2();
                mainController.updateTable3();
                controller.DialogInfo("Договор успешно добавлен! ");



            }catch (Exception e){
                controller.DialogError("Не удалось добавить договор!");

                e.printStackTrace();
            }

        }

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


}