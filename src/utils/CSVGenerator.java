package utils;

import java.io.FileWriter;
import java.io.IOException;

import models.Country;

/**
 * Created by ebrahim-elgaml on 17/07/16.
 */
public class CSVGenerator {
	/**
	 * generate csv output file.
	 * file header _id,name,type,latitude,longitude.
	 * @param countries is the list of the response to generate the csv file with data from it.
	 * @param fileName is the name of the generated csv output file.
	 */
	public static void generateCSVFiler(Country[] countries, String fileName){
		FileWriter writer;
		try {
			writer = new FileWriter(fileName);
			writer.append("_id,name,type,latitude,longitude");
		    writer.append('\n');
		    for(Country c : countries){
				try
				{
				    writer.append(c.getId() + "");
				    writer.append(',');
				    writer.append(c.getName());
				    writer.append(',');
				    writer.append(c.getType());
				    writer.append(',');
				    writer.append(c.getGeoPosition().getLatitude() + "");
				    writer.append(',');
				    writer.append(c.getGeoPosition().getLongitude() + "");
			        writer.append('\n');
				    
				}
				catch(IOException e)
				{
				     e.printStackTrace();
				} 
			}
		    writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
