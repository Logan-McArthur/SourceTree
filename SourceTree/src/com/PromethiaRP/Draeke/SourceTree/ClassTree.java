package com.PromethiaRP.Draeke.SourceTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassTree {
	ClassNode root;
	private Set<ClassNode> interfaces = new HashSet<ClassNode>();
	private Map<Class, ClassNode> classNodeMap = new HashMap<Class, ClassNode>();
	
	public ClassTree() {
		root = new ClassNode(Object.class);
		classNodeMap.put(Object.class, root);
	}
	
	public void insert(Class clss) {
		if (clss == null) {
			return;
		}
//		feedTree(root, clss);
		if ( ! classNodeMap.containsKey(clss)) {
			createClassNode(clss);
		}
	}
	

	private void addSuperClass(ClassNode clss) {
		// Check if the superclass is null here to prevent errors from appearing elsewhere
		if (clss.node.getSuperclass() == null) {
			return;
		}
		ClassNode parent = classNodeMap.get(clss.node.getSuperclass());
		if (parent == null) {
			parent = createClassNode(clss.node.getSuperclass());
		}
		parent.children.add(clss);
		clss.superClass = parent;
		
	}
	
	private ClassNode createClassNode(Class clss) {

		ClassNode classNode = new ClassNode(clss);
		addInterfaces(classNode);
		addSuperClass(classNode);
		
		classNodeMap.put(clss, classNode);
		return classNode;
	}
	
	
	private void addInterfaces(ClassNode node) {
		ClassNode tmp;
		// Because it is a for each loop, when there are no inherited interfaces nothing is run
		for (Class impl : node.node.getInterfaces()) {
			tmp = classNodeMap.get(impl);
			if (tmp == null) {
				tmp = createClassNode(impl);
			}
			interfaces.add(tmp);		
			tmp.children.add(node);		// A way to get from the interface to the implementing classes
			node.implementedInterfaces.add(tmp);
		}
	}
	

	
	public boolean contains(Class clss) {
		return classNodeMap.containsKey(clss);
	}
	
	public int size() {
		return classNodeMap.size();
	}
	
	public String[] getSubclassNames(Class clss) {
		if ( ! classNodeMap.containsKey(clss)) {
			throw new IllegalArgumentException("The class " + clss.getName() + " does not exist in the tree.");
		}
		ClassNode request = classNodeMap.get(clss);
		String[] names = new String[request.children.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = request.children.get(i).node.getName();
		}
		return names;
	}
	
	public Class[] getSubclasses(Class clss) {
		if ( ! classNodeMap.containsKey(clss)) {
			throw new IllegalArgumentException("The class " + clss.getName() + " does not exist in the tree.");
		}
		ClassNode request = classNodeMap.get(clss);
		Class[] classes = new Class[request.children.size()];
		classes = request.children.toArray(classes);
		
		return classes;
	}
	
	public Class getTopClass() {
		return root.node;
	}
}

class ClassNode {
	Class node;
	List<ClassNode> children;
	ClassNode superClass;
	List<ClassNode> implementedInterfaces;
	
	public ClassNode(Class clss) {
		this(clss, null, new ArrayList<ClassNode>(), new ArrayList<ClassNode>());
	}
	
	public ClassNode(Class clss, ClassNode superClass, List<ClassNode> chldrn, List<ClassNode> implementedInterfaces) {
		children = chldrn;
		node = clss;
		this.superClass = superClass;
		this.implementedInterfaces = implementedInterfaces;
	}

}