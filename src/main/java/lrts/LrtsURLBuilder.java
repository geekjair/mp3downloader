package lrts;

import org.apache.http.client.utils.URIBuilder;


public class LrtsURLBuilder extends URIBuilder {
	
	private String token = "wKuk38rKUlTP1SZKF6GjFg**_Vb_E5-vcHwxbZ0PqUTgmP_rb9srdZRs_";
	//private String imei = "ODYzMjU0MDMyNjY2NzQ0";
	
	public URIBuilder getURI(){
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http");
		//builder.setHost("dapis.mting.info");
		//builder.addParameter("imei", imei);
		builder.addParameter("token", token);
		builder.addParameter("nwt", "1");
		return builder;
	}
}
