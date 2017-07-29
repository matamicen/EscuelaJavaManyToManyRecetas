package com.example.ManyToMany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ManyToMany.model.DaoIngrediente;
import com.example.ManyToMany.model.DaoReceIngre;
import com.example.ManyToMany.model.DaoReceta;
import com.example.ManyToMany.model.Ingrediente;
import com.example.ManyToMany.model.ReceIngre;
import com.example.ManyToMany.model.Receta;



@org.springframework.stereotype.Controller
public class maincontroller {
	
	@Autowired
	DaoReceta daoreceta;
	@Autowired
	DaoIngrediente daoingrediente;
	@Autowired
	DaoReceIngre daoreceingre;
	
	
	@RequestMapping(value="/crear", method = RequestMethod.GET)
	public String crear (Model model)
	{
	
		//Comienzo a crear receta de Tarta de Jamon	
	   Receta receta1 = new Receta("Tarta de Jamon");

       Ingrediente ingrediente1 = new Ingrediente("Jamon","Paladini");
	   Ingrediente ingrediente2 = new Ingrediente("Masa","La Salteña");
	   	   
	   ReceIngre receingre1 = new ReceIngre(receta1,ingrediente1,300,"g");
	   ReceIngre receingre2 = new ReceIngre(receta1,ingrediente2,600,"g");
	   
	   receta1.agregoIngrediente(receingre1);
	   receta1.agregoIngrediente(receingre2);
	   
	   ingrediente1.agregoReceta(receingre1);
	   ingrediente1.agregoReceta(receingre2);
	   
	
	   daoreceta.save(receta1);
//	   daoingrediente.save(ingrediente1);
//	   daoingrediente.save(ingrediente2);
//	   daoreceingre.save(receingre1);
//	   daoreceingre.save(receingre2);
	   
	   
	   
	   
	    
	//	System.out.println("nombre es: "+nombre);
	//	model.addAttribute("postweb", post);
	
		return "nuevoIngrediente";
	}
}