import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;



public class APIRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Read API Data from here 
		String str=getAPIResult("http://finance.google.com/finance/info?client=ig&q=AAPL,YHOO");
		System.out.println(str);
		
	}
	
	public static String getAPIResult(String url){
		
		 StringBuilder result = new StringBuilder();
	        try { 
	        	
	        	//If you want to hit a webpage then this is the best way which is currently used in Android also for native calls
	        	

	            HttpClient client = new DefaultHttpClient();
	            HttpParams httpParameters = client.getParams();
	            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
	            HttpConnectionParams.setSoTimeout(httpParameters, 5000);
	            HttpConnectionParams.setTcpNoDelay(httpParameters, true);
	            HttpGet request = new HttpGet();
	          
	            
	            
//	            URL uri=new URL(url);
//	            URLConnection hc=uri.openConnection();
//	            hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
//	            System.out.println("baby "+hc.getContent());
//	            
//	            System.out.println(convertResponsetoString((InputStream)hc.getContent()));
	            
	            
	            
	            request.setURI(new URI(url));
	            HttpResponse response = client.execute(request);
	            InputStream ips = response.getEntity().getContent();

	            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
	                    "UTF-8"));

	            StringBuilder sb=new StringBuilder();
	            String s="";
	            while (true) {
	            	
	                s = buf.readLine();
	               
	                if (s == null || s.length() == 0)
	                    break;
	                
	                else
	                	sb.append(s);

	            }
	            buf.close();
	            ips.close();
	            result.append(sb);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return result.toString();
		
	}
	
	
	
	public static String convertResponsetoString(InputStream ips){
		
		
		
		
		String result="";
		try{
		
		
		
		
    

        BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
                "UTF-8"));

       // StringBuilder sb = new StringBuilder();
        String sb="";
        String s="";
        while (true) {
        	
            s = buf.readLine();
            System.out.println("Raja "+s);
            if (s == null || s.length() == 0)
                break;
            
            else
            	sb+=s;

        }
        buf.close();
        ips.close();
        result = sb;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return result;

		
		
	}

}
