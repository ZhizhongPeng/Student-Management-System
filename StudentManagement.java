import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class StudentManagement extends JFrame implements ActionListener{
	//use rowData to store row data
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	//define sql variable
		Connection ct= null;
		PreparedStatement ps= null;	
		ResultSet rs = null;
	
    public static void main(String args[]) {
    	StudentManagement tst4 = new StudentManagement();
    }
    
    public StudentManagement() {
    	jp1 = new JPanel();
    	jtf = new JTextField(10);
    	jb1 = new JButton("Search");
    	jb1.addActionListener(this);
    	jl1 = new JLabel("Please enter student name");
    	
    	jp1.add(jl1);
    	jp1.add(jtf);
    	jp1.add(jb1);
    	
    	jp2 = new JPanel();
    	jb2 = new JButton("Add");
    	jb2.addActionListener(this);
    	jb3 = new JButton("Update");
    	jb3.addActionListener(this);
    	jb4 = new JButton("Delete");
    	jb4.addActionListener(this);
    	
    	jp2.add(jb2);
    	jp2.add(jb3);
    	jp2.add(jb4);
    	
		sm = new StuModel();

		
		jt= new JTable(sm);
		
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1) {
			String name = this.jtf.getText().trim();
			
			String sql = "select * from student.student_info.stu where StuName='"+name+"'";
			
			sm = new StuModel(sql);
			
			jt.setModel(sm);
		} else if(e.getSource()==jb2) {
			StuAddDialog sa = new StuAddDialog(this,"Add student", true);
			//create a new model, refresh
			sm = new StuModel();
			jt.setModel(sm);
		} else if(e.getSource()==jb3) {
			int rowNum = this.jt.getSelectedRow();
			if(rowNum==-1) {
				JOptionPane.showMessageDialog(this, "Please select one line");
				return;
			}
			//get stuId
			String StuId = (String)sm.getValueAt(rowNum, 0);
			StuUpdDialog su = new StuUpdDialog(this,"Update student", true, sm, rowNum);
			//create a new model, refresh
			sm = new StuModel();
			jt.setModel(sm);
		} else if(e.getSource()==jb4) {
			int rowNum = this.jt.getSelectedRow();
			if(rowNum==-1) {
				JOptionPane.showMessageDialog(this, "Please select one line");
				return;
			}
			//get stuId
			String StuId = (String)sm.getValueAt(rowNum, 0);
			
			String sql="delete from student.student_info.stu where StuId=?";
			String[] paras= {StuId};
			StuModel temp = new StuModel();
			temp.updStu(sql, paras);
			
			//create a new model, refresh
			sm = new StuModel();
			jt.setModel(sm);
		}
	}
}
