package protezio.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;




public class RestClient {


        private static final boolean FALSE = false;
        private static final boolean TRUE = true;
		JSONObject data = new JSONObject();
        static String url;
        boolean errore = FALSE;
        private static AsyncHttpClient client = new AsyncHttpClient();


        public RestClient(String s){

            url = s;
        }
        
        public void get(String url, RequestParams params,  AsyncHttpResponseHandler responseHandler) {
    		
    		
    		client.get(getAbsoluteUrl(url), params, responseHandler);
    	      
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

        
        
        
        
        
        
        
        

        public  void SetGPIO(String number,String status){
        	
        	HttpClient client = new DefaultHttpClient();        	
        	HttpPost post = new HttpPost(url+"/GPIO/"+ number +"/value/"+ status);
        	
        	try {
        		
				 client.execute(post);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return response;
        	executeAsyncGet();
        	
        }

        
        public String GetGPIO(String number)
        {
        	String valore = "ERRORE";
        	
        	if (errore) return valore;
        	
        	else{
        	try {
        		
				//JSONObject gpio_data = new JSONObject(this.executeGet());
        		this.executeAsyncGet();
				JSONObject gpio_data = data;
        		
        		String value = gpio_data.getString(number);
				JSONObject gpio_value = new JSONObject(value);
				
				return  gpio_value.getString("value");
			     
			    } catch (Exception e) 
			    {
			    	
			      e.printStackTrace();
			      return valore;
			    }
        	//
        	}
        }
    
public void executeAsyncGet(){
		
	    
		get(url+"/GPIO/*", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            	//Log.i("INFO",response.toString() );
            	
            	
            	Log.i("DATA","RESPONSE " + response.toString());
            	data = response;
            	Log.i("DATA","DATA " + response.toString());
            	errore=FALSE;
            }
            
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
               
            }
            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, java.lang.Throwable throwable, JSONObject errorResponse)
            
            {
            	
            	Log.i("INFO","ERRORE FAILURE");
            	errore= TRUE;
            	            	
            	
            }

            
            
           
        });
	}
        
        
		
		public int error ()
		{
			
			this.executeAsyncGet();
			if (errore) return 1 ;
			else 	return 0;
			
		}
		
		
		
		
		

        private String executeGet() { 

            HttpClient httpClient = new DefaultHttpClient();
          
            HttpGet httpget = new HttpGet(url+"/GPIO/*");
            
            String result = null;
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            try {
                result = httpClient.execute(httpget, responseHandler);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }

		
		
		
		
}