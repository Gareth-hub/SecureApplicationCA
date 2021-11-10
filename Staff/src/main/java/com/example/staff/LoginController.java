package com.example.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField enterpasswordField;
    @FXML
    private Button homeButton;
    @FXML
    private Button loginButton;
    @FXML
    private int logincounter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if(usernameTextfield.getText().isBlank() == false && enterpasswordField.getText().isBlank() ==false){
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter your login details");
        }
    }




    public void homepageButtonOnAction(ActionEvent event){
        homeForm();
    }

    public void homeForm(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 420);
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(scene);
            Stage.show();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }

    public void Security(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("security.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 420);
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(scene);
            Stage.show();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextfield.getText() + "' AND password ='" + enterpasswordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Success");
                    Security();
                }
                else if(logincounter > 3){
                    loginMessageLabel.setText("Failure");
                    homeForm();
                }
                else{
                    loginMessageLabel.setText("Incorrect Login Details " + (4-logincounter) + " tries left");
                    logincounter++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
