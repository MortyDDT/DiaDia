package it.uniroma3.diadia;

public enum Direzione {
	nord(0) { 
		@Override
		public Direzione opposta() { return sud; }
	},

	sud(180) {
		@Override
		public Direzione opposta() { return nord; }
	},

	est(90) { 
		@Override
		public Direzione opposta() { return ovest; }
	},

	ovest(270) { 
		@Override
		public Direzione opposta() { return est; }
	}; 


	private final int gradi;


	private Direzione(int gradi) { 
		this.gradi= gradi;
	}

	public int getGradi() { return this.gradi; }
	public abstract Direzione opposta();

}
