package com.cqut.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	private static PropertyManager manager = null;
	private Properties properties = null;
	private static String propsName = "db.properties";
	
	private String resourceURI;

	public static String getProperty(String name) throws Exception {
		if (manager == null) {
			manager = new PropertyManager(propsName);
		}
		return manager.getProp(name);
	}

	private PropertyManager(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	protected String getProp(String name) throws Exception {
		if (properties == null) {
			loadProps();
		}
		String property = properties.getProperty(name);
		if (property == null) {
			return null;
		} else {
			return property.trim();
		}
	}

	/**
	 * Loads dbsource properties from the disk.
	 */
	private void loadProps() throws Exception {
		properties = new Properties();
		InputStream in = null;
		try {
			 /*System.out.println(System.getProperty("user.dir"));
			 System.out.println(PropertyManager.class.getResource("/").getPath()+propsName);*/
			//in = new BufferedInputStream (new FileInputStream(PropertyManager.class.getResource("/").getPath()+propsName));
			in = getClass().getResourceAsStream("/"+propsName);
			//in = new FileInputStream(propsName);
			properties.load(in);
		} catch (FileNotFoundException ex) {

			throw new Exception("文件访问出错", ex);
		} catch (IOException ex) {

			throw new Exception("IO异常", ex);
		} catch (Exception e) {

			throw new Exception(e);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

}
