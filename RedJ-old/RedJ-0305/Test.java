import java.io.*;
import java.awt.*;
import javax.swing.*;
public class Test
{
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		Console a = new Console(26,80);
		a.append("Blah!\nBlah blah!\n");
		JScrollPane p = new JScrollPane(a);
		c.add(p);
		f.setBounds(300,300,0,0);
		f.setSize(a.getPreferredSize());
		f.setVisible(true);
	}
}
