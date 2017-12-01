package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveAndLoad {
	public static void main(String[] args) {
		TestObjects testObj = new TestObjects();
		System.out.println(testObj.toString());
		
		System.out.println("Saving Objects");
		save(testObj);
		System.out.println("Loading Objects");
		load();
		
	}
	
	public static final String filename = "TestOutput.sav";
	
	public static void save(Serializable objectsToSerialise) {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("TestOutput.sav");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objectsToSerialise);
			oos.flush();
			oos.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		if(checkFileExists()) {
			FileInputStream fis = null;
			
			try {
				
				fis = new FileInputStream(filename);
				
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				TestObjects loadObject = (TestObjects) ois.readObject();
				
				ois.close();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean checkFileExists() {
		
		return new File(filename).isFile();
	}
	}
