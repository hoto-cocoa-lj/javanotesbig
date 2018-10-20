import java.util.*;import java.io.*;import java.lang.Thread;import java.net.*;

class Listen implements Runnable{
	DataInputStream dis;Socket s;String mes;String me;
	public Listen(Socket s){
		try{
			this.s=s;
			me=s.getInetAddress()+":"+s.getPort();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void Listen1(){
		try{			
			DataInputStream dis=new DataInputStream(s.getInputStream());
			while(true){
				mes=dis.readUTF();
				if(mes.equalsIgnoreCase("q")){s.close();}
				System.out.println(me+mes);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){Listen1();}

}

class Say implements Runnable{
	Socket s;String mes;DataOutputStream dos;BufferedReader br;
	Say(Socket s){
		try{
			this.s=s;

		}catch(Exception e){
			e.printStackTrace();
		}			
	}
	public void Say1(){
		try{										
		InputStreamReader is=new InputStreamReader(System.in);	
			BufferedReader br=new BufferedReader(is);	
		DataOutputStream dos=new DataOutputStream(s.getOutputStream());	

			while(true){
				mes=br.readLine();
				dos.writeUTF(mes);dos.flush();
				if(mes.equalsIgnoreCase("q")){s.close();}}
		}catch(Exception e){
			e.printStackTrace();
		}			
	}
	public void run(){Say1();}
}