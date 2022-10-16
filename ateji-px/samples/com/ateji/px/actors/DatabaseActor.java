/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.actors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ateji.px.comprehension.util.MultiMap;

import apx.lang.IChan;

/**
 * DatabaseActor manages a database
 *
 */
public class DatabaseActor extends Actor {

	/**
	 * The populateDataBase action takes an url and a MultiMap and writes them to the database.
	 */
	@SuppressWarnings("unused")
	private void act_populateDataBase(String url, MultiMap<String, Integer, Integer> multimap){
		for(String word : multimap.keySet()) {
			int count = multimap.get(word);

			try {
				Statement statement = c.createStatement();
				String query = "INSERT INTO index VALUES('"+url+"', '"+word+"', "+count+")";				
				statement.executeUpdate(query);	
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}							
	}

	final Connection c;

	public DatabaseActor(IChan<Message> mailbox) {
		super(mailbox);
		// load database driver
		try {
			// see: http://hsqldb.org/
			// http://hsqldb.org/web/hsqlLicense.html
			Class.forName("org.hsqldb.jdbcDriver" );
		} catch (Exception e) {
			System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
			e.printStackTrace();
			c = null;
			return;
		}

		// Local server Databases
		String url = "./";
		String dbName = "ActorsSampleDataBase";
		try {
			c = DriverManager.getConnection("jdbc:hsqldb:"+url+dbName, "sa", "");
		} catch (SQLException e) {			
			throw new Error(e);
		}

		Statement statement;
		try {
			statement = c.createStatement();
			statement.executeUpdate("CREATE TABLE index ("+
					"url varchar(50), "+
					"word varchar(20), "+
			"index int)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The displayDataBase action displays the database.
	 */
	@SuppressWarnings("unused")
	private void act_displayDataBase(int indexLowBound){
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = c.createStatement();
			resultSet = statement.executeQuery("SELECT *  FROM index WHERE index > "+indexLowBound);
			String[] cols = {"url", "word", "index"}; 
			while (resultSet.next()) {
				for (String col : cols) {
					Object value = resultSet.getObject(col);					
					System.out.print(value+" ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * the shutdown action shutdowns the data base
	 */
	@SuppressWarnings("unused")
	public void act_shutdown() {
		try {

			Statement statement = c.createStatement();
			statement.executeUpdate("DROP TABLE index");
			statement.close();

			statement = c.createStatement();
			statement.execute("shutdown");
			statement.close();
			c.close();    // if there are no other open connection		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
