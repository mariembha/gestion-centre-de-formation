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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class FormationadminController implements Initializable {
   
    @FXML
    private TableView<formation> table;
    @FXML
    private TableColumn<formation, Integer> code;
    @FXML
    private TableColumn<formation, String> nom;
    @FXML
    private TableColumn<formation, Integer> tarif;
    @FXML
    private TableColumn<formation, Integer> nombreh;
    
    

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        code.setCellValueFactory(new PropertyValueFactory<formation, Integer>("code"));
        nom.setCellValueFactory(new PropertyValueFactory<formation, String>("nom"));
        tarif.setCellValueFactory(new PropertyValueFactory<formation, Integer>("tarif"));
        nombreh.setCellValueFactory(new PropertyValueFactory<formation, Integer>("nombreh"));
        
        try{
            loadformation();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
     public void loadformation() throws SQLException {
    
        ObservableList<formation> formations = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            c = ConnectionService.openConnection();
            //2.  create a statement object
            statement = c.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM formation");
            
            //4.  create volunteer objects from each record
            while (resultSet.next())
            {
               
                formation newformation = new formation (resultSet.getInt("code"),resultSet.getString("nom"),resultSet.getInt("tarif"),resultSet.getInt("nombreh"));
                                                 
                                                       
               
                
                formations.add(newformation);
            }
            
            table.getItems().addAll(formations);
            
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
      public void boutonAjout (ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("nouveauformation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
       public void formationvisiteur(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("visiteur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
       
        // TODO
        // TODO
    }    