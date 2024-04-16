package pack3;

public abstract class Ex19Employee {
	private String irum;
	private int nai;

	public Ex19Employee() {

	}

	public Ex19Employee(String irum, int nai) {
		this.irum = irum;
		this.nai = nai;
	}

	public abstract double pay();

	public abstract void print();

	public void display() {
		System.out.println("이름은: " + irum + "나이는: " + nai);
	}
}
