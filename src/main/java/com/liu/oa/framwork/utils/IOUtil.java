package com.liu.oa.framwork.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class IOUtil {
	
	
	
	public static void outPutStream(InputStream in,HttpServletResponse response,String contetType)  {
		OutputStream out =null;
		int i;
		try {
			i = in.available();
			byte data [] = new byte[i];
			 out = response.getOutputStream();
			response.setHeader("Content-Type", contetType);
			in.read(data);
			
			out.write(data);
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
	

}
