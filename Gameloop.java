package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Scanner;

public class Gameloop {
	public
		int roomchanged = 0;
		int wintrigger =0;
		int gameexit = 0;
		String current_command;
		Room current_room;
		Mymap newworld;
		Map <String,Item> Inventory = new HashMap <String,Item>();
		
	public Gameloop(Mymap newworld){
		this.newworld=newworld;
	}
	public void start(){
		System.out.println("Welcome!");
		Scanner in= new Scanner(System.in);
		current_room = this.newworld.Rooms.get("Entrance");
		System.out.println(current_room.Getdescription());
		while(true){
			if(wintrigger==1){
				break;
			}
			if(roomchanged == 1){
					roomchanged = 0;
					System.out.println(current_room.Getdescription());
			}
			current_command = in.nextLine();
			int exit = ProcessCommamd(current_command);
			if(exit==1)break;
		}
		//System.out.println("Thank you!");
	}/*
	public int Checktrigger(String itemname){
		int triggerset = 0;
		if(current_room.Gettrigger()!=null){
			if(current_room.Gettrigger().command.equals(current_command)){
				String obname =current_room.Gettrigger().Getcondition().get("object");
				if(current_room.Gettrigger().Getcondition().containsKey("has")){
					String conmeet = current_room.Gettrigger().Getcondition().get("has");
					String ownership = current_room.Gettrigger().Getcondition().get("owner");
					if(conmeet.equals("no")){
						if(newworld.Rooms.containsKey(ownership)){
							if(newworld.Rooms.get(ownership).Items.contains(obname)){
								triggerset =0;
							}else if(newworld.Rooms.get(ownership).creatures.contains(obname)){
								triggerset =0;
							}else if(newworld.Rooms.get(ownership).containers.contains(obname)){	
								triggerset =0;
							}else {
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}
						}else if(newworld.Containers.containsKey(ownership)){
							if(newworld.Containers.get(ownership).items.contains(obname)){
								triggerset =0;
							}else {
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}
						}else if(ownership.equals("inventory")){
							if(Inventory.containsKey(obname)){
								triggerset =0;
							}else{
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}
						}
					}else{
						if(newworld.Rooms.containsKey(ownership)){
							if(newworld.Rooms.get(ownership).Items.contains(obname)){
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}else if(newworld.Rooms.get(ownership).creatures.contains(obname)){
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}else if(newworld.Rooms.get(ownership).containers.contains(obname)){	
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}else {
								triggerset =0;
							}
						}else if(newworld.Containers.containsKey(ownership)){
							if(newworld.Containers.get(ownership).items.contains(obname)){
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}else {
								triggerset =0;
							}
						}else if(ownership.equals("inventory")){
							if(Inventory.containsKey(obname)){
								triggerset =1;
								System.out.println(current_room.Gettrigger().Getprint());
							}else{
								triggerset =0;
							}
						}
					}
				}else{
					if(newworld.Containers.containsKey(obname)){
						if(newworld.Containers.get(obname).Getstatus().equals(current_room.Gettrigger().Getcondition().get("status"))){
							System.out.println(current_room.Gettrigger().Getprint());
							triggerset = 1;
						}else{
							triggerset = 0;
						}
					}else if(newworld.Items.containsKey(obname)){
						if(newworld.Items.get(obname).Getstatus().equals(current_room.Gettrigger().Getcondition().get("status"))){
							System.out.println(current_room.Gettrigger().Getprint());
							triggerset =1;
						}else{
							triggerset =0;
						}
					}else if(newworld.Creatures.containsKey(obname)){
						if(newworld.Creatures.get(obname).Getstatus().equals(current_room.Gettrigger().Getcondition().get("status"))){
							System.out.println(current_room.Gettrigger().Getprint());
							triggerset =1;
						}else{
							triggerset =0;
						}
					}
				}
			}else if(newworld.Creatures.get(itemname)!=null){
				if(newworld.Creatures.get(itemname).Gettrigger()!=null){
					Trigger newtrigger = newworld.Creatures.get(itemname).Gettrigger();
					String obname =newworld.Creatures.get(itemname).Gettrigger().Getcondition().get("object");
					String status =newworld.Creatures.get(itemname).Gettrigger().Getcondition().get("status");
					//System.out.println("Creature's trigger "+obname+" "+status);
					if(Inventory.containsKey(obname)){
						if(newworld.Items.get(obname).Getstatus().equals(status)){
							triggerset = 1;
							if(newtrigger.typein!=1){
								newworld.Creatures.get(itemname).Gettrigger().typein=1;
							//	System.out.println(newworld.Creatures.get(itemname).Gettrigger().Getprint());
								List<String> prints = newworld.Creatures.get(itemname).Gettrigger().Getprint();
								Iterator<String> a = prints.iterator();
								while(a.hasNext()){
									System.out.println(a.next());
								}
							}
							if(newtrigger.Getaction()!=null){
								Iterator<String> a = newtrigger.Getaction().iterator();
								while(a.hasNext()){
									String b = a.next();
									ProcessCommamd(b);
								}
							}
						}
					}
				}
			}
		}else{
			//triggerset = 1;
		}
		return triggerset;
	}*/
	
