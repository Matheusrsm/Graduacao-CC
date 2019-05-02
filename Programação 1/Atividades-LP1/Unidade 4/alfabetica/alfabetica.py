# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atividade: Ordem Alfabética

quantidade = int(input())
lista = []
antes = 0
depois = 0

for n in range(quantidade):
    palavra = raw_input()
    lista.append(palavra)
print '---'
referencia = raw_input()
for i in range(len(lista)):
    if referencia > lista[i]:
        antes += 1
    if referencia < lista[i]:
        depois += 1
print '%d antes\n%d depois' % (antes, depois)
