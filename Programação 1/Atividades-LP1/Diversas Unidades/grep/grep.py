# coding: utf-8
# Aluno: Matheus Silva
# Matricula: 117110412
# Atividade: Grep

palavra = raw_input()
n = int(input())

for num in range(n):
    frase = raw_input()
    palavras = frase.split()
for p in palavras:
    if p == palavra:
        print frase

