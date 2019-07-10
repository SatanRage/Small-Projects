
package timestamplogger;

import java.io.*;
import java.util.*;

public class dataFinder 
{
InputStreamReader input = new InputStreamReader(System.in);
BufferedReader keyboard = new BufferedReader(input);


public dataEntry finder(ObjectInputStream fIN) throws IOException, ClassNotFoundException 
{
	
int vecCap=1;
String temp_IP="0";

dataEntry temp__Object= new dataEntry();
Vector SameIP_array =new Vector(1,1);

System.out.println("inserire l'ip per vedere il timestamp"); 
try{temp_IP=keyboard.readLine();}catch(Exception e){}
	while(true)
	{
		try
		{
		temp__Object=(dataEntry)fIN.readObject();
				if(temp_IP.equals(temp__Object.IP))
				{
				SameIP_array.add(temp__Object);//conserva l'oggetto
				vecCap++;
				SameIP_array.ensureCapacity(vecCap);
				}
		}
		catch(EOFException e)
		{
		if(SameIP_array.isEmpty())
			{
			System.out.println("ip non trovato");
			}
		if(SameIP_array.capacity()==1)	
			{
			return (dataEntry)SameIP_array.firstElement();
			}
		else
			{
			IDfinder searchID=new IDfinder( SameIP_array);
			return searchID.find();
			}
		}        
	}   
}

}
  
class IDfinder
 {
  Vector SameIP_array;
  InputStreamReader input = new InputStreamReader(System.in);
  BufferedReader keyboard = new BufferedReader(input);
 
  IDfinder(Vector SameIP_array)
  {
     this.SameIP_array=SameIP_array;
  }
 
 public dataEntry find()
 	{
	 int I=0;
	 String temp_ID="0";
	 String exit="n";
	 dataEntry temp__Object= new dataEntry();
	 while(true){
		 		System.out.println("inserire l'id per vedere il timestamp"); 
		 		try{temp_ID=keyboard.readLine();}catch(Exception e ){}
		 		for(I=0;I<SameIP_array.size();I++)
		 			{
		 			temp__Object=(dataEntry)SameIP_array.elementAt(I);
		 			if(temp_ID.equals(temp__Object.ID))
		 				{
		 				return (dataEntry)SameIP_array.elementAt(I);
		 				}
		 			}
		 		System.out.println("ID non trovato riprovare? y/n");
		 		try{exit=keyboard.readLine();}catch(Exception e){}
		 		if(exit.equals("n"))
		 			{
		 			break;    
		 			}
     			}
	 return null;
 	}
 }
////////////////////////////////////////////////////////////

