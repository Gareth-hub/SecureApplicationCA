package com.example.staff;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView newtImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setpasswordField;
    @FXML
    private PasswordField confirmpasswordField;
    @FXML
    private Label confirmpasswordLabel;
    @FXML
    private Label getRegistrationResultLabel;
    @FXML
    private Label emailResultLabel;
    @FXML
    private TextField codeTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;

    public void initialize(URL url, ResourceBundle resourceBundle){
        File newtFile = new File("src/main/resources/register.png");
        Image newtImage = new Image(newtFile.toURI().toString());
        newtImageView.setImage(newtImage);
    }

    public void closeButtonOnAction(ActionEvent event){
        home();
    }

    public void loginButtonOnAction(ActionEvent event){
        loginForm();
    }

    public void registerButtonOnAction(ActionEvent event){

        if((setpasswordField.getText().equals(confirmpasswordField.getText())) &&
                (setpasswordField.getText().isBlank() == false && (confirmpasswordField.getText().isBlank() == false)
                && (firstnameTextField.getText().isBlank() == false) && (lastnameTextField.getText().isBlank()==false)
                && (codeTextField.getText().isBlank()==false) && (emailTextField.getText().isBlank()==false))){
            confirmpasswordLabel.setText("Passwords matching");
            checkUsername();
        }else{
            confirmpasswordLabel.setText("Password Fields must match & Textfields cant be empty");

        }
    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText() ;
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setpasswordField.getText();
        String email = emailTextField.getText();
        String code = codeTextField.getText();

        String insertFields = "INSERT INTO user_account(firstname, lastname, username, email, password, clearancecode) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + email + "','" +password + "','" +code + "')" ;
        String insertToRegister= insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("User has been Registered Successfully");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void Security(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("security.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 420);
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
            Stage stage = (Stage) registerButton.getScene().getWindow();
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
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }

    public void loginForm(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 420);
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
    public void checkUsername(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String username = usernameTextField.getText();
        String selectFields = "SELECT username from user_account where username = '";
        String selectValues = username + "'";
        String QueryCheck = selectFields + selectValues;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeQuery(QueryCheck);
            ResultSet rs = statement.executeQuery(QueryCheck);
            if(rs.next()){
                registrationMessageLabel.setText("User taken try "+ username + "123");
            }
            else{
                registerUser();
                Security();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}

