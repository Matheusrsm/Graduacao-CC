# coding: utf-8
# Aluno: Matheus Silva
# Matrícula: 117110412
# Atividade: Ciclo Trigonométrico

a = float(raw_input())	

if a <= 360:
	if a >= 1 and a <= 89:
		print 'Quadrante 1' 
	elif a == 0 or a == 90:
		print 'Sobre eixos'
	elif a > 90 and a < 180:
		print 'Quadrante 2'
	elif a == 180:
		print 'Sobre eixos'
	elif a > 180 and a < 270:
		print 'Quadrante 3'
	elif a == 270:
		print 'Sobre eixos'
	elif a > 270 and a < 360:
		print 'Quadrante 4'
	elif a == 360:
		print 'Sobre eixos'
else:
	num2 = a % 360
	if num2 >= 1 and num2 <= 89:
		print 'Quadrante 1'
	elif num2 == 0 or num2 == 90:
		print 'Sobre eixos'
	elif num2 > 90 and num2 < 180:
		print 'Quadrante 2'
	elif num2 == 180:
		print 'Sobre eixos'
	elif num2 > 180 and num2 < 270:
		print 'Quadrante 3'
	elif num2 == 270:
		print 'Sobre eixos'
	elif num2 > 270 and num2 < 360:
		print 'Quadrante 4'
	elif num2 == 360:
		print 'Sobre eixos'	

