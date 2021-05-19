package sample;

public class Colours implements Cloneable {
    int Blue;
    int Green;
    int Red;

    public void setBlue(int blue) {
        Blue = blue;
    }

    public void setGreen(int green) {
        Green = green;
    }

    public void setRed(int red) {
        Red = red;
    }

    public Colours(int blue, int green, int red) {
        Blue = blue;
        Green = green;
        Red = red;
    }
    public void setColours(int blue, int green, int red){
        Blue = blue;
        Green = green;
        Red = red;
    }
    public void setColours(Colours C){
        Blue = C.Blue;
        Green = C.Green;
        Red = C.Red;
    }
    public Boolean compare(Colours color){

        return (Blue==color.Blue)&(Green==color.Green)&(Red==color.Red);
    }
    @Override
    protected Colours clone() throws CloneNotSupportedException {
        return (Colours)super.clone();
    }
}
