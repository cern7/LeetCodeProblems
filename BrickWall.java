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
	
	
	public static void main(String[] args) {
		BrickWall w=new BrickWall();
		List<List<Integer>> wall=new ArrayList<List<Integer>>();
		wall.add(new ArrayList<Integer>());
		wall.get(0).add(0,7);
		wall.get(0).add(1,1);
		wall.get(0).add(2,2);
//		wall.get(0).add(3,1);
		wall.add(new ArrayList<Integer>());
		wall.get(1).add(0,3);
		wall.get(1).add(1,5);
		wall.get(1).add(2,1);
		wall.get(1).add(3,1);
		wall.add(new ArrayList<Integer>());
		wall.get(2).add(0,10);
//		wall.get(2).add(1,3);
//		wall.get(2).add(2,2);
//		wall.add(new ArrayList<Integer>());
//		wall.get(3).add(0,2);
//		wall.get(3).add(1,4);
//		wall.add(new ArrayList<Integer>());
//		wall.get(4).add(0,3);
//		wall.get(4).add(1,1);
//		wall.get(4).add(2,2);
//		wall.add(new ArrayList<Integer>());
//		wall.get(5).add(0,1);
//		wall.get(5).add(1,3);
//		wall.get(5).add(2,1);
//		wall.get(5).add(3,1);
		w.leastBricks(wall);
			
	}
}