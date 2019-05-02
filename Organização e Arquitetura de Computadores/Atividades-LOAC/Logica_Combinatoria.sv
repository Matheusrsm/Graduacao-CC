//Questão 1
logic banheiro1;
logic banheiro2;
logic banheiro3;

always_comb begin
	banheiro1 <= SWI[0];
	banheiro2 <= SWI[1];
	banheiro3 <= SWI[2];
	LED[0] <= (banheiro1 || banheiro2 || banheiro3) && !SWI[7];
	LED[1] <= (banheiro2 || banheiro3) && !SWI[7];
end

//Questão 2
logic t1;
logic t2;

always_comb begin
	t1 <= SWI[3];
	t2 <= SWI[4];
	LED[6] <= (!t2 && !t1) && !SWI[7];
	LED[7] <= (t1 && t2) && !SWI[7];
	SEG[7] <= (!t1 && t2) && !SWI[7];
end

//Questão 3
logic cofre;
logic relogio;
logic interruptor;

always_comb begin
	cofre <= SWI[0];
	relogio <= SWI[1];
	interruptor <= SWI[2];
	SEG[0] <= (cofre && !(relogio && !interruptor)) && SWI[7];
end

//Questão 4
logic a;
logic b;
logic c;
logic d;

always_comb begin
	a <= SWI[3];
	b <= SWI[4];
	c <= SWI[5];
	d <= SWI[6];
	LED[2] <= (((a && b) && !(c && d && b) || (!(a && b) && (c && d && b))) && SWI[7];
end
