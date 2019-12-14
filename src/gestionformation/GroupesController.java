/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionformation;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import static java.lang.System.load;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.INSERT;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class GroupesController implements Initializable {

    @FXML
    private TableView<modeltable> table;
    @FXML
    private TableColumn<modeltable, String> nom;
    @FXML
    private TableColumn<modeltable, Integer> codeFormation;
    
   
   
     
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        nom.setCellValueFactory(new PropertyValueFactory<modeltable, String>("nom"));
        codeFormation.setCellValueFactory(new PropertyValueFactory<modeltable, Integer>("codeForamtion"));
        try{
            loadgroupes();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
        
    /**
     *
     * @throws SQLException
     */
   public void loadgroupes() throws SQLException {
    
        ObservableList<modeltable> groupes = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            c = ConnectionService.openConnection();
            //2.  create a statement object
            statement = c.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM groupe");
            
            //4.  create volunteer objects from each record
            while (resultSet.next())
            {
                modeltable newmodeltable = new modeltable (resultSet.getString("nom"),
                                                       resultSet.getInt ("code-formation"));
                                                       
               
                
                groupes.add(newmodeltable);
            }
            
            table.getItems().addAll(groupes);
            
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
   public void gotoajout(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ajoutergroupe.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
  

}


  
        


   

           
            
          
          
          
      
      
      
      
      
      
      
      
   


   
        
        
    



