package main;

import java.io.FileNotFoundException;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Flashlight flash = new Flashlight();
		
		//start!
		flash.start();
		
		//generate!
		try {
			flash.generateDRL();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