	public boolean checktrigger(Trigger tbcheck){
		//if checktrigger activated, return ture;
		//else return false;
		if(tbcheck.typein==1){
			//single and already been activated
			return false;
		}else if(tbcheck.typein==0){
			//single and not been activated
			if(tbcheck.command!=""){
				if(tbcheck.command.equals(current_command)){
					if(tbcheck.condition!=null){
						if(CheckCondition(tbcheck.condition)){
							//condition met
							tbcheck.typein=1;
							return true;
						}else{
							//condition not met
							return false;
						}
					}else{
						// don't have condition
						tbcheck.typein=1;
						return true;
					}
				}else{
					//need command but the current command is not the one
					return false;
				}
			}else{
				if(tbcheck.condition!=null){
					if(CheckCondition(tbcheck.condition)){
						//condition met
						tbcheck.typein=1;
						return true;
					}else{
						//condition not met
						return false;
					}
				}else{
					tbcheck.typein=1;
					// don't have condition
					return true;
				}
				//no command needed to activate
			}
		}else{
			//not single
			if(tbcheck.command!=""){
				if(tbcheck.command.equals(current_command)){
					if(tbcheck.condition!=null){
						if(CheckCondition(tbcheck.condition)){
							//condition met
					//		tbcheck.typein=1;
							return true;
						}else{
							//condition not met
							return false;
						}
					}else{
						// don't have condition
					//	tbcheck.typein=1;
						return true;
					}
				}else{
					//need command but the current command is not the one
					return false;
				}
			}else{
				if(tbcheck.condition!=null){
					if(CheckCondition(tbcheck.condition)){
						//condition met
				//		tbcheck.typein=1;
						return true;
					}else{
						//condition not met
						return false;
					}
				}else{
			//		tbcheck.typein=1;
					// don't have condition
					return true;
				}
				//no command needed to activate
			}
		}
		//return false;
	}
	
