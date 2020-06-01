/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


Statement statement; 
    @FXML
    private TextField id_tf; 
    @FXML
    private TextField name_tf; 
    @FXML
    private TextField password_tf; 
    @FXML
    private TextField phone_tf; 
    @FXML
    private TextField email_tf;
    @FXML
    private TextField gender_tf;
    @FXML
    private TextField adress_tf;
    @FXML
    private TableView<Customer> customer_table; 
    @FXML
    private TableColumn<Customer, Integer> cus_id_col;
    @FXML
    private TableColumn<Customer, String> cus_name_col; 
    @FXML
    private TableColumn<Customer, String> cus_pass_col; 
    @FXML
    private TableColumn<Customer, Integer> cus_phone_col; 
    @FXML
    private TableColumn<Customer, String> cus_email_col; 
    @FXML
    private TableColumn<Customer, String> cus_gender_col;
    @FXML
    private TableColumn<Customer, String> cus_adress_col;
    @FXML
    private TableColumn<Customer, Integer> cus_num_of_safe_col; 
    @FXML
    private TableView<safes> safes_table;
    @FXML
    private TableColumn<safes, Integer> customer_id_col; 
    @FXML
    private TableColumn<safes, Integer> lock_number_col;
    private ResultSet resultSet;
    Logger x;
     @FXML
    private ComboBox<Integer> Cid_combob; 
     @FXML
    private ComboBox<Integer> locknum_combob; 
    @FXML
    private TextField contain_tf_2;
    @FXML
    private TableView<safes> cus_table_safe;
    @FXML
    private TableColumn<safes, Integer> locknum_col_2; 
    @FXML
    private TableColumn<safes, String> contain_col_2;
    @FXML
    private TextField log_user;
    @FXML
    private TextField log_pass;
    @FXML
    private RadioButton log_r_cus;
    @FXML
    private ToggleGroup choose;
    @FXML
    private RadioButton log_r_emp;
    @FXML
    private Button log_btn;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wwwmo
 */
public class EmpController implements Initializable {
Statement statement; 
@FXML
    private BorderPane main_pane;
    @FXML
    private TextField id_tf;
    @FXML
    private TextField name_tf;
    @FXML
    private TextField password_tf;
    @FXML
    private TextField phone_tf;
    @FXML
    private TextField email_tf;
    @FXML
    private TextField gender_tf;
    @FXML
    private TextField adress_tf;
    @FXML
    private Button show_btn;
    @FXML
    private Button add_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private ComboBox<Integer> Cid_combob;
    @FXML
    private Button add_safe_btn;
    @FXML
    private Button delete_safe_btn;
    @FXML
    private TableView<Customer> customer_table;
    @FXML
    private TableColumn<Customer, Integer> cus_id_col;
    @FXML
    private TableColumn<Customer, String> cus_name_col;
    @FXML
    private TableColumn<Customer, String> cus_pass_col;
    @FXML
    private TableColumn<Customer, Integer> cus_phone_col;
    @FXML
    private TableColumn<Customer, String> cus_email_col;
    @FXML
    private TableColumn<Customer, String> cus_gender_col;
    @FXML
    private TableColumn<Customer, String> cus_adress_col;
    @FXML
    private TableColumn<Customer, Integer> cus_num_of_safe_col;
    @FXML
    private TableView<safes> safes_table;
    @FXML
    private TableColumn<safes, Integer> customer_id_col;
    @FXML
    private TableColumn<safes, Integer> lock_number_col;
      Logger x;
      ResultSet resultSet;
    @FXML
    private Button logout_emp;
  

