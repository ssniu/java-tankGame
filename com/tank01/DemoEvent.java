package com.tank01;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DemoEvent extends JFrame implements ActionListener{
	
	MyPanel mp = null;
	JButton jb1 = null;
	JButton jb2 = null;
	

	public static void main(String[] args) {
		DemoEvent de = new DemoEvent();
		

	}
	
	public DemoEvent(){
		mp = new MyPanel();
		jb1 = new JButton("black");
		jb2 = new JButton("red");
		
		this.add(jb1, BorderLayout.NORTH);
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);
		
		this.setSize(200,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		jb1.addActionListener(this);
		jb1.setActionCommand("button1");
		
		jb2.addActionListener(this);
		jb2.setActionCommand("button2");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("button1")){
			System.out.println("click on button 1");
		}
		else if(e.getActionCommand().equals("button2")){
			System.out.println("click on button 2");
		}
	}
	

}

class MyPanel extends JPanel{
	
	public void panit(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.blue);
	}
}

