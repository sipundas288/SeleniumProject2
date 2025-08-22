package dataDrivenTesting;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReadDataFromJson {

	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fir=new FileReader("./src\\test\\resources\\commondata.json");
		JSONParser jsonparser= new JSONParser();
		Object javaobj = jsonparser.parse(fir);
		
		//create  json object to json object(downcasting)
		JSONObject obj=(JSONObject)javaobj;
		
		//read data using get()
		String BROWSER = obj.get("browser").toString();
		System.out.println(BROWSER);
		
		
		
		
		
		
	}

}
