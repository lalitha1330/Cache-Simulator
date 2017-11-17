package Cachesim;
import java.io.IOException;
class Cachesim 
{
	static int cold=0;       //cold misses
	static int capacity=0;   //capacity misses
	static int conflict=0;   //capacity misses
    public static void main(String[] args) throws IOException
    {  
    	//Taking inputs as arguments
    	
    	String filename=args[0];
    	int m=Integer.parseInt(args[1]);
    	int n=Integer.parseInt(args[2]);
    	int setAssoc=Integer.parseInt(args[3]);
    	int l=m/n;                       //Number of lines
    	int num_of_Sets=l/setAssoc;          //Number of sets
    	int block_size=(int) (Math.log(n) / Math.log(2));	    //Block size
    	int set_size=(int) (Math.log(num_of_Sets) / Math.log(2));   //Set size
        Cache cache = new Cache(num_of_Sets, setAssoc , l);         //Cache created
        cache.fromTrace(filename,block_size,set_size);
        System.out.println("The number of sets are: "+ num_of_Sets);
        System.out.println("The number of ways are: "+ setAssoc);
        System.out.println("The number of tag bits are: "+ (32-block_size-set_size));
        System.out.println("The number of index bits are: "+ set_size);
        System.out.println("The number of offset bits are: "+ block_size);
        cache.Results();
    }
}
