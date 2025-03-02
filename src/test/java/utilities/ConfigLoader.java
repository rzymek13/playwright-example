package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getProperty(String key) {
        String systemProp = System.getProperty(key);
        return systemProp != null ? systemProp : properties.getProperty(key);
    }
    public static void getAllProperties(){
        for (Object key:properties.keySet()){
            System.out.println(properties.get(key));

        }
    }

    public static void main(String[] args) {
        ConfigLoader.getAllProperties();
    }
}
