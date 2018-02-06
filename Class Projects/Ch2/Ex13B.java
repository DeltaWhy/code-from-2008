// Question 2-13-b
//Michael Limiero
import java.awt.*;
import javax.swing.*;

/**
 *  This program displays a red cross on a white
 *  background.
 */

public class Ex13B extends JPanel
{
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method
                              //   to paint the background
    int xCenter = getWidth() / 2;
    int yCenter = getHeight() / 2;
    g.setColor(Color.RED);
    g.fillOval(xCenter - 30, yCenter - 30, 60, 60);
    g.setColor(Color.WHITE);
    g.fillOval(xCenter - 20, yCenter - 20, 40, 40);    
    g.setColor(Color.RED);
    g.fillOval(xCenter - 10, yCenter - 10, 20, 20);
  }

  public static void main(String[] args)
  {
    JFrame window = new JFrame("Ex13B");
    window.setBounds(300, 300, 200, 200);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Ex13B panel = new Ex13B();
    panel.setBackground(Color.WHITE);
    Container c = window.getContentPane();
    c.add(panel);
    window.setVisible(true);
  }
}

