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
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class NouveauformationController implements Initializable {
    @FXML private TextField code;
    @FXML private TextField nom;
     @FXML private TextField tarif;
     @FXML private TextField nombreh;
     @FXML private Label submitMsg;
             

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML public void AjoutSubmit (ActionEvent event) throws IOException, SQLException {
      boolean isAdded = this.Nouveauformation();
      if(isAdded){
          this.submitMsg.setText("Le nouveau formation est ajouté avec succés !");
      }else{
          this.submitMsg.setText("Une erreur est survenue lors de sauvgarde Merci de ressayer !!");
      }
    }
     private boolean Nouveauformation() throws SQLException{
        
        
        
        Connection c = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1. connect to the database
            c = ConnectionService.openConnection();
           //get your object 
           
            //2.  create a preparedStatement object
            preparedStatement = c.prepareStatement(
                    "INSERT INTO `formation` (`code`,`nom`, `tarif`, `nombreh`) "
                            + "VALUES (?, ?, ?, ?);" );
            preparedStatement.setInt(1, Integer.parseInt(code.getText()));
            preparedStatement.setString(2, nom.getText());
            preparedStatement.setInt(3, Integer.parseInt(tarif.getText()));
            preparedStatement.setInt(4, Integer.parseInt(nombreh.getText()));
           
            
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
     public void gotoformation(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("formationadmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
}
