package tp1.p2.logic;

import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;

public class Record {
	
	private  int recordEasy;
	private  int recordHard;
	private  int recordInsane;
	private String nombreTXT;

	public Record(String txt){
		this.nombreTXT = txt;
	}
	
	public void cargar() throws GameException {
		BufferedReader inChars = null;
		try {
			try{
				inChars = new BufferedReader( new FileReader(nombreTXT));
				String l;
				String[] f;
				
				while((l = inChars.readLine()) != null ) {
					
					f = l.trim().split("\\s+");
					
					if(f[0].equals("EASY:"))
						recordEasy =  Integer.parseInt(f[1]);
					else if(f[0].equals("HARD:"))
						recordHard =  Integer.parseInt(f[1]);
					else
						recordInsane =  Integer.parseInt(f[1]);
				}
			} finally {
				 if (inChars != null) { 
					 inChars.close(); 
					 	}
				 
				 } 
		}catch(IOException io) {
			throw new RecordException(io.getMessage() + " " + Messages.RECORD_READ_ERROR, io);
	    } 
	}
	
    public void guardar(int puntuacion, String level) throws GameException {
    	PrintWriter out = null;
    	
    	if(level.equals("EASY") && recordEasy < puntuacion)
			recordEasy = puntuacion;
		else if(level.equals("HARD") && recordHard <  puntuacion)
			recordHard =  puntuacion;
		else if(level.equals("INSANE") && recordInsane <  puntuacion)
			recordInsane = puntuacion;
    	
	    try {
    		try {
	    		out = new PrintWriter( new OutputStreamWriter( new FileOutputStream(nombreTXT), "UTF-8"));
	    		 out.format("EASY: %s%s",recordEasy,Messages.LINE_SEPARATOR); 
	    		 out.format("HARD: %s%s",recordHard,Messages.LINE_SEPARATOR); 
	    		 out.format("INSANE: %s",recordInsane); 
	    	
			}  finally {
				 if (out != null)  
					 out.close(); 				
	    	}
	    }catch(IOException io) {
			throw new RecordException(io.getMessage() + " " + Messages.RECORD_WRITE_ERROR, io);
	    }  
	}
    
    
    public int gettextlevel(String level) {
    	int aux = 0;
    	if(level.equals("EASY"))
    		aux = this.recordEasy;
    	else if(level.equals("HARD"))
    		aux = this.recordHard;
    	else  if(level.equals("INSANE"))
    		aux = this.recordInsane;
    	
    	return aux;

    }
    
    
    
    
    
    
    
}
