# coding: utf-8 
# Média Final
# Matheus Silva / UFCG, Programação 1

distancia = float(raw_input())
aliquota = float(raw_input())

valor = 51.00 + distancia*aliquota

pag1 = 0.75*valor
pag2 = 0.95*valor

parcela1 = pag2/6
parcela2 = valor/10

print "Valor da passagem: R$ %.2f" % valor
print ""
print "Pagamento:"
print "1. À vista. R$ %.2f" % pag1
print "2. Em 6 parcelas. Total: R$ %.2f" % pag2
print "   6 x R$ %.2f" % parcela1
print "3. Em 10 parcelas. Total: R$ %.2f" % valor
print "   10 x R$ %.2f" % parcela2
