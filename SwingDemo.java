import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDBCSwingDemo extends JFrame {

private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
Connection con;
Statement stmt;
ResultSet rs;

int id;
String name,city;
/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
JDBCSwingDemo frame = new JDBCSwingDemo();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

/**
* Create the frame.
*/
public JDBCSwingDemo() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 706, 508);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

setContentPane(contentPane);
contentPane.setLayout(null);

try
{
Class.forName("com.mysql.jdbc.Driver");  
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","scott","tiger");  
stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
rs=stmt.executeQuery("select * from emp");
}
catch(Exception e)
{
e.printStackTrace();
}


JLabel lblNewLabel = new JLabel("ID");
lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
lblNewLabel.setBounds(58, 49, 60, 28);
contentPane.add(lblNewLabel);

JLabel lblName = new JLabel("NAME");
lblName.setFont(new Font("Times New Roman", Font.BOLD, 15));
lblName.setBounds(58, 106, 60, 28);
contentPane.add(lblName);

JLabel lblCity = new JLabel("CITY");
lblCity.setFont(new Font("Times New Roman", Font.BOLD, 15));
lblCity.setBounds(58, 164, 60, 28);
contentPane.add(lblCity);

textField = new JTextField();
textField.setBounds(154, 51, 173, 28);
contentPane.add(textField);
textField.setColumns(10);

textField_1 = new JTextField();
textField_1.setColumns(10);
textField_1.setBounds(154, 112, 173, 28);
contentPane.add(textField_1);

textField_2 = new JTextField();
textField_2.setColumns(10);
textField_2.setBounds(154, 170, 173, 28);
contentPane.add(textField_2);

JButton btnNewButton = new JButton("FIRST");
btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
try
{
if(rs.first())
{
displayrecord();
}
}
catch(Exception e1)
{
e1.printStackTrace();
}
}
});
btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
btnNewButton.setBounds(53, 262, 108, 43);
contentPane.add(btnNewButton);

JButton btnLast = new JButton("LAST");
btnLast.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
try
{
if(rs.last())
{
displayrecord();
}
}
catch(Exception e1)
{
e1.printStackTrace();
}
}
});
btnLast.setFont(new Font("Times New Roman", Font.BOLD, 15));
btnLast.setBounds(194, 262, 108, 43);
contentPane.add(btnLast);

JButton btnNext = new JButton("NEXT");
btnNext.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
try
{
if(rs.next())
{
displayrecord();
}
}
catch(Exception e1)
{
e1.printStackTrace();
}
}
});
btnNext.setFont(new Font("Times New Roman", Font.BOLD, 15));
btnNext.setBounds(334, 262, 108, 43);
contentPane.add(btnNext);

JButton btnPrevious = new JButton("PREVIOUS");
btnPrevious.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
try
{
if(rs.previous())
{
displayrecord();
}
}
catch(Exception e1)
{
e1.printStackTrace();
}
}
});
btnPrevious.setFont(new Font("Times New Roman", Font.BOLD, 15));
btnPrevious.setBounds(482, 262, 130, 43);
contentPane.add(btnPrevious);

JButton btnClear = new JButton("CLEAR");
btnClear.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
textField.setText("");
textField_1.setText("");
textField_2.setText("");
}
});
btnClear.setFont(new Font("Times New Roman", Font.BOLD, 15));
btnClear.setBounds(53, 336, 130, 43);
contentPane.add(btnClear);

JButton btnInsert = new JButton("INSERT");
btnInsert.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
try
{
Class.forName("com.mysql.jdbc.Driver");  
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","password");  
stmt=con.createStatement();
id=Integer.parseInt(textField.getText());
name=textField_1.getText();
city=textField_2.getText();
int r=stmt.executeUpdate("insert into emp values("+id+",'"+name+"','"+city+"')");
if(r>0)
{
System.out.println("Record inserted successfully");
}
}
catch(Exception e2)
{
e2.printStackTrace();
}
}
});
btnInsert.setFont(new Font("Times New Roman", Font.BOLD, 15));
btnInsert.setBounds(218, 336, 130, 43);
contentPane.add(btnInsert);
}

public void displayrecord() throws SQLException
{
textField.setText(String.valueOf(rs.getInt(1)));
textField_1.setText(rs.getString(2));
textField_2.setText(rs.getString(3));
}
}
