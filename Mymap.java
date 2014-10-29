package org;

import java.util.Map;

public class Mymap {
	public Map <String,Room> Rooms;
	public Map <String,Item> Items;
	public Map <String,Container> Containers;
	public Map <String,Creature> Creatures;
	public void SetRooms(Map <String,Room> newRoom){
		this.Rooms = newRoom;
	}
	public void SetItems(Map <String,Item> newItems){
		this.Items = newItems;
	}
	public void SetContainer(Map <String,Container> newContainer){
		this.Containers = newContainer;
	}
	public void SetCreature(Map <String,Creature> newCreature){
		this.Creatures = newCreature;
	}
	public Room GetRoom(String name){
		return (this.Rooms.get(name));
	}
	public Item GetItem(String name){
		return (this.Items.get(name));
	}
	public Container GetContainer(String name){
		return (this.Containers.get(name));
	}
	public Creature GetCreature(String name){
		return (this.Creatures.get(name));
	}
	
}
