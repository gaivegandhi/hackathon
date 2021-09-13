/**
 * 
 */
package com.sparklingminds.adt.map;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * 
 * PerformantHashMap is a hash table based implementation of the Map interface.
 * The current version does not have implementation of all the optional map
 * operations, however it permits null values and the null key. Unlike the
 * Hashtable class, the PerformantHashMap is not synchronized by default. The
 * PerformantHashMap makes no guarantees as to the order of the map; in
 * particular, it does not guarantee that the order will remain constant over
 * time. <br>
 * <br>
 * The PerformantHashMap implementation provides constant-time performance for
 * the basic operations (get and put), assuming the hash function disperses the
 * elements properly among the buckets. In case of hash collisions, which are
 * practically unavoidable, the PerformantHashMap implements <i> separate
 * chaining using linked list </i> as the hash collision resolution strategy.
 * The PerformantHashMap implementation provides multiple times better
 * performance than HashMap for the Keys which are <b> most frequently accessed
 * </b> using the get(Key k) operation. It implements a <i> self-organizing
 * heuristic (using the transpose method) </i> when accessing a map entry for a
 * Key using the get(Key k) method. This technique involves swapping an accessed
 * map entry with its predecessor. Therefore, if any map entry is accessed, it
 * is swapped with the map entry in front unless it is the head node, thereby
 * increasing its priority. This algorithm is space efficient and is more likely
 * to keep frequently accessed map entry at the front of the list. However, the
 * transpose method is more cautious. i.e. it will take many accesses to move
 * the element to the head of the list. This method also does not allow for
 * rapid response to changes in the query distributions on the map entries in
 * the list. <br>
 * <br>
 * In addition, the PerformantHashMap also has an option to enable
 * implementation of a <b> Bloom Filter </b> to check for the availability of a
 * Key in the performant hashmap <b> without accessing </b> the map. The
 * mightContain(Key k) method is used for this purpose. Every time a Key is
 * added to an instance of the PerformantHashMap, its entry is recorded within
 * the bloom filter using multiple hash functions. Later, the mightContain(Key
 * k) method can be used to check for the availability of the map entry for a
 * Key without accessing the map. Since bloom filter is a probabilistic data
 * structure, there is a chance of <b> false positive errors </b> where the
 * mightContain(Key k) method may occasionally return true even if the map entry
 * for the Key is not in the map. Please note, that the bloom filter will never
 * return false negatives i.e. it will never return false even if a map entry
 * for a Key is available in the map. The PerformantHashMap with the bloom
 * filter enabled guarantees false positive errors to be always <b> less than 1
 * percent </b>. It is advised to enable bloom filter for a PerformantHashMap
 * only if there is a requirement and the map size is not extremely large as the
 * bloom filter data structure occupies additional memory space. <br>
 * <br>
 * Iteration over collection views requires time proportional to the "capacity"
 * of the PerformantHashMap instance (the number of buckets) plus its size (the
 * number of key-value mappings). Thus, it's very important not to set the
 * initial capacity too high (or the load factor too low) if iteration
 * performance is important. <br>
 * <br>
 * An instance of PerformantHashMap has three parameters that affect its
 * performance: initial capacity, load factor and bloom filter. The capacity is
 * the number of buckets in the hash table, and the initial capacity is simply
 * the capacity at the time the hash table is created. The load factor is a
 * measure of how full the hash table is allowed to get before its capacity is
 * automatically increased. When the number of entries in the hash table exceeds
 * the product of the load factor and the current capacity, the hash table is
 * rehashed (that is, internal data structures are rebuilt) so that the hash
 * table has approximately twice the number of buckets. The bloom filter flag
 * decides whether the bloom filter has to be enabled or not for a particular
 * instance of the PerformantHashMap. <br>
 * <br>
 * As a general rule, the default load factor (.75) offers a good tradeoff
 * between time and space costs. Higher values decrease the space overhead but
 * increase the lookup cost (reflected in most of the operations of the
 * PerformantHashMap class, including get and put). The expected number of
 * entries in the map and its load factor should be taken into account when
 * setting its initial capacity, so as to minimize the number of rehash
 * operations. If the initial capacity is greater than the maximum number of
 * entries divided by the load factor, no rehash operations will ever occur.
 * <br>
 * <br>
 * If many mappings are to be stored in a PerformantHashMap instance, creating
 * it with a sufficiently large capacity will allow the mappings to be stored
 * more efficiently than letting it perform automatic rehashing as needed to
 * grow the table. Note that using many keys with the same hashCode() is a sure
 * way to slow down performance of any hash table. To ameliorate impact, when
 * keys are Comparable, this class may use comparison order among keys to help
 * break ties. <br>
 * <br>
 * Note that this implementation is not synchronized. If multiple threads access
 * a performant hash map concurrently, and at least one of the threads modifies
 * the map structurally, it must be synchronized externally. (A structural
 * modification is any operation that adds or deletes one or more mappings;
 * merely changing the value associated with a key that an instance already
 * contains is not a structural modification.) This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map. If no such
 * object exists, the map should be "wrapped" using the
 * Collections.synchronizedMap method. This is best done at creation time, to
 * prevent accidental unsynchronized access to the map: <br>
 * <br>
 * <i> Map m = Collections.synchronizedMap(new HashMap(...)); </i> <br>
 * <br>
 * The iterators returned by all of this class's "collection view methods" are
 * fail-fast: if the map is structurally modified at any time after the iterator
 * is created, in any way except through the iterator's own remove method, the
 * iterator will throw a ConcurrentModificationException. Thus, in the face of
 * concurrent modification, the iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined time in the
 * future. <br>
 * <br>
 * Note that the fail-fast behavior of an iterator cannot be guaranteed as it
 * is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification. Fail-fast iterators throw
 * ConcurrentModificationException on a best-effort basis. Therefore, it would
 * be wrong to write a program that depended on this exception for its
 * correctness: the fail-fast behavior of iterators should be used only to
 * detect bugs.
 * 
 * @author Gaive Gandhi
 * @version 1.0
 */
