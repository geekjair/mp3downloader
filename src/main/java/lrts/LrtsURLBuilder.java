package lrts;

import org.apache.http.client.utils.URIBuilder;


public class LrtsURLBuilder extends URIBuilder {
	
	private String token = "2OSXRAhhlMPP1SZKF6GjFg**_Vb_E5-vcHwyyhwyRQq_5wocjzYKi5ka4";
	private String imei = "ODYzMjU0MDMyNjY2NzQ0";
	
	public URIBuilder getURI(){
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http");
		//builder.setHost("dapis.mting.info");
		builder.addParameter("imei", imei);
		builder.addParameter("token", token);
		builder.addParameter("nwt", "1");
		return builder;
	}
}
