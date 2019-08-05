//Código obtenido de http://www.it.uc3m.es/celeste/docencia/cr/2003/PracticaSocketsTCP/

import java.net.*;
import java.io.*;


public class ServidorTCP 
{
	  public static void main(String[] args) throws IOException 
	  {

	    ServerSocket socketServidor = null;
	    try {
	      socketServidor = new ServerSocket(4444);
	    } catch (IOException e) {
	      System.out.println("No puede escuchar en el puerto: " + 4444);
	      System.exit(-1);
	    }

	    Socket socketCliente = null;
	    BufferedReader entradaSocket = null;
	    PrintWriter salidaSocket = null;

	    System.out.println("Escuchando: " + socketServidor);
	    
	    socketCliente=socketServidor.accept();
	    try {

	      System.out.println("Conexión aceptada: "+ socketCliente);
	      entradaSocket = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

	      salidaSocket = new PrintWriter(new BufferedWriter(new 
		  OutputStreamWriter(socketCliente.getOutputStream())),true);
	      String aux="";
	      String mensaje="", salida="";

	      do{  
	        aux = entradaSocket.readLine();
	        mensaje = entradaSocket.readLine();
			System.out.println("Recibido: "+ aux + " " +mensaje);

			switch (aux) {
				case "1":
					salidaSocket.println(mensaje);
					salidaSocket.println(mensaje.toUpperCase());
					salidaSocket.println(mensaje.toLowerCase());
					break;
				case "2":
			        
					int dni=0;
					int resul=0;
					char[] arrayDNI = new char[24];
					char[] numDNI=mensaje.toCharArray();
					
					for(int i=0; i<numDNI.length-1; i++)
					{
						dni=(dni*10)+convertirCharToInt(numDNI[i]);
					}
					
					llenarArrayDNI(arrayDNI);

					resul=dni%23;
					
					if(numDNI[numDNI.length-1]==arrayDNI[resul])
						salidaSocket.println("DNI OK");
					else
						salidaSocket.println("DNI ERROR");
					break;
				case "3":
					int suma=0;

					do
					{
						suma=suma+Integer.parseInt(mensaje);
				        System.out.println("entrada server: " + mensaje + " -suma-: " + suma);
				        mensaje = entradaSocket.readLine();
					}while(!mensaje.equals("-1"));
					
					salidaSocket.println(suma);
					break;
				case "4":
					salidaSocket.println("Bien");
					break;
				default:
					salidaSocket.println(aux +": "+ mensaje.toUpperCase());
					break;
			}
	      }while(!mensaje.equals("Adios") || aux.equals("-1"));

	    } catch (IOException e) {
	      System.out.println("IOException: " + e.getMessage());
	    }  
	    salidaSocket.close();
	    entradaSocket.close();
	    socketCliente.close();
	    socketServidor.close();
	  }
	  
	static void llenarArrayDNI(char[] array)
	{
		array[0]='T';
		array[1]='R';
		array[2]='W';
		array[3]='A';
		array[4]='G';
		array[5]='M';
		array[6]='Y';
		array[7]='F';
		array[8]='P';
		array[9]='D';
		array[10]='X';
		array[11]='B';
		array[12]='N';
		array[13]='J';
		array[14]='Z';
		array[15]='S';
		array[16]='Q';
		array[17]='V';
		array[18]='H';
		array[19]='L';
		array[20]='C';
		array[21]='K';
		array[22]='E';
		array[23]='T';
	}
	
	static int convertirCharToInt(char num)
	{
		int entero=0;
		
		switch (num) {
		case '0':
			entero=0;
			break;
		case '1':
			entero=1;
			break;
		case '2':
			entero=2;
			break;
		case '3':
			entero=3;
			break;
		case '4':
			entero=4;
			break;
		case '5':
			entero=5;
			break;
		case '6':
			entero=6;
			break;
		case '7':
			entero=7;
			break;
		case '8':
			entero=8;
			break;
		case '9':
			entero=9;
			break;
		default:
			break;
		}
		
		return entero;
	}

}
