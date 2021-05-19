package sample.EditorPack;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import sample.IMG;
import sample.MainM;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class EditorBmp {

    @FXML
    Rectangle Rectangleleft;

    @FXML
    Rectangle Rectangleright;
    @FXML
    private Button ButtonSaveRGB;
    @FXML
    private Tab tabFilter;

    @FXML
    private Button ButttonSaveCute;



    @FXML
    private Tab tabSetColor;

    @FXML
    private Tab tabFile;

    @FXML
    private Slider SliderBlue;

    @FXML
    private Slider SliderRed;

    @FXML
    private AnchorPane AnchorRGBFilt;

    @FXML
    private Slider SliderGreen;

    @FXML
    private AnchorPane AnchorReplaceColor;

    @FXML
    private Button ButtonCompress;


    @FXML
    void OnRGBTab(Event event) {
        System.out.println("ss");
        if (tabFilter.isSelected()) {

                //FilterIMG=MainThis.getMainIMG().getNotNullCopy();
            if(imagevievMain!=null) {
            imagevievMain.setVisible(true);}
            if(CutePane!=null)
                CutePane.setVisible(false);

            //System.out.println("Tab is Selected");
            //Do stuff here
        }
    }

    @FXML
    void OnSetColorTab(Event event) {
        System.out.println("ss");
        if (tabSetColor.isSelected()) {

            //FilterIMG =MainThis.getMainIMG().getNotNullCopy();
            if(imagevievMain!=null) {
            imagevievMain.setVisible(true);}
            if(CutePane!=null)
                CutePane.setVisible(false);

            //System.out.println("Tab is Selected");
            //Do stuff here
        }
    }

    @FXML
    void OnSelectedTabFile(Event event){
        System.out.println("s12");
        if (tabFile.isSelected()) {

            //FilterIMG =MainThis.getMainIMG().getNotNullCopy();
            if(imagevievMain!=null) {
                imagevievMain.setVisible(true);

            }
if(CutePane!=null)
            CutePane.setVisible(false);
            //System.out.println("Tab is Selected");
            //Do stuff here
        }

    }
    @FXML
    private TextField TextFieldCutY;
    @FXML
    private TextField TextFieldCutX;

    @FXML

    private Button ButtonCute;
    @FXML

    private Pane CutePane;

    @FXML
    private Button ButtonSaveReplace;
    @FXML
    private Button ButtonCancelReplace;


    //private IMG FilterIMG;
    private IMG FilterIMG;
    private Thread T;
    private IMG[][] Ar;
    final FileChooser fileChooser = new FileChooser();
    final DirectoryChooser directoryChooser = new DirectoryChooser();
    final FileChooser directoryChooser2 = new FileChooser();
    @FXML
   private Button ButtonRGBF;
    @FXML
    private Button ButtonSave;

    @FXML
    private Button ButtoncancelRGB;
    @FXML
    private Button ButtonOpen;
    @FXML
    private TextField  TFieldBlue;
    @FXML
    private TextField  TFieldRed;
    @FXML
    private TextField  TFieldGreen;

    @FXML
    private ChoiceBox ChoiceBoxCompr;

    @FXML
            private CheckBox CheckBoxCompr;



    MainM MainThis;
    @FXML
    private ImageView imagevievMain;
    public void Init(){

        ButtonSave.setOnAction(x->{


            directoryChooser2.setTitle("Select directory for save");
            directoryChooser2.setInitialFileName("Image");
            directoryChooser2.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.bmp"));
            File file = directoryChooser2.showSaveDialog(MainThis.returnthisStage());
            String Path = file.getAbsolutePath();

            try {
                FileUtils.writeByteArrayToFile(new File(Path), FilterIMG.getFileContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ChoiceBoxCompr.setValue("x2");

        ButtonCompress.setOnAction(x->{





            for(int i=0;i<(getCompressionSize()/2);i++) {
                FilterIMG=new IMG(FilterIMG);}

            MainThis.setMainIMG(FilterIMG);
            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));
        });





        ButtonSaveRGB.setOnAction(x->{

                MainThis.setMainIMG(FilterIMG.getNotNullCopy());

        });

        ButtoncancelRGB.setOnAction(x->{

            FilterIMG=MainThis.getMainIMG().getNotNullCopy();
            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));

            TFieldGreen.setText("");
                    TFieldRed.setText("");
            TFieldBlue.setText("");

            SliderBlue.setValue(0);
            SliderRed.setValue(0);
            SliderGreen.setValue(0);


        });

        if(MainThis.getMainIMG()!=null){
            imagevievMain.setImage(new Image(new ByteArrayInputStream(MainThis.getMainIMG().getFileContent())));

        }



        TFieldGreen.textProperty().addListener((obs, oldText, newText) -> {

            FilterIMG.SetGreen(Integer.parseInt(newText));
            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));
        });



        TFieldRed.textProperty().addListener((obs, oldText, newText) -> {

            FilterIMG.SetRed(Integer.parseInt(newText));
            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));
        });

        TFieldBlue.textProperty().addListener((obs, oldText, newText) -> {

            FilterIMG.SetBlue(Integer.parseInt(newText));
            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));
        });

        TFieldBlue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TFieldBlue.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (Integer.parseInt(newValue)>255) {
                    TFieldBlue.setText(String.valueOf(255));
                }
                if(oldValue!=newValue)
                    SliderBlue.setValue(Integer.parseInt(newValue));
            }
        });

        TFieldRed.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TFieldRed.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (Integer.parseInt(newValue)>255) {
                    TFieldRed.setText(String.valueOf(255));
                }
                if(oldValue!=newValue)
                    SliderRed.setValue(Integer.parseInt(newValue));
            }
        });

        TFieldGreen.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TFieldGreen.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (Integer.parseInt(newValue)>255) {
                    TFieldGreen.setText(String.valueOf(255));
                }
                if(oldValue!=newValue)
                    SliderGreen.setValue(Integer.parseInt(newValue));

            }
        });




        SliderGreen.setOnMouseReleased(x->{
            TFieldGreen.setText( Integer.toString((int)SliderGreen.getValue()));
        });

        SliderBlue.setOnMouseReleased(x->{
            TFieldBlue.setText( Integer.toString((int)SliderBlue.getValue()));
        });

        SliderRed.setOnMouseReleased(x->{
            TFieldRed.setText( Integer.toString((int)SliderRed.getValue()));
        });
        /*SliderGreen.valueProperty().addListener(new ChangeListener<Number>() {
                                                   @Override
                                                   public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                       if(oldValue!=newValue)
                                                       TFieldGreen.setText(String.valueOf(newValue));
                                                   }
                                               }


        );*/


      /*  ButtonRGBF.setOnAction(x->{
            System.out.println("pp1");

            //IMG a=new IMG("C:\\Users\\Тент\\IdeaProjects\\Calculator\\src\\sample\\ups21.bmp");
            // a.SetGreen(255);
            //MainThis.getMainIMG().SetGreen();
            MainThis.getMainIMG().changeСolor(255,255,255,18,137,86);
            Image image = new Image(new ByteArrayInputStream(MainThis.getMainIMG().getFileContent()));
            imagevievMain.setImage(image);

            // a.SetRed(255);
            //IMG[] AraayIMG=a.cutImage(3,5);

          //  try {
            //    for(int i=0;i<3*5;i++){
              //      FileUtils.writeByteArrayToFile(new File("C:\\Users\\Тент\\IdeaProjects\\Calculator\\src\\sample\\US"+i+"ps2.bmp"), AraayIMG[i].getFileContent());
               // }
            //} catch (IOException e) {
              //  e.printStackTrace();
           // }
            //File fi=new File();


          //  IMG b=new IMG(a,new SizeIMG(40,200,40,200));
            //Image image = new Image(new ByteArrayInputStream(a.getFileContent()));
            //imagevievMain.setImage(image);

        });*/
        ButtonOpen.setOnAction(x->{
            File file = fileChooser.showOpenDialog(MainThis.returnthisStage());
            String Path = file.getAbsolutePath();
            System.out.println("Path:"+Path);
            MainThis.setMainIMG(new IMG(Path));
    IMG Temp=new IMG(Path);
           if(CheckBoxCompr.isSelected())
            for(int i=0;i<(getCompressionSize()/2);i++){
                Temp=new IMG(Temp);
            }
            MainThis.setMainIMG(Temp);

            //Image image = new Image(new ByteArrayInputStream(MainThis.getMainIMG().getFileContent()));
            byte []a=MainThis.getMainIMG().getFileContent();
          //  System.out.println(MainThis.getMainIMG().getHeith());
           // System.out.println(MainThis.getMainIMG().getWidth());
           // System.out.println(MainThis.getMainIMG());
//            Image Im=new Image("C:/Users/Тент/Desktop/ups.bmp");
            //Image I=new Image(new ByteArrayInputStream(a));
            //imagevievMain.setImage(Im);
            imagevievMain.setImage(new Image(new ByteArrayInputStream(MainThis.getMainIMG().getFileContent())));
            //pt_commutatorPic.setSwitchImage(Path);
            FilterIMG=MainThis.getMainIMG().getNotNullCopy();
        });


        for(int i=0;i<12;i++){

            Node x=AnchorReplaceColor.getChildren().get(i);
            if(x instanceof TextField){
                Slider y=(Slider)AnchorReplaceColor.getChildren().get(i-3);
               // Rectangle R=(Rectangle) AnchorReplaceColor.getChildren().get(i/13+12);
                int u=(i/8);
                ((TextField)x).textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {

                        if (!newValue.matches("\\d*")) {
                            ((TextField)x).setText(newValue.replaceAll("[^\\d]", ""));
                        }else{
                            if(!newValue.equals("")){
                           /* try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                            if (Integer.parseInt(newValue)>255) {
                            ((TextField)x).setText(String.valueOf(255));
                        }



                        if(oldValue!=newValue)
                            y.setValue(Integer.parseInt(newValue));

                      //      R.setFill();
                            if(u==0) {
                                Rectangleleft.setFill(new Color(Double.parseDouble(((TextField) AnchorReplaceColor.getChildren().get(3)).getText()) / 255,
                                        Double.parseDouble(((TextField) AnchorReplaceColor.getChildren().get(4)).getText()) / 255,
                                        Double.parseDouble(((TextField) AnchorReplaceColor.getChildren().get(5)).getText()) / 255, 1));
                            }
                            if(u==1) {
                                Rectangleright.setFill(new Color(Double.parseDouble(((TextField) AnchorReplaceColor.getChildren().get(9)).getText()) / 255,
                                        Double.parseDouble(((TextField) AnchorReplaceColor.getChildren().get(10)).getText()) / 255,
                                        Double.parseDouble(((TextField) AnchorReplaceColor.getChildren().get(11)).getText()) / 255, 1));
                            }
                            FilterIMG =MainThis.getMainIMG().getNotNullCopy();
                            FilterIMG.changeСolor(Integer.parseInt(((TextField) AnchorReplaceColor.getChildren().get(3)).getText()),
                                    Integer.parseInt(((TextField) AnchorReplaceColor.getChildren().get(4)).getText()),
                                    Integer.parseInt(((TextField) AnchorReplaceColor.getChildren().get(5)).getText()),
                                    Integer.parseInt(((TextField) AnchorReplaceColor.getChildren().get(9)).getText()),
                                    Integer.parseInt(((TextField) AnchorReplaceColor.getChildren().get(10)).getText()),
                                    Integer.parseInt(((TextField) AnchorReplaceColor.getChildren().get(11)).getText()));
                            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));


                    }}}
                });

            }





            if(x instanceof Slider){
                TextField a=((TextField)AnchorReplaceColor.getChildren().get(i+3));
                x.setOnMouseReleased(l->{


                a.setText( Integer.toString((int)((Slider)x).getValue()));
                });
            }
        }


        ButtonSaveReplace.setOnAction(x->{
            MainThis.setMainIMG(FilterIMG.getNotNullCopy());
        });

        ButtonCancelReplace.setOnAction(y->{

            FilterIMG =MainThis.getMainIMG().getNotNullCopy();
            imagevievMain.setImage(new Image(new ByteArrayInputStream(FilterIMG.getFileContent())));

            for(int i=0;i<12;i++){



                    Node x=AnchorReplaceColor.getChildren().get(i);

                if(x instanceof TextField){
                    ((TextField)x).setText("0");
                }
                if(x instanceof Slider){
                    ((Slider)x).setValue(0);
                }
            }

        });

        ButtonCute.setOnAction(x->{

            int X=Integer.parseInt(TextFieldCutX.getText());
            int Y=Integer.parseInt(TextFieldCutY.getText());
            double Kof=((double)FilterIMG.getWidth())/((double) FilterIMG.getHeith());
           VBox mainV=new VBox();
            mainV.setSpacing(3);
           mainV.setPrefSize(515*Kof,515);
            Queue<HBox> Qy=new LinkedList<HBox>() ;

            IMG[][] Ar=FilterIMG.cutImage(Y,X);

            for(int i=0;i<Y;i++){

                Queue<ImageView> Qx=new LinkedList<ImageView>() ;
                for(int j=0;j<X;j++){
                    ImageView Vi=new ImageView(new Image(new ByteArrayInputStream(Ar[j][i].getFileContent())));
                    Vi.setFitHeight((515-Y*3)/Y);
                    Vi.setFitWidth((515*Kof-X*3)/X);

                    ((LinkedList<ImageView>) Qx).add(j,Vi);
                }
                HBox a=new HBox();
                a.setSpacing(3);
                a.getChildren().addAll(Qx);
                ((LinkedList<HBox>) Qy).add(i,a);
            }

            mainV.getChildren().addAll(Qy);

            CutePane.getChildren().clear();
            CutePane.getChildren().add(mainV);
            imagevievMain.setVisible(false);
            CutePane.setVisible(true);

        });

        ButttonSaveCute.setOnMouseReleased(x->{
            File file = directoryChooser.showDialog(MainThis.returnthisStage());
            String Path = file.getAbsolutePath();
            int X=Integer.parseInt(TextFieldCutX.getText());
            int Y=Integer.parseInt(TextFieldCutY.getText());
            Ar=FilterIMG.cutImage(Y,X);
                 for(int i=0;i<Y;i++){
                     for(int j=0;j<X;j++){
                         try {
                             FileUtils.writeByteArrayToFile(new File(Path+"\\pic{"+(j+1)+"x"+(i+1)+"}.bmp"), Ar[j][i].getFileContent());
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }

                 }

        });
    }

    public int getCompressionSize(){
        return (int)(((String)ChoiceBoxCompr.getValue()).charAt(1)-48);
    }
    public void setMain(MainM M){
        MainThis=M;
    }

}
