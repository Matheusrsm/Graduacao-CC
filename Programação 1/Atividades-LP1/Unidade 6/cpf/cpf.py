# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atividade: Dígitos de Verificação do CPF

def calcula_digitos_verificacao(digitos):
	multiplicador = 2
	soma = 0
	for i in range((len(digitos) - 1), -1, -1):
		x = int(digitos[i])
		x *= multiplicador
		soma += x
		multiplicador += 1
	soma *= 10
	digito1 = soma % 11
	if digito1 == 10:
		digito1 = 0
	soma = digito1 * 2
	multiplicador = 3
	for i in range((len(digitos) - 1), -1, -1):
		x = int(digitos[i])
		x *= multiplicador
		soma += x
		multiplicador += 1
	soma *= 10
	digito2 = soma % 11
	if digito2 == 10:
		digito2 = 0
	return str(digito1) + str(digito2)
