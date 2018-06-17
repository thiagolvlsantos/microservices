package com.alberto.istio.receita;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.istio.server.common.AbstractController;
import com.alberto.istio.server.common.IPayload;

@CrossOrigin
@RestController
@RequestMapping(ReceitaController.BASE)
public class ReceitaController extends AbstractController {

	static final String BASE = "receita";

	private int contador = 0;

	public ReceitaController() {
		super("java", BASE, "v1");
	}

	@RequestMapping(path = "/{id}")
	public ResponseEntity<Object> consulta(@PathVariable("id") String cpfOrCnpj) {
		Situacao payload = Situacao.builder().cpfCnpj(cpfOrCnpj).ativo(++contador % 2 == 0 ? "1" : "0").build();
		return process(IPayload.of(payload));
	}
}