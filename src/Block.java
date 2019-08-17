import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

    private BufferedImage blockImage;

    public Block(int x, int y, ID id, Sprites sprites) {
        super(x, y, id, sprites);
        blockImage = sprites.grabImage(5,2,32,32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.PINK);
        //g.fillRect(x,y,32,32);
        g.drawImage(blockImage,x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
