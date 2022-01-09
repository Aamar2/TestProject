package qa.tests;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	
public static Properties properties;
	
	public PropertyReader(){
//		loadAllProperties();
	}

	public static void loadAllProperties() {
		if (properties != null) {	//config.properties is only read once. This if statement ensures that config file is read ONLY ONCE.
			return;
		}
		properties = new Properties();
		try{
			System.out.println("Loading config.properties data.");
			properties.load(new FileInputStream( System.getProperty("user.dir") +"/userDetails.properties"));
		}catch(Exception e){
			throw new RuntimeException("Could not read Properties File");
		}		
	}
	
	public static synchronized String readItem(String propertyName){
		return properties.getProperty(propertyName);
	}

}
