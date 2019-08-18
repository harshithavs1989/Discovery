package com.discovery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;



public class ReadPropertiesFile 
{
	private static Logger log = Logger.getLogger("Logger");
	private static String fileName;


	/**
	 * Set the absolute path of the file 
	 * @param fileName
	 */
	public static void setFilePath(String fileName)
	{	
		ReadPropertiesFile.fileName = fileName;
	}
	
	/**
	 * @param fileName
	 * @param key
	 * @return You can pass file name where file is under Resources folder or
	 *         the entire path of the file.
	 */
	public static String getValue(String key) 
	{
		log.info("Getting the value of the key " + key);
		String keyValue = null;
		FileInputStream propertiesFile = null;


		File propertiesFileToRead = new File(fileName);

		try {
			propertiesFile = new FileInputStream(propertiesFileToRead);
			Properties prop = new Properties();
			prop.load(propertiesFile);
			keyValue = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				propertiesFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("The value of the key is " + keyValue);

		return keyValue;
	}


	/**
	 * Get all the values of the key for a given environment. Store the values in the properties file as env_key
	 * @param env
	 * @return
	 */
	public static Map<String, String> getAllKeyValue(String env)
	{
		log.info("Getting all the key value pairs");
		FileInputStream propertiesFile = null;

		File propertiesFileToRead = new File(fileName);
		Map<String,String> keyValuePair = new HashMap<String, String>();


		try {
			propertiesFile = new FileInputStream(propertiesFileToRead);
			Properties prop = new Properties();
			prop.load(propertiesFile);
			Set<Object> keySet = prop.keySet();

			Iterator<Object> it = keySet.iterator();
			while(it.hasNext())
			{
				String key = it.next().toString();
				String envInKey = key.split("_")[0];
				if(envInKey.equalsIgnoreCase(env))
				{
					keyValuePair.put(key.split("_")[1].trim(), prop.getProperty(key).trim());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				propertiesFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		log.info("The key value pair of the file " + fileName + " is " + keyValuePair.toString());
		return keyValuePair;
	}

	/**
	 * Get all the values from the properties file
	 * @param env
	 * @return
	 */
	public static Map<String, String> getAllKeyValue()
	{
		log.info("Getting all the key value pairs");
		FileInputStream propertiesFile = null;

		File propertiesFileToRead = new File(fileName);
		Map<String,String> keyValuePair = new HashMap<String, String>();


		try {
			propertiesFile = new FileInputStream(propertiesFileToRead);
			Properties prop = new Properties();
			prop.load(propertiesFile);
			Set<Object> keySet = prop.keySet();

			Iterator<Object> it = keySet.iterator();
			while(it.hasNext())
			{
				String key = it.next().toString();
				keyValuePair.put(key, prop.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				propertiesFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		log.info("The key value pair of the file " + fileName + " is " + keyValuePair.toString());
		return keyValuePair;
	}
}
