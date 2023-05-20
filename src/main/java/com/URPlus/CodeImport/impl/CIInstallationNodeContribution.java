package com.URPlus.CodeImport.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import javax.management.NotificationFilterSupport;

import org.omg.CORBA.ExceptionList;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.InstallationAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class CIInstallationNodeContribution implements InstallationNodeContribution {

	private final InstallationAPIProvider apiProvider;
	private final DataModel model;
	private final CIInstallationNodeView view;
	private static final String NO_DATA_KEY = "# No data read";
	private File dest_sim = new File("/home/ur/ursim/ursim-5.11.0.108249/programs/ci_user.script");
	private File dest_real = new File("/programs/ci_user.script");
	public CIInstallationNodeContribution(InstallationAPIProvider apiProvider, CIInstallationNodeView view,
			DataModel model, CreationContext context) {
		// TODO Auto-generated constructor stub
		this.apiProvider = apiProvider;
		this.model = model;
		this.view = view;
	}

	@Override
	public void openView() {
		// TODO Auto-generated method stub

		try {
			copyFile(dest_sim);
			System.out.println("placed ci_user.script into /simulator/programs folder...done!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("placing ci_user.script... failure!");
		}
		try {
			copyFile(dest_real);
			System.out.println("placed ci_user.script into programs folder...done!");
		} catch(Exception e) {
			System.out.println("placing ci_user.script... failure!");
		}
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		try { //read from real cobot folder
			if (!readScriptFile("/programs/ci_user.script").equals(NO_DATA_KEY)) {
				writer.appendRaw(readScriptFile("/programs/ci_user.script"));
				System.out.println("read from physical cobot...done!");
			}
		} catch (Exception e) {
			System.out.println("read from physical cobot...failure!");
		}
		try { // read from simulator folder
			if (!readScriptFile("/home/ur/ursim/ursim-5.11.0.108249/programs/ci_user.script").equals(NO_DATA_KEY)) {
				writer.appendRaw(readScriptFile("/home/ur/ursim/ursim-5.11.0.108249/programs/ci_user.script"));
				System.out.println("read from ursim...done!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("read from ursim...failure!");
		}
		
	}
	private void copyFile(File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = CIInstallationNodeContribution.class.getResourceAsStream("/script/ci_user.script");
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		}finally {
			input.close();
			output.close();
		}
	}
	private String readScriptFile(String filepath){
		try {
			File file = new File(filepath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			
			System.out.println("Read the file: "+filepath);
			System.out.println("The content was:");
			System.out.println(stringBuffer.toString());
			
			return stringBuffer.toString();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
		return NO_DATA_KEY;
	}

}