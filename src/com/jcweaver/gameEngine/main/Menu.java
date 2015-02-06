package com.jcweaver.gameEngine.main;

import java.awt.Graphics;

import com.jcweaver.gameEngine.display.Display;
import com.jcweaver.gameEngine.display.Texture;
import com.jcweaver.gameEngine.display.Window;
import com.jcweaver.gameEngine.game.Scene;
import com.jcweaver.gameEngine.util.Mouse;

public class Menu extends Scene{
	Texture bg;
	public void init() {
		bg = new Texture("com/jcweaver/gameEngine/main/brick.jpg");
		bg.load();
		Window.setVerticalMeters(10);
		System.out.println(Window.getScale());
	}

	public void update(double delta) {
		//System.out.println(Mouse.getX()+", "+Mouse.getY());

	}

	public void render(Graphics g) {
		Window.draw(g, bg, Window.getMX()-1.25f,Window.getMY()+1.25f,2.5f,2.5f,0);
		
	}

}
