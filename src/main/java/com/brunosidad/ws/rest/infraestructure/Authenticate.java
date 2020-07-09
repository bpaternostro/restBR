package com.brunosidad.ws.rest.infraestructure;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.brunosidad.ws.rest.infraestructure.Base64Encoder;

public class Authenticate {
	
	private RestConnector con;
			
    public Authenticate() {
        con = RestConnector.getInstance();
    }
    /**
     * @param username
     * @param passowrd
     * @return true if authenticated at the end of this method.
     * @throws Exception
     *
     * convenience method used by other examples to do their login
     */
    public boolean login(String username, String passowrd) throws Exception {
        String authenticationPoint = this.isAuthenticated();
        if (authenticationPoint != null) {
            return this.login(authenticationPoint, Constants.USERNAME, Constants.PASSWORD);
        }
        return true;
    }
    /**
     * @param loginUrl
     *            to authenticate at
     * @param username
     * @param password
     * @return true on operation success, false otherwise
     * @throws Exception
     *
     * logging in to our system is standard http login (basic authentication), where one must store
     * the returned cookies for further use.
     */
    public boolean login(String loginUrl, String clientId, String secret) throws Exception {
        //Create a string that looks like "Basic ((username:password)lt;as bytesgt;)lt;64encodedgt;"
        //byte[] credBytes = ("clientId:"+ clientId + "," + "secret:" + secret).getBytes();   
        //String credEncodedString = "Basic " + Base64Encoder.encode(credBytes);
        
        JSONObject auth = new JSONObject();
        auth.put("clientId", clientId);
        auth.put("secret", secret);
        
        byte[] credBytes = auth.toString().getBytes();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json;charset=UTF-8");
        System.out.println(loginUrl);
        
        Response response = con.httpPost(loginUrl, credBytes, map);
        System.out.println(response.getStatusCode());
        boolean ret = response.getStatusCode() == HttpURLConnection.HTTP_OK;
        return ret;
    }
    /**
     * @return true if logout successful
     * @throws Exception
     *             close session on server and clean session cookies on client
     */
    public boolean logout() throws Exception {
        //New that the get operation logs us out by setting authentication cookies to: LWSSO_COOKIE_KEY="" using server response header Set-Cookie
        Response response =
                con.httpGet(con.buildUrl("qcbin/authentication-point/logout"), null, null);
        return (response.getStatusCode() == HttpURLConnection.HTTP_OK);
    }
    /**
     * @return null if authenticated.lt;brgt;
     *         a url to authenticate against if not authenticated.
     * @throws Exception
     */
    public String isAuthenticated() throws Exception {
        String isAuthenticateUrl = con.buildUrl("qcbin/rest/is-authenticated");
        String ret;
        Response response = con.httpGet(isAuthenticateUrl, null, null);
        int responseCode = response.getStatusCode();
        //If already authenticated
        if (responseCode == HttpURLConnection.HTTP_OK) {
            ret = null;
        }
        //If not authenticated - get the address where to authenticate via WWW-Authenticate
        else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
            Iterable<String> authenticationHeader =
                    response.getResponseHeaders().get("WWW-Authenticate");
            String newUrl = authenticationHeader.iterator().next().split("=")[1];
            newUrl = newUrl.replace("\"", "");
            newUrl += "/authenticate";
            ret = newUrl;
        }
        //Not OK and not unauthorized - means some kind of error, like 404, or 500
        else {
            throw response.getFailure();
        }
        return ret;
    }

}
