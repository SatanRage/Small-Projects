package timestamplogger;
import java.io.*;
public class logIO 
{
//inizializzazione input da tastiera
InputStreamReader input = new InputStreamReader(System.in);
BufferedReader keyboard = new BufferedReader(input);

logIO(String choiceWR) 
{
	switch(choiceWR)
	{
	case "w":this.writer();break;
	case "r":this.browser();break;
	default :break;
	}
}

private void writer()
{
int n=0;

	System.out.println("quanti timestamp devi inserire?");
	try{n=Integer.valueOf(keyboard.readLine());}catch(Exception e){}

	String EntryArray[]=new String[2];
	dataEntry dataEntry[]= new dataEntry[n];
	for(int I=0;I<n;I++)
		{
		System.out.println("inserisci il "+(I+1)+"° time stamp <ID,IP,TS>");
		try{ EntryArray=keyboard.readLine().split(","); } catch(IOException e){}
		dataEntry[I]= new dataEntry(EntryArray[0],EntryArray[1],EntryArray[2]);
		}


	try
	{   
	System.out.print("nome file: ");
    String fileName=keyboard.readLine();
    System.out.println(fileName);
    fileName=fileName+".dat";
    System.out.println(fileName);
    
    FileOutputStream f = new FileOutputStream(fileName,true);
    ObjectOutputStream fOUT = new ObjectOutputStream(f);
    System.out.print("file creato con ");
    
    int I=0;
    try	{
    	for(I=0;I<n;I++){fOUT.writeObject(dataEntry[I]);}
    	}
    catch(Exception e)
    	{
    	System.out.println("non riesce a scrivere dati sul file!");
    	}
    
    System.out.print((I)+" time stamp registrati");
    fOUT.flush();
    fOUT.close();
	}catch(Exception e){}
}
private void browser() 
{
boolean FileNotFound=false;

try{
	do{
    try {
        System.out.println("digitare Il nome del file  ");
        String fileName=keyboard.readLine()+".dat";
        
        FileInputStream f = new FileInputStream(fileName);
        ObjectInputStream fIN = new ObjectInputStream(f);
        
        FileNotFound=false;
        System.out.println("File Trovato!");
        
        dataEntry userdata = new dataEntry();
        dataFinder search=new dataFinder();
        userdata=search.finder(fIN);
        
        System.out.println(userdata.ID+","+userdata.IP+","+userdata.TS+" timestamp trovato");
        }
    	catch(FileNotFoundException e)
        {
        System.out.println("file non trovato riprova");
        FileNotFound=true;
        } 
    	catch (IOException e) {}
	}while(FileNotFound);





}
catch(Exception e){System.out.println("ciccia");}

}




}
//REMEMBER effetuare casting dal file con (dataEntry)

