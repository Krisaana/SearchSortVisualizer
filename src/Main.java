import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    private ArrayList<Block> list;
    private int bottom;
    private int top;
    int target = 0;
    private int numBlocks = 20;

    boolean info = false;
    int canvasW;
    int loc;
    int blockSize = 20;

    int margins = 15;

    String numbers = "";

    boolean fin = false;
    public static PApplet pApplet;

    public Main() {
        pApplet = this;
    }

    public static void main(String[] args){
        PApplet.main("Main"); // name of class
    }

    public void settings () {
        canvasW = ((numBlocks + 1) * margins) + (numBlocks * blockSize);
        int canvasH = (blockSize * 2) + 300;
        size (canvasW, canvasH);

    }
    public void setup(){
        list = new ArrayList<>();

        int x = margins;
        int y = height - (margins + blockSize);
        for (int i = 0; i < numBlocks; i++){
            int index = (int)(Math.random()*100);
            Block b = new Block(x, y, blockSize, blockSize, index);
            list.add(b);
            x = x + blockSize + margins;
        }
        top = list.size() - 1;
        bottom = 0;
    }

    public void draw(){
        background (255);
        fill(221,160,221);
        rect(10, 10, canvasW - 20, 100);
        for (int i = 0; i < list.size(); i++){
            list.get(i).display();
        }
        if(fin == true){
            if(loc != -1){
                fill(0);
                textAlign(CENTER);
                textSize(20);
                text("Element found at index " + loc +"!", canvasW/2, 150);
            }else{
                fill(0);
                textAlign(CENTER);
                textSize(20);
                text("Element not found in array", canvasW/2, 150);
                textSize(14);
            }
        }
        if(info == true){
            textAlign(CENTER);
            textSize(14);
            stroke(0);
            String info = "Press s to sort the array. " +'\n' +
                    "Type in the number you want to find and press n. " + '\n'+
                    "Press b to search the array until the number is found. " + '\n' +
                    "Press e to start over! ";
            text(info, width/2, 30);
        } else{
            textAlign(CENTER);
            textSize(20);
            stroke(0);
            text("Instructions", width/2, 50);
            textSize(14);
        }
    }

    public void keyPressed(){
        if(Character.isDigit(key)){
            numbers = numbers + key;
        }else if(key == 'n'){
            target = Integer.parseInt(numbers);
            numbers = "";
        }
        if(key == 's'){
            selectionSort();
        }
        if(key == 'b'){
            loc = binarySearchIterative();
        }
        if(key == 'e'){
            fin = false;
            for(int i = 0; i < list.size(); i++){
                Block b = list.get(i);
                b.hasChecked(false);
            }
            top = list.size() - 1;
            bottom = 0;
        }
    }

    public void mouseClicked(){
        if(mouseX>10 && mouseX<(width-20 +10) && mouseY > 5 && mouseY<(5 + 100)){
            info = !info;
        }
    }

    private void selectionSort(){
        for (int curIndex = 0; curIndex < list.size() - 1; curIndex++) {
            int minIndex = curIndex;
            int thing = curIndex;
            for (int next = curIndex + 1; next < list.size(); next ++){
                if (list.get(next).getID() < list.get(thing).getID()) {
                    minIndex = next;
                    thing = next;
                }
            }
            int curX = list.get(curIndex).getX();
            int minX = list.get(minIndex).getX();
            list.get(curIndex).setX(minX);
            list.get(minIndex).setX(curX);

            Block boo = list.get(curIndex);
            list.set(curIndex, list.get(minIndex));
            list.set(minIndex, boo);
        }

    }

    //private int binarySearchRecursive(int bottom, int top, int target) {
        //bottom = 0;
       // top = list.size() - 1;
       // int mid = (bottom + top) / 2;
       // if (bottom > top) {
           // return -1;
       // }
       // if (target == list.get(mid)) {
            //  return mid;
       // } else if (target < list.get(mid)) {
           // return binarySearchRecursive(bottom, mid - 1, target);
       // } else {
          //  return binarySearchRecursive(mid + 1, top, target);
      //  }
  //  }

    private int binarySearchIterative() {
        if (bottom <= top) {
            int mid = (bottom + top) / 2;
            list.get(mid).hasChecked(true);
            if (target == list.get(mid).getID()) {
                fin = true;
                return mid;
            } else if (target < list.get(mid).getID()) {
                top = mid - 1;
            } else {
                bottom = mid + 1;
            }
        }
        if(bottom > top){
            fin = true;
        }
        return -1;
    }
}

