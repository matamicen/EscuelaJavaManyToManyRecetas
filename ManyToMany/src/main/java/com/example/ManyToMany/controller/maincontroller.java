package com.example.ManyToMany.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ManyToMany.model.DaoIngrediente;
import com.example.ManyToMany.model.DaoReceIngre;
import com.example.ManyToMany.model.DaoReceta;
import com.example.ManyToMany.model.Ingrediente;
import com.example.ManyToMany.model.ReceIngre;
import com.example.ManyToMany.model.Receta;
import com.example.ManyToMany.services.Pasos;




@org.springframework.stereotype.Controller
public class maincontroller {
	
	@Autowired
	DaoReceta daoreceta;
	@Autowired
	DaoIngrediente daoingrediente;
	@Autowired
	DaoReceIngre daoreceingre;
	
	ArrayList<Pasos> listaPasos = new ArrayList<Pasos>();
	
	
	@RequestMapping(value="/nuevareceta", method = RequestMethod.GET)
	public String nuevareceta (Model model)
	{
		Receta rec = new Receta();
		model.addAttribute("receta",rec);
		return "nuevareceta";
	}
	
	@RequestMapping(value="/crearreceta", method = RequestMethod.POST)
	public String creareceta (Model model, @ModelAttribute Receta receta)
	{
		daoreceta.save(receta);
		System.out.println("id de receta:" + receta.getId());
		model.addAttribute("receta",receta);
		model.addAttribute("listaingredientes",daoingrediente.findAll());
		
		return "listadeingredientes";
	}
	
	@RequestMapping(value="/agregaingrediente", method = RequestMethod.POST)
	public String agregaingrediente (Model model, @ModelAttribute Receta receta, 
			@RequestParam long idingrediente,@RequestParam int qty, @RequestParam String um)
	{
		//daoreceta.save(receta);
	// ReceIngre receingre = new ReceIngre(receta,daoingrediente.findOne(idingrediente),qty,um); 
	   Pasos paso = new Pasos(daoingrediente.findOne(idingrediente), qty, um);
	   listaPasos.add(paso);
		
	   for(Pasos pas: listaPasos){
		   System.out.println(pas.getIngrediente().getNombre() + " " + pas.getQty() + " " + pas.getUm() );
		   
	   }
	   
	   
		System.out.println("id de receta:" + receta.getId());
		model.addAttribute("receta",receta);
		model.addAttribute("listaingredientes",daoingrediente.findAll());
		
		return "listadeingredientes";
	}
	
	
	
	@RequestMapping(value="/grabarpasosreceta", method = RequestMethod.GET)
	public String grabarpasos (Model model, @RequestParam long id)
	{
		Receta rec = daoreceta.findOne(id);
		for(Pasos pas: listaPasos ){
			   ReceIngre receingre = new ReceIngre(rec,pas.getIngrediente(),pas.getQty(),pas.getUm());
			   rec.agregoIngrediente(receingre);
		      }
		   
		   daoreceta.save(rec);
		
		model.addAttribute("receta",rec);
		return "pasoscreados";
	}
	
	
	
	
	
	
	
	
	
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
	   
	//   ingrediente1.agregoReceta(receingre1);
	//   ingrediente1.agregoReceta(receingre2);
	   
	
	   daoreceta.save(receta1);
//	   daoingrediente.save(ingrediente1);
//	   daoingrediente.save(ingrediente2);
//	   daoreceingre.save(receingre1);
//	   daoreceingre.save(receingre2);
	   
	   
	   
	   
	    
	//	System.out.println("nombre es: "+nombre);
	//	model.addAttribute("postweb", post);
	
		return "nuevoIngrediente";
	}
	
	
	/*
	 * Este metodo de abajo fue para probar que se puede hacer lo de arriba, no lo uso, y tampoco debo explicarlo
	 * de esta forma.
	 */
	@RequestMapping(value="/crearnuevareceta", method = RequestMethod.GET)
	public String crearnuevareceta (Model model)
	{
	
		ArrayList<Pasos> listaPasos = new ArrayList<Pasos>();
		
		//Comienzo a crear receta de Tarta de Jamon
	   Receta receta = new Receta("Empanada de Carne 2");
	   
	   Pasos paso = new Pasos(daoingrediente.findOne((long)5),250,"g");
	   listaPasos.add(paso);
	   paso = new Pasos(daoingrediente.findOne((long)4),500,"g");
	   listaPasos.add(paso);
	   
	   for(Pasos pas: listaPasos ){
		   ReceIngre receingre = new ReceIngre(receta,pas.getIngrediente(),pas.getQty(),pas.getUm());
		   receta.agregoIngrediente(receingre);
	      }
	   
	   daoreceta.save(receta);
	   
	   Receta rec = daoreceta.findOne((long)3);
	   
	   
	   
	   
		return "nuevoIngrediente";
	}
}