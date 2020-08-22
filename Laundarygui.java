package project;

import java.awt.Color;
import java.util.*;
//import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.*;

import javax.swing.border.LineBorder;
import javax.swing.Timer;

/**
 *
 * @author Vishal
 */
public class Laundarygui {

    public static int wtoken, btoken;

    String name, mobile, add1, add2, add3, typ;
    int wkg, qua;
    boolean urg, present_already = false;
    static int ns = 100;
    int tot;
    int cust_i;
    boolean inser = false;

    Object data[][] = {{new Integer(10), "10/10/2019", "WF", "5", "4:30"},
    {new Integer(11), "10/10/2019", "WF", "6", "5:30"},
    {new Integer(12), "10/10/2019", "WF", "8", "7:30"}};

    String columns[] = {"ORDER ID", "DATE", "TYPE", "KG", "NOC", "TIME"};

    Object data2[][] = {{new Integer(10), "10/10/2019", "WF", "4:30", new Double(200)},
    {new Integer(11), "10/10/2019", "WF", "5:30", new Double(20)},
    {new Integer(12), "10/10/2019", "WF", "7:30", new Double(150)}};

    String columns2[] = {"ORDER ID", "DATE", "TYPE", "TIME", "Amt"};

    JFrame jf1 = new JFrame("Recept");

    JTable tabpend = new JTable(data, columns);
    JTable tabwdone = new JTable(data2, columns2);

    JLabel lbbrand = new JLabel("#-------HSV Laundary------#");
    JLabel lbaddorder = new JLabel("#ORDER DETAILS#");
    JLabel lbcname = new JLabel("Name:");
    JLabel lbcmobile = new JLabel("Mobile No:");
    JLabel lbAddr = new JLabel("Address:");
    JLabel lbaddr1 = new JLabel("Flat/House No :");
    JLabel lbaddr2 = new JLabel("Locality :");
    JLabel lbaddr3 = new JLabel("Landmark :");
    JLabel lbordertype = new JLabel("Order Type :");
    JLabel lbKg = new JLabel("Quantity :");
    JLabel lbquantity = new JLabel("Weight(KG):");

