import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(int width, int height, String title, Play play){

        JFrame frame = new JFrame(title);

        //window size in game
        frame.setPreferredSize(new Dimension(width, height));//pref
        frame.setMaximumSize(new Dimension(width, height));//max
        frame.setMinimumSize(new Dimension(width, height));//min

        frame.add(play);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
