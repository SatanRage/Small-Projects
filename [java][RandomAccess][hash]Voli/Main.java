package Voli;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main 
{
static String DebugPrefix="###DEBUG### ";
static boolean Debug;
@SuppressWarnings("unused")
public static void main(String[] args) 
{
/*creazione/scrittura voli.dat
un menu
-creazione di un volo
-prenotazione volo
	-prenotazione posto
	-anullamento prenotazione
	-stato volo
	-back
-uscita*/
InputStreamReader input = new InputStreamReader(System.in);
BufferedReader keyboard = new BufferedReader(input);

String exit="n";
int choice=0;

File_Voli DatiVolo=new File_Voli();

while( exit.equals("n") )
{
System.out.println("1]crea volo");
System.out.println("2]elimina volo");
System.out.println("3]prenota volo");
System.out.println("0]esci");
try {choice=Integer.parseInt(keyboard.readLine());}catch(Exception e) {}
	switch(choice) 
	{
	 case 0: exit="y";
	 		break;
	 case 1: DatiVolo.Crea();;
		 	break;
	 case 2:/*DatiVolo.Elimina()*/
	 case 3:
		  	//*-------------------------###DEBUG###------------------------------
		 	System.out.println(DebugPrefix+" inserisci codice volo precedente:");
	 		String CodiceVolo="Default";
	 		try{CodiceVolo=keyboard.readLine();}catch(Exception e) {}
		 	
		 	if(DatiVolo.visualizza(CodiceVolo))break;
		 	//------------------------###END_DEBUG###----------------------------*/
		    /*	System.out.println("codice volo?"); //WORK IN PROGRESS
		      		 	String CodiceVolo="Default";
		     
		 		try{CodiceVolo=keyboard.readLine();}catch(Exception e) {}
		 	 	if(DatiVolo.cerca(CodiceVolo));
		 	   		{File_singoloVolo Prenotazioni= new File_singoloVolo;}
		 	   			else
		 	   			{System.out.println("volo non presente");break;}
		 	   
		 	*/
		 	
		 		System.out.println("1]prenota posto");
		 		System.out.println("2]annulla prenotazione");
		 		System.out.println("3]visualizza prenotazioni");
		 		System.out.println("0]back");
		 	
		 		try {choice=Integer.parseInt(keyboard.readLine());}catch(Exception e) {}
		 		
		 			switch(choice)
		 			{
		 			case 0:break;
		 			case 1:/*Prenotazioni.prenota()*/break;
		 			case 2:/*Prenotazioni.cancella()*/break;
		 			case 3:/*Prenotazioni.visualizza()*/break;
		 			
		 		} 
		 	break;
	 
	}
		
}
		
}

}
