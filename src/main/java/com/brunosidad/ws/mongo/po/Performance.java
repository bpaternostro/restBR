package com.brunosidad.ws.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "performance")
public class Performance {

	//to do: incorporar los datos del server y los de Jmeter
	@Id
	String _Id;
	String _class;
	Integer producto;
	String script;
	Integer hilos;
	String host;
	String tiempo_prom_ejecucion;
	String fecha_inicio;
	String fecha_fin;
	String release;
	Boolean flag_corrida_exitosa;
	String conf_concurrencia;
	String datos_server;
	
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public Integer getHilos() {
		return hilos;
	}
	public void setHilos(Integer hilos) {
		this.hilos = hilos;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTiempo_prom_ejecucion() {
		return tiempo_prom_ejecucion;
	}
	public void setTiempo_prom_ejecucion(String tiempo_prom_ejecucion) {
		this.tiempo_prom_ejecucion = tiempo_prom_ejecucion;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public Boolean getFlag_corrida_exitosa() {
		return flag_corrida_exitosa;
	}
	public void setFlag_corrida_exitosa(Boolean flag_corrida_exitosa) {
		this.flag_corrida_exitosa = flag_corrida_exitosa;
	}
	public String getConf_concurrencia() {
		return conf_concurrencia;
	}
	public void setConf_concurrencia(String conf_concurrencia) {
		this.conf_concurrencia = conf_concurrencia;
	}
	public String getDatos_server() {
		return datos_server;
	}
	public void setDatos_server(String datos_server) {
		this.datos_server = datos_server;
	}
	
	
	
}
