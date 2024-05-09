package debugtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import debug.Calculator;

public class CalculatorTest {

//	Calculator calculator = new Calculator();
	Calculator calculator;
	
	@Before
	public void setup() {
		calculator = new Calculator();
	}
	@Test
	public void testPlus() {
		assertTrue(calculator.plus(8, 4) == 12);
		
		int a =8, b = 8;
		assertEquals(a, b); // 변수의 값이 같은지 확인 : assertEquals
		
		int[]arr = {0,0};
		int arr2[] = new int[2];
		assertArrayEquals(arr, arr2);
	}

	@Ignore
	@Test
	public void testMulti() {
		fail("Not yet implemented");
	}

	@Test(timeout = 1000) // 1초 = 1000 단위로 수행시간 검사
	public void testDivide() {
		assertTrue(calculator.divide(8, 4) == 2);
	//	assertTrue(calculator.divide(10, 4) == 2.5);
		
		for (int i = 0; i < 1000000; i++) {
			System.out.print(i + " "); // 1000000하니가 1초이네 못들어옴 err:1.031s
		}
		
	}

}
