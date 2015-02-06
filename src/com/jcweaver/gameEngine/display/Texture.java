package com.jcweaver.gameEngine.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Texture{
	
	private BufferedImage b;
	private String loc;
	public Texture(String loc){
		this.loc = loc;
		
	}
	
	
	public void load(){
			try {
				if(Display.isDebug())System.out.println(Display.getApplet().getCodeBase()+loc);
				b = ImageIO.read(new URL(Display.getApplet().getCodeBase()+loc));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		
	}
	public void unload(){
		b = null;
	}
	public void draw(Graphics g,float x,float y,float w,float h, float angle){
		try{
		g.drawImage(b, (int)x, (int)y, (int)w, (int)h, null);
		}catch(Exception e){e.printStackTrace();}
	}
	
}
