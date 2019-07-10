package Voli;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


@SuppressWarnings("serial")
public class RecordVolo implements Serializable
{   static String DebugPrefix="###DEBUG### ";
	private String Codice;
	private String Destinazione;
	private int nFile;
	private int pFila;
    

RecordVolo(String DatiSfusi)
	{
	String ArrayDati[]=new String[3]; 
	ArrayDati=StringGuard(DatiSfusi);
	this.Codice=ArrayDati[0];
	this.Destinazione=ArrayDati[1];
	this.nFile=Integer.parseInt(ArrayDati[2]);
	this.pFila=Integer.parseInt(ArrayDati[3]);
	
	}

public String[] GetInfo()
{
String[] array = {this.Codice,this.Destinazione};
return array;
}

public int[] GetSize()

{
int[] array = {this.nFile,this.pFila};
return array;
}

public static byte[] Serializza(RecordVolo Record)
        throws IOException {

    ByteArrayOutputStream streamDiByte = new ByteArrayOutputStream();
    ObjectOutputStream streamDiRecord = new ObjectOutputStream(streamDiByte);
    streamDiRecord.writeObject(Record);
    streamDiRecord.flush();

    return streamDiByte.toByteArray();
}

public static RecordVolo deserializza(byte[] byteArray)
        throws IOException, ClassNotFoundException 
	{

    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray));
    RecordVolo Record = (RecordVolo) ois.readObject();

    return Record;
	}

private String[] StringGuard(String DatiSfusi) 
	{ 
	InputStreamReader input = new InputStreamReader(System.in);
	BufferedReader keyboard = new BufferedReader(input);
	
	String[] StringArray=new String[4];
	while(true) 
	{if  (!(DatiSfusi.contains(",")))
	{
	System.out.println("Devi Scrivere i 4 campi separandoli con le virgole:");
	System.out.println("Atteso input: ");
	try{DatiSfusi=keyboard.readLine();}catch(Exception e) {System.out.println("dato non letto"+e);}
	}
	else{break;}
	}
	
	if (DatiSfusi.contains(","))
		{	
				StringArray=DatiSfusi.split(",");
				while(true) 
				{
					if	(   
						(StringArray[0].length()!=6)
					) 
					{
					System.out.println("Il codice deve essere di 2 lettere iniziali e 4 numeri finali:");
					System.out.println("Atteso input: ");
					try{StringArray[0]=keyboard.readLine();}catch(Exception e) {System.out.println("dato non letto"+e);}
					}
				else{break;}
				}
				
				while(true) 
				{
					if ( StringArray[1].isEmpty() )
			 		{
					System.out.println("Devi inserire una destinazione");
					System.out.println("Atteso input: ");
					try{StringArray[1]=keyboard.readLine();}catch(Exception e) {System.out.println("dato non letto"+e);}
			 		}
				else{break;}
				}
				
				while(true) 
				{
					if	(   
						( !Sharin_Tools.isNumeric(StringArray[2]) )||
						( StringArray[2].length()>3               )||
						( StringArray[2].startsWith("0")          )||
						( StringArray[2].isEmpty()                )
					)
					{
					System.out.println("Il terzo campo deve essere un numero compreso tra 0 e 1000 esclusi:");
					System.out.println("Atteso input: ");
					try{StringArray[2]=keyboard.readLine();}catch(Exception e) {System.out.println("dato non letto"+e);}
					}
				else{break;}
				}
				
				
				while(true) 
				{
				if	(   
						( !Sharin_Tools.isNumeric(StringArray[3]) )||
						( StringArray[3].length()>3               )||
						( StringArray[3].startsWith("0") 		  )||
						( StringArray[3].isEmpty()                )
					)
					{
					System.out.println("Il Quarto campo deve essere un numero compreso tra 0 e 1000 esclusi:");
					System.out.println("Atteso input: ");
					try{StringArray[3]=keyboard.readLine();}catch(Exception e) {System.out.println("dato non letto"+e);}
					}
				else{break;}
				}
				
			}
	return StringArray;
	}
}
