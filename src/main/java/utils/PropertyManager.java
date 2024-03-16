package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    Properties property;

    public PropertyManager() {
        Properties props = new Properties();
        try {
            props.load(new FileReader(new File(ClassLoader.getSystemResource("config.properties").getPath())));
            property = props;
        } catch (IOException ex) {
            // handle error
            System.out.println("Properties file not found!");
        }
    }

    public String getProperty(String key) {
        Properties props = property;
        if (props != null) {
            return props.getProperty(key);
        }
        return null;
    }
}
