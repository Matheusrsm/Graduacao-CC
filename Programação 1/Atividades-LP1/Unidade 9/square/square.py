# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atividade: Square Code

def string(frase):
	string = ''
	for letter in frase:
		if letter.isalpha():
			string += letter
	return string

def matriz(string):
	import math
	qlinhas = math.floor(math.sqrt(len(string)))
	qcolunas = math.ceil(len(string)/qlinhas)
	linhas = []
	for i in range(qlinhas):
		for j in range(qcolunas):
			linhas.append(string[j][i]):
				
		