public class PerformantHashMap<K, V> extends AbstractMap<K, V>
		implements java.util.Map<K, V>, java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 362498820763181265L;

	private Object[] performantMap;

	private int mapEntrySize;

	private int mapSize;

	private int listSize;

	private float loadFactor;

	private Set<Map.Entry<K, V>> entrySet;

	private Set<K> keySet;

	private Set<V> valueSet;

	private boolean isBloomFilter;

	private BloomFilter bloomFilter;

	{

		mapSize = 16;

		loadFactor = 0.75f;

		listSize = 0;

		isBloomFilter = false;

		bloomFilter = null;

	}

	/**
	 * Constructs an empty PerformantHashMap with the default initial capacity (16),
	 * the default load factor (0.75) and a disabled bloom filter.
	 */
	public PerformantHashMap() {

		performantMap = new Object[mapSize];

	}

	/**
	 * Constructs an empty PerformantHashMap with the specified initial capacity,
	 * the default load factor (0.75) and a flag for bloom filter.
	 * 
	 * @param initialCapacity the initial capacity
	 * @param isBloomFilter   the flag for bloom filter
	 */
	public PerformantHashMap(int initialCapacity, boolean isBloomFilter) {

		if (initialCapacity < 0) {

			throw new IllegalArgumentException();

		}

		if (isBloomFilter) {

			bloomFilter = new BloomFilter((mapSize * 10), 7);

		}

		this.mapSize = initialCapacity;

		this.isBloomFilter = isBloomFilter;

		performantMap = new Object[mapSize];

	}

	/**
	 * Constructs an empty PerformantHashMap with the specified initial capacity,
	 * load factor and a flag for bloom filter.
	 * 
	 * @param initialCapacity the initial capacity
	 * @param loadFactor      the load factor
	 * @param isBloomFilter   the flag for bloom filter
	 * @throws IllegalArgumentException if the initial capacity is negative or the
	 *                                  load factor is non-positive
	 */
	public PerformantHashMap(int initialCapacity, float loadFactor, boolean isBloomFilter) {

		if (initialCapacity < 0 || loadFactor <= 0) {

			throw new IllegalArgumentException();

		}

		if (isBloomFilter) {

			bloomFilter = new BloomFilter((mapSize * 10), 7);

		}

		this.mapSize = initialCapacity;

		this.loadFactor = loadFactor;

		this.isBloomFilter = isBloomFilter;

		performantMap = new Object[mapSize];

	}

	/**
	 * Constructs a new PerformantHashMap with the same mappings as the specified
	 * Map. The PerformantHashMap is created with the default load factor (0.75) and
	 * an initial capacity sufficient to hold the mappings in the specified Map.
	 * 
	 * @param m             the map whose mappings are to be placed in this map
	 * @param isBloomFilter the flag for bloom filter
	 * @throws NullPointerException if the specified map is null
	 */
	public PerformantHashMap(Map<? extends K, ? extends V> m, boolean isBloomFilter) {

		if (m == null) {

			throw new NullPointerException();

		}

		if (isBloomFilter) {

			bloomFilter = new BloomFilter((mapSize * 10), 7);

		}

		this.isBloomFilter = isBloomFilter;

		performantMap = new Object[mapSize];

		for (Entry<? extends K, ? extends V> me : m.entrySet()) {

			put(me.getKey(), me.getValue());

		}

	}

	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * @return the number of key-value mappings in this map
	 */
	@Override
	public int size() {

		return mapEntrySize;

	}

	/**
	 * Returns true if this map contains no key-value mappings.
	 * 
	 * @return true if this map contains no key-value mappings
	 */
	@Override
	public boolean isEmpty() {

		return mapEntrySize == 0;

	}

	/**
	 * Returns true if this map contains a mapping for the specified key.
	 * 
	 * @param key the key whose presence in this map is to be tested
	 * @return true if this map contains a mapping for the specified key.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean containsKey(Object key) {

		if (isBloomFilter == true && !mightContain((K) key)) {

			return false;

		} else {

			int index = getBucketIndex((K) key);

			PerformantHashMapEntry<K, V> currentPosition = (PerformantHashMapEntry<K, V>) performantMap[index];

			while (currentPosition != null) {

				if (key == null && currentPosition.getKey() == null) {

					return true;

				} else if (key != null && key.equals(currentPosition.getKey())) {

					return true;

				}

				currentPosition = currentPosition.getNext();

			}

		}

		return false;

	}

	/**
	 * Returns true if this map maps one or more keys to the specified value.
	 * 
	 * @param value whose presence in this map is to be tested
	 * @return true if this map maps one or more keys to the specified value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean containsValue(Object value) {

		for (int i = 0; i < performantMap.length; i++) {

			if (performantMap[i] != null) {

				PerformantHashMapEntry<K, V> currentPosition = (PerformantHashMapEntry<K, V>) performantMap[i];

				while (currentPosition != null) {

					if (value == null && currentPosition.getValue() == null) {

						return true;

					} else if (value != null && value.equals(currentPosition.getValue())) {

						return true;

					}

					currentPosition = currentPosition.getNext();

				}

			}

		}

		return false;

	}

	/**
	 * Returns the value to which the specified key is mapped, or null if this map
	 * contains no mapping for the key. <br>
	 * <br>
	 * More formally, if this map contains a mapping from a key k to a value v such
	 * that (key==null ? k==null : key.equals(k)), then this method returns v;
	 * otherwise it returns null. (There can be at most one such mapping.) <br>
	 * <br>
	 * A return value of null does not necessarily indicate that the map contains no
	 * mapping for the key; it's also possible that the map explicitly maps the key
	 * to null. The containsKey operation may be used to distinguish these two
	 * cases.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {

		if (isBloomFilter == true && !mightContain((K) key)) {

			return null;

		} else {

			int index = getBucketIndex((K) key);

			PerformantHashMapEntry<K, V> currentPosition = (PerformantHashMapEntry<K, V>) performantMap[index];

			PerformantHashMapEntry<K, V> previousPosition = null;

			while (currentPosition != null) {

				if (key == null && currentPosition.getKey() == null) {

					return currentPosition.getValue();

				}

				if (!key.equals(currentPosition.getKey())) {

					previousPosition = currentPosition;

				}

				if (previousPosition == null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() == null) {

					return currentPosition.getValue();

				} else if (previousPosition == null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() != null) {

					return currentPosition.getValue();

				} else if (previousPosition != null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() != null) {

					K tempKey = previousPosition.getKey();
					V tempValue = previousPosition.getValue();

					previousPosition.setKey(currentPosition.getKey());
					previousPosition.setValue(currentPosition.getValue());

					currentPosition.setKey(tempKey);
					currentPosition.setValue(tempValue);

					return previousPosition.getValue();

				} else if (previousPosition != null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() == null) {

					K tempKey = previousPosition.getKey();
					V tempValue = previousPosition.getValue();

					previousPosition.setKey(currentPosition.getKey());
					previousPosition.setValue(currentPosition.getValue());

					currentPosition.setKey(tempKey);
					currentPosition.setValue(tempValue);

					return previousPosition.getValue();

				}

				currentPosition = currentPosition.getNext();

			}

		}

		return null;

	}

	/**
	 * Associates the specified value with the specified key in this map. If the map
	 * previously contained a mapping for the key, the old value is replaced.
	 * 
	 * @param key   the key with which the specified value is to be associated
	 * @param value the value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key. (A null return can also indicate that the map
	 *         previously associated null with key.)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public V put(K key, V value) {

		int bucketIndex = getBucketIndex(key);

		PerformantHashMapEntry<K, V> currentPosition = (PerformantHashMapEntry<K, V>) performantMap[bucketIndex];

		if (containsKey(key)) {

			while (currentPosition != null) {

				if (key == null && currentPosition.getKey() == null) {

					currentPosition.setValue(value);

					return currentPosition.getValue();

				} else if (currentPosition.getKey().equals(key)) {

					currentPosition.setValue(value);

					return currentPosition.getValue();
				}

				currentPosition = currentPosition.getNext();

			}

		} else {

			float totalNoOfMapEntry = new Integer(listSize).floatValue();

			float sizeOfPerformantMap = new Integer(mapSize).floatValue();

			float calculatedloadFactor = (totalNoOfMapEntry / sizeOfPerformantMap);

			boolean isHashingRequired = (calculatedloadFactor >= loadFactor);

			if (isHashingRequired) {

				rehash();

			}

			bucketIndex = getBucketIndex(key);

			listSize++;

			currentPosition = (PerformantHashMapEntry<K, V>) performantMap[bucketIndex];

			PerformantHashMapEntry<K, V> newPeformantMapEntry = new PerformantHashMapEntry<K, V>(key, value);

			newPeformantMapEntry.setNext(currentPosition);

			performantMap[bucketIndex] = newPeformantMapEntry;

			mapEntrySize++;

			if (isBloomFilter == true && key != null) {

				bloomFilter.add(key);

			}

			return ((PerformantHashMapEntry<K, V>) performantMap[bucketIndex]).getValue();

		}

		return null;

	}

	/**
	 * Copies all of the mappings from the specified map to this map. These mappings
	 * will replace any mappings that this map had for any of the keys currently in
	 * the specified map.
	 * 
	 * @param m the mappings to be stored in this map
	 * @throws NullPointerException if the specified map is null
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {

		if (m == null) {

			throw new NullPointerException();

		}

		for (Entry<? extends K, ? extends V> me : m.entrySet()) {

			put(me.getKey(), me.getValue());

		}

	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * 
	 * @param key whose mapping is to be removed from the map
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key. (A null return can also indicate that the map
	 *         previously associated null with key.)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {

		if (isBloomFilter == true && !mightContain((K) key)) {

			return null;

		} else {

			int index = getBucketIndex((K) key);

			PerformantHashMapEntry<K, V> currentPosition = (PerformantHashMapEntry<K, V>) performantMap[index];

			PerformantHashMapEntry<K, V> previousPosition = null;

			while (currentPosition != null) {

				if (key == null && currentPosition.getKey() == null) {

					performantMap[index] = currentPosition.getNext();

					mapEntrySize--;

					return currentPosition.getValue();

				}

				if (!key.equals(currentPosition.getKey())) {

					previousPosition = currentPosition;

				}

				if (previousPosition == null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() == null) {

					performantMap[index] = null;

					mapEntrySize--;

					return currentPosition.getValue();

				} else if (previousPosition == null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() != null) {

					performantMap[index] = currentPosition.getNext();

					mapEntrySize--;

					return currentPosition.getValue();

				} else if (previousPosition != null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() != null) {

					previousPosition.setNext(currentPosition.getNext());

					mapEntrySize--;

					return currentPosition.getValue();

				} else if (previousPosition != null && key.equals(currentPosition.getKey())
						&& currentPosition.getNext() == null) {

					previousPosition.setNext(null);

					mapEntrySize--;

					return currentPosition.getValue();

				}

				currentPosition = currentPosition.getNext();

			}

		}

		return null;

	}

	/**
	 * Removes all of the mappings from this map. The map will be empty after this
	 * call returns.
	 */
	@Override
	public void clear() {

		mapEntrySize = 0;

		listSize = 0;

		loadFactor = 0.75f;

		mapSize = 16;

		performantMap = new Object[mapSize];

	}

	/**
	 * Returns a Set view of the keys contained in this map. The set is backed by
	 * the map, so changes to the map are reflected in the set, and vice-versa. If
	 * the map is modified while an iteration over the set is in progress (except
	 * through the iterator's own remove operation), the results of the iteration
	 * are undefined. The set supports element removal, which removes the
	 * corresponding mapping from the map, via the Iterator.remove, Set.remove and
	 * clear operations. It does not support the add or addAll operations.
	 * 
	 * @return a set view of the keys contained in this map
	 */
	@Override
	public Set<K> keySet() {

		if (keySet == null) {

			keySet = new PerformantKeySet();

		}

		return keySet;
	}

	/**
	 * Returns a Collection view of the values contained in this map. The collection
	 * is backed by the map, so changes to the map are reflected in the collection,
	 * and vice-versa. If the map is modified while an iteration over the collection
	 * is in progress (except through the iterator's own remove operation), the
	 * results of the iteration are undefined. The collection supports element
	 * removal, which removes the corresponding mapping from the map, via the
	 * Iterator.remove, Collection.remove and clear operations. It does not support
	 * the add or addAll operations.
	 * 
	 * @return a view of the values contained in this map
	 */
	@Override
	public Collection<V> values() {

		if (valueSet == null) {

			valueSet = new PerformantValueSet();

		}

		return valueSet;

	}

	/**
	 * Returns a Set view of the mappings contained in this map. The set is backed
	 * by the map, so changes to the map are reflected in the set, and vice-versa.
	 * If the map is modified while an iteration over the set is in progress (except
	 * through the iterator's own remove operation, or through the setValue
	 * operation on a map entry returned by the iterator) the results of the
	 * iteration are undefined. The set supports element removal, which removes the
	 * corresponding mapping from the map, via the Iterator.remove, Set.remove and
	 * clear operations. It does not support the add or addAll operations.
	 * 
	 * @return a set view of the mappings contained in this map
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {

		if (entrySet == null) {

			entrySet = new PerformantEntrySet();

		}

		return entrySet;

	}

	/**
	 * Returns true if this map contains a mapping for the specified key. Since
	 * bloom filter is a probabilistic data structure, there is a below 1 percent
	 * chance of false positive errors i.e. the method returns true even if the map
	 * does not contain an entry for the specified key. There are no chances of a
	 * bloom filter emitting false negative errors i.e. it will never return false
	 * for an existing map entry for a specified Key.
	 * 
	 * @param key the key whose presence in this map is to be tested
	 * @return true if this map contains a mapping for the specified key.
	 * @throws UnsupportedOperationException if bloom filter is not enabled for this
	 *                                       PerformantHashMap instance
	 */
	public boolean mightContain(K key) {

		if (!isBloomFilter) {

			throw new UnsupportedOperationException();

		}

		if (key != null) {

			return bloomFilter.contains(key);

		}

		return true;

	}

	/**
	 * Returns a shallow copy of this PerformantHashMap instance: the keys and
	 * values themselves are not cloned.
	 * 
	 * @return a shallow copy of this map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {

		PerformantHashMap<K, V> result;

		try {

			result = (PerformantHashMap<K, V>) super.clone();

		} catch (CloneNotSupportedException e) {

			throw new CloneNotSupportedException();

		}

		result.putAll(this);

		return result;

	}

	@SuppressWarnings("unchecked")
	private void rehash() {

		Object[] temp;

		try {

			mapSize = 2 * mapSize;

			mapSize = getNextPrime(mapSize);

			listSize = 0;

			mapEntrySize = 0;

			temp = performantMap;

			performantMap = new Object[mapSize];

			if (isBloomFilter) {

				bloomFilter.clear();

				bloomFilter = new BloomFilter((mapSize * 10), 7);

			}

			for (Object headNode : temp) {

				PerformantHashMapEntry<K, V> perfMapEntryHeadNode = (PerformantHashMapEntry<K, V>) headNode;

				while (perfMapEntryHeadNode != null) {

					put(perfMapEntryHeadNode.getKey(), perfMapEntryHeadNode.getValue());

					perfMapEntryHeadNode = perfMapEntryHeadNode.getNext();

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private int getNextPrime(int number) {

		try {

			while (true) {

				boolean isPrime = true;

				number = number + 1;

				int sqrt = (int) Math.sqrt(number);

				for (int i = 2; i <= sqrt; i++) {

					if (number % i == 0) {

						isPrime = false;

						break;

					}

				}

				if (isPrime) {

					return number;

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	private int getBucketIndex(K key) {

		if (key == null) {

			return 0;

		}

		int hashCode = key.hashCode();

		try {

			return Math.abs(hashCode) % mapSize;

		} catch (ArithmeticException e) {

			e.printStackTrace();

		}

		return 0;

	}

	final class PerformantEntrySet extends java.util.AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Map.Entry<K, V>> iterator() {

			return new PerformantEntryIterator();

		}

		@Override
		public int size() {

			return mapEntrySize;

		}

	}

	final class PerformantKeySet extends java.util.AbstractSet<K> {

		@Override
		public Iterator<K> iterator() {

			return new PerformantKeyIterator();

		}

		@Override
		public int size() {

			return mapEntrySize;

		}

	}

	final class PerformantValueSet extends java.util.AbstractSet<V> {

		@Override
		public Iterator<V> iterator() {

			return new PerformantValueIterator();

		}

		@Override
		public int size() {

			return mapEntrySize;

		}

	}

	final class PerformantEntryIterator extends HashIterator implements Iterator<Map.Entry<K, V>> {

		@Override
		public boolean hasNext() {

			return hasNextEntry();

		}

		@Override
		public Map.Entry<K, V> next() {

			return nextEntry();

		}

	}

	final class PerformantKeyIterator extends HashIterator implements Iterator<K> {

		@Override
		public boolean hasNext() {

			return hasNextEntry();

		}

		@Override
		public K next() {

			return nextEntry().getKey();

		}

	}

	final class PerformantValueIterator extends HashIterator implements Iterator<V> {

		@Override
		public boolean hasNext() {

			return hasNextEntry();

		}

		@Override
		public V next() {

			return nextEntry().getValue();

		}

	}

	class HashIterator {

		PerformantHashMapEntry<K, V> current;

		PerformantHashMapEntry<K, V> next;

		int index;

		int expectedMapEntrySize;

		@SuppressWarnings("unchecked")
		HashIterator() {

			Object[] table = performantMap;

			index = 0;

			expectedMapEntrySize = mapEntrySize;

			if (table != null && mapEntrySize > 0) {

				do {

					// do nothing
				} while (index < table.length && (next = (PerformantHashMapEntry<K, V>) table[index++]) == null);

			}

		}

		public boolean hasNextEntry() {

			return next != null;

		}

		@SuppressWarnings("unchecked")
		public PerformantHashMapEntry<K, V> nextEntry() {

			Object[] table;

			PerformantHashMapEntry<K, V> pme = next;

			if (expectedMapEntrySize != mapEntrySize) {

				throw new ConcurrentModificationException();

			}

			if (pme == null) {

				throw new NoSuchElementException();

			}

			if ((next = (current = pme).getNext()) == null && (table = performantMap) != null) {

				do {

					// do nothing

				} while (index < table.length && (next = (PerformantHashMapEntry<K, V>) table[index++]) == null);

			}

			return pme;

		}

		public void remove() {

			PerformantHashMapEntry<K, V> pme = current;

			if (pme == null) {

				throw new IllegalStateException();

			}

			if (expectedMapEntrySize != mapEntrySize) {

				throw new ConcurrentModificationException();

			}

			current = null;

			K key = pme.getKey();

			PerformantHashMap.this.remove(key);

			expectedMapEntrySize = mapEntrySize;

		}

	}

}