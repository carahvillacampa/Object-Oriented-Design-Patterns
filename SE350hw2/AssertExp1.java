package hw2;
import java.util.*; // for comparing arrays in main() tests only

class AssertExp1  {

	/*
	 * minValue returns the minimum value in an array of doubles. You can assume
	 * the array is nonempty and has no duplicates. Your solution must go
	 * through the array exactly once. Your solution must not call any other
	 * functions. Here are some examples (using "==" informally):
	 * @param array of type double
	 * @return minimum value generated from array 
	 * 
	 */
	public static double minValue(double[] list) {
		double min = list[0];
		for (int i = 0; i < list.length; ++i) {
			if (list[i] < min) {
				min = list[i];
			}
		}
		return min;
	}

	/* 
	 * minPosition returns the position of the minimum value in an array of
	 * doubles. The first position in an array is 0 and the last is the
	 * array.length-1. You can assume the array is nonempty and has no
	 * duplicates. Your solution must go through the array exactly once. Your
	 * solution must not call any other functions. Here are some examples (using
	 * "==" informally):
	 * 
	 * 0 == minPosition(new double[] { -7 }) 2 == minPosition(new double[] { 1,
	 * -4, -7, 7, 8, 11 }) 0 == minPosition(new double[] { -13, -4, -7, 7, 8, 11
	 * }) 6 == minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 * 
	 * @param array of type double
	 * @return index of the smallest value from array 
	 */
	public static int minPosition(double[] list) {
		double min = 0;
		int index=-1;
		
		if(list.length==0) {
			return -1;
		}
		
		if(list.length==1) {
			return 0;
		}
		else {
			for (int i = 0; i < list.length; ++i) {
				if (list[i] < min) {
					min = list[i];
					index=i;
				}
			}
		}
		return index;
		
	}


	/*
	 * numUnique returns the number of unique values in an array of doubles.
	 * Unlike the previous questions, the array may be empty and it may contain
	 * duplicate values. Also unlike the previous questions, you can assume the
	 * array is sorted.
	 * 
	 * Your solution must go through the array exactly once. Your solution must
	 * not call any other functions. Here are some examples (using "=="
	 * informally):
	 * 
	 * 0 == numUnique(new double[] { }) 1 == numUnique(new double[] { 11 }) 1 ==
	 * numUnique(new double[] { 11, 11, 11, 11 }) 8 == numUnique(new double[] {
	 * 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }) 8
	 * == numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66,
	 * 77, 88 })
	 * 
	 * @param array of type double
	 * @return number of values in the array 
	 */
	public static int numUnique(double[] list) {
		
		assert(isSortedList(list));
		int uniqueCount;
	      if(list.length== 0){
		     return 0;
	      }      
	      else{
			if (list.length == 1){
				 uniqueCount= 1;
			}	
			else{	
			uniqueCount=1;
			for( int i=1; list.length>i; i++){
				uniqueCount++;
				if(list[i-1] == list[i]){
					uniqueCount--;
					
				}
			}
		}		
	}   
	return uniqueCount;		
		
	}
	
	private static double unique(double[] l) {
		Set<Double> setUnique= new LinkedHashSet<>();
		for(double x : l) {
			setUnique.add(x);
		}
		return setUnique.size();
	}
	
	private static boolean isSortedList(double[] list) {
		for(int i=0; i< list.length; i++) {	
			if(list[i]< list[i+1]) {
				//assert(list[i+1] > list[i]);
				return true;
			}
		}
		return false;
	}

	/*
	 * removeDuplicates returns the number of unique values in an array of
	 * doubles. You may assume that the list is sorted, as you did for
	 * numUnique.
	 * 
	 * Your solution may call numUnique, but should not call any other
	 * functions. After the call to numUnique, you must go through the array
	 * exactly one more time. Here are some examples (using "==" informally):
	 * 
	 * new double[] { } == removeDuplicates(new double[] { }) new double[] { 11
	 * } == removeDuplicates(new double[] { 11 }) == removeDuplicates(new
	 * double[] { 11, 11, 11, 11 }) new double[] { 11, 22, 33, 44, 55, 66, 77,
	 * 88 } == removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44,
	 * 44, 44, 44, 55, 55, 66, 77, 88, 88 }) == removeDuplicates(new double[] {
	 * 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
	 * 
	 * @param array of type double
	 * @return an array of doubles free of duplicates
	 */
	
	public static double[] removeDuplicates(double[] list) {
		assert(isSortedList(list));
		double [] newArray= new double[numUnique(list)];
		
		if(list.length ==0) { //if it's empty, return an empty array
			return newArray;
		}
		double current= list[0];
		newArray[0]= current;
		
		int position=1;
		for(int i=1; i < list.length; i++) {
			if(list[i] != current) {
				newArray[position]= list[i];
				position++;
				current= list[i];
			}
		}
		return newArray;
		}


}
