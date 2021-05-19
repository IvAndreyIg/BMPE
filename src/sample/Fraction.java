package sample;

import java.math.BigDecimal;

public class Fraction implements Cloneable {

   // BigDecimal a=new BigDecimal("323232323231231231231233232323233");

     int max = 500;
    char wholePart[] = new char[max];
    char fraction[] = new char[max];
    boolean sign = false;
    int wholepartlength, fractionpartlength;
    boolean getSign() { return sign; }
    void setSign(boolean S) { sign = S; }


    @Override
    protected Fraction clone() throws CloneNotSupportedException {
        return (Fraction)super.clone();
    }

    Fraction() {};
    Fraction( char[] whole, char[] fraction, boolean sign, int wholelength, int fractionlength)
    {     this.sign=sign;
        this.wholepartlength=wholepartlength;
        this.fractionpartlength=fractionpartlength;

        for (int i = 0; i < wholelength + 1; i++)
            wholePart[i] = whole[i];

        for (int i = 0; i < fractionlength + 1; i++)
            fraction[i] = fraction[i];
    };

    Fraction(String chislo) {
        int positionOfComma = chislo.indexOf('.');


        if (chislo.charAt(0)== '-') {
            sign = true;
            wholepartlength = positionOfComma - 1;
        }
        else wholepartlength = positionOfComma;

        fractionpartlength = chislo.length() - positionOfComma - 1;

        wholePart = new  char[wholepartlength + 1];
        fraction = new  char[fractionpartlength + 1];
        wholePart[wholepartlength] = '\0';
        fraction[fractionpartlength] = '\0';

        for (int i = 0; i < wholepartlength; i++)
            wholePart[i] = chislo.charAt(positionOfComma - i - 1);

        for (int i = 0; i < fractionpartlength; i++)
            fraction[i] = chislo.charAt(i + positionOfComma + 1);
    }

    @Override
    public String toString() {
        StringBuilder tempStr=new StringBuilder();

        if (sign) tempStr.append("-");
        for (int i = wholepartlength - 1; i >= 0; i--)
            tempStr.append(wholePart[i]);
        tempStr.append(".");
        for (int i = 0; i < fractionpartlength; i++)
            tempStr.append(fraction[i]);
        return tempStr.toString();
    }



   public Fraction addition(Fraction rightarg) {
        try {


            if (getSign() == false && rightarg.getSign() == false) {
                int This2[] = func2();
                int Rightarg2[] = rightarg.func2();
                int dopZN = 0;
                int length2;
                if (fractionpartlength > rightarg.fractionpartlength)
                    length2 = fractionpartlength;
                else
                    length2 = rightarg.fractionpartlength;

                int Temp2[] = new int[length2];

                for (int ix = 0; ix < length2; ix++)
                    Temp2[ix] = 0;

                for (int ix = 0; ix < length2; ix++) {
                    Temp2[ix] = Temp2[ix] + Rightarg2[ix] + This2[ix]; // суммируем последние разряды чисел

                }
                for (int ix = length2 - 1; ix > 0; ix--) {
                    //cout <<"ix"<< ix;
                    Temp2[ix - 1] = Temp2[ix - 1] + (Temp2[ix] / 10); // если есть разряд для переноса, переносим его в следующий разряд
                    Temp2[ix] = Temp2[ix] % 10; // если есть разряд для переноса он отсекается
                }

                if (Temp2[0] / 10 != 0) {
                    dopZN = Temp2[0] / 10;
                    Temp2[0] = Temp2[0] % 10;
                }
                StringBuilder TEMP2 = new StringBuilder();

                for (int i = 0; i < length2; i++) {
                    TEMP2.append((char) (Temp2[i] + 48));
                }

                /////////
                int This[] = func();
                int Rightarg[] = rightarg.func();

                int length;
                if (wholepartlength > rightarg.wholepartlength)
                    length = wholepartlength + 1;
                else
                    length = rightarg.wholepartlength + 1;

                int Temp[] = new int[length + 1];

                for (int ix = 0; ix < length + 1; ix++)
                    Temp[ix] = 0;
                Temp[0] = dopZN;
                for (int ix = 0; ix < length; ix++) {
                    Temp[ix] = Temp[ix] + Rightarg[ix] + This[ix]; // суммируем последние разряды чисел
                    Temp[ix + 1] = Temp[ix + 1] + (Temp[ix] / 10); // если есть разряд для переноса, переносим его в следующий разряд
                    Temp[ix] = Temp[ix] % 10; // если есть разряд для переноса он отсекается
                }

                if ((Temp[length - 1]) == 0)
                    length--;
                StringBuilder TEMP = new StringBuilder();

                for (int i = 0; i < length; i++) {
                    TEMP.append((char) (Temp[length - i - 1] + 48));

                }

                ///////////////////////

                //Fraction ab;
                TEMP.append(".");
                TEMP.append(TEMP2);
                //(TEMP);

                return new Fraction(TEMP.toString());
            }

            if (getSign() == false && rightarg.getSign() == true) {
                Fraction b = rightarg.clone();
                b.setSign(false);


                return  this.subtraction(b);
            }
            if (getSign() == true && rightarg.getSign() == false) {
                Fraction b = this.clone();
                b.setSign(false);
                Fraction a = b.subtraction(rightarg);
                a.setSign(true);
                return a;
            }
            if (getSign() == true && rightarg.getSign() == true) {
                Fraction a = this.clone(), b = rightarg.clone();
                b.setSign(false);
                a.setSign(false);
                a = a.subtraction(b);
                a.setSign(true);
                return a;
            }

        }catch (Exception e){ e.printStackTrace();}

return rightarg;
    }

