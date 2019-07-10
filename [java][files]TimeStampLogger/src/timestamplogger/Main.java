
package timestamplogger;

import java.io.*;


class Main {
public static void main(String[] args) 
{
	//Si tratta di un semplice menù che gestisce
String exit="n";
	
	while( exit.equals("n") )
	{
		String choiceWR="";
		
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(input);

		System.out.println("Read or Write on Logs? (r/w)");
		try{ choiceWR=keyboard.readLine();} catch(Exception e){}
 
		logIO logIO=new logIO(choiceWR);

		System.out.println("uscire? y/n");
		try{ exit=keyboard.readLine();}catch(Exception e){}
	}
}
}
