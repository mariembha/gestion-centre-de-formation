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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


   
   



public class DemandeController implements Initializable {
    
    @FXML
    private TableView<demande> table;
    @FXML
    private TableColumn<demande, Integer> id;
    @FXML
    private TableColumn<demande, String> nom;
    @FXML
    private TableColumn<demande, String> prenom;
    @FXML
    private TableColumn<demande, String> email;
    @FXML
    private TableColumn<demande, String> CIN;
    @FXML
    private TableColumn<demande, String> fonction;
    @FXML
    private TableColumn<demande, String> tel;
    @FXML
    private TableColumn<demande, Integer> codeformation;
    @FXML
    private TableColumn actions;
    @FXML private Label actionMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
         actions.setCellFactory( ActionButtonTableCell.<demande>forTableColumn("Remove", (demande s) -> {
            boolean isDeleted;
              try {
                  //supression in db
                  isDeleted = supprimerdemande(s);
                  if(isDeleted){
                 table.getItems().remove(s);
                 actionMsg.setText("La demande est supprimée avec succés!");
                }else {
                      actionMsg.setText("Une erreur est survenue lors de suppression Merci de ressayer !!");
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(DemandeController.class.getName()).log(Level.SEVERE, null, ex);
              }
            
            return s;
        }));
        id.setCellValueFactory(new PropertyValueFactory<demande, Integer>("id")); 
        nom.setCellValueFactory(new PropertyValueFactory<demande, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<demande, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<demande, String>("email"));
        CIN.setCellValueFactory(new PropertyValueFactory<demande, String>("CIN"));
        fonction.setCellValueFactory(new PropertyValueFactory<demande, String>("fonction"));
        tel.setCellValueFactory(new PropertyValueFactory<demande, String>("tel"));
        codeformation.setCellValueFactory(new PropertyValueFactory<demande, Integer>("codeformation"));
        try{
            loadstagiares();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
        // TODO
    
 public void loadstagiares() throws SQLException {
    
        ObservableList<demande> demandes = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            c = ConnectionService.openConnection();
            //2.  create a statement object
            statement = c.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM demande");
            
            //4.  create volunteer objects from each record
            while (resultSet.next())
            {
               
                demande newdemande = new demande (resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("CIN"),resultSet.getString("fonction"),resultSet.getString("tel"),resultSet.getInt ("codeformation"));
                                                 
                                                       
               
                
                demandes.add(newdemande);
            }
            
            table.getItems().addAll(demandes);
            
        } catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            if (c != null)
                c.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
 }
 public void gobackmenu(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("visiteur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
 
}
  private boolean supprimerdemande(demande s) throws SQLException{
        Connection c = null;
        PreparedStatement preparedStatement = null;
        try{
            //1. connect to the database
            c = ConnectionService.openConnection();
           //get your object 
           
            //2.  create a preparedStatement object
            preparedStatement = c.prepareStatement(
                    "DELETE FROM `demande`  "
                            + "WHERE id = ? " );
            preparedStatement.setInt(1, s.getId());
            
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

