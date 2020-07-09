package com.brunosidad.ws.rest.infraestructure;


import java.util.HashMap;
import java.util.Map;


public class EntityCreator {
	
    private static RestConnector con;
    
    public EntityCreator() {
        con = RestConnector.getInstance();
    }
    
    /**
     * @param collectionUrl
     *            the url of the collection of the relevant entity type.
     * @param postedEntityXml
     *            the xml describing an instance of said entity type.
     * @return the url of the newly created entity.
     * @throws java.lang.Exception
     */
    public String createEntity(String collectionUrl, String postedEntityXml) throws Exception {
        Map<String, String> requestHeaders = new HashMap<String, String>();
        //System.out.println(collectionUrl);
        //System.out.println(postedEntityXml);
        requestHeaders.put("Content-Type", "application/xml; charset=UTF-8");
        requestHeaders.put("Accept", "application/xml;charset=UTF-8");
        requestHeaders.put("Cookie", con.getCookieString());
        /*System.out.println(con.getCookieString());
        System.out.println(collectionUrl);
        System.out.println(postedEntityXml.getBytes());*/
        //As can be seen in the implementation below, creating an entity is simply posting its XML into the correct collection.
        Response response = con.httpPost(collectionUrl, postedEntityXml.getBytes(), requestHeaders);
        //System.out.println(response);
        Exception failure = response.getFailure();
        if (failure != null) {
            throw failure;
        }
        /*
         Note that we also get the XML of the newly created entity.
         At the same time, we get the URL where it was created in a location response header.
        */
        String entityUrl = response.getResponseHeaders().get("Location").iterator().next();
        return entityUrl;
    }
    
    /**
     * @param entityUrl
     *            the url of the entity that we wish to remove
     * @return xml of deleted entity
     * @throws java.lang.Exception
     */
    public String deleteEntity(String entityUrl) throws Exception {
        //As we can see from the implementation below, to delete an entity is simply to use HTTP delete on its URL.
        Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("Accept", "application/xml; charset=UTF-8");
        return con.httpDelete(entityUrl, requestHeaders).toString();
    }

    
    public static String generateUpdateXml(String entity,String field, String value) {
         return "<Entity Type=\""
        		 +entity
        		 +"\"><Fields>"
                 + "<Field Name=\""
                 + field
                 + "\"><Value>"
                 + value
                 + "</Value></Field>"
                 + "</Fields></Entity>";
    }
    
    public static String getXmlResponse(String collectionUrl,String queryString) throws Exception {
    	 Map<String, String> requestHeaders = new HashMap<String, String>();
         requestHeaders.put("Content-Type", "application/xml; charset=UTF-8");
         requestHeaders.put("Accept", "application/xml; charset=UTF-8");
         requestHeaders.put("Cookie", con.getCookieString());
         
         Response resp=con.httpGet(collectionUrl, queryString, requestHeaders);
         return resp.toString();
         
    }
      
 
    /**
    * @param entityUrl
    * to update
    * @param updatedEntityXml
    * new entity descripion. only lists updated fields. unmentioned fields will not
    * change.
    * @return xml description of the entity on the serverside, after update.
    * @throws Exception
    */
    public Response update(String entityUrl, String updatedEntityXml) throws Exception {
            Map<String, String> requestHeaders = new HashMap<String, String>();
            requestHeaders.put("Content-Type", "application/xml; charset=UTF-8");
            requestHeaders.put("Accept", "application/xml; charset=UTF-8");
            Response put = con.httpPut(entityUrl, updatedEntityXml.getBytes(), requestHeaders);
            return put;
    }

}
