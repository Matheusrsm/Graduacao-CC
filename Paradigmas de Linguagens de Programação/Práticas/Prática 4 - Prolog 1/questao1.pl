:- initialization(main).

doar(o, a).
doar(o, b).
doar(o, ab).
doar(a, ab).
doar(b, ab).

receber(a, o).
receber(b, o).
receber(ab, o).
receber(ab, a).
receber(ab, b).

doar_receber(o, o).
doar_receber(a, a).
doar_receber(b, b).
doar_receber(ab, ab).

incompativel(a, b).
incompativel(b, a).

doacao:- read(X), read(Y) -> (
	doar(X, Y), writeln("doar");
	receber(X, Y), writeln("receber");
	doar_receber(X, Y), writeln("doar_e_receber");
	incompativel(X, Y), writeln("incompativel")).
		
main:- doacao.
