/* *********************************************************************** *
 * project: org.matsim.*
 * Entry.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
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

package org.matsim.matrices;

import org.matsim.core.gbl.Gbl;
import org.matsim.world.Location;

public class Entry {

	//////////////////////////////////////////////////////////////////////
	// member variables
	//////////////////////////////////////////////////////////////////////

	private final Location f_loc;
	private final Location t_loc;
	private double value;

	//////////////////////////////////////////////////////////////////////
	// Constructors
	//////////////////////////////////////////////////////////////////////

	protected Entry(final Location f_loc, final Location t_loc, final double value) {
		if ((f_loc == null)||(t_loc == null)) {
			Gbl.errorMsg("[f_loc="+f_loc+",t_loc="+t_loc+", 'null' is not allowed!]");
		}
		if (!f_loc.getLayer().equals(t_loc.getLayer())) {
			Gbl.errorMsg("[f_loc_id="+f_loc.getId()+",f_layer_type="+f_loc.getLayer().getType()+
									 "t_loc_id="+t_loc.getId()+",t_layer_type="+t_loc.getLayer().getType()+", not the same layer!]");
		}
		this.f_loc = f_loc;
		this.t_loc = t_loc;
		this.value = value;
	}

	//////////////////////////////////////////////////////////////////////
	// create methods
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// set/add methods
	//////////////////////////////////////////////////////////////////////
	public final void setValue(final double value) {
		this.value = value;
	}

	//////////////////////////////////////////////////////////////////////
	// get methods
	//////////////////////////////////////////////////////////////////////

	public final Location getFromLocation() {
		return this.f_loc;
	}

	public final Location getToLocation() {
		return this.t_loc;
	}

	public final double getValue() {
		return this.value;
	}

	//////////////////////////////////////////////////////////////////////
	// print methods
	//////////////////////////////////////////////////////////////////////

	@Override
	public final String toString() {
		return "[" + this.f_loc.getId() + "===" + this.value + "==>" + this.t_loc.getId() + "]";
	}
}
