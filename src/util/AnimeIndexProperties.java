package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AnimeIndexProperties
{
	private final static String PROPERTIES_PATH = FileManager.getAppDataPath() + "properties.properties";
	private static Properties applicationProps;
	public static Properties createProperties() 
	{
		// create and load default properties
		Properties defaultProps = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(PROPERTIES_PATH);
			defaultProps.load(in);
			defaultProps.setProperty("List_to_visualize_at_start", "Anime completati");
			defaultProps.setProperty("Update_system", "false");
			defaultProps.setProperty("List_to_Check", "None");
			in.close();
		} 
		catch (FileNotFoundException fe)
		{
			File prop = new File(PROPERTIES_PATH);
			try {
				prop.getParentFile().mkdirs();
				prop.createNewFile();
				defaultProps.setProperty("Update_system", "false");
				defaultProps.setProperty("List_to_visualize_at_start", "Anime completati");
				defaultProps.setProperty("List_to_Check", "None");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		applicationProps = new Properties(defaultProps);

		// now load properties 
		// from last invocation
		try {
			in = new FileInputStream(PROPERTIES_PATH);
			applicationProps.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		};
		return applicationProps;
	}
	
	public static void saveProperties(Properties prop)
	{
		FileOutputStream out;
		try {
			out = new FileOutputStream(PROPERTIES_PATH);
			prop.store(out, "---Start Option---");
			out.close();
		} catch (Exception e) {
		}
	}
}
