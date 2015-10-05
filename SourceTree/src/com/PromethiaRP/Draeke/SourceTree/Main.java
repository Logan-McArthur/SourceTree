package com.PromethiaRP.Draeke.SourceTree;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
//		ControllerState unit;
//		fX f;
//		SegmentController seg;
//		JS j;
//		ModManager mm;
//		
//		ClassTree tree = new ClassTree();
//		tree.insert(ControllerState.class);
//		tree.insert(fX.class);
//		tree.insert(SegmentController.class);
		
		System.out.println();
	}
	
	public static void processMethods(Method[] methods) {
		for (Method mthd : methods) {
			System.out.println(getMethodSignature(mthd));
			
		}
	}
	
	public static String getMethodSignature(Method method) {
		String str = "";
		str += method.getReturnType().getSimpleName();
		str += " " + method.getName();
		str += "(";
		Class[] parameters = method.getParameterTypes();
		if (parameters.length > 0) {
			str += parameters[0].getSimpleName();
		}
		for (int i = 1; i < parameters.length; i++) {
			str += ", " + parameters[i].getSimpleName();
		}
		str += ")";
		return str;
	}
	
	public static <T> void printArray(T[] array) {
//		if (array.length > 0) {
//			System.out.println(array[0]);
//		}
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
