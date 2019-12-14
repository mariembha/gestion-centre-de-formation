/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionformation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML private Label actiontarget;
    
    @FXML private TextField username_box;
    
    @FXML private PasswordField password_box;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        boolean isValid = isValidCredentials(username_box.getText(),password_box.getText());
        if(isValid){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("visiteur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
            
          
        }else{
            actiontarget.setText("verify your logins !!!");
        }
    }
    
    private boolean isValidCredentials(String username, String password)
    {
        boolean let_in = false;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        try {
            c = ConnectionService.openConnection();
            System.out.println("Opened database successfully");
            preparedStatement = c.prepareStatement(
                    "SELECT * FROM Users WHERE USERNAME= ? AND PASSWORD = ?" );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            
            while ( rs.next() ) {
                 if (rs.getString("USERNAME") != null && rs.getString("PASSWORD") != null) { 
                     String  usernameResult = rs.getString("USERNAME");
                     String passwordResult = rs.getString("PASSWORD");
                     let_in = true;
                 }  
            }
            rs.close();
            preparedStatement.close();
            c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Operation done successfully");
            return let_in;
        
    }
     
    
}
