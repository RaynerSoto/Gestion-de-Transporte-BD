package Controladora;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

import model.Carro;
import model.Combustible;
import model.Hoja_de_ruta;
import model.Marca;
import services.Carro_serv;
import services.Combustible_serv;
import services.Hoja_de_ruta_serv;
import services.Marca_serv;
import utils.ConnectionManager;

public class transbus {
	public static transbus trans;
	public static transbus gettransbus() throws ClassNotFoundException, SQLException{
		if(trans== null)
			trans = new transbus();
		return trans;
	}
 private ArrayList<Combustible> combustible;
 private ArrayList<Marca> marcas;
 private ArrayList<Carro> carros;
 private ArrayList<Hoja_de_ruta>hoja_ruta;

 
    public transbus() throws ClassNotFoundException, SQLException {
	super();
	this.combustible = Combustible_serv.Cargar_combustible();
	this.marcas = Marca_serv.cargar_marcar();
	this.carros = Carro_serv.cargar_carro();
	this.hoja_ruta = Hoja_de_ruta_serv.cargar_hoja_ruta();
}

/**
 * @return the combustible
 */
    public ArrayList<Combustible> getCombustible() {
	return combustible;
}

/**
 * @param combustible the combustible to set
 */
    public void setCombustible(ArrayList<Combustible> combustible) {
	this.combustible = combustible;
}
    
 
   

   /**
 * @return the marcas
 */
public ArrayList<Marca> getMarcas() {
	return marcas;
}

/**
 * @param marcas the marcas to set
 */
public void setMarcas(ArrayList<Marca> marcas) {
	this.marcas = marcas;
}



	/**
 * @return the carros
 */
public ArrayList<Carro> getCarros() {
	return carros;
}

/**
 * @param carros the carros to set
 */
public void setCarros(ArrayList<Carro> carros) {
	this.carros = carros;
}


	/**
 * @return the hoja_ruta
 */
public ArrayList<Hoja_de_ruta> getHoja_ruta() {
	return hoja_ruta;
}

/**
 * @param hoja_ruta the hoja_ruta to set
 */
public void setHoja_ruta(ArrayList<Hoja_de_ruta> hoja_ruta) {
	this.hoja_ruta = hoja_ruta;
}

	// Buscar Combustibles
    public int buscar_pos_combustible(String comb){
    	int poss = -1;
    	boolean encontrada=false;
    	for(int i = 0; i < combustible.size() && !encontrada; i++) {
    		if(combustible.get(i).getNombre().equals(comb)) {
    			poss = i;
    			encontrada = true;
    		}
    	}
    	return poss;
    }

    public Combustible buscar_combustible_modificado(String nombre) {
    	ListIterator<Combustible> iter = combustible.listIterator();
    	Combustible combus;
    	Combustible comb = null;
    	 while(iter.hasNext()) {
    		 combus = iter.next();
    		 if(combus.getNombre().equals(nombre)) {
    			 comb = combus;
    		 }
    	 }
    	return comb;
    }
    
    //Marca
    
    public Marca buscar(String m) {
    	ListIterator<Marca> iter = marcas.listIterator();
    	Marca marca;
    	Marca marcas = null;
    	 while(iter.hasNext()) {
    		 marca = iter.next();
    		 if(marca.getNombre().equals(m)) {
    			 marcas = marca;
    		 }
    	 }
    	return marcas;
    }
    
    public int buscar_pos(String comb){
    	int poss = -1;
    	boolean encontrada=false;
    	for(int i = 0; i < marcas.size() && !encontrada; i++) {
    		if(marcas.get(i).getNombre().equals(comb)) {
    			poss = i;
    			encontrada = true;
    		}
    	}
    	return poss;
    }
    
    //carro
    
    public Carro buscar_carro(String c) {
    	ListIterator<Carro> iter = carros.listIterator();
        Carro car;
    	Carro ca = null;
    	 while(iter.hasNext()) {
    		 car = iter.next();
    		 if(car.getPlaca().equals(c)) {
    			 ca = car;
    		 }
    	 }
    	return ca;
    }
    
    public int buscar_pos_carro(String placa){
    	int poss = -1;
    	boolean encontrada=false;
    	for(int i = 0; i < carros.size() && !encontrada; i++) {
    		if(carros.get(i).getPlaca().equals(placa)) {
    			poss = i;
    			encontrada = true;
    		}
    	}
    	return poss;
    }
    
    // Hoja de ruta
    public Hoja_de_ruta buscar_hoja_ruta(long id) {
    	ListIterator<Hoja_de_ruta> iter = hoja_ruta.listIterator();
        Hoja_de_ruta ruta;
    	Hoja_de_ruta h_ruta = null;
    	 while(iter.hasNext()) {
    		 ruta = iter.next();
    		 if(ruta.getIdentificador()== id) {
    			 h_ruta = ruta;
    		 }
    	 }
    	return h_ruta;
    }
    
    public int buscar_pos_ruta(long id){
    	int poss = -1;
    	boolean encontrada=false;
    	for(int i = 0; i < hoja_ruta.size() && !encontrada; i++) {
    		if(hoja_ruta.get(i).getIdentificador() == id) {
    			poss = i;
    			encontrada = true;
    		}
    	}
    	return poss;
    }

}
