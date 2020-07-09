package com.brunosidad.ws.rest.infraestructure;

public class Constants {
	
	public static String HOST = "http://wapp-prpm205:8080";
	public static String URL = "http://wapp-prpm205:8080/qcbin";
	public static String APIURL = "http://wapp-prpm205:8080/qcbin/rest/oauth2/login";
    public static String PORT = "8080";
    public static String USERNAME = "usuarioautoldap";
    public static String PASSWORD = "Prisma15";
    public static String DOMAIN = "PRISMA";
    public static String PROJECT = "BancaElectronica";
    public static String CLIENTID= "apikey-otjbcbafncskejadflgh";
    public static String SECRET= "mmmbbggenffnmkcm";
        
    
    /**
     * Supports running tests correctly on both versioned
     * and non-versioned projects.
     * @return true if entities of entityType support versioning
     */
    /*public static boolean isVersioned(String entityType,
        final String domain, final String project)
        throws Exception {

        RestConnector con = RestConnector.getInstance();
        String descriptorUrl =
            con.buildUrl("rest/domains/"
                 + domain
                 + "/projects/"
                 + project
                 + "/customization/entities/"
                 + entityType);

        String descriptorXml =
           con.httpGet(descriptorUrl, null, null).toString();
        EntityDescriptor descriptor =
                EntityMarshallingUtils.marshal
                    (EntityDescriptor.class, descriptorXml);

        boolean isVersioned = descriptor.getSupportsVC().getValue();

        return isVersioned;
    }*/

    public static String generateFieldXml(String field, String value) {
        return "<Field Name=\"" + field
           + "\"><Value>" + value
           + "</Value></Field>";
    }

    /**
     * This string used to create new "requirement" type entities.
     */
    public static final String entityToPostName = "run"
        + Double.toHexString(Math.random());
    public static final String entityToPostFieldName =
        "type-id";
    public static final String entityToPostFieldValue = "1";
    public static final String entityToPostFormat =
        "<Entity Type=\"run\">"
                + "<Fields>"
                + Constants.generateFieldXml("%s", "%s")
                + Constants.generateFieldXml("%s", "%s")
                + "</Fields>"
                + "</Entity>";

    public static final String entityToPostXml =
        String.format(
                entityToPostFormat,
                "name",
                entityToPostName,
                entityToPostFieldName,
                entityToPostFieldValue);

    public static final CharSequence entityToPostFieldXml =
        generateFieldXml(Constants.entityToPostFieldName,
        Constants.entityToPostFieldValue);
	
}
