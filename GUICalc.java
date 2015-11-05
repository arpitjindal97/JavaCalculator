import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class GUICalc implements ActionListener
{
    JLabel answer;
    JTextField tf;
    public static void main(String arg[])
    {
        new test();
    }
    test()
    {
        tf=new JTextField();
        tf.setBounds(5,10,290,30);
        
        answer=new JLabel();
        answer.setBounds(15,5+42,80+40,30);
        answer.setText("Answer");
        
        JButton b1=new JButton("Calculate");
        b1.setBounds(175,5+40,80+40,30);
        b1.addActionListener(this);
        
        JFrame f=new JFrame("Calculator");
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        
        f.add(answer);
        f.add(b1);
        f.add(tf);
        f.getRootPane().setDefaultButton(b1);
        f.setSize(315,300);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
           Calculate t=new Calculate(tf.getText());
           String str=t.getString();
           answer.setText(str);
    }
}