    //------------------
    Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 17);
    Font f1 = new Font(Font.MONOSPACED, Font.BOLD, 25);

    //----------------------------------
    JTextField txtname = new JTextField();
    JTextField txtmob = new JTextField();
    JTextField txtaddr1 = new JTextField();
    JTextField txtaddr2 = new JTextField();
    JTextField txtpin = new JTextField();
    JTextField txtKGw = new JTextField();
    JTextField txtKGi = new JTextField();

    JTextField txtquantity = new JTextField();
    JTextField txtfbill = new JTextField();
    // JTextField txtq

    JRadioButton rbwf = new JRadioButton("WASH FOLD");
    JRadioButton rbwi = new JRadioButton("WASH IRON");
    JRadioButton rbs = new JRadioButton("SHOES");
    JRadioButton rbb = new JRadioButton("BAGS");
    JRadioButton rburg = new JRadioButton("Urgent");

    JButton log_out = new JButton("Log Out");
    JButton btnaddorder = new JButton("Add");//creating instance of JButton  
    JButton btnclear = new JButton("Clear");
    JButton btncsearch = new JButton("S");

    JButton btndone = new JButton("Pending Pickup");
    JButton btnpend = new JButton("Pending Orders");
    JButton btnfbill = new JButton("Final Bill");

    JButton btntake = new JButton("Pending Orders");
    JButton btnhistory = new JButton("Final Bill");

    JPanel panel1 = new JPanel();
    JPanel panel12 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel22 = new JPanel();

    JPanel panel3 = new JPanel(new CardLayout());
    JPanel panel31 = new JPanel();
    JPanel panel32 = new JPanel();
    JPanel panel33 = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    JPanel panel4 = new JPanel();

    JSpinner spquantityw;
    JSpinner spquantitys;
    JSpinner spquantityi;
    JSpinner spquantityb;

    public static String type;

    public Laundarygui(int wtoken, int btoken) {
        this.btoken = btoken;
        this.wtoken = wtoken;
        type = "";
    }

    void add(Connection con) throws Exception {
        panel31.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Pending Orders:"));

        panel32.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Pickup Pending:"));

        jf1.setSize(1400, 750);//w,h
        jf1.setLayout(null);

        lbaddorder.setBounds(150, 70, 400, 60);
        lbcname.setBounds(70, 120, 100, 30);
        txtname.setBounds(70, 145, 150, 30);
        // txtname.setToolTipText("Enter Name");
        lbcmobile.setBounds(250, 120, 100, 30);
        txtmob.setBounds(250, 145, 100, 30);
        lbAddr.setBounds(70, 190, 100, 30);
        lbaddr1.setBounds(170, 180 + 20, 100, 30);
        txtaddr1.setBounds(150, 205 + 20, 210, 30);
        lbaddr2.setBounds(170, 230 + 20, 100, 30);
        txtaddr2.setBounds(150, 255 + 20, 210, 30);
        lbaddr3.setBounds(170, 280 + 20, 100, 30);
        txtpin.setBounds(150, 305 + 20, 210, 30);

        lbordertype.setBounds(50, 355, 100, 30);
        rbwf.setBounds(130, 340 + 20 + 20, 100, 30);
        rbwi.setBounds(130, 380 + 20 + 20, 100, 30);
        rbs.setBounds(130, 420 + 20 + 20, 100, 30);
        rbb.setBounds(130, 460 + 20 + 20, 100, 30);
        rburg.setBounds(190, 540, 100, 30);

        lbKg.setBounds(235, 355, 100, 30);
        txtKGw.setBounds(340, 380, 100, 30);
        txtKGi.setBounds(340, 420, 100, 30);
        lbquantity.setBounds(340, 355, 100, 30);
        SpinnerModel valuew
                = new SpinnerNumberModel(0, //initial value  
                        0, //minimum value  
                        100, //maximum value  
                        1); //step  
        spquantityw = new JSpinner(valuew);
        spquantityw.setBounds(235, 380, 70, 30);

        SpinnerModel valuei
                = new SpinnerNumberModel(0, //initial value  
                        0, //minimum value  
                        100, //maximum value  
                        1); //step  
        spquantityi = new JSpinner(valuei);
        spquantityi.setBounds(235, 420, 70, 30);

        SpinnerModel values
                = new SpinnerNumberModel(0, //initial value  
                        0, //minimum value  
                        100, //maximum value  
                        1); //step  
        spquantitys = new JSpinner(values);
        spquantitys.setBounds(235, 460, 70, 30);

        SpinnerModel valueb
                = new SpinnerNumberModel(0, //initial value  
                        0, //minimum value  
                        100, //maximum value  
                        1); //step 
        spquantityb = new JSpinner(valueb);
        spquantityb.setBounds(235, 500, 70, 30);
        // txtquantity.setBounds(150,205+20,0,30);

        ButtonGroup bg = new ButtonGroup();
        log_out.setBounds(1200, 0, 100, 45);
        btnaddorder.setBounds(250, 590, 100, 40);
        btnclear.setBounds(120, 590, 100, 40);
        btncsearch.setBounds(350, 145, 30, 30);
        btncsearch.setToolTipText("Search Mobile Number From History");

        panel1.setBounds(0, 0, 1200, 45);
        panel12.setBounds(0, 45, 1400, 5);
        panel2.setBounds(500, 50, 5, 750);
        panel22.setBounds(795, 50, 5, 750);
        panel3.setBounds(800, 100, 600, 750);
        panel31.setBounds(800, 100, 600, 750);
        panel32.setBounds(800, 100, 600, 750);
        panel33.setBounds(800, 100, 600, 750);

        panel4.setBounds(800, 50, 600, 50);

        btnpend.setBounds(800, 50, 300, 50);
        btndone.setBounds(1100, 50, 300, 50);

        btnfbill.setBounds(600, 590, 100, 50);
        txtfbill.setBounds(620, 530, 60, 40);

        //tabpend.setBounds(900,500,200,300);
        jf1.add(panel12);
        jf1.add(panel22);
        panel1.add(lbbrand);
        jf1.add(panel2);
        jf1.add(panel3);
        jf1.add(log_out);
        //jf1.add(panel4);
        jf1.add(panel1);
        jf1.add(lbaddorder);
        jf1.add(lbcname);
        jf1.add(txtname);
        jf1.add(txtmob);
        jf1.add(lbcmobile);
        jf1.add(btncsearch);
        jf1.add(lbAddr);
        jf1.add(lbaddr1);
        jf1.add(txtaddr1);
        jf1.add(lbaddr2);
        jf1.add(txtaddr2);
        jf1.add(txtpin);
        jf1.add(lbaddr3);
        jf1.add(txtquantity);
        jf1.add(lbordertype);
        jf1.add(lbKg);
        jf1.add(txtKGw);
        jf1.add(txtKGi);
        jf1.add(lbquantity);
        jf1.add(spquantityw);
        jf1.add(spquantityi);
        jf1.add(spquantitys);
        jf1.add(spquantityb);
        jf1.add(rbwf);
        jf1.add(rbwi);
        jf1.add(rbs);
        jf1.add(rbb);
        jf1.add(rburg);
        jf1.add(btnaddorder);
        jf1.add(btnclear);

        jf1.add(btnpend);
        jf1.add(btndone);
        jf1.add(btnfbill);
        jf1.add(txtfbill);

        SimpleDigitalClock clock1 = new SimpleDigitalClock();
        clock1.setBounds(600, 100, 110, 50);
        clock1.setBackground(Color.LIGHT_GRAY);
        jf1.add(clock1);

//        JScrollPane sp1=new JScrollPane(tabpend);
//        tabpend.setBackground(Color.getHSBColor(50.0f, 500.0f,90.0f));
//        panel31.add(sp1);
//        panel3.add(panel31);
        print_pending_orders(con);
        print_pending_pickups(con);
        tabwdone.setRowHeight(tabwdone.getRowHeight() + 6);
        tabpend.setRowHeight(tabpend.getRowHeight() + 6);
        JScrollPane sp2 = new JScrollPane(tabwdone);
        sp2.setBackground(Color.red);
        panel32.add(sp2);
        panel3.add(panel32);

        lbaddorder.setSize(150, 70);
        lbaddorder.setFont(f2);
        lbbrand.setFont(f1);

        panel1.setBackground(Color.getHSBColor(16.0f, 194.0f, 245.0f));
        panel4.setBackground(Color.DARK_GRAY);
        panel12.setBackground(Color.DARK_GRAY);
        panel22.setBackground(Color.DARK_GRAY);
        panel2.setBackground(Color.DARK_GRAY);
        panel31.setBackground(Color.LIGHT_GRAY);
        panel32.setBackground(Color.LIGHT_GRAY);

        btnaddorder.setBackground(Color.darkGray);
        btnaddorder.setForeground(Color.white);
        btnaddorder.setBorder(new LineBorder(Color.BLACK));

        btnpend.setBackground(Color.darkGray);
        btnpend.setForeground(Color.white);
        btnpend.setBorder(new LineBorder(Color.LIGHT_GRAY));

        btndone.setBackground(Color.darkGray);
        btndone.setForeground(Color.white);
        btndone.setBorder(new LineBorder(Color.LIGHT_GRAY));

        btnfbill.setBackground(Color.darkGray);
        btnfbill.setForeground(Color.white);
        btnfbill.setBorder(new LineBorder(Color.LIGHT_GRAY));

        lbaddorder.setForeground(Color.red);

        jf1.getContentPane().setBackground(Color.LIGHT_GRAY);

        jf1.setVisible(true);

        btnaddorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_po p = new new_po();
                String getw = "";
                boolean bag = false;
                int kg;
                String nav1 = txtname.getText();
                String mo = txtmob.getText();
                String ad1 = txtaddr1.getText();
                String ad2 = txtaddr2.getText();
                String ad3 = txtpin.getText();
                //String type = "";
                String quant = "";
                int count = 0;
                int ord1 = 0, ord2 = 0, ord3 = 0, ord4 = 0;
                if (rbwf.isSelected()) {
                    type = "wf";
                    getw = txtKGw.getText();
                    quant = spquantityw.getValue() + "";

                    boolean ur = false;
                    int quan = Integer.parseInt(quant);
                    double f = Double.parseDouble(getw);//f=f+0.5f;
                    f = Math.ceil(f);
                    kg = (int) Math.round(f);
                    try {
                        ord1 = add_inDB(nav1, mo, ad1, ad2, ad3, type, kg, quan, ur, present_already, con);

                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
                if (rbwi.isSelected()) {
                    type = "wi";
                    getw = txtKGi.getText();
                    quant = spquantityi.getValue() + "";

                    boolean ur = false;
                    if (rburg.isSelected()) {
                        ur = true;
                    }
                    int quan = Integer.parseInt(quant);
                    double f = Double.parseDouble(getw);//f=f+0.5f;
                    f = Math.ceil(f);
                    kg = (int) Math.round(f);
                    try {
                        ord2 = add_inDB(nav1, mo, ad1, ad2, ad3, type, kg, quan, ur, present_already, con);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
                if (rbs.isSelected()) {
                    type = "S";
                    bag = true;
                    quant = spquantitys.getValue() + "";

                    boolean ur = false;
                    if (rburg.isSelected()) {
                        ur = true;
                    }
                    int quan = Integer.parseInt(quant);
                    //double f=Double.parseDouble(getw);//f=f+0.5f;
                    //f=Math.ceil(f);
                    //kg=(int)Math.round(f);
                    kg = 0;
                    try {
                        ord3 = add_inDB(nav1, mo, ad1, ad2, ad3, type, kg, quan, ur, present_already, con);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
                if (rbb.isSelected()) {
                    type = "B";
                    bag = true;
                    quant = spquantityb.getValue() + "";

                    boolean ur = false;
                    if (rburg.isSelected()) {
                        ur = true;
                    }
                    int quan = Integer.parseInt(quant);

                    kg = 0;
                    try {
                        ord4 = add_inDB(nav1, mo, ad1, ad2, ad3, type, kg, quan, ur, present_already, con);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }

                try {
                    insert_current(ord1, ord2, ord3, ord4, mo, con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }

                try {
                    p.New_did(mo, con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }

                try {
                    print_pending_orders(con);
                } catch (Exception e2) {
                    System.out.println(e2);
                }

                panel3.removeAll();
                panel3.repaint();
                panel3.revalidate();
                panel3.add(panel31);
                panel3.repaint();
                panel3.revalidate();

            }

        });

        btndone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel3.removeAll();
                panel3.repaint();
                panel3.revalidate();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Harshit@123");
                    print_pending_pickups(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                panel3.add(panel32);
                panel3.repaint();
                panel3.revalidate();

            }
        });

        btnpend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel3.removeAll();
                panel3.repaint();
                panel3.revalidate();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Harshit@123");
                    print_pending_orders(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                panel3.add(panel31);
                panel3.repaint();
                panel3.revalidate();

            }
        });

        log_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf1.dispose();
                Login l = new Login(wtoken, btoken, con);
                l.addlog();
            }
        });

        btnfbill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //fbills f=new fbills();
                //f.add();

            }
        });
        btnclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //fbills f=new fbills();
                //f.add();
                jf1.dispose();
                Laundarygui G1 = new Laundarygui(wtoken, btoken);
                try {
                    G1.add(con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });

        btnfbill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    final_bill fb = new final_bill();
                    int i = Integer.parseInt(txtfbill.getText());
                    fb.add(i, con);
