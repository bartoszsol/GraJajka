package application;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PolaczenieDoSerwera {
	
	
	public void wyslijWynik(Gracz gracz) throws IOException {
		
		// Server will be started on 1700 port number
	    ServerSocket server=new ServerSocket(1700);
		
	   // Server listening for connection
	    Socket s=server.accept();
		
	   // Stream object for sending object 
	    ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
		
	   os.writeObject(gracz);
	   
	   s.close();
	   server.close();
	}
}
