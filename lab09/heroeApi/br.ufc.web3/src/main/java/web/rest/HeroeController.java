package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/heroe/{id}")
	public Heroe getHeroeById(@PathVariable int id) {
		return heroeService.getHeroeById(id);

	}

	@PostMapping("/heroe")
	public void addHeroe(@RequestBody Heroe heroe) {
		heroeService.addHeroe(heroe);
	}

	@PutMapping("heroe/{id}")
    public Heroe updateHeroe(@PathVariable int id, @RequestBody Heroe updatedHeroe) {
        Heroe existingHeroe = heroeService.getHeroeById(id);
        existingHeroe.setNome(updatedHeroe.getName());

        return heroeService.putHeroe(existingHeroe);
    }

	@DeleteMapping("/heroe/{id}")
	public void removeHeroeByCode(@PathVariable int id) {
		heroeService.removeHeroeById(id);
	}
	
	@DeleteMapping("/heroe")
	public void removeHeroeByCode(@RequestBody Heroe heroe) {
		heroeService.removeHeroe(heroe);
	}

}