	// proceed to another room if possible
	public void Moving(){
		if(current_room.Getborders()!=null){
			Iterator<Border> a = (current_room.Getborders()).iterator();
			while(a.hasNext()){
				Border newborder =  (Border) a.next();
				if(newborder.Getdirection().equals(current_command)){
					current_room = this.newworld.Rooms.get(newborder.Getname());
					roomchanged = 1;
					break;
				}
			}
			if(roomchanged!=1)System.out.println("you can't go that direction.");
		}else{
			System.out.println("you are trapped!");
		}
	}
	//show the current surroundings of the current room
	public void Look(){
		System.out.println(current_room.Getdescription());
		if(current_room.GetItems()!=null){
			Iterator<Item> a = (current_room.GetItems()).iterator();
			System.out.println("Items:");
			while(a.hasNext()){
				Item b = a.next();
				//String status = "";
				//if(b.Getstatus()!=null)status = b.Getstatus();
				//System.out.println("has sth?");
				System.out.println(" "+b.Getname());
			}
		}
		if(current_room.GetContainer()!=null){
			Iterator<Container> a = (current_room.GetContainer()).iterator();
			System.out.println();
			System.out.println("Containers:");
			while(a.hasNext()){
				Container b = a.next();
				String status = "";
				if(b.Getstatus()!=null)status = b.Getstatus();
				//System.out.println("has sth?");
				System.out.println(" "+b.Getname()+" "+status);
			}
		}
		if(current_room.GetCreature()!=null){
			Iterator<String> a = (current_room.GetCreature()).iterator();
			System.out.println();
			System.out.println("Creatures:");
			while(a.hasNext()){
				String b = a.next();
			//	int set = Checktrigger(b);
				//if(set==1){
					System.out.println(" "+b);
			//	}
			}
		}
	}
	// show the current contents of the inventory
	public void showinventory(){
		if(Inventory.keySet()!=null){
			System.out.println("Inventory: ");
			List<String> keys = new ArrayList<String>(Inventory.keySet());
			for (String key: keys) {
				Item current_one = Inventory.get(key);
				String status = "";
				if(current_one.Getstatus()!=null)status = current_one.Getstatus();
				System.out.println(current_one.Getname());
			//	System.out.println(current_one.Getname()+" Status "+status);
			}
		}else{
			System.out.println("you don't have anything");
		}
	}
	
	//take command
	public void take(String Itemname){
		//System.out.println("take + "+Itemname);
		if(current_room.GetItems()!=null){
			Iterator<Item> b = (current_room.GetItems()).iterator();
			int count = 0;
			while(b.hasNext()){
				Item newitem = b.next();
				if(newitem.Getname().equals(Itemname)){
					Inventory.put(Itemname,newworld.Items.get(Itemname));
					System.out.println(Itemname+" added to inventory");
					current_room.GetItems().remove(current_room.GetItems().get(count));
					break;
				}
				count++;
			}
		}else{
			System.out.println("Error");
		}
	}
	//open exit or open an container
	public void open(String Itemname){
		//System.out.println("open + "+Itemname);
		if(Itemname.equals("exit")){
			if(current_room.Gettype().equals("exit")){
				System.out.println("Game Over");
				wintrigger =1;
			}else{
				System.out.println("you can't finish the game here!");
			}
		}else if(current_room.GetContainer()!=null){
			Iterator<Container> b = (current_room.GetContainer()).iterator();
			while(b.hasNext()){
				Container newcontainer = b.next();
				if(newcontainer.Getname().equals(Itemname)){
					String status = newworld.GetContainer(newcontainer.Getname()).Getstatus();
					//System.out.println(newworld.GetContainer(newcontainer.Getname()).Getname());
					if(status==""){
						status="unlocked";
					}
					List<String>removeable = new ArrayList<String>();
					if(status.equals("unlocked")){
						Iterator<String> c = newworld.GetContainer(newcontainer.Getname()).Getitems().iterator();
						int count=0;
						while(c.hasNext()){
							String item = c.next();
							Item newitem = new Item();
							newitem.Setname(item);
							current_room.GetItems().add(newitem);
							System.out.println("you found "+item+"!");
							removeable.add(item);
							count++;
						}
						if(count==0){
							System.out.println("The "+newcontainer.Getname()+" is empty");
						}else{
							Iterator<String> h = removeable.iterator();
							while(h.hasNext()){
								String item = h.next();
								newworld.GetContainer(newcontainer.Getname()).RemoveItem(item);
							}	
						}
					}else if(newworld.GetContainer(newcontainer.Getname()).Getstatus().equals("locked")){
						System.out.println(newcontainer.Getname()+" is locked");
					}
					//System.out.println("Can I open the "+Itemname+" ?");
					break;
				}
				
			}
		}else{
			System.out.println("Error");
		}
	}
	//drop an item from inventory
	public void drop(String Itemname){
		//System.out.println("drop + "+Itemname);
		if(Inventory.get(Itemname)!=null){
			Item newitem = new Item();
			newitem.Setname(Itemname);
			current_room.SetItem(newitem);
			Inventory.remove(Itemname);
			System.out.println(Itemname+" dropped");
		}else{
			System.out.println("you don't have that");
		}
	}
	//put item in a container
	public void put(String Itemname, String Container){
		//System.out.println("put + "+Itemname+" "+Container);
		if(Inventory.containsKey(Itemname)){
			Iterator<Container> b =current_room.GetContainer().iterator(); // need to check trigger
			while(b.hasNext()){
				Container newcontainer = b.next();
				if(newcontainer.Getname().equals(Container)){
					if(newworld.Containers.get(newcontainer.Getname()).Getaccept()==""){
						//can accept everything
						if(newworld.Containers.get(newcontainer.Getname()).Getstatus().equals("locked")){
							
						}else{
							newworld.Containers.get(newcontainer.Getname()).Setitem(Itemname);
							System.out.println("Item "+Itemname+" added to "+newcontainer.Getname());
						}
					}else{
						if(newworld.Containers.get(newcontainer.Getname()).Getaccept().equals(Itemname)){
							newworld.Containers.get(newcontainer.Getname()).Setitem(Itemname);
							System.out.println("Item "+Itemname+" added to "+newcontainer.Getname());
						}
					}
				}
			}
		}else{
			System.out.println("Error");
		}
	}
	
