package com.brunosidad.ws.rest.vo;

public class Resultado {
	
	String framework;
	String passed;
	String failed;
	String cantPassed;
	String cantFailed;
	String cantOtrosEstados;
	String cantTotal;
	
	public String getFramework() {
		return framework;
	}
	public void setFramework(String framework) {
		this.framework = framework;
	}
	public String getPassed() {
		return passed;
	}
	public void setPassed(String passed) {
		this.passed = passed;
	}
	public String getFailed() {
		return failed;
	}
	public void setFailed(String failed) {
		this.failed = failed;
	}
	public String getCantPassed() {
		return cantPassed;
	}
	public void setCantPassed(String cantPassed) {
		this.cantPassed = cantPassed;
	}
	public String getCantFailed() {
		return cantFailed;
	}
	public void setCantFailed(String cantFailed) {
		this.cantFailed = cantFailed;
	}
	public String getCantOtrosEstados() {
		return cantOtrosEstados;
	}
	public void setCantOtrosEstados(String cantOtrosEstados) {
		this.cantOtrosEstados = cantOtrosEstados;
	}
	public String getCantTotal() {
		return cantTotal;
	}
	public void setCantTotal(String cantTotal) {
		this.cantTotal = cantTotal;
	}
	
	
	

}
