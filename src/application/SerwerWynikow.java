package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SerwerWynikow {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	     Socket s=new Socket("127.0.0.1",1700);
	     ObjectInputStream is=new ObjectInputStream(s.getInputStream());
	     Gracz gracz=(Gracz)is.readObject();
	     
	     System.out.println("-----------------------serwer printuje :" + gracz.toString());
	     
	     s.close();
		

	}
}
