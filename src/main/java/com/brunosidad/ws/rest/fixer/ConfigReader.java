package com.brunosidad.ws.rest.fixer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author hagra
 */
public class ConfigReader 
{
    private final String path_file;
    private final HashMap<String,String> keys = new HashMap<String,String>();
    
    public ConfigReader(String p_path_file,String[] p_keys) throws FileNotFoundException, IOException, Exception
    {
        path_file = p_path_file;
        Properties properties = new Properties();
        properties.load(new FileInputStream(path_file));
        
        for (String p_key : p_keys) 
        {
            String aux = properties.getProperty(p_key);
            if (aux == null) 
            {
                throw new Exception("No se encontro la clave " + p_key + " en el archivo de configuración.");
            }
            
            keys.put(p_key, properties.getProperty(p_key));
        }
        
    }
    
    public String getKeyValue(String key)
    {
        return keys.get(key);
    }
    
}
