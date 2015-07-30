package util;
//executor
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalProgram extends Thread{
	 private String arguments;
	
	public ExternalProgram(String s){
		arguments = s;
	}
	
	public void run(){
        try{
            Process pr = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", arguments});
            InputStream in = pr.getInputStream();
            OutputStream out = pr.getOutputStream();
            InputStream err = pr.getErrorStream();
            System.exit(0);
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}