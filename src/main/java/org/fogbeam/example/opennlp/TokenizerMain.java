
package org.fogbeam.example.opennlp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.*;

public class TokenizerMain
{
	public static void main( String[] args ) throws Exception
	{
		
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		File archivo = null;
		
	    FileReader fr = null;
	    BufferedReader br = null;
		
	    FileWriter fichero = null;
        PrintWriter pw = null;
	    
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
			archivo = new File ("models/entrada.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
				
			String linea;
			String[] tokens = null;
			
	        while((linea=br.readLine())!=null)
	        	 tokens = tokenizer.tokenize(linea);
	    
	        fichero = new FileWriter("models/salida.txt");
            pw = new PrintWriter(fichero);

	        
			for( String token : tokens )
			{
				pw.println(token);
				System.out.println( token );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
			
			
			// En el finally cerramos el fichero, para asegurarnos
	        // que se cierra tanto si todo va bien como si salta 
	        // una excepcion.
	        try{                    
	            if( null != fr ){   
	               fr.close();     
	            }   
	            if( null != br ){   
		             br.close();     
		        } 
	        }catch (Exception e2){ 
	            e2.printStackTrace();
	        }
			
			
	        try {
	            // Nuevamente aprovechamos el finally para 
	            // asegurarnos que se cierra el fichero.
	            if (null != fichero)
	               fichero.close();
            } catch (Exception e3) {
               e3.printStackTrace();
            }
			
		        
			
		}
		System.out.println( "\n-----\ndone" );
	}
}
