package com.hand.Exam3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class App {

    public static void main( String[] args ){
    	App app =new App();
    	try {
    		
			URL url=new URL("http://hq.sinajs.cn/list=sz300170");
			InputStream is=url.openConnection().getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"gbk");
			BufferedReader br=new BufferedReader(isr);
			String str=null;
			StringBuilder builder=new StringBuilder();
			while((str= br.readLine())!=null){				
				builder.append(str);
			}
			String s[]=builder.toString().split(",");
			app.createXML(s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
public void createXML(String str[]){
		
		DocumentBuilder builder;
		File file =new File("myXml.xml");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			Document document=builder.newDocument();
			
			Element stock=document.createElement("stock");
			
			Element name=document.createElement("name");
			name.setTextContent(str[0]);
			Element open=document.createElement("open");
			open.setTextContent(str[1]);
			
			Element close=document.createElement("close");
			close.setTextContent(str[2]);
			Element current=document.createElement("current");
			current.setTextContent(str[3]);
			Element high=document.createElement("high");
			high.setTextContent(str[4]);
			Element low=document.createElement("low");
			low.setTextContent(str[5]);
			
			stock.appendChild(name);
			stock.appendChild(open);
			stock.appendChild(close);
			stock.appendChild(current);
			stock.appendChild(high);
			stock.appendChild(low);
						
			document.appendChild(stock);
			
			TransformerFactory transformerFactory =TransformerFactory.newInstance();
			Transformer transformer=transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult("myXml.xml"));
			/*StringWriter writer=new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			System.out.println(writer);*/
		
		} catch (ParserConfigurationException e) {			
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}
}
