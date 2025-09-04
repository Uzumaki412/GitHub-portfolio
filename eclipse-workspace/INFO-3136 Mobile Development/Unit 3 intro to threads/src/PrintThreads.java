/**
 * Program Name: PrintThreads.java
 * Purpose: 
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 30, 2025
 */

public class PrintThreads extends Thread {
	
	public static void main(String[] args) {
		PrintChar charThread = new PrintChar('A', 100);
		PrintNum numThread = new PrintNum(100);
		charThread.start(); 
		numThread.start(); 
		
	}

}
//end class
