package playground.balac.avignon;

import java.util.TreeMap;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.contrib.locationchoice.bestresponse.DestinationChoiceBestResponseContext;
import org.matsim.contrib.locationchoice.bestresponse.scoring.DCActivityScoringFunction;
import org.matsim.contrib.locationchoice.facilityload.FacilityPenalty;

/**
 * @author balacm
 */
public class AvignonScoringFunction extends DCActivityScoringFunction {

	//X and Y coordinates of the Bellevue
	double centerX = 683217.0; 
	double centerY = 247300.0;		
		
		
	public AvignonScoringFunction(Plan plan, TreeMap<Id, FacilityPenalty> facilityPenalties,
			DestinationChoiceBestResponseContext dcContext) {
			
			super(plan, facilityPenalties, dcContext);		
		
		}
	
	@Override
	public void finish() {				
		super.finish();	
		
		
	}

}
