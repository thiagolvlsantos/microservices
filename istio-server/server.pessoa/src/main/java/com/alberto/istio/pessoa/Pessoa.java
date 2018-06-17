package com.alberto.istio.pessoa;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pessoa {
	private String cpfCnpj;
	private String nome;
}
