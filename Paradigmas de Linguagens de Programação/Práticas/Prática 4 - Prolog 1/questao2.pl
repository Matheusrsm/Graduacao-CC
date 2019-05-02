:- initialization(main).

terra("Terra").
mercurio("Mercurio").
venus("Venus").
marte("Marte").
jupter("Jupter").
saturno("Saturno").
urano("Urano").
netuno("Netuno").

read_Number(Number):- read_line_to_codes(user_input, Codes), string_to_atom(Codes, Atom), atom_number(Atom, Number).

calculo(Resposta):- read_line_to_string(user_input, Planeta), read_Number(Idade) -> (
	terra(Planeta), Resposta is round(Idade/31557600);
	mercurio(Planeta), Resposta is round((Idade/31557600)*0.2408467);
	venus(Planeta), Resposta is round((Idade/31557600)*0.61519726);
	marte(Planeta), Resposta is round((Idade/31557600)*1.8808158);
	jupter(Planeta), Resposta is round((Idade/31557600)*11.862615);
	saturno(Planeta), Resposta is round((Idade/31557600)*29.447498);
	urano(Planeta), Resposta is round((Idade/31557600)*84.016846);
	netuno(Planeta), Resposta is round((Idade/31557600)*164.79132)).

main:- calculo(Resposta), write(Resposta), write(" Anos").
