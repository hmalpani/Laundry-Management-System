package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.*;
import java.awt.Font;
import java.sql.PreparedStatement;

class new_po {

    public static int flag = 0;

    JFrame Fr;
    JLabel namL, add1L, add2L, add3L, mobL, line1, task, quantity, f_bill, weight, line2, line3, line4, line5, bill1, bill2, bill3, bill4, task2, quantity2, weight2, task3, quantity3, weight3, task4, quantity4, weight4, bill_rec, tm, dt;
    JLabel TB, RM;
    int total = 0, Rem = 0;
    JTextField tf1;
    JButton check, cancel, confirm;
    String dat, tim;

    void add(int Cust_i, int yz, Connection con) throws Exception {
        //System.out.println("xxxxxxxxxxxxxxx" + Cust_i);
        String nam = "";
        String add1 = "";
        String add2 = "";
        String add3 = "";
        String mob = "";
        String t1 = "", t4 = "", t2 = "", t3 = "";
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0, w1 = 0, w2 = 0, w3 = 0, w4 = 0, a1 = 0, b1 = 0, to1 = 0, to2 = 0, to3 = 0, to4 = 0, a2 = 0, b2 = 0, a3 = 0, b3 = 0, a4 = 0, b4 = 0, TO = 0;
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("select * from Customer where cust_id=" + Cust_i);
        //System.out.println("select * from Customer where cust_id=" + Cust_i);
        while (rs1.next()) {
            nam = rs1.getString(2);
            mob = rs1.getString(3);
            add1 = rs1.getString(4);
            add2 = rs1.getString(5);
            add3 = rs1.getString(6);
        }
        //	Statement st4=con.createStatement();
        //	Resultset rs121=st4.executeQuery(arg0)
        if (yz == 0) {
            Statement st3 = con.createStatement();
            ResultSet rs1229 = st3.executeQuery("select CURTIME()");
            //System.out.println("select * from Customer where mobile='"+mo+"'");
            while (rs1229.next()) {
                tim = rs1229.getString(1);
                //System.out.println(g);
            }
            Statement st34 = con.createStatement();
            ResultSet rs1239 = st3.executeQuery("select CURDATE()");
            //System.out.println("select * from Customer where mobile='"+mo+"'");
            while (rs1239.next()) {
                dat = rs1239.getString(1);
                //System.out.println(g);
            }
        }

        Statement st1 = con.createStatement();
        //System.out.println("select * from Current_orders where cust_id=" + Cust_i + " and o_time='" + tim + "' and o_date='" + dat + "'");;
        ResultSet rs12 = st1.executeQuery("select * from Current_orders where cust_id=" + Cust_i + " and o_time='" + tim + "' and o_date='" + dat + "'");
        while (rs12.next()) {
            dat = rs12.getString(8);
            tim = rs12.getString(9);
            //System.out.println("mmm" + dat + tim);
            int x1 = rs12.getInt(2);
            if (x1 != 0) {
                Statement st11 = con.createStatement();
                ResultSet rs121 = st11.executeQuery("select * from Order1 where order_id=" + x1);
                while (rs121.next()) {
                    q1 = rs121.getInt(3);
                    w1 = rs121.getInt(5);
                    a1 = w1;
                    b1 = rs121.getInt(12);
                    to1 = a1 * b1;
                    total = total + to1;
                }
            }
            int x2 = rs12.getInt(3);
            if (x2 != 0) {
                Statement st12 = con.createStatement();
                ResultSet rs122 = st12.executeQuery("select * from Order1 where order_id=" + x2);
                while (rs122.next()) {
                    q2 = rs122.getInt(3);
                    w2 = rs122.getInt(5);
                    a2 = w2;
                    b2 = rs122.getInt(12);
                    to2 = a2 * b2;
                    total = total + to2;
                }
            }
            int x3 = rs12.getInt(4);
            if (x3 != 0) {
                Statement st12 = con.createStatement();
                ResultSet rs122 = st12.executeQuery("select * from Order1 where order_id=" + x3);
                while (rs122.next()) {
                    q3 = rs122.getInt(3);
                    w3 = rs122.getInt(5);
                    a3 = q3;
                    b3 = rs122.getInt(12);
                    to3 = a3 * b3;
                    total = total + to3;
                }
            }
            int x4 = rs12.getInt(5);
            if (x4 != 0) {
                Statement st12 = con.createStatement();
                ResultSet rs122 = st12.executeQuery("select * from Order1 where order_id=" + x4);
                while (rs122.next()) {
                    q4 = rs122.getInt(3);
                    w4 = rs122.getInt(5);
                    a4 = q4;
                    b4 = rs122.getInt(12);
                    to4 = a4 * b4;
                    total = total + to4;
                }
            }

        }
        System.out.println(nam + mob + add1);
        Fr = new JFrame();
        bill_rec = new JLabel("Bill Reciept");
        dt = new JLabel("Date : " + dat);
        tm = new JLabel("Time : " + tim);
        namL = new JLabel("Name : " + nam);
        mobL = new JLabel("Contact :" + mob);
        add1L = new JLabel("Address : " + add1);
        add2L = new JLabel("               " + add2);
        add3L = new JLabel("               " + add3);
        line1 = new JLabel("-----------------------------------------------------------------------------");

        task = new JLabel("Task :  " + "Whash And Fold");
        quantity = new JLabel("Quantity                                             :" + q1);
        weight = new JLabel("Weight                                                :" + w1);
        line2 = new JLabel("--------------------------------------------------------------");
        bill1 = new JLabel("Total                                       " + a1 + "X" + b1 + "=" + to1);

        task2 = new JLabel("Task :  " + "Wash And Iron");
        quantity2 = new JLabel("Quantity                                             :" + q2);
        weight2 = new JLabel("Weight                                                :" + w2);
        line3 = new JLabel("--------------------------------------------------------------");
        bill2 = new JLabel("Total                                        " + a2 + "X" + b2 + "=" + to2);

        task3 = new JLabel("Task :  " + "Shoes");
        quantity3 = new JLabel("Quantity                                             :" + q3);
        weight3 = new JLabel("Weight                                                :" + w3);
        line4 = new JLabel("--------------------------------------------------------------");
        bill3 = new JLabel("Total                                        " + a3 + "X" + b3 + "=" + to3);

        task4 = new JLabel("Task :  " + "Bags");
        quantity4 = new JLabel("Quantity                                             :" + q4);
        weight4 = new JLabel("Weight                                                :" + w4);
        line5 = new JLabel("--------------------------------------------------------------");
        bill4 = new JLabel("Total                                        " + a4 + "X" + b4 + "=" + to4);
        if(yz==0)
            Rem=total;
        TB = new JLabel("Total Bill :   		" + total);
        RM = new JLabel("Due  Amount :			" + Rem);

        tf1 = new JTextField();
        cancel = new JButton("Cancel");
        confirm = new JButton("Confirm");
        check = new JButton("Enter");
        //----------------------------------------------------------------------------------------
        Fr.setSize(400, 700);
        Fr.setLocationRelativeTo(add1L);
        Fr.setLayout(null);
        Fr.setVisible(true);
        //-------------------------------------------------------------------------------------
        bill_rec.setBounds(100, 5, 300, 30);
        dt.setBounds(5, 40, 150, 20);
        tm.setBounds(250, 40, 150, 20);
        namL.setBounds(50, 70, 300, 20);
        mobL.setBounds(50, 90, 300, 20);
        add1L.setBounds(50, 110, 300, 20);
        add2L.setBounds(50, 130, 300, 20);
        add3L.setBounds(50, 150, 300, 20);
        line1.setBounds(0, 170, 400, 10);
        task.setBounds(50, 180, 300, 20);
        quantity.setBounds(50, 200, 300, 20);
        weight.setBounds(50, 220, 300, 20);
        line2.setBounds(50, 230, 300, 10);
        bill1.setBounds(50, 240, 300, 20);

        task2.setBounds(50, 260, 300, 20);
        quantity2.setBounds(50, 280, 300, 20);
        weight2.setBounds(50, 300, 300, 20);
        line3.setBounds(50, 310, 300, 10);
        bill2.setBounds(50, 320, 300, 20);

        task3.setBounds(50, 340, 300, 20);
        quantity3.setBounds(50, 360, 300, 20);
        weight3.setBounds(50, 380, 300, 20);
        line4.setBounds(50, 390, 300, 10);
        bill3.setBounds(50, 400, 300, 20);

        task4.setBounds(50, 420, 300, 20);
        quantity4.setBounds(50, 440, 300, 20);
        weight4.setBounds(50, 460, 300, 20);
        line5.setBounds(50, 470, 300, 10);
        bill4.setBounds(50, 480, 300, 20);
        Font font;
        TB.setBounds(50, 510, 400, 30);
        font = new Font("Monospaced", Font.BOLD, 20);
        TB.setFont(font);
        bill_rec.setFont(font);
        RM.setBounds(50, 540, 400, 30);
        RM.setFont(font);

        tf1.setBounds(100, 590, 100, 30);
        check.setBounds(200, 590, 100, 30);
        check.setToolTipText("Create Final Reciept");
        cancel.setBounds(10, 630, 100, 30);
        confirm.setBounds(290, 630, 100, 30);
        System.out.println(nam + mob + add1);
        //---------------------------------------------------------------------------------
        Fr.add(bill_rec);
        Fr.add(dt);
        Fr.add(tm);
        Fr.add(namL);
        Fr.add(mobL);
        Fr.add(add1L);
        Fr.add(add2L);
        Fr.add(add3L);
        Fr.add(line1);
        Fr.add(task);
        Fr.add(quantity);
        Fr.add(weight);
        Fr.add(line2);
        Fr.add(bill1);
        Fr.add(task2);
        Fr.add(quantity2);
        Fr.add(weight2);
        Fr.add(line3);
        Fr.add(bill2);
        Fr.add(task3);
        Fr.add(quantity3);
        Fr.add(weight3);
        Fr.add(line4);
        Fr.add(bill3);
        Fr.add(task4);
        Fr.add(quantity4);
        Fr.add(weight4);
        Fr.add(line5);
        Fr.add(bill4);
        Fr.add(TB);
        Fr.add(RM);
        Fr.add(tf1);
        Fr.add(check);
        Fr.add(cancel);
        Fr.add(confirm);

        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fr.dispose();
                try {
                    insert(Cust_i, tim, con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }

                String x = tf1.getText();
                int re = Integer.parseInt(x);
                Rem = total - re;
                try {
                    insertR(Rem, Cust_i, tim, con);
                    flag = 1;
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                total = 0;
                try {
                    add(Cust_i, 1, con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fr.dispose();

                try {
                    del(Cust_i, con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                ////idhar
                try{
                if (Laundarygui.type == "wi" || Laundarygui.type == "wf")
                {
                    ++Laundarygui.wtoken;
                    Statement st=con.createStatement();
                    st.execute("update token set t=t+1");
                    System.out.print("increased");
                }
                else if(Laundarygui.type == "S" || Laundarygui.type == "B")
                {
                    ++Laundarygui.btoken;
                    Statement st=con.createStatement();
                    st.execute("update token2 set t=t+1");
                    //System.out.println();
                }
                }
                catch(Exception et)
                {
                    et.printStackTrace();
                }
                Fr.dispose();
            }
        });

    }

    void insert(int Cust_i, String ti, Connection con) throws Exception {
        Statement st = con.createStatement();
        String sq = "update Current_orders set total_bill=" + total + " where cust_id=" + Cust_i + " and o_time='" + ti + "'";
        //System.out.println("aa" + sq);
        st.executeUpdate(sq);

    }

    void del(int cu, Connection con) throws Exception {
        int o1 = 0, o2 = 0, o3 = 0, o4 = 0;
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("select * from Current_orders where cust_id=" + cu + " and o_time='" + tim + "' and o_date='" + dat + "'");
        //System.out.println("select * from Customer where cust_id="+Cust_i);
        while (rs1.next()) {
            o1 = rs1.getInt(2);
            o2 = rs1.getInt(3);
            o3 = rs1.getInt(4);
            o4 = rs1.getInt(5);
        }

        Statement st2 = con.createStatement();

        String a1 = "delete from Order1 where order_id=" + o1 + "";
        String a2 = "delete from Order1 where order_id=" + o2 + "";
        String a3 = "delete from Order1 where order_id=" + o3 + "";
        String a4 = "delete from Order1 where order_id=" + o4 + "";
        String sq = "delete from Current_orders where cust_id=" + cu + " and o_time='" + tim + "' and o_date='" + dat + "'";
        //System.out.println(sq);
        st2.executeUpdate(a1);
        st2.executeUpdate(a2);
        st2.executeUpdate(a3);
        st2.executeUpdate(a4);
        st2.executeUpdate(sq);

    }

    void insertR(int x, int Cust_i, String ti, Connection con) throws Exception {
        Statement st = con.createStatement();
        String sq = "update Current_orders set rem_bill=" + x + " where cust_id=" + Cust_i + " and o_time='" + ti + "'";

        st.executeUpdate(sq);
        pop1.ar.firstElement().send_on_insert(con);
        pop1.ar.lastElement().send_on_insert(con);

    }

    void New_did(String mob, Connection con) throws Exception {
        int p = 0;
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("select * from Customer where mobile=" + mob);
        while (rs1.next()) {
            p = rs1.getInt(1);
        }
        try {
            add(p, 0, con);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
}

public class pop1 {

    public static Vector<ClientHandler> ar = new Vector<>();

    public static void main(String[] args) {
        try { // s="ert";
            //s1="asd";

            String st = "jdbc:mysql://localhost:3306/" + "project";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    st, "root", "Harshit@123");

            Statement sts = con.createStatement();
            ResultSet rs = sts.executeQuery("Select * from token");
            rs.next();

            int wtoken = rs.getInt(1);
            rs = sts.executeQuery("Select * from token2");
            rs.next();
            int btoken = rs.getInt(1);

            /*            Laundarygui G1 = new Laundarygui(wtoken, btoken);

             ServerSocket ss = new ServerSocket(7500);
             Socket s = ss.accept();

             ClientHandler mtch = new ClientHandler(s, G1);
             Thread t = new Thread(mtch);
             ar.add(mtch);
             t.start();

             s = ss.accept();
             ClientHandler mtch2 = new ClientHandler(s, G1);
             Thread t2 = new Thread(mtch2);
             ar.add(mtch2);
             t2.start();
             */
            Login l = new Login(wtoken, btoken, con);
            l.addlog();

            /*Statement sts = con.createStatement();
             ResultSet rs = sts.executeQuery("Select * from token");
             rs.next();

             int wtoken = rs.getInt(1);
             rs = sts.executeQuery("Select * from token2");
             rs.next();
             int btoken = rs.getInt(1);

             Laundarygui G1 = new Laundarygui(wtoken, btoken);*/
            //G1.add(con);
        } catch (Exception e1) {
            System.out.println(e1);
        }

    }
}

class ClientHandler implements Runnable {

    Socket s;
    Laundarygui G1;

    public ClientHandler(Socket ss, Laundarygui G1) {
        s = ss;
        this.G1 = G1;

    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Harshit@123");

            //Statement st=con.createStatement();
            //ResultSet rsi=st.executeQuery("Select count(*) from current_orders");
            //rsi.next();
            //int i=rsi.getInt(1);
            //ResultSet rss=st.executeQuery("select o.token,o.t_date,o.type,o.kg,o.noc,o.urgent,o.t_time from order1 o, current_orders c where o.order_id=c.ord1 OR o.order_id=c.ord2 OR o.order_id=c.ord3 OR o.order_id=c.ord4 AND o.t_date=c.o_date");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            send(con, dis, dos);

            Statement st = con.createStatement();
            ResultSet rsi = st.executeQuery("select count(*) from order1 o, current_orders c where (o.order_id=c.ord3 OR o.order_id=c.ord4 OR o.order_id=c.ord1 OR o.order_id=c.ord2) AND o.t_date=c.o_date AND o.status='pending'");
            rsi.next();
            int i = rsi.getInt(1);

            /*while(s.isConnected())
             {
             if
             send(con,dis,dos);
             }
            
             /*while(s.isConnected())
             {    
                
             int i=dis.readInt();
             System.out.println(i);
             char c=dis.readChar();
             System.out.println(c);
             char c2=dis.readChar();
             System.out.println(c2);
                
             Statement st=con.createStatement();
             String ss="Update order1 set status='DONE' where token="+i+" AND (type='wf' OR type='wi') AND status='pending'";
             send(con,dis,dos); 
             }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(Connection con, DataInputStream dis, DataOutputStream dos) throws Exception {
        Statement st = con.createStatement();

        char ty = dis.readChar();
        System.out.println(ty);

        //ResultSet rss=st.executeQuery("Select * from customer");
        //Send number of pending orders for clothes
        if (ty == 'w') {
            ResultSet rsi = st.executeQuery("select count(*) from order1 o, current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2) AND o.t_date=c.o_date AND o.status='pending' AND (type='wf' OR type='wi')");
            rsi.next();
            int i = rsi.getInt(1);
            System.out.println("Count=" + i);
            dos.writeInt(i);
            ResultSet rss = st.executeQuery("select o.token,o.t_date,o.type,o.kg,o.noc,o.urgent,o.t_time from order1 o, current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2) AND o.t_date=c.o_date AND o.status='pending' AND (type='wf' OR type='wi')");

            objtransfer a;
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            while (rss.next()) {
                a = new objtransfer(rss.getInt(1), rss.getString(2), rss.getString(3), rss.getInt(4), rss.getInt(5), rss.getString(6), rss.getString(7));
                oos.writeObject(a);
            }
        } else if (ty == 's') {
            ResultSet rsi = st.executeQuery("select count(*) from order1 o, current_orders c where (o.order_id=c.ord3 OR o.order_id=c.ord4) AND o.t_date=c.o_date AND o.status='pending' AND (type='S' OR type='B')");
            rsi.next();
            int i = rsi.getInt(1);
            System.out.println("Count=" + i);
            dos.writeInt(i);
            ResultSet rss = st.executeQuery("select o.token,o.t_date,o.type,o.kg,o.noc,o.urgent,o.t_time from order1 o, current_orders c where (o.order_id=c.ord3 OR o.order_id=c.ord4) AND o.t_date=c.o_date AND o.status='pending' AND (type='B' OR type='S')");

            objtransfer a;
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            while (rss.next()) {
                a = new objtransfer(rss.getInt(1), rss.getString(2), rss.getString(3), rss.getInt(4), rss.getInt(5), rss.getString(6), rss.getString(7));
                oos.writeObject(a);
            }
        }
    }

    //ResultSet rss = st.executeQuery("select o.token,o.t_date,o.type,o.kg,o.noc,o.urgent,o.t_time from order1 o, current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2 OR o.order_id=c.ord3 OR o.order_id=c.ord4) AND o.t_date=c.o_date AND o.status='pending'");
        /*objtransfer a;
     OutputStream os = s.getOutputStream();
     ObjectOutputStream oos = new ObjectOutputStream(os);

     while (rss.next()) {
     a = new objtransfer(rss.getInt(1), rss.getString(2), rss.getString(3), rss.getInt(4), rss.getInt(5), rss.getString(6), rss.getString(7));
     oos.writeObject(a);
     }*/
//}
    public void send_on_insert(Connection con) throws Exception {
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        //dos.writeUTF("SENDING");
        if (s.isConnected()) {
            send(con, dis, dos);
        }
        //pop1.ar.firstElement().send(con, dis, dos);
        //pop1.ar.lastElement().send(con, dis, dos);
    }
}

class objtransfer implements java.io.Serializable {

    int token;
    String o_date;
    String type;
    int kg;
    int qty;
    String urg;
    String ETA;

    objtransfer(int token, String o_date, String type, int kg, int qty, String urg, String ETA) {
        this.token = token;
        this.o_date = o_date;
        this.type = type;
        this.kg = kg;
        this.qty = qty;
        this.urg = urg;
        this.ETA = ETA;
    }

}
