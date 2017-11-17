package Cachesim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

class Cache
{   
	Set sets[];
    int setAssoc, hit, miss;
    Cache(int passedSets, int passedAssoc, int passedlines)
    {
        sets = new Set[passedSets];
        for(int i = 0; i < sets.length; i++)
        {
            sets[i] = new Set(passedAssoc, passedlines);
        }
        hit = 0; miss = 0; 
    }    
   void fromTrace(String fileName, int bs, int ss) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(fileName));
    	String line;  
    	
    	//Reading the input file line by line
    	while ((line = br.readLine()) != null) 
    	{
    		 String[] tokens=line.split(" ");
    		 String inst=tokens[0];
    		 String off=tokens[1];
    		 String addr=tokens[2];
    		 String longpc= new BigInteger(addr, 16).toString(2);
    		 String pc;
    		 int a1;
    		 int length;
    		 if(longpc.length()>32)
    			 {
    			 length=longpc.length()-32;
    		     pc=longpc.substring(length);
    			 }
    		 else
    		     {
    			 length=32-longpc.length();
    			 String s="";
    				for(a1=0;a1<length;a1++)
    				{
    					s=s+"0";
    				}
    			 pc=s+longpc.substring(0);
    		     }
    		 int tag_size=pc.length()-ss-bs;
    		 String tag=pc.substring(0,tag_size);
    		 String set=pc.substring(tag_size, tag_size+ss);
    		 int set_num;
    		 if(set.equals(""))
    		 set_num=0;
    		 else
    	     set_num=Integer.parseInt(set, 2);
             int ifHit = sets[set_num].checkSet(tag);
             if(ifHit==1) {
                 hit++;
             }
             else {
                miss++;
             }
        }
    }
   //Printing Results
    void Results() {
        System.out.println("Hit Count is: " + hit);
        System.out.println("Miss Count is: " + miss);
        System.out.println("Cold misses are: " + Cachesim.cold);
        System.out.println("Conflict misses are: " + Cachesim.conflict);
        System.out.println("Capacity misses are: " + Cachesim.capacity);
    }
}
