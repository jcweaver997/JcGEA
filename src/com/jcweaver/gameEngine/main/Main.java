package com.jcweaver.gameEngine.main;
import javax.swing.JApplet;

import com.jcweaver.gameEngine.display.Display;

public class Main extends JApplet{
	
	
	public void init(){
		new Display(this);
		
		Display.addScene(new Menu());
		Display.create();
		
		
		
	}
	
	
	

}
