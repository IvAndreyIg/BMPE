package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainM extends Application {

    private Stage stage; //Определяет главное окно модуля
    private Desktop desktop = Desktop.getDesktop();
    private Pane rootLayout; //Главный родительский элемент в контейнере

    public IMG getMainIMG() {
        return mainIMG;
    }

    public void setMainIMG(IMG mainIMG) {
        this.mainIMG = mainIMG;
    }

    private IMG mainIMG;
    public static void main(String[] args) {


        launch(args);
    }

    public void Inic(){
        try{
            FXMLLoader loader=new FXMLLoader();

            javafx.scene.image.Image a=new Image("sample/cursorNFV1.png");
            loader.setLocation(FormC.class.getResource("Form.fxml"));

            rootLayout=(Pane)loader.load();
            FormC   controllerTableForm = loader.getController();
            //controllerTableForm.setParent(this);
            stage=new Stage();
            // controllerTableForm.setPstage(stage)
            controllerTableForm.setMain(this);
            controllerTableForm.Init();
            stage.setTitle("Программа с графическим интерфейсом");



            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            scene.setCursor(new ImageCursor(a));

            stage.show();

        }catch (IOException o){
            o.printStackTrace();
        }



    }

    public Stage returnthisStage(){
        return stage;
    }
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.stage.setTitle("Тест библиотеки графических компонентов для Системы");

        Inic();


    }


}
