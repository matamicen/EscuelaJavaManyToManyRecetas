package com.example.ManyToMany.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private String marca;
	private String url;
	

	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "ingrediente" )
	private List <ReceIngre> receingre;
	
	
	
	public Ingrediente() {
		super();
		this.receingre = new ArrayList<ReceIngre>();
	}

	

	public Ingrediente(String nombre, String marca) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.receingre = new ArrayList<ReceIngre>();
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getMarca() {
		return marca;
	}




	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void agregoReceta(ReceIngre receingre)
	{
		this.receingre.add(receingre);
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public List<ReceIngre> getReceingre() {
		return receingre;
	}



	public void setReceingre(List<ReceIngre> receingre) {
		this.receingre = receingre;
	}


	
	
	
	
	
	
	
	

}