//                    print_pending_orders(con);
                    print_pending_pickups(con);
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
        });

        btncsearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    search(con);
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });

        spquantityw.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                txtquantity.setText("" + ((JSpinner) e.getSource()).getValue());

            }
        });

    }

    void search(Connection con) throws Exception {

        String mob = txtmob.getText();

        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("select * from Customer where mobile=" + "'" + mob + "'");
        String p = "";
        while (rs1.next()) {
            //rintln(rs1.getString(2));
            if (mob.equals(rs1.getString(3))) { //res=true;
                txtname.setText(rs1.getString(2));
                txtaddr1.setText(rs1.getString(4));
                txtaddr2.setText(rs1.getString(5));
                txtpin.setText(rs1.getString(6));
                present_already = true;
            }
        }

    }

    ///database ci=onnection
    int add_inDB(String n, String mo, String a1, String a2, String a3, String ty, int w, int q, boolean ur, boolean pres, Connection con) throws Exception {
        name = n;
        ns++;
        mobile = mo;
        add1 = a1;
        add2 = a2;
        add3 = a3;
        typ = ty;
        int rate = 0;
        wkg = w;
        qua = q;
        urg = ur;//System.out.println(wkg+qua);
        if (ty.equals("S")) {
            tot = q * 200;
            rate = 200;
        } else if (ty.equals("B")) {
            tot = q * 250;
            rate = 250;
        } else {
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select * from Chart where rmin<=" + w + " and " + "rmax>=" + w + " and task='" + typ + "'");
            while (rs1.next()) {
                tot = w * rs1.getInt(1);
                rate = rs1.getInt(1);
            }

        }

        //System.out.println(tot);
        if (present_already == false && inser == false) {

            Statement st = con.createStatement();
            String sq = "insert into Customer(name,mobile,add1,add2,add3) " + "values('" + name + "','" + mobile + "','" + add1 + "','" + add2 + "','" + add3 + "')";
            inser = true;
            st.executeUpdate(sq);

        }

        Statement st3 = con.createStatement();
        //System.out.println("select * from Customer where mobile='" + mo + "'");
        ResultSet rs1 = st3.executeQuery("select * from Customer where mobile='" + mo + "'");
        //System.out.println("select * from Customer where mobile='" + mo + "'");
        while (rs1.next()) {
            cust_i = rs1.getInt(1);
        }

        Statement st5 = con.createStatement();
        if (ty == "wi" || ty == "wf") {
            String sq1 = "insert into Order1(type,noc,status,kg,bill,t_date,t_time,cust_id,urgent,rate,token) " + "values('" + ty + "'," + q + ",'pending'," + w + "," + tot + ",CURDATE(),CURRENT_TIME()," + cust_i + "," + ur + "," + rate + "," + wtoken + ")";
            st5.executeUpdate(sq1);
            //++wtoken;
            //st5.execute("update token set t=t+1");
        } else if (ty == "B" || ty == "S") {
            String sq1 = "insert into Order1(type,noc,status,kg,bill,t_date,t_time,cust_id,urgent,rate,token) " + "values('" + ty + "'," + q + ",'pending'," + w + "," + tot + ",CURDATE(),CURRENT_TIME()," + cust_i + "," + ur + "," + rate + "," + btoken + ")";
            st5.executeUpdate(sq1);
            //++btoken;
            //st5.execute("update token2 set t=t+1");
        }

        //System.out.println("insert into Order1(type,noc,status,kg,bill,t_date,t_time,cust_id,urgent) " + "values('" + ty + "'," + q + ",'pending'," + w + "," + tot + ",CURDATE(),CURR_TIME()," + cust_i + "," + ur + ")");
        Statement st = con.createStatement();
        int p = 0;
        ResultSet rs1C = st.executeQuery("select max(order_id) from Order1");
        while (rs1C.next()) {
            p = rs1C.getInt(1);
        }

        return p;

    }

    void insert_current(int ord1, int ord2, int ord3, int ord4, String mo, Connection con) throws Exception {
        int ci = 0;
        Statement st3 = con.createStatement();
        //System.out.println("select * from Customer where mobile='" + mo + "'");
        ResultSet rs1 = st3.executeQuery("select * from Customer where mobile='" + mo + "'");
        //System.out.println("select * from Customer where mobile='" + mo + "'");
        while (rs1.next()) {
            ci = rs1.getInt(1);
        }
        Statement st = con.createStatement();
        String sq = "insert into Current_orders(cust_id,ord1,ord2,ord3,ord4,o_date,o_time) values(" + ci + "," + ord1 + "," + ord2 + "," + ord3 + "," + ord4 + ",CURDATE(),CURTIME()" + ")";
        //System.out.println("lll" + sq);
        st.executeUpdate(sq);
    }

    /**
     * @param args the command line arguments
     */
    void print_pending_orders(Connection con) throws Exception {
        Statement st = con.createStatement();
        //System.out.println("HIII 1");
        int i = 0;
        ResultSet rss = st.executeQuery("select count(*) token from Order1 o INNER JOIN Current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2 OR o.order_id=c.ord3 OR o.order_id=c.ord4) AND status='pending'");
        while (rss.next()) {
            i = rss.getInt(1);
        }
        //System.out.println("HIII 3");
        ResultSet rsi = st.executeQuery("select order_id,t_date,type,kg,noc,t_time from Order1 o INNER JOIN Current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2 OR o.order_id=c.ord3 OR o.order_id=c.ord4) AND status='pending'");
        Object data1[][] = new Object[i][6];

        //System.out.println("HI");
        int x = 0;
        while (rsi.next()) {
            data1[x][0] = rsi.getInt(1);
            data1[x][1] = rsi.getDate(2);
            data1[x][2] = rsi.getString(3);
            data1[x][3] = rsi.getInt(4);
            data1[x][4] = rsi.getInt(5);
            data1[x][5] = rsi.getTime(6);
            x++;
            //System.out.println(i);
        }
        //System.out.println("HI");
        tabpend = new JTable(data1, columns);
        //System.out.println("HI");
        JScrollPane sp1 = new JScrollPane(tabpend);
        tabpend.setBackground(Color.getHSBColor(50.0f, 500.0f, 90.0f));
        tabpend.setRowHeight(tabpend.getRowHeight() + 6);
        panel3.removeAll();
        panel3.repaint();
        panel31.removeAll();
        panel31.repaint();
        panel31.add(sp1);
        panel3.add(panel31);
        panel3.revalidate();
    }

    void print_pending_pickups(Connection con) throws Exception {
        Statement st = con.createStatement();
        int i = 0;
        ResultSet rsi = st.executeQuery("Select count(*) token from Order1 o Inner Join Current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2 OR o.order_id=c.ord3 OR o.order_id=c.ord4) AND o.status='DONE'");
        rsi.next();
        i = rsi.getInt(1);

        ResultSet rss = st.executeQuery("select order_id,t_date,type,t_time,bill from Order1 o INNER JOIN Current_orders c where (o.order_id=c.ord1 OR o.order_id=c.ord2 OR o.order_id=c.ord3 OR o.order_id=c.ord4) AND status='DONE'");
        Object data1[][] = new Object[i][5];
        int x = 0;
        while (rss.next()) {
            data1[x][0] = rss.getInt(1);
            data1[x][1] = rss.getDate(2);
            data1[x][2] = rss.getString(3);
            data1[x][3] = rss.getTime(4);
            data1[x][4] = rss.getInt(5);
            x++;
        }
        tabwdone = new JTable(data1, columns2);
        tabwdone.setRowHeight(tabwdone.getRowHeight() + 6);
        JScrollPane sp1 = new JScrollPane(tabwdone);
        panel3.removeAll();
        panel3.repaint();
        panel32.removeAll();
        panel32.repaint();
        panel32.add(sp1);
        panel3.add(panel32);
        panel3.revalidate();

    }

    public static void main(String[] args) {
        // TODO code application logic here

        //Laundarygui G1=new Laundarygui();
        //G1.add();
        //System.out.println(G1.ns);
        try { // s="ert";
            //s1="asd";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project", "root", "rada");
            //Laundarygui G1 = new Laundarygui(token);
            //G1.add(con);
            Statement st3 = con.createStatement();
            ResultSet rs1 = st3.executeQuery("select CURTIME()");
            //System.out.println("select * from Customer where mobile='"+mo+"'");
            while (rs1.next()) {
                String g = rs1.getString(1);
                //System.out.println(g);
            }

        } catch (Exception e1) {
            System.out.println(e1);
        }

    }

}

