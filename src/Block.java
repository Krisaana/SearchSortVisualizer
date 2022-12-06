public class Block {
    private int x;
    private int y;
    private int blockW;
    private int blockH;
    private int ID;
    private int checked;

    public Block(int _x, int _y, int _blockW, int _blockH, int _ID){
       x = _x;
       y = _y;
       blockW = _blockW;
       blockH = _blockH;
       ID = _ID;
       checked = 0;
   }
   public int getID(){

        return ID;
   }
    public int getX() {
        return x;
    }

    public void setX(int blah){
        x = blah;
    }

    public void display(){
        Main.pApplet.fill(checked);
        Main.pApplet.rect(x, y, blockW, blockH);
        Main.pApplet.fill(255);
        Main.pApplet.text(ID, x + 10, y + 13);
    }

    public void hasChecked(boolean color){
        if (color == true){
            checked = 210;
        }else{
            checked = 0;
        }
    }

}
