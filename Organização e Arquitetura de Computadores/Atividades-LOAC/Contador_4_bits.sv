logic [3:0] contador;

always_comb begin

LED[6:0] <= contador;

  case (contador)
    0: SEG[6:0] <= 7'b0111111;
		1: SEG[6:0] <= 7'b0000110;
		2: SEG[6:0] <= 7'b1011011;
		3: SEG[6:0] <= 7'b1001111;
		4: SEG[6:0] <= 7'b1100110;
		5: SEG[6:0] <= 7'b1101101;
		6: SEG[6:0] <= 7'b1111101;
		7: SEG[6:0] <= 7'b0000111;
		8: SEG[6:0] <= 7'b1111111;
		9: SEG[6:0] <= 7'b1101111;
		10: SEG[6:0] <= 7'b1110111;
		11: SEG[6:0] <= 7'b1111100;
		12: SEG[6:0] <= 7'b0111001;
		13: SEG[6:0] <= 7'b1011110;
		14: SEG[6:0] <= 7'b1111001;
		15: SEG[6:0] <= 7'b1110001;
  endcase
end

always_ff @(posedge clk_2) begin
  contador <= contador + 1;
end
