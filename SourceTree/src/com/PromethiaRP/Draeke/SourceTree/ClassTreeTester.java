package com.PromethiaRP.Draeke.SourceTree;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;


public class ClassTreeTester {

	public static void main(String[] args) {
		ClassTree tree = new ClassTree();
		
		tree.insert(Integer.class);
		tree.insert(Double.class);
		tree.insert(String.class);
		tree.insert(Random.class);
		tree.insert(Float.class);
		tree.insert(Math.class);
		tree.insert(Comparable.class);
	
		
		
		
		System.out.println(Arrays.toString(tree.getSubclassNames(Object.class)));
	}
	
	
}
