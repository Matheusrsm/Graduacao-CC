# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: Agenda Telefônica

def bubblesort(agenda):
	for N in range(len(agenda)):
		for i in range(N - 1, -1, -1):
			if agenda[i][0] > agenda[i + 1][0]:
				agenda[i], agenda[i + 1] = agenda[i + 1], agenda[i]
	return agenda
	
agenda = []
nomes = []
	
while True:
	operacao = raw_input()
	if operacao == 'finalizar': break
	if operacao == 'inserir':
		quantidade = int(input())
		for i in range(quantidade):
			nome, numero = raw_input(), raw_input()
			agenda.append((nome, numero))
			nomes.append(nome)
	elif operacao == 'buscar':
		nome = raw_input()
		for j in range(len(agenda)):
			if nome == agenda[j][0]:
				print 'Nome: %s\nFone: %s\n----------' % (agenda[j][0],agenda[j][1])
		if nome not in nomes:
			print 'Nome inexistente\n----------'
	elif operacao == 'imprimir':
		bubblesort(agenda)
		for j in range(len(agenda)):
			print 'Nome: %s\nFone: %s\n----------' % (agenda[j][0],agenda[j][1])
