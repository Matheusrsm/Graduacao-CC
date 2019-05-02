# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: Tiro ao Alvo 2

import math
import sys
contador = 0
distancias = []


def calc_dist(x, y):
    quadrado = x ** 2 + y ** 2
    raiz = math.sqrt(quadrado)
    return raiz


while True:
    x, y = float(raw_input()), float(raw_input())
    if calc_dist(x, y) <= 200:
        contador += 1
    else: 
        break
    distancias.append(calc_dist(x, y))
soma = 0
maior = 0
menor = sys.maxint
for d in distancias:
    soma += d
    if d > maior:
        maior = d
for d in distancias:
    if d < menor:
        menor = d
        print '%.2f cm (melhor tiro)' % d
    else:
        print '%.2f cm' % d
print '--\nnum tiros: %d' % contador
print 'melhor tiro: %.2f cm' % menor
print 'distancia media: %.2f cm' % (soma / len(distancias))
