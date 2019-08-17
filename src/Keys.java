import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter {

    Handler handler;

    public Keys(Handler handler) {
        this.handler = handler;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tmpObj = handler.object.get(i);

            if (tmpObj.getId() == ID.Player){
                if(key ==KeyEvent.VK_W) handler.setUp(true);
                if(key ==KeyEvent.VK_A) handler.setLeft(true);
                if(key ==KeyEvent.VK_S) handler.setDown(true);
                if(key ==KeyEvent.VK_D) handler.setRight(true);
                //if(key ==KeyEvent.VK_1) handler.setChange(true);
            }
        }
        super.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tmpObj = handler.object.get(i);

            if (tmpObj.getId() == ID.Player){
                if(key ==KeyEvent.VK_W) handler.setUp(false);
                if(key ==KeyEvent.VK_A) handler.setLeft(false);
                if(key ==KeyEvent.VK_S) handler.setDown(false);
                if(key ==KeyEvent.VK_D) handler.setRight(false);
            }
        }
        super.keyReleased(e);
    }
}
