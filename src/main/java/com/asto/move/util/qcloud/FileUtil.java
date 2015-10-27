package com.asto.move.util.qcloud;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件相关操作
 * @author Administrator
 *
 */
public class FileUtil {

	/**
	 * 写文件,追加内容
	 * @param path
	 */
	public static void writeFile(String msg){
		String path = "./move.txt";
		BufferedWriter out = null;
		File file = new File(path);
		try {
			if(!file.exists()){
				file.createNewFile();
				if(msg != null && !"".equals(msg)){
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
					out.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"   "+msg+"\n");
				}
			}else{
				if(msg != null && !"".equals(msg)){
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
					out.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"   "+msg+"\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }    
		}
	}
	
	public static void main(String[] args) {
		writeFile("111111");
	}
}