    public static StringBuilder getZerosStr(int lenth){
        StringBuilder a=new StringBuilder();
        for(int i=0;i<lenth;i++){
        a.append('0');}

        return a;
    }



    public Fraction subtraction(Fraction rightarg) {
        System.out.println("dev1"+this);
        System.out.println("dev2"+rightarg);
        try {
            int J = Biggest(this.func(), this.wholepartlength, rightarg.func(), rightarg.wholepartlength, this.func2(), this.fractionpartlength, rightarg.func2(), rightarg.fractionpartlength);
            System.out.println("J:"+J);
            if (getSign() == false && rightarg.getSign() == false && J != 2) {
                System.out.println("Jib:");
                int k = Biggest(this.func(), this.wholepartlength, this.func(), this.wholepartlength, this.func2(), this.fractionpartlength, rightarg.func2(), rightarg.fractionpartlength);

                int dopZN = 0;

                int length2 = fractionpartlength;
                if (fractionpartlength < rightarg.fractionpartlength)
                {
                    length2 = rightarg.fractionpartlength;

                }

                StringBuilder Fr1=getZerosStr(length2), Fr2=getZerosStr(length2);

               // FRR2.replace(0,1,"7");

                for (int i = length2 - fractionpartlength, j = 0; i < length2; i++, j++) {
                    Fr1.replace(i,i+1,String.valueOf((char)(func2()[fractionpartlength - j - 1] + 48)));
                }
                for (int i = length2 - rightarg.fractionpartlength, j = 0; i < length2; i++, j++) {
                    //Fr2[i] = rightarg.func2()[rightarg.fractionpartlength - j - 1] + 48;
                    Fr2.replace(i,i+1,String.valueOf((char)(rightarg.func2()[rightarg.fractionpartlength - j - 1] + 48)));
                }

                if (k == 2) {
                    dopZN = 1;
                    Fr1.append("1");
                }
                //cout << "Fr1=" << Fr1 << "Fr2=" << Fr2 << endl;


                length2 = Fr1.length();
                int []x2 = new  int[Fr1.length()], y2 = new  int[Fr2.length()], z2 = new  int[length2];

                for (int i = 0; i < Fr1.length(); i++) {
                    x2[i] = (int)(Fr1.charAt(i) - 48);

                }
                for (int i = 0; i < Fr2.length(); i++) {
                    y2[i] = (int)(Fr2.charAt(i) - 48);

                }

                for (int i = 0; i < length2; i++) {
                    z2[i] = 0;
                }

                System.out.println(Fr1.toString());
                System.out.println(Fr2.toString());





                for (int ix = 0; ix < (length2 -dopZN); ix++) // проход по всем разрядам числа, начиная с последнего, не доходя до первого
                {
                    if (ix < (length2 - 1)) // если текущий разряд чисел не первый
                    {
                        x2[ix + 1]--; // в следующуем разряде большего числа занимаем 1.
                        z2[ix] += 10 + x2[ix]; // в ответ записываем сумму значения текущего разряда большего числа и 10-ти

                    }
                    else  // если текущий разряд чисел - первый
                        z2[ix] += x2[ix]; // в ответ суммируем значение текущего разряда большего числа

                    z2[ix] -= y2[ix]; // вычитаем значение текущего разряда меньшего числа

                    if (z2[ix] / 10 > 0) // если значение в текущем разряде двухразрядное
                    {
                        z2[ix + 1]++; // переносим единицу в старший разряд
                        z2[ix] %= 10; // в текущем разряде отсекаем ее
                    }
                }

              //  System.out.println();
                //for(int i=0;i<length2;i++){
                //    System.out.print(z2[i]);
               // }
               // System.out.println();
                if (k == 2) {
                    length2--;
                    //length2--;
                }

                StringBuilder TEMP2=getZerosStr(length2);
              //  String TEMP2(length2, 0);

                for (int i = 0; i < length2; i++) {
                    TEMP2.replace(i,i+1,String.valueOf((char)(z2[length2 - i - 1] + 48)));
                  //  TEMP2[i] = (char)(z2[length2 - i - 1] + 48);
                }

                System.out.printf("TEMP2:"+TEMP2);

                while (TEMP2.charAt(0) == '0') {
                    if (TEMP2.length() == 1) { break; }
                    TEMP2.replace(0,1,"");
                    if (TEMP2.length() == 1) { break; }
                }

                //cout << endl << "TEMP2=" << TEMP2 << endl;
                ///////////////////////////////
                int []This = func();
                int []Rightarg;
                if (dopZN == 1) {

                    Fraction a, b;
                    b = rightarg;
                   // rightarg.nach("1.00");
                    //cout << "rightarg" << rightarg << endl << "b" << b << endl;
                    //cout << "I";
                    a = new Fraction("1.00").addition( b);
                    //cout << "M";
                    rightarg = b.clone();
                    Rightarg = a.func();
                }
                else {
                    Rightarg = rightarg.func();
                }
                int length = wholepartlength;

               int[] x, y, z = new int[length];

                x = This;
                y = Rightarg;


                for (int i = 0; i < length; i++) {
                    z[i] = 0;
                }

                for (int ix = 0; ix < (length - 0); ix++) // проход по всем разрядам числа, начиная с последнего, не доходя до первого
                {
                    if (ix < (length - 1)) // если текущий разряд чисел не первый
                    {
                        x[ix + 1]--; // в следующуем разряде большего числа занимаем 1.
                        z[ix] += 10 + x[ix]; // в ответ записываем сумму значения текущего разряда большего числа и 10-ти

                    }
                    else  // если текущий разряд чисел - первый
                        z[ix] += x[ix]; // в ответ суммируем значение текущего разряда большего числа

                    z[ix] -= y[ix]; // вычитаем значение текущего разряда меньшего числа

                    if (z[ix] / 10 > 0) // если значение в текущем разряде двухразрядное
                    {
                        z[ix + 1]++; // переносим единицу в старший разряд
                        z[ix] %= 10; // в текущем разряде отсекаем ее
                    }
                }

                for (int i = length-1; i > 2; i--) {
                    if ((z[i]) == 0) {
                        length--;
                    }
                    else {
                        break;
                    }
                }
                StringBuilder TEMP=getZerosStr(length);
                //string TEMP(length, 0);

                for (int i = 0; i < length; i++) {
                    TEMP.replace(i,i+1,String.valueOf((char)(z[length - i - 1] + 48)));

                }

                while (TEMP.charAt(0) == '0') {
                    if (TEMP.length() == 1) { break; }
                    TEMP.replace(0,1,"");
                    if (TEMP.length() == 1) { break; }
                }

                //cout << "TEMP=" << TEMP<<endl;
                TEMP.append(".");
                TEMP.append(TEMP2);
                //cout << "k=" << k << endl;


                return new Fraction(TEMP.toString());
            }
            if (getSign() == false && rightarg.getSign() == true) {
                Fraction b = rightarg.clone();
                b.setSign(false);
                return this.addition(b);
            }

            if (getSign() == true && rightarg.getSign() == false) {
                Fraction a = this.clone(), b = rightarg.clone();
                b.setSign(false);
                a.setSign(false);
                a = a.addition( b);
                a.setSign(true);
                return a;
            }
            if (getSign() == true && rightarg.getSign() == true) {
                Fraction a = this.clone(), b = rightarg.clone();
                b.setSign(false);
                a.setSign(false);

                return  b.subtraction(a);
            }


            if (getSign() == false && rightarg.getSign() == false &&
                    J == 2) {
                Fraction Temp = rightarg.subtraction(this);
                Temp.setSign(true);
                return Temp;
            }





        }catch (Exception e){ e.printStackTrace();}

        return rightarg;
    }

