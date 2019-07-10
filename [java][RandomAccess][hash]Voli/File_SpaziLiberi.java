package Voli;
/*
soluzione al Problema riscontrabile dopo il probabile sviluppo dell'opzione "elimina volo"

Classe necessaria per evitare di ingrandire continuamente il file quando c'è dello spazio contiguo libero
inutilizzato, ponendo come alternativa 
lo scrivere negli spazi liberi invece di accodare
continuamente gli oggetti serializzati

In fase di cancellazione di un oggetto serializzato dal File Voli,
questa classe dovra registrare
offeset e lunghezza di tale record cancellato nel file SpaziLiberi poi
dovra ordinare ai file indice di cancellare (scrivendoci zeri) gli indici dell'oggetto da cancellare

tale classe dovra essere chiamata in causa alla scrittura
di un nuovo file, in quanto, prendera le dimensioni dell'oggetto serializzato 
e confrontera se c'è uno spazio libero di dimensioni sufficienti per tale oggetto, 
in caso positivo restituira l'offeset dove scrivere l'oggetto
e la classe File_Voli dovra utilizzare questo offset al posto di accodare l'oggetto serializzato

##########################SCARTATO##########################
facoltativo: prima di registrare lo spazio vuoto, andra a verificare quanto questo spazio vuoto sia distante 
da un'altro precendemente registrato; in tal caso se è distante MAX distanza arbitraria, provvedera ad rilevare
se è presente un'oggetto tra tali spazi,(deserializzandolo)
(tramite un test ancora da definire,vedi sotto)
e verificare il suo codicevolo, affinche possa spostarlo in una locazione libera (verso l'inizio del file),
chiamare in causa il gestore degli indici(serve una nuova funzione "modifica"), 
comunica la nuova locazione, salva, riprende in buffer di byte l'oggetto serializzato e lo slitta per lo spazio 
vuoto rilevato prima, prende l'offeset piu grande, ci sottrae la lunghezza dello spazio libero con l'offeset minore
e imposta il risultato come nuovo offeset, e la somma delle lunghezze come nuova lunghezza, elimina i dati dello 
spazio libero piu vecchio e aggiunge questi nuovi dati al suo posto

ultra-facoltativo: per capire quando tra due spazi ci possa essere un'oggetto possiamo riservare uno spazio
 nel file degli spazi liberi una variabile "dimensione max oggetto" che man mano che nella nostra funzione
passano oggetti serializzati, la nostra classe possa leggere confrontare e scrivere la massima dimensione 
di un'oggetto riscontrata durante l'esecuzione delle sue funzioni,usando questa euristica
 potremmo anche rendendere riutilizzabile tale codice 
 in programmi terzi che trattano oggetti di diversa natura
 #####################FINE_SCARTATO##########################
 
 EDIT: le ultime due soluzioni per rasoio di occam vanno scartate, 
 per evitare letture di serie di byte ambigue e test a 
 tentativi, si potrebbe in fase di scrittura dei record predisporre 
 un determinato numero di 0 prima del record da scrivere; affinche siano riconosciuti in maniera
 inequivoca l'inizio e la fine degli oggetti serializzati, consentendo anche la lettura sequenziale 
 nel caso servisse a questi ultimi
 
 tip: se è presente il requisito di lettura per ordine di inserimento, salta fuori la necessita 
 di dover inserire un record (sempre spaziati inseriti tra due oggetti), 
 che possa indicare l'offset del prossimo record da leggere, 
 in contesto di normale accodamento tali record indicheranno sempre l'oggetto succesivo
 la magia avviene quando un record viene cancellato, 
 o un nuovo record viene inserito in uno spazio contiguo
 ciò andra a riscrivere l'offset del record cancellato con
  l'offset di quello succesivo nel primo caso;
 nel secondo caso il record "goto" accodato
  andra a indicare l'offset dell'oggetto nuovo 
 (che sara posizionato nello spazio contiguo) e nel record "goto" che segue quest'ultimo 
 andra scritto l'offset della fine del file 
 (cosi puo ritornare nella lettura della fine del file o di un'altro goto che portera a un'altro posto
 e ritornera dopo oppure di un oggetto futuro)
 p.s. : si assomiglia sorprendentemente a come funziona una lista fatta coi puntatori in C
*/
public class File_SpaziLiberi {

}
