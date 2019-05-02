#coding: utf-8
import socket
import sys 
from threading import Thread
import os
 
connections = {}
def client(connection):

    connection.send(("You are connected!\nType your name: ").encode())
    nome = connection.recv(4096).decode('utf-8').strip()
    connections[connection] = nome
    connection.send(">> ".encode())
    while True:
        mensagem = connection.recv(4096).decode('utf-8').strip()
        if mensagem == ":bye":
            connection.send("You are disconnected!\n".encode())
            print("%s has been disconnected!" % nome)
            connection.close()
            del connections[connection]
            for con in connections:
                con.send(("%s has been disconnected!\n"%nome).encode())
            exit()
        if mensagem:
            print("%s: %s"%(nome, mensagem))
            for con in connections:
                if not (con == connection):
                    con.send(("%s: %s \n>> "%(nome, mensagem)).encode())
                else: con.send(">> ".encode())
port = int(sys.argv[1] if len(sys.argv) > 1 else 2060)
host_ip = "127.0.0.1"
with socket.socket() as s:
    s.bind((host_ip, port))
    s.listen()
    print("Connect to IP %s through %s port"%(host_ip, port))

    while True:
        connection, address = s.accept()
        print("A client has connected!")
        Thread(target=client, args=(connection,)).start()