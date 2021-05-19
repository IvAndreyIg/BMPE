package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Calculator {
    @FXML
    private javafx.scene.control.Button Button;

    @FXML
    private ChoiceBox<String> CBbase1;
    @FXML
    private ChoiceBox<String> CBbase2;
    @FXML
    private ChoiceBox<String> CBbase3;
    @FXML
    private ChoiceBox<String> CBsign;
    @FXML
    private TextArea TextArea1;
    @FXML
    private TextArea TextArea2;
    @FXML
    private Button BM;

    @FXML
    private TextArea TextArea3;
    public void Init(){
        CBsign.getSelectionModel().selectFirst();
        CBbase1.getSelectionModel().selectFirst();
        CBbase2.getSelectionModel().selectFirst();
        CBbase3.getSelectionModel().selectFirst();

BM.setOnAction(x->{
    Fraction.toTenSystem("A5013.332132",15);
});

        Button.setOnAction(x->{
            Fraction a1=new Fraction(TextArea1.getText());
            Fraction a2=new Fraction(TextArea2.getText());

            //Fraction a3=new Fraction("-212122.23");
/*
            try {
                Fraction a4=a3.clone();
                System.out.println(a4);
            }
            catch (CloneNotSupportedException e){e.printStackTrace();}
*/


            switch(CBsign.getValue()){
                case "+":TextArea3.setText(a1.addition(a2).toString());break;
                case "-":TextArea3.setText(a1.subtraction(a2).toString());break;
                case "*":TextArea3.setText(a1.multiplication(a2).toString());break;
                case "/":TextArea3.setText(a1.division(a2).toString());break;
            }




        });
    }
}