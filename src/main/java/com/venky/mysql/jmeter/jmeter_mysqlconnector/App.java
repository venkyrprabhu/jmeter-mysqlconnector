package com.venky.mysql.jmeter.jmeter_mysqlconnector;

/**
 * 
 */


/**
 * @author vp1938
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] labels = {"label","platform","status","callSign",
				"requestData","responsedata"};
		String[] intlabels = {"duration","ccid","responseCode","errorCode"};
		String[] inputValues = {"Auth","iphone","success","CNN",
				"http://asdfjaslfkjsfkljaslkjlsjcsdc.s/sjdcl/asdclsdc","asflkjasfkjclasjflksdjfldkcskcjsklc"};
		int[] inputIntValues = {150,1231,200,504};
		

		MySQLConnector mySQLConnector = MySQLConnector.getMySQLConnector("127.0.0.1", "3306", "mysql","mysql_user", "mysql_password");
		mySQLConnector.insertToDB("cdvr_samples", labels,intlabels,inputValues,inputIntValues);
		MySQLConnector mySQLConnector1 = MySQLConnector.getMySQLConnector("127.0.0.1", "3306", "mysql","mysql_user", "mysql_password");
		mySQLConnector1.insertToDB("cdvr_samples", labels,intlabels,inputValues,inputIntValues);
	}

}
