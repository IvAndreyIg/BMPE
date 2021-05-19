package sample;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;




public class TextConversion {

    public static String bytetoIntStr(byte b) {
        int value;
        if (b < 0) {
            value = 256 + b;
        } else {
            value = b;
        }
        return Integer.toString(value);
    }

    /**
     * Метод кодирования текста из ASCII используя кодировку Base64
     *
     * */

    public static String AsciiToBase64(String asciiStr) {

        return new String(Base64.getEncoder().encode(asciiStr.getBytes()));

    }
    /**
     * Метод декодирования текста в ASCII используя кодировку Base64
     *
     * */

    public static String Base64ToAscii(String Base64Str) {

        return new String(Base64.getDecoder().decode(Base64Str));

    }


    /**
     * Метод конвертации текста из ASCII в формат URL
     *
     * */
    public static String AsciiToURL(String asciiStr){
        try {
            return new String(java.net.URLEncoder.encode(asciiStr,"UTF-8"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "0";

    }


    /**
     * Метод конвертации текста из формата URL в ASCII
     *
     * */
    public static String URLToAscii(String URLStr) {
        try {
            return new String(java.net.URLDecoder.decode(URLStr, "UTF-8"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private static String AsciiToHex(String asciiStr,boolean withspaces) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        if (withspaces)
            for (char ch : chars) {
                hex.append(Integer.toHexString((int) ch));
                hex.append(" ");
            }
        else
            for (char ch : chars) {
                hex.append(Integer.toHexString((int) ch));

            }

        return hex.toString();
    }


    /**
     * Метод конвертации текста из ASCII в Hex
     *
     * */
    public static String AsciiToHex(String asciiStr) {

        return AsciiToHex(asciiStr,true);
    }
    public static String AsciiTo1NumHex(String asciiStr) {

        String numberOnly= asciiStr.replaceAll("[^0-9]", "");
        Integer.toHexString(Integer.parseInt(numberOnly)).toUpperCase();

        return Integer.toHexString(Integer.parseInt(numberOnly)).toUpperCase();
    }

    /**
     * Метод конвертации текста из формата Hex в ASCII
     *
     * */

    public static String HexToAscii(String hexStr) {
        hexStr=hexStr.replaceAll("\\s","");
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
    public static String OneNumHexToAscii(String oneNumHexStr) {
        String numberOnly= oneNumHexStr.replaceAll("[^0-9]", "");
        return Integer.toString(Integer.parseInt(numberOnly, 16));
    }

    private static String AsciiToBinary(String asciiStr,boolean withspaces) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder binary = new StringBuilder();
        if (withspaces)
            for (char ch : chars) {
                binary.append(Integer.toBinaryString((int) ch));
                binary.append(" ");
            }
        else
            for (char ch : chars) {
                binary.append(Integer.toBinaryString((int) ch));

            }

        return binary.toString();
    }


    /**
     * Метод конвертации текста из ASCII в Binary
     *
     * */
    public static String AsciiToBinary(String asciiStr) {

        return AsciiToBinary(asciiStr,true);
    }


    /**
     * Метод конвертации текста из формата Binary в ASCII
     *
     * */
    public static String BinaryToAscii(String binaryStr) {
        StringBuilder output = new StringBuilder("");

        for (int index = 0; index < binaryStr.length(); index += 9) {
            String temp = binaryStr.substring(index, index + 8);
            int num = Integer.parseInt(temp, 2);

            output.append((char) num);

        }
        return output.toString();
    }

    public static String TextToDecimal(String Text){

        StringBuilder strDecimal=new StringBuilder("");


        try {
            byte[] bytes = Text.getBytes("UTF-8");

            for (int i=0;i<bytes.length-1;i++){
                strDecimal.append(bytetoIntStr(bytes[i]));
                strDecimal.append(" ");
            }
            strDecimal.append(bytetoIntStr(bytes[bytes.length-1]));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.printf("Некоректный аргумент для переконвертирование ");

        }

        return strDecimal.toString();
    }

    public static String TextToHex(String Text){

        String decText=TextToDecimal(Text);


        int length=decText.length()-1;
        String b="";
        StringBuilder d=new StringBuilder("");
        int searchBorder =0;

        while(searchBorder<length&&(searchBorder>=1||(b==""&&searchBorder>=0))) {
            int x = decText.indexOf(' ', searchBorder);

            if(x>0) {
                b = decText.substring(searchBorder, x );
                //b=Integer.toHexString(Integer.parseInt(b));
                d.append(Integer.toHexString(Integer.parseInt(b)));
                d.append(" ");
            }
            else{
                b = decText.substring(searchBorder, length+1 );

                d.append(Integer.toHexString(Integer.parseInt(b)));

            }
            searchBorder = x + 1;

        }
        return d.toString();
    }

    public static String TextToBinary(String Text){

        String decText=TextToDecimal(Text);

        int length=decText.length()-1;
        String b="";
        StringBuilder d=new StringBuilder("");
        int searchBorder =0;

        while(searchBorder<length&&(searchBorder>=1||(b==""&&searchBorder>=0))) {
            int x = decText.indexOf(' ', searchBorder);

            if(x>0) {
                b = decText.substring(searchBorder, x );
                //b=Integer.toHexString(Integer.parseInt(b));
                d.append(Integer.toBinaryString(Integer.parseInt(b)));
                d.append(" ");
            }
            else{
                b = decText.substring(searchBorder, length+1 );

                d.append(Integer.toBinaryString(Integer.parseInt(b)));

            }
            searchBorder = x + 1;

        }
        return d.toString();
    }




    private static String AsciiToDecimal(String asciiStr,boolean withspaces) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder decimal = new StringBuilder();
        if (withspaces)
            for (char ch : chars) {
                decimal.append((int)ch);
                decimal.append(" ");
            }
        else
            for (char ch : chars) {
                decimal.append((int)ch);

            }

        return decimal.toString();
    }


    /**
     * Метод конвертации текста из ASCII в Decimal
     *
     * */
    public static String AsciiToDecimal(String asciiStr) {

        return AsciiToDecimal(asciiStr,true);
    }


    /**
     * Метод конвертации текста из формата Decimal в ASCII
     *
     * */
    public static String DecimalToText(String DecStr) {



        int elements=1;
        for(int i=0;i<DecStr.length();i++){

            if(DecStr.charAt(i)==' '){
                elements++;
            }
        }
        byte bytes[]=new byte[elements];
        int length=DecStr.length()-1;
        String b="";
        StringBuilder d=new StringBuilder("");
        int searchBorder =0;
        elements=0;
        while(searchBorder<length&&(searchBorder>=1||(b==""&&searchBorder>=0))) {
            int x = DecStr.indexOf(' ', searchBorder);

            if(x>0) {
                b = DecStr.substring(searchBorder, x );
                int temp=Integer.parseInt(b);
                bytes[elements]=(byte)(temp>127?temp-256:temp);
                elements++;
            }
            else{
                b = DecStr.substring(searchBorder, length+1 );
                int temp=Integer.parseInt(b);
                bytes[elements]=(byte)(temp>127?temp-256:temp);
                elements++;

            }
            searchBorder = x + 1;

        }


        String str1 = new String(bytes, StandardCharsets.UTF_8);


        return str1;
    }

    public static String HexToText(String hexStr) {


        hexStr=hexStr.replaceAll("\\s","");
        int elements=hexStr.length()/2;

        byte bytes[]=new byte[elements];
        int length=hexStr.length()-1;
        String b="";
        StringBuilder d=new StringBuilder("");
        int searchBorder =0;
        elements=0;
        /*
        while(searchBorder<length&&(searchBorder>=1||(b==""&&searchBorder>=0))) {
            int x = hexStr.indexOf(' ', searchBorder);

            if(x>0) {
                b = hexStr.substring(searchBorder, x );
                int temp=Integer.parseInt(b,16);
                bytes[elements]=(byte)(temp>127?temp-256:temp);
                elements++;
            }

            else{
                b = hexStr.substring(searchBorder, length+1 );
                int temp=Integer.parseInt(b,16);
                bytes[elements]=(byte)(temp>127?temp-256:temp);
                elements++;

            }
            searchBorder = x + 1;

        }*/


        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {

            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
            int temp=Integer.parseInt(str,16);
            bytes[elements]=(byte)(temp>127?temp-256:temp);
            elements++;
        }


        String str1 = new String(bytes, StandardCharsets.UTF_8);


        return str1;
    }
    public static String BinaryToText(String hexStr) {



        int elements=1;
        for(int i=0;i<hexStr.length();i++){

            if(hexStr.charAt(i)==' '){
                elements++;
            }
        }
        byte bytes[]=new byte[elements];
        int length=hexStr.length()-1;
        String b="";
        StringBuilder d=new StringBuilder("");
        int searchBorder =0;
        elements=0;
        while(searchBorder<length&&(searchBorder>=1||(b==""&&searchBorder>=0))) {
            int x = hexStr.indexOf(' ', searchBorder);

            if(x>0) {
                b = hexStr.substring(searchBorder, x );
                int temp=Integer.parseInt(b,2);
                bytes[elements]=(byte)(temp>127?temp-256:temp);
                elements++;
            }

            else{
                b = hexStr.substring(searchBorder, length+1 );
                int temp=Integer.parseInt(b,2);
                bytes[elements]=(byte)(temp>127?temp-256:temp);
                elements++;

            }
            searchBorder = x + 1;

        }


        String str1 = new String(bytes, StandardCharsets.UTF_8);


        return str1;
    }



    public static String FromtoACSII(String from,String TextForConversion1){
        //TextForConversion="";
        String   TextForConversion="";
        try {
            switch (from) {
                case "ASCII":
                    TextForConversion = TextForConversion1;
                    break;
                case "Base64":

                    TextForConversion = TextConversion.Base64ToAscii(TextForConversion1);
                    break;
                case "Decimal":

                    TextForConversion = TextConversion.DecimalToText(TextForConversion1);
                    break;
                case "HEX представление":

                    TextForConversion = TextConversion.HexToText(TextForConversion1);
                    break;
                case "Binary":

                    TextForConversion = TextConversion.BinaryToText(TextForConversion1);
                    break;
                case "URL":

                    TextForConversion = TextConversion.URLToAscii(TextForConversion1);
                    break;
                case "16 С.С.":

                    TextForConversion = TextConversion.OneNumHexToAscii(TextForConversion1);
                    break;
                case "StringToHex":
                    System.out.printf("\n12345\n");
                    TextForConversion=TextConversion.HexToAscii(TextForConversion1);
                    break;
                default:
                    break;
            }
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

        return TextForConversion;
    }

    public static String FromACSIIto(String from,String TextForConversion1){

        String   TextForConversion="";
        try {
            switch (from) {
                case "ASCII":

                    TextForConversion=TextForConversion1;
                    break;
                case "Base64":

                    TextForConversion = TextConversion.AsciiToBase64(TextForConversion1);
                    break;
                case "Decimal":

                    TextForConversion = TextConversion.TextToDecimal(TextForConversion1);
                    break;
                case "HEX представление":

                    TextForConversion = TextConversion.TextToHex(TextForConversion1);
                    break;
                case "Binary":

                    TextForConversion = TextConversion.TextToBinary(TextForConversion1);
                    break;
                case "URL":

                    TextForConversion = TextConversion.AsciiToURL(TextForConversion1);
                    break;
                case "16 С.С.":

                    TextForConversion = TextConversion.AsciiTo1NumHex(TextForConversion1);
                    break;
                case "StringToHex":
                    System.out.printf("\nddwdwd\n");
                    TextForConversion=TextConversion.AsciiToHex(TextForConversion1);
                    System.out.printf(TextForConversion);
                    break;
                default:
                    break;
            }
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

        return TextForConversion;
    }


}


