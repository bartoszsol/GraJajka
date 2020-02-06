package application;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PolaczenieDoSerwera {
	
	
	public void wyslijWynik(Gracz gracz) throws IOException {
	//public static void main(String[] args) throws IOException {
		
		// need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // make a bunch of messages to send.
        /*
        List<Gracz> gracze = new ArrayList<>();
        gracze.add(new Gracz("gracz1", 101));
        gracze.add(new Gracz("gracz2", 102));
        gracze.add(new Gracz("gracz3", 103));
		*/
        
        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(gracz);

        System.out.println("Closing socket and terminating program.");
        socket.close();
	}
}
