/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionformation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class AjoutstagiareController implements Initializable {
    
    
     @FXML private Label submitMsg;
     @FXML private TextField nom;
     @FXML private TextField prenom;
     @FXML private TextField cin;
     @FXML private TextField email;
     @FXML private TextField tel;
     @FXML private TextField fonction;
     @FXML private TextField codeFormation;
     
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException, SQLException {
      boolean isAdded = this.ajouterNouveauStagaire();
      if(isAdded){
          this.submitMsg.setText("Le nouveau stagaire est ajouté avec succés !");
      }else{
          this.submitMsg.setText("Une erreur est survenue lors de sauvgarde Merci de ressayer !!");
      }
    }
    public void gobackbutton(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("stagiare.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    private boolean ajouterNouveauStagaire() throws SQLException{
        
        
        
        Connection c = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1. connect to the database
            c = ConnectionService.openConnection();
           //get your object 
           
            //2.  create a preparedStatement object
            preparedStatement = c.prepareStatement(
                    "INSERT INTO `stagiare` (`nom`, `prenom`, `email`, `CIN`, `fonction`, `tel`, `codeformation`) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?);" );
            preparedStatement.setString(1, this.nom.getText());
            preparedStatement.setString(2, prenom.getText());
            preparedStatement.setString(3, email.getText());
            preparedStatement.setString(4, cin.getText() );
            preparedStatement.setString(5, fonction.getText());
            preparedStatement.setString(6, tel.getText());
            preparedStatement.setInt(7, Integer.parseInt(codeFormation.getText()));
            
            //3.  create the SQL query
            int result = preparedStatement.executeUpdate(); 
            c.commit();
        return result > 0 ;
           
        } catch (Exception e)
        {
            System.err.println(e);
            return false;
        }
        finally
        {
            if (c != null)
                c.close();
            if(preparedStatement != null)
                preparedStatement.close();
        }
        
    }
}