	/*
	public boolean Containertrigger(Container newcontainer, String Itemname){
		if(newworld.GetContainer(newcontainer.Getname()).Gettrigger()==null){
			//System.out.println("trigger null");
		};
		if(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcommand().equals("")){
		//	System.out.println("command null");
		}
	//	System.out.println(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcommand());
		if(!newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcommand().equals("")){
			if(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcommand().equals(current_command)){
				if(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition()!=null){
					String obname = newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("object");
					String status = newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("status");
					if(newworld.Items.get(Itemname).Getname().equals(obname)){
						if(newworld.Items.get(Itemname).Getstatus().equals(status)){
							List<String> prints = newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getprint();
							Iterator<String> a = prints.iterator();
							while(a.hasNext()){
								System.out.println(a.next());
							}
							List<String> actions = newworld.Containers.get(newcontainer.Getname()).Gettrigger().Getaction();
							if(actions==null)return true;
							Iterator<String> Hi = actions.iterator();
							//Inventory.remove(Itemname);
							while(Hi.hasNext()){
								String action = Hi.next();
								ProcessCommamd(action);
							}
							return true;
						}
					}
				}else{
					List<String> prints = newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getprint();
					Iterator<String> a = prints.iterator();
					while(a.hasNext()){
						System.out.println(a.next());
					}
					List<String> actions = newworld.Containers.get(newcontainer.Getname()).Gettrigger().Getaction();
					Iterator<String> Hi = actions.iterator();
					//Inventory.remove(Itemname);
					while(Hi.hasNext()){
						String action = Hi.next();
						//System.out.println("action is "+action);
						ProcessCommamd(action);
					}
					return true;
				}
			}
		}else if(newworld.GetContainer(newcontainer.Getname()).Getaccept().equals(Itemname)){
			newworld.GetContainer(newcontainer.Getname()).Setitem(Itemname);
			if(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("object").equals(Itemname)){
				//System.out.println(newcontainer.Getname());
				//System.out.println(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("object"));
				//System.out.println(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("has"));
				if(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("has")!=null){
					if(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getcondition().get("has").equals("yes")){
						//System.out.println(newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getprint());
						List<String> prints = newworld.GetContainer(newcontainer.Getname()).Gettrigger().Getprint();
						Iterator<String> a = prints.iterator();
						while(a.hasNext()){
							System.out.println(a.next());
						}
						List<String> actions = newworld.Containers.get(newcontainer.Getname()).Gettrigger().Getaction();
						if(actions==null)return true;
						Iterator<String> Hi = actions.iterator();
						//Inventory.remove(Itemname);
						while(Hi.hasNext()){
							String action = Hi.next();
							//System.out.println("action is "+action);
							ProcessCommamd(action);
						}
						return true;
					}
				}
			}
		}
		return false;
	}*/
	//Read a item in Inventory
	public void read(String Itemname){
		//System.out.println("read "+Itemname);
		if(Inventory.containsKey(Itemname)){	
			Item b =  newworld.Items.get(Itemname);
			if(b.Getwriting()!=null){
				System.out.println(b.Getwriting());
			}else{
				System.out.println("Nothing Written");
			}	
		}else{
			System.out.println("Error");
		}
	}
	//Add turn on command
	public void turnon(String Itemname){
		if(Inventory.containsKey(Itemname)){
			Item newitem = newworld.Items.get(Itemname);
			if(newitem.Getturnon()!=null){
				Map <String,String> turnon = newitem.Getturnon();
				String command = turnon.get("action");
				System.out.println(turnon.get("print"));
				ProcessCommamd(command);
			}
			//newworld.Items.get(Itemname).Setstatus(newstatus)
		}else{
			System.out.println("Error");
		}
		
	}
	//Add an object to the current_room
	public void Add(String obname,String des){
		if(newworld.Rooms.containsKey(des)){
			if(newworld.Containers.containsKey(obname)){
			//	if(newworld.Rooms.get(des).containers.)
				Container newcontainer = new Container();
				newcontainer.Setname(obname);
				newworld.Rooms.get(des).Setcontainer(newcontainer);
			}else if(newworld.Creatures.containsKey(obname)){
				Creature newcreature = new Creature();
				newcreature.Setname(obname);
				if(!newworld.Rooms.get(des).creatures.contains(obname)){
					newworld.Rooms.get(des).Setcreature(obname);
				}
			}else{
				Item newitem = new Item();
				newitem.Setname(obname);
				if(!newworld.Rooms.get(des).Items.contains(newitem)){
					newworld.Rooms.get(des).SetItem(newitem);
				}
			}
		}else if(newworld.Containers.containsKey(des)){
			if(newworld.Items.containsKey(obname)){
				if(!newworld.Containers.get(des).items.contains(obname)){
					newworld.Containers.get(des).Setitem(obname);
				}
			}
		}	
	}
	//Update status
	public void Update(String obname, String status){
		if(Inventory.containsKey(obname)){
			newworld.Items.get(obname).Setstatus(status);
			newworld.Items.get(obname).Setstatus(status);
		}else if(newworld.Rooms.containsKey(obname)){
			newworld.Rooms.get(obname).Setstatus(status);
		}else if(newworld.Items.containsKey(obname)){
			newworld.Items.get(obname).Setstatus(status);
		}else if(newworld.Containers.containsKey(obname)){
			newworld.Containers.get(obname).Setstatus(status);
		}else if(newworld.Creatures.containsKey(obname)){
			newworld.Creatures.get(obname).Setstatus(status);
		}
	}
	//check if the condition for attack or trigger is met
	public boolean CheckCondition(Map<String, String> condition){
		//if condition met, return ture;
		//else return false;
		//System.out.println("check condition");
		//System.out.println("check conditon");
		if(condition.get("has")!=null){
			if(condition.get("has").equals("yes")){
				if(condition.get("owner")!=null){
					if(condition.get("owner").equals("inventory")){
						System.out.println("has "+condition.get("object")+" in inventory");
						if(Inventory.containsKey(condition.get("object"))){
							return true;
						}
					}else{
						Container check = new Container();
						check = newworld.Containers.get(condition.get("owner"));
						if(check.items.contains(condition.get("object"))){
							return true;
						}
					}
					//Container checkone
				}
			}else if(condition.get("has").equals("no")){
				if(condition.get("owner")!=null){
					if(condition.get("owner").equals("inventory")){
						if(Inventory.containsKey(condition.get("object"))){
							return false;
						}else{
							return true;
						}
					}else{
						Container check = new Container();
						check = newworld.Containers.get(condition.get("owner"));
						if(check.items.contains(condition.get("object"))){
							return false;
						}else {
							return true;
						}
					}
					//Container checkone
				}
			}
		}else if(Inventory.containsKey(condition.get("object"))){
			//is not a ownership problem
		   if(newworld.Items.get(condition.get("object")).Getstatus()!=null){
			   System.out.println(newworld.Items.get(condition.get("object")).Getstatus());
			   System.out.println(condition.get("status"));
			   if(newworld.Items.get(condition.get("object")).Getstatus().equals(condition.get("status"))){
				   return true;
			   }
		   }
		}else{
			//creature status check
			if(newworld.Creatures.get(condition.get("object"))!=null){
				if(newworld.Creatures.get(condition.get("object")).Getstatus().equals(condition.get("status"))){
					   return true;
				 }
			}
			//container status check
			if(newworld.Containers.get(condition.get("object"))!=null){
				if(newworld.Containers.get(condition.get("object")).Getstatus().equals(condition.get("status"))){
					   return true;
				 }
			}
			
		}
		return false;
	}
	//newattack
	public void newattack(String target, String weapon){
		if(current_room.creatures.contains(target)){
			if(Inventory.containsKey(weapon)){
				System.out.println("You assult the "+target+" with "+weapon);
				if(newworld.Creatures.get(target).Getvul().contains(weapon)){
					//System.out.println("good assult");
					if( newworld.Creatures.get(target).attack()!=null){
						Attack attack = newworld.Creatures.get(target).attack();
						if(attack.Condition==null){
						//	System.out.println("here");
							List<String> prints = attack.Getprint();
							if(prints!=null){
								Iterator<String> a = prints.iterator();
								while(a.hasNext()){
									System.out.println(a.next());
								}
							}
							//excute the actions
							List<String> actions = attack.Getaction();
							if(actions!=null){
								Iterator<String> a = (attack.Getaction()).iterator();	
								while(a.hasNext()){
									String b = a.next();
									ProcessCommamd(b);
								}
							}
						}else{
							if(CheckCondition(attack.Condition)){
								//excute the prints
								System.out.println("succeed");
								List<String> prints = attack.Getprint();
								if(prints!=null){
									Iterator<String> a = prints.iterator();
									while(a.hasNext()){
										System.out.println(a.next());
									}
								}
								//excute the actions
								List<String> actions = attack.Getaction();
								if(actions!=null){
									Iterator<String> a = (attack.Getaction()).iterator();	
									while(a.hasNext()){
										String b = a.next();
										ProcessCommamd(b);
									}
								}
							}//end of excute 
						}
					}
				}else{
					//the weapon is not vun of the creature
				}
			}else{
				System.out.println("you don't have that");
			}
		}else{
			System.out.println("Error");
		}

	}/*
	public void attack(String target, String weapon){
		Attack newattack = newworld.Creatures.get(target).attack();
		Map<String, String> newcondition =null;
		if(newattack.Getcondition()!=null){
			newcondition = newattack.Getcondition();
		}
		if(current_room.creatures.contains(target)){
			if(newcondition!=null){
				String obname = newcondition.get("object");
				if(obname!=null){
					if(newworld.Items.get(obname)!=null){
					//	System.out.println("vun is "+newworld.Creatures.get(target).Getname());
					//	System.out.println("vun is "+newworld.Creatures.get(target).Getvul());
						if(newworld.Creatures.get(target).Getvul().contains(obname)){
							if(newworld.Items.get(obname).Getstatus().equals(newcondition.get("status"))){
								current_room.RemoveCreature(target);
								System.out.println(newattack.Getprint());
								Iterator<String> a = (newattack.Getaction()).iterator();
								while(a.hasNext()){
									String b = a.next();
									ProcessCommamd(b);
								}
							}
						}else{
							System.out.println("you can't attack "+target+" with that.");
						}
					}else if(newworld.Creatures.get(obname)!=null){
						System.out.println(newworld.Creatures.get(obname).Getstatus());
						System.out.println(newcondition.get("status"));
						if(newworld.Creatures.get(target).Getvul().equals(obname)){
							if(newworld.Creatures.get(obname).Getstatus().equals(newcondition.get("status"))){
								current_room.RemoveCreature(target);
								System.out.println(newattack.Getprint());
								Iterator<String> a = (newattack.Getaction()).iterator();
								while(a.hasNext()){
									String b = a.next();
									ProcessCommamd(b);
								}
							}
						}
						
					}
				}
			}
		}else{
			System.out.println("Error");
		}
	}
	*/
	
