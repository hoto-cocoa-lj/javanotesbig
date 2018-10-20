import java.util.*;import java.io.*;import java.lang.Thread;import java.net.*;

public class S{
public static void main(String[] args){
try{	
	new Thread(new XC()).start();
	ServerSocket ss=new ServerSocket(6666);
	while(true){
		
		Socket s=ss.accept();
		Listen l=new Listen(s);Thread t=new Thread(l);t.start(); 
		Say sa=new Say(s);Thread t1=new Thread(sa);t1.start(); 
	}

}catch(Exception e){e.printStackTrace();}}}

class XC implements Runnable{
	public void run(){
		while(true){
			System.out.println(Thread.activeCount());
try{Thread.sleep(2000);}catch(Exception e){;}
	}}
}
