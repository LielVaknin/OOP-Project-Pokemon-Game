package gameClient;
import Server.Game_Server_Ex2;
import api.directed_weighted_graph;
import api.edge_data;
import api.game_service;
import api.node_data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the simplest "Client-Game" main class
 * which uses the "server for moving the "Agents".
 * Note: this code is a very simple no algorithm no threaded mechanism - it is presented just to show the basic
 * use of the "server".
 */
public class SimpleGameClient {
	public static void main(String[] args){
		game_service game = Game_Server_Ex2.getServer(23); // you have [0,23] games
		game.addAgent(0);
		game.addAgent(1);
//		Arena a = new Arena(23);
//		String s = game.toString();
//		System.out.println(s);
		System.out.println(game.getGraph());
		System.out.println(game.getAgents());
		System.out.println(game.getPokemons());
	}
}
