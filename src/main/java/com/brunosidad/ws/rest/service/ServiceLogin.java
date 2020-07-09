package com.brunosidad.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brunosidad.ws.rest.vo.VOusuario;

@Path("/brunosidad")
public class ServiceLogin {
	
	@POST
	@Path("/validarUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public VOusuario validaUsuario(VOusuario vo) {
		vo.setUserValido(false);
		if(vo.getUsuario().equals("Cata") && vo.getPassword().equals("Renata")) {
			vo.setUserValido(true);
		}
		return vo;
	}
}
