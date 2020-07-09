package com.brunosidad.ws.mongo.po;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ejecucion")
public class Ejecucion {
	
	@Id
	String _id;
	Integer id_tipo_prueba;
	String fecha_ejecucion;
	Integer id_canal;
	Integer id_estado;
	Integer id_exception_type;
    String datos;
    Integer id_framework;
    String robot;
    String tester;
    Integer id_browser;
    Integer id_so;
    String host;
	String fecha_inicio;
	String fecha_fin;
	Integer id_qc_project;
	Integer releaseId;
	Integer cycleId;
	Integer testId;
    Integer suiteId;
    Integer id_producto;
    Integer id_funcionalidad;
	Integer id_plataforma;
	Integer id_ambiente;
	Integer tiempo_respuesta;
	
	
	public Integer getId_tipo_prueba() {
		return id_tipo_prueba;
	}
	public void setId_tipo_prueba(Integer id_tipo_prueba) {
		this.id_tipo_prueba = id_tipo_prueba;
	}
	public String getFecha_ejecucion() {
		return fecha_ejecucion;
	}
	public void setFecha_ejecucion(String fecha_ejecucion) {
		this.fecha_ejecucion = fecha_ejecucion;
	}
	public Integer getId_canal() {
		return id_canal;
	}
	public void setId_canal(Integer id_canal) {
		this.id_canal = id_canal;
	}
	public Integer getId_estado() {
		return id_estado;
	}
	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}
	public Integer getId_exception_type() {
		return id_exception_type;
	}
	public void setId_exception_type(Integer id_exception_type) {
		this.id_exception_type = id_exception_type;
	}
	public String getDatos() {
		return datos;
	}
	public void setDatos(String datos) {
		this.datos = datos;
	}
	public Integer getId_framework() {
		return id_framework;
	}
	public void setId_framework(Integer id_framework) {
		this.id_framework = id_framework;
	}
	public String getRobot() {
		return robot;
	}
	public void setRobot(String robot) {
		this.robot = robot;
	}
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	public Integer getId_browser() {
		return id_browser;
	}
	public void setId_browser(Integer id_browser) {
		this.id_browser = id_browser;
	}
	public Integer getId_so() {
		return id_so;
	}
	public void setId_so(Integer id_so) {
		this.id_so = id_so;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	public Integer getId_qc_project() {
		return id_qc_project;
	}
	public void setId_qc_project(Integer id_qc_project) {
		this.id_qc_project = id_qc_project;
	}
	public Integer getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}
	public Integer getCycleId() {
		return cycleId;
	}
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public Integer getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(Integer suiteId) {
		this.suiteId = suiteId;
	}
	public Integer getId_funcionalidad() {
		return id_funcionalidad;
	}
	public void setId_funcionalidad(Integer id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}
	public Integer getId_plataforma() {
		return id_plataforma;
	}
	public void setId_plataforma(Integer id_plataforma) {
		this.id_plataforma = id_plataforma;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public Integer getId_ambiente() {
		return id_ambiente;
	}
	public void setId_ambiente(Integer id_ambiente) {
		this.id_ambiente = id_ambiente;
	}
	public Integer getTiempo_respuesta() {
		return tiempo_respuesta;
	}
	public void setTiempo_respuesta(Integer tiempo_respuesta) {
		this.tiempo_respuesta = tiempo_respuesta;
	}
	

	
}
