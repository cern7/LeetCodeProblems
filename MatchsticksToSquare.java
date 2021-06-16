/*
 * You are given an integer array matchsticks where 
 * matchsticks[i] is the length of the ith matchstick. 
 * You want to use all the matchsticks to make one square. 
 * You should not break any stick, but you can link them up, 
 * and each matchstick must be used exactly one time.
 * 
 * Return true if you can make this square and false otherwise.
 */
import java.util.*;
import java.util.stream.IntStream;

public class MatchsticksToSquare {
	
	private boolean constructSide(int[] matchsticks, List<Boolean> chosen, int sideLength) {
		//if the square side is 0 return true
		if (sideLength == 0)
			return true;
		//going through the array(matchsticks) values
		for(int i = matchsticks.length - 1; i >= 0; i--) {
			/*check if the value is in the side length range
			 * and if the value is already used
			 */
			if(matchsticks[i] <= sideLength && !chosen.get(i)) {
				//add the value position to an auxiliary ArrayList
				chosen.set(i,  true);
				//using recursion to check the next array element
				boolean result = constructSide(matchsticks, chosen, sideLength - matchsticks[i]);
				
				/*
				 * if not true
				 * if the array value isn't less or equal to side
				 * length and if the value isn't already used
				 * set it to false in the auxiliary array List
				 */
				if (!result) 
					chosen.set(i, false);
				else return result;
			}
		}
		return false;
	}
	
	//trying to make a square from given array of sticks
	public boolean makesquare(int[] matchsticks) {
		
		/* using IntStream(java 8) which is a sequence of elements. 
		 * It has an 'of' method taking an array as argument and returns 
		 * a stream of the elements of array. Calling the 'sum' method on 
		 * the returned stream provides the sum of array elements
		 */
		IntStream stream=IntStream.of(matchsticks);
		int sum = stream.sum();
		
		final int numSides = 4;
		int sideLength = sum / numSides;
		boolean result = true;
		List<Boolean> chosen;
		
		//checking if the side length has an integer value
		if(sum % numSides != 0)
			return false;
		
		/*checking if the array greatest value is exceeding 
		 * the square side length 
		 */
		Arrays.sort(matchsticks);
		if(matchsticks[matchsticks.length - 1] > sideLength)
			return false;
		
		/*initialising the ArrayList of 'matchsticks' length
		 *  with all values set to 'false'
		 */
		chosen = new ArrayList<>();
		for (int i = 0; i < matchsticks.length; i++)
			chosen.add(false);
		
		//calling four times the constructSide method
		for (int i=0; i<numSides && result; i++)
			result=constructSide(matchsticks, chosen, sideLength);
		
		return result;
	}
	
	public static void main(String[] args) {
		MatchsticksToSquare obj=new MatchsticksToSquare();
//		int [] side = {1,2,2,1,2};
		int [] side = {1,5,4,3,1,5,1};
		System.out.println(obj.makesquare(side));
		
	}

}
