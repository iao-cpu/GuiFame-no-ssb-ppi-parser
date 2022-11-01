package no.ssb.ppi.parser;

import java.io.File;
import java.util.Vector;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

	public class DomParser {

		DocumentBuilderFactory docF = null;
		DocumentBuilder docB = null;
		Document xml_doc = null;
		private String fileLocation;
		public String s1 = null;
		public String s2 = null;
		private String userName;
		public String getU = null;
		public String prod_db;
		public Vector vc, vc1;
		public String[] ss, mydb;
		public String user_all = "data";
		public String ref_db;
		
		public DomParser() {		
			userName = null;
			userName = System.getProperty("user.name");
		    //fileLocation = "famedataservice-linux.xml";
			InputStream is = null; 
			
			try {		
				docF = DocumentBuilderFactory.newInstance();
				docB = docF.newDocumentBuilder();
				//doc = docB.parse("C:\\JavaFame\\FameWebAccess\\famedataservice-linux.xml");
				is = this.getClass().getResourceAsStream("/no/ssb/ppi/parser/famedataservice-linux.xml");
				xml_doc = docB.parse(is);				
			} catch (Exception e) {	
				e.printStackTrace();
			}
				parse();
		}
	
		public void parse(){		
			vc = new Vector();
			vc1 = new Vector();
			//NodeList nodeList = doc.getElementsByTagName("myaction");
			NodeList nodeList = xml_doc.getElementsByTagName("pool");
			int size = nodeList.getLength();
			//System.out.println("Node Name : Pool and Total Node in XML : "+size);

			for(int i = 0 ; i < size ; i++){
				//System.out.println("---------------Node ("+i+")--------------------");
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element) node;
					//System.out.println("pool name:"+e.getAttribute("name"));
					s1 = e.getAttribute("name");
					
					NodeList serverNodeList = e.getElementsByTagName("server");
					int serverNodeListSize = serverNodeList.getLength();
					for(int k = 0; k < serverNodeListSize; k++){
						Node serverNode = serverNodeList.item(k);
						if(serverNode.getNodeType() == Node.ELEMENT_NODE){
							Element serverE = (Element) serverNode;
							//System.out.println("server :" + serverE.getAttribute("address"));
						}			  
					}

					NodeList connNodeList = e.getElementsByTagName("connection");
					int connNodeListSize = connNodeList.getLength();
					for(int h = 0; h < connNodeListSize; h++){			
						Node connNode = connNodeList.item(h);
						if(connNode.getNodeType() == Node.ELEMENT_NODE){
							Element connE = (Element) connNode;			
							s2 = connE.getAttribute("user");
							//System.out.println("User :" + connE.getAttribute("user"));		
						}
					}

					NodeList resNodeList = e.getElementsByTagName("resources");
					int resNodeListSize = resNodeList.getLength();
					for(int m = 0; m < resNodeListSize; m++){
						Node resNode = resNodeList.item(m);
						if(resNode.getNodeType() == Node.ELEMENT_NODE){
							Element resE = (Element) resNode;
							//System.out.println(resE.getChildNodes());
						}
					}

					NodeList singleNodeList = e.getElementsByTagName("single");
					int singleNodeListSize = singleNodeList.getLength();
					for(int n = 0; n < singleNodeListSize; n++){
						Node singleNode = singleNodeList.item(n);
						if(singleNode.getNodeType() == Node.ELEMENT_NODE){
							Element singleE = (Element) singleNode;		
							if(s1.equals(user_all)){
								ref_db = singleE.getAttribute("name");
								vc1.add(ref_db);
							}
							if(s2.equals(userName)){
								//System.out.println(singleE.getAttribute("name"));
								prod_db = singleE.getAttribute("name");
								vc.add(prod_db);	
							}
						}
					}
				}
			}
			
			ss = (String[])vc.toArray(new String[vc.size()]); 
			mydb =(String[]) vc1.toArray(new String[vc1.size()]);		
		}
	}


