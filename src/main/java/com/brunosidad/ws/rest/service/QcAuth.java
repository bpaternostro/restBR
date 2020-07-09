package com.brunosidad.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


import org.w3c.dom.NodeList;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.brunosidad.ws.rest.infraestructure.Authenticate;
import com.brunosidad.ws.rest.infraestructure.AutomEmail;
import com.brunosidad.ws.rest.infraestructure.AutomEmailBodyData;
import com.brunosidad.ws.rest.infraestructure.Constants;
import com.brunosidad.ws.rest.infraestructure.EntityCreator;
import com.brunosidad.ws.rest.infraestructure.Orun;
import com.brunosidad.ws.rest.infraestructure.RestConnector;
import com.brunosidad.ws.rest.vo.Canal;
import com.brunosidad.ws.rest.vo.Resultado;
import com.brunosidad.ws.rest.vo.Suite;

import net.minidev.json.JSONObject;

import com.brunosidad.ws.rest.infraestructure.Response;

@Path("/qcauth")
public class QcAuth {
	
	private EntityCreator entity;
	private Authenticate auth= new Authenticate();
		
	@POST
	@Path("/postrun")
	@Consumes({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	public String execLogin(Orun orun) throws Exception {
		synchronized(QcAuth.class){	
			
			
			
			String domain=orun.getDomain();
			String project=orun.getProject();
			String estado=orun.getEstado();
	        String testId=orun.getTestId();
	        String suiteId=orun.getSuiteId();
	        String actual=orun.getActual();  
	        String datos=orun.getDatos();
	        String framework=orun.getFramework();
	        String tipoCorrida=orun.getTipoCorrida();
	        String id=orun.getId();
	        String tester=orun.getTester();
	        String browser=orun.getBrowser();
	        String product_version=orun.getProduct_version();
	        String so=orun.getSo();
	        String host=orun.getHost();
	        String dispositivo=orun.getDispositivo();
	        Boolean debug=orun.getDebug();
	        
	        if(debug) 
	        	id="debug-"+orun.getId();
	        
	        RestConnector con =
	                RestConnector.getInstance().init(
	                        new HashMap<String, String>(),
	                        Constants.HOST,
	                        domain,
	                        project);
	        
	        Boolean loginResult = auth.login(Constants.APIURL, Constants.CLIENTID, Constants.SECRET);
	        
	        if(!loginResult) {
	        	return "{'resulset':false,"
	        	+ "'detalle':'Existieron inconvenientes en el ingreso de la corrida.'}";
	        }
	         
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("Content-Type", "application/xml; charset=UTF-8");
	        requestHeaders.put("Accept", "application/xml; charset=UTF-8");
	
	        //Build the location of the requirements collection, and build the XML for an entity of type requirement.
	        String instanceUrl = con.buildEntityCollectionUrl("test-instance");
	        String instanceToPostXml =	("<Entity Type=\"test-instance\">"
	        		+ "<Fields>"
	        			+ "<Field Name=\"cycle-id\"><Value>"+suiteId+"</Value></Field>"
	        			+ "<Field Name=\"status\"><Value>"+estado+"</Value></Field>"
						+ "<Field Name=\"actual-tester\"><Value>"+tester+"</Value></Field>"
						+ "<Field Name=\"test-id\"><Value>"+testId+"</Value></Field>"
						+ "<Field Name=\"user-01\"><Value>"+id+"</Value></Field>"
						+ "<Field Name=\"subtype-id\"><Value>hp.qc.test-instance.MANUAL</Value></Field>"
						+ "<Field Name=\"owner\"><Value>usuarioautoldap</Value></Field>"
					+ "</Fields></Entity>");
	        
	        entity = new EntityCreator();
	        //Create the entity on the server-side, keep its URL
	        String newlyCreatedEntityUrlInstance = entity.createEntity(instanceUrl, instanceToPostXml);
	        //System.out.println("response for created entity: " + newlyCreatedEntityUrlInstance);
	        
	        String instanceId=newlyCreatedEntityUrlInstance.substring(newlyCreatedEntityUrlInstance.lastIndexOf("/")+1, newlyCreatedEntityUrlInstance.length());
	        //Build the location of the requirements collection, and build the XML for an entity of type requirement.
	        String runUrl = con.buildEntityCollectionUrl("run");
	        String runToPostXml=null;
	        
	        if(project.contains("BancaElectronica"))
	        	runToPostXml = ("<Entity Type=\"run\">"
					+ "<Fields>"
						+ "<Field Name=\"name\"><Value>Ejecucion Automatica</Value></Field>"
						+ "<Field Name=\"test-id\"><Value>"+testId+"</Value></Field>"
						+ "<Field Name=\"testcycl-id\"><Value>"+instanceId+"</Value></Field>"
						+ "<Field Name=\"owner\"><Value>"+tester+"</Value></Field>"
						+ "<Field Name=\"subtype-id\"><Value>hp.qc.run.MANUAL</Value></Field>"
						+ "<Field Name=\"cycle-id\"><Value>"+suiteId+"</Value></Field>"
						+ "<Field Name=\"status\"><Value>"+estado+"</Value></Field>"
						+ "<Field Name=\"user-01\"><Value>"+tipoCorrida+"</Value></Field>"
						+ "<Field Name=\"user-02\"><Value>"+framework+"</Value></Field>"
						+ "<Field Name=\"user-06\"><Value>"+browser+"</Value></Field>"
						+ "<Field Name=\"os-name\"><Value>"+so+"</Value></Field>"
						+ "<Field Name=\"user-07\"><Value>"+product_version+"</Value></Field>"
						+ "<Field Name=\"user-08\"><Value>"+dispositivo+"</Value></Field>"
						+ "<Field Name=\"user-03\"><Value>"+id+"</Value></Field>"
						+ "<Field Name=\"host\"><Value>"+host+"</Value></Field>"
						+ "<Field Name=\"user-09\"><Value>"+host+"</Value></Field>"
					+ "</Fields></Entity>");
	        else if(project.contains("Billetera")) 
	        	runToPostXml = ("<Entity Type=\"run\">"
	    				+ "<Fields>"
	    					+ "<Field Name=\"name\"><Value>Ejecucion Automatica</Value></Field>"
	    					+ "<Field Name=\"test-id\"><Value>"+testId+"</Value></Field>"
	    					+ "<Field Name=\"testcycl-id\"><Value>"+instanceId+"</Value></Field>"
	    					+ "<Field Name=\"owner\"><Value>"+tester+"</Value></Field>"
	    					+ "<Field Name=\"subtype-id\"><Value>hp.qc.run.MANUAL</Value></Field>"
	    					+ "<Field Name=\"cycle-id\"><Value>"+suiteId+"</Value></Field>"
	    					+ "<Field Name=\"status\"><Value>"+estado+"</Value></Field>"
	    					+ "<Field Name=\"user-01\"><Value>"+tipoCorrida+"</Value></Field>"
	    					+ "<Field Name=\"user-02\"><Value>"+framework+"</Value></Field>"
	    					+ "<Field Name=\"user-05\"><Value>"+browser+"</Value></Field>"
	    					+ "<Field Name=\"os-name\"><Value>"+so+"</Value></Field>"
	    					+ "<Field Name=\"user-06\"><Value>"+product_version+"</Value></Field>"
	    					+ "<Field Name=\"user-07\"><Value>"+dispositivo+"</Value></Field>"
	    					+ "<Field Name=\"user-03\"><Value>"+id+"</Value></Field>"
	    					+ "<Field Name=\"host\"><Value>"+host+"</Value></Field>"
	    					+ "<Field Name=\"user-08\"><Value>"+host+"</Value></Field>"
	    				+ "</Fields></Entity>");	
	        else
	        	//para medios de pago
	        	runToPostXml = ("<Entity Type=\"run\">"
	    				+ "<Fields>"
	    					+ "<Field Name=\"name\"><Value>Ejecucion Automatica</Value></Field>"
	    					+ "<Field Name=\"test-id\"><Value>"+testId+"</Value></Field>"
	    					+ "<Field Name=\"testcycl-id\"><Value>"+instanceId+"</Value></Field>"
	    					+ "<Field Name=\"owner\"><Value>"+tester+"</Value></Field>"
	    					+ "<Field Name=\"subtype-id\"><Value>hp.qc.run.MANUAL</Value></Field>"
	    					+ "<Field Name=\"cycle-id\"><Value>"+suiteId+"</Value></Field>"
	    					+ "<Field Name=\"status\"><Value>"+estado+"</Value></Field>"
	    					+ "<Field Name=\"user-01\"><Value>"+tipoCorrida+"</Value></Field>"
	    					+ "<Field Name=\"user-02\"><Value>"+framework+"</Value></Field>"
	    					+ "<Field Name=\"user-06\"><Value>"+browser+"</Value></Field>"
	    					+ "<Field Name=\"os-name\"><Value>"+so+"</Value></Field>"
	    					+ "<Field Name=\"user-07\"><Value>"+product_version+"</Value></Field>"
	    					+ "<Field Name=\"user-08\"><Value>"+dispositivo+"</Value></Field>"
	    					+ "<Field Name=\"user-03\"><Value>"+id+"</Value></Field>"
	    					+ "<Field Name=\"host\"><Value>"+host+"</Value></Field>"
	    					+ "<Field Name=\"user-09\"><Value>"+host+"</Value></Field>"
	    				+ "</Fields></Entity>");
	        
	        //Create the entity on the server-side, keep its URL
	        String newlyCreatedEntityUrlRun = entity.createEntity(runUrl, runToPostXml);
	        //System.out.println("response for created entity: " + newlyCreatedEntityUrlRun);
	        
	        //Build the location of the requirements collection, and build the XML for an entity of type requirement.
	        String rundId=newlyCreatedEntityUrlRun.substring(newlyCreatedEntityUrlRun.lastIndexOf("/")).replace("/", "");
	        String runStepUrl = con.buildEntityCollectionUrl("runs/"+rundId+"/run-step");
	        
	        //Create an XML that, when posted, modifies the entity.
	        String urlToGetSteps=Constants.URL+"/rest/domains/"+domain+"/projects/"+project+"/runs/"+rundId+"/run-steps";
	        Response oStep=con.httpGet(urlToGetSteps, null, requestHeaders);
	        String stepResponse=oStep.toString();
	               
	        org.w3c.dom.Document doc = convertStringToDocument(stepResponse);
	        NodeList elementsByTagName = doc.getElementsByTagName("Entity");
	                
	        String updatedRNStatusXml = EntityCreator.generateUpdateXml("run-step","status", estado);
	        String updatedRNExecutionModeXml = EntityCreator.generateUpdateXml("run-step","user-01", "Automatico");
	        String updatedRNIdXml = EntityCreator.generateUpdateXml("run-step","user-03", id);
	        String updatedRNActualXml = EntityCreator.generateUpdateXml("run-step","actual", actual);
	        String updatedRNDescriptionXml = EntityCreator.generateUpdateXml("run-step","description", datos);
	        
	        for(int h=0;h<elementsByTagName.getLength();h++){
	            Node item = elementsByTagName.item(h);     
	            //get the node FIELDs
	            NodeList childNodes = item.getChildNodes().item(1).getChildNodes();
	            for(int n=0;n<childNodes.getLength();n++){
	            	Node item1 = childNodes.item(n);
	                NamedNodeMap attributes = item1.getAttributes();
	                Node namedItem = attributes.getNamedItem("Name");
	                String nodeValue = namedItem.getTextContent();
	                if("id".equals(nodeValue)){
	                	Node item2 = item1.getFirstChild();
	                    String pos = item2.getTextContent();
	                    entity.update(runStepUrl+"/"+pos, updatedRNStatusXml).toString();
	                    entity.update(runStepUrl+"/"+pos, updatedRNExecutionModeXml).toString();
	                    entity.update(runStepUrl+"/"+pos, updatedRNActualXml).toString();
	                    entity.update(runStepUrl+"/"+pos, updatedRNDescriptionXml).toString();
	                    entity.update(runStepUrl+"/"+pos, updatedRNIdXml).toString();
	                }
	            }       
	        }
	                                               
	        String updatedInstanceXml = EntityCreator.generateUpdateXml("test-instance","status", estado);
	        //Update the entity
	        String putInstance = entity.update(newlyCreatedEntityUrlInstance, updatedInstanceXml).toString();
	        //System.out.println("entity updated: " + putInstance.trim());               
	
	        //And now we logout
	        auth.logout();
	        //And now we can see that we are indeed logged out - because isAuthenticated once again returns a URL, and not null.
	        if (auth.isAuthenticated() != null) {
	            System.out.println("user and password successfully logged out, cookies: "
	                               + con.getCookieString());
	        }
	        else {
	            System.out.println("logout failed.");
	        }
	        
	        return "{'resulset':true,"
	        		+ "'detalle':'La corrida "+rundId+" fue incorporada correctamente.'}";
		}
	}
	
	
	@POST
	@Path("/getSuiteName")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String getSuiteName(Orun orun) throws Exception {
		synchronized(QcAuth.class){	
			String domain=orun.getDomain();
			String project=orun.getProject();
			String suiteId=orun.getSuiteId();
		
			RestConnector con =
	                RestConnector.getInstance().init(
	                        new HashMap<String, String>(),
	                        Constants.HOST,
	                        domain,
	                        project);
	        
	        Boolean loginResult = auth.login(Constants.APIURL, Constants.CLIENTID, Constants.SECRET);
	        
	        if(!loginResult) {
	        	return "{'resulset':false,"
	        	+ "'detalle':'Existieron inconvenientes en el ingreso de la corrida.'}";
	        }
	         
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("Content-Type", "application/xml;charset=UTF-8");
	        requestHeaders.put("Accept", "application/xml;charset=UTF-8");
			
			String suiteUrl = con.buildEntityCollectionUrl("test-set")+"/"+suiteId;
			Response oSuite=con.httpGet(suiteUrl, null, requestHeaders);
	        String suiteResponse=oSuite.toString();
	        
	        //System.out.println("esta es la url para traer la suite:"+suiteUrl);
	        //String xmlSuite=EntityCreator.getXmlResponse(suiteUrl, "");
	        org.w3c.dom.Document doc1 = convertStringToDocument(suiteResponse);
	        NodeList suiteName = doc1.getElementsByTagName("Field");
	        String suite=null;
	        for(int i=0;i<suiteName.getLength();i++) {
	        	Node nod=suiteName.item(i);
	        	//System.out.println(nod.getAttributes().getNamedItem("Name").getTextContent());
	        	if(nod.getAttributes().getNamedItem("Name").getTextContent().contains("name")) {
	        		Node item2 = nod.getFirstChild();
	                suite = item2.getTextContent();
	        	}     	
	        	
	        }
	        
			return "{'resulset':true,"
	        		+ "'SuiteName':'"+suite+"'}";
		}
	
	}
	
	@POST
	@Path("/getTestCaseName")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String getTestCaseName(Orun orun) throws Exception {
		synchronized(QcAuth.class){	
			String domain=orun.getDomain();
			String project=orun.getProject();
			String testId=orun.getTestId();
		
			RestConnector con =
	                RestConnector.getInstance().init(
	                        new HashMap<String, String>(),
	                        Constants.HOST,
	                        domain,
	                        project);
	        
	        Boolean loginResult = auth.login(Constants.APIURL, Constants.CLIENTID, Constants.SECRET);
	        
	        if(!loginResult) {
	        	return "{'resulset':false,"
	        	+ "'detalle':'Existieron inconvenientes en el ingreso de la corrida.'}";
	        }
	         
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("Content-Type", "application/xml;charset=UTF-8");
	        requestHeaders.put("Accept", "application/xml;charset=UTF-8");
			
			String testUrl = con.buildEntityCollectionUrl("test")+"/"+testId;
			System.out.println(testUrl);
			Response oTest=con.httpGet(testUrl, null, requestHeaders);
	        String testCaseResponse=oTest.toString();
	        System.out.println(testCaseResponse);
	        //System.out.println("esta es la url para traer la suite:"+suiteUrl);
	        //String xmlSuite=EntityCreator.getXmlResponse(suiteUrl, "");
	        org.w3c.dom.Document doc1 = convertStringToDocument(testCaseResponse);
	        NodeList testName = doc1.getElementsByTagName("Field");
	        String test=null;
	        for(int i=0;i<testName.getLength();i++) {
	        	Node nod=testName.item(i);
	        	if(nod.getAttributes().getNamedItem("Name").getTextContent().toString().contains("name")) {
	        		System.out.println(nod.getAttributes().getNamedItem("Name").getTextContent().toString());
	        		Node item2 = nod.getFirstChild();
	        		if(test==null) 
	        			test = item2.getTextContent();	
	        		
	        	}     	
	        	
	        }
	        
			return "{'resulset':true,"
	        		+ "'TestCaseName':'"+test+"'}";
		}
	
	}
	
	
	@POST
	@Path("/sendEmail")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String sendEmail(AutomEmail msg) {
		synchronized(QcAuth.class){	
		      String from = "tduser@10.16.10.135";
		      String to = msg.getTo();
		      String htmlBody= msg.getBodyMsg();
		      String subject = msg.getSubject();
	
		      // Assuming you are sending email from localhost	      
		      Properties props = new Properties();
			  //props.put("mail.smtp.auth", "true");
			  props.put("mail.smtp.starttls.enable", "true");
			  props.put("mail.smtp.host", "10.16.10.135");
			  props.put("mail.smtp.port", "25");
	
			  Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {					
			  });
	
			  try {
	
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
					message.setSubject(subject);
					message.setContent(htmlBody, "text/html; charset=utf-8");
	
					Transport.send(message);
	
					System.out.println("Done");
	
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
		      
		      return "{'resulset':true}";
		}
	   }
	
	   @POST
	   @Path("/getemaildatabody")
	   @Consumes({MediaType.APPLICATION_JSON})
	   @Produces({MediaType.APPLICATION_JSON})
	   public String getemaildata(AutomEmailBodyData q) throws SQLException, ClassNotFoundException {
		   synchronized(QcAuth.class){	
			   String db_url;
			   if(q.getDb().contains("Banca")) 
				   db_url = "jdbc:sqlserver://WDBS-PRPM070:1433;DatabaseName=prisma_bancaelectronica_db";
			   else			   
				   db_url = "jdbc:sqlserver://WDBS-PRPM070:1433;DatabaseName=prisma_billetera_db";
			   	   	   
			   //String db_driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
			   String db_username = "user_QC";
			   String db_password = "user_QC_2019";
			   q.setQuery();
			   
			   //JSONObject globalJson = new JSONObject();
			   JSONObject canales =  new JSONObject();
			   
			   try {
				   
				   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				   Connection conn = DriverManager.getConnection(db_url, db_username, db_password);
		           
				   Statement stmtObj = conn.createStatement();
				   ResultSet resObj = stmtObj.executeQuery(q.getQuery());
				   
				   Canal canal=new Canal();
				   //canal.setCanal("aux");
				   String canalNameAux="aux";
				   String actualCanalName=null;
				   
				   
				   while (resObj.next()) {
				
					   actualCanalName=resObj.getString("Canal");
					   
					   if(!canalNameAux.contains(actualCanalName)) { 
						   canal=new Canal();
						   //canal.setCanal(actualCanalName);
					   }
					   
					   Suite suite=new Suite();
					   suite.setSuite(resObj.getString("Suite"));
					   Resultado res=new Resultado();
					   
					   res.setCantFailed(resObj.getString("cantFailed"));
					   res.setCantOtrosEstados(resObj.getString("cantOtrosEstados"));
					   res.setCantPassed(resObj.getString("cantPassed"));
					   res.setCantTotal(resObj.getString("cantTotal"));
					   res.setFailed(resObj.getString("Failed"));
					   res.setPassed(resObj.getString("Passed"));
					   res.setFramework(resObj.getString("Framework"));
					   
					   suite.setResultado(res);				   
					   canal.addSuite(suite);				 
					   canales.put(resObj.getString("Canal"),canal);
					   canalNameAux=resObj.getString("Canal");
				   }
			
			   }catch (Exception e){
				   e.printStackTrace();
			   }
			   
			   String message=canales.toString();
			   return message;
		   }
	   }
	
	   private static org.w3c.dom.Document convertStringToDocument(String xmlStr) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try  
	        {  
	            builder = factory.newDocumentBuilder();  
	            org.w3c.dom.Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	}
}
