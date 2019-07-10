package Voli;

import java.io.RandomAccessFile;
import java.io.Serializable;

@SuppressWarnings("serial")
public class File_Indici implements Serializable
{
static String DebugPrefix="###DEBUG### ";	
RandomAccessFile f;
long CollisionCounter;
static int  ByteField_Collision=8;
static long ByteField_ObjOffset=8;
static long ByteField_ObjLenght=8;//+1 implementare collisions, 
static long ByteRecordSize=ByteField_ObjOffset+ByteField_ObjLenght;
static int  BlockSize=100;
static int headersize="header".length();
static long ByteBlockSize=BlockSize*ByteRecordSize;

//static long ByteBlockSize=header+ByteRecordSize*BlockSize;//
//static long headersize=DIMENSIONE in BYTE della primitiva usata per l'header


public void CreateBlock(long SelectedBlock) 
{	
	System.out.println("");
	System.out.println			  (DebugPrefix+" ________________________________ ");
	System.out.println(DebugPrefix+"| Creazione blocco a Indici.hash |");
	System.out.println			  (DebugPrefix+"|________________________________|");
	try{RandomAccessFile f =new RandomAccessFile("Indici.hash","rw");
	long blockOffset=ByteBlockSize*SelectedBlock;
	f.seek(blockOffset);
	System.out.println			  (DebugPrefix+"|alla posizione: |"
	+Sharin_Tools.Formatta(String.valueOf(f.getFilePointer()),15)+"|");
	System.out.println			  (DebugPrefix+"|________________|_______________|");
	System.out.println			  (DebugPrefix+"                                  ");
	System.out.println			  (DebugPrefix+"numero blocco: "+SelectedBlock);

	for(long I=0;I<ByteBlockSize;I++) 
		{
		
		f.seek(blockOffset+I);
		f.writeByte(0);
		
		if( (I==0)||(I==ByteBlockSize-1) )
			{
			System.out.println
			(DebugPrefix+" scritto "+String.valueOf((I))+"° Byte "
			+ "alla posizione n. "+String.valueOf(f.getFilePointer()));
			}
		}
	
	if(SelectedBlock==0) 
	{	
		System.out.println			  (DebugPrefix+"Creazione contatori collisioni");
		System.out.println			  (DebugPrefix+"Blocco n: "+SelectedBlock);
		
		for(int I=0;I<BlockSize;I++) 
		{
			long CollisionFieldOffset=I*(ByteRecordSize);
			f.seek(CollisionFieldOffset);
			f.writeLong(1);
		
			if( (I==0)||(I==ByteBlockSize-1) )
			{
				System.out.println			(DebugPrefix+"Puntatore a"+f.getFilePointer());
				System.out.println			(DebugPrefix+"Scrittura valore collisioni (Long): 1");
			}
			
			if(I==(BlockSize-1)) 
			{	
				System.out.println		  (DebugPrefix+"Record scritti"+I);
				System.out.println		(DebugPrefix+"Su un blocco di dimensioni: "+ByteBlockSize+" Bytes");
				System.out.println		(DebugPrefix+"FINE FOR");
			}
		}
	}
	
	f.close();
	}
	catch(Exception e) {System.out.println("problema creazione blocco");}
	System.out.println(DebugPrefix+" ________________________________ ");
	System.out.println(DebugPrefix+"|Fine Creazione blocco           |");
	System.out.println(DebugPrefix+"|________________________________|");
	System.out.println("");
}

public void Aggiungi(String IDcode,long OffsetObject,long ObjectLenght)
{	
	System.out.println("funzione di accodamento indici inizializzata...");
	System.out.println("Registrazione Posizione Record codice: "+IDcode+" --->");
	long offset=calcOffset(IDcode);
	
	long selectedblock;
	try{System.out.println("");
		RandomAccessFile f =new RandomAccessFile("Indici.hash","rw");
		
		System.out.println(DebugPrefix+" ________________________________ ");
		System.out.println(DebugPrefix+" verifica presenza blocco 0:");
		System.out.println(DebugPrefix+ f.length()+"<"+ByteBlockSize+"= "+ (f.length()<ByteBlockSize));
		if(f.length()<ByteBlockSize) 
		System.out.println(DebugPrefix+"Creazione Blocco 0");
		else
		System.out.println(DebugPrefix+"Blocco 0 presente");
		System.out.println(DebugPrefix+" ________________________________ ");
		if(f.length()<ByteBlockSize) 
		{
			this.CreateBlock(0);
		}
		f.seek(offset);
		CollisionCounter=f.readLong();
		selectedblock=CollisionCounter;
		f.writeLong(CollisionCounter+1);
		if(f.length()<selectedblock*ByteBlockSize)
			this.CreateBlock(selectedblock);
		
		f.seek(selectedblock*BlockSize);
		offset=ByteBlockSize*selectedblock+offset;
		
		f.seek(offset);
		System.out.println(DebugPrefix+"puntatore file a posizione: "+ f.getFilePointer());
		f.writeLong(OffsetObject);
		System.out.println(DebugPrefix+"Scrittura valore Long: \""+OffsetObject+"\"");
		f.seek(offset+8); 
		System.out.println  (DebugPrefix+"");
		System.out.println(DebugPrefix+"puntatore file a posizione: "+ f.getFilePointer());
		f.writeLong(ObjectLenght);
		System.out.println(DebugPrefix+"Scrittura valore Long: \""+ObjectLenght+"\"");
		System.out.println(DebugPrefix+"puntatore file a posizione: "+ f.getFilePointer());
		f.close();
		System.out.println("");
		}
		catch(Exception e) {	System.out.println(e);}
	}

public long[] Leggi(String IDcode,int SelectedBlock )
	{	
		long[] objectCoord= new long[3];
		long offset=calcOffset(IDcode);
		
		try {
			RandomAccessFile f =new RandomAccessFile("Indici.hash","r");
			System.out.println("");
			System.out.println(DebugPrefix+"lunghezza file: "+f.length());
			System.out.println("");
			if(SelectedBlock==0) 
			{
			f.seek(offset);
			objectCoord[2]=f.readLong();
			}
			offset=SelectedBlock*ByteBlockSize+offset;
			f.seek(offset);
			System.out.println(DebugPrefix+"puntatore file a posizione: "+ f.getFilePointer());
			objectCoord[0]=f.readLong();
			System.out.println(DebugPrefix+"Lettura valore Long: '"+objectCoord[0]+"'");
			System.out.println("");
			f.seek(offset+8);
			System.out.println(DebugPrefix+"puntatore file a posizione: "+ f.getFilePointer());
			objectCoord[1]=f.readLong();
			System.out.println(DebugPrefix+"Lettura valore Long: '"+objectCoord[1]+"'");
			System.out.println("");
			System.out.println("");
			
			f.close();
			}
		catch(Exception e) 	
			{
			
			}
		return objectCoord;
		}

private static long calcOffset(String IDcode)
	{
	System.out.println("");
	long index=Integer.toUnsignedLong(IDcode.hashCode())%BlockSize;
	System.out.println  (DebugPrefix+"________________________________ ___ ___ ___ ___");
	System.out.println	(DebugPrefix+"Calcolo indice di un blocco di dimensioni "+BlockSize+" Record");
	System.out.println	(DebugPrefix+"Integer.toUnsignedLong(IDcode.hashCode()) % BlockSize= "
						+Integer.toUnsignedLong(IDcode.hashCode())+" % "+BlockSize+" = index = "+index);
	System.out.println  (DebugPrefix+"________________________________ ___ ___ ___ ___");					
	long offset=index*ByteRecordSize; //
	System.out.println	(DebugPrefix+"Calcolo offset in byte: ");
	System.out.println	(DebugPrefix+"index*ByteRecordSize = "+index+" * "+ByteRecordSize+" = offset = "+offset);
	System.out.println  (DebugPrefix+"________________________________ ___ ___ ___ ___");
	System.out.println	("");
	return offset;
	}

}
