/*
 * Given n pairs of parentheses, write a function to generate
 *  all combinations of well-formed parentheses.
 */
import java.util.*;

public class GenerateParentheses {
	
	private void _generateParentheses(List<String> parentheses, 
										String tempParentheses, 
										int leftParentheses, 
										int rightParentheses) {
		
		/*
		 * [base condition]
		 * if both rightParentheses and leftParentheses are equal
		 * to 0 then add tempParentheses to parentheses container
		 */
		if(leftParentheses == 0 && rightParentheses == 0) { 
			parentheses.add(tempParentheses);
			return;
		}
		/*
		 * Checking if there are still left parentheses to be added. On every function call, 
		 * add one left parentheses character to the temporal string variable, decrease the 
		 * number of left parentheses to be added and increase the number of right parentheses 
		 * to be added                                                             
		 */
		if(leftParentheses > 0)
			_generateParentheses(parentheses, tempParentheses + "(", 
								leftParentheses - 1, rightParentheses + 1);
		/*
		 * Checking if there are still right parentheses to be added. On every function call 
		 * add one right parentheses to the temporal string variable, decrease the number of 
		 * right parentheses and keep the value of left parentheses    
		 */
		if(rightParentheses > 0)
			_generateParentheses(parentheses, tempParentheses + ")",
								leftParentheses, rightParentheses - 1);
	}
	
	public List<String> generateParentheses(int n){
		List<String> resultParentheses;
		resultParentheses = new ArrayList<>();
		
		_generateParentheses(resultParentheses, "", n, 0);
		
		for(int i = 0; i < resultParentheses.size(); i++) {
			System.out.println(resultParentheses.get(i));
		}
		
		return resultParentheses;
		
	}
	public static void main(String[] args) {
		int n=4;
		if (n>0) {
			GenerateParentheses o = new GenerateParentheses();
			o.generateParentheses(n);
		}
		
		

	}

}
