#coding: utf-8

import sys


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