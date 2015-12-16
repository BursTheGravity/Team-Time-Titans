//Team Time Titans -- Sungbin Kim, Leo Au-Yeung, Jason Mohabir
//APCS1 pd10
//Hw48 -- Halfling Time Trials
//2015-12-14

/*============================================
  class OrderedArrayList
  Wrapper class for ArrayList.
  Imposes the restriction that stored items 
  must remain sorted in ascending order
  ============================================*/

//ArrayList's implementation is in the java.util package
import java.util.ArrayList;


public class OrderedArrayList {

    // instance of class ArrayList, holding objects of type Comparable 
    // (ie, instances of a class that implements interface Comparable)
    private ArrayList<Comparable> _data;


    // default constructor initializes instance variable _data
    public OrderedArrayList() {
	_data = new ArrayList<Comparable>();
    }


    public String toString() { 
	return _data.toString(); 
    }


    public Comparable remove( int index ) { 
	return _data.remove(index); 
    }


    public int size() { 
	return _data.size();
    }

    
    public Comparable get( int index ) { 
	return _data.get(index); 
    }


    // addLinear takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a linear search to find appropriate index
    public void addLinear( Comparable newVal ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {
	    if ( newVal.compareTo( _data.get(p) ) < 0 ) { //newVal < oal[p]
		_data.add( p, newVal );
		return; //Q:why not break?
	    }
	}
	_data.add( newVal ); //newVal > every item in oal, so add to end
    }
    

    // addBinary takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a binary search to find appropriate index


    public void addBinary( Comparable newVal ) { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;
	
	while ( lo <= hi ) { //running until target is found or bounds cross
	    
	    med = (int)((lo + hi) / 2);
	    int x = _data.get(med).compareTo( newVal );
	    
	    if ( x == 0 ) { //equal value found, insert here
		_data.add( med, newVal );
		return;
	    }
	    else if ( x > 0 ) //newVal < med, so look at lower half of data
		hi = med - 1;
	    else //newVal > med, so look at upper half of data
		lo = med + 1;
	}
	// If you make it this far, newVal was not in the ArrayList.
	// So insert at lo. Q: How do you know lo is correct insertion index?
	_data.add( lo, newVal );
    }	
    
    
    // determine whether element present in data structure using linear search
    // return index of occurrence or -1 if not found
    public int findLin( Comparable target ) {
	for (int ctr = 0; ctr < _data.size(); ctr++){
	    if (_data.get(ctr).compareTo(target) == 0) {return ctr;}
	}
	return -1;
    }
    
    // determine whether element present in data structure using binary search
    // return index of occurrence or -1 if not found
    // recursive versions
    public int findBinR( Comparable target ) {
	int lo = 0;
	int hi = _data.size()-1;
	return findBinH( target, lo,  hi);
    }	
    
    public int findBinH( Comparable target, int lo, int hi){
	if (lo < hi) {
	    int med = (int)((lo + hi) / 2);
	    int comp = _data.get(med).compareTo(target);
	    if (comp > 0) {
		return findBinH(target, lo, med - 1);}
	    if (comp < 0) {
		return findBinH(target, med + 1, hi);}
	    return med;
	}
	return -1; //covers appending first digit 
    } // binarySearch
    
    // main method solely for testing purposes
    public static void main( String[] args ) 
    {
    	/*
	OrderedArrayList Franz = new OrderedArrayList();
	
	System.out.println("\nValues to add via addLinear() calls:");
	
	// testing linear search
	for( int i = 0; i < 15; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addLinear( valToAdd );
	}
	
	System.out.println("\nafter population via addLinear() calls:");
	System.out.println( Franz );
	System.out.println();
	
	Franz = new OrderedArrayList();
	
	System.out.println("\nValues to add via addBinary() calls:");
	
	
	// testing binary search
	for( int i = 0; i < 500; i++ ) {
	    int valToAdd = (int)( 100 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addBinary( valToAdd );
	}
	
	System.out.println("\nafter population via addBinary() calls:");
	System.out.println( Franz );
	System.out.println();
	*/
	
	//Experimentation with timing of findLin & findBin
	System.out.println();

		//Adds 500,000 items to OrderedArrayList Titan
		OrderedArrayList Titan = new OrderedArrayList();
		for( int i = 0; i < 500000; i++ ) {
			int valToAdd = (int)( 100000 * Math.random() );
			Titan.addBinary( valToAdd );
		}
		int sizeTitan = Titan.size();
		System.out.println( "Starting timing experimentation of findLin & findBin for " + sizeTitan + " items in OAL." );

		int cnt = 0;
		while( cnt < 10 ) {
		    int valToTest = (int)( 100000 * Math.random() ); //create some random value in same range as numbers in OAL
		
		    //Tests findLin time 
		    System.out.println( "Testing time required for findLin(" + valToTest + "): " );
		    long startLin = System.currentTimeMillis(); //stores the start time for start of findLin
		    Titan.findLin(valToTest); //executes findLin
		    long endLin = System.currentTimeMillis(); //stores the end time for end of findLin
		    
		    //prints out times
		    System.out.println( "Searched value:\t" + Titan.findLin(valToTest);
		    System.out.println( "Time start:\t" + startLin );
		    System.out.println( "Time end:\t" + endLin );
		
		    System.out.println( "Total time required: " + (endLin - startLin) + " milliseconds." ); //calculates the difference, which is the amount of time required
		    System.out.println();
		   
		
		    //Tests findBin time
		    System.out.println( "Testing time required for findBin(" + valToTest + "): " );
		    long startBin = System.currentTimeMillis(); //stores the start time for start of findBin
		    Titan.findBinR(valToTest); //executes findBin
		    long endBin = System.currentTimeMillis(); //stores the end time for end of findBin
		    
		    //prints out times
		    System.out.println( "Searched value:\t" + Titan.findLin(valToTest);
		    System.out.println( "Time start:\t" + startBin );
		    System.out.println( "Time end:\t" + endBin );
		
		    System.out.println( "Total time required: " + (endBin - startBin) + " milliseconds." ); //calculates the difference, which is the amount of time required
		    System.out.println();

		    cnt++;
			}		
    }
   
}//end class OrderedArrayList

