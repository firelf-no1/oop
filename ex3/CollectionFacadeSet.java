/**
 * FILE : CollectionFacadeSet.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * Wraps an underlying Collection<String> and serves to both simplify its API and give it a common type
 * with the implemented SimpleHashSets.
 */


public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {
	protected java.util.Collection<java.lang.String> collection;
	
	/**
	 * Creates a new facade wrapping the specified collection.
	 * @param collection The Collection to wrap.
	 */
	public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {

		String[] newArray = new String[collection.size()];
		collection.toArray(newArray);
		this.collection = collection;
		this.collection.clear();
		for (String str:newArray) {
			this.add(str);
		}
	}
	

	/**
	 * Adds the specified value to the underlying collection,
	 * if it's not already in it.
	 * @param newValue The value to add
	 * @return False iff the value was already in the Collection
	 */
	@Override
	public boolean add(java.lang.String newValue) {
		if(collection.contains(newValue))
			return false;
		collection.add(newValue);
		return true;
	}


	/**
	 * @param searchVal The value to search for in the underlying Collection.
	 * @return Whether the specified value appears in the underlying Collection.
	 */
	@Override
	public boolean contains(java.lang.String searchVal) {
		return collection.contains(searchVal);
	}


	/**
	 * Delete the specified value from the underlying Collection.
	 * @param toDelete The value to delete
	 * @return True if the value was indeed in the underlying Collection
	 */
	@Override
	public boolean delete(java.lang.String toDelete) {
		return collection.remove(toDelete);
	}


	/**
	 * @return The size of the underlying Collection
	 */
	@Override
	public int size() {
		return collection.size();
	}
}
