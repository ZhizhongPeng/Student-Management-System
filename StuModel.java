import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.*;
import java.util.*;

import javax.swing.table.*;
/**
 * This is a student table model
 * @author pengzhizhong
 *
 */
public class StuModel extends AbstractTableModel {
	Vector rowData, columnNames;
	//定义数据库
	Connection ct= null;
	PreparedStatement ps= null;	
	ResultSet rs = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url= "jdbc:sqlserver://localhost:1433;databaseName=student";
    String user="sa";
	String password="Pzz,.123456";
	public boolean updStu(String sql,String[] paras) {
		boolean b = true;
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, password);
			ps=ct.prepareStatement(sql);
			
			for(int i=0;i<paras.length;i++) {
				ps.setString(i+1, paras[i]);
			}
			
			if(ps.executeUpdate()!=1) {
				b=false;
			}
		} catch(Exception e) {
			b=false;
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return b;
	}
	public void init(String sql) {
		if(sql.equals("")){
			sql="select * from student.student_info.stu";
		}
		columnNames = new Vector();
		//设置列名
		columnNames.add("StuId");
		columnNames.add("StuName");
		columnNames.add("StuSex");
		columnNames.add("StuAge");
		columnNames.add("StuHometown");
		columnNames.add("StuDepName");
		
		rowData = new Vector();
		try {
			//load driver
			Class.forName(driver);
			ct=DriverManager.getConnection(url,user,password);
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				rowData.add(hang);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public StuModel(String sql) {
		this.init(sql);
	}
	
    public StuModel() {
		this.init("");
	}
    
  
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	@Override
	public int getRowCount() {		
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}

}
