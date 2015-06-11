package com.PromethiaRP.Draeke.SourceTree;

import org.schema.game.common.data.player.PlayerState;

public class FieldWebTester {

	public static void main(String[] args) {
		FieldWeb web = new FieldWeb();
		
		web.build(PlayerState.class);
		
		System.out.println();
	}
}
