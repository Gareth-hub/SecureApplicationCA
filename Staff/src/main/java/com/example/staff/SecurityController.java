package com.example.staff;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SecurityController {
    @FXML
    private Button finishButton;
    @FXML
    private TextField answerTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label usernameLabel;





    public void finish(ActionEvent event)  {
        if ((answerTextField.getText().equals("102"))){
            validateUsername();
        }
        else if((answerTextField.getText().isBlank()==true) && (nameTextField.getText().isBlank()==true)) {
          usernameLabel.setText("Please fill in both fields");
        }else if((answerTextField.getText().isBlank()==false) && (nameTextField.getText().isBlank()==false)){
            home();
        }else{
            if((answerTextField.getText().isBlank()==false) && (nameTextField.getText().isBlank()==true)){
                usernameLabel.setText("Please enter a username");
            }
            else{
                usernameLabel.setText("Please enter an answer");
            }
        }
    }

    public void validateUsername(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + nameTextField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1){
                    Main();
                }
                else{
                    usernameLabel.setText("Invalid username, please try again");
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void Main(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(scene);
            Stage.show();
            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }
    public void home(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 420);
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }
    }
