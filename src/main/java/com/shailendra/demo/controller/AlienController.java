package com.shailendra.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shailendra.demo.dao.AlienRepo;
import com.shailendra.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo ar;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping(path="/alien",consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien) {
		ar.save(alien);
		return alien;
	}
	
	
	/*
	 * @RequestMapping("/addAlien") public String addAlien(Alien alien) {
	 * ar.save(alien); return "home.jsp"; }
	 */
	
	/*
	 * @RequestMapping("/getAlien") public ModelAndView addAlien(@RequestParam int
	 * aid) { ModelAndView mv = new ModelAndView("showAlien.jsp"); Alien alien =
	 * ar.findById(aid).orElse(new Alien()); mv.setViewName("showAlien.jsp");
	 * mv.addObject(alien); System.out.println(ar.findByTechSorted("Java")); return
	 * mv; }
	 */
	
	/*
	 * @RequestMapping(path="aliens", produces= {"application/xml"})
	 * 
	 * @ResponseBody public List<Alien> getAlien() { return ar.findAll();
	 * 
	 * }
	 */
	
	@RequestMapping(path="aliens")
	@ResponseBody
	public List<Alien> getAlien() {
		return ar.findAll();
		
	}
	
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int  aid) {
		return ar.findById(aid);
	}
	
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		
		Alien a = ar.getOne(aid);
		ar.delete(a);
		return "delted";
	}
	
	@PutMapping("/alien")
	public Alien updateAlien(@RequestBody Alien alien) {
		
		ar.save(alien);
		return alien;
	}
}
	

