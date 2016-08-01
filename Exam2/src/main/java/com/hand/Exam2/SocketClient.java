package com.hand.Exam2;

import java.io.BufferedReader;
import java.io.File;
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
			FileWriter writter = new FileWriter(file, true);
			Socket socket=new Socket("10.211.119.221",12345);			
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s=null;
			while((s=br.readLine())!=null){
				writter.write(s);
			}
			
			br.close();
			writter.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
