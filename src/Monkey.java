import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Monkey extends GameObject {

    Handler handler;
    Play game;
    Animation animation;

    private BufferedImage[] monkey = new BufferedImage[3];

    public Monkey(int x, int y, ID id, Handler handler, Play game, Sprites sprites) {
        super(x, y, id, sprites);
        this.handler = handler;
        this.game = game;

        monkey[0] = sprites.grabImage(1,1,32,48);
        monkey[1] = sprites.grabImage(2,1,32,48);
        monkey[2] = sprites.grabImage(3,1,32,48);

        animation = new Animation(20, monkey[0], monkey[1], monkey[2]);


    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) x = 0;
        if (x >= 2000) x = 2000;
        if (y <= 0) y = 0;
        if (y >= 2000) y = 2000;

        collision();

        //movement of monkey
        if(handler.isUp()) velY = -4;
        else if (!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = 4;
        else if (!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = 4;
        else if (!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -4;
        else if (!handler.isRight()) velX = 0;

        animation.runAnimation();

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.yellow);
        //g.fillRect(x,y,32,32);
        if(velX == 0 && velY == 0){
            g.drawImage(monkey[0],x,y,null);
        } else {
            animation.drawAnimation(g,x,y,0);
        }


    }

    private void collision() {
            for (int i = 0; i < handler.object.size(); i++){
                GameObject tmpObj = handler.object.get(i);

                if (tmpObj.getId() == ID.Block){
                    if (getBounds().intersects(tmpObj.getBounds())){
                        x += velX * -1f;
                        y += velY * -1f;
                       // game.hp-=5; //touching walls do not harm
                    }
                }

                if (tmpObj.getId() == ID.Crate){
                    if (getBounds().intersects(tmpObj.getBounds())){
                        game.ammo += 10;
                        handler.removeObject(tmpObj);
                    }
                }

                if (tmpObj.getId() == ID.Enemy){
                    if (getBounds().intersects(tmpObj.getBounds())){
                        game.hp--;
                    }
                }

                if (tmpObj.getId() == ID.Player){ //remove player when 0 hp
                    if (getBounds().intersects(tmpObj.getBounds())){
                        if (game.hp <= 0) {
                            handler.removeObject(tmpObj);
                        }
                    }
                }

                if (tmpObj.getId() == ID.Bonus){
                    if (getBounds().intersects(tmpObj.getBounds())){
                        game.points += 100;
                        handler.removeObject(tmpObj);
                    }
                }
            }
        }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
