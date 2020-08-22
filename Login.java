/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//utf8mb4
//utf8mb4_0900_ai_ci



package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Vishal
 */
public class Login {

    public int btoken, wtoken;
    Connection con;

    String userID, password;

    JFrame f = new JFrame("Laundary Management");
    JLabel lbbrand = new JLabel("#-------HSV Laundary------#");
    JLabel username = new JLabel("Enter Username:");
    JLabel pass = new JLabel("Enter Password:");

    JTextField txtuser = new JTextField();
    JPasswordField txtpass = new JPasswordField();

    JButton loginn = new JButton("Login");
    Font f1 = new Font(Font.MONOSPACED, Font.BOLD, 25);

    JPanel panel1 = new JPanel();
    JPanel pan2 = new JPanel();

    public Login(int wtoken, int btoken, Connection con) {
        this.con = con;
        this.wtoken = wtoken;
        this.btoken = btoken;
        userID = "HSV2123";
        password = "HSV2123";
    }

    void addlog() {
        panel1.setBounds(0, 0, 800, 45);
        username.setBounds(300, 150, 150, 40);
        txtuser.setBounds(300, 195, 150, 30);
        pass.setBounds(300, 250, 150, 40);
        txtpass.setBounds(300, 295, 150, 30);
        loginn.setBounds(300, 380, 150, 40);

        f.add(panel1);
        f.add(username);
        f.add(pass);
        f.add(txtuser);
        f.add(txtpass);
        f.add(loginn);
        panel1.add(lbbrand);

        lbbrand.setFont(f1);

        panel1.setBackground(Color.getHSBColor(16.0f, 194.0f, 245.0f));

        loginn.setBackground(Color.darkGray);
        loginn.setForeground(Color.white);
        loginn.setBorder(new LineBorder(Color.LIGHT_GRAY));

        f.setSize(800, 600);
        f.setLocationRelativeTo(pass);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
//                                    Laundarygui G1=new Laundarygui(wtoken,btoken);
                    //                            G1.add(con);
                    String x1, x2;
                    x1 = txtuser.getText();
                    x2 = Arrays.toString(txtpass.getPassword());
                    int i = x2.length();
                    //System.out.print(i);
                    String x3 = "";
                    for (int j = 0; j < i; j++) {
                        if (x2.charAt(j) != '[' && x2.charAt(j) != ']' && x2.charAt(j) != ' ' && x2.charAt(j) != ',') {
                            x3 += x2.charAt(j);
                        }
                        //j++;
                    }

                    System.out.println(x1 + "\n" + x2 + "\n" + x3);

                    if (x1.equals(userID) && x3.equals(password)) {

                        System.out.print("HELLO");

                        Laundarygui G1 = new Laundarygui(wtoken, btoken);

                        ServerSocket ss = new ServerSocket(7500);
                        Socket s = ss.accept();

                        ClientHandler mtch = new ClientHandler(s, G1);
                        Thread t = new Thread(mtch);
                        pop1.ar.add(mtch);
                        t.start();

                        s = ss.accept();
                        ClientHandler mtch2 = new ClientHandler(s, G1);
                        Thread t2 = new Thread(mtch2);
                        pop1.ar.add(mtch2);
                        t2.start();

                        G1.add(con);
                        f.dispose();
                    }
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });
    }
}
