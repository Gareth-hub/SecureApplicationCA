package com.example.staff;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController {

    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button contactButton;




    public void contactButtonOnAction(ActionEvent event){
        contactForm();
    }

    public void registerpageButtonOnAction(ActionEvent event){
        registerForm();
    }

    public void loginpageButtonOnAction(ActionEvent event){
        loginForm();
    }


    public void exitButtonOnAction(ActionEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    public void contactForm(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 420);
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
            Stage stage = (Stage) contactButton.getScene().getWindow();
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
    public void registerForm(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 520);
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(scene);
            Stage.show();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}