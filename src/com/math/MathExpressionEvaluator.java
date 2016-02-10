/**
 * Objective: To be able to consume any Math expression as an input and then validate and evaluate it and print out its result in the console.
 * 
 * GIVEN:
 * 1. the application supports following mathematical operators, i.e. +, -, /, *, ^ and
 * 2. grouping using parenthesis, i.e. ()
 * WHEN a mathematical expression is passed as an input to the application
 * THEN the application:
 * 1. check if its valid and
 * 2. if valid, parses and evaluates it and
 * 3. prints the outcome to console
 * 
 */

package com.math;

import com.math.exceptions.InvalidInputMathExpression;
import com.math.lib.MathExpressionEvaluatorUtil;

public class MathExpressionEvaluator {

	public static void main(String args[]) throws InvalidInputMathExpression{
		MathExpressionEvaluatorUtil mathExpEvaluator = new MathExpressionEvaluatorUtil();
		String inputExp = "1 + 2 ^ 3 - 3 + 4 / 2";
		System.out.println("Math output for " + inputExp + " :: " + mathExpEvaluator.calculate(inputExp));
	}
}
