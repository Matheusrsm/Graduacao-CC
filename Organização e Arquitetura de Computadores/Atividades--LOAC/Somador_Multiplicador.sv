logic signed [2:0] a;
logic signed [2:0] b;
logic signed [5:0] c;
logic [2:0] e;
logic [2:0] f;

always_comb begin
	
	a <= SWI[7:5];
	b <= SWI[2:0];
	e <= SWI[7:5];
	f <= SWI[2:0];

	unique case(SWI[4:3])
		0: c <= a+b;
		1: c <= a-b;
		2: c <= e*f;
		3: c <= a*b;
	endcase

	case(c)
		-8: SEG[7:0] <= 8'b11111111;
		-7: SEG[7:0] <= 8'b10000111;
		-6: SEG[7:0] <= 8'b11111101;
		-5: SEG[7:0] <= 8'b11101101;
		-4: SEG[7:0] <= 8'b11100110;
		-3: SEG[7:0] <= 8'b11001111;
		-2: SEG[7:0] <= 8'b11011011;
		-1: SEG[7:0] <= 8'b10000110;
		 0: SEG[7:0] <= 8'b00111111;
		 1: SEG[7:0] <= 8'b00000110;
		 2: SEG[7:0] <= 8'b01011011;
		 3: SEG[7:0] <= 8'b01001111;
		 4: SEG[7:0] <= 8'b01100110;
		 5: SEG[7:0] <= 8'b01101101;
		 6: SEG[7:0] <= 8'b01111101;
	default: 
		SEG[7:0] <= 8'b01000000;
	endcase

	LED[5:0] <= c;
	LED[7] <= (c > 3 || c < -4) ? 1:0;
end