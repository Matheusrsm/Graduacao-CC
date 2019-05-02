# coding: utf-8
# Nota Fiscal
# Matheus Silva / UFCG, Programação 1

valor_total = float(raw_input())
data = float(raw_input())
quantidade_de_produtos = int(raw_input())

media = valor_total / quantidade_de_produtos

print 'Data: %.2f' % data
print 'O valor total da compra foi de R$ %.2f. A média do preço dos produtos é de %s.' % (valor_total, media)