    Fraction multiplication(Fraction rightarg) {
        int k = Biggest(this.func(), this.wholepartlength, this.func(), this.wholepartlength, this.func2(), this.fractionpartlength, rightarg.func2(), rightarg.fractionpartlength);

        int[] This = func2();
        int[] Rightarg = rightarg.func2();
        int length = fractionpartlength + rightarg.fractionpartlength + 1;
        int[] a = new int[fractionpartlength], b = new int[rightarg.fractionpartlength], c = new int[length];
        for (int i = 0; i < length; i++) {
            c[i] = 0;
        }

        for (int i = 0; i < fractionpartlength; i++) {
            a[i] = This[fractionpartlength - i - 1];
        }
        for (int i = 0; i < rightarg.fractionpartlength; i++) {
            b[i] = Rightarg[rightarg.fractionpartlength - i - 1];
        }
        for (int ix = 0; ix < fractionpartlength; ix++)
            for (int jx = 0; jx < rightarg.fractionpartlength; jx++)
                c[ix + jx] += a[ix] * b[jx];
        for (int ix = 0; ix < length - 1; ix++) {
            c[ix + 1] += c[ix] / 10;
            c[ix] %= 10;
        }
        for (int i = length - 1; i > 1; i--) {
            if ((c[i]) == 0) length--;
            else break;
        }

        StringBuilder TEMP = getZerosStr(length);
        StringBuilder TEMP2 = new StringBuilder("0.");

        for (int i = 0; i < length; i++) {
            TEMP.replace(i, i + 1, String.valueOf((char) (c[length - i - 1] + 48)));

        }
        for (int i = 0; i < fractionpartlength + rightarg.fractionpartlength - length; i++) {
            TEMP2.append("0");
        }
        TEMP2.append(TEMP);
        Fraction FxF = new Fraction(TEMP2.toString());

        //////
        int[] A = func();
        int[] B = rightarg.func();
        int length2 = wholepartlength + rightarg.wholepartlength + 1;

        int[] C = new int[length2];
        for (int i = 0; i < length2; i++) {
            C[i] = 0;
        }
        for (int ix = 0; ix < wholepartlength; ix++)
            for (int jx = 0; jx < rightarg.wholepartlength; jx++)
                C[ix + jx] += A[ix] * B[jx];
        for (int ix = 0; ix < length2 - 1; ix++) {
            C[ix + 1] += C[ix] / 10;
            C[ix] %= 10;
        }
        for (int i = length2 - 1; i > 1; i--) {
            if ((C[i]) == 0) length2--;
            else break;
        }

        StringBuilder TEMP3 = getZerosStr(length2);


        for (int i = 0; i < length2; i++) {

            TEMP3.replace(i, i + 1, String.valueOf((char) (C[length2 - i - 1] + 48)));
        }
        if(TEMP3.length()==0){
            TEMP3.append("0");
        }
        TEMP3.append(".0");
        while (TEMP3.charAt(0) == '0') {
            if (TEMP3.charAt(0) == '.') {
                break;
            }
            if (TEMP3.length() == 1) {
                break;
            }
            TEMP3.replace(0, 1, "");
            if (TEMP3.length() == 1) {
                break;
            }

        }
        if(TEMP3.length()==2){
            TEMP3=new StringBuilder("0.0");
        }
        Fraction WxW = new Fraction(TEMP3.toString());


        //cout << WxW << endl;
        ///////////////
        int length3 = wholepartlength + rightarg.fractionpartlength + 1;

        int[] C1 = new int[length3];
        for (int i = 0; i < length3; i++) {
            C1[i] = 0;
        }
        for (int ix = 0; ix < wholepartlength; ix++)
            for (int jx = 0; jx < rightarg.fractionpartlength; jx++)
                C1[ix + jx] += A[ix] * b[jx];
        for (int ix = 0; ix < length3 - 1; ix++) {
            C1[ix + 1] += C1[ix] / 10;
            C1[ix] %= 10;
        }

	/*
	for (int i = length3 - 1; i >1; i--) {
		if ((C1[i]) == 0) length3--;
		else break;
	}
	*/
        StringBuilder TEMP4 = getZerosStr(length3);
        // string TEMP4(length3, 0);

        for (int i = 0; i < length3; i++) {

            TEMP4.replace(i, i + 1, String.valueOf((char) (C1[length3 - i - 1] + 48)));
        }

        for (int i = length3 - 1; i > 1; i--) {
            if ((C1[i]) == 0) length3--;
            else break;
        }
        while (TEMP4.charAt(0) == '0') {
            if (TEMP4.length() == 1) {
                break;
            }
            TEMP4.replace(0, 1, "");
            if (TEMP4.length() == 1) {
                break;
            }

        }
        if (rightarg.fractionpartlength < TEMP4.length()) {
            TEMP4.insert(TEMP4.length() - rightarg.fractionpartlength, ".");
        } else {
            StringBuilder a1 = new StringBuilder("0.");
            for (int i = 0; i < rightarg.fractionpartlength - TEMP4.length(); i++) {
                a1.append("0");
            }
            a1.append(TEMP4);
            TEMP4 = a1;

        }
       // System.out.println("T:" + TEMP4);
        //TEMP4 += ".0";
        Fraction WxF = new Fraction(TEMP4.toString());


        //////////////////////////
        length3 = rightarg.wholepartlength + fractionpartlength + 1;
        System.out.println("length3:"+length3);
        C1 = new int[length3];
        for (int i = 0; i < length3; i++) {
            C1[i] = 0;
        }
        for (int ix = 0; ix < fractionpartlength; ix++)
            for (int jx = 0; jx < rightarg.wholepartlength; jx++){
                //System.out.println("ix+jx:"+(ix+jx));
               // System.out.println(C1[2]+" "+ B[jx]);
               // System.out.println(C1[2]+" "+a[ix]);

                C1[ix + jx]
                        += (a[ix]
                        * B[jx]);}
        for (int ix = 0; ix < length3 - 1; ix++) {
            C1[ix + 1] += C1[ix] / 10;
            C1[ix] %= 10;
        }

        StringBuilder TEMP5 = getZerosStr(length3);


        for (int i = 0; i < length3; i++) {
            TEMP5.replace(i, i + 1, String.valueOf((char) (C1[length3 - i - 1] + 48)));

        }

        for (int i = length3 - 1; i > 1; i--) {
            if ((C1[i]) == 0) length3--;
            else break;
        }
        while (TEMP5.charAt(0) == '0') {
            if (TEMP5.length() == 1) {
                break;
            }
            TEMP5.replace(0, 1, "");
            if (TEMP5.length() == 1) {
                break;
            }

        }
        if (fractionpartlength < TEMP5.length()) {
            TEMP5.insert(TEMP5.length() - fractionpartlength, ".");
        } else {
            StringBuilder a3 = new StringBuilder("0.");
            for (int i = 0; i < fractionpartlength - TEMP5.length(); i++) {
                a3.append("0");
            }
            a3.append(TEMP5);
            TEMP5 = a3;

        }
        // cout << "T2:" << TEMP5 << endl;
        //TEMP4 += ".0";
        Fraction FxW = new Fraction(TEMP5.toString());


        ////////////////

        Fraction S;
        S = FxW.addition(WxW).addition(WxF).addition(FxF);

        StringBuilder Stub=new StringBuilder(S.toString());

        for(int i=Stub.length()-1;i>3;i--){

            if(Stub.charAt(i-2)=='.'){break;}
            if(Stub.charAt(i)!='0') {
                break;
            }
if(Stub.charAt(i)=='0') {
    Stub.delete(i, i + 1);
}

        }
        Fraction Ss;
        Ss =new Fraction(Stub.toString());
      //  System.out.println("Ss1:"+S.toString());
       // System.out.println("Ss:"+Stub);

        if ( (getSign() == true && rightarg.getSign() == false) ||
                (getSign() == false && rightarg.getSign() == true) )
        {
            Ss.setSign(true);
        }
       // System.out.println("FxW"+FxW);
        //System.out.println("WxW"+WxW);
       // System.out.println("WxF"+WxF);
       // System.out.println("FxF"+FxF);
        return Ss;
    }
    Fraction division(Fraction rightarg) {
        int TOCHPoz = wholepartlength - rightarg.wholepartlength + 1;
        int k = Biggest(this.func(), this.wholepartlength, rightarg.func(), rightarg.wholepartlength, this.func2(), this.fractionpartlength, rightarg.func2(), rightarg.fractionpartlength);
        System.out.println("k = " + k);
         int []This = func2();
         int []Rightarg = rightarg.func2();
        int length = fractionpartlength + rightarg.fractionpartlength + 1;
         int []a = new  int[fractionpartlength], b = new  int[rightarg.fractionpartlength];


        for (int i = 0; i < fractionpartlength; i++) {
            a[i] = This[fractionpartlength - i - 1];
        }
        for (int i = 0; i < rightarg.fractionpartlength; i++) {
            b[i] = Rightarg[rightarg.fractionpartlength - i - 1];
        }
        This = func();
        Rightarg = rightarg.func();


         int []A = new  int[fractionpartlength + wholepartlength];

        int i=0, j = 0;
        for (i = 0; i < fractionpartlength; i++) {

            A[i] = a[i];
        }
        for (int I = 0; I < wholepartlength; I++, i++) {

            A[i] = This[I];
        }

        //for (int I = 0; I < wholepartlength + fractionpartlength; I++) {

          //  cout << A[I] << "  ";
     //   }
       // cout << endl;
        /////////////////////////////
         int []B = new  int[rightarg.fractionpartlength + rightarg.wholepartlength];

        i = 0; j = 0;
        for (; i < rightarg.fractionpartlength; i++) {

            B[i] = b[i];
        }
        for (int I = 0; I < rightarg.wholepartlength; I++, i++) {

            B[i] = Rightarg[I];
        }
       // cout << endl;
       // for (int I = 0; I < rightarg.wholepartlength + rightarg.fractionpartlength; I++) {

           // cout << B[I] << "  ";
      //  }
      //  cout << endl;
        /////////////
        //////////
        if (k == 1) {
           // System.out.println("K----1");
            Fraction q1=new Fraction("0.0"), q2=new Fraction("0.0") , q4=new Fraction("0.0"), X10=new Fraction("10.0");


            int ciasti = 0;
            StringBuilder Chastnoe = new StringBuilder("");
            StringBuilder V = new StringBuilder("");
            StringBuilder V2 =  new StringBuilder("");
            int nevzEl = fractionpartlength + wholepartlength - 1;
            /////


            if (((fractionpartlength + wholepartlength) > (rightarg.fractionpartlength + rightarg.wholepartlength))||((fractionpartlength + wholepartlength) == (rightarg.fractionpartlength + rightarg.wholepartlength))) {

                for (int i1 = 0; i1 < (rightarg.fractionpartlength + rightarg.wholepartlength); i1++) {
                    V.append((char)(A[nevzEl] + 48));
                    nevzEl--;
                }
                 int []t = new  int[V.length()];
                for (int i2 = 0; i2 < V.length(); i2++) {
                    t[i2] = V.charAt(V.length() - i2 - 1) - 48;
                }
                if (Biggest(t, V.length(), B, rightarg.fractionpartlength + rightarg.wholepartlength, A, 0, A, 0) == 2) {

                    V.append((char)(A[nevzEl] + 48));
                    nevzEl--;
                    TOCHPoz--;
                }
                //cout << endl;

                for (int i3 = 0; i3 < (rightarg.fractionpartlength + rightarg.wholepartlength); i3++) {
                    V2.append((char)(B[rightarg.fractionpartlength + rightarg.wholepartlength - i3 - 1] + 48));

                }



                V.append(".0");
                V2.append(".0");
                //	cout << "V=" << V << endl;
                //	cout << "V2=" << V2 << endl;
                ////

                q1=new Fraction(V.toString());
                q2=new Fraction(V2.toString());
                //q3 = q1.subtraction(q2);
                q4 = q2;
                //	cout << endl << q3 << endl;

                while (Biggest(q1.func(), q1.wholepartlength, q2.func(), q2.wholepartlength, A, 0, A, 0) == 1) {
                    q2 = q2.addition(q4);
                    ciasti++;
                }
                //System.out.println("q2:"+q2);
                //System.out.println("q4:"+q4);
                q2 = q2.subtraction(q4);
            }
            if ((fractionpartlength + wholepartlength) < (rightarg.fractionpartlength + rightarg.wholepartlength)) {
                {

                    for (int i4 = 0; i4 < (fractionpartlength + wholepartlength); i4++) {

                        V.append((char)(A[nevzEl] + 48));
                        nevzEl--;
                    }
                     int []t = new  int[V.length()];
                    for (int i5 = 0; i5 < V.length(); i5++) {
                        t[i5] = V.charAt(V.length() - i5 - 1) - 48;
                    }
                    while (Biggest(t, V.length(), B, rightarg.fractionpartlength + rightarg.wholepartlength, A, 0, A, 0) == 2) {

                        V.append( "0");
                        t = new  int[V.length()];
                        for (int i6 = 0; i6 < V.length(); i6++) {
                            t[i6] = V.charAt(V.length() - i6 - 1) - 48;
                        }
                    }
                    //cout << endl;

                    for (int i7 = 0; i7 < (rightarg.fractionpartlength + rightarg.wholepartlength); i7++) {
                        V2.append ((char)(B[rightarg.fractionpartlength + rightarg.wholepartlength - i7 - 1] + 48));

                    }



                    V.append(".0");
                    V2.append(".0");
                    //cout << "V=" << V << endl;
                    //cout << "V2=" << V2 << endl;
                    ////

                    q1=new Fraction(V.toString());
                    q2=new Fraction(V2.toString());
                   // q3 = q1.subtraction(q2);
                    q4 = q2;
                   // cout << endl << q3 << endl;

                    while (Biggest(q1.func(), q1.wholepartlength, q2.func(), q2.wholepartlength, A, 0, A, 0) == 1) {
                        q2 = q2.addition(q4);
                        ciasti++;
                    }
                    q2 = q2.subtraction(q4);



                }
            }


            //	cout << endl << "q2=" << q2 << endl << "ci=" << ciasti << endl;
            Chastnoe.append((char)(ciasti + 48));
            Fraction RX = q1.subtraction(q2), TE;
            //	cout << "q5:" << RX << endl;
            System.out.println("Chastnoe1:"+Chastnoe);
            while (nevzEl >= 0) {
                ciasti = 0;
                StringBuilder temp = new StringBuilder("");

                temp.append((char)(A[nevzEl] + 48));
                temp.append(".0");
                TE=new Fraction(temp.toString());
                //System.out.println("RX:"+(RX).toString());
                //System.out.println("RX:"+(X10).toString());
              //  System.out.println("TE:"+(TE).toString());
                RX = (RX.multiplication(X10).addition(TE));


                if (Biggest(RX.func(), RX.wholepartlength, q4.func(), q4.wholepartlength, A, 0, A, 0) != 2) {
                    Fraction temp4 = q4;
                    while (Biggest(RX.func(), RX.wholepartlength, temp4.func(), temp4.wholepartlength, A, 0, A, 0) == 1) {
                        temp4 = temp4.addition(q4);
                        ciasti++;
                    }
                    temp4 = (temp4.subtraction(q4));
                    RX = RX.subtraction(temp4);
                }

                Chastnoe.append((char)(ciasti + 48));
               // System.out.println("Chastnoe2:"+Chastnoe);
                nevzEl--;
            }
            int Nuli = 7;
            while (Nuli > 0) {
                ciasti = 0;
                String temp = "";

                //temp += (char)(A[nevzEl] + 48);
                //temp += ".0";
                //TE.nach(temp);
               // System.out.println("RXM:"+RX);
                RX = (RX.multiplication(X10))/* + TE*/;


                if (Biggest(RX.func(), RX.wholepartlength, q4.func(), q4.wholepartlength, A, 0, A, 0) != 2) {
                    Fraction temp4 = q4;
                    while (Biggest(RX.func(), RX.wholepartlength, temp4.func(), temp4.wholepartlength, A, 0, A, 0) == 1) {
                        temp4 = temp4.addition(q4);
                        ciasti++;
                     //   System.out.println("Debug1");
                    }
                    temp4 = (temp4.subtraction(q4));
                    RX = RX.subtraction(temp4);
                }

                Chastnoe.append((char)(ciasti + 48));
                System.out.println("Chastnoe3:"+Chastnoe);
                Nuli--;
            }
            //if ((fractionpartlength + wholepartlength) > (rightarg.fractionpartlength + rightarg.wholepartlength)) {
            //Chastnoe.in
            Chastnoe.insert(TOCHPoz, ".");
            //}
            //	cout << "Chastnoe:" << Chastnoe << endl;

            Fraction Temp12;
            String Te=Chastnoe.toString();


            if(((int)Te.charAt(Te.length()-1)-48)>4){
                StringBuilder Str=new StringBuilder("0.");
                for(int d=0;d<(Te.length()-TOCHPoz-3);d++){
                    Str.append("0");
                }
                ////важно
                Str.append("1");
                System.out.println(((int)Te.charAt(Te.length()-1)-48));
                System.out.println("Al:"+(Te.length()-TOCHPoz-1));
                Chastnoe.deleteCharAt(Chastnoe.length()-1);
                Temp12=new Fraction(Chastnoe.toString());
                return Temp12.addition(new Fraction(Str.toString()));
            }else {
                Temp12=new Fraction(Chastnoe.toString());
                return Temp12;
            }

        }
        ////////////////
        if (k == 2) {
            //for(int g=0;g<fractionpartlength + wholepartlength;g++){
             //   System.out.println("A{"+g+"}:"+A[g]);
           // }

            //for(int g=0;g<fractionpartlength + wholepartlength;g++){
             //   System.out.println("A{"+g+"}:"+A[g]);
           // }
           // System.out.println("K-----2");
            TOCHPoz *= -1;
            Fraction q1=new Fraction(), q2=new Fraction(), q3=new Fraction(), q4=new Fraction(), X10;
            X10=new Fraction("10.0");
            int ciasti = 0;
            StringBuilder Chastnoe =  new StringBuilder("0.");
            StringBuilder V =  new StringBuilder("");
            StringBuilder V2 = new StringBuilder("");
            int nevzEl = fractionpartlength + wholepartlength - 1;
            /////

            if ((fractionpartlength + wholepartlength) > (rightarg.fractionpartlength + rightarg.wholepartlength)) {

                for (int i8 = 0; i8 < (rightarg.fractionpartlength + rightarg.wholepartlength); i8++) {
                    V.append((char)(A[nevzEl] + 48));
                    nevzEl--;
                }
                 int []t = new  int[V.length()];
                for (int i9 = 0; i9 < V.length(); i9++) {
                    t[i9] = V.charAt(V.length() - i9 - 1) - 48;
                }
                if (Biggest(t, V.length(), B, rightarg.fractionpartlength + rightarg.wholepartlength, A, 0, A, 0) == 2) {

                    V.append((char)(A[nevzEl] + 48));
                    nevzEl--;
                    TOCHPoz++;
                }


                for (int i9 = 0; i9 < (rightarg.fractionpartlength + rightarg.wholepartlength); i9++) {

                    V2.append ((char)(B[rightarg.fractionpartlength + rightarg.wholepartlength - i9 - 1] + 48));

                }



                V.append(".0");
                V2.append(".0");
              //  cout << "V=" << V << endl;
               // cout << "V2=" << V2 << endl;
                ////

                q1=new Fraction(V.toString());
                q2=new Fraction(V2.toString());
                q3 = q1.subtraction(q2);
                q4 = q2;
                //cout << endl << q3 << endl;

                while (Biggest(q1.func(), q1.wholepartlength, q2.func(), q2.wholepartlength, A, 0, A, 0) == 1) {
                    q2 = q2.addition(q4);
                    ciasti++;
                }
                q2 = q2.subtraction(q4);
            }

            if (((fractionpartlength + wholepartlength) < (rightarg.fractionpartlength + rightarg.wholepartlength))||((fractionpartlength + wholepartlength) == (rightarg.fractionpartlength + rightarg.wholepartlength))) {


                    for (int i10 = 0; i10 < (fractionpartlength + wholepartlength); i10++) {

                        V.append ((char)(A[nevzEl] + 48));
                        nevzEl--;
                    }
                     int []t = new  int[V.length()];
                    for (int i12 = 0; i12 < V.length(); i12++) {
                        t[i12] = V.charAt(V.length() - i12 - 1) - 48;
                    }
                    while (Biggest(t, V.length(), B, rightarg.fractionpartlength + rightarg.wholepartlength, A, fractionpartlength + wholepartlength, A, fractionpartlength + wholepartlength) == 2) {

                        V.append("0");
                        t = new  int[V.length()];
                        for (int i13 = 0; i13 < V.length(); i13++) {
                            t[i13] = V.charAt(V.length() - i13 - 1) - 48;
                        }
                    }
                    //		cout << endl;

                    for (int i11 = 0; i11 < (rightarg.fractionpartlength + rightarg.wholepartlength); i11++) {

                        V2.append ((char)(B[rightarg.fractionpartlength + rightarg.wholepartlength - i11 - 1] + 48));
                    }



                    V.append(".0");
                    V2.append(".0");
                    //	cout << "V=" << V << endl;
                    //	cout << "V2=" << V2 << endl;
                    ////

                System.out.println("V:"+V.toString()+" V2:"+V2.toString());
                    q1=new Fraction(V.toString());
                    q2=new Fraction(V2.toString());
                    q3 = q1.subtraction(q2);
                    q4 = q2;
                   // cout << endl << q3 << endl;

                    while (Biggest(q1.func(), q1.wholepartlength, q2.func(), q2.wholepartlength, A, 0, A, 0) == 1) {
                        q2 = q2.addition(q4);
                        ciasti++;
                    }
               // System.out.println("Chast1:"+ciasti);
                    q2 = q2.subtraction(q4);




            }


            //cout << endl << "q2=" << q2 << endl << "ci=" << ciasti << endl;
            Chastnoe.append((char)(ciasti + 48));
            Fraction RX = q1.subtraction(q2), TE;
            //	cout << "q5:" << RX << endl;
            while (nevzEl >= 0) {
                ciasti = 0;
                StringBuilder temp =new StringBuilder( "");

                temp.append((char)(A[nevzEl] + 48));
                temp.append(".0");
                TE=new Fraction(temp.toString());
                RX = (RX.multiplication(X10)).addition(TE);


                if (Biggest(RX.func(), RX.wholepartlength, q4.func(), q4.wholepartlength, A, 0, A, 0) != 2) {
                    Fraction temp4 = q4;
                    while (Biggest(RX.func(), RX.wholepartlength, temp4.func(), temp4.wholepartlength, A, 0, A, 0) == 1) {
                       // System.out.println("temp4:"+temp4);
                      //  System.out.println("q4:"+q4);
                        temp4 = temp4.addition(q4);
                        ciasti++;
                    }
                    temp4 = (temp4.subtraction(q4));
                    RX = RX.subtraction(temp4);
                }

                Chastnoe.append((char)(ciasti + 48));
                nevzEl--;
            }
            int Nuli = 7;
            while (Nuli > 0) {
                ciasti = 0;
                StringBuilder temp = new StringBuilder("");

                //temp += (char)(A[nevzEl] + 48);
                //temp += ".0";
                //TE.nach(temp);
                RX = (RX.multiplication(X10))/* + TE*/;


                if (Biggest(RX.func(), RX.wholepartlength, q4.func(), q4.wholepartlength, A, 0, A, 0) != 2) {
                    Fraction temp4 = q4;
                    while (Biggest(RX.func(), RX.wholepartlength, temp4.func(), temp4.wholepartlength, A, 0, A, 0) == 1) {
                        temp4 = temp4.addition(q4);
                        ciasti++;
                    }
                    temp4 = (temp4.subtraction(q4));
                    RX = RX.subtraction(temp4);
                }

                Chastnoe.append((char)(ciasti + 48));
                Nuli--;
            }
            //if ((fractionpartlength + wholepartlength) > (rightarg.fractionpartlength + rightarg.wholepartlength)) {

            //	cout<<endl<<"Toc"<<TOCHPoz<<endl;
            for (int i14 = 0; i14 < TOCHPoz; i14++) {
                Chastnoe.insert(2, "0");
            }

            //}
            //	cout << "Chastnoe:" << Chastnoe << endl;

            Fraction Temp12;
            String Te=Chastnoe.toString();


            if(((int)Te.charAt(Te.length()-1)-48)>4){
                StringBuilder Str=new StringBuilder("0.");
                for(int d=0;d<(Te.length()-4);d++){
                    Str.append("0");
                }
                ////важно
                Str.append("1");
                System.out.println(((int)Te.charAt(Te.length()-1)-48));
                System.out.println("Al:"+(Te.length()-TOCHPoz-1));
                Chastnoe.deleteCharAt(Chastnoe.length()-1);
                Temp12=new Fraction(Chastnoe.toString());
                return Temp12.addition(new Fraction(Str.toString()));
            }else {
                Temp12=new Fraction(Chastnoe.toString());
                return Temp12;
            }


        }
        if (k == 3) {
            System.out.println("K3");
            Fraction Temp12;
            Temp12=new Fraction("1.0");
            return Temp12;
        }

        return new Fraction("1.0");
    }






