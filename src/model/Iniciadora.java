package model;

import java.awt.EventQueue;

import visual.Identificacion;

public class Iniciadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Identificacion frame = new Identificacion();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

}


