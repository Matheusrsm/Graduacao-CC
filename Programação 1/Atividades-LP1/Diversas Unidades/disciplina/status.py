# coding: utf-8
# Aluno: Matheus Silva
# Matricula: 117110412
# Atividade: Status de uma Disciplina

nota1 = float(input())
nota2 = float(input())
nota3 = float(input())
faltas = int(input())

media = (nota1 + nota2 + nota3) / 3
presenca = (3000 - 100 * faltas) / 30

if presenca >= 75:
    if media >= 7:
        print 'aprovado por media'
    if media < 4:
        print 'reprovado por nota'
    if 4 <= media < 7:
        print 'prova final'
else:
    print 'reprovado por faltas'