    int [] func() {
         int [] arr = new int[max];
        for (int i = 0; i < max; i++)
            arr[i] = 0;

        for (int i = 0; i < wholepartlength; i++)
            arr[i] = (int)(wholePart[i] - 48);

        return arr;
    }

     int []func2() {
         int [] arr = new int[max];
        for (int i = 0; i < max; i++)
            arr[i] = 0;

        for (int i = 0; i < fractionpartlength; i++)
            arr[i] = (int)(fraction[i] - 48);

        return arr;
    }
    int Biggest( int []first, int firstL,  int []second, int secondL,  int []first2, int firstL2,  int []second2, int secondL2) {
        int k = 3; // если к == 3, значит числа одинаковой длинны
        int length = firstL;
        if (firstL > secondL)
        {
            length = firstL;
            k = 1; // если к == 1, значит первое число длиннее второго
            return k;
        }
        else
        if (secondL > firstL)
        {
            length = secondL;
            k = 2; // если к == 2, значит второе число длиннее первого
            return k;
        }
        else {
           // System.out.println("firstL:"+firstL);
            //System.out.println("secondL:"+secondL);


            if(firstL==1){

                if(first[0] > second[0]){
                    k = 1; // значит первое число длиннее второго
                    return k;
                }
                if(first[0] < second[0]){
                    k = 1; // значит первое число длиннее второго
                    return k;
                }
                if (first[0] == second[0]){
                    k=3;
                }
            }

            else {
                for (int ix = firstL - 1; ix >= 0; ix--) // поразрядное сравнение весов чисел
                {
                    if (first[ix] > second[ix]) // если разряд первого числа больше
                    {
                        k = 1; // значит первое число длиннее второго
                        return k;

                    }

                    if (second[ix] > first[ix]) // если разряд второго числа больше
                    {
                        k = 2; // значит второе число длиннее первого
                        return k;

                    }
                }
            }

        }
        if (k == 3) {

            int length2 = firstL2;

            if (secondL2 < firstL2)
            {
                length2 = secondL2;

            }
            //cout << "LLL=" << length << endl;
            for (int ix = 0; ix < length2; ix++) // поразрядное сравнение весов чисел
            {
                if (first2[ix] > second2[ix]) // если разряд первого числа больше
                {
                    //cout << "111=" << 1 << endl;
                    k = 1; // значит первое число длиннее второго
                    return k;

                }

                if (second2[ix] > first2[ix]) // если разряд второго числа больше
                {
                   // cout << "222=" << 2 << endl;
                    k = 2; // значит второе число длиннее первого
                    return k;

                }
            }
            if (k == 3) {
                if (firstL2 > secondL2)
                {

                    k = 1; // если к == 1, значит первое число длиннее второго
                    return k;
                }
                else {
                    if (firstL2 < secondL2)
                    {

                        k = 2; // если к == 1, значит первое число длиннее второго
                        return k;
                    }
                    else {
                        k = 3;
                        return k;
                    }
                }



            }

            return k;
        }
        return k;
    }

    static public String toTenSystem(String number,int system ){



        int positionOfComma = number.indexOf('.');
        System.out.println(positionOfComma);

        Fraction whole=new Fraction("0.00");

        Fraction SystemFr=new Fraction(String.valueOf(system)+".00");
        System.out.println("step1:"+SystemFr);


        for(int i=0;i<positionOfComma;i++){

           Fraction Temp=new Fraction( String.valueOf(dig2int(number.charAt(i)))+".00");

            Fraction TeRM= SystemFr.exponentiation(positionOfComma-i-1);

            System.out.println("Temp"+i+"-"+Temp.toString()+"}{"+TeRM.toString());
            Temp=Temp.multiplication(TeRM);
            System.out.println("Tem2p"+i+"-"+Temp.toString());
            whole=whole.addition(Temp);
            System.out.println("i:"+i+"----"+whole.toString());
        }
        System.out.println("Roar11111111111111");
        for(int i=positionOfComma+1;i<number.length();i++){
            Fraction Temp=new Fraction( String.valueOf(dig2int(number.charAt(i)))+".00");
            Fraction TeRM= SystemFr.exponentiation(((i-(positionOfComma+1))*-1));
            System.out.println();
            System.out.println("Temp"+i+"-"+Temp.toString()+"}{"+TeRM.toString());
            System.out.println();
        }

        System.out.println("stepp:"+whole.toString());
        return "2";
    }
    static String Scale="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static int dig2int(char d)
    {
        int i;
        for (i=0; i<36; i++) if (Scale.charAt(i)==d) return i;
        return 0;
    }



    public Fraction exponentiation(int st){
        Fraction a= null;
        try {
            a = this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if(st>=0) {
            for (int i = 1; i < st; i++) {

                //  System.out.println("AA:"+a.toString());
                a = a.multiplication(this);
            }
            if (st == 0) {

                return new Fraction("1.00");
            }
        }
        else{
            System.out.println("Aa:"+a);
            Fraction Ome=new Fraction("1.00");
            for (int i = 1; i < st; i++) {

                //  System.out.println("AA:"+a.toString());
                a = a.multiplication(this);
            }
            System.out.println("1:"+Ome.toString());
            System.out.println("2:"+a.toString());
            System.out.println("Ome:"+Ome.division(a).toString()+"    "+Ome.toString()+"  0--"+a.toString());
            return Ome.division(a);
        }
        return a;
    }

}
