import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject{

    Handler handler;
    Random r = new Random();
    Animation animation;
    Play game;
    int choose = 0;


    private BufferedImage[] enemy = new BufferedImage[3];

    public Enemy(int x, int y, ID id, Handler handler, Sprites sprites, Play game) {
        super(x, y, id, sprites);
        this.handler = handler;
        this.game = game;

        enemy[0] = sprites.grabImage(4,1,32,32);
        enemy[1] = sprites.grabImage(5,1,32,32);
        enemy[2] = sprites.grabImage(6,1,32,32);

        animation = new Animation(10, enemy[0], enemy[1], enemy[2]);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tmpObj = handler.object.get(i);

            if (x >= 2000 || x <= 0 || y <= 0 || y >= 2000) {
                handler.removeObject(this);
            }

            if (tmpObj.getId() == ID.Block) {
                if (getBounds1().intersects(tmpObj.getBounds())) {
                    velX = 0;
                    velY = 0;
                    x += velX * 2f * -1.5f;
                    y += velY * 1.5f * -2f;
                    velX = 0;
                    velY = 0;
                } else if (choose == 0) {
                    velX = (r.nextInt(5) - 2);
                    velY = (r.nextInt(5) - 2);
                }
            }

            if (tmpObj.getId() == ID.Shoot) {
                if (getBounds().intersects(tmpObj.getBounds())) {
                    game.points+=5;
                    handler.removeObject(this);
                    handler.removeObject(tmpObj);
                }
            }
            animation.runAnimation();
        }
    }


    @Override
    public void render(Graphics g) {
        //g.setColor(Color.red);
        //g.fillRect(x,y,32,32);
        //g.drawImage(enemy,x,y,null);

        if(velX == 0 && velY == 0){
            g.drawImage(enemy[0],x,y,null);
        } else {
            animation.drawAnimation(g,x,y,0);
        }

        //enemy collider
        //Graphics2D g2d = (Graphics2D) g;
        //g.setColor(Color.yellow);
        //g2d.draw(getBounds1());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public Rectangle getBounds1() {
        return new Rectangle(x-16,y-16,64,64);
    }
}
