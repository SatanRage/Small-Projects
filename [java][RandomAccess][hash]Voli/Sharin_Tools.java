package Voli;

public class Sharin_Tools {
	
 static public void DiagBytesArray(byte[] Array,String ArrayName) 
	{	
	 	System.out.println("");
	 	System.out.println("");
	 	System.out.println("------------------------------------------------------------");
	 	System.out.println("#Sharin_Tools Class -Strumento di dignostica Array di bytes#");
	 	System.out.println("------------------------------------------------------------");
	 	System.out.println("");
		System.out.println("Nome Array: "+"\""+ArrayName+"\""+" Dimensioni: "+Array.length);
		System.out.println("Caricamento ByteArray...");
		try{	System.out.println("   ____ ____ ____ ____ ");
				for(int i=0,n=4;i<Array.length;i=i+n)
				{
				if((i+n)>=(Array.length)) {n=Array.length-i;}
				if(n>=1)	{System.out.print("->|"+Formatta(String.valueOf(Array[i]  ) , 4));}
					else 	{System.out.print("|"+Formatta(String.valueOf("") , 4));}
				if(n>=2)	{System.out.print("|"+Formatta(String.valueOf(Array[i+1]) , 4));}
					else 	{System.out.print("|"+Formatta(String.valueOf("") , 4));}
				if(n>=3)	{System.out.print("|"+Formatta(String.valueOf(Array[i+2]) , 4));}
					else 	{System.out.print("|"+Formatta(String.valueOf("") , 4));}
				if(n>=4)	{System.out.print("|"+Formatta(String.valueOf(Array[i+3]) , 4));}
					else 	{System.out.print("|"+Formatta(String.valueOf("") , 4));}
				System.out.print(	"|->"+" indice array:"+i+"-"+(i+(n-1)));
				System.out.println("");
				System.out.println("  |____|____|____|____|");
				}
		System.out.println("");		
		System.out.println("ByteArray Letto senza Errori");
		}
		catch(Exception e) {System.out.println("Errore ciclo For: "+e);}
		System.out.println("");
	 	System.out.println("------------------------------------------------------------");
	 	System.out.println("#Sharin_Tools Class -Strumento di dignostica Array di bytes#");
	 	System.out.println("#                FINE della DIAGNOSTICA                    #");
	 	System.out.println("------------------------------------------------------------");
	 	System.out.println("");
	}
 
 	public static String Formatta(String str, int num) 
 	{
     return String.format("%1$-" + num + "s", str);
 	}
 	
 	public static boolean isNumeric(String str)  
 	{  
 	  try  
 	  {  
 	    double d = Double.parseDouble(str);  
 	  }  
 	  catch(NumberFormatException nfe)  
 	  {  
 	    return false;  
 	  }  
 	  return true;  
 	}
 	
 	
}
