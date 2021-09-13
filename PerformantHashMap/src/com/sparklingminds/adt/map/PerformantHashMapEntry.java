package com.sparklingminds.adt.map;

/**
 * PerformantHashMapEntry is a map entry (key-value pair) implementation of the
 * Map.Entry interface. The PerformantHashMap.entrySet() method returns a
 * collection-view of the map, whose elements are of this class. The only way to
 * obtain a reference to a map entry is from the iterator of this
 * collection-view.
 * 
 * @author Gaive Gandhi
 * @version 1.0
 */
public class PerformantHashMapEntry<K, V> implements java.util.Map.Entry<K, V>, java.io.Serializable {

	private static final long serialVersionUID = 262498820763181265L;

	private Object key;

	private Object value;

	private PerformantHashMapEntry<K, V> next;

	protected PerformantHashMapEntry(Object key, Object value) {

		this.key = key;

		this.value = value;

	}

	/**
	 * Returns the key corresponding to this entry.
	 * 
	 * @return the key corresponding to this entry
	 */
	@SuppressWarnings("unchecked")
	@Override
	public K getKey() {

		return (K) key;

	}

	/**
	 * Returns the value corresponding to this entry.
	 * 
	 * @return the value corresponding to this entry
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V getValue() {

		return (V) value;

	}

	/**
	 * Replaces the value corresponding to this entry with the specified value. The
	 * behavior of this call is undefined if the mapping has already been removed
	 * from the map (by the iterator's remove operation).
	 * 
	 * @param value new value to be stored in this entry
	 * @return old value corresponding to the entry
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V setValue(V value) {

		V tempValue = (V) this.value;

		this.value = value;

		return tempValue;

	}

	@SuppressWarnings("unchecked")
	protected K setKey(K key) {

		return (K) (this.key = key);

	}

	protected PerformantHashMapEntry<K, V> getNext() {

		return next;

	}

	protected void setNext(PerformantHashMapEntry<K, V> next) {

		this.next = next;

	}

}
