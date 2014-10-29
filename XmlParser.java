package org;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;

import org.Item;
import org.Room;
import org.Trigger;
import org.Mymap;

public class XmlParser{

	public static Mymap Readxml(String filename){
		final String ROOM = "room";
		final String NAME = "name";
		final String DES = "description";
		final String TRIGGER = "trigger";
		final String ITEM = "item";
		final String HAS = "has";
		final String OBJECT = "object";
		final String OWNER = "owner";
		final String CONDITION = "condition";
		final String PRINT = "print";
		final String ACTION = "action";
		final String STATUS = "status";
		final String TYPE = "type";
		final String TURNON = "turnon";
		final String WRITING = "writing";
		final String CONTAINER = "container";
		final String ACCEPT = "accept";
		final String CREATURE = "creature";
		final String VULNERABILITY = "vulnerability";
		final String BORDER = "border";
		final String DIRECTION = "direction";
		final String COMMAND = "command";
		final String ATTACK = "attack";
		int roomflag = 0;
		int itemflag = 0;
		int containerflag = 0;
		Mymap world =null;
		//System.out.println("the file is " + filename);
		File file = new File(filename);
		try{  
			world = new Mymap();
			Map <String,Room> Rooms = new HashMap<String,Room>();
			Map <String,Item> Items = new HashMap<String,Item>();
			Map <String,Container> Containers = new HashMap<String,Container>();
			Map <String,Creature> Creatures = new HashMap<String,Creature>();
		      	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		      	InputStream in = new FileInputStream(file);
		      	XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		      	while (eventReader.hasNext()){
		      		roomflag = 0;
					itemflag = 0;
					containerflag = 0;
		    	  	XMLEvent event = eventReader.nextEvent();
		          	if (event.isStartElement()) {
		        	  //	StartElement startElement = event.asStartElement();
		        	  	if (event.asStartElement().getName().getLocalPart() == (ROOM)){
		        		    Room newRoom = new Room();
		        		    while (eventReader.hasNext()){
		        	  			event = eventReader.nextEvent();      	  			
		        		 	 	if (event.isStartElement()) {
		        		 	 		//System.out.println(event.asStartElement().getName().getLocalPart());
			        				if (event.asStartElement().getName().getLocalPart().equals(NAME)) {
			        	 	   		//this element is a Name
			        					event = eventReader.nextEvent();
			        					newRoom.Setname(event.asCharacters().getData());
			        					continue;
			        				}
			        				if (event.asStartElement().getName().getLocalPart().equals(CONTAINER)) {
				        	 	   		//this element is a Name
				        					event = eventReader.nextEvent();
				        					Container newcontainer = new Container();
				        					newcontainer.Setname(event.asCharacters().getData());
				        					newRoom.Setcontainer(newcontainer);
				        					continue;
			        				}
			        				if (event.asStartElement().getName().getLocalPart().equals(CREATURE)) {
				        	 	   		//this element is a Name
				        					event = eventReader.nextEvent();
				        					//Creature newcreature = new Creature();
				        					//newcreature.Setname(event.asCharacters().getData());
				        					newRoom.Setcreature(event.asCharacters().getData());
				        					continue;
			        				}
			        				if (event.asStartElement().getName().getLocalPart().equals(TYPE)) {
				        	 	   		//this element is a Name
				        					event = eventReader.nextEvent();
				        					newRoom.Settype(event.asCharacters().getData());
				        					continue;
				        				}
			        				if (event.asStartElement().getName().getLocalPart().equals(DES)) {
			        				  	//this element is a Description
			        				  	event = eventReader.nextEvent();
			        				  	newRoom.Setdescription(event.asCharacters().getData());
			        				  	continue;
			        			  	}
									//Starting to process complex elements i.e Item, Trigger	      
			        			  	if (event.asStartElement().getName().getLocalPart().equals(TRIGGER)) {     
			        				        Trigger newTrigger = new Trigger();  		        				       
			        				        while(eventReader.hasNext()){
			        				        	event = eventReader.nextEvent();
			        				        	//Complete reading of a trigger
			        				        	if (event.isStartElement()) {
			        				        		if (event.asStartElement().getName().getLocalPart().equals(PRINT)){
			        				   	  				event = eventReader.nextEvent();
			        				   	  				newTrigger.Setprint(event.asCharacters().getData());
			        				   	  				continue;
			        				    			}
			        				        		if (event.asStartElement().getName().getLocalPart().equals(ACTION)){
			        				   	 				event = eventReader.nextEvent();
			        				   	 				newTrigger.Setaction(event.asCharacters().getData());
			        				   	 				continue;
			        				    			}
			        				    			if (event.asStartElement().getName().getLocalPart().equals(COMMAND)){
			        				   	 				event = eventReader.nextEvent();
			        				   	 				newTrigger.Setcommand(event.asCharacters().getData());
			        				   	 				continue;
			        				    			}
			        				    			if (event.asStartElement().getName().getLocalPart().equals(TYPE)){
			        				   	  				event = eventReader.nextEvent();
			        				   	  				newTrigger.Settype(event.asCharacters().getData());
			        				   	  				continue;
			        				    			}
			        				        		if (event.asStartElement().getName().getLocalPart().equals(CONDITION)){
			        				      			  	Map <String, String> newcondition = new HashMap<String,String>();
			        				      			  	while(eventReader.hasNext()){
			        				      				  	event = eventReader.nextEvent();
			        				      				  	if (event.isStartElement()) {
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(HAS)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(HAS,event.asCharacters().getData());
			        				      						  	continue;
			        				      					  	}
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(OBJECT)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(OBJECT,event.asCharacters().getData());
			        				      						  	continue;
			        				      					  	}
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(OWNER)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(OWNER,event.asCharacters().getData());
			        				      						  	continue;
			        				      					  	}
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(STATUS,event.asCharacters().getData());
			        				      						  	continue;
			        				      				  		}
			        				      				  	}
			        				      				  	if (event.isEndElement()){
			        				      				  		EndElement endElement = event.asEndElement();		        		      	
			        				      				  		if (endElement.getName().getLocalPart() == (CONDITION)){
			        				      				  			newTrigger.Setcondition(newcondition);
			        				      				  			break;  
			        				      				  		}else continue;
			        				      				  	}
			        				      			    }
			        				      			    continue;
			        				      			}	  			        				
			        				    		}
			        				    		if (event.isEndElement()){
			        				    			EndElement endElement = event.asEndElement();		        		      	
    	    			 	     			  		if (endElement.getName().getLocalPart() == (TRIGGER)){
    	    			 	     			  			newRoom.Settrigger(newTrigger); 
    	    			 	     			  			break;  
    	    			 	     			  		}
			        				    		}
			        				        }
			        				        continue;
			        			    }
			        			    if (event.asStartElement().getName().getLocalPart().equals(ITEM)){
			        			    	//FIND a new item			    	
			        				  	Item newItem = new Item();
			        				  	event = eventReader.nextEvent();
			        				 // 	System.out.println("name is "+event.asCharacters().getData());
				    	 	   			newItem.Setname(event.asCharacters().getData());
				    	 	   			newRoom.SetItem(newItem);
				    	 	   			continue;
			        			  	}
			        			  	if (event.asStartElement().getName().getLocalPart().equals(BORDER)) {
			        				  	Border newborder = new Border();
			        				  	while(eventReader.hasNext()){
			        				  		event = eventReader.nextEvent();
			        				  		if (event.isStartElement()) {
			        				  			if (event.asStartElement().getName().getLocalPart().equals(NAME)){
			        				  				event = eventReader.nextEvent();
				    	 	   				  		newborder.Setname(event.asCharacters().getData());
				    	 	   				  		continue;
			        				  			}
			        				  			if (event.asStartElement().getName().getLocalPart().equals(DIRECTION)){
			        				  				event = eventReader.nextEvent();
			        				  				if(event.asCharacters().getData().equals("north")){
			        				  					newborder.Setdirection("n");	
			        				  				}
			        				  				else if(event.asCharacters().getData().equals("south")){
			        				  					newborder.Setdirection("s");
			        				  				}
			        				  				else if(event.asCharacters().getData().equals("east")){
			        				  					newborder.Setdirection("e");
			        				  				}
			        				  				else if(event.asCharacters().getData().equals("west")){
			        				  					newborder.Setdirection("w");
			        				  				}
				    	 	   				  		
				    	 	   				  		continue;
			        				  			}
			        				  		}
			        				  		if (event.isEndElement()) {
			        				  			if (event.asEndElement().getName().getLocalPart() == (BORDER)) {
			        				  				newRoom.Setborder(newborder);
			        				  				break;
			        				  			}else continue;
			        				  		}
			        				  	}
			        			  	}

			        	 	 	}
		        		 	 	if (event.isEndElement()) {
		        		 	 		EndElement endElement = event.asEndElement();
		        		 	 		if (endElement.getName().getLocalPart() == (ROOM)) {
		        		 	 			Rooms.put(newRoom.Getname(),newRoom);
		        		 	 			roomflag = 1;
		        		 	 			break;
		        		 	 		}else continue;
		        		  		}
		        		  	}
		        	  	}
		        	  	if(roomflag==1)continue;
		        	  	if (event.asStartElement().getName().getLocalPart().equals(ITEM)) {
			       			//FIND a new item
			       		  	Item newItem = new Item();
			       		  	while(eventReader.hasNext()){
			       		  		event = eventReader.nextEvent();
			       		  		if (event.isStartElement()) {
			       		  		//	System.out.println(event.asStartElement().getName());
			       		  			if (event.asStartElement().getName().getLocalPart().equals(NAME)){
			       		  				event = eventReader.nextEvent();
		    	 	  			  		newItem.Setname(event.asCharacters().getData());
		    	 	  			  		continue;
			       		  			}
			       		  			if (event.asStartElement().getName().getLocalPart().equals(WRITING)){
			       		  				event = eventReader.nextEvent();
	    	 	  			  			newItem.Setwriting(event.asCharacters().getData());
	    	 	  			  			//System.out.println(newItem.Getwriting()+" ");
	    	 	  			  			continue;
			       		  			}
			       		  			if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
			       		  				event = eventReader.nextEvent();
			       		  				newItem.Setstatus(event.asCharacters().getData());
			       		  				continue;
			       		  			}
			       		  			if (event.asStartElement().getName().getLocalPart().equals(DES)){
			       		  				event = eventReader.nextEvent();
			       		  				newItem.Setdescription(event.asCharacters().getData());
			       		  				continue;
			       		  			}
			       		  			if (event.asStartElement().getName().getLocalPart().equals(TURNON)){
			       		  				Map <String, String> newturnon = new HashMap<String,String>();
			       		  				while(eventReader.hasNext()){
		       		      				  	event = eventReader.nextEvent();
		       		      				  	if (event.isStartElement()) {
		       		      					  	if (event.asStartElement().getName().getLocalPart().equals(PRINT)){
		       		      					  		event = eventReader.nextEvent();
		       		      					  		newturnon.put(PRINT,event.asCharacters().getData());
		       		      					  		continue;
			       		  						}
		       		      					  	if (event.asStartElement().getName().getLocalPart().equals(ACTION)){
		       		      					  		event = eventReader.nextEvent();
		       		      					  		newturnon.put(ACTION,event.asCharacters().getData());
		       		      					  		continue;
			       		  						}
			       		  					}
		       		      				  	if (event.isEndElement()){
		       		      				  		EndElement endElement = event.asEndElement();		        		      	
		       		      				  		if (endElement.getName().getLocalPart() == (TURNON)){
		       		      				  			newItem.Setturnon(newturnon);
		       		      				  			break;  
		       		      				  		}else continue;
		       		      				  	}
			       		  				}
			       		  			}
			       		  			
			       		  		}
			       		  		if (event.isEndElement()){
			       		  			EndElement endElement = event.asEndElement();
			       		  			if (endElement.getName().getLocalPart() == (ITEM)) {
			       		  				Items.put(newItem.Getname(),newItem);
			       		  				itemflag = 1;
			       		  				break;
			       		  			}
			       		  		}
			       		  	}
			        	}
			        	if(roomflag==1|itemflag==1)continue;
			        	if (event.asStartElement().getName().getLocalPart().equals(CONTAINER)) {
			        		Container newcontainer = new Container();
			        		 while (eventReader.hasNext()){
			        			 event = eventReader.nextEvent();
     				        	//Complete reading of a trigger
     				        	if (event.isStartElement()) {
     				        		if (event.asStartElement().getName().getLocalPart().equals(NAME)){
			       		  				event = eventReader.nextEvent();
			       		  				newcontainer.Setname(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(ACCEPT)){
			       		  				event = eventReader.nextEvent();
			       		  				newcontainer.Setaccept(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
			       		  				event = eventReader.nextEvent();
			       		  				newcontainer.Setstatus(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(ITEM)){
			       		  				event = eventReader.nextEvent();
			       		  				newcontainer.Setitem(event.asCharacters().getData());
			       		  				continue;
			       		  			}
			       		  			if (event.asStartElement().getName().getLocalPart().equals(TRIGGER)) {     
			        				        Trigger newTrigger = new Trigger();  		        				       
			        				        while(eventReader.hasNext()){
			        				        	event = eventReader.nextEvent();
			        				        	//Complete reading of a trigger
			        				        	if (event.isStartElement()) {
			        				        		if (event.asStartElement().getName().getLocalPart().equals(PRINT)){
			        				   	  				event = eventReader.nextEvent();
			        				   	  				newTrigger.Setprint(event.asCharacters().getData());
			        				   	  				continue;
			        				    			}
			        				    			if (event.asStartElement().getName().getLocalPart().equals(ACTION)){
			        				   	 				event = eventReader.nextEvent();
			        				   	 				newTrigger.Setaction(event.asCharacters().getData());
			        				   	 				continue;
			        				    			}
			        				    			if (event.asStartElement().getName().getLocalPart().equals(COMMAND)){
			        				   	 				event = eventReader.nextEvent();
			        				   	 				newTrigger.Setcommand(event.asCharacters().getData());
			        				   	 				continue;
			        				    			}
			        				    			if (event.asStartElement().getName().getLocalPart().equals(TYPE)){
			        				   	  				event = eventReader.nextEvent();
			        				   	  				newTrigger.Settype(event.asCharacters().getData());
			        				   	  				continue;
			        				    			}
			        				        		if (event.asStartElement().getName().getLocalPart().equals(CONDITION)){
			        				      			  	Map <String, String> newcondition = new HashMap<String,String>();
			        				      			  	while(eventReader.hasNext()){
			        				      				  	event = eventReader.nextEvent();
			        				      				  	if (event.isStartElement()) {
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(HAS)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  //	System.out.println(HAS+" "+event.asCharacters().getData());
			        				      						  	newcondition.put(HAS,event.asCharacters().getData());
			        				      						  	continue;
			        				      					  	}
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(OBJECT)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(OBJECT,event.asCharacters().getData());
			        				      						  	continue;
			        				      					  	}
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(OWNER)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(OWNER,event.asCharacters().getData());
			        				      						  	continue;
			        				      					  	}
			        				      					  	if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
			        				      						  	event = eventReader.nextEvent();
			        				      						  	newcondition.put(STATUS,event.asCharacters().getData());
			        				      						  	continue;
			        				      				  		}
			        				      				  	}
			        				      				  	if (event.isEndElement()){
			        				      				  		EndElement endElement = event.asEndElement();		        		      	
			        				      				  		if (endElement.getName().getLocalPart() == (CONDITION)){
			        				      				  			newTrigger.Setcondition(newcondition);
			        				      				  		//	System.out.println("Am I here ever? "+newcondition.get("has"));
			        				      				  		//	System.out.println(newcontainer.Getname());
			        				      				  			break;  
			        				      				  		}else continue;
			        				      				  	}
			        				      			    }
			        				      			    continue;
			        				      			}	  			        				
			        				    		}
			        				    		if (event.isEndElement()){
			        				    			EndElement endElement = event.asEndElement();		        		      	
    	    			 	     			  		if (endElement.getName().getLocalPart() == (TRIGGER)){
    	    			 	     			  			newcontainer.Settrigger(newTrigger); 
    	    			 	     			  			break;  
    	    			 	     			  		}
			        				    		}
			        				        }
			        				        continue;
			        			    }

     				        		
     				        	}
     				        	if(event.isEndElement()){
     				        		EndElement endElement = event.asEndElement();
     				        		if(endElement.getName().getLocalPart().equals(CONTAINER)){
     				        			Containers.put(newcontainer.Getname(), newcontainer);
     				        			containerflag = 1;
     				        			break;
     				        		}
     				        	}
			        		 }
						}
						if(roomflag==1|itemflag==1|containerflag ==1)continue;
						//System.out.println("ever here?");
			        	if (event.asStartElement().getName().getLocalPart().equals(CREATURE)) {
			        		Creature newcreature = new Creature();
			        		while (eventReader.hasNext()){
			        			 event = eventReader.nextEvent();
     				        	if (event.isStartElement()) {
     				        		if (event.asStartElement().getName().getLocalPart().equals(NAME)){
			       		  				event = eventReader.nextEvent();
			       		  				newcreature.Setname(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
			       		  				event = eventReader.nextEvent();
			       		  				newcreature.Setstatus(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(VULNERABILITY)){
			       		  				event = eventReader.nextEvent();
			       		  				newcreature.Setvun(event.asCharacters().getData());
			       		  				//System.out.println(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(DES)){
			       		  				event = eventReader.nextEvent();
			       		  				newcreature.Setdes(event.asCharacters().getData());
			       		  				continue;
			       		  			}
     				        		if (event.asStartElement().getName().getLocalPart().equals(TRIGGER)) {     
		        				        Trigger newTrigger = new Trigger();  		        				       
		        				        while(eventReader.hasNext()){
		        				        	event = eventReader.nextEvent();
		        				        	//Complete reading of a trigger
		        				        	if (event.isStartElement()) {
		        				        		if (event.asStartElement().getName().getLocalPart().equals(PRINT)){
		        				   	  				event = eventReader.nextEvent();
		        				   	  				newTrigger.Setprint(event.asCharacters().getData());
		        				   	  				continue;
		        				    			}
		        				        		if (event.asStartElement().getName().getLocalPart().equals(ACTION)){
		        				   	 				event = eventReader.nextEvent();
		        				   	 				newTrigger.Setaction(event.asCharacters().getData());
		        				   	 				continue;
		        				    			}
		        				    			if (event.asStartElement().getName().getLocalPart().equals(COMMAND)){
		        				   	 				event = eventReader.nextEvent();
		        				   	 				newTrigger.Setcommand(event.asCharacters().getData());
		        				   	 				continue;
		        				    			}
		        				    			if (event.asStartElement().getName().getLocalPart().equals(TYPE)){
		        				   	  				event = eventReader.nextEvent();
		        				   	  				newTrigger.Settype(event.asCharacters().getData());
		        				   	  				continue;
		        				    			}
		        				        		if (event.asStartElement().getName().getLocalPart().equals(CONDITION)){
		        				      			  	Map <String, String> newcondition = new HashMap<String,String>();
		        				      			  	while(eventReader.hasNext()){
		        				      				  	event = eventReader.nextEvent();
		        				      				  	if (event.isStartElement()) {
		        				      					  	if (event.asStartElement().getName().getLocalPart().equals(HAS)){
		        				      						  	event = eventReader.nextEvent();
		        				      						  	newcondition.put(HAS,event.asCharacters().getData());
		        				      						  	continue;
		        				      					  	}
		        				      					  	if (event.asStartElement().getName().getLocalPart().equals(OBJECT)){
		        				      						  	event = eventReader.nextEvent();
		        				      						  	newcondition.put(OBJECT,event.asCharacters().getData());
		        				      						  	continue;
		        				      					  	}
		        				      					  	if (event.asStartElement().getName().getLocalPart().equals(OWNER)){
		        				      						  	event = eventReader.nextEvent();
		        				      						  	newcondition.put(OWNER,event.asCharacters().getData());
		        				      						  	continue;
		        				      					  	}
		        				      					  	if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
		        				      						  	event = eventReader.nextEvent();
		        				      						  	newcondition.put(STATUS,event.asCharacters().getData());
		        				      						  	continue;
		        				      				  		}
		        				      				  	}
		        				      				  	if (event.isEndElement()){
		        				      				  		EndElement endElement = event.asEndElement();		        		      	
		        				      				  		if (endElement.getName().getLocalPart() == (CONDITION)){
		        				      				  			newTrigger.Setcondition(newcondition);
		        				      				  			break;  
		        				      				  		}else continue;
		        				      				  	}
		        				      			    }
		        				      			    continue;
		        				      			}	  			        				
		        				    		}
		        				    		if (event.isEndElement()){
		        				    			EndElement endElement = event.asEndElement();		        		      	
	    			 	     			  		if (endElement.getName().getLocalPart() == (TRIGGER)){
	    			 	     			  			newcreature.Settrigger(newTrigger); 
	    			 	     			  			break;  
	    			 	     			  		}
		        				    		}
		        				        }
		        				        continue;
     				        		}
     				        		if (event.asStartElement().getName().getLocalPart().equals(ATTACK)){
			       		  				Attack newattack = new Attack();
			       		  				while(eventReader.hasNext()){
			       		  					event = eventReader.nextEvent();
			       		  					if (event.isStartElement()) {
			       		  						if (event.asStartElement().getName().getLocalPart().equals(CONDITION)){
			       		  						//	System.out.println("yoyo");
    				      						  	Map <String, String> newcondition = new HashMap<String,String>();
    				      						  	while(eventReader.hasNext()){
    				      							  	event = eventReader.nextEvent();
    				      							  	if (event.isStartElement()) {
    				      								  	if (event.asStartElement().getName().getLocalPart().equals(HAS)){
    				      									  	event = eventReader.nextEvent();
    				      									  	newcondition.put(HAS,event.asCharacters().getData());
    				      									  	continue;
    				      								  	}
    				      								  	if (event.asStartElement().getName().getLocalPart().equals(OBJECT)){
    				      									  	event = eventReader.nextEvent();
    				      									  	newcondition.put(OBJECT,event.asCharacters().getData());
    				      									  	continue;
    				      								  	}
    				      								  	if (event.asStartElement().getName().getLocalPart().equals(OWNER)){
    				      									  	event = eventReader.nextEvent();
    				      									  	newcondition.put(OWNER,event.asCharacters().getData());
    				      									  	
    				      									  	continue;
    				      								  	}
    				      								  	if (event.asStartElement().getName().getLocalPart().equals(STATUS)){
    				      									  	event = eventReader.nextEvent();
    				      									  	newcondition.put(STATUS,event.asCharacters().getData());
    				      							//		  	System.out.println("hello");
    				      									  	continue;
    				      							  		}
    				      							  	}
    				      							  	if (event.isEndElement()){
    				      							  		EndElement endElement = event.asEndElement();		        		      	
    				      							  		if (endElement.getName().getLocalPart() == (CONDITION)){
    				      							  			newattack.SetCondition(newcondition);
    				      							  			break;  
    				      							  		}else continue;
    				      							  	}
			       		  							}
    				      						  	continue;
     				        					}
     				        					if (event.asStartElement().getName().getLocalPart().equals(PRINT)){
			       		  							event = eventReader.nextEvent();
			       		  							newattack.Setprint(event.asCharacters().getData());
			       		  							continue;
     				        					}
     				        					if (event.asStartElement().getName().getLocalPart().equals(ACTION)){
    	        				   	 				event = eventReader.nextEvent();
    	        				   	 				newattack.Setaction(event.asCharacters().getData());
    	        				   	 				continue;
    	        				    			}
     				        					
     				        				}
     				        				if(event.isEndElement()){
     				        					if(event.asEndElement().getName().getLocalPart().equals(ATTACK)){
     				        						newcreature.Setattack(newattack);
     				        						break;
     				        					}
     				        				}
     				        		}
			       		  			continue;
     				        	}
     				        	continue;

			        		}
     				        if(event.isEndElement()){
     				       		if(event.asEndElement().getName().getLocalPart().equals(CREATURE)){
     				       			Creatures.put(newcreature.Getname(), newcreature);			
     				       			break;
     				       		}
     				       	}
						}
		          	}
		        }
		    }
		      	world.SetItems(Items);
				world.SetRooms(Rooms);
				world.SetContainer(Containers);
				world.SetCreature(Creatures);
		}catch(FileNotFoundException e){
			System.out.println("file not found!");
		}catch(XMLStreamException e) {
			e.printStackTrace();
		};
		
		return world;
	}
}


