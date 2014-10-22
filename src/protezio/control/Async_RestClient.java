package protezio.control;
import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.*;
import java.util.concurrent.Future;

public class Async_RestClient {
	
	//private static final String BASE_URL = "";
	public String url;
	JSONObject asy_response = null;
	  
	AsyncHttpResponseHandler responseH;

    public  Async_RestClient(String s) 
    {
    	url = s;
    	
	}
	
    private static AsyncHttpClient client = new AsyncHttpClient();
    
    
	public void getget (String url, RequestParams params,  AsyncHttpResponseHandler responseHandler) {
		
		
		client.get(getAbsoluteUrl(url), params, responseHandler);
	
		
	  }
    
    
    
    
	public void get(String url, RequestParams params,  AsyncHttpResponseHandler responseHandler) {
		
		
		client.get(getAbsoluteUrl(url), params, responseHandler);
		responseHandler = responseH;
	      
	  }
	
	public String get_response (){
		
		
		return url;
		
		
	}
	
	
	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }

	  private static String getAbsoluteUrl(String relativeUrl) {
	      return relativeUrl;
	}

	  




}