package com.brunosidad.ws.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "validaciones")
public class Validacion {
	
	@Id
	String _id;
	String robot;
	String resultado_obtenido;
	Integer id_campo;
	Integer id_estado;
	
	public String getRobot() {
		return robot;
	}
	public void setRobot(String robot) {
		this.robot = robot;
	}
	public String getResultado_obtenido() {
		return resultado_obtenido;
	}
	public void setResultado_obtenido(String resultado_obtenido) {
		this.resultado_obtenido = resultado_obtenido;
	}
	public Integer getId_campo() {
		return id_campo;
	}
	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}
	public Integer getId_estado() {
		return id_estado;
	}
	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}
	
    
	
	
	

	
}
