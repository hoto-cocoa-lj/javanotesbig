import java.util.*;import java.io.*;import java.lang.Thread;import java.net.*;
public class C{
public static void main(String[] args){
try{
	Socket s=new Socket("localhost",6666);
	Say sa=new Say(s);Thread t1=new Thread(sa);t1.start(); 
	Listen l=new Listen(s);Thread t=new Thread(l);t.start(); 
}catch(Exception e){e.printStackTrace();}}}


