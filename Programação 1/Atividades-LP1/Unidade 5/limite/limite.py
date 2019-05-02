# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: No Limite

lista = []

while True:
    indice = raw_input()
    if indice == '-': 
        break
    lista.append(indice)
limite = float(raw_input())
soma = 0
i = 0 
while i < len(lista):
    soma += float(lista[i])
    media = soma / (i + 1)
    i += 1
    if media > limite:
        print 'media = %.1f\nnum = %d' % (media, i)
        break
else:
    print 'limite não alcançado'
