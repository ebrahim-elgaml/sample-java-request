package network;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import models.Country;

/**
 * Created by ebrahim-elgaml on 17/07/16.
 */
public class Request {
	private RequestCallback requestCallbackListner;
	
	public Request(){
		
	}
	/**
	 * send get request to the server to get the json response.
	 * @param url is the url to send the request to.
	 */
	public void sendGetRequest(String url){
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            int statusCode = result.getStatusLine().getStatusCode();
            switch(statusCode){
            	case 200: 
            		Gson gson = new Gson();
                    Country[] res = gson.fromJson(json,Country[].class);
                    if(res.length > 0){
                    	requestCallbackListner.onSuccess(res);
                    }else{
                    	requestCallbackListner.onFailure("No matches are found");
                    }
                    break;
            	case 422: 
            		requestCallbackListner.onFailure("Wrong input country");
                    break;
            	case 500: 
            		requestCallbackListner.onFailure("Internal server error");
                    break;
            	case 401: 
            		requestCallbackListner.onFailure("Unauthorized");
                    break;
                default : 
                	requestCallbackListner.onFailure("Something went wrong please check again");
                    break;
            }
        } catch (IOException ex) {
        	requestCallbackListner.onFailure("Something went wrong please check again");
        }
	}
	public RequestCallback getRequestCallbackListner() {
		return requestCallbackListner;
	}

	public void setRequestCallbackListner(RequestCallback requestCallbackListner) {
		this.requestCallbackListner = requestCallbackListner;
	}
	
	
}
