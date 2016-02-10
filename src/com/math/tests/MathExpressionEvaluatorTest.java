/**
 * This class contains all the unit tests for the MathExpressionEvaluatorUtil.java
 */

package com.math.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.math.exceptions.InvalidInputMathExpression;
import com.math.lib.MathExpressionEvaluatorUtil;

public class MathExpressionEvaluatorTest {
	
	@Test
	public void testMathSimpleAdd() throws InvalidInputMathExpression {
		MathExpressionEvaluatorUtil mathExpEval = new MathExpressionEvaluatorUtil();
		String inputExpression = "1.0 + 2.0";
		final String expectedValue = "3.0";
	    assertEquals(expectedValue, mathExpEval.calculate(inputExpression));
	}
	
	@Test
	public void testMathSpacing() throws InvalidInputMathExpression {
		MathExpressionEvaluatorUtil mathExpEval = new MathExpressionEvaluatorUtil();
		String inputExpression = "         3.0        +7.0          ";
		final String expectedValue = "10.0";
	    assertEquals(expectedValue, mathExpEval.calculate(inputExpression));
	}
}
