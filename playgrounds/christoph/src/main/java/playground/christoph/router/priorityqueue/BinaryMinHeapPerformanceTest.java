/* *********************************************************************** *
 * project: org.matsim.*
 * BinaryMinHeapTest.java
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

package playground.christoph.router.priorityqueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.matsim.core.gbl.MatsimRandom;

/**
 * @author muelleki
 */
public class BinaryMinHeapPerformanceTest extends BinaryMinHeapTest {
	static final int ITERS = 500000;
	static final int OUTDEGREE = 3;
	static final int DECREASE = 1;
	static final int MAXENTRIES = ITERS * OUTDEGREE;

	public long doTestDijkstraPerformance(BinaryMinHeap<DummyHeapEntry> pq,
			Iterator<DummyHeapEntry> it) {
		Random R = MatsimRandom.getLocalInstance();

		System.gc();

		double cc = 0.0;
		pq.add(it.next(), cc);

		long t = System.nanoTime();
		for (int i = 1;; i++) {
			double c = pq.peekCost();
			assertTrue("Nondecreasing order for costs", c >= cc);
			cc = c;
			pq.remove();

			if (i < ITERS) {
				for (int j = 0; j < OUTDEGREE; j++) {
					pq.add(it.next(), c + R.nextDouble());
				}
			} else if (pq.isEmpty())
				break;

			for (int j = 0; j < DECREASE; j++) {
				final int index = R.nextInt(pq.size());
				DummyHeapEntry e = pq.peek(index);
				double co = pq.peekCost(index);
				double cn = cc + (co - cc) * R.nextDouble();
				pq.decreaseKey(e, cn);
			}
		}

		long tt = System.nanoTime();
		return tt - t;
	}

	public void testDijkstraPerformance() {
		int RUNS = 50;
		DescriptiveStatistics S = new DescriptiveStatistics();

		final BinaryMinHeap<DummyHeapEntry> heap = new BinaryMinHeap<DummyHeapEntry>(MAXENTRIES);

		final DummyHeapEntry[] E = new DummyHeapEntry[MAXENTRIES];
		for (int gen = 0; gen < MAXENTRIES; gen++)
			E[gen] = new DummyHeapEntry(gen);

		for (int i = 0; i < RUNS; i++) {
			@SuppressWarnings("unchecked")
			double dt = (doTestDijkstraPerformance(heap,
					((Iterator<DummyHeapEntry>)new ArrayIterator(E))) / 1.0e6);
			S.addValue(dt);
			log.info(String.format("Iteration: %d, Time: %f", i, dt));
		}

		log.info(String.format(
				"Time: Min/Max: %f/%f, Mean: %f, StDev: %f, 95%% CI: (%f, %f)",
				S.getMin(), S.getMax(), S.getMean(), S.getStandardDeviation(),
				(S.getMean() - 1.96 * S.getStandardDeviation()),
				(S.getMean() + 1.96 * S.getStandardDeviation())));
		log.info(Arrays.toString(S.getSortedValues()));
	}

	public static void main(String args[]) {
		new BinaryMinHeapPerformanceTest().testDijkstraPerformance();
	}
}
