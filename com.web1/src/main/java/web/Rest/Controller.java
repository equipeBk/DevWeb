package web.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.Service.AlunoService;

@RestController
public class Controller {
	
	@Autowired
    AlunoService alunoService;

   
    @GetMapping
    List<Aluno> getNome(@RequestParam Map<String, String> params){

        return alunoService.getNome(buscaNome);
    }

}

