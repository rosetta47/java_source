package pack3;

public class Ex19Temporary extends Ex19Employee {
	@Override
	public double pay() {
		return ilsu*ildang;
	}
	@Override
	public void print() {
		display();
		System.out.println("월급은"+pay()+"원");
		
	}

	private int ilsu;
	private int ildang;
	
	public Ex19Temporary(String irum, int nai, int ilsu, int ildang) {
		super(irum, nai);
		this.ilsu = ilsu;
		this.ildang = ildang;
		
	}
}
