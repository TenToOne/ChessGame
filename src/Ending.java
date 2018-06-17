package src;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ending{

    public static void ending(int num) {
        JFrame frm = new JFrame("Ending");
        ImageIcon ic1=new ImageIcon("./image/good ending.jpg");
        ImageIcon ic2=new ImageIcon("./image/bad ending.jpg");
        ImageIcon ic3=new ImageIcon("./image/hidden ending.jpg");
        JLabel lbImage1=null;
        switch(num) {
            case 1 : lbImage1 = new JLabel(ic1); break;
            case 2 : lbImage1 = new JLabel(ic2); break;
            case 3 : lbImage1 = new JLabel(ic3);
        }
        frm.add(lbImage1);
        frm.setVisible(true);
        frm.setBounds(0, 0, 540, 960);
        frm.setSize(540,960);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame frm = new JFrame("Ending");
        ImageIcon ic  = new ImageIcon("./image/hidden ending.jpg");
        JLabel lbImage1  = new JLabel(ic);

        frm.add(lbImage1);
        frm.setVisible(true);
        frm.setBounds(0, 0, 540, 960);
        frm.setSize(540,960);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

