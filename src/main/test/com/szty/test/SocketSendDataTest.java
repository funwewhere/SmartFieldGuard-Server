package com.szty.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class SocketSendDataTest {
	
	public static void main(String[] args) {
		BufferedReader br = null;
		PrintStream pw = null;
		BufferedReader systemin = null;
		try {
			Socket client = new Socket("192.168.240.1", 5757);
//			client.setSoTimeout(10000);
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintStream(client.getOutputStream());
			systemin = new BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				System.out.println("输入：");
				String readLine = systemin.readLine();
				System.out.println(555 + readLine);
				if(readLine == null || readLine.isEmpty() || readLine.equals("666")){
					pw.println("666");
					break;
				} else if ("long".equals(readLine)){
				} else {
					pw.println("{\"type\":\"connect_wifi\", \"ssid\":\"llidm123\",\"psw\":\"13579abc\", \"encryption\":\"psk2\"}");
				}
				String sereverLine = br.readLine();
				System.out.println(sereverLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
