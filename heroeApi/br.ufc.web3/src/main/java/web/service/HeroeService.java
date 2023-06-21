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

	public Heroe addHeroe(Heroe heroe) {

		return heroeRepository.save(heroe);

	}
	
	public void removeHeroeByCode(int codigo) {
	    Optional<Heroe> optionalHeroe = heroeRepository.findById(codigo);

	    if (optionalHeroe.isPresent()) {
	        Heroe heroe = optionalHeroe.get();
	        heroeRepository.delete(heroe);
	    } else {
	        throw new IllegalArgumentException("Não foi encontrado nenhum herói com o código fornecido: " + codigo);
	    }
	}

	
	public void removeHeroe(Heroe heroe) {
		heroeRepository.delete(heroe);
	}

}
