/*
 * *********************************************************************** *
 * project: org.matsim.*
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2019 by the members listed in the COPYING,        *
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
 * *********************************************************************** *
 */

package org.matsim.contrib.etaxi.run;

import java.net.URL;

import org.junit.Test;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.examples.ExamplesUtils;

/**
 * @author Michal Maciejewski (michalm)
 */
public class RunETaxiBenchmarkTest {
	@Test
	public void testRuleBased() {
		URL configUrl = IOUtils.extendUrl(ExamplesUtils.getTestScenarioURL("dvrp-grid"), "one_etaxi_benchmark_config.xml");
		RunETaxiBenchmark.run(configUrl, 2);
	}
}
