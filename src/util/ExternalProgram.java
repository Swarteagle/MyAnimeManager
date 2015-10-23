package util;
//executor
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalProgram extends Thread{
	 private String arguments;
	
	public ExternalProgram(String s){
		arguments = s;
	}
	
	public ExternalProgram(String s, int time){
		arguments = s + " -t " + time;
	}
	public void run(){
        try{
            Process pr = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", arguments});
            InputStream in = pr.getInputStream();
            OutputStream out = pr.getOutputStream();
            InputStream err = pr.getErrorStream();
            in.close();
            out.close();
            err.close();
            System.exit(0);
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}