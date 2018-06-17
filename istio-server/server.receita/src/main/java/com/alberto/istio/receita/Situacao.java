package com.alberto.istio.receita;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Situacao {
	private String cpfCnpj;
	private String ativo;
}
