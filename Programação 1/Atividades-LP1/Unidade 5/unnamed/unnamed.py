# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: Unnamed

lista = []
contador = 0

while True:
    numero = raw_input()
    if numero == 'fim': break
    if int(numero) < 0:
        lista.append(contador)
        contador = 0
    else:
        contador += 1
maior = 0
for i in range(len(lista)):
    if lista[i] > lista[maior]:
        maior = i
if len(lista) > 0:
    print 'Conjunto %d - %d elemento(s)' % ((maior + 1), lista[maior])