    /**
     * Initializes the controller class.
     
     *
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logout_emp.setText("customer");
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
            this.in();
     this.show_safes();
        } catch (Exception ex) {
            x.warning("wrong on database ");
            ex.printStackTrace();
        }

        customer_table.getSelectionModel().selectedItemProperty().addListener(
                event -> show_selected_customer()
        );
        safes_table.getSelectionModel().selectedItemProperty().addListener(
                event -> show_selected_safes()
        );
        
   
    }

    @FXML
    private void add_btn_action(ActionEvent event) throws SQLException {
        Integer id = Integer.parseInt(id_tf.getText());
        Integer phone = Integer.parseInt(phone_tf.getText());
        String username = name_tf.getText();
        String pass = password_tf.getText();
        String gender = gender_tf.getText();
        String adress = adress_tf.getText();
        String email = email_tf.getText();
        String sql = "INSERT INTO `customer` (`id`, `username`, `pass`, `phone`, `gender`, `adress`, `num_of_locker`, `email`) VALUES (" + id + ",'" + username + "','" + pass + "','" + phone + "','" + gender + "','" + adress + "','0','" + email + "')";
        //INSERT INTO `customer` (`id`, `username`, `pass`, `gender`, `adress`, `num_of_locker`, `email`) VALUES ('12', '12', '12', '12', '12', '12', '12');
        this.statement.executeUpdate(sql);
        this.clear_cus();
        this.show_cus();
        this.combo();
    }

    @FXML
    private void show_btn_action(ActionEvent event) throws SQLException {
        this.show_cus();
    }

    @FXML
    private void update_btn_action(ActionEvent event) throws SQLException {
        Integer id = Integer.parseInt(id_tf.getText());
        Integer phone = Integer.parseInt(phone_tf.getText());
        String username = name_tf.getText();
        String password = password_tf.getText();
        String email = email_tf.getText();
        String gender = gender_tf.getText();
        String adress = adress_tf.getText();
        String sql = "update customer set username = '" + username + "',pass = '" + password + "', phone ='" + phone + "',gender = '" + gender + "',adress = '" + adress + "',email = '" + email + "' where id = " + id;
        this.statement.executeUpdate(sql);
        this.clear_cus();
        this.show_cus();
        this.combo();
    }

    @FXML
    private void delete_btn_action(ActionEvent event) throws SQLException {
        String sql1 = "delete from safes where  cus_id =" + Integer.parseInt(id_tf.getText());
        this.statement.executeUpdate(sql1);
        String sql = "delete from customer where  id =" + Integer.parseInt(id_tf.getText());
        this.statement.executeUpdate(sql);
        this.clear_cus();
        this.show_cus();
        this.combo();
        this.clear_safes();
        this.show_safes();
        
    }



    private void in() throws SQLException {
        cus_id_col.setCellValueFactory(new PropertyValueFactory("id"));
        cus_name_col.setCellValueFactory(new PropertyValueFactory("username"));
        cus_pass_col.setCellValueFactory(new PropertyValueFactory("pass"));
        cus_phone_col.setCellValueFactory(new PropertyValueFactory("phone"));
        cus_email_col.setCellValueFactory(new PropertyValueFactory("email"));
        cus_gender_col.setCellValueFactory(new PropertyValueFactory("gender"));
        cus_adress_col.setCellValueFactory(new PropertyValueFactory("adress"));
        cus_num_of_safe_col.setCellValueFactory(new PropertyValueFactory("num_of_locker"));
        customer_id_col.setCellValueFactory(new PropertyValueFactory("cus_id"));
        lock_number_col.setCellValueFactory(new PropertyValueFactory("lock_number"));  
    }

    private void show_cus() throws SQLException {
        resultSet = this.statement.executeQuery("Select * From customer");
        customer_table.getItems().clear();

        while (resultSet.next()) {

            Customer cus = new Customer();
            cus.setId(resultSet.getInt("id"));
            cus.setUsername(resultSet.getString("username"));
            cus.setPass(resultSet.getString("pass"));
            cus.setEmail(resultSet.getString("email"));
            cus.setPhone(resultSet.getInt("phone"));
            cus.setGender(resultSet.getString("gender"));
            cus.setAdress(resultSet.getString("adress"));
            cus.setNum_of_locker(resultSet.getInt("num_of_locker"));

            customer_table.getItems().add(cus);
            x.info("c");
        }
    }
    private void show_safes() throws SQLException {
        resultSet = this.statement.executeQuery("Select * From safes");
        safes_table.getItems().clear();

        while (resultSet.next()) {
            safes safe = new safes();
            safe.setCus_id(resultSet.getInt("cus_id"));
            safe.setLock_number(resultSet.getInt("lock_number"));            
            safes_table.getItems().add(safe);
            x.info("c");
        }
        this.combo();
    }
    

    private void clear_cus() {
        id_tf.setText("");
        name_tf.setText("");
        password_tf.setText("");
        phone_tf.setText("");
        email_tf.setText("");
        gender_tf.setText("");
        adress_tf.setText("");
        customer_table.getItems().clear();
    }
    private void clear_safes() {
        Cid_combob.getItems().clear();
        safes_table.getItems().clear();
    }
     
    private safes show_selected_safes() {
        safes safe = safes_table.getSelectionModel().getSelectedItem();
       
     return safe;
    }
   

    private void show_selected_customer() {
        Customer stu = customer_table.getSelectionModel().getSelectedItem();
        if (stu != null) {
            id_tf.setText(String.valueOf(stu.getId()));
            phone_tf.setText(String.valueOf(stu.getPhone()));
            name_tf.setText(stu.getUsername());
            password_tf.setText(stu.getPass());
            gender_tf.setText(stu.getGender());
            adress_tf.setText(stu.getAdress());
            email_tf.setText(stu.getEmail());
            customer_table.getItems().clear();
        }

    }
    private void combo() throws SQLException{
        Cid_combob.getItems().clear();
    resultSet = this.statement.executeQuery("Select * From customer");
        customer_table.getItems().clear();

        while (resultSet.next()) {
            Cid_combob.getItems().add(resultSet.getInt("id"));
            x.info("c");
        }   
    }
    

    @FXML
    private void add_safe_btn_action(ActionEvent event) throws SQLException {
        Integer id = Cid_combob.getValue();
        
        
        String sql = "INSERT INTO `safes` (`lock_number`, `cus_id`, `contain`) VALUES (NULL,'"+id+"','')";
        this.statement.executeUpdate(sql);       
        this.clear_safes();
        this.show_safes();
        this.combo();
        String sql1 = "UPDATE `customer` SET `num_of_locker` = `num_of_locker`+1 where id = '"+id+"'";
        this.statement.executeUpdate(sql1);
    }

    @FXML
    private void delete_safe_btn_action(ActionEvent event) throws SQLException {
        safes s = this.show_selected_safes();
         if (s != null) {     
            safes_table.getItems().clear();
            String sql = "delete from safes where  `safes`.`lock_number`= '" +s.getLock_number()+ "'";
            
        this.statement.executeUpdate(sql);
        String sql1 = "UPDATE `customer` SET `num_of_locker` = `num_of_locker`-1 where id = '"+s.getCus_id()+"'";
        this.statement.executeUpdate(sql1);
        this.clear_safes();
        this.show_safes();
        this.combo();
        }
        
    }
   


    @FXML
    private void log_out_btn_action(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("customer.fxml"));  
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }
    }