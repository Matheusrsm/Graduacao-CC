# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: Verifica Operações Extrato

limite = int(input())
saldo = float(raw_input())
cont = 0
while True:
    op = raw_input().split()
    if op[0] == 'saque':
        cont += 1
        saldo -= float(op[1])
        if saldo < 0 or cont > limite:
            print 'Operação inválida: saque %.2f.' % float(op[1])
            saldo += float(op[1])
            print 'Seu saldo é R$ %.2f.' % saldo
            break
    else:
        if float(op[1]) > 1000:
            print 'Operação inválida: depósito %.2f.' % float(op[1])
            print 'Seu saldo é R$ %.2f.' % saldo
            break
        else:
            saldo += float(op[1])
