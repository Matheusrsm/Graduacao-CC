def ordena_tipos(lista):
    numeros = []
    alphas = []
    alphanumericos = []
    for i in range(len(lista)):
        if lista[i].isdigit():
            numeros.append(lista[i])
        elif lista[i].isalpha():
            alphas.append(lista[i])
        else:
            alphanumericos.append(lista[i])
    return numeros + alphas + alphanumericos
