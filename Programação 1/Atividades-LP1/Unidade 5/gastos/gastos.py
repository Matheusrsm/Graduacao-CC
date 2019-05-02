# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: Limite de Gastos

entrada = float(raw_input())
lista = []


while True:
    linha = raw_input()
    quebrado = linha.split()
    soma = 0
    if linha == 'fim': break
    else:
        for i in range(len(quebrado)):
            soma += float(quebrado[i])
        string = ''
        if (soma / len(quebrado)) > entrada:
            for num in quebrado:
                string += ' ' + '%.1f' % float[num]
            print string.strip()
        if (soma / len(quebrado)) <= (entrada / 2): break
