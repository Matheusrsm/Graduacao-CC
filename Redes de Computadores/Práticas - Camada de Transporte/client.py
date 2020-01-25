# -*- coding: utf-8 -*- 
import socket
from threading import Thread
from queue import Queue

LOCALHOST = '127.0.0.1'
PORT = 8080

def wait_ack(acks):
    response, _ = clientSocket.recvfrom(1024)
    acks.put(response.decode('utf-8'))

def wait_data(responses):
    response, _ = clientSocket.recvfrom(1024)
    responses.put(response.decode('utf-8'))

def create_connection(method, arg):
    connection = Thread(target=method, args=(arg, ))
    connection.start()
    connection.join(timeout=2)

if __name__ == '__main__':
    clientSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    acks = Queue()
    responses = Queue()
    answer = ''
    result = ''

    while True:
        message = input()
        clientSocket.sendto(message.encode('utf-8'), (LOCALHOST, PORT))
        
        while(not acks.qsize()):
            create_connection(wait_ack, acks)

        if acks.qsize():
            while(not responses.qsize()):
                create_connection(wait_data, responses)
            answer = acks.get()
            if answer == 'ACK':
                print('[SUCESS] Received ACK from server\n')
                if responses.qsize():
                    result = responses.get()
                    if result and result != 'ACK':
                        print('The answer is: {}\n'.format(result))
                    else:
                        print('[ERROR] Result not received')
                else:
                    print('[ERROR] Result not received')
            else:  
                print('[ERROR] ACK not received')

    clientSocket.close()