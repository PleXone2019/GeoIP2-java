////package com.maxmind.geoip2;

import java.lang.*;
import java.util.*;
import java.io.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.impl.auth.*;
import org.apache.http.auth.*;
/**
 * Hello world!
 *
 */
public class App 
{
  public static void main( String[] args )
  {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    try {
      String user_id = args[0];
      String license_key = args[1];
      String ip_address = args[2];
      HttpGet httpget = new HttpGet("https://geoip.maxmind.com/geoip/country/" + ip_address);
     httpget.addHeader("Accept","application/json");
      httpget.addHeader(BasicScheme.authenticate(
      new UsernamePasswordCredentials(user_id,license_key),"UTF-8",false));
        //System.out.println( "Hello " + args[0]);
      HttpResponse response = httpclient.execute(httpget);
      System.out.println(response.getStatusLine());
      HttpEntity entity = response.getEntity();
      System.out.println(response.getStatusLine());
      if (entity != null) {
        System.out.println("Response content length: " + entity.getContentLength());
        InputStream instream = entity.getContent();
        BufferedReader reader = new BufferedReader(
                 new InputStreamReader(instream));
        System.out.println(reader.readLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
  }
}

