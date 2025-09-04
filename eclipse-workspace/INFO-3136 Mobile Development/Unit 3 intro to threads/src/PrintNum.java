/**
 * Program Name: PrintChar.java
 * Purpose:  create a thread object that prints a character 100 times 
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 30, 2025
 */

public class PrintNum extends Thread {

	private int lastNum;
	
	public PrintNum(int _lastNum) {
		this.lastNum = _lastNum;
	}
	
	public void run() {
		for (int i = 0; i < lastNum; i++) {
			System.out.print(i + " ");
		}
	}
}
//end class
