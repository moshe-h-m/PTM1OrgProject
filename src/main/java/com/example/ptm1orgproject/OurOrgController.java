//package com.example.ptm1orgproject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URL;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.FileChooser;
//
//
//public class OurOrgController implements Initializable {
//
//    dbConnection db = new dbConnection();
//
//
//
//    @FXML
//    private TextField namefld;
//    @FXML
//    private TextField addfld;
//    private File mainfile;
//
//    @FXML
//    public void InsertPressed() throws SQLException, FileNotFoundException {
//        //to insert values
//        PreparedStatement psmnt = db.getCon().prepareStatement("insert into user_table(username, useraddress) values(?,?,?)");
//        FileInputStream fis = new FileInputStream(mainfile);
//        psmnt.setString(1, namefld.getText());
//        psmnt.setString(2, addfld.getText());
//        psmnt.setBinaryStream(3, (InputStream) fis, (int) mainfile.length());
//
//        int s = psmnt.executeUpdate();
//        if (s > 0) {
//            System.out.println("Uploaded successfully !");
//        } else {
//            System.out.println("unsucessfull to upload image.");
//        }
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            db.dbConnect();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(OurOrgController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(OurOrgController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//}