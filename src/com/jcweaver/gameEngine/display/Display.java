package com.jcweaver.gameEngine.display;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JApplet;

import com.jcweaver.gameEngine.game.Scene;
import com.jcweaver.gameEngine.util.Keyboard;
import com.jcweaver.gameEngine.util.Mouse;

public class Display {

	private static JApplet frame;
	private static GraphicsConfiguration gc;
	private static VolatileImage vimg;
	private static ArrayList<Scene> scenes;
	private static int curScene;
	private static double inverseFPS = 1/60.0;
	private static boolean isActive,isCreating,debug;
	private static Color clearColor = Color.WHITE;
	public Display(JApplet a){
		frame = a;
		frame.addComponentListener(new list());
		setup();
	}
	public static boolean isDebug(){
		return debug;
	}
	public static void setDebug(boolean b){
		debug  = b;
	}
	public class list implements ComponentListener{
		public void componentResized(ComponentEvent e) {
			if(debug)System.out.println("Resized");
			vimg = gc.createCompatibleVolatileImage(frame.getWidth(), frame.getHeight());
			Window.setVerticalMeters(Window.getVM());
			
			
		}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentHidden(ComponentEvent e) {}
		
	}
	
	private void setup(){
		scenes = new ArrayList<Scene>();
		Mouse m = new Mouse();
		frame.addMouseListener(m);
		frame.addMouseMotionListener(m);
		frame.addKeyListener(new Keyboard());
		frame.setBackground(Color.BLACK);
		frame.setIgnoreRepaint(true);
		
	}
	public static void setScene(int sce){
		curScene = sce;
		scenes.get(curScene).init();
	}
	public static Applet getApplet(){
		
		return frame;
	}
	
	public static void setClearColor(Color c){
		clearColor = c;
	}
	
	public static void setIsActive(boolean act){
		
		if(act && !isActive){
		isActive = true;
		}
		if(!act){
			isActive = false;
		}
		
	}
	
	public static void setFPS(int des){
		inverseFPS = 1.0/des;
		
	}
	public static int addScene(Scene s){
		scenes.add(s);
		return scenes.size()-1;
	}
	
	public static void create(){
		isActive = false;
		isCreating = true;
		System.out.println("creating");
		frame.setVisible(true);
		gc = frame.getGraphicsConfiguration();
		isActive = true;
		vimg = gc.createCompatibleVolatileImage(frame.getWidth(), frame.getHeight());
		System.out.println(gc.getDevice().toString());
		
		scenes.get(curScene).init();
		
		isActive = true;
		loop();
		isCreating = false;
	}
	
	
	private static long bef,aft;
	private static double delta;
	private static long dif;
	
	private static void loop(){
		
		while(isActive){
			bef = System.nanoTime();


		
		update(delta);
		render(vimg.getGraphics());
		frame.getGraphics().drawImage(vimg, 0, 0, null);

		
		
		aft = System.nanoTime();
		dif = aft-bef;
		delta = dif/(1000000.0*1000);
		if(delta < inverseFPS){
			try{
			Thread.sleep((long) (1000*((inverseFPS)-delta)));
			delta = inverseFPS;
			}catch(Exception e){e.printStackTrace();}
		}
		
		
	}
	}
	
	
	private static void update(double delta){
		scenes.get(curScene).update(delta);
	}
	
	private static void render(Graphics g){
		g.setColor(clearColor);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.BLACK);
		scenes.get(curScene).render(g);
		
		
	}
	
}