	//Delete an object
	public void Delete(String obname){
		//System.out.println(obname);
		if(Inventory.containsKey(obname)){
			newworld.Items.remove(obname);
		}else if(newworld.Rooms.containsKey(obname)){
			Room tobede = newworld.GetRoom(obname);
			if(tobede!=null){
				newworld.Rooms.remove(tobede);
			}
		}else if(newworld.Items.containsKey(obname)){
			//Iterator<Room> temproom =  
			List<String> keys = new ArrayList<String>(newworld.Rooms.keySet());
			for(String key:keys){
				Room temproom = newworld.Rooms.get(key);
				Iterator<Item> haha =temproom.Items.iterator();
				Item tobede = new Item();
				while(haha.hasNext()){
					Item bad = haha.next();
					if(bad.Getname().equals(obname)){
						tobede = bad;
					}
				}
				if(tobede!=null){
					temproom.Items.remove(tobede);
				}
			}
		}else if(current_room.containers.contains(obname)){
			List<String> keys = new ArrayList<String>(newworld.Rooms.keySet());
			for(String key:keys){
				Room temproom = newworld.Rooms.get(key);
				if(temproom.containers!=null){
					Iterator<Container> haha =temproom.containers.iterator();
					Container tobede = new Container();
					while(haha.hasNext()){
						Container bad = haha.next();
						if(bad.Getname().equals(obname)){
							tobede = bad;
						}
					}
					if(tobede!=null){
						temproom.containers.remove(tobede);
					}
				}
			}
		}else if(newworld.Creatures.containsKey(obname)){
			List<String> keys = new ArrayList<String>(newworld.Rooms.keySet());
			for(String key:keys){
				Room temproom = newworld.Rooms.get(key);
				if(temproom.creatures!=null){
					Iterator<String> haha =temproom.creatures.iterator();
					String tobede="";
					while(haha.hasNext()){
						String bad = haha.next();
						if(bad.equals(obname)){
							tobede = bad;
						}
					}
					if(tobede!=""){
						temproom.creatures.remove(tobede);
					}
				}
			}
		}
	}
	
	public boolean Triggers(){
		//check room triggers
		int i = 0;
		if(current_room.tricount!=0){	
			Map<Integer,Trigger> temp = current_room.Gettriggers();
			for(i = 0;i<current_room.tricount;i++){
			//	System.out.println("tri is "+i);
				Trigger temptri = temp.get(i);
			//	newworld.Containers.get(Container).trigger = temptri;
				if(checktrigger(temptri)){
					//execute
					List<String> prints = temptri.Getprint();
					if(prints!=null){
						Iterator<String> a = prints.iterator();
						while(a.hasNext()){
							System.out.println(a.next());
						}
					}
					//excute the actions
					List<String> actions = temptri.Getaction();
					if(actions!=null){
						Iterator<String> a = actions.iterator();	
						while(a.hasNext()){
							String b = a.next();
							ProcessCommamd(b);
						}
					}
					return true;
				};
			}
		}
		//check container triggers
		if(!current_room.containers.isEmpty()){
			Iterator<Container> a = current_room.containers.iterator();
			while(a.hasNext()){
				String b = a.next().Getname();
				if(newworld.Containers.get(b).tricount!=0){
					Map<Integer,Trigger> temp = newworld.Containers.get(b).Gettriggers();
					for(i = 0;i<newworld.Containers.get(b).tricount;i++){
						//	System.out.println("tri is "+i);
							Trigger temptri = temp.get(i);
						//	newworld.Containers.get(Container).trigger = temptri;
							if(checktrigger(temptri)){
								List<String> prints = temptri.Getprint();
								if(prints!=null){
									Iterator<String> c = prints.iterator();
									while(c.hasNext()){
										System.out.println(c.next());
									}
								}
								//excute the actions
								List<String> actions = temptri.Getaction();
								if(actions!=null){
									Iterator<String> c = actions.iterator();	
									while(c.hasNext()){
										String d = c.next();
										ProcessCommamd(d);
									}
								}
								return true;
							}
						}	
				}else{
					//this container doesn't has trigger
				}
			}
		}
		//check item triggers
		if(!current_room.Items.isEmpty()){
			Iterator<Item> a = current_room.Items.iterator();
			while(a.hasNext()){
				String b = a.next().Getname();
				if(newworld.Items.get(b).tricount!=0){
					Map<Integer,Trigger> temp = newworld.Items.get(b).Gettriggers();
					for(i = 0;i<newworld.Items.get(b).tricount;i++){
						//	System.out.println("tri is "+i);
							Trigger temptri = temp.get(i);
						//	newworld.Containers.get(Container).trigger = temptri;
							if(checktrigger(temptri)){
								//excute
								List<String> prints = temptri.Getprint();
								if(prints!=null){
									Iterator<String> c = prints.iterator();
									while(c.hasNext()){
										System.out.println(c.next());
									}
								}
								//excute the actions
								List<String> actions = temptri.Getaction();
								if(actions!=null){
									Iterator<String> c = actions.iterator();	
									while(c.hasNext()){
										String d = c.next();
										ProcessCommamd(d);
									}
								}
								return true;
							};
						}	
				}else{
					//this item doesn't has trigger
				}
			}
		}
		//check creature triggers
		if(!current_room.creatures.isEmpty()){
			Iterator<String> a = current_room.creatures.iterator();
			while(a.hasNext()){
				String b = a.next();
				if(newworld.Creatures.get(b).tricount!=0){
					Map<Integer,Trigger> temp = newworld.Creatures.get(b).Gettriggers();
					for(i = 0;i<newworld.Creatures.get(b).tricount;i++){
						//	System.out.println("tri is "+i);					
							Trigger temptri = temp.get(i);
						//	newworld.Containers.get(Container).trigger = temptri;
							if(checktrigger(temptri)){
							//	System.out.println("the creature trigger activate!");
								List<String> prints = temptri.Getprint();
								if(prints!=null){
									Iterator<String> c = prints.iterator();
									while(c.hasNext()){
										System.out.println(c.next());
									}
								}
								//excute the actions
								List<String> actions = temptri.Getaction();
								if(actions!=null){
									Iterator<String> c = actions.iterator();	
									while(c.hasNext()){
										String d = c.next();
										System.out.println(d);
										ProcessCommamd(d);
									}
								}
								return true;
							};
						}	
				}else{
					//this creature doesn't has trigger
				}
			}
		}
		return false;
	}
	
	public int ProcessCommamd(String command){
		current_command = command;
	//	int gameexit = 0;
		if(!Triggers()){
			if(command.equals("n")|command.equals("s")|command.equals("w")|command.equals("e")){	
			   this.Moving();
			}
			else if(command.equals("look")){
				this.Look();
			}
			else if(command.equals("i")){	
				showinventory();
			}
			else if(command.matches("take [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				this.take(ItemName);
			}
			else if(command.matches("open [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				open(ItemName);
			}
			else if(command.matches("read [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				read(ItemName);
			}
			else if(command.matches("drop [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				drop(ItemName);
			}
			else if(command.matches("put [a-zA-Z]+ in [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				String ItemName2 = (command.split(" "))[3];
				put(ItemName, ItemName2);
			}
			else if(command.matches("turnon [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				turnon(ItemName);
			}
			else if(command.matches("attack [a-zA-Z]+ with [a-zA-Z]+")){
				String target = (command.split(" "))[1];
				String weapon = (command.split(" "))[3];
				newattack(target,weapon);
			}
			else if(command.matches("Add [a-zA-Z]+ to [a-zA-Z]+")){
				String obname = (command.split(" "))[1];
				String des2 = (command.split(" "))[3];
				Add(obname, des2);
			}
			else if(command.matches("Delete [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				Delete(ItemName);
			}
			else if(command.matches("Update [a-zA-Z]+ to [a-zA-Z]+")){
				String ItemName = (command.split(" "))[1];
				String ItemName2 = (command.split(" "))[3];
				Update(ItemName,ItemName2);
			}
			else if(command.matches("Game Over")){
				gameexit= 1;
			}else{
				System.out.println("Error");
			}
			Triggers();
			return gameexit;
		}
		return gameexit;
	}
}
