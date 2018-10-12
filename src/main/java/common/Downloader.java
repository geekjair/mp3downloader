package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Downloader {

	public String getBodyStr(String url){
		CloseableHttpClient httpclient = generateClientDemo();
		HttpGet httpGet = new HttpGet(url);
		StringBuffer sb = new StringBuffer();
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpGet);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
			
			String str = null;
			while((str = bufferedReader.readLine()) != null)
			{
				sb.append(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return sb.toString();
		}
	}
	
	public InputStream getBody(String url){
		CloseableHttpClient httpclient = generateClientDemo();
		HttpGet httpGet = new HttpGet(url);
		StringBuffer sb = new StringBuffer();
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpGet);
			return response1.getEntity().getContent();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println(response1.getStatusLine().getStatusCode());
				response1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return null;
		}
	}
	
	private  CloseableHttpClient generateClientDemo() {
		 HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
       HttpClientBuilder httpClientBuilder = HttpClients.custom();
       //httpClientBuilder.setProxy(proxy);
       //httpClientBuilder.setConnectionManager(connectionManager);
       //if (site.getUserAgent() != null) {
           httpClientBuilder.setUserAgent("Android8.0.0/yyting/Xiaomi/MI 6/ch_xiaomi/174/Android");
       //} else {
           //httpClientBuilder.setUserAgent("");
       //}
//       if (site.isUseGzip()) {
          httpClientBuilder.addInterceptorFirst(new HttpRequestInterceptor() {

			public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
				// TODO Auto-generated method stub
				  request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
			}

           });
//       }
       //解决post/redirect/post 302跳转问题
       //httpClientBuilder.setRedirectStrategy(new CustomRedirectStrategy());

       SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();
       socketConfigBuilder.setSoKeepAlive(true).setTcpNoDelay(true);
       socketConfigBuilder.setSoTimeout(3000);
       SocketConfig socketConfig = socketConfigBuilder.build();
       httpClientBuilder.setDefaultSocketConfig(socketConfig);
      // connectionManager.setDefaultSocketConfig(socketConfig);
       //httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(site.getRetryTimes(), true));
       //generateCookie(httpClientBuilder, site);
       return httpClientBuilder.build();
   }	
}
