package web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.Heroe;
import web.repository.HeroeRepository;

@Service
public class HeroeService {

	@Autowired
	HeroeRepository heroeRepository;
	Heroe heroe = new Heroe();

	public Iterable<Heroe> getsHeroe() {

		return heroeRepository.findAll();
	}

	public Heroe getHeroeById(int id) {
		Optional<Heroe> optionalHeroe = heroeRepository.findById(id);

		if (optionalHeroe.isPresent()) {
			return optionalHeroe.get();
		} else {
			throw new IllegalArgumentException("Não foi encontrado nenhum herói com o id fornecido: " + id);
		}
	}

	public Heroe addHeroe(Heroe heroe) {
	    return heroeRepository.save(heroe);
	}


	public Heroe putHeroe(Heroe heroe) {

		return heroeRepository.save(heroe);

	}

	public void removeHeroeById(int id) {
		Optional<Heroe> optionalHeroe = heroeRepository.findById(id);

		if (optionalHeroe.isPresent()) {
			Heroe heroe = optionalHeroe.get();
			heroeRepository.delete(heroe);
		} else {
			throw new IllegalArgumentException("Não foi encontrado nenhum herói com o id fornecido: " + id);
		}
	}

	public void removeHeroe(Heroe heroe) {
		heroeRepository.delete(heroe);
	}

}
