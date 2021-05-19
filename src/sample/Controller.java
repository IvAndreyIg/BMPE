package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.Fraction;

public class Controller {
    @FXML
    private Button Button;
    @FXML
    private ImageView imageviev;
    @FXML
    private Button BM;
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
    private AnchorPane tab2;
    @FXML
    private TextArea TextArea3;
    public void Init(){
        CBsign.getSelectionModel().selectFirst();
        CBbase1.getSelectionModel().selectFirst();
        CBbase2.getSelectionModel().selectFirst();
        CBbase3.getSelectionModel().selectFirst();



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







            //System.out.println(a1.addition(a2));
/*
            String a= String.format("%1$"+12+ "s", "");
            StringBuilder FRR1=new StringBuilder("012345678901234");
            StringBuilder FRR2=new StringBuilder(String.format("%1$"+12+ "s", ""));
            //FRR1.
            //a.i
            FRR2.replace(11,12,"7");
            FRR2.replace(6,7,"7");
            System.out.print(FRR2);System.out.println("]");
            System.out.println(FRR1);
*/

        });
        BM.setOnAction(c->{
            System.out.println("A");



                IMG a=new IMG("C:\\Users\\Тент\\IdeaProjects\\Calculator\\src\\sample\\ups2.bmp");
               // a.SetGreen(255);
               // a.SetRed(255);
            IMG[][] AraayIMG=a.cutImage(3,5);

            /*try {
                for(int i=0;i<3*5;i++){
             //       FileUtils.writeByteArrayToFile(new File("C:\\Users\\Тент\\IdeaProjects\\Calculator\\src\\sample\\US"+i+"ps2.bmp"), AraayIMG[i].getFileContent());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //File fi=new File();

                a.changeСolor(255,255,255,18,137,86);
                IMG b=new IMG(a,new SizeIMG(40,200,40,200));
                Image image = new Image(new ByteArrayInputStream(b.getFileContent()));
                imageviev.setImage(image);
                //imageviev.setX();
            //imageviev.setX();

*/


        });


    }



}
