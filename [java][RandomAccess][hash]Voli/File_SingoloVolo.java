package Voli;
/*
nella face di creazione di un volo verra convocata questa classe che tramite funzione prendera
codice volo,nfile,e postiperfila come attributi; cosi creera un file di nome = codice volo,
e riempira di nfile*postiperfila record "posti",
 
il posto sarà una innerclass con una funzione che andra a scrivere grazie ai dati (nfila,n.posto) 
una stringa formattata vuota (ad indicare il nome) e un carattere ad indicare se il posto è prenotato
o no
  
tale classe quando viene trovato il codice volo, carica il file con nome uguale al codice stesso 
e prende i parametri(n.file e n.postiperfila) per capire quali sono gli offset 
(cosa permessa dal fatto che la dimensione dei record è fissa)

dovra avere le funzioni di prenotazione (trova l'offeset e ci scrive nome, e cambia il flag di prenotazione)
di cancellazione (toglierà nome e prenotazione dal posto selezionato)
e quella di visualizzazione che provvedera stampare uno per uno i posti con relativo flag in una tabella
ove saranno segnate anche le coordinate, in seguito listera a schermo anche coordinata e nome in succesione per riga

*/
public class File_SingoloVolo 
{
	File_SingoloVolo() 
	{
	//creare un file di nome CodiceVolo 
	//creando N record “vuoti” (non prenotati),
	//utilizzando una stringa di inizializzazione
	//String record Blank;
	//
	}

}


