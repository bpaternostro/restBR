package com.brunosidad.ws.rest.service;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brunosidad.ws.rest.fixer.Constants;
import com.brunosidad.ws.rest.infraestructure.Requester;


@Path("/fixer")
public class Fixer {
	    
	@POST
	@Path("/exe")
	@Consumes({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	public String executeFixer(Requester r) throws FileNotFoundException, IOException, Exception {
		synchronized(Fixer.class){
			Socket miCliente;
	        DataInputStream miRespuesta = null;
	        DataOutputStream miPedido = null;
	        String solicitud = r.getSolicitud();
	        //String solicitud = "getPbf";
	        System.out.println("recibio una solicitud "+solicitud);    
	        String ans=null;
	        try
	        {
	            //CONNECT TO SERVER
	            miCliente = new Socket();
	            miCliente.connect(new InetSocketAddress(Constants.IP_TANDEM,Constants.PORT));
	            
	            miRespuesta = new DataInputStream(miCliente.getInputStream());
	            miPedido= new DataOutputStream(miCliente.getOutputStream());
	
	            //SEND REQUEST
	            miPedido.writeUTF("usuarioautoldap" + "_" + solicitud);
	
	            //GET ANSWER
	            ans=miRespuesta.readUTF();
	            System.out.println(ans);
	            
	            //CLOSE CONNECTIONS
	            miRespuesta.close();
	            miPedido.close();
	            miCliente.close();
	            
	        }
	        catch(NumberFormatException ex) 
	        {
	            JOptionPane.showMessageDialog(null,"Que mandaste??? Avisa!","Error (NumberFormatException)",JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        } 
	        catch (IOException ex) 
	        {
	            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error (NumberFormatException)",JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	        
	        return ans;
		}
	}
}
