package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllerLogin.ControllerLogin;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLogin extends JDialog {

    private JPanel contentPanel = new JPanel();
    private ControllerLogin controller;
    private JTextField textField_user;
    private JTextField textField_password;
    private String profession;
    private JPasswordField passwordField;

    public ViewLogin(Frame owner, String profession) {
        super(owner, "Login", true);
        this.profession = profession;
        controller = new ControllerLogin(this);
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblLogin = new JLabel("Username");
        lblLogin.setFont(new Font("Verdana Pro", Font.PLAIN, 12));
        lblLogin.setBounds(87, 68, 86, 16);
        contentPanel.add(lblLogin);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Verdana Pro", Font.PLAIN, 12));
        lblPassword.setBounds(87, 120, 68, 13);
        contentPanel.add(lblPassword);

        textField_user = new JTextField();
        textField_user.setBounds(168, 68, 138, 19);
        contentPanel.add(textField_user);
        textField_user.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(165, 118, 141, 19);
        contentPanel.add(passwordField);
        passwordField.setColumns(10);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton LoginButton = new JButton("Login");
        if(profession.contains("medecin")) {
        	  LoginButton.addActionListener(controller.new loginMedecin());
        }else if(profession.contains("technicien")) {
        	LoginButton.addActionListener(controller.new loginTechnicien());
        }else if(profession.contains("admin")) {
        	LoginButton.addActionListener(controller.new loginAdmin());
        }
        buttonPane.add(LoginButton);
        getRootPane().setDefaultButton(LoginButton);

        JButton cancelClear = new JButton("Clear");
        cancelClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField_user.setText("");
        		passwordField.setText("");
        	}
        });
        	
        buttonPane.add(cancelClear);
    }

    public String getUser() {
        return textField_user.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
}

