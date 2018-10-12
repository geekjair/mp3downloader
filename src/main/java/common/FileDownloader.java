package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {
	
	public static boolean saveFile(String path,String fileName,String strUrl) {
		boolean result = true;
		FileOutputStream fops = null;
		try { 
	      URL url = new URL(strUrl); 
	      HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
	      conn.setRequestMethod("GET"); 
	      //conn.setConnectTimeout(5 * 1000); 下载大文件容易超时
	      InputStream inStream = conn.getInputStream();//通过输入流获取数据 
	      byte[] buffer = new byte[4096]; 
	      int len = 0; 
	      File file = new File("D:\\"+path+"\\"+fileName+".mp3"); 
	      fops = new FileOutputStream(file); 
	      while( (len=inStream.read(buffer)) != -1 ){ 
	        fops.write(buffer, 0, len); 
	      } 
	     
	    } catch (Exception e) { 
	    	result = false;
	      e.printStackTrace(); 
	    } finally {
	    	 try {
	    		 if(fops != null){
	    			 fops.flush();
	 				fops.close();  
	    		 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return result;
	}
}
