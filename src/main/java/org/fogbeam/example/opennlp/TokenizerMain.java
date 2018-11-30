
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
			/**
			 * Ruta y nombre del fichero de entrada
			 */	
			archivo = new File ("models/entrada.txt");//ruta y nombre del fichero de entrada
			/**
			 * Abrimos el archivo en modo lectura
			 */	
			fr = new FileReader (archivo); //abrimos el archivo en modo lectura
			br = new BufferedReader(fr);
			/**
			 * Ruta y nombre del fichero de salida
			 */	
			fichero = new FileWriter("models/salida.txt");//ruta y nombre del fichero de salida
			/**
			 * Abrimos el fichero en modo escritura
			 */	
            pw = new PrintWriter(fichero);//abrimos el fichero en modo escritura
			
			String linea;
			String[] tokens = null;
			/**
			 * Vamos leyendo el archivo hasta el final
			 */	
	        while((linea=br.readLine())!=null) {// vamos leyendo el archivo hasta el final
	        	/**
				 * Tokenizamos linea a linea el archivo
				 */	
	        	 tokens = tokenizer.tokenize(linea);//tokenizamos linea a linea el archivo
	        	 /**
				 * Recorremos el vector de string tokenizado
				 */	
	        	 for( String token : tokens )
	 			{
	        		 /**
	 				 * Escribe el token en el fichero
	 				 */	
	 				pw.println(token);// escribe el token en el fichero
	 				System.out.println( token );
	 			}
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
	            if (null != pw)
		               pw.close();
            } catch (Exception e3) {
               e3.printStackTrace();
            }
			
		        
			
		}
		System.out.println( "\n-----\ndone" );
	}
}
