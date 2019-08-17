import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoCrate extends GameObject {

    private BufferedImage ammoCrate;

    public AmmoCrate(int x, int y, ID id, Sprites sprites) {
        super(x, y, id, sprites);

        ammoCrate = sprites.grabImage(6,2,32,32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.blue);
        //g.fillRect(x,y,48,48);
        g.drawImage(ammoCrate,x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,48,48);
    }
}
