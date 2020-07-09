package com.brunosidad.ws.rest.vo;

import java.util.ArrayList;
import java.util.List;

public class Canal {
	
	
	public ArrayList<Suite> suites;
	
	
	public Canal() {
		suites= new ArrayList<Suite>();
	}
	
	public List<Suite> getSuites() {
		return suites;
	}
	public void setSuites(ArrayList<Suite> suites) {
		this.suites = suites;
	}
	
	public void addSuite(Suite func) {
		suites.add(func);
		
	}
	
}
