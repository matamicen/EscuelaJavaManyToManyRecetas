package com.example.ManyToMany.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Receta {
	
	@Id
	@GeneratedValue
	private long id;
	private String nombreReceta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
	        mappedBy = "receta" )
	private List<ReceIngre> listareceingre;
	
		
	public Receta() {
		super();
		this.listareceingre = new ArrayList<ReceIngre>();
	}

	

	public Receta(String nombreReceta) {
		super();
		this.nombreReceta = nombreReceta;
		this.listareceingre = new ArrayList<ReceIngre>();
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombreReceta() {
		return nombreReceta;
	}


	public void setNombreReceta(String nombreReceta) {
		this.nombreReceta = nombreReceta;
	}
	
	public void agregoIngrediente(ReceIngre receingre)
	{
		this.listareceingre.add(receingre);
				
	}
	
}


