package ui.conf.model;

import java.util.ArrayList;
import java.util.Iterator;

import ui.conf.view.Observer;

public abstract class Model {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	public Model() {
		super();
	}

	public void registerObserver(Observer o)
	{
	   observers.add(o);
	}
	
   public void removeObserver(Observer o)
   {
	   observers.remove(o);
   }
   

   public void changeModel(Model model)
   {
       dataUpdate();
   }
   
   private void dataUpdate()
   {
	   Iterator<Observer> i = observers.iterator();
	   
	   while(i.hasNext()){
		   Observer o = (Observer)(i.next());
           o.dataUpdate(this);
	   }
	   
   }
	
	
}
