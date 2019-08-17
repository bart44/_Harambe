import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private Handler handler;
    private Camera camera;
    private Play game;
    private Sprites sprites;

    public Mouse(Handler handler, Camera camera, Play game, Sprites sprites){
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.sprites = sprites;
    }
    public void mousePressed(MouseEvent e){
        int mouseX = (int) (e.getX() + camera.getX());
        int mouseY = (int) (e.getY() + camera.getY());

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tmpObj = handler.object.get(i);

            if (tmpObj.getId() == ID.Player && game.ammo >= 1) {
                handler.addObject(new Shoot(tmpObj.getX()+16, tmpObj.getY()+16, ID.Shoot, handler, mouseX, mouseY, sprites));
                game.ammo--;
            }
        }
    }
}

