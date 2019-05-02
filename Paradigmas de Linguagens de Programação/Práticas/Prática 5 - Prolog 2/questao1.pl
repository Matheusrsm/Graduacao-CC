:- initialization(main).

transformacao('\''):- write("").
transformacao('<'):- write("--ptr;").
transformacao('>'):- write("++ptr;").
transformacao('+'):- write("++*ptr;").
transformacao('-'):- write("--*ptr;").
transformacao('.'):- write("putchar(*ptr);").
transformacao(','):- write("*ptr=getchar();").
transformacao('['):- write("while (*ptr) {").
transformacao(']'):- write("}").

converte([]):- write("").
converte([X|L1]) :- transformacao(X), converte(L1).

main :-
    read(X),
    term_string(X, String),
    atom_codes(A, String), 
    atom_chars(A, Lista),
    converte(Lista).
