public class Camera {

    private float x,y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){
        x += ((object.getX() - x) - 900/2) * 0.05f;
        y += ((object.getY() - y) - 900/2) * 0.05f;

        if (x <= 0) x = 0;
        if (x >= 900) x = 900;
        if (y <= 0) y = 0;
        if (y >= 1090) y = 1090;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
