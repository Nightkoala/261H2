/**
 * Majority.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 * 
 * Purpose	Given the size of an array of integers, and a listing of
 *		integers first determine whether a value consists of the
 *		majority of of the numbers, then determine whether there
 *		exists a value that makes up a third of the list.
 *
 */

import java.util.Scanner;

public class Majority {
	
	// Attributes
	
	private int numberOfValues;
	private int[] theValues;
	
	// Constructor
	
	/**
	 * Constructor for creating an instance of Majority
	 * 
	 * @param numVals	The number of values the data set holds
	 * @param values	The data set of values
	 */
	public Majority( int numVals, int[] values ) {
		this.numberOfValues = numVals;
		this.theValues = values;
	}//end Majority constructor
	
	// Methods
	
	/**
	 * Creates a smaller array containing all of the values less than the
	 * pivot
	 * 
	 * @param values	The array of values
	 * @param pivot		the value checking against
	 * 
	 * @return	The array of values less than the pivot
	 */
	public int[] createSmallerList( int[] values, int pivot ) {
		int size = 0;
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] < pivot ) {
				size++;
			}//end if
		}//end for
		int[] smallerList = new int[size];
		size = 0;
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] < pivot ) {
				smallerList[size] = values[i];
				size++;
			}//end if
		}//end for
		return smallerList;
	}//end createSmallerList
	
	/**
	 * Creates an array containing all of the values greater than the pivot
	 * 
	 * @param values	The array of values
	 * @param pivot		The value checking against
	 * 
	 * @return	The array of values greater than the pivot
	 */
	public int[] createBiggerList( int[] values, int pivot ) {
		int size = 0;
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] > pivot ) {
				size++;
			}//end if
		}//end for
		int[] biggerList = new int[size];
		size = 0;
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] > pivot ) {
				biggerList[size] = values[i];
				size++;
			}//end if
		}//end for
		return biggerList;
	}//end createBiggerList
	
	/**
	 * Counts the number of times that the pivot occurs in the array
	 * 
	 * @param values	The array of values to test
	 * @param pivot		The value we are counting
	 * 
	 * @return	The number of times pivot occurs in values
	 */
	public int countPivot( int[] values, int pivot ) {
		int count = 0;
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] == pivot ) {
				count += 1;
			}//end if
		}//end for
		return count;
	}//end countPivot
	
	/**
	 * Recursive function that finds the median in an array of values.
	 * 
	 * @param values	The array of values.
	 * @param k		The position the median would be if array was
	 *			sorted.
	 * 			k = (length of values)/2 rounded down.
	 * 
	 * @return	The median of values.
	 */
	public int findMedian( int[] values, int k ) {
		int mid = (int) Math.floor(values.length/2);
		int pivot = values[mid];
		int[] smallerList = createSmallerList( values, pivot );
		int[] biggerList = createBiggerList( values, pivot );
		int count = countPivot( values, pivot );
		int size = smallerList.length;
		if( ( k >= size ) && ( k < ( size+count ) ) ) {
			return pivot;
		}//end if
		else if( k < size ) {
			return findMedian( smallerList, k);
		}//end else if
		else {
			return findMedian( biggerList, k-size-count );
		}//end else
	}//end findMedian
	
	/**
	 * Determines if there exists a value in values that occurs more than
	 * n/2 times.
	 * 
	 * @param values	The array of values.
	 * @param median	The median of values.
	 * 
	 * @return	true, if a majority exists.
	 * 			false, otherwise.
	 */
	public boolean isMajority( int[] values, int median ) {
		int size = (int) Math.floor(values.length/2);
		int[] smallerList = createSmallerList( values, median );
		int[] biggerList = createBiggerList( values, median );
		int num_median = countPivot( values, median );
		if( num_median >= ( size+1 ) ) {
			return true;
		}//end if
		boolean littleMajority = true;
		int firstValue = smallerList[0];
		for( int i = 1 ; i < smallerList.length ; i++ ) {
			if( smallerList[i] != firstValue ) {
				littleMajority = false;
				break;
			}//end if
		}//end for
		if( littleMajority ) {
			if( firstValue != median ) {
				littleMajority = false;
			}//end if
		}//end if
		boolean bigMajority = true;
		firstValue = biggerList[0];
		for( int i = 1 ; i < biggerList.length ; i++ ) {
			if( biggerList[i] != firstValue ) {
				bigMajority = false;
				break;
			}//end if
		}//end for
		if( bigMajority ) {
			if( firstValue != median ) {
				bigMajority = false;
			}//end if
		}//end if
		return (littleMajority || bigMajority );
	}//end isMajority

	/**
	 * Checks if there is a value that occurs in the data set at more than
	 * n/3 times
	 * 
	 * @param values	the dataset of values to check
	 * 
	 * @return	true, if there exists a value
	 * 			false, otherwise
	 */
	public boolean isMajority3( int[] values ) {
		int size = (int) Math.ceil(values.length/3);
		int median1 = findMedian( values, size);
		int numMedian1 = countPivot( values, median1 );
		if( numMedian1 > size ) {
			return true;
		}//end if
		int[] smallerList1 = createSmallerList( values, median1 );
		boolean smallMajority1 = true;
		int firstValue = smallerList1[0];
		for( int i = 0 ; i < smallerList1.length ; i++ ) {
			if( smallerList1[i] != firstValue ) {
				smallMajority1 = false;
				break;
			}//end if
		}//end for
		if( smallMajority1 ) {
			if( firstValue != median1 ) {
				smallMajority1 = false;
			}//end if
		}//end if
		int[] condensedValues = createBiggerList( values, median1 );
		int median2 = findMedian( condensedValues, (int) Math.ceil(
				condensedValues.length/2));
		int numMedian2 = countPivot( condensedValues, median2 );
		if( numMedian2 > size ) {
			return true;
		}//end if
		int[] smallerList2 = createSmallerList( condensedValues, median2 );
		System.out.println(median2);
		boolean smallMajority2 = true;
		if( smallerList2.length != 0 ) {
			smallMajority2 = true;
			firstValue = smallerList2[0];
			for( int i = 0 ; i < smallerList2.length ; i++ ) {
				if( smallerList2[i] != firstValue ) {
					smallMajority2 = false;
					break;
				}//end if
			}//end for
			if( smallMajority2 ) {
				if( firstValue != median2 ) {
					smallMajority2 = false;
				}//end if
			}//end if
		}//end if
		else if( smallerList2.length == 0) {
			smallMajority2 = false;
		}//end else
		int[] biggerList = createBiggerList( condensedValues, median2 );
		boolean bigMajority = true;
		firstValue = biggerList[0];
		for( int i = 0 ; i < biggerList.length ; i++ ) {
			if( biggerList[i] != firstValue ) {
				bigMajority = false;
				break;
			}//end if
		}//end for
		if( bigMajority ) {
			if( firstValue != median2 ) {
				bigMajority = false;
			}//end if
		}//end if
		return ( smallMajority1 || smallMajority2 || bigMajority );
	}//end isMajority3
	
	/**
	 * Reads in the data to be used,
	 * Determines if there exists a value in the data set that occurs more
	 * than n/2 times,
	 * Determines if there exists a value in the data set that occurs more
	 * than n/3 times,
	 * Displays YES or NO for each depending on outcome.
	 * 
	 * @param args	not used.
	 */
	public static void main( String[] args ) {
		
		// Read in the values
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		int numVals = Integer.parseInt(input);
		int[] values = new int[numVals];
		for( int i = 0 ; i < numVals ; i++ ) {
			input = sc.next();
			int val = Integer.parseInt(input);
			values[i] = val;
		}//end for
		sc.close();
		Majority M = new Majority( numVals, values );
		
		// Find n/2 Majority
		int median = M.findMedian(values, (int) Math.ceil(
			values.length/2) );
		boolean halfMajority = M.isMajority( values, median );
		if( halfMajority ) {
			System.out.println("YES");
			System.out.println("YES");
		}//end if
		else {
			System.out.println("NO");
			boolean thirdMajority = M.isMajority3( values );
			if( thirdMajority ) {
				System.out.println("YES");
			}//end if
			else {
				System.out.println("NO");
			}//end else
		}//end else
		
		// Find n/3 Majority
	}//end main
}//end Majority
