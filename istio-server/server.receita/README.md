# Exemplo de serviços poliglota
Esse projeto apresenta varios serviços Rest de demonstração em golang, .netcore, java e nodejs.
Esses serviços serão utilizados para demonstrar a execução de aplicações stateless no kuberntes e istio.

# End points comuns a todos os serviços
**/health** - return {"status":"UP"}

**/info** retorna nome servico,versao,ambiente (.net,java,nodejs,golang) 
	{ nome: 'pessoa', versao: 'v1', ambiente: 'java' , delay: '0' }
	
**/seterror/:quando** (sempre|par|impar) - gera erro sempre,a cada consulta par ou impar.

**/metric** - metricas de consultas atendidas, erros gerados
{ consultas: '100' , erro: '10', sucesso: '90', atrasadas: '5'}

**/setdelay/:segs**  -insere delay de segs segundos em cada resposta.

# Consulta pessoa , escrito em nodejs

**/pessoa/:cpf** - retorna pessoa com cpf especifico. Ex. de retorno:

{ nome: 'ALBERTO CARLOS VIEIRA DE AZEVEDO',
  cpf: '630.481.834-34',
  email: 'alberto.azevedo@bcb.gov.br',
}

**/pessoas** - retorna todas as pessoas

# Consulta processo , escrito em golang

**/processo/:cpf|cnpj** - retorna um processo especifico. Ex. de retorno:

{ numero: '1', nome: 'ACME', cpfcnpj: '63048183434', descricao: 'Gritou com a mae', decisao: 'culpado'}

**/processo**s - retorna todos os processos

# Consulta receita , escrito em java springboot

**/consultaReceita/:cpf|cnpj**  - retorna status de uma cpf cnpj, ex. de retorno:
{ ativo: '0|1' }

# Consulta autorizações , escrito em .net core
**/autorizacao/:numero**  - retorna autorização expecifica. Ex. retorno:

{ numero: '1', assunto: 'Autorizacao de Eleicao', cnpj: 'xxxxxx', eleitos: [{cpf:'63048183434',cargo: 'Diretor Geral'}], data: '01/04/2018'}

**/autorizacoes**  - retona um array com todas as autorizações. Ex. retorno:

[
{ numero: '1', assunto: 'Autorizacao de Eleicao', cnpj: 'xxxxxx', eleitos: [{cpf:'63048183434',cargo: 'Diretor Geral'}], data: '01/04/2018'},

{ numero: '2', assunto: 'Autorizacao de Eleicao', cnpj: 'zzzzzz', eleitos: [{cpf:'63048183434',cargo: 'Diretor Conselho Fiscal'}], data: '01/01/2017'}
]
