# coding: utf-8

'''Digita-se o nome da linha primeiramente, após isso o ID dos bairros.
   Para finalizar os bairros dê enter sem nenhum ID. Após isso serão impressas
   as conexões.
   Para finalizar o programa dê enter quando estiver em linha sem nada escrito.
   '''

while True:
    print "---\nLinha:",
    linha = raw_input()
    
    if linha:
        bairros = []
        while True:
            print "Bairro:",
            bairro = raw_input()
            if not bairro: break
            bairros.append(int(bairro))
            
        print "---"
        conexoes = []
        
        for i in bairros:
            for j in bairros:
                if not i == j and not (j, i) in conexoes:
                    conexoes.append((i, j))
        
        for i in xrange(len(conexoes)):
            print conexoes[i][0], conexoes[i][1], '\"' + linha + '\"'
        
        print ""
        
    else:
        break
