package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/*class objtransfer implements java.io.Serializable
 {
 int token;
 String o_date;
 String type;
 int kg;
 int qty;
 String urg;
 String ETA;
	
 objtransfer(int token,String o_date,String type,int kg,int qty,String urg,String ETA)
 {
 this.token=token;
 this.o_date=o_date;
 this.type=type;
 this.kg=kg;
 this.qty=qty;
 this.urg=urg;
 this.ETA=ETA;
 }
	
 }
 */
public class Shoes implements ActionListener {

    public Socket s;
    public static DataInputStream dis;
    public DataOutputStream dos;
    public InputStream is;
    Connection con;

    Object data[][] = {{new Integer(10), "10/10/2019", "WF", "5", "14", "YES", "4:30"},
    {new Integer(11), "10/10/2019", "WF", "6", "17", "NO", "5:30"},
    {new Integer(12), "10/10/2019", "WF", "8", "21", "NO", "7:30"}};

    String columns[] = {"TOKEN", "DATE", "TYPE", "KG", "QUANTITY", "URGENT", "EXPECT TIME"};

    JFrame fwash = new JFrame();

    JPanel toppan = new JPanel();
    JPanel pan1 = new JPanel();
    JTable table1 = new JTable(data, columns);

    JTextField txtdone = new JTextField();

    JLabel lbtitle = new JLabel("#------HSV Laundry Shoes & Bags Dept------#");
    JLabel lbdone = new JLabel("Enter done Token.");

    JButton btndone = new JButton("Done");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int w = screenSize.width;
    int h = screenSize.height;

    public Shoes() throws Exception {
        s = new Socket("localhost", 7500);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        is = s.getInputStream();
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Harshit@123");

        btndone.addActionListener(this);
        //addlog();
    }

    void addlog() throws Exception {
        fwash.setSize(w, h);
        fwash.setLayout(null);
        toppan.setBounds(0, 0, 1400, 45);
        pan1.setBounds(350, 100, 700, 450);
        lbtitle.setBounds(500, 10, 200, 30);
        lbdone.setBounds(550, 560, 150, 40);
        txtdone.setBounds(700, 560, 100, 40);
        btndone.setBounds(650, 620, 100, 40);
        txtdone.setToolTipText("Enter Tokens");
        fwash.add(toppan);
        fwash.add(pan1);
        fwash.add(lbdone);
        fwash.add(txtdone);
        fwash.add(btndone);
        toppan.add(lbtitle);
        table1.setRowHeight(table1.getRowHeight() + 3);

        JScrollPane scrpan1 = new JScrollPane(table1);
        pan1.add(scrpan1);

//        btndone.addActionListener((ActionListener) this);
        fwash.setVisible(true);

        /*btndone.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         try {
         int i = Integer.parseInt(txtdone.getText());
         dos.writeChar('w');
         dos.writeInt(i);
         print(s, is, dis);
         } catch (Exception ae) {
         ae.printStackTrace();
         }
         }
         });*/
        /*btndone.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
         try {
         int x;
         x=Integer.parseInt(txtdone.getText());
                    
         dos.writeChar('w');
         dos.writeInt(x);
                    
         print();
         } catch (Exception e) {
         e.printStackTrace();
         }
         }
         });*/
    }

    public void actionPerformed(ActionEvent ae) {
        //try{
           /*DataInputStream temp1=new DataInputStream(s.getInputStream());
         DataOutputStream temp2=new DataOutputStream(s.getOutputStream());
         //           dos.writeChar('w');
         int x=Integer.parseInt(txtdone.getText());
         temp2.writeInt(x);
         System.out.println("sent "+ x);
         temp2.writeChar('w');
         temp2.writeChar('w');
         System.out.println("sent w");
         //print();
         }
         catch(Exception e)
         {
         e.printStackTrace();
         }*/

        if (ae.getSource() == btndone) {
            try {

                Statement st = con.createStatement();
                int x = Integer.parseInt(txtdone.getText());

                String sss = "UPDATE order1 set status='DONE' where token=" + x + " AND (type='B' OR type='S') AND status='pending'";
//            st.execute("UPDATE order1 set status='DONE where token="+x+" AND (type='wf' OR type='wi') AND status='pending'");
                st.execute(sss);

                ResultSet rsi = st.executeQuery("select count(*) from order1 o, current_orders c where (o.order_id=c.ord3 OR o.order_id=c.ord4) AND o.t_date=c.o_date AND o.status='pending' AND (type='S' OR type='B')");
                rsi.next();
                x = rsi.getInt(1);
                ResultSet rss = st.executeQuery("select o.token,o.t_date,o.type,o.kg,o.noc,o.urgent,o.t_time from order1 o, current_orders c where (o.order_id=c.ord3 OR o.order_id=c.ord4) AND o.t_date=c.o_date AND o.status='pending' AND (type='S' OR type='B')");

                data = new Object[x][7];
                int j = 0;
                while (rss.next()) {
                    data[j][0] = rss.getInt(1);
                    data[j][1] = rss.getDate(2);
                    data[j][2] = rss.getString(3);
                    data[j][3] = rss.getInt(4);
                    data[j][4] = rss.getInt(5);
                    data[j][5] = rss.getInt(6);
                    data[j][6] = rss.getTime(7);
                    j++;
                }
                table1 = new JTable(data, columns);
                JScrollPane scrpan1 = new JScrollPane(table1);
                pan1.removeAll();
                pan1.repaint();
                pan1.add(scrpan1);
                //System.out.println("Done printing");
                
                addlog();

            //print();
                //addlog();
                //print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void print() throws Exception {
        System.out.println("Starting printing");
        objtransfer a;
        int i = 0;
        dos.writeChar('s');
        System.out.println("read char");
        //System.out.println(s.getLocalAddress());

        i = dis.readInt();
        System.out.println("reading int");

        //int i=10;
        data = new Object[i][7];
//        System.out.println(i);

        ObjectInputStream ois = new ObjectInputStream(is);
        int j = 0;
        while (j < i) {
            a = (objtransfer) ois.readObject();
            //if(a.type=="wf"||a.type=="wi")
            {
                data[j][0] = a.token;
                data[j][1] = a.o_date;
                data[j][2] = a.type;
                data[j][3] = a.kg;
                data[j][4] = a.qty;
                data[j][5] = a.urg;
                data[j][6] = a.ETA;
            }
            j++;
        }

        table1 = new JTable(data, columns);
        JScrollPane scrpan1 = new JScrollPane(table1);
        pan1.removeAll();
        pan1.repaint();
        pan1.add(scrpan1);
        System.out.println("Done printing");
        //fwash.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Socket s = new Socket("localhost", 7500);
            //s = new Socket("localhost", 7500);
            //DataInputStream dis = new DataInputStream(s.getInputStream());
            //DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            //InputStream is = s.getInputStream();

//                int i=dis.readInt();
//                System.out.println(i);
            Shoes s = new Shoes();
            while (true) {
                System.out.println("in while");
                s.print();
                System.out.println("hi");

                s.addlog();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
