/**
 * 
 */
package com.sparklingminds.adt.map;

import java.util.Map;
/**
 * @author Gaive Gandhi
 *
 */
public class PerformantMapTestClient {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		Map<String, String> map = new PerformantHashMap<String, String>();
		Map<String, String> map1 = new PerformantHashMap<String, String>(16,false);
		PerformantHashMap<String, String> map2 = new PerformantHashMap<String, String>(16,true);
		Map<String, String> map3 = new PerformantHashMap<String, String>(16,0.75f,false);
		PerformantHashMap<String, String> map4 = new PerformantHashMap<String, String>(16,0.75f,true);
		
		Map<String,String> map5 = new PerformantHashMap<String, String>();
		
		map.put("soli", "soli gandhi");
	    map.put("pillu", "pillu gandhi");
		map.put("gaive", "gaive gandhi"); 
	    map.put("rashna", "rashna gandhi"); 
	    map.put("jk", "jk gandhi");
		map.put("prashant", "prashant gandhi"); 
		map.put("perin", "perin gandhi");
		map.put("kawas", "kawas gandhi"); 
		map.put("john", null); 
		map.put("kate",null); 
		map.put(null, "gandhi");
	
		map1.put("soli", "soli gandhi");
	    map1.put("pillu", "pillu gandhi");
		map1.put("gaive", "gaive gandhi"); 
	    map1.put("rashna", "rashna gandhi"); 
	    map1.put("jk", "jk gandhi");
		map1.put("prashant", "prashant gandhi"); 
		map1.put("perin", "perin gandhi");
		map1.put("kawas", "kawas gandhi"); 
		map1.put("john", null); 
		map1.put("kate",null); 
		map1.put(null, "gandhi");
		
		map2.put("soli", "soli gandhi");
	    map2.put("pillu", "pillu gandhi");
		map2.put("gaive", "gaive gandhi"); 
	    map2.put("rashna", "rashna gandhi"); 
	    map2.put("jk", "jk gandhi");
		map2.put("prashant", "prashant gandhi"); 
		map2.put("perin", "perin gandhi");
		map2.put("kawas", "kawas gandhi"); 
		map2.put("john", null); 
		map2.put("kate",null); 
		map2.put(null, "gandhi");

		map3.put("soli", "soli gandhi");
	    map3.put("pillu", "pillu gandhi");
		map3.put("gaive", "gaive gandhi"); 
	    map3.put("rashna", "rashna gandhi"); 
	    map3.put("jk", "jk gandhi");
		map3.put("prashant", "prashant gandhi"); 
		map3.put("perin", "perin gandhi");
		map3.put("kawas", "kawas gandhi"); 
		map3.put("john", null); 
		map3.put("kate",null); 
		map3.put(null, "gandhi");
		
		map4.put("soli", "soli gandhi");
	    map4.put("pillu", "pillu gandhi");
		map4.put("gaive", "gaive gandhi"); 
	    map4.put("rashna", "rashna gandhi"); 
	    map4.put("jk", "jk gandhi");
		map4.put("prashant", "prashant gandhi"); 
		map4.put("perin", "perin gandhi");
		map4.put("kawas", "kawas gandhi"); 
		map4.put("john", null); 
		map4.put("kate",null); 
		map4.put(null, "gandhi");
		
		map5.put("rashna", "rashna gandhi"); 
	    map5.put("jk", "jk gandhi");
		map5.put("akash", "gupta");
		map5.put("kaneez", "surka");
		map5.put("prashasti", "singh");
		

		System.out.println("Map");
		System.out.println("Size: "+map.size());
		System.out.println("Is Empty: "+map.isEmpty());
		System.out.println();
		System.out.println();
		
		System.out.println("Get Key Exists: "+map.get("soli"));
		System.out.println("Get Key Does Not Exists: "+map.get("soli g"));
		System.out.println("Get Null Key: "+map.get(null));
		System.out.println();
		System.out.println();
		
		System.out.println("Put New Entry: "+map.put("abhinav","chaturvedi"));
		System.out.println("Put Existing Entry: "+map.put("abhinav","ambrish chaturvedi"));
		System.out.println("Put Null Entry: "+map.put(null,null));
		System.out.println();
		System.out.println();
		
		System.out.println("Size:"+map.size());
		System.out.println("Map Put All");
		map.putAll(map5);
		System.out.println("Size:"+map.size());
		System.out.println();
		System.out.println();
		
		System.out.println("Contains Key Key Exists: "+map.containsKey("gaive"));
		System.out.println("Contains Key Key Does Not Exists: "+map.containsKey("gav"));
		System.out.println("Contains Key Key Is Null: "+map.containsKey(null));
		System.out.println();
		System.out.println();
		
		System.out.println("Contains Value Value Exists: "+map.containsValue("soli gandhi"));
		System.out.println("Contains Value Value Does Not Exists: "+map.containsValue("gav"));
		System.out.println("Contains Value Value Is Null: "+map.containsKey(null));
		System.out.println();
		System.out.println();
		
		System.out.println("Size: "+map.size());
		System.out.println("Remove Value Exists: "+map.remove("soli"));
		System.out.println("Remove Value Does Not Exists: "+map.remove("gav"));
		System.out.println("Remove Value Is Null: "+map.remove(null));
		System.out.println("Size: "+map.size());
		System.out.println();
		System.out.println();
		
		System.out.println("Entry Set");
		for(Map.Entry<String,String> me: map.entrySet()) {
			  
			System.out.println("Key: "+me.getKey()+" Value: "+me.getValue());
				  
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Key Set");
		for(String me: map.keySet()) {
			  
			System.out.println("Key: "+me);
				  
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Value Set");
		for(String me: map.values()) {
			  
			System.out.println("Value: "+me);
				  
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println("Might Contains Key Key Exists: "+map2.mightContain("gaive"));
		System.out.println("Might Contains Key Key Does Not Exists: "+map2.mightContain("gav"));
		System.out.println("Might Contains Key Key Is Null: "+map2.mightContain(null));
		
	}

}
