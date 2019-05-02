#coding: utf-8

import sys

########## PRÁTICA 1 ##########

def retorna_matriculas_decrescente(alist):
    pivot = alist[0]
    itemsAbovePivot = []
    itemsBelowPivot = []
    del alist[0]
    for item in alist:
        if item >= pivot:
            itemsAbovePivot.append(item)
        else:
            itemsBelowPivot.append(item)
    itemsBelowPivotSorted = itemsBelowPivot
    itemsAbovePivotSorted = itemsAbovePivot
    if len(itemsBelowPivot) > 1:
        itemsBelowPivotSorted = retorna_matriculas_decrescente(itemsBelowPivot);
    if len(itemsAbovePivot) > 1:
        itemsAbovePivotSorted = retorna_matriculas_decrescente(itemsAbovePivot);
    return itemsAbovePivotSorted + [pivot] + itemsBelowPivotSorted


########## PRÁTICA 2 ##########

def retorna_minimo_moedas(valor, tipos_moedas):
    print valor, tipos_moedas
    if valor <= 0: return 0
    return minimoMoedas(tipos_moedas, valor, [0] * valor)

def minimoMoedas(moedas, valor, listaContadores):
    if valor < 0: return -1
    if valor == 0: return 0
    if listaContadores[valor - 1] != 0: return listaContadores[valor - 1]
    min_moedas = sys.maxint
    for moeda in moedas:
        resultado = minimoMoedas(moedas, valor - moeda, listaContadores)
        if resultado >= 0 and resultado < min_moedas:
                min_moedas = 1 + resultado
    listaContadores[valor - 1] = -1 if min_moedas == sys.maxint else min_moedas
    return listaContadores[valor - 1]

########## PRÁTICA 3 ##########

def retorna_minimo_moedas(valor, tipos_moedas):
    print valor, tipos_moedas
    if valor <= 0: return 0
    return minimoMoedas(tipos_moedas, valor)

def minimoMoedas(moedas, valorTroco):
    moedas.sort(reverse = True)
    qtdMoedas = 0
    troco = valorTroco

    for moeda in moedas:
        if moeda <= troco:
            qtdMoedas += (troco / moeda)
            troco %= moeda

    if troco > 0: return -1
    return qtdMoedas

########## PRÁTICA 4 ##########

# Retorna o valor maximo que cabe na mochila com capacidade peso_maximo

def mochila_binaria(peso_maximo, pesos, valores, n):
    cabeAlgumItem = False
    for item in pesos:
        if item <= peso_maximo:
            cabeAlgumItem = True
    if cabeAlgumItem:
        matriz = [[0 for x in range(peso_maximo + 1)] for x in range(n + 1)]
        for i in range(n + 1):
            for j in range(peso_maximo + 1):
                if pesos[i - 1] <= j:
                    matriz[i][j] = max(valores[i - 1] + matriz[i - 1][j-pesos[i - 1]],  matriz[i - 1][j])
                else: 
                    matriz[i][j] = matriz[i - 1][j]
        return matriz[n][peso_maximo]
    return 0
