/**
 * 
 */
package com.utility.action;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * @author salil_mulay
 *
 */
public class Mouse {

	/**
	 * These coordinates are screen coordinates as per screen resolution
	 * @param xCoord
	 * @param yCoord
	 */
	public static void move(int xCoord, int yCoord) {
		try {
		    
		    Robot robot = new Robot();
		    robot.mouseMove(xCoord, yCoord);
		} catch (AWTException e) {
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		// Keep the mouse moving periodically, to stop the system from going into standby mode 
		for (int i = 10; i <= 800; i+=10) {
			move(i, i);
			Thread.sleep(60*1000l);
		}
		
		
	}
}
