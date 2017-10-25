package com.venky.mysql.jmeter.jmeter_mysqlconnector;
/**
 * 
 */
import java.sql.*;
import groovy.sql.Sql;

/**
 * @author vp1938
 *
 */
public class MySQLConnector {

	private static volatile MySQLConnector thisSQLConnector;
	private String ipAddress;
	private String port;
	private String dbName;
	private String userName;
	private String password;
	private Sql sql;

	private MySQLConnector() {
		// Singleton Implementation
	}

	private MySQLConnector(String ipAddress, String port, String dbName, String userName, String password) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			//System.out.println("Initializing sql connection instance");
			sql = Sql.newInstance("jdbc:mysql://" + this.ipAddress + ":" + this.port + "/" + this.dbName, this.userName,this.password,"com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		catch( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static MySQLConnector getMySQLConnector(String ipAddress, String port, String dbName, String username,
			String password) {
		if (null == thisSQLConnector) {
			synchronized (MySQLConnector.class) {
				if(thisSQLConnector == null){
				thisSQLConnector = new MySQLConnector(ipAddress, port, dbName, username, password);
				}
			}
		}
			return thisSQLConnector;
		
		}
	
	
	public boolean insertToDB(String tableName,String[] labels, String[] intLabels, String[] inputValues, int[] inputIntValues){
		StringBuilder inputLabelString = new StringBuilder(), inputValueString = new StringBuilder();
		for(int i = 0;i<labels.length;i++){
			inputLabelString.append(labels[i]);
			inputValueString.append("'"+escapeValueSql(inputValues[i])+"'");
				inputLabelString.append(",");
				inputValueString.append(",");
			
		}
		for(int i = 0;i< intLabels.length;i++){
			inputLabelString.append(intLabels[i]);
			inputValueString.append(inputIntValues[i]);
			if(i!=intLabels.length-1){
				inputLabelString.append(",");
				inputValueString.append(",");
			}
		}
		StringBuilder sqlstr = new StringBuilder();
		sqlstr.append("INSERT INTO "+tableName+" (")
		.append(inputLabelString+")")
		.append(" VALUES (")
		.append(inputValueString)
		 .append(")");
		try {
			sql.execute(sqlstr.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
		
	}
	

	public static String escapeValueSql(String val){
		if(null != val){
		val = val.replaceAll("\t", "\\t")
                     .replaceAll("\00", "\\0")
                     .replaceAll("'", "\\'");
                  //   .replaceAll("\\"","\\"");
		}else{val = "null";}
          return val;
	}

}
