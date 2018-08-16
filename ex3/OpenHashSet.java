/**
 * FILE : OpenHashSet.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * a hash-set based on chaining. Extends SimpleHashSet.
 */
import java.util.TreeSet;
import java.util.LinkedList;


public class OpenHashSet extends SimpleHashSet {
	
	private int size = 0;
	private StringList[] table = new StringList[INITIAL_CAPACITY]; 
			
	/**
	 * Constructs a new, empty table with the given load factors,
	 * and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
	}
	
	/**
	 * A default constructor.
	 * Constructs a new, empty table with default initial capacity (16),
	 * an upper load factor (0.75) and a lower load factor (0.25).
	 */    
	public OpenHashSet() {
		super();
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements
	 * one by one.
	 * Duplicate values should be ignored.
	 * The new table has the default values of initial capacity 
	 * (16), upper load factor (0.75), and lower load factor (0.25). 
	 * @param data Values to add to the set.
	 */
	public OpenHashSet(java.lang.String[] data) {
		this();
		for(String str : data)
			add(str);
	}
	/**
	 * Adds a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False if newValue already exists in the set
	 */
	@Override
	public boolean add(java.lang.String newValue) {
		int bucket = clamp(newValue.hashCode());
		if(table[bucket] == null)
			table[bucket] = new StringList(new LinkedList<String>());
		
		if(!table[bucket].contains(newValue)) {
			table[bucket].add(newValue);
			size++;
			if((float)size/table.length > getUpperLoadFactor())
				rehash(true);
			return true;
		}
		return false;
	}
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public boolean contains(java.lang.String searchVal) {
		int bucket = clamp(searchVal.hashCode());
		if(table[bucket] == null)
			return false;
		return table[bucket].contains(searchVal);
	}
	/**
	 * Deletes the input element from the set.
	 * @param toDelete Value to delete
	 * @return True if toDelete is found and deleted
	 */
	@Override
	public boolean delete(java.lang.String toDelete) {
		int bucket = clamp(toDelete.hashCode());
		if(table[bucket] == null)
			return false;
		if(table[bucket].delete(toDelete)) {
			size--;
			if(1 < table.length && (float)size/table.length < getLowerLoadFactor())
				rehash(false);
			return true;
		}
		return false;
	}
	/**
	 * @return The current size of the table.
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	@Override
	public int capacity() {
		return table.length;
	}

	/**
	 * If we are under "lowerLoadFactor" or above "upperLoadFactor", we must resize the table and re-insert
	 * the whole membrane.
	 * @param increaseSize - a boolean factor thats tell us if we need to rehash.
	 */
	private void rehash(boolean increaseSize) {
		int newSize = increaseSize ? table.length * 2 : table.length / 2;
		capacityMinusOne = newSize-1;
		StringList[] oldTable = table; 
		table = new StringList[newSize];
		for(int i = 0 ; i < oldTable.length ; i++)
		{
			if(oldTable[i] == null)
				continue;
			for(String val : oldTable[i]) {
				int bucket = clamp(val.hashCode());
				if(table[bucket] == null)
					table[bucket] = new StringList(new LinkedList<String>());
				table[bucket].add(val);
			}
		}
	}
}
