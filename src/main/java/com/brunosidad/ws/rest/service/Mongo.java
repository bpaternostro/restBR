package com.brunosidad.ws.rest.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.brunosidad.ws.mongo.po.Ejecucion;
import com.brunosidad.ws.mongo.po.Validacion;


@Path("/mongo")
public class Mongo {

	@POST
	@Path("/postEjecucion")
	@Consumes({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	
	public String postEjecucion(Ejecucion datos) {
		synchronized(Mongo.class){
			 	 
			String msg="";
			ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
			// For Annotation
			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
						
			try {
				mongoOperation.save(datos);
		    }catch(Exception e) {
		    	msg="problema en el metodo postDatosFijos de Mongo: "+e.getMessage();
		    	System.out.println(msg);
		    }
			
			if(msg=="") 
				msg="{status:'OK',message:'el registro fue incorporado correctamente.'}";
			
			return msg;
			
			
		}
		
	}	
	
	@POST
	@Path("/postValidacion")
	@Consumes({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	
	public String postValidacion(Validacion val) {
		synchronized(Mongo.class){
			 	 
			String msg="";
			ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
			// For Annotation
			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
						
			try {
				mongoOperation.save(val);
		    }catch(Exception e) {
		    	msg="problema en el metodo postDatosFijos de Mongo: "+e.getMessage();
		    	System.out.println(msg);
		    }
			
			if(msg=="") 
				msg="{status:'OK',message:'el registro fue incorporado correctamente.'}";
			
			return msg;
			
			
		}
		
	}
}
