package start;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import presentation.*;

public class Start {
	
	public Start(){
		
	}

	public static void main(String args[]) throws SQLException, FileNotFoundException, DocumentException {
		 Instructions instr = new Instructions();
		 instr.readInstructions(args[0]);
	}
	
	
}
