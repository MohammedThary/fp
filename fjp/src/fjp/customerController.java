/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fjp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wwwmo
 */
public class customerController implements Initializable {

    @FXML
    private Label title_label_2;
    @FXML
    private ComboBox<Integer> locknum_combob;
    @FXML
    private TextField contain_tf_2;
    @FXML
    private Button add_btn_2;
    @FXML
    private Button take_btn_2;
    @FXML
    private Button back;
    @FXML
    private TableView<safes> cus_table_safe;
    @FXML
    private TableColumn<safes, Integer> locknum_col_2;
    @FXML
    private TableColumn<safes, String> contain_col_2;
    Logger x;
      ResultSet resultSet;
      Statement statement; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setText("back");
         locknum_col_2.setCellValueFactory(new PropertyValueFactory("lock_number"));
        contain_col_2.setCellValueFactory(new PropertyValueFactory("contain"));
        // TODO
        try {
            x = Logger.getLogger(Fjp.class.getName());
            FileHandler fx = new FileHandler(new File(".").getCanonicalPath() + "\\src\\fjp\\fml.log");
            x.addHandler(fx);
            x.info(() -> {
                return "the program start ";
            });
            // TODO
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.
                    getConnection("jdbc:mysql://127.0.0.1:3306/fpj?serverTimezone=UTC", "root", "");        
            this.statement = connection.createStatement();   
            this.combos();
            this.clear_safes1();
            this.show_safes1();
            
            
        } catch (Exception ex) {
            x.warning("database problem ");
            ex.printStackTrace();
        }
        
        cus_table_safe.getSelectionModel().selectedItemProperty().addListener(
                event -> show_selected_cus_safes()
        );
        
       
    }    

    @FXML
     private void add_btn_action_2(ActionEvent event) throws SQLException {
         
              
              
            
            Integer sd = locknum_combob.getValue(); 
            if (!(sd==null)) {
                Integer locknum = locknum_combob.getValue();
        String con = contain_tf_2.getText();
        String sql = "UPDATE `safes` SET `contain`= CONCAT(contain, ' ,"+con+"') where `safes`.`lock_number`= '" +locknum+ "'";
        this.statement.executeUpdate(sql);
        contain_tf_2.setText("");
        locknum_combob.setValue(null);
        this.clear_safes1();
        this.show_safes1(); 
        }else{
              Alert a1 = new Alert(Alert.AlertType.WARNING,"select id and tybe contain");
              a1.show();
              } 
    }
    @FXML
    private void take_btn_action_2(ActionEvent event) throws SQLException {
         
        Integer sd = locknum_combob.getValue(); 
            if (!(sd==null)) {
                Integer locknum = locknum_combob.getValue();
        String con = contain_tf_2.getText();
        String sql = "UPDATE `safes` SET `contain`= TRIM(',"+con+"' FROM contain) where `safes`.`lock_number`= '" +locknum+ "'";
        this.statement.executeUpdate(sql);
        contain_tf_2.setText("");
        locknum_combob.setValue(null);
        this.clear_safes1();
        this.show_safes1(); 
        }else{
              Alert a1 = new Alert(Alert.AlertType.WARNING,"select id and tybe contain");
              a1.show();
              } 
    }
    @FXML
    private void back_tn_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Emp.fxml"));  
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }
    private void show_safes1() throws SQLException {
        resultSet = this.statement.executeQuery("Select * From safes");
        while (resultSet.next()) {

            safes safe = new safes();
            safe.setContain(resultSet.getString("contain"));
            safe.setLock_number(resultSet.getInt("lock_number"));            
            cus_table_safe.getItems().add(safe);
            x.info("c");
        }      
    }
    private void combos() throws SQLException{
        locknum_combob.getItems().clear();
    resultSet = this.statement.executeQuery("Select * From safes");
        cus_table_safe.getItems().clear();

        while (resultSet.next()) {
       
            locknum_combob.getItems().add(resultSet.getInt("lock_number"));
            x.info("c");
        }   
    }
    private void clear_safes1() {
        cus_table_safe.getItems().clear();
    }
     private safes show_selected_cus_safes() {
        safes safe = cus_table_safe.getSelectionModel().getSelectedItem();
       
     return safe;
    }
     
}
