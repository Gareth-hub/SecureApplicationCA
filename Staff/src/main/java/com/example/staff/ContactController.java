package com.example.staff;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ContactController implements Initializable {

    @FXML
    private ImageView helpImageView;
    @FXML
    private Button backButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label queryMessageLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField contactTextField;
    @FXML
    private TextArea queryTextArea;


    public void initialize(URL url, ResourceBundle resourceBundle){
        File helpFile = new File("src/main/resources/com/example/staff/help.png");
        Image helpImage = new Image(helpFile.toURI().toString());
        helpImageView.setImage(helpImage);
    }
    public void backButtonOnAction(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        home();
    }

    public void submitButtonOnAction(ActionEvent event){
        if((queryTextArea.getText().isBlank()==false) && (usernameField.getText().isBlank()==false)
        && (nameTextField.getText().isBlank()==false) && (contactTextField.getText().isBlank()==false)){
            queryUser();
            queryMessageLabel.setText("Thank you for your query! Press back to go to homepage");
        } else{
            queryMessageLabel.setText("Please do not leave any Text fields  blank");
        }
    }

    public void queryUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String name = nameTextField.getText() ;
        String email = contactTextField.getText();
        String username = usernameField.getText();
        String query = queryTextArea.getText();

        String insertFields = "INSERT INTO user_query (name, username, email, query) VALUES ('";
        String insertValues = name + "','" + username + "','" + email + "','" + query + "')" ;
        String insertToQuery= insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToQuery);
            queryMessageLabel.setText("Thank you for your query!");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void home(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 420);
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(scene);
            Stage.show();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}