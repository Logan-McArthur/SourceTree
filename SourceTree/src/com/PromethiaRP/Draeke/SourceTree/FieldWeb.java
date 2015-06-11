package com.PromethiaRP.Draeke.SourceTree;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldWeb {

	
	private Map<Field, FieldWebNode> fieldWebMap;
	
	public FieldWeb() {
		fieldWebMap = new HashMap<Field, FieldWebNode>();
	}
	
	public void build(Class clss) {
		for (Field fl : clss.getDeclaredFields()) {
			constructNode(fl);
		}
	}
	
	private FieldWebNode constructNode(Field field) {
		FieldWebNode node = new FieldWebNode(field);
		fieldWebMap.put(field, node);
		
		Class fieldClass = field.getType();
		Field[] fields = fieldClass.getDeclaredFields();
		FieldWebNode[] connections = new FieldWebNode[fields.length];
		
		for (int i = 0; i < connections.length; i++) {
			if (fieldWebMap.containsKey(fields[i])) {
				connections[i] = fieldWebMap.get(fields[i]);
			} else {
				connections[i] = constructNode(fields[i]);
			}
		}
		
		node.connections = connections;
		return node;
	}
	
}

class FieldWebNode {
	Field value;
	// Having a connection is a directional path from one node to another
	FieldWebNode[] connections;
	
	public FieldWebNode() {
		this(null);
	}
	public FieldWebNode(Field value) {
		this(value, new FieldWebNode[0]);
	}
	public FieldWebNode(Field value, FieldWebNode[] connections) {
		this.value = value;
		this.connections = connections;
	}
	
	public String toString() {
		return "FieldWebNode containing: " + value.toGenericString();
	}
}