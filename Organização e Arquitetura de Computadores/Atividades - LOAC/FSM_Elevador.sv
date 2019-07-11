// Nota = 7.0

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

logic clk_1;

always_ff @(posedge clk_2) begin
	clk_1 <= ~clk_1;
end

logic reset;
logic [3:1] ir;
logic sobe;
logic desce;
logic [1:0] andar;

parameter andar1 = 1, andar2 = 2, andar3 = 3;

always_comb begin
	ir <= SWI[3:1];
	reset <= SWI[0];
end

always_ff @(posedge clk_1) begin
	if (reset) begin
		andar <= andar1;
		sobe <= 0;
		desce <= 0;
	end
	else begin
		unique case(andar)
			0: andar <= andar;
			1: 
			begin
				if (ir == 1) begin
					andar <= andar1;
					sobe <= 0;
					desce <= 0;
				end
				else if (ir == 2) begin
					andar <= andar2;
					sobe <= 1;
					desce <= 0;
				end
				else if (ir == 3) begin
					andar <= andar3;
					sobe <= 1;
					desce <= 0;
				end
			end
			2:
			begin
				if (ir == 1) begin
					andar <= andar1;
					sobe <= 0;
					desce <= 1;
				end
				else if (ir == 2) begin
					andar <= andar2;
					sobe <= 0;
					desce <= 0;
				end
				else if (ir == 3) begin
					andar <= andar3;
					sobe <= 1;
					desce <= 0;
				end
			end
			3:
			begin
				if (ir == 1) begin
					andar <= andar1;
					sobe <= 0;
					desce <= 1;
				end
				else if (ir == 2) begin
					andar <= andar2;
					sobe <= 0;
					desce <= 1;
				end
				else if (ir == 3) begin
					andar <= andar3;
					sobe <= 0;
					desce <= 0;
				end
			end
		endcase
	end
end

always_comb begin
	SEG[7] <= clk_1;
	LED[6] <= sobe;
	LED[7] <= desce;
	LED[2:0] <= andar;
end

endmodule