package com.hand.Exam2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class SocketServer {

	public static void main(String[] args) {
		File file =new File("exam.pdf");
		SocketServer server=new SocketServer();
		try {
			ServerSocket serverSocket = new ServerSocket(12345,10,InetAddress.getByName("10.211.119.221"));
			while(true){
				Socket socket=serverSocket.accept();
				JOptionPane.showMessageDialog(null,"有客户端链接到12345端口");
				server.send(socket, file);	
			}			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void send(Socket socket,File file){		
		BufferedReader br=null;
		String str =null;
		PrintWriter writer;
		try {
			br= new BufferedReader(new FileReader(file));
			writer = new PrintWriter(socket.getOutputStream());
			while((str=br.readLine())!=null){
				writer.println(str);
				System.out.println(str);
			}			
			writer.flush();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
