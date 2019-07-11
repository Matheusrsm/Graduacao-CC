parameter NINSTR_BITS = 32;
parameter NBITS_TOP = 8, NREGS_TOP = 32, NBITS_LCD = 64;
module top(input  logic clk_2,
           input  logic [NBITS_TOP-1:0] SWI,
           output logic [NBITS_TOP-1:0] LED,
           output logic [NBITS_TOP-1:0] SEG,
           output logic [NBITS_TOP-1:0] lcd_a, lcd_b;
           output logic [NINSTR_BITS-1:0] lcd_instruction,
           output logic [NBITS_TOP-1:0] lcd_registrador [0:NREGS_TOP-1],
           output logic [NBITS_TOP-1:0] lcd_pc, lcd_SrcA, lcd_SrcB,
             lcd_ALUResult, lcd_Result, lcd_WriteData, lcd_ReadData, 
           output logic lcd_MemWrite, lcd_Branch, lcd_MemtoReg, lcd_RegWrite);

parameter PASS = 16;
parameter BITS = 4;
parameter NOM = 4;
parameter FRAN = 12;
parameter INT = 3;

logic [(BITS * FRAN) + INT:0] a, d1, d2, d3, d4, d5, m, pi, t;
logic reset;

always_comb begin
	reset <= SWI[0];
	t <= 1 << (BITS * FRAN);
	m <= NOM * t;
	d1 <= m / a-m / (a+2);
	d2 <= m / (a+4)-m / (a+6);
	d3 <= m / (a+8)-m / (a+10);
	d4 <= m / (a+12)-m / (a+14);
	lcd_a <= a;
	lcd_b <= pi;
end

always_ff @(posedge clk_2) begin
	if(reset) begin
		a <= 1;
		pi <= 0;
	end
	else begin
		if (d1 != 0 && d2 != 0 && d3 != 0 && d4 != 0) begin
			a <= a + PASS;
			pi <= pi + d1 + d2 + d3 + d4;
		end
	end
end

endmodule