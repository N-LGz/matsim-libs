/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package org.matsim.core.mobsim.queuesim;

import org.matsim.api.core.v01.population.Person;
import org.matsim.core.api.internal.MatsimFactory;

/**
 * @author dgrether
 */
public class AgentFactory implements MatsimFactory {

	protected final QueueSimulation simulation;

	public AgentFactory(final QueueSimulation simulation) {
		this.simulation = simulation;
	}

	public QueuePersonAgent createPersonAgent(final Person p) {
		QueuePersonAgent agent = new QueuePersonAgent(p, this.simulation);
		return agent;
	}

}
