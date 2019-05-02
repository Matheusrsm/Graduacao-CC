# coding: utf-8
# Aluno: Matheus Silva Medeiros
# Matrícula: 117110412
# Atividade: Árvore de Natal

altura = int(input())
espacos = altura
for parte in range(1, altura + 1):
    espacos -= 1
    print '%s%s' % (espacos * ' ', 'o' * (parte + parte - 1))
print '%so' % (' ' * (altura - 1))
