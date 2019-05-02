import socket
import os
import sys

HOST = "localhost"
PORT = 2019
tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
origin = (HOST, PORT)

tcp.bind(origin)
tcp.listen(1)

print ("\nServidor TCP iniciado no IP " + HOST + " na porta " + str(PORT))

while True:
    connection, client = tcp.accept()
    pid = os.fork()
    if pid == 0:
        tcp.close()
        print ("\nConexao realizada por: ", client)
        while True:
            message = connection.recv(4096)
            if not message: break
            connection.sendall(message.upper())
            print (client, message.upper())
        print ("Finalizando conexao do cliente ", client)
        connection.close()
        sys.exit(0)
    else:
        connection.close()
