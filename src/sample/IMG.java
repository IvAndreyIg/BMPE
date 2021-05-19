package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class IMG implements Cloneable {


    @Override
    protected IMG clone() throws CloneNotSupportedException {
        IMG img=(IMG)super.clone();
        img.pixels=new Colours[width][heith];
        for(int j=0;j<heith;j++)
            for(int i=0;i<width;i++)
                img.pixels[i][j]=pixels[i][j].clone();
        return img;
    }

    public IMG getNotNullCopy(){
        if(this!=null){
            try {
                return this.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
         return null;
    }


    public int getHeith() {
        return heith;
    }

    public int getWidth() {
        return width;
    }

    byte[] Head=new byte[54];
    int width;
    int heith;
    int offset;
    Colours [][] pixels;

    public void changeСolor(int Red,int Green,int Blue,int Red2,int Green2,int Blue2){
        Colours a1=new Colours(Blue,Green,Red);
        Colours a2=new Colours(Blue2,Green2,Red2);


        for(int j=0;j<heith;j++)
            for(int i=0;i<width;i++) {
                if (pixels[i][j].compare(a1)){
                pixels[i][j]=a2;}
            }
    }


    public void SetRed(int Color){
        for(int j=0;j<heith;j++)
            for(int i=0;i<width;i++)
                pixels[i][j].setRed(Color);
    }
    public  void SetBlue(int Color){
        for(int j=0;j<heith;j++)
            for(int i=0;i<width;i++)
                pixels[i][j].setBlue(Color);
    }

    public void SetGreen(int Color){
        for(int j=0;j<heith;j++)
            for(int i=0;i<width;i++)
                pixels[i][j].setGreen(Color);
    }public  void SetOneColor(Colours pixel){
        for(int j=0;j<heith;j++)
            for(int i=0;i<width;i++)
                pixels[i][j].setColours(pixel);
    }
    public IMG() {
    }

    public byte[] getFileContent(){
        byte[] contentPapicha=new byte[54+heith*width*3+offset*heith];

        for(int k=0;k<54;k++){
            contentPapicha[k]=Head[k];
        }
        int o=54;
        for(int j=0;j<heith;j++){
            for(int i=0;i<width;i++){


                contentPapicha[o]=(byte)pixels[i][j].Blue;
                contentPapicha[o+1]=(byte)pixels[i][j].Green;
                contentPapicha[o+2]=(byte)pixels[i][j].Red;

                o+=3;
            }
            for(int p=0;p<offset;p++){
                contentPapicha[o]=0;
                o++;
            }

        }


        return contentPapicha;
    }

    public IMG[][]  cutImage(int Hparts,int Wparts){
        IMG[][] AraayIMG=new IMG[Wparts][Hparts];

        SizeIMG[][] sizes=new SizeIMG[Hparts][Wparts];

        int w=0;
        int W=w;
        int h=0;
        int H=h;
        for (int i=0;i<Hparts;i++){
            if(i==(Hparts-1)){
                H=heith;
            }else
                H=h+heith/Hparts;
            for(int j=0;j<Wparts;j++){


                if(j==(Wparts-1)){
                    W=width;
                }else
                    W=w+width/Wparts;

                sizes[i][j]=new SizeIMG(h,H,w,W);

                w=W;
            }
            w=0;
            h=H;


        }

        int N=0;

        for (int i=0;i<Hparts;i++){
            for(int j=0;j<Wparts;j++){


                AraayIMG[j][Hparts-1-i]=new IMG(this,sizes[i][j]);
                //System.out.print("h:"+sizes[i][j].hb+"-"+sizes[i][j].he+"}{"+"w:"+sizes[i][j].wb+"-"+sizes[i][j].we+"  ");

                N++;
            }
            //System.out.println("\n\n");


        }




        return AraayIMG;
    }

    public IMG(IMG origImg,SizeIMG origSize){
            heith=origSize.he-origSize.hb;
            width=origSize.we-origSize.wb;
        offset=width%4;
            //Head=origImg.Head;
        for(int k=0;k<54;k++){
            Head[k]=origImg.Head[k];
        }


            byte[] bsize=intToByteArray(54+heith*width*3+offset*heith);

            Head[2]=bsize[0];
            Head[3]=bsize[1];
            Head[4]=bsize[2];
            Head[5]=bsize[3];
            bsize=intToByteArray(width);
        Head[18]=bsize[0];
        Head[19]=bsize[1];
        Head[20]=bsize[2];
        Head[21]=bsize[3];

        bsize=intToByteArray(heith);
        Head[22]=bsize[0];
        Head[23]=bsize[1];
        Head[24]=bsize[2];
        Head[25]=bsize[3];
        bsize=intToByteArray(heith*width*3+offset*heith);
        Head[34]=bsize[0];
        Head[35]=bsize[1];
        Head[36]=bsize[2];
        Head[37]=bsize[3];

        pixels=new Colours[width][heith];

        for(int j=0;j<heith;j++){
            for(int i=0;i<width;i++){


                pixels[i][j]= origImg.pixels[i+origSize.wb][j+origSize.hb];

            }

        }

    }

    public IMG(String filepath) {
        byte[] fileContent=new byte[0];
        try {
            File fi = new File(filepath);
            fileContent = Files.readAllBytes(fi.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }



        for(int k=0;k<54;k++){
            Head[k]=fileContent[k];
        }
         width= byteArrayToInt2(fileContent[18], fileContent[19], fileContent[20], fileContent[21]);
         heith = byteArrayToInt2(fileContent[22], fileContent[23], fileContent[24], fileContent[25]);
         offset=width%4;
        pixels=new Colours[width][heith];
        int o=54;
        for(int j=0;j<heith;j++){
            for(int i=0;i<width;i++){

                pixels[i][j]= new Colours
                        (fileContent[o] & 0xFF,
                                fileContent[o+1] & 0xFF,
                                fileContent[o+2] & 0xFF);
                o+=3;
            }
            o+=offset;
        }
    }

    public static final int byteArrayToInt2(byte b1,byte b2,byte b3,byte b4) {
        return  ((int)(b4& 0xFF) << 24) + ((int)(b3& 0xFF) << 16) + ((int)(b2& 0xFF) << 8) + (int)(b1& 0xFF);
    }
    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value),
                (byte)(value >>> 8),
                (byte)(value >>> 16),
                (byte)(value >>> 24)};
    }

   public IMG(IMG amage)
    {
        heith=amage.heith/2+amage.heith%2;
        width=amage.width/2+amage.width%2;
        offset = width % 4;

        //width = byteArrayToInt2(static_cast<int>(head[18]),static_cast<int>(head[19]), static_cast<int>(head[20]), static_cast<int>(head[21]));
        //  height = byteArrayToInt2(static_cast<int>(head[22]), static_cast<int>(head[23]), static_cast<int>(head[24]), static_cast<int>(head[25]));

        int size=54+width*heith*3+heith*offset;
        for (int i = 0; i < 54; i++) {
            Head[i] = amage.Head[i];
        }
        byte[] bsize=intToByteArray(54+heith*width*3+offset*heith);

        //  System.out.println("\n");
        //System.out.println("h:"+heith+"w:"+width+"S:"+(54+heith*width*3+offset*heith));
        //System.out.println("\n");
        Head[2]=bsize[0];
        Head[3]=bsize[1];
        Head[4]=bsize[2];
        Head[5]=bsize[3];
        bsize=intToByteArray(width);
        Head[18]=bsize[0];
        Head[19]=bsize[1];
        Head[20]=bsize[2];
        Head[21]=bsize[3];

        bsize=intToByteArray(heith);
        Head[22]=bsize[0];
        Head[23]=bsize[1];
        Head[24]=bsize[2];
        Head[25]=bsize[3];
        bsize=intToByteArray(heith*width*3+offset*heith);
        Head[34]=bsize[0];
        Head[35]=bsize[1];
        Head[36]=bsize[2];
        Head[37]=bsize[3];


        pixels=new Colours[width][heith];

        for(int j=0;j<heith;j++){
            for(int i=0;i<width;i++){

                //System.out.println("i:"+i);
                //System.out.println("j:"+j);
                //System.out.println(origSize.hb);
                //System.out.println(origSize.wb);


            }

        }








        for (int i = 0; i < heith-amage.heith%2; i++) {
            for (int j = 0; j < width-amage.width%2; j++) {
                pixels[j][i]=fourPixToOne(amage.pixels[j*2][i*2+1],amage.pixels[j*2+1][i*2],amage.pixels[j*2][i*2],amage.pixels[j*2+1][i*2+1]);
            }

        }

        if(amage.width%2==1){
            for (int i = 0; i < heith; i++) {
                if((i*2+1)<amage.heith){
                    //pixels[width-1][i]=avaragepics2(amage->pixels[width-1][i*2],amage->pixels[width-1][i*2+1]);
                    pixels[width-1][i]=amage.pixels[width*2-2][i*2];
                }
                else {

                    pixels[width-1][i]=amage.pixels[width*2-2][i*2];
                    //pixels[width-1][i]=avaragepics2(amage->pixels[width-1][i*2],amage->pixels[width-1][i*2]);}
                }

            }}
        if(amage.heith%2==1){
            for (int j2 = 0; j2 < width; j2++) {
                if((j2*2+1)<amage.width){
                    pixels[j2][heith-1]=twoPixToOne(amage.pixels[j2*2][heith*2-2],amage.pixels[j2*2+1][heith*2-2]);
                    //pixels[j2][height-1]=amage->pixels[j2*2][height*2-2];
                }
                else{

                    //pixels[j2][height-1]=amage->pixels[j2*2][height*2-2];
                    pixels[j2][heith-1]=twoPixToOne(amage.pixels[j2*2][heith*2-2],amage.pixels[j2*2][heith*2-2]);


                }

            }
        }



        // pixel **pixels;



    }
        Colours fourPixToOne(Colours pix1, Colours pix2, Colours pix3, Colours pix4)
    {

        Colours Pixels[]={pix1,pix2,pix3,pix4};

        int max=0;
        int Max=0;
        int This=0;
        for(int i=0;i<4;i++){

            for(int j=0;j<4;j++){
                if(Pixels[i].compare(Pixels[j])){This++;}

            }
            if(This>Max){
                max=i;Max=This;}
        }


        return Pixels[max];
    }
    Colours twoPixToOne(Colours pix1, Colours pix2)
    {
        return new Colours ((pix1.Blue+pix2.Blue)/2,(pix1.Green+pix2.Green)/2,(pix1.Red+pix2.Red)/2);
    }

}
