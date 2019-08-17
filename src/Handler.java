import java.awt.Graphics;
import java.util.*;

public class Handler {

    ArrayList<GameObject> object = new ArrayList<>();

    private boolean up = false, down = false, right = false, left = false;

    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tmpObj = object.get(i);
            tmpObj.tick();
        }
    }


    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tmpObj = object.get(i);
            tmpObj.render(g);
        }
    }


    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public void addObject(GameObject tmpObj){
        object.add(tmpObj);
    }

    public void removeObject(GameObject tmpObj){
        object.remove(tmpObj);
    }
}