class SimpleDigitalClock extends JPanel {

    String stringTime;
    int hour, minute, second;
    String ahour = "";
    String bminute = "";
    String csecond = "";

    public void setStringTime(String abc) {
        this.stringTime = abc;
    }

    public int Number(int a, int b) {
        return (a <= b) ? a : b;
    }

    SimpleDigitalClock() {
        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        t.start();
    }

    @Override
    public void paintComponent(Graphics v) {
        super.paintComponent(v);
        Calendar rite = Calendar.getInstance();
        hour = rite.get(Calendar.HOUR_OF_DAY);
        minute = rite.get(Calendar.MINUTE);
        second = rite.get(Calendar.SECOND);

        if (hour < 10) {
            this.ahour = "0";
        }
        if (hour >= 10) {
            this.ahour = "";
        }
        if (minute < 10) {
            this.bminute = "0";
        }
        if (minute >= 10) {
            this.bminute = "";
        }
        if (second < 10) {
            this.csecond = "0";
        }
        if (second >= 10) {
            this.csecond = "";
        }

        setStringTime(ahour + hour + ":" + bminute + minute + ":" + csecond + second);
        v.setColor(Color.RED);
        int length = Number(this.getWidth(), this.getHeight());
        Font Font1 = new Font("SansSerif", Font.BOLD, length / 3);
        v.setFont(Font1);
        v.drawString(stringTime, (int) length / 6, length / 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

}

class final_bill {

    JFrame Fr;
    JLabel namL, add1L, add2L, add3L, mobL, line1, task, quantity, f_bill, weight, line2, line3, line4, line5, bill1,
            bill2, bill3, bill4, task2, quantity2, weight2, task3, quantity3, weight3, task4, quantity4, weight4,
            bill_rec, tm, dt;
    JLabel TB, RM;
    int total = 0, Rem = 0;
    JTextField tf1;
    JButton check, cancel, confirm;
    String dat, tim;
    int rem_amt;
    int rem = 0;
    int pos;

    void add(int o_id, Connection con) throws Exception {
        String tim1 = "";
        String dat1 = "";
        Statement st3 = con.createStatement();
        ResultSet rs1229 = st3.executeQuery("select CURTIME()");
        while (rs1229.next()) {
            tim1 = rs1229.getString(1);
        }

        ResultSet rs1239 = st3.executeQuery("select CURDATE()");
// System.out.println("select * from Customer where mobile='"+mo+"'");
        while (rs1239.next()) {
            dat = rs1239.getString(1);
// System.out.println(g);
        }
        String nam = "";
        String ad1 = "";
        String ad2 = "";
        String ad3 = "";
        String mob = "";
        int Cust_i = 0;
        ResultSet rs = st3.executeQuery("select cust_id from Order1 where order_id=" + o_id);
        while (rs.next()) {
            Cust_i = rs.getInt(1);
        }
        ResultSet rs1 = st3.executeQuery("select * from Customer where cust_id=" + Cust_i);
        System.out.println("select * from Customer where cust_id=" + Cust_i);
        while (rs1.next()) {
            nam = rs1.getString(2);
            mob = rs1.getString(3);
            ad1 = rs1.getString(4);
            ad2 = rs1.getString(5);
            ad3 = rs1.getString(6);
        }

        System.out.println("select rem_bill Current_orders where ord1=" + o_id + " or ord2=" + o_id + " or ord3=" + o_id + " or ord4=" + o_id);
        ResultSet rs2 = st3.executeQuery("select * from Current_orders where ord1=" + o_id + " or ord2=" + o_id + " or ord3=" + o_id + " or ord4=" + o_id);
        while (rs2.next()) {

            rem = rs2.getInt(7);
            for (int i = 2; i < 6; i++) {
                if (rs2.getInt(i) == o_id) {
                    pos = i;
                }
            }
        }
        Fr = new JFrame();
        bill_rec = new JLabel("Bill Reciept");
        dt = new JLabel("Date : " + dat);
        tm = new JLabel("Time : " + tim1);
        namL = new JLabel("Name : " + nam);
        mobL = new JLabel("Contact :" + mob);
        add1L = new JLabel("Address : " + ad1);
        add2L = new JLabel("               " + ad2);
        add3L = new JLabel("               " + ad3);
        line1 = new JLabel("-----------------------------------------------------------------------------");
        RM = new JLabel("Due Amount : " + rem);
        check = new JButton("Enter");
        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");
        tf1 = new JTextField();
// ----------------------------------------------------------------------------------------
        Fr.setSize(400, 350);
        Fr.setLocationRelativeTo(add1L);
        Fr.setLayout(null);
        Fr.setVisible(true);
// -------------------------------------------------------------------------------------
        bill_rec.setBounds(100, 5, 300, 30);
        dt.setBounds(5, 40, 150, 20);
        tm.setBounds(250, 40, 150, 20);
        namL.setBounds(50, 70, 300, 20);
        mobL.setBounds(50, 90, 300, 20);
        add1L.setBounds(50, 110, 300, 20);
        add2L.setBounds(50, 130, 300, 20);
        add3L.setBounds(50, 150, 300, 20);
        line1.setBounds(0, 170, 400, 10);
        RM.setBounds(50, 180, 300, 20);

        tf1.setBounds(100, 210, 100, 30);
        check.setBounds(200, 210, 100, 30);
        cancel.setBounds(10, 260, 100, 30);
        confirm.setBounds(290, 260, 100, 30);
        Font font;
// TB.setBounds(50,510,400,30);font=new
// Font("Monospaced",Font.BOLD,20);TB.setFont(font);bill_rec.setFont(font);
// RM.setBounds(50,540,400,30);RM.setFont(font);

// tf1.setBounds(100,590,100,30);
// System.out.println(nam+mob+add1);
// ---------------------------------------------------------------------------------
        Fr.add(bill_rec);
        Fr.add(dt);
        Fr.add(tm);
        Fr.add(namL);
        Fr.add(mobL);
        Fr.add(add1L);
        Fr.add(add2L);
        Fr.add(add3L);
        Fr.add(line1);
        Fr.add(RM);
        Fr.add(check);
        Fr.add(tf1);
        Fr.add(cancel);
        Fr.add(confirm);

        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rem_amt1 = tf1.getText();
                rem_amt = rem - Integer.parseInt(rem_amt1);
                try {
                    set_rem(rem_amt, o_id, con);
                } catch (Exception e1) {

                }

            }
        });
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    del_ord(o_id, con);
                    del_row(con);
                    Fr.dispose();
                } catch (Exception e1) {

                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fr.dispose();

            }
        });

    }

    void set_rem(int remain, int o_id, Connection con) throws Exception {

        int p = pos - 1;
        String or = "ord" + p;
        Statement st = con.createStatement();
        String sq = "update Current_orders set rem_bill=" + remain + " where " + or + "=" + o_id;
        st.executeUpdate(sq);
    }

    void del_ord(int o_id, Connection con) throws Exception {
        int p = pos - 1;
        String or = "ord" + p;
        Statement st = con.createStatement();
        String sq = "update Current_orders set " + or + "=0 where " + or + "=" + o_id;
        st.executeUpdate(sq);

    }

    void del_row(Connection con) throws Exception {

        Statement st = con.createStatement();
        String sq = "delete from Current_orders where ord1=0 and ord2=0 and ord3=0 and ord4=0";
        st.executeUpdate(sq);
    }
}
