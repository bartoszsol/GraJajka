package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SerwerWynikow {

	

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ArrayList<Gracz> listaGraczy = new ArrayList<>();
		int i = 0;
		
		ServerSocket ss = new ServerSocket(7777);
		System.out.println("Serwer oczkeuje na polaczenie ...");

		while (true) {
			Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
			System.out.println("Polaczenie z  " + socket + "!");

			// get the input stream from the connected socket
			InputStream inputStream = socket.getInputStream();
			// create a DataInputStream so we can read data from it.
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			Gracz gracz = (Gracz) objectInputStream.readObject();
			listaGraczy.add(gracz);
			System.out.println("Otrzymana wiadomosc od : " + socket);
			System.out.println("-------------------------------------------------------------------- Aktualna Lista Graczy :");

			//System.out.println("-----------------------serwer printuje :" + gracz.toString());
			i++;
			
			for (Gracz graczIt : listaGraczy) {
			System.out.println("-------------------------------------------------------------------  serwer printuje :" +	graczIt.toString()); }
			
		}
//        System.out.println("Zamykam  sockety.");
//        ss.close();
//        socket.close();

	}
}
