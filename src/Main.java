package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main  extends  JFrame implements ActionListener {
    static ImageIcon back;
    static Image backI;

    JButton up;
    JButton down;
    int num=0;

    public Main(int num) {
        super("Invaded Chess");
        this.num=num;
        switch (num) {
            case 1 :
                back = new ImageIcon("./image/Start.jpg");
                up = new JButton("How To Play");
                down = new JButton("Game Start");
                break;
            case 2 :
                back = new ImageIcon("./image/How to play_1.jpg");
                up = new JButton("Back to Menu");
                down = new JButton("Next");
                break;
            case 3 :
                back = new ImageIcon("./image/How to play_2.jpg");
                up = new JButton("Prev");
                down = new JButton("Back to Menu");
                break;
        }
        backI= back.getImage();
        JLabel lbImage1 = new JLabel(back);
        lbImage1.setPreferredSize(new Dimension(540,800));
        this.setLayout(new FlowLayout());

        this.add(lbImage1);
        this.add(up);
        up.setPreferredSize(new Dimension(540,50));
        up.setBackground(Color.ORANGE);
        up.setFont(new Font("DIALOG", Font.PLAIN, 20));
        up.addActionListener(this);
        this.add(down);
        down.setPreferredSize(new Dimension(540,50));
        down.setBackground(Color.ORANGE);
        down.setFont(new Font("DIALOG", Font.PLAIN, 20));
        down.addActionListener(this);
        setResizable(false);
        this.setSize(540,960);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == up) {
            switch (num) {
                case 1 :
                    new Main(2);
                    dispose();
                    break;
                case 2 :
                    new Main(1);
                    dispose();
                    break;
                case 3 :
                    new Main(2);
                    dispose();
                    break;
            }
        }
        else {
            switch (num) {
                case 1:
                    int[] start = {0, 0, 0, 0};
                    try {
                        new Chess(1, start);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                    break;
                case 2 :
                    new Main(3);
                    dispose();
                    break;
                case 3 :
                    new Main(1);
                    dispose();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        back = new ImageIcon("./image/Start");
        backI= back.getImage();
        new Main(1);
    }
}
