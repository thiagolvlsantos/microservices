package com.alberto.istio.pessoa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.istio.server.common.AbstractController;
import com.alberto.istio.server.common.IPayload;

@CrossOrigin
@RestController
@RequestMapping(PessoaController.BASE)
public class PessoaController extends AbstractController {

	static final String BASE = "pessoa";

	public PessoaController() {
		super(".NET", BASE, "v2");
	}

	@RequestMapping(path = "/{id}")
	public ResponseEntity<Object> consulta(@PathVariable("id") String cpfOrCnpj) {
		Pessoa payload = Pessoa.builder().cpfCnpj(cpfOrCnpj).nome("Fulano do CPF " + cpfOrCnpj).build();
		return process(IPayload.of(payload));
	}
}