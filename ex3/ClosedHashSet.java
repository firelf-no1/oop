/**
 * FILE : ClosedHashSet.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * A hash-set based on closed-hashing with quadratic probing. Extends SimpleHashSet.
 */

import java.util.Objects;

public class ClosedHashSet extends SimpleHashSet {
	//used to mark deleted entries
	private static final String DELETED = "";
	private int size = 0;
	private String[] table = new String[INITIAL_CAPACITY];


	/**
	 * Constructs a new, empty table with the specified load factors,
	 * and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		table = new String[INITIAL_CAPACITY];
	}

	/**
	 * A default constructor.
	 * Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public ClosedHashSet() {
		super();
		table = new String[INITIAL_CAPACITY];

	}

	/**
	 * Data constructor - builds the hash set by adding the elements
	 * one by one.
	 * Duplicate values should be ignored.
	 * The new table has the default values of initial capacity
	 * (16), upper load factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public ClosedHashSet(java.lang.String[] data) {
		this();
		for(String str : data)
			add(str);
	}

	@Override
	public boolean add(java.lang.String newValue) {
		if(this.contains(newValue))
			return false;
		if((float)(size + 1)/table.length > getUpperLoadFactor()) {
			rehash(true);
			table[probe(newValue, false)] = newValue;
		}
		else {
			int cell = probe(newValue, false);
			if (cell <= -1) {
				return false;
			}
			table[cell] = newValue;
		}
		size++;
		return true;
	}


	@Override
	public boolean contains(java.lang.String searchVal) {
		int cell = probe(searchVal, true);
		return (cell != -1 && table[cell] != null && !table[cell].equals(DELETED));
	}

	@Override
	public boolean delete(java.lang.String toDelete) {
		int cell = probe(toDelete, true);
		if(cell == -1 || table[cell] == null || table[cell].equals(DELETED))
			return false;
		table[cell] = DELETED;
		size--;
		if(1 < table.length && (float)size/table.length < getLowerLoadFactor())
			rehash(false);
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return table.length;
	}

	//continues until found or null
	private int probe(java.lang.String value, boolean skipDeleted) {
		int initial = clamp(value.hashCode());
		int currCell = initial;
		int i;
		for(i = 1 ;
			table[currCell] != null && (Objects.equals(table[currCell], DELETED) ||
							!table[currCell].equals(value)) && i < table.length ; i++){
			if(!skipDeleted && Objects.equals(table[currCell], DELETED))
				break;
			//c1=c2=1/2
			currCell = clamp(initial + (i + i*i)/2);
		}
		//table is full
		if(i == table.length)
			return -1;
		return currCell;
	}

	/*
	Rehashes for bigger table when increaseSize=true, otherwise rehashes for a smaller table.
	 */
	private void rehash(boolean increaseSize) {
		int newSize = increaseSize ? table.length * 2 : table.length / 2;
		capacityMinusOne = newSize - 1;
		String[] oldTable = table;
		table = new String[newSize];
		for(int i = 0 ; i < oldTable.length ; i++) {
			if(null == oldTable[i] || Objects.equals(oldTable[i], DELETED))
				continue;
			int cell = probe(oldTable[i], true);
			table[cell] = oldTable[i];
		}
	}
}
