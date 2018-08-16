/**
 * FILE : SimpleHashSet.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * A superclass for implementations of hash-sets implementing the SimpleSet
 * interface.
 */
public abstract class SimpleHashSet implements SimpleSet {
	/** A number that describes the capacity of a newly created hash set */
	protected static final int INITIAL_CAPACITY = 16;
	/** A number that describes the higher load factor of a newly created hash set */
	protected static final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f;
	/** A number that describes the lower load factor of a newly created hash set */
	protected static final float DEFAULT_LOWER_LOAD_FACTOR = 0.25f;

	private String[] table = new String[INITIAL_CAPACITY];

	protected int capacityMinusOne = table.length - 1;
	/** A number that describes the higher load factor and the lower load factor of a hash set */
	private final float lowerLoadFactor, upperLoadFactor;






	/**
	 Constructs a new hash set with capacity INITIAL_CAPACITY
	 * @param upperLoadFactor - the upper load factor before rehashing
	 * @param lowerLoadFactor - the lower load factor before rehashing
	 */
	protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
		this.lowerLoadFactor = lowerLoadFactor;
		this.upperLoadFactor = upperLoadFactor;
		this.table = new String[INITIAL_CAPACITY];

	}


	/**
	 * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
	 * DEFAULT_HIGHER_CAPACITY
	 */
	protected SimpleHashSet() {
		this.lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
		this.upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
		this.table = new String[INITIAL_CAPACITY];
	}
	
	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	public abstract int capacity();

	/**
	 * @return The lower load factor of the table.
	 */
	protected float getLowerLoadFactor() {
		return this.lowerLoadFactor;
	}
	/**
	 * @return The higher load factor of the table.
	 */
	protected float getUpperLoadFactor() {
		return this.upperLoadFactor;
	}
	/**
	 * Clamps hashing indices to fit within the current table capacity .
	 * @param index - the index before clamping .
	 * @return an index properly clamped .
	 */
	protected int clamp(int index) {
		return index & capacityMinusOne;
	}

}
