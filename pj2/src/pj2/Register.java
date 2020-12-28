/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pj2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import sun.security.util.Password;
/**
 *
 * @author dell
 */
public class Register implements ActionListener{
    JLabel label1;
    JLabel label2;
    JButton registerButton;  //Register button
    JTextField userName;     //input username 
    JPasswordField userPassword;    //password of username
    JFrame register_frame;    //register frame
    HashMap<String, String> regisMap; //hash to store user inform
    
    //read data from user.txt
    public void read_user_data(String fileName){
        try{
            regisMap = new HashMap<String, String>();
            
            File myfile = new File(fileName);
            Scanner myReader = new Scanner(myfile);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] temp = data.split("`", 2);
                this.regisMap.put(temp[0], temp[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }
    
    public void createUI(){
        label1 = new JLabel("Username:");
        label2 = new JLabel("Password:");
        
        registerButton = new JButton("Register");
        userName = new JTextField();
        userPassword = new JPasswordField();
        
        register_frame = new JFrame("Dang ky");
        register_frame.setSize(500, 300);
  
        register_frame.setLayout(null);
        
        //set visual
        label1.setSize(100, 30);
        label1.setLocation(142, 33);
        register_frame.add(label1);
        
        userName.setSize(200, 30);
        userName.setLocation(142, 60);
        register_frame.add(userName);
        
        label2.setSize(100, 30);
        label2.setLocation(142, 95);
        register_frame.add(label2);
        
        userPassword.setSize(200, 30);
        userPassword.setLocation(142, 120);
        register_frame.add(userPassword);
        
        registerButton.setSize(100, 30);       
        registerButton.setLocation(192, 170);
        register_frame.add(registerButton);

        
        //action listener
//        userName.setActionCommand("usn");
//        userName.addActionListener(this);
        
//        userPassword.setActionCommand("pw");
//        userPassword.addActionListener(this);
        
        registerButton.setActionCommand("rgt");
        registerButton.addActionListener(this);
        
        //click exit to stop application
        register_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //register_frame.setLayout(new FlowLayout());
        register_frame.setVisible(true);
    }
    
    //constructor
    public Register(){
        this.read_user_data("user.txt");
        this.createUI();
    }
    
    public void exit_program(){
        System.exit(0);
    }
    
    public static void main(String[] args) {
        Register register = new Register();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("rgt".equals(e.getActionCommand())){
            String usr_n = userName.getText();
            String pw = userPassword.getText();
             if(usr_n.equals("") || pw.equals("")){
                JOptionPane.showMessageDialog(register_frame,"Please type your username and your password!");
            }
            if (regisMap.get(usr_n) != null)
                JOptionPane.showMessageDialog(register_frame,"This username is existed, please typing different username");
            
            else{
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter("user.txt", true));     
                    output.write(usr_n+ "`" + pw + "\n");
                    regisMap.put(usr_n, pw);
                    output.close();
                    JOptionPane.showMessageDialog(register_frame,"Sign-up success");                 
                    new Login().setVisible(true);
                    this.register_frame.dispose();
                    } catch (Exception exc) {
                        System.out.println("Error happen!");
                    }
            }
        }
    }
}
