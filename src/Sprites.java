import java.awt.image.BufferedImage;

public class Sprites {

    private BufferedImage image;

    public Sprites(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return image.getSubimage((col*32)-32,(row*32)-32, width, height);
    }
}
