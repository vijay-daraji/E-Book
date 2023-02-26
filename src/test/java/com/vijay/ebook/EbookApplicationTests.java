package com.vijay.ebook;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class EbookApplicationTests {
	
	private Calculator c = new Calculator();

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	void testSum() {
		int expected = 17;
		int result = c.doSum(12, 3, 2);
		
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	void testProduct() {
		int excepted = 6;
		int result = c.doProduct(3, 2);
		
		assertThat(result).isEqualTo(excepted);
	}
	
	@Test
	void testCompareTwoNums() {
		boolean result = c.compareTwoNums(3, 3);
		assertThat(result).isTrue();
		
	}
}
