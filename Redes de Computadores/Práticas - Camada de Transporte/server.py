# -*- coding: utf-8 -*- 
import socket
from threading import Thread
import time

LOCALHOST = '127.0.0.1'
PORT = 8080

def calculator(operation, number1, number2):
    result = 'Your operation is not registered.'
    number1 = float(number1)
    number2 = float(number2)
    operation = operation.lower()

    if operation == 'add':
        result = number1 + number2
    elif operation == 'sub':
        result = number1 - number2
    elif operation == 'mult':
        result = number1 * number2
    elif operation == 'exp':
        result = number1 ** number2
    elif operation == 'div':
        if number2 == 0:
            result = 'Impossible to divide by 0.'
        else:
            result = number1 / number2

    return result

def connection_handler():
    data, address = serverSocket.recvfrom(1024)
    operation, number1, number2 = data.decode('utf-8').split(' ')
    result = str(calculator(operation, number1, number2)).encode('utf-8')

    serverSocket.sendto('ACK'.encode('utf-8'), address)
    serverSocket.sendto(result, address)

if __name__ == '__main__':
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    serverSocket.bind((LOCALHOST, PORT))
    
    while True:
        connection = Thread(target=connection_handler)
        connection.start()
        connection.join(timeout=1)

    serverSocket.close()