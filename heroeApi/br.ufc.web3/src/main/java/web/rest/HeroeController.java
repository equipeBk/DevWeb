package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.model.Heroe;
import web.service.HeroeService;

@RestController
@RequestMapping("/api")
public class HeroeController {

	@Autowired
	private HeroeService heroeService;

	@GetMapping("/heroe")
	public Iterable<Heroe> getHeroe() {
		return heroeService.getsHeroe();
	}

	@PostMapping("/heroe")
	public void addHeroe(@RequestBody Heroe heroe) {
		heroeService.addHeroe(heroe);
	}
	
	@DeleteMapping("/heroe/{codigo}")
	public void removeHeroeByCode(@PathVariable int codigo) {
	    heroeService.removeHeroeByCode(codigo);
	}



}
