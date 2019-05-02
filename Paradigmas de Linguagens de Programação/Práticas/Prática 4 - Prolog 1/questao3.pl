:- initialization(main).

procuraMenor(Elemento, N, Menor):- K is N-1 -> (
	N =< 0, Menor is Elemento, !; 
	read(ProxElemento) -> (
		ProxElemento < Elemento, procuraMenor(ProxElemento, K, Menor);
		procuraMenor(Elemento, K, Menor))).

loop(0, Troco):- writeln(Troco), !.
loop(N, Troco):- K1 is N-1, read(Orcamento), read(Itens), K2 is Itens-1, read(PrimeiroItem), procuraMenor(PrimeiroItem, K2, Menor), SubTroco is mod(Orcamento, Menor), K3 is Troco + SubTroco, loop(K1, K3).

main:- read(QtdItens), loop(QtdItens, 0).
