package pack3;

public class Ex19Manager extends Ex19Regular {
	@Override
	public double pay() {
		// TODO Auto-generated method stub
		return super.pay();
	}

	@Override
	public void print() {
		display();
		System.out.println("수령액은" + pay() + " " + getIncentive());
	}

	private double incentive;

	public double getIncentive() {
		return incentive;
	}

	public Ex19Manager(String irum, int nai, int salary) {
		super(irum, nai, salary);

		if (salary >= 2500000) {
			incentive = salary * 0.6;
		} else if (salary >= 2000000) {
			incentive = salary * 0.5;
		} else if (salary < 2000000) {
			incentive = salary * 0.4;
		}

	}

}
