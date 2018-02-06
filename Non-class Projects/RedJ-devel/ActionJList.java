//http://www.rgagnon.com/javadetails/java-0201.html
import javax.swing.*;
import java.awt.event.*;

public class ActionJList extends JList {
 /*
 ** sends ACTION_PERFORMED event for double-click
 ** and ENTER key
 */
 ActionListener al;

 public ActionJList(String[] it){
  super(it);

 addMouseListener(new MouseAdapter() {
  public void mouseClicked(MouseEvent me) {
   if (al == null) return;
   Object ob[] = getSelectedValues();
   if (ob.length > 1) return;
   if (me.getClickCount() == 2) {
     al.actionPerformed(new ActionEvent(this,
        ActionEvent.ACTION_PERFORMED,
        ob[0].toString()));
     me.consume();
     }
   }
  });

  addKeyListener(new KeyAdapter() {
   public void keyReleased(KeyEvent ke) {
    if (al == null) return;
    Object ob[] = getSelectedValues();
    if (ob.length > 1) return;
      if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
        al.actionPerformed(new ActionEvent(this,
        ActionEvent.ACTION_PERFORMED,
        ob[0].toString()));
        ke.consume();
        } 
    }
   });
   this.setSelectedIndex(0); 
  }

  public void addActionListener(ActionListener al){
   this.al = al;
   }
}