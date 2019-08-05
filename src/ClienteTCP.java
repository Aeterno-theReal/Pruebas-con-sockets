import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP 
{
	public static void main(String[] args)  throws IOException 
	{
	    Socket socketCliente = null;
	    BufferedReader entradaSocket = null;
	    PrintWriter salidaSocket = null;


	    try {
	      socketCliente = new Socket("localhost", 4444);

	      entradaSocket = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      salidaSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
	
	    } catch (IOException e) 
	    {
		System.err.println("No puede establecer canales de E/S para la conexi�n");
	        System.exit(-1);
	    }
	    
	    Scanner teclado = new Scanner(System.in);

	    String lineaServidor, lineaTeclado = null;
	    int ejercicio=0;
	    String[] numeros=new String[100];

	    try 
	    {
	      do 
	      {
	    	  System.out.println("\nIntroduzca el ejerc�cio a enviar: ");
	    	  ejercicio=teclado.nextInt();
	    	  teclado.nextLine();
	    	  
		      salidaSocket.println(ejercicio);
		      
	    	  switch (ejercicio) {
	    	  	case 1:
					System.out.println("----Ejercicio 1: enviar una palabra y devuelve min�sculas y may�sculas-----");
					System.out.print("Introduce el mensaje a enviar: ");
					lineaTeclado = teclado.nextLine();	        
			        
			        salidaSocket.println(lineaTeclado);
					lineaServidor = entradaSocket.readLine();
			        System.out.println("Respuesta servidor: '" + lineaServidor+"'");
			        lineaServidor = entradaSocket.readLine();
			        System.out.println("Respuesta servidor: '" + lineaServidor+"'");
			        lineaServidor = entradaSocket.readLine();
			        System.out.println("Respuesta servidor: '" + lineaServidor+"'");
					break;
	    	  	case 2:
					System.out.println("----Ejercicio 2: enviar DNI y calcular que es correcto-----");
					System.out.print("Introduce el DNI a enviar: ");
					lineaTeclado = teclado.nextLine();
					salidaSocket.println(lineaTeclado);
					lineaServidor = entradaSocket.readLine();
					System.out.println("Respuesta servidor: '" + lineaServidor + "'");
					break;
	    	  	case 3:
					System.out.println("----Ejercicio 3: Env�a n�meros y devuelve la suma de todos-----");
					do
					{
						System.out.print("Introduce el n�mero a enviar: ");
						lineaTeclado = teclado.nextLine();	 
						salidaSocket.println(lineaTeclado);					
					}while(!lineaTeclado.equals("-1"));
					
					lineaServidor = entradaSocket.readLine();
					System.out.println("La suma de todos los n�meros es: " + lineaServidor);
					break;
	    	  	case 4:
					System.out.println("----Ejercicio 4: enviar n�meros entre corchetes y devuelve la suma de todos-----");
					System.out.print("Introduce los n�meros a enviar: ");
					lineaTeclado = teclado.nextLine();	 
					break;
	    	  	default:
	    	  		System.out.println("Error!, opci�n no v�lida");
	    	  		break;
	    	  }


	      }while (!lineaTeclado.equals("Adios") || ejercicio!=-1);
	      
	    } catch (IOException e) {
	    	System.out.println("IOException: " + e.getMessage());
	    }


	    salidaSocket.close();
	    entradaSocket.close();
	    teclado.close();
	    socketCliente.close();
	    
	    System.out.println("Finalizado!");
	}

}
