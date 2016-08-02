package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String args[]){
		File file=new File("clientFile.pdf");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			
			
			Socket socket=new Socket("127.0.0.1",12345);			
			BufferedInputStream bis=new BufferedInputStream(socket.getInputStream());
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
			int s=0;
			while((s=bis.read())!=0){
				bos.write(s);
			}
			
			bos.close();
			bis.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
