/**
 * FILE : StringList.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * A superclass for implementations of String list implementing the Collection Facade Set
 * interface.
 */
import java.util.Iterator;
import java.util.Collection;

public class StringList extends CollectionFacadeSet implements Iterable<String>{
	
	public StringList(Collection<String> collection) {
		super(collection);
	}

	@Override
	public Iterator<String> iterator() {
		return collection.iterator();
	}
}
