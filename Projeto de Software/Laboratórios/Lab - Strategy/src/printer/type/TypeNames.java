package printer.type;

public enum TypeNames {
	
	ITALIC {
		@Override
		public Type getType() {
			return new Italic();
		}
	},
	
	BOLD {
		@Override
		public Type getType() {
			return new Bold();
		}
	},
	
	UPPERCASE {
		@Override
		public Type getType() {
			return new Uppercase();
		}
	},
	
	LOWERCASE {
		@Override
		public Type getType() {
			return new Lowercase();
		}
	};
	
	public abstract Type getType();	
}