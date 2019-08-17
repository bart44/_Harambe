import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected float velX = 0, velY = 0; //speed
    protected ID id;
    protected Sprites sprites;

    public GameObject(int x, int y, ID id, Sprites sprites) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.sprites = sprites;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds(); //collider

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() { return x; }

    public int getY() {
        return y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
