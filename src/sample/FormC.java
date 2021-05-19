package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.EditorPack.EditorBmp;

import java.io.IOException;

public class FormC {
    @FXML
    MenuButton Pravka;
    @FXML
    AnchorPane AnchorPaneWork;

    MainM MainThis;

    public void Init(){
      //  Pravka.s
        MenuItem item1 = new MenuItem("Калькулятор");

        item1.setOnAction(a->{


            try {

               // Stage stage;
                AnchorPaneWork.getChildren().clear();
                System.out.println("op1");
                FXMLLoader loader=new FXMLLoader();
               // AnchorPaneWork.getChildren().clear();

                loader.setLocation(Calculator.class.getResource("CalculatorForm.fxml"));
                Pane rootLayout;

                rootLayout=(Pane)loader.load();
                Calculator   controllerTableForm = loader.getController();
                //controllerTableForm.setParent(this);
                //stage=new Stage();
                // controllerTableForm.setPstage(stage);

                controllerTableForm.Init();
                //stage.setTitle("генератор SFC");
                //AnchorPaneWork.getChildren().addAll(rootLayout);
                AnchorPaneWork.getChildren().addAll(rootLayout);
            //    Scene scene = new Scene(rootLayout);
//                AnchorPaneWork
             //  Scene scene = new Scene(rootLayout);
               // stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        MenuItem item2 = new MenuItem("Редактор изображений");
        item2.setOnAction(a->{
            try {
                AnchorPaneWork.getChildren().clear();
                // Stage stage;
                System.out.println("op2");
                FXMLLoader loader=new FXMLLoader();
                // AnchorPaneWork.getChildren().clear();

                loader.setLocation(EditorBmp.class.getResource("/sample/EditorPack/EditorBmpForm2.fxml"));
                Pane rootLayout;

                rootLayout=(Pane)loader.load();
                EditorBmp    controllerTableForm = loader.getController();
                //controllerTableForm.setParent(this);
                //stage=new Stage();
                // controllerTableForm.setPstage(stage);
                controllerTableForm.setMain(MainThis);
                controllerTableForm.Init();

                //stage.setTitle("генератор SFC");
                //AnchorPaneWork.getChildren().addAll(rootLayout);
                AnchorPaneWork.getChildren().addAll(rootLayout);
                //    Scene scene = new Scene(rootLayout);
//                AnchorPaneWork
                //  Scene scene = new Scene(rootLayout);
                // stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Pravka.getItems().addAll(item1,item2);
        Pravka.setPopupSide(Side.BOTTOM);

    }
    public void setMain(MainM M){
        MainThis=M;
    }

}
