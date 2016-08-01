package com.hand.Exam1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class App {

    public static void main( String[] args ){
    	File f1=new File("myFile.pdf");
    	File f2=new File("Exam.pdf");
    	if(!f1.exists()){
    		try {
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	App app=new App();
    	app.copyFileBYLine(f1,f2);
       
    }
    public void copyFileBYLine(File f1,File f2){//按行复制文件f1<==f2
		FileWriter writter =null;
		BufferedReader br=null;
		String str=null;
		
		try {
			writter = new FileWriter(f1, true);
			br= new BufferedReader(new FileReader(f2));
			while((str=br.readLine())!=null){
				writter.write(str+"\r\n");
				System.out.println(str);
			}
			
			writter.close();
			br.close();
			
		} catch (IOException e) {			
			System.out.println(e.toString());
		}
	}
}
