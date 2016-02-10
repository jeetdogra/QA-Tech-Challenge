/**
 * This class parses and evaluates the input mathematical expression.
 */

package com.math.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.math.exceptions.InvalidInputMathExpression;

public class MathExpressionEvaluatorUtil {
	
    private ArrayList<String> stringExpressionSplitter(String expression) {
    	
    	ArrayList<String> list = new ArrayList<String>();
    	expression = expression.replaceAll("\\s", "");
    	
    	String afterSymbols = "(?<=[()^/*+])";
    	String afterNums = "(?<=[0123456789.]++)";
    	
    	String afterNumsPreMinus = "(?<=[0123456789.]++-)";
    	String regex = afterSymbols + "|" + afterNums + "|" + afterNumsPreMinus;
    	
        list.addAll(Arrays.asList(expression.split(regex)));
    	
        ArrayList<String> emptyElements = new ArrayList<String>();
        emptyElements.add("");
    	list.removeAll(emptyElements);
    	
    	return list;
    }
    
    private boolean isExpression(ArrayList<String> expression) {
    	
    	String lastElement =  expression.get(expression.size()-1);
    	if (lastElement.matches("[/*+-]")) {
    		return false;
    	}
    	return true;
    }
    
    private String parseAndCalculate(String expression) {
    	ArrayList<String> list = stringExpressionSplitter(expression);
    	
    	if (!isExpression(list)) {
    		throw new NumberFormatException();
    	}
    	
    	while(list.size()>1) {
    		
    		String result;
			int operator;
			int endSubList;
    		
    		// Brackets
    		if (list.indexOf("(") >= 0) {
    			// find the substring between here and the next ')', then recursion
    			operator = list.indexOf("(");
    			endSubList = list.indexOf(")");
    			
    			// prepare the sub-expression (within the brackets)
    			List<String> subexpression = list.subList(operator+1, endSubList);
    			String stringExpression = subexpression.toString().replace(",", "");
    			stringExpression = stringExpression.substring(1, stringExpression.length()-1);
    			stringExpression = parseAndCalculate(stringExpression);
    			
    			// add the result to the list and remove the processed values
    			result = stringExpression;
    			operator++;
    			endSubList++;
    		}
    		
			// Exponents
    		else if (list.indexOf("^") > 0) {
				operator = list.indexOf("^");
    			result = "" + Math.pow(Double.parseDouble(list.get(operator-1)), Double.parseDouble(list.get(operator+1)));
    			endSubList = operator+2;
    		}
			
			// Division & Multiplication
    		else if (list.indexOf("/") > 0 || list.indexOf("*") > 0) {
    			int divIndex = list.indexOf("/");
    			int multIndex = list.indexOf("*");
    			
    			if (multIndex < 0 || (divIndex >= 0 && divIndex < multIndex)) {
    				operator = divIndex;
					result = "" + (Double.parseDouble(list.get(operator-1)) / Double.parseDouble(list.get(operator+1)));
				}
    			else {
    				operator = multIndex;
    				result = "" + (Double.parseDouble(list.get(operator-1)) * Double.parseDouble(list.get(operator+1)));
    			}
    			endSubList = operator+2;
    		}
			
			// Addition & Subtraction
    		else if (list.indexOf("+") > 0 || list.indexOf("-") > 0) {
    			int addIndex = list.indexOf("+");
    			int subIndex = list.indexOf("-");
    			
    			if (addIndex >= 0 && (subIndex < 0 || addIndex < subIndex)) {
    				operator = addIndex;
					result = "" + (Double.parseDouble(list.get(operator-1)) + Double.parseDouble(list.get(operator+1)));
				}
    			else {
    				operator = subIndex;
    				result = "" + (Double.parseDouble(list.get(operator-1)) - Double.parseDouble(list.get(operator+1)));
    			}
    			endSubList = operator+2;
    		}
    		
    		else {
        		throw new NumberFormatException();
        	}
    			
			// add the result to the list and remove the processed values
			list.set(operator-1, result + "");			
			list.subList(operator, endSubList).clear();
    	}
    	
    	return "" + Double.parseDouble(list.get(0));
    }
    
    public String calculate(String expression) throws InvalidInputMathExpression {
        try {
        	return parseAndCalculate(expression);
		} catch(NumberFormatException e) {
			throw new InvalidInputMathExpression("Expression cannot be evaluated: \"" + expression + "\" ");
		}
    }
}
