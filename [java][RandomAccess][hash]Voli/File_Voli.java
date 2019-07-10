package Voli;

import java.io.*;


public class File_Voli 
{	static String DebugPrefix="###DEBUG### ";
	InputStreamReader input = new InputStreamReader(System.in);
	BufferedReader keyboard = new BufferedReader(input);       
	File_Indici FileIndice =new File_Indici();				
	
	String fileName="Voli"+".dat";
	
	
    
    
	public void Crea() 
	{

	int n=0;
	System.out.println("quanti voli devi inserire?");
	try{n=Integer.valueOf(keyboard.readLine());}catch(Exception e){}
	
	RecordVolo ArrayVoli[]= new RecordVolo[n];
	
	System.out.println("Formato:  Codice,Destinazione,nFile,nPostiPerFila ");
	String DatiSfusi="      ,      ,      ,      ";
	for(int I=0;I<n;I++)
		{
		System.out.println("inserisci i dati del "+(I+1)+"° volo ");
		try{DatiSfusi=keyboard.readLine();}catch(Exception e) {}
		ArrayVoli[I]=new RecordVolo(DatiSfusi);
		System.out.println("codice volo registrato: "+String.valueOf(ArrayVoli[I].GetInfo()[0]));
		
		}

		try
		{   
			RandomAccessFile f =new RandomAccessFile(fileName,"rw");
			byte[] ByteBuffer;
			
			int I=0;
			for(I=0;I<n;I++)   
			{
			try	{
				
				f.seek(f.length());
				System.out.println(DebugPrefix+"il puntatore file è in posizione:"+f.getFilePointer());
				ByteBuffer=RecordVolo.Serializza(ArrayVoli[I]);
				System.out.println(DebugPrefix+"ArrayVoli["+I+"] serializzato :");
				Sharin_Tools.DiagBytesArray(ByteBuffer,"ByteBuffer");
				System.out.println("");
				FileIndice.Aggiungi
				(
				String.valueOf(ArrayVoli[I].GetInfo()[0]),//key
				f.getFilePointer(),                       //offset oggetto
				Integer.toUnsignedLong(ByteBuffer.length) //lunghezza oggetto
				);
				System.out.println("");
				System.out.println("------------------------------------------");
				System.out.println(DebugPrefix+"Aggiunto indice con valori ");
				System.out.println(DebugPrefix+"Codice volo:          "+String.valueOf(ArrayVoli[I].GetInfo()[0]));
				System.out.println(DebugPrefix+"Offset inizio record: "+f.getFilePointer());
				System.out.println(DebugPrefix+"Lunghezza record:     "+Integer.toUnsignedLong(ByteBuffer.length));
				System.out.println("------------------------------------------");
				System.out.println("");
				System.out.println(DebugPrefix+"inizio Scrittura record");
				f.write(ByteBuffer);
				System.out.println(DebugPrefix+"fine Scrittura record");
				System.out.println("");
				System.out.println("Aggiunti al file:");
				}
				catch(Exception e)
				{
				System.out.println("non riesce a scrivere dati sul file!");
				}
			}//FOR
			System.out.print(" "+I+" voli.");
			System.out.println("");
			
			//-------------------------###DEBUG###------------------------------
			for(I=0;I<n;I++)
			{
			System.out.println(DebugPrefix+" Avvio Automatico Visualizzatore");
			String CodiceVolo=ArrayVoli[I].GetInfo()[0];
			visualizza(CodiceVolo);
			}
			//------------------------###END_DEBUG###----------------------------
			
			f.close();
		}catch(Exception e){}
		
		} 
	
	public void Elimina() {}
	
	//private void ListaVoli() {}
	
	public boolean visualizza(String CodiceVolo) 
	{
		try
		{
			RandomAccessFile f = new RandomAccessFile(fileName,"r");
			RecordVolo DatiVolo=null; 
			byte[] ByteBuffer;
			int I=0; 
			long[] objCoord;
			System.out.println(DebugPrefix+"codice volo ricevuto: "+ CodiceVolo);
				try 
					{
			     	long Collision=FileIndice.Leggi(CodiceVolo,0)[2];//passaggio collisioncounter
			     	System.out.println(DebugPrefix+"n. Colisioni "+Collision );
			     	for(I=1;I<=Collision;I++)
					{
					objCoord=FileIndice.Leggi(CodiceVolo,I);
					ByteBuffer=new byte[(int)objCoord[1]];
					f.seek(objCoord[0]);
					f.read(ByteBuffer, 0,(int)objCoord[1]);
					Sharin_Tools.DiagBytesArray(ByteBuffer,"ByteBuffer Bytes da deserializzare");
					DatiVolo=RecordVolo.deserializza(ByteBuffer);
					
					System.out.println(DebugPrefix+"Valore dimensione Record: ");
			     	System.out.println(DebugPrefix+"objCoord[1]="+objCoord[1]);
			     	System.out.println(DebugPrefix+"Valore dimensione ByteBuffer: ");
			     	System.out.println(DebugPrefix+"(int)objCoord[1]="+(int)objCoord[1]);
			     	
						if(I==objCoord[2])System.out.println("Volo inesistente");
						System.out.println(DebugPrefix+"CodiceVolo: "+CodiceVolo);
						System.out.println(DebugPrefix+"DatiVolo.GetInfo()[0]: "+DatiVolo.GetInfo()[0]);
						System.out.println(DebugPrefix+"Risultato CodiceVolo.matches( DatiVolo.GetInfo()[0] )"+CodiceVolo.matches( DatiVolo.GetInfo()[0] ) );
						
						if(
							CodiceVolo.matches( DatiVolo.GetInfo()[0] ) 
						  )
						{
							System.out.println("");
							System.out.println("-------------------------------");
							System.out.println("Volo "+I);
							System.out.println("Codice: "+DatiVolo.GetInfo()[0]);
							System.out.println("Destinazione: "+DatiVolo.GetInfo()[1]);
							System.out.println("-------------------------------");
							System.out.println("");
							break;
						}
					}
					
				 	}
					catch(EOFException EOF) 
					{}
					
				
			f.close();
			return false;
		}
		catch(Exception e)	
			{
			System.out.println("file dei voli inesistente!");
		    System.out.println("Crea prima un Volo.");
		    System.out.println("Errore: "+ e);
		    return true;
			}
		}
	

}



	 
	

