package com.tank01;

import javax.swing.*;


import java.awt.*;


public class MyTankGame1 extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {
		MyTankGame1 mt = new MyTankGame1();

	}
	
	public MyTankGame1(){
		mp = new MyPanel();
		
		this.add(mp);
		this.setSize(400,300);
		this.setVisible(true);
	}

}
//Panel of Game
class MyPanel extends JPanel{
	
	Hero hero = null;
	
	public MyPanel(){
		
		hero = new Hero(10, 10);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
	}
	
	public void drawTank(int x, int y, Graphics g, int direct, int type){
		//type
		switch(type){
		case 0: 
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//direction
		switch(direct){
		case 0:
			
			//g.drawRect(x, y, 5, 30);
			g.fillRect(x, y, 5, 30);
			
			g.fillRect(x+15, y, 5, 30);
			g.fillRect(x + 5, y+5, 10, 20);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y + 15);
		}
	}
	
}
//Create Tank class as a super class
class Tank{
	
	int x = 0;
	int y = 0;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x, int y){
		this.x = x;
		this.y = y;
	}
}
// Child tank class 
class Hero extends Tank{
	
	public Hero(int x, int y){
		super(x, y);
	}
}

