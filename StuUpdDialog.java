import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
/**
 * update student dialog
 * @author pengzhizhong
 *
 */
public class StuUpdDialog extends JDialog implements ActionListener{
		JLabel jl1, jl2, jl3, jl4, jl5, jl6;
		JButton jb1,jb2;
		JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
		JPanel jp1, jp2, jp3;
		
		//owner is father's window
		//title window name
		//model model window or non model window
		public StuUpdDialog(Frame owner, String title, boolean modal, StuModel sm, int rowNums)
		{
			super(owner, title, modal); 
			jl1=new JLabel("StuId");
			jl2=new JLabel("StuName");
			jl3=new JLabel("StuSex");
			jl4=new JLabel("StuAge");
			jl5=new JLabel("StuHometown");
			jl6=new JLabel("StuDepName");
			
			//
			jtf1=new JTextField();
			jtf1.setText((String)sm.getValueAt(rowNums, 0).toString());
			jtf1.setEditable(false);
			jtf2=new JTextField();
			jtf2.setText((String)sm.getValueAt(rowNums, 1).toString());
			jtf3=new JTextField();
			jtf3.setText((String)sm.getValueAt(rowNums, 2).toString());
			jtf4=new JTextField();
			jtf4.setText((String)sm.getValueAt(rowNums, 3).toString());
			jtf5=new JTextField();
			jtf5.setText((String)sm.getValueAt(rowNums, 4).toString());
			jtf6=new JTextField();
			jtf6.setText((String)sm.getValueAt(rowNums, 5).toString());
			
			jb1=new JButton("Update");
			jb1.addActionListener(this);
			jb2=new JButton("Cancel");
			jb2.addActionListener(this);
			
			jp1=new JPanel();
			jp2=new JPanel();
			jp3=new JPanel();
			

			jp1.setLayout(new GridLayout(6,1));
			jp2.setLayout(new GridLayout(6,1));
			

			jp1.add(jl1);
			jp1.add(jl2);
			jp1.add(jl3);
			jp1.add(jl4);
			jp1.add(jl5);
			jp1.add(jl6);
			
			jp2.add(jtf1);
			jp2.add(jtf2);
			jp2.add(jtf3);
			jp2.add(jtf4);
			jp2.add(jtf5);
			jp2.add(jtf6);
			
			jp3.add(jb1);
			jp3.add(jb2);
			
			this.add(jp1, BorderLayout.WEST);
			this.add(jp2, BorderLayout.CENTER);
			this.add(jp3, BorderLayout.SOUTH);
			

			this.setSize(300,200);
			//this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.setVisible(true);
		}
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jb1)
			{
				String str = "update student.student_info.stu set StuName=?, " + "StuSex=?, StuAge=?, StuHometown=?, StuDepName=? where StuId=?";
			    String[] paras= {jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf1.getText()};
			
			StuModel temp = new StuModel();
			if(!temp.updStu(str, paras))
			{
				JOptionPane.showMessageDialog(this, "Update failed");
			} else {
				JOptionPane.showMessageDialog(this, "Update successfully");
			}
			this.dispose();
			}
			else if(e.getSource()==jb2) {
				this.dispose();
			} 
		}


}
