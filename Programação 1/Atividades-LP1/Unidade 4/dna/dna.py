# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atividade: DNA

seq1 = raw_input()
seq2 = raw_input()
iguais = 0

for i in range(len(seq1)):
    if seq1[i] == seq2[i]:
        iguais += 1
if iguais > (len(seq1) / 2):
    print 'sim'
else:
    print 'nao'
        

