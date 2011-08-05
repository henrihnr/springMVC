package app.anttask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.jasypt.util.text.BasicTextEncryptor;

public class PasswordEncryptorTask extends Task {
	
	Log logger = LogFactory.getLog(PasswordEncryptorTask.class);
	
	private String fromFile;
	private String toFile;
	
	public String getFromFile() {
		return fromFile;
	}
	public void setFromFile(String fromFile) {
		this.fromFile = fromFile;
	}

	public String getToFile() {
		return toFile;
	}
	public void setToFile(String toFile) {
		this.toFile = toFile;
	}
	
	public void execute() throws BuildException {
		Map<String, String> mapPassword = new HashMap<String, String>();
		
		try {
			File original = new File(fromFile);
			
			if (original.isFile()) {
				BufferedReader inputStream = new BufferedReader(new FileReader(original));
				
				BasicTextEncryptor encryptor = new BasicTextEncryptor();
				encryptor.setPassword("password");
				
				String line;
				while ((line = inputStream.readLine()) != null) {
					String encrypted = encryptor.encrypt(line);
					mapPassword.put(line, encrypted);
					
					logger.debug(line + " -> " + encrypted);
				}
				
				if(new File(getToFile()).exists()) {
					new File(getToFile()).delete();
				}
				
				PrintWriter printWriter = new PrintWriter((new FileOutputStream(getToFile(), false)), true);
				
				printWriter.println("original \t encrypted");
				printWriter.println("------------------------------------");
				
				for(String password:mapPassword.keySet()) {
					printWriter.println(password + " \t " + mapPassword.get(password));
				}
				
				printWriter.close();
				
			} else {
				logger.error("Fail to read " + original.getCanonicalPath() );
			}
			
		} catch (Exception e) {
			logger.error("Exception during Password Encryption " + e.getMessage(), e );
		}
	}

}
