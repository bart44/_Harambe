import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonus extends GameObject {

    private BufferedImage bonusCrate;


    public Bonus(int x, int y, ID id, Sprites sprites) {
        super(x, y, id, sprites);
        bonusCrate = sprites.grabImage(9,2,32,32);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.blue);
        //g.fillRect(x,y,48,48);
        g.drawImage(bonusCrate,x,y,null);
    }

    @Override
    public Rectangle getBounds() { return new Rectangle(x,y,48,48); }
}
