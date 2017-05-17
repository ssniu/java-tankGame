package com.test6;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.Vector;



public class MyTankGame2 extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {
		MyTankGame2 mt = new MyTankGame2();

	}
	//constructor
	public MyTankGame2(){
		mp = new MyPanel();
		
		this.add(mp);
		this.setSize(400,300);
		this.setVisible(true);
		
		this.addKeyListener(mp);
	}

}
//Panel of Game
class MyPanel extends JPanel implements KeyListener {
	
	Hero hero = null;
	
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	int enSize = 3;
	
	public MyPanel(){
		
		hero = new Hero(10, 10);
		for(int i = 0; i < enSize; i++){
			EnemyTank et = new EnemyTank((i + 1) * 50, 0);
			et.setColor(0);
			et.setDirect(2);
			ets.add(et);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		for(int i = 0; i < ets.size(); i++){
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 0);
		}
		
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
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y, 5, 30, false);
			g.fill3DRect(x + 5, y+5, 10, 20, false);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y);
			break;
		case 1:
			g.fill3DRect(x, y,30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			g.fillOval(x + 10, y + 5, 10, 10);
			g.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
			
		case 2:
			g.fill3DRect(x, y,5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case 3:
			g.fill3DRect(x, y,30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			g.fillOval(x + 10, y + 5, 10, 10);
			g.drawLine(x + 15, y + 10, x, y + 10);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_W){
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode() == KeyEvent.VK_D){
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyCode() == KeyEvent.VK_S){
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyCode() == KeyEvent.VK_A){
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		this.repaint();//make the tank moving
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

