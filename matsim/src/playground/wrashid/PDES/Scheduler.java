package playground.wrashid.PDES;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.matsim.gbl.Gbl;

public class Scheduler {
	private double simTime=0;
	private MessageQueue queue=new MessageQueue();
	HashMap<Long,SimUnit> simUnits=new HashMap<Long, SimUnit>();


	
	
	public void schedule(Message m){		
		if (m.getMessageArrivalTime()>=simTime){	
			queue.putMessage(m);
		} else {
			System.out.println("WARNING: You tried to send a message in the past. Message discarded.");
			//System.out.println("m.getMessageArrivalTime():"+m.getMessageArrivalTime());
			//System.out.println("simTime:"+simTime);
			//System.out.println(m.getClass());
			assert(false); // for backtracing, where a wrong message has been scheduled
		}
	}
	
	public void unschedule(Message m){
		queue.removeMessage(m);
	}
	
	
	public void startSimulation(){
		
		long simulationStart=System.currentTimeMillis();
		double hourlyLogTime=3600;
		
		initializeSimulation();
		
		Message m;
		
		while(queue.hasElement() && simTime<SimulationParameters.maxSimulationLength){
			m=queue.getNextMessage();
			
			Executor executor = Executors.newFixedThreadPool(2);
			executor.execute (new MessageExecutor (m));
			
			/*
			simTime=m.getMessageArrivalTime();
			m.printMessageLogString();
			if (m instanceof SelfhandleMessage){
				((SelfhandleMessage) m).selfhandleMessage();
			} else {
				m.receivingUnit.handleMessage(m);
			}
			*/
			
			// print output each hour
			if (simTime / hourlyLogTime > 1){
				hourlyLogTime = simTime + 3600;
				System.out.print("Simulation at " + simTime/3600 + "[h]; ");
				System.out.println("s/r:"+simTime/(System.currentTimeMillis()-simulationStart)*1000);
				Gbl.printMemoryUsage();
			}
			
			
			
			// debug - don't needed anymore remove after some time...
			/*
			if ((queue.counter % 100000 == 0)){
				System.out.println("s/r:"+simTime/(System.currentTimeMillis()-simulationStart)*1000);
				Gbl.printMemoryUsage();
			}
			*/
			
			
		}
	}
	
	
	public void register(SimUnit su){
		simUnits.put(new Long(su.unitNo), su);
	}
	
	public Object getSimUnit(long unitId){
		return simUnits.get(new Long(unitId));
	}
	
	
	// attention: this procedure only invokes
	// the initialization method of objects, which
	// exist at the beginning of the simulation
	public void initializeSimulation(){
		Object[] objects=simUnits.values().toArray();
		SimUnit su;
		
		for (int i=0;i<objects.length;i++){
			su=(SimUnit) objects[i];
			su.initialize();
		}
	}


	//public double getSimTime() {
	//	return simTime;
	//}


	public void unregister(SimUnit unit) {
		simUnits.remove(new Long(unit.unitNo));
	}
	
	
}
