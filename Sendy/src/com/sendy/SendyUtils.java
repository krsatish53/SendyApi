/**
 * 
 */
package com.sendy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author satish
 *
 */
public class SendyUtils {
	
	
	static String SENDY_BASEURL= "http://your_sendy_installation/";
	static String SENDY_API_KEY ="your_sendy_api_key";
	
	public static String addEmailToList(String listid, String email, String name) throws Exception {

		String url = SENDY_BASEURL+"subscribe";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");		

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("list", listid));		
		urlParameters.add(new BasicNameValuePair("name", name));		
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("boolean", "true"));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		//System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());		
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		//return=1 
		return result.toString();		
	}
	
	public static String unsubscribeEmailFromList(String listid, String email) throws Exception {

		String url = SENDY_BASEURL+"unsubscribe";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");		

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("list", listid));				
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("boolean", "true"));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());		
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		//return true 200
		return result.toString();		
	}
	
	public static String deleteEmailFromList(String apikey, String listid, String email) throws Exception {

		String url = SENDY_BASEURL+"api/subscribers/delete.php";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");		

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("list_id", listid));				
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("api_key", apikey));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());		
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		//return true 200
		return result.toString();		
	}
	
	public static String activeSubscriberCountList(String apikey, String listid) throws Exception {

		String url = SENDY_BASEURL+"api/subscribers/active-subscriber-count.php";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("list_id", listid));		
		urlParameters.add(new BasicNameValuePair("api_key", apikey));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());		
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		//You'll get an integer of the active subscriber count
		return result.toString();		
	}


	public static int createAndSendCampaign(String apikey, String listid,String subject,String plaintext, String html, String from_name) throws Exception {

		String url = SENDY_BASEURL+"api/campaigns/create.php";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		urlParameters.add(new BasicNameValuePair("api_key", apikey));	
		urlParameters.add(new BasicNameValuePair("from_name", from_name));
		urlParameters.add(new BasicNameValuePair("from_email", "you@yourdomain.com"));
		urlParameters.add(new BasicNameValuePair("reply_to", "you@yourdomain.com"));
		urlParameters.add(new BasicNameValuePair("subject", subject));
		urlParameters.add(new BasicNameValuePair("plain_text", plaintext));
		urlParameters.add(new BasicNameValuePair("html_text", html));
		//Required only if you set send_campaign to 1 to send the campaign and not just create a draft. 
		//List IDs should be single or comma-separated. The encrypted & hashed ids can be found under View all lists section named ID.
		urlParameters.add(new BasicNameValuePair("list_ids", listid));
		//Required only if you are creating a 'Draft' campaign (send_campaign set to 0 or left as default).
		//Brand IDs can be found under 'Brands' page named ID
		//urlParameters.add(new BasicNameValuePair("brand_id", ""));
		//urlParameters.add(new BasicNameValuePair("query_string", ""));		
		//Set to 1 if you want to send the campaign as well and not just create a draft. Default is 0.
		urlParameters.add(new BasicNameValuePair("send_campaign", "1"));
		

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " +response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		//Success: Campaign created
		//Success: Campaign created and now sending
		//send success code
		System.out.println(result.toString());
		return response.getStatusLine().getStatusCode();

	}
	
	
	public static void main(String[] args) throws Exception {
		
		
	   
	}

}
