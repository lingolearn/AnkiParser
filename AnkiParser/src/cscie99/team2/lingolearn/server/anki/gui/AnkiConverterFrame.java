package cscie99.team2.lingolearn.server.anki.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cscie99.team2.lingolearn.server.anki.AnkiConverter;

public class AnkiConverterFrame extends JFrame implements ActionListener {
	
	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 400;
	public static final int TEXT_FLD_WIDTH = 20;
	public static final String WINDOW_TITLE = "LingoLearn Anki File Converter";
	public static final String BROWSE_TEXT = "Browse";
	public static final String PARSE_BUTTON_TEXT = "Parse";
	public static final String INPUT_LABEL = "Anki File";
	public static final String OUTPUT_LABEL = "CSV Output File";
	
	private JFileChooser inputFileBrowser;
	private JLabel inputFileLabel;
	private JLabel outputFileLabel;
	private JTextField inputFile;
	private JTextField outputFile;
	private JButton inputBrowseButton;
	private JButton outputBrowseButton;
	private JButton parseButton;
	private JTextArea console;
	private JPanel inputControls;
	private JPanel outputControls;
	private JPanel actionControls;
	
	public AnkiConverterFrame(){
		
		//Set basic JFrame attributes
		setTitle(WINDOW_TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        initializeControls();

        // Add Controls to Widget layout
        setLayout(new BorderLayout());
        inputControls.add(inputFileLabel);
        inputControls.add(inputFile);
        inputControls.add(inputBrowseButton);
        outputControls.add(outputFileLabel);
        outputControls.add(outputFile);
        outputControls.add(outputBrowseButton);
        actionControls.add(parseButton);
        add(inputControls, BorderLayout.PAGE_START );
        add(outputControls, BorderLayout.CENTER);
        add(actionControls, BorderLayout.EAST);
        add(console, BorderLayout.PAGE_END);
        
        
        setVisible(true);
	}
	
	/**
	 * This method handles events from controls that
	 * use this class as an action listener.
	 */
	@Override
	public void actionPerformed(ActionEvent event){
		if( event.getSource() == inputBrowseButton ){
			browseForAnkiFile();
		}else if( event.getSource() == parseButton ){
			convertAnkiFile();
		}
	}
	
	/*
	 * Helper method to conver the Anki file to a CSV file.
	 * The conversion is performed with the AnkiConverter class.
	 */
	private void convertAnkiFile(){
		String inputFilename = inputFile.getText().trim();
		if( inputFilename.isEmpty() ){
			console.append("You must specify an anki file.");
		}else{
			AnkiConverter converter = new AnkiConverter(inputFilename);
			if( converter.parseSucceeded() ){
				converter.writeOutputFile();
				Iterator<String> messageItr = converter.getStatusMessages();
				while( messageItr.hasNext() ){
					String message = messageItr.next();
					console.append(message);
				}
			}else{
				console.append("Parsing Anki file failed.");
			}
		}
	}
	
	/*
	 * Helper method to allow widget to browse the file system for
	 * an input Anki file.
	 */
	private void browseForAnkiFile(){
		int returnVal = inputFileBrowser.showOpenDialog(AnkiConverterFrame.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = inputFileBrowser.getSelectedFile();
            //This is where a real application would open the file.
            this.inputFile.setText(file.getAbsolutePath());
            String newOutFilename = this.outputFile.getText().trim();
            if( newOutFilename.isEmpty() ){
            	newOutFilename = file.getAbsolutePath() + "_output.csv";
            	this.outputFile.setText(newOutFilename);
            }
        } else {
        	
        }
	}
	
	/*
	 * Helper method to initialize the control elements
	 * in this Widget.
	 */
	private void initializeControls(){
		
        inputFile = new JTextField(TEXT_FLD_WIDTH);
        outputFile = new JTextField(TEXT_FLD_WIDTH);
        inputBrowseButton = new JButton(BROWSE_TEXT);
        inputBrowseButton.addActionListener(this);
        outputBrowseButton = new JButton(BROWSE_TEXT);
        parseButton = new JButton(PARSE_BUTTON_TEXT);
        parseButton.addActionListener(this);
        inputFileLabel = new JLabel( INPUT_LABEL );
        outputFileLabel = new JLabel( OUTPUT_LABEL );
        console = new JTextArea(10, 1);
        inputFileBrowser = new JFileChooser();
        inputControls = new JPanel();
        outputControls = new JPanel();
        actionControls = new JPanel();
	}
	
	/**
	 * Main method to initialize this Widget.
	 * @param args Command Line arguments
	 */
	public static void main( String args[] ){
		
		@SuppressWarnings("unused")
		AnkiConverterFrame ankiConverter = new AnkiConverterFrame();
		
	}

}
