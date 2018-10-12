package lrts;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;


/**
 * 对请求参数进行校验
 * @author admin
 *
 */
public class ParamsVerify {
	
	public static String secret = "iJ0DgxmdC83#I&j@iwg";
	
	public  static String convertUrlWIthAuth(String url){
		URIBuilder uriBuilder;
		String recortUrl = url;
		try {
			uriBuilder = new URIBuilder(url);
			//自然排序参数
			URIBuilder paramsUriBuilder = getUrlParams(uriBuilder);
			paramsUriBuilder.setCharset(Charset.defaultCharset());
			String pathParams = uriBuilder.getPath()+paramsUriBuilder.toString();
			pathParams = URLDecoder.decode(pathParams,"UTF-8");//防止加密失效
	        String sc = encode(pathParams + secret);
	        uriBuilder.addParameter("sc", sc);
	        url = uriBuilder.build().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	 public static String encode(String str) {
	        MessageDigest messageDigest = null;
	        try {
	            messageDigest = MessageDigest.getInstance("MD5");
	            messageDigest.reset();
	            messageDigest.update(str.getBytes("UTF-8"));
	        } catch (NoSuchAlgorithmException e) {
	            System.exit(-1);
	        } catch (UnsupportedEncodingException e2) {
	            e2.printStackTrace();
	        }
	        byte[] digest = messageDigest.digest();
	        StringBuffer stringBuffer = new StringBuffer();
	        for (int i = 0; i < digest.length; i++) {
	            if (Integer.toHexString(digest[i] & 255).length() == 1) {
	                stringBuffer.append("0").append(Integer.toHexString(digest[i] & 255));
	            } else {
	                stringBuffer.append(Integer.toHexString(digest[i] & 255));
	            }
	        }
	        return stringBuffer.toString().toLowerCase();
    }
	 
		/**
	  * 获取排好顺序的
	  * @param paramHttpUrl
	  * @return
	  */
	 public static URIBuilder getUrlParams(URIBuilder uriBuilder)
	  {
		 List<NameValuePair> paramsList = uriBuilder.getQueryParams();
	    TreeMap localTreeMap = new TreeMap();
	    URIBuilder result = new URIBuilder();
	    for (NameValuePair nameValuePair : paramsList) {
	    	if("sc".equals(nameValuePair.getName())){
	    		continue;
	    	}
	    	localTreeMap.put(nameValuePair.getName(), nameValuePair.getValue());
		}
	    Iterator paramHttpUrlI = localTreeMap.keySet().iterator();
	    while (paramHttpUrlI.hasNext())
	    {
	      String  key = (String)paramHttpUrlI.next();
	      result.addParameter(key, localTreeMap.get(key).toString());
	    }
	    return result;
	  }
}
