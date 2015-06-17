package org.crow.test.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
	public static void main(String[] args) {
		// 测试获取properties文件
//		System.out.println(new T().getProperties1());
		System.out.println(new GetProperties().getProperties2());
//		System.out.println(new T().getProperties1());
//		System.out.println(new T().getProperties1());
	}
	
	private String getProperties2(){
		File file = new File("");
		File propFile = new File(file.getAbsoluteFile().toString(), "src/path.properties");
		String info = null;
		Properties props = new Properties();
		InputStream is;
		try {
			is = new FileInputStream(propFile);
			props.load(is);
			info = props.getProperty("UPLOAD_PATH");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	private String getProperties1(){
		String info = null;
		Properties props = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/path.properties");
		try {
			props.load(is);
			info = props.getProperty("UPLOAD_PATH");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
}
