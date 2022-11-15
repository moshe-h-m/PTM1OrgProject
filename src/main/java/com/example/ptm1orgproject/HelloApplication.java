package com.example.ptm1orgproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
//import java.awt.*;
import javafx.scene.control.TextField;
import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HelloApplication extends Application {



    volunteer v1 = new volunteer("mosh","2345","old mans",0);
    inspector darom = new inspector(v1);


    Button button1,button2,button3;
    Scene s1,s2;
    Stage mainWindow;
    @Override
    public void start(Stage stage) throws IOException {





        mainWindow = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Image prime_image = new Image( new FileInputStream("C:\\Users\\mhm23\\Desktop\\CS Y3 22\\S C 22\\PTM1\\PTM1OrgProject\\organization member.png"));
        ImageView prime_imega_view = new ImageView(prime_image);
        prime_imega_view.setX(50);
        prime_imega_view.setY(25);
        prime_imega_view.setFitHeight(845);
        prime_imega_view.setFitWidth(900);
        prime_imega_view.setPreserveRatio(true);
        VBox up_box = new VBox(10);
        up_box.setAlignment(Pos.CENTER);

        //Group root = new Group(prime_imega_view);

        HBox down_menu = new HBox(60);
        Button button1 = new Button(" start volunteering ");
        Button button2 = new Button();
        Button button3 = new Button(" exit");

        button1.setOnAction(ActionEvent ->{
            pressButton1();
        });

        button3.setOnAction(actionEvent -> CloseProgram());
        button2.setText(" add volunteer");

        button2.setOnAction(e ->{
            try {
                pressButton();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });

        down_menu.setAlignment(Pos.BASELINE_CENTER);
        down_menu.getChildren().addAll(button1,button2, button3);
        //BorderPane layout = new BorderPane();
        //layout.setBottom(down_menu);
//        Scene s2 = new Scene(layout,1000,250);

        Label text = new Label("In what way would you like to contribute?");

        up_box.getChildren().addAll(prime_imega_view,text,down_menu);
        Scene scene = new Scene(up_box,900,750);

        mainWindow.setTitle("our organization");
        //mainWindow.setScene(s2);
        mainWindow.setScene(scene);
        mainWindow.show();

        //button1.setOnAction(e ->{} );

    }

    private void pressButton1() {
        try {

            int admin_hurs;
            administrator admin;
            inspector inspect;
            volunteer v1 = null;
            Stage stage3 = new Stage();
            stage3.initOwner(mainWindow);
            GridPane gridPane = new GridPane();
            Label a_name = new Label("admin info:");
            Label i_name = new Label("____| inspect info: |____\n");
            dbConnection db = new dbConnection();
            try {
                db.dbConnect();
                String query = "select * from volunteers where manage_area IS NOT NULL";
                Statement stmt = db.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    admin_hurs = rs.getInt(4);
                    admin = new administrator(rs.getString(1),rs.getString(2),rs.getString(7));
                    System.out.println(admin.toString());
                    String query1 = "select * from volunteers where administrator IS NOT NULL";
                    Statement stmt1 = db.getCon().createStatement();
                    ResultSet rs1 = stmt.executeQuery(query1);
                    while(rs1.next()){
                        admin.getinspectors().add( inspect = new inspector(rs1.getString(1),rs1.getString(2),rs1.getString(3),(int)rs1.getInt(4)));
                        System.out.println(admin.getinspectors().get(0).toString());
                        String query2 = "select * from volunteers where manage_area IS NULL OR manage_area=' ' AND  administrator IS NULL OR administrator=' '";
                        Statement stmt2 = db.getCon().createStatement();
                        ResultSet rs2 = stmt.executeQuery(query2);
                        int i = 0;
                        while(rs2.next()){
                            admin.getinspectors().get(0).getVolunteers().add(new volunteer(rs2.getString(1),rs2.getString(2),rs2.getString(3),(int)rs2.getInt(4)));
                            System.out.println(inspect.getVolunteers().get(i++).toString());
                        }
                        String text1="    |  report  "+inspect.reporting()+" |\n";
                        i_name.setFont(Font.font("Tahoma",20));
                        i_name.setAlignment(Pos.CENTER);
                        System.out.println(text1);
//                        for(organizationMember v: inspect.getVolunteers()){
//                            if(v instanceof volunteer)
//                                v1 =(volunteer) v;
//                            System.out.println(v1.toString());
//                        }
                        //inspect.run();

                        ExecutorService executor = Executors.newFixedThreadPool(inspect.getVolunteers().size());
//                        for(inspector in: admin.getinspectors()){
//                            executor.execute((Runnable) in);
//                        }
                        for(organizationMember om: inspect.getVolunteers()){
                            if(om instanceof  volunteer){
                                volunteer vul = (volunteer)om;
                                text1+="\n * "+vul.getName()+" is voluntering\n\t"+vul.getHours_of_volunteering()+" hurs";
                                executor.execute((Runnable) vul);
                            }
                        }

                        executor.shutdown();
//
                        while (!executor.isTerminated());
                        i_name.setText("____| inspector info: |____\n\n"+text1);
                        a_name.setFont(Font.font("Helvetica",20));
                        a_name.setAlignment(Pos.CENTER);
                        String text2 = "\nreporting of vulonteer : "+admin.reporting()+"\nsalary of admin "+admin.getName()+" : "+admin.monthly_salary(admin_hurs)+"\n";
                        System.out.println(text2);
                        a_name.setText("\n____| admin info: |____"+text2+"\n");
                    }


                }
                rs.close();
                stmt.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            gridPane.add(a_name,0,1);
            gridPane.add(i_name,0,2);
            VBox infoBox = new VBox(10);
            infoBox.getChildren().addAll(gridPane);
            gridPane.setAlignment(Pos.CENTER);
            Scene sc3 = new Scene(infoBox,500,600);
            stage3.setScene(sc3);
            stage3.show();

            System.out.println("data out ");


            }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pressButton() throws Exception {
        try {

            Stage stage2 = new Stage();
            stage2.initOwner(mainWindow);
//            Label task = new Label("dir volunteer!!\nfull your details");
//            task.setLayoutX(80);
//            task.setLayoutY(50);
            GridPane gridPane = new GridPane();

            Label v_name = new Label("your name");
            v_name.setFont(Font.font("Times New Roman",20));
            v_name.setAlignment(Pos.CENTER);

            Label v_id = new Label("your id");
            v_id.setFont(Font.font("Times New Roman",20));
            v_id.setAlignment(Pos.CENTER);

            Label v_area = new Label("what is your volunteer area?");
            v_area.setFont(Font.font("Times New Roman",20));
            v_area.setAlignment(Pos.CENTER);

            Label v_hours = new Label("how much hours per week?");
            v_hours.setFont(Font.font("Times New Roman",20));
            v_hours.setAlignment(Pos.CENTER);

            Label v_inspector = new Label("your region(for volunteer)");
            v_inspector.setFont(Font.font("Times New Roman",20));
            v_inspector.setAlignment(Pos.CENTER);

            Label v_admin = new Label("your manager (for inspector)");
            v_admin.setFont(Font.font("Times New Roman",20));
            v_admin.setAlignment(Pos.CENTER);
            Label v_manage = new Label("your manage area (for admin)");
            v_manage.setFont(Font.font("Times New Roman",20));
            v_manage.setAlignment(Pos.CENTER);

            TextField tName = new TextField();
            TextField tId = new TextField();
            TextField tArea = new TextField();
            TextField tHours = new TextField();
            TextField tInspector = new TextField();//for volunteers
            TextField tAdmin = new TextField();//for inspector
            TextField tManage = new TextField();//for admin
            Button addButton = new Button("contribute");
            addButton.setFont(Font.font(null, FontWeight.BOLD, 36));
            addButton.setStyle("-fx-background-color: PINK");
            addButton.setPrefHeight(60);


            addButton.setOnAction(ActionEvent -> {
                dbConnection db = new dbConnection();
                try {
                    //Connection con1 = DriverManager.getConnection()
                    db.dbConnect();
                    String query = "insert into volunteers(vname, vid, varea, contributeHuors, inspector, administrator, manage_area)values(?,?,?,?,?,?,?)";
                    PreparedStatement ps = db.getCon().prepareStatement(query);
                    ps.setString(1, tName.getText());
                    ps.setString(2,tId.getText());
                    ps.setString(3,tArea.getText());
                    ps.setString(4,tHours.getText());
                    ps.setString(5,tInspector.getText());
                    ps.setString(6,tAdmin.getText());
                    ps.setString(7,tManage.getText());
                    ps.execute();
                    System.out.println("data insert ");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                //Platform.exit();
                try {
                    answerVolunteer();
                } catch (volunteerExeption e) {
                    throw new RuntimeException(e);
                }
                stage2.close();
            });
            gridPane.add(v_name,0,0);
            gridPane.add(v_id,0,1);
            gridPane.add(v_area,0,2);
            gridPane.add(v_hours,0,3);
            gridPane.add(v_inspector,0,4);
            gridPane.add(v_admin,0,5);
            gridPane.add(v_manage,0,6);
            gridPane.add(tName,1,0);
            gridPane.add(tId,1,1);
            gridPane.add(tArea,1,2);
            gridPane.add(tHours,1,3);
            gridPane.add(tInspector,1,4);
            gridPane.add(tAdmin,1,5);
            gridPane.add(tManage,1,6);
            gridPane.setAlignment(Pos.CENTER);
            addButton.setAlignment(Pos.CENTER);
            VBox vulBox = new VBox(10);
            vulBox.getChildren().addAll(gridPane, addButton);
            vulBox.setAlignment(Pos.CENTER);
            Scene sc2 = new Scene(vulBox,450,300);
            stage2.setScene(sc2);
            stage2.show();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void CloseProgram(){
        //פונקציית סגירת התכנית נבחר כל מיני אופציות לסגירת התכנית נבחר בדר"כ ב-YES_NO_OPTION
        int answer = JOptionPane.showConfirmDialog(null, "Exit","Exit? ",JOptionPane.YES_NO_OPTION);

        if(answer == JOptionPane.YES_OPTION) {

            System.out.print("Closing");
            mainWindow.close();

        }

    }
    private void answerVolunteer() throws volunteerExeption {
        //פונקציית סגירת התכנית נבחר כל מיני אופציות לסגירת התכנית נבחר בדר"כ ב-YES_NO_OPTION
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to join us?\n");


    }

    public static void main(String[] args) {
        launch();
        volunteer v1 = new volunteer("mosh","2345","old mans",0);
        inspector darom = new inspector(v1);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(organizationMember v: darom.getVolunteers()){
            executor.execute((Runnable) v);
        }

        executor.shutdown();
//
        while (!executor.isTerminated());
    }

}