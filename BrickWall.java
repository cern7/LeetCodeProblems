/*
 * There is a rectangular brick wall in front of you with n rows of bricks.
The ith row has some number of bricks each of the same height (i.e., one unit) 
but they can be of different widths. The total width of each row is the same.

Draw a vertical line from the top to the bottom and cross the least bricks. 
If your line goes through the edge of a brick, then the brick is not considered as crossed. 
You cannot draw a line just along one of the two vertical edges of the wall, in which case 
the line will obviously cross no bricks.

Given the 2D array wall that contains the information about the wall, return the minimum 
number of crossed bricks after drawing such a vertical line.
 */

import java.util.*;
import java.util.Map.Entry;

public class BrickWall{
	public static int leastBricks(List<List<Integer>> wall) {
		int rowSum=0; //sum of the row values
		int rowLength=0;
		int numberOfBricks=0; //number of layers crossed by a line
		// determine the sum
		for(int k=0;k<wall.get(0).size();k++) {
			rowSum+=wall.get(0).get(k);
		}
		for(int r=0;r<wall.size();r++) {
			for(int g=0;g<wall.get(r).size();g++) {
				if(rowLength<g)
					rowLength=g;
			}
		}
		
		int edgeSum=0;// position of brick edge on each layer
		// a 2D array to store the edge position
		int [][] edge=new int [wall.size()][rowLength+1];
		//pushing the brick edges into the array
		if(edgeSum<=rowSum) {
			for(int i=0;i<wall.size();i++) {
				for(int j=0;j<wall.get(i).size();j++) {
					edgeSum+=wall.get(i).get(j);
					edge[i][j]=edgeSum;
				}
			
			edgeSum=0;
			}
		}
		
		//countMap holds the count details of each element from the edge array
		Map<Integer, Integer> countMap=new HashMap<Integer, Integer>();
		/*check if each of the edge array element is present in countmap using containsKey() method
		 *if the element is present => increment the count by 1
		 * and put the element back into the countMap
		 * 
		 * excluding the values 0(zero) and "rowSum"=>
		 * we cannot draw the line at the edge of the wall
		 */
		if(edge[0].length!=1) {
			for(int a=0;a<edge.length;a++) {
				for(int s=0;s<edge[0].length;s++) {
					int count=0;
					int key=edge[a][s];
					if(countMap.containsKey(key)) {
						count=countMap.get(key);
						count++;
						countMap.put(key, count);
					}else if(key!=0 && key!=rowSum) {
						countMap.put(key, 1);
					}
				}
			}
			/*
			 * Find the number of occurrences of the values,
			 * assigne the highest to the "occurrence",
			 * this value represents the number of layers
			 * that contain the edge in that particular position.
			 * Drawing the line in this position we cut the 
			 * minimum number of layers.
			 * Substract the maximum value from the number of layers(wall.size())
			 */
				
			int occurrence=0;
			for(Entry<Integer, Integer> val: countMap.entrySet()) {
//				System.out.println(val.getKey()+" "+val.getValue());
				if(val.getValue()>occurrence) {
					occurrence=val.getValue();
					numberOfBricks=wall.size()-occurrence;
				}
			}
			
		}else
			numberOfBricks=edge.length;
		
		System.out.println(numberOfBricks);
		return numberOfBricks;
	}
}
