import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;


public class NaiveAPICall {

	public static void main(String[] args) throws IOException {
		 
		String address = "http://finance.google.com/finance/info?client=ig&q=AAPL,YHOO";
//		String query = "java tutorial";
//		String charset = "UTF-8";
	 
		URL url = new URL(address);
	 
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String str;
	 
		while ((str = in.readLine()) != null) {
			System.out.println(str);
		}
	 
		in.close();
	}

}
