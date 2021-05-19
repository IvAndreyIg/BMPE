package sample;




import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class controllerForm {
    @FXML
    private ScrollPane ScrollPane_Pane1;
    @FXML
    private Button JFXButton_UpCaseHex1;
    @FXML
    private Button JFXButton_SpaceXHex1;
    @FXML
    private Button JFXButton_UpCaseHex;
    @FXML
    private Button JFXButton_SpaceXHex;
    @FXML
    private TextArea JFXTextArea_TextFromHex1;
    @FXML
    private Button JFXButton_Conversion;
    @FXML
    private Button JFXButton_Copy;
    @FXML
    private TextArea JFXTextArea_TextForConversion;
    @FXML
    private TextArea JFXTextArea_ConvertedText;
    @FXML
    private ComboBox JFXComboBox_FromChooser;
    @FXML
    private ComboBox JFXComboBox_ToChooser;
    @FXML
    private AnchorPane AnchorPane_main1;
    @FXML
    private AnchorPane AnchorPane_hex;
    @FXML
    private TextArea JFXTextArea_TextFromHex;
    @FXML
    private Label Label_Hex;
    @FXML
    private Button JFXButton_CopyHex;
    @FXML
    private Button JFXButton_ConvertHex;
    @FXML
    private AnchorPane AnchorPane_Decimal;
    @FXML
    private TextArea JFXTextArea_TextFromDecimal;
    @FXML
    private Label Label_Decimal;
    @FXML
    private Button JFXButton_CopyDecimal;
    @FXML
    private Button JFXButton_ConvertDecimal;
    @FXML
    private AnchorPane AnchorPane_Binary;
    @FXML
    private TextArea JFXTextArea_TextFromBinary;
    @FXML
    private Label Label_Binary;
    @FXML
    private Button JFXButton_CopyBinary;
    @FXML
    private Button JFXButton_ConvertBinary;

    @FXML
    private AnchorPane AnchorPane_URL;
    @FXML
    private TextArea JFXTextArea_TextFromURL;
    @FXML
    private Label Label_URL;
    @FXML
    private Button JFXButton_CopyURL;
    @FXML
    private Button JFXButton_ConvertURL;
    @FXML
    private AnchorPane AnchorPane_Base64;
    @FXML
    private TextArea JFXTextArea_TextFromBase64;
    @FXML
    private Label Label_Base64;
    @FXML
    private Button JFXButton_CopyBase64;
    @FXML
    private Button JFXButton_ConvertBase64;
    @FXML
    private AnchorPane AnchorPane_ACSII;
    @FXML
    private TextArea JFXTextArea_TextFromACSII;
    @FXML
    private Label Label_ACSII;
    @FXML
    private Button JFXButton_CopyACSII;
    @FXML
    private Button JFXButton_ConvertACSII;
    @FXML
    private AnchorPane AnchorPane_1NumHex;
    @FXML
    private TextArea JFXTextArea_TextFrom1NumHex;
    @FXML
    private Label Label_1NumHex;
    @FXML
    private Button JFXButton_Copy1NumHex;
    @FXML
    private Button JFXButton_Convert1NumHex;


    //буфер для конфертированного текста
    String TextForConversion=new String();


    /*
      Инициализация контроллера (Вместо конструктора)
     */

    static boolean lowHexText =true;
    static boolean lowHexText1 =true;
    //static boolean withSpacedText =true;


    public void Init(){



        JFXButton_UpCaseHex.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(lowHexText) {
                    JFXTextArea_TextFromHex.setText((JFXTextArea_TextFromHex.getText().toUpperCase()));
                    JFXButton_UpCaseHex.setText("a↓");
                    lowHexText =false;
                }
                else{
                    JFXTextArea_TextFromHex.setText((JFXTextArea_TextFromHex.getText().toLowerCase()));
                    JFXButton_UpCaseHex.setText("A↑");
                    lowHexText =true;
                }
            }
        });
        JFXButton_SpaceXHex.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if(withSpacedText) {

                JFXTextArea_TextFromHex.setText((JFXTextArea_TextFromHex.getText().replaceAll("\\s","")));
                JFXButton_SpaceXHex.setText("↔");
           /*  withSpacedText =false;
            }
            else{



                JFXTextArea_TextFromHex.setText((JFXTextArea_TextFromHex.getText().toLowerCase()));
                JFXButton_SpaceXHex.setText("↮");
                withSpacedText =true;
            }*/
            }
        });

        JFXButton_UpCaseHex1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(lowHexText1) {
                    JFXTextArea_TextFromHex1.setText((JFXTextArea_TextFromHex1.getText().toUpperCase()));
                    JFXButton_UpCaseHex1.setText("a↓");
                    lowHexText1 =false;
                }
                else{
                    JFXTextArea_TextFromHex1.setText((JFXTextArea_TextFromHex1.getText().toLowerCase()));
                    JFXButton_UpCaseHex1.setText("A↑");
                    lowHexText1 =true;
                }
            }
        });
        JFXButton_SpaceXHex1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if(withSpacedText) {

                JFXTextArea_TextFromHex1.setText((JFXTextArea_TextFromHex1.getText().replaceAll("\\s","")));
                JFXButton_SpaceXHex1.setText("↔");
           /*  withSpacedText =false;
            }
            else{



                JFXTextArea_TextFromHex.setText((JFXTextArea_TextFromHex.getText().toLowerCase()));
                JFXButton_SpaceXHex.setText("↮");
                withSpacedText =true;
            }*/
            }
        });

        for(int i=0;i<8;i++) {
            Button temp = ((Button) (((AnchorPane) (AnchorPane_main1.getChildren().get(i))).getChildren().get(1)));
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //System.out.printf(((JFXTextArea) (((AnchorPane) temp.getParent()).getChildren().get(0))).getText() + "\n");

                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(((TextArea) (((AnchorPane) temp.getParent()).getChildren().get(0))).getText());
                    clipboard.setContent(content);

                }
            });
        }
        for(int i=0;i<8;i++) {
            int a=i;
            Button temp = ((Button) (((AnchorPane) (AnchorPane_main1.getChildren().get(i))).getChildren().get(2)));
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    JFXButton_UpCaseHex.setText("A↑");
                    lowHexText =true;
                    JFXButton_UpCaseHex1.setText("A↑");
                    lowHexText1 =true;
                    //   JFXButton_SpaceXHex.setText("↮");
                    // withSpacedText =true;
                    try {
                        //System.out.printf(((JFXTextArea) (((AnchorPane) temp.getParent()).getChildren().get(0))).getText() + "\n");
                        System.out.printf("kkk::" + (((Label) (((AnchorPane) temp.getParent()).getChildren().get(3))).getText()) + "\n");

                        String tempstr = ((TextArea) (((AnchorPane) temp.getParent()).getChildren().get(0))).getText();
                        tempstr = TextConversion.FromtoACSII((((Label) (((AnchorPane) temp.getParent()).getChildren().get(3))).getText()), tempstr);

                        for (int j = 0; j < 8; j++) {
                            if(j!=a) {
                                AnchorPane temp3 = ((((AnchorPane) (AnchorPane_main1.getChildren().get(j)))));
                                //((TextArea) temp3.getChildren().get(0)).setText(" ");
                                String tempstr2 = TextConversion.FromACSIIto(((Label) temp3.getChildren().get(3)).getText(), tempstr);
                                ((TextArea) temp3.getChildren().get(0)).setText(tempstr2);
                            }
                        }
                        System.out.printf(tempstr + "9\n");
                    }
                    catch(NullPointerException e){
                        System.out.printf("Не выбран формат кодировки/конвертации");
                        //JFXTextArea_ConvertedText.setText("Не выбран формат кодировки/конвертации");
                    }
                    catch(NumberFormatException e){

                        System.out.printf("Некоректный аргумент для переконвертирование ");
                        //JFXTextArea_ConvertedText.setText("Некоректный аргумент для переконвертирование ");
                    }
                    catch (StringIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    catch(IllegalArgumentException e){
                        System.out.printf("Некоректный аргумент для декодирования из Base64");
                        // JFXTextArea_ConvertedText.setText("Некоректный аргумент для декодирования из Base64");
                    }
                }
            });
        }



        JFXTextArea_TextFrom1NumHex.setFont(new Font(31));


        AnchorPane_main1.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                //System.out.printf(AnchorPane_hex.getChildren().toString()+"\n"+"\n"+"\n");


                double Heith=Double.parseDouble(newValue.toString())/3.19;
                ///////
                for(int i=0;i<8;i++){

                    AnchorPane temp=(((AnchorPane)(AnchorPane_main1.getChildren().get(i))));
                    //System.out.printf((((AnchorPane)(AnchorPane_main1.getChildren().get(i))).toString()));
                    /*Area*/     ((TextArea)temp.getChildren().get(0)).setLayoutY(Heith/11);
                    /*Area*/     ((TextArea)temp.getChildren().get(0)).setPrefHeight(Heith/1.32);
                    /*Convert*/  ((Button)temp.getChildren().get(2)).setFont(new Font(Double.parseDouble(newValue.toString())/51));
                    /*Convert*/  ((Button)temp.getChildren().get(2)).setLayoutY(Heith/1.154);
                    /*Convert*/  ((Button)temp.getChildren().get(2)).setPrefHeight(Heith/9.02);
                    /*Copy*/     ((Button)temp.getChildren().get(1)).setFont(new Font(Double.parseDouble(newValue.toString())/51));
                    /*Copy*/     ((Button)temp.getChildren().get(1)).setLayoutY(Heith/1.154);
                    /*Copy*/     ((Button)temp.getChildren().get(1)).setPrefHeight(Heith/9.02);

                    JFXButton_UpCaseHex.setFont(new Font(Double.parseDouble(newValue.toString())/54));
                    JFXButton_UpCaseHex.setLayoutY(Heith/1.154);
                    JFXButton_UpCaseHex.setPrefHeight(Heith/9.02);
                    JFXButton_SpaceXHex.setFont(new Font(Double.parseDouble(newValue.toString())/54));
                    JFXButton_SpaceXHex.setLayoutY(Heith/1.154);
                    JFXButton_SpaceXHex.setPrefHeight(Heith/9.02);

                    JFXButton_UpCaseHex1.setFont(new Font(Double.parseDouble(newValue.toString())/54));
                    JFXButton_UpCaseHex1.setLayoutY(Heith/1.154);
                    JFXButton_UpCaseHex1.setPrefHeight(Heith/9.02);
                    JFXButton_SpaceXHex1.setFont(new Font(Double.parseDouble(newValue.toString())/54));
                    JFXButton_SpaceXHex1.setLayoutY(Heith/1.154);
                    JFXButton_SpaceXHex1.setPrefHeight(Heith/9.02);

                    /*Label*/    ((Label)temp.getChildren().get(3)).setLayoutY(Heith/117.33);
                    /*Label*/    ((Label)temp.getChildren().get(3)).setFont(new Font(Double.parseDouble(newValue.toString())/45));
                    temp.setPrefWidth(Heith);

                    System.out.printf("\n");
                }


                ((AnchorPane)(AnchorPane_main1.getChildren().get(0))).setLayoutY(Heith*1.07);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(2))).setLayoutY(Heith*1.07);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(7))).setLayoutY(Heith*1.07);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(3))).setLayoutY(Heith/0.4911);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(5))).setLayoutY(Heith/12);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(6))).setLayoutY(Heith/12);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(4))).setLayoutY(Heith/12);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(1))).setLayoutY(Heith/0.4911);


            }
        });
        AnchorPane_main1.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //  System.out.printf(oldValue+"\n"+newValue+"\n"+"\n"+"\n");


                double Width=Double.parseDouble(newValue.toString())/3.072;


                for(int i=0;i<8;i++){

                    AnchorPane temp=(((AnchorPane)(AnchorPane_main1.getChildren().get(i))));
                    //System.out.printf((((AnchorPane)(AnchorPane_main1.getChildren().get(i))).toString()));
                    /*Area*/     ((TextArea)temp.getChildren().get(0)).setPrefWidth(Width/1.05);
                    /*Area*/     ((TextArea)temp.getChildren().get(0)).setFont(new Font(Width/39));
                    /*Convert*/  ((Button)temp.getChildren().get(2)).setLayoutX(Width/3.84);
                    /*Convert*/  ((Button)temp.getChildren().get(2)).setPrefWidth(Width/2.5);
                    /*Copy*/     ((Button)temp.getChildren().get(1)).setLayoutX(Width/1.48);
                    /*Copy*/     ((Button)temp.getChildren().get(1)).setPrefWidth(Width/3.43);
                    /*Label*/    ((Label)temp.getChildren().get(3)).setLayoutX(Width/78.2);
                    temp.setPrefWidth(Width);

                    System.out.printf("\n");
                }
                JFXButton_UpCaseHex.setPrefWidth(Width/9.92);
                JFXButton_SpaceXHex.setPrefWidth(Width/9.92);
                JFXButton_SpaceXHex.setLayoutX(Width/9.154);
                JFXButton_UpCaseHex1.setPrefWidth(Width/9.92);
                JFXButton_SpaceXHex1.setPrefWidth(Width/9.92);
                JFXButton_SpaceXHex1.setLayoutX(Width/9.154);

                ((AnchorPane)(AnchorPane_main1.getChildren().get(0))).setLayoutX(Width*1.013);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(2))).setLayoutX(Width/45.71);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(7))).setLayoutX(Width*2);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(3))).setLayoutX(Width/45.71);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(5))).setLayoutX(Width/45.71);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(6))).setLayoutX(Width*1.013);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(4))).setLayoutX(Width*2);
                ((AnchorPane)(AnchorPane_main1.getChildren().get(1))).setLayoutX(Width*1.013);

            }
        });
    }
}
