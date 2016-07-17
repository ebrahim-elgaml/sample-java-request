package main;

import models.Country;
import network.Request;
import network.RequestCallback;
import utils.CSVGenerator;
/**
 * Created by ebrahim-elgaml on 17/07/16.
 */
public class Main{
	private static String cityName;
	private static RequestCallback callbackListener = new RequestCallback(){

		@Override
		public void onSuccess(Country[] response) {
			System.out.println("Success, Please check the output file <"+ cityName + ".csv>");
			CSVGenerator.generateCSVFiler(response, cityName + ".csv");
		}

		@Override
		public void onFailure(String message) {
			System.out.println("Failure");
			System.out.println(message);
			
		}
		
	};
	public static void main(String [] args){
		Request r = new Request();
		r.setRequestCallbackListner(callbackListener);
		cityName = args[0];
		r.sendGetRequest("http://api.goeuro.com/api/v2/position/suggest/en/" + cityName);
	}

}
