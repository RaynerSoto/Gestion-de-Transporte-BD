package Iniciar;

import java.awt.EventQueue;

import visual.Identificacion;
import visual.Interfaz_principal;

public class Iniciadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Identificacion iden = new Identificacion();
						iden.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

}


