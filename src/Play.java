import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.*;

public class Play extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private Sprites sprites;
    private Font myFont = new Font ("Courier New", 1, 32);

    private BufferedImage level64 = null;
    private BufferedImage sprite = null;
    private BufferedImage floor = null;


    public int ammo = 20;
    public int hp = 100;
    public int points = 0;


    public Play() {

        new Window(1200,1200,"myGame",this);
        start();


        handler = new Handler();
        camera = new Camera(0,0);
        this.addKeyListener(new Keys(handler)); //listener for key Inputs


        BufferedImageLoader loader = new BufferedImageLoader();
        level64 = loader.loadImage("/level64.png");
        sprite = loader.loadImage("/spriteAlfa2.png");

        sprites = new Sprites(sprite);
        floor = sprites.grabImage(4,2,32,32);

        this.addMouseListener(new Mouse(handler,camera,this,sprites));

        loadLevel(level64);

        handler.addObject((new Monkey(100,100,ID.Player, handler, this, sprites)));
    }


    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() throws InterruptedException {
        isRunning = false;
        thread.join();
    }
    public void tick(){

        for (int i=0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player) {
                camera.tick(handler.object.get(i));
            }
        }
        handler.tick();
    }

    public void gameOver() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3); //param - creates 3 frames before showup
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.red);
        g.setFont (myFont);
        g.drawString("GAME OVER!",500,500);
        g.drawString("Final Score " + (points + ammo + hp), 500, 530);
        g.dispose();
        bs.show();

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3); //param - creates 3 frames before showup
            return;
        }
        //init draw
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;


        //draw anything
        //g.setColor(Color.cyan);
        //g.fillRect(0,0,1200,1200);

        g2d.translate(-camera.getX(), -camera.getY());

        for (int xx = 0; xx < 30*72; xx+=32){
            for (int yy = 0; yy < 30*72; yy+=32){
                g.drawImage(floor, xx, yy, null);
            }
        }

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());


        //hp bar
        g.setColor(Color.gray);
        g.fillRect(5,5,200,32);
        g.setColor(Color.green);
        g.fillRect(5,5,hp*2,32);
        g.setColor(Color.black);
        g.drawRect(5,5,200,32);
        g.setFont (myFont);
        g.drawString("HP",5,32);

        //points
        g.setColor(Color.red);
        g.setFont (myFont);
        g.drawString("Points " + points,5,64);

        //ammo left
        g.setColor(Color.red);
        g.setFont (myFont);
        g.drawString("Ammo " + ammo,1050,24);

        //game over
        if(hp <= 0 || handler.object.size() == 1312 ) {
            gameOver();
        }

        //show
        g.dispose();
        bs.show();

        //System.out.println(handler.object.size());
    }

    //loading level
    private void loadLevel(BufferedImage image){
        int h = image.getHeight();
        int w = image.getWidth();

        for (int xx = 0; xx < w; xx++) {
            for (int yy=0; yy < h; yy++) {
                int pixel = image.getRGB(xx,yy);
                int red = (pixel>>16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) {
                    handler.addObject((new Block(xx*32, yy*32, ID.Block, sprites)));
                }

                if (blue == 255 && green == 0) {
                    handler.addObject((new Monkey(xx*32, yy*32, ID.Player, handler, this, sprites)));
                }

                if (green == 255 && blue == 0) {
                    handler.addObject((new Enemy(xx*32, yy*32, ID.Enemy, handler, sprites, this)));
                }

                if (green == 255 && blue == 255) {
                    handler.addObject((new AmmoCrate(xx*32, yy*32, ID.Crate, sprites)));
                }

                if (green == 255 && red == 250 && blue == 127) {
                    handler.addObject((new Bonus(xx*32, yy*32, ID.Bonus, sprites)));
                }
            }
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Play play = new Play();

    }
}
