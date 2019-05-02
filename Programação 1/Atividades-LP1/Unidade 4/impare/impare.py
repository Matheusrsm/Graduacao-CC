# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atividade: Série (ímpare 1)

numeros = range(1, 103, 2)
for n in numeros:
    if n % 3 == 0 or n % 5 == 0:
        n = '*'
    print n
