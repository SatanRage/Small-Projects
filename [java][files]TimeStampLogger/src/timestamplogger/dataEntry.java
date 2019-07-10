package timestamplogger;

import java.io.*;

 class dataEntry implements Serializable
{String ID,IP,TS;
 dataEntry(){}
 dataEntry(String ID,String IP,String TS)
 {
 this.ID=ID;
 this.IP=IP;
 this.TS=TS;
 }

}
