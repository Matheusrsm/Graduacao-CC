# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atendimentos no SAMU

lista = []
soma = 0
for mes in range(12):
    valor_mes = int(input())
    soma += valor_mes
    lista.append(valor_mes)
print 'Média mensal de atendimentos: %.2f' % (soma / 12.0)
print '----'
for x in range(len(lista)):
    if lista[x] > (soma / 12):
        print 'Mês %d: %d' % ((x + 1), lista[x])

