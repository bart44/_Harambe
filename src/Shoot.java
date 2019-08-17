import java.awt.*;
import java.awt.image.BufferedImage;

public class Shoot extends GameObject {

    private Handler handler;
    private int bulletSpeed = 40; //less is faster
    Animation animation;

    private BufferedImage[] banana = new BufferedImage[4];

    public Shoot(int x, int y, ID id, Handler handler, int mouseX, int mouseY, Sprites sprites) {
        super(x, y, id, sprites);
        this.handler = handler;
        velX = (mouseX - x)  / bulletSpeed;
        velY = (mouseY - y) / bulletSpeed;

        banana[0] = sprites.grabImage(7,1,24,24);
        banana[1] = sprites.grabImage(8,1,24,24);
        banana[2] = sprites.grabImage(9,1,24,24);
        banana[3] = sprites.grabImage(7,2,24,24);

        animation = new Animation(1, banana[0], banana[1], banana[2], banana[3]);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tmpObj = handler.object.get(i);

            if (velX == 0 && velY == 0) { //remove bullet if it get stuck in place
                handler.removeObject(this);
            }

            if (tmpObj.getId() == ID.Block){ //remove bullet if it touches wall
                if (getBounds().intersects(tmpObj.getBounds())){
                    handler.removeObject(this);
                }
            }
        }
        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.black);
        //g.fillRect(x,y,4,4);
        animation.drawAnimation(g,x,y,0);
    }



    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,4,4);
    }
}
