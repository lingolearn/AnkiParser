package cscie99.team2.lingolearn.server.anki;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnkiCsvWriter {

	private static final Logger logger = LoggerFactory.getLogger(AnkiCsvWriter.class);
	public static final String USAGE = "AnkiCsvWriter ankifilename [ouputfilename]";
	
	public static void main(String[] args) {
		if( args.length == 0 || args.length > 2 ){
			System.out.println(USAGE);
			return;
		}
		
		String ankiFilename = args[0];
		AnkiConverter ankiConverter = null;
		if( args.length == 1 ){
			ankiConverter = new AnkiConverter(ankiFilename);
		}else{
			String outputFilename = args[1];
			ankiConverter = new AnkiConverter(ankiFilename, outputFilename );
		}
		
		if( ankiConverter.parseSucceeded() )
			ankiConverter.writeOutputFile();
		
		Iterator<String> statusMessageItr = ankiConverter.getStatusMessages();
		while( statusMessageItr.hasNext() ){
			String message = statusMessageItr.next();
			System.out.println(message);
		}
		
		logger.info("input file: " + ankiFilename);
			
	}

}