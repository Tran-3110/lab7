package set_student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class MyPredicates {
	// Remove every object, obj, from coll for which p.test(obj)
	// is true. (This does the same thing as coll.removeIf(p).)
	public static <T> void remove(Collection<T> coll, Predicate<T> p) {
		Iterator<T> iterator = coll.iterator();
		while(iterator.hasNext()) {
			T obj = iterator.next();
			if(p.test(obj)) iterator.remove();
		}
	}

	// Remove every object, obj, from coll for which
	// pr.test(obj) is false. (That is, retain the
	// objects for which the predicate is true.)
	public static <T> void retain(Collection<T> coll, Predicate<T> p) {
		Iterator<T> iterator = coll.iterator();
		while(iterator.hasNext()) {
			T obj = iterator.next();
			if(!p.test(obj)) iterator.remove();
		}
	}

	// Return a Set that contains all unique objects, obj,
	// from the collection, coll, such that p.test(obj)
	// is true.
	public static <T> Set<T> collect(Collection<T> coll, Predicate<T> p) {
		Set<T> set = new HashSet<>();
		Iterator<T> iterator = coll.iterator();
		while(iterator.hasNext()) {
			T obj = iterator.next();
			if(p.test(obj)) set.add(obj);
		}
		return set;
	}

	// Return the index of the first item in list
	// for which the predicate is true, if any.
	// If there is no such item, return -1.
	public static <T> int find(Collection<T> coll, Predicate<T> p) {
		Iterator<T> iterator = coll.iterator();
		int index = -1;
		while(iterator.hasNext()) {
			index++;
			T obj = iterator.next();
			if(p.test(obj)) return index;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(4);
		list.add(5);
		list.add(5);
		list.add(6);
		list.add(8);
		Even even = new Even();

//		MyPredicates.remove(list, even);
//		System.out.println(list);
		
//		MyPredicates.retain(list, even);
//		System.out.println(list);
		
		Set<Integer> set = MyPredicates.collect(list, even);
		System.out.println(set);
		
		System.out.println(MyPredicates.find(list, even));
	}
}
