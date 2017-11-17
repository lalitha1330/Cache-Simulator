package Cachesim;

import java.util.*;
class Set {
    ArrayDeque<String> elements; 
    int setlength;
    int setfull;
    public Set(int setlength, int setfull)
    {
        this.setlength = setlength;
        this.setfull = setfull;
        if(setfull!=setlength)
        elements = new ArrayDeque<String>();
        else
        elements = new ArrayDeque<String>(setlength);
    }
    
    //Check if tag is present
    int checkSet(String t) {
        if(elements.contains(t)) { 
            updateSet(t);
            return 1;
        }
        insertIntoSet(t);
        return 0;
    }
    
    //Update LRU if tag is present
    void updateSet(String g) {
        Iterator<String> SetIterator = elements.iterator(); 
        while(SetIterator.hasNext()) { 
            String elementToCheck = SetIterator.next(); 
            if(elementToCheck.equals(g)) { 
                SetIterator.remove();  
                break;
            }
        }
        elements.add(g);
    }
 
    //Insert new tag into Set
   void insertIntoSet(String tg) {
        if(elements.size() >= setlength) { 
            elements.remove();
            Cachesim.conflict++;
            }
        else
        	{
        	Cachesim.cold++;
        	}
        elements.add(tg);
        
    }
}
