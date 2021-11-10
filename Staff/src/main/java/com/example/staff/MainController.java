package com.example.staff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfSalary;
    @FXML
    private TableView<Staff> tvStaff;
    @FXML
    private TableColumn<Staff, Integer> colId;
    @FXML
    private TableColumn<Staff, String> colName;
    @FXML
    private TableColumn<Staff, String> colEmail;
    @FXML
    private TableColumn<Staff, Integer> colAge;
    @FXML
    private TableColumn<Staff, Integer> colSalary;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button closeButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField codeTextField;


    public void btnInsertOnAction(ActionEvent event){
        if((tfId.getText().isBlank()==false) && (tfSalary.getText().isBlank()==false)
        && (tfAge.getText().isBlank()==false) && (tfEmail.getText().isBlank()==false)
        && (tfName.getText().isBlank()==false)){
            checkID();
        }else{
            errorLabel.setText("Please ensure all fields have been filled out to insert information");
        }
    }

    public void btnDeleteOnAction(ActionEvent event){
        if((tfId.getText().isBlank()==false) && (tfSalary.getText().isBlank()==false)
                && (tfAge.getText().isBlank()==false) && (tfEmail.getText().isBlank()==false)
                && (tfName.getText().isBlank()==false)){
           check2Code();
        }
        else{
            errorLabel.setText("Please ensure all fields have been filled out to delete information");
        }
    }

    public void btnUpdateOnAction(ActionEvent event){
        if((tfId.getText().isBlank()==false) && (tfSalary.getText().isBlank()==false)
                && (tfAge.getText().isBlank()==false) && (tfEmail.getText().isBlank()==false)
                && (tfName.getText().isBlank()==false)){
            check1Code();
        }
        else{
            errorLabel.setText("Please ensure all fields have been filled out with new data to update information");
        }
    }

    public void closeButtonOnAction(ActionEvent event){
        home();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStaff();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db", "root","48BarnHall23$");
            return conn;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public ObservableList<Staff> getStaffList(){
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM staff";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Staff staff;
            while(rs.next()){
                staff = new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age"),rs.getInt("salary"));
                staffList.add(staff);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return staffList;
    }

    public void showStaff(){
        ObservableList<Staff> list = getStaffList();

        colId.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
        colAge.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("age"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("salary"));

        tvStaff.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO staff VALUES (" + tfId.getText() + ",'" + tfName.getText() + "','" + tfEmail.getText() + "',"
                + tfAge.getText() + "," + tfSalary.getText() + ")";
        executeQuery(query);
        showStaff();
    }
    private void updateRecord(){
        String query = "UPDATE  staff SET name  = '" + tfName.getText() + "', email = '" + tfEmail.getText() + "', age = " +
                tfAge.getText() + ", salary = " + tfSalary.getText() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showStaff();
    }
    private void deleteButton(){
        String query = "DELETE FROM staff WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showStaff();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void checkID(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String id = tfId.getText();
        String selectFields = "SELECT id from staff where id = '";
        String selectValues = id + "'";
        String QueryCheck = selectFields + selectValues;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeQuery(QueryCheck);
            ResultSet rs = statement.executeQuery(QueryCheck);
            if(rs.next()){
              errorLabel.setText("ID assigned already, please use new ID");
            }
            else{
                checkCode();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void checkCode(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String id = codeTextField.getText();
        String selectFields = "SELECT clearancecode from user_account where clearancecode = '";
        String selectValues = id + "'";
        String QueryCheck = selectFields + selectValues;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeQuery(QueryCheck);
            ResultSet rs = statement.executeQuery(QueryCheck);
            if(rs.next()){
                errorLabel.setText("Code Matched");
                insertRecord();
            }
            else{
                errorLabel.setText("Invalid code, please use assigned code");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void check1Code(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String id = codeTextField.getText();
        String selectFields = "SELECT clearancecode from user_account where clearancecode = '";
        String selectValues = id + "'";
        String QueryCheck = selectFields + selectValues;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeQuery(QueryCheck);
            ResultSet rs = statement.executeQuery(QueryCheck);
            if(rs.next()){
                errorLabel.setText("Code Matched");
                updateRecord();
            }
            else{
                errorLabel.setText("Invalid code, please use assigned code");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void check2Code(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String id = codeTextField.getText();
        String selectFields = "SELECT clearancecode from user_account where clearancecode = '";
        String selectValues = id + "'";
        String QueryCheck = selectFields + selectValues;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeQuery(QueryCheck);
            ResultSet rs = statement.executeQuery(QueryCheck);
            if(rs.next()){
                errorLabel.setText("Code Matched");
                deleteButton();
            }
            else{
                errorLabel.setText("Invalid code, please use assigned code");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}