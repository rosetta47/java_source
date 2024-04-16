package pack3;

public class Ex15PolyTaxi extends Ex15PolyCar{
	private int passenger = 2;
	
	public Ex15PolyTaxi() {
		//super();생략됨
		System.out.println("난 택시라고 해~");
	}
	
	@Override
	public void dispalySpeed() {
		System.out.println("택시 승객은" + passenger+"명");
	}
}
