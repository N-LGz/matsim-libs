/* *********************************************************************** *
 * project: org.matsim.*
 * ParameterGetter.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

/**
 * 
 */
package playground.yu.parameterSearch;

import org.matsim.core.config.Config;

import playground.yu.integration.cadyts.CalibrationConfig;

/**
 * gets parameter value from {@code Config}
 * 
 * @author yu
 * 
 */
public class ParametersGetter {
	/**
	 * @param cfg
	 * @param name
	 * @return
	 */
	public static double getValueOfParameter(Config cfg, String name) {
		String value = cfg.planCalcScore().getParams().get(name);
		if (value == null) {
			value = cfg.findParam(CalibrationConfig.BSE_CONFIG_MODULE_NAME,
					name);
			if (value == null) {
				throw new RuntimeException("The parameter\t" + name
						+ "\tcan NOT be found");
			}
		}
		return Double.parseDouble(value);
	}
}
