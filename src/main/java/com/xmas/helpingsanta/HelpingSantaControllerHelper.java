/**
 * 
 */
package com.xmas.helpingsanta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author muthu
 * 
 * Helper class for HelpingSantaController class.
 *
 */
@Component
public class HelpingSantaControllerHelper {

	/**
	 *  Method to compute the Minimum hoods from the given  capacity and the list of weights.
	 *  Logic:
	 *     
	 *     1. Filter the weights which is less than given weight.
	 *     2. Use recursive method to compute all possible combinations.
	 */
	List<Integer> getMinimumHoods(final int capacity, final List<Integer> presentWeights) {
		
		final List<Integer> minHoods = new ArrayList<Integer>();
		
		if(capacity < 0) {
			return minHoods;
		}
		
		// This will hold all the possible combination of Hoods.
		List<List<Integer>> resultsList = new ArrayList<List<Integer>>();
		
		// This list will contains weights less than the Actual Capacity.
		final List<Integer> possibleWights = new ArrayList<Integer>();
		
		presentWeights.stream().filter(wieght -> wieght<capacity).forEach(wieght -> possibleWights.add(wieght));
		
		Collections.reverse(possibleWights);
		
		computeAllPossibleCombinations(resultsList, possibleWights, capacity, 0, minHoods);
		
		return getMinimumHoodsFromPossibleCombinations(resultsList);
	}
	
    /**	
     * Method to compute Minimum hoods from possible values.
     */
	private List<Integer> getMinimumHoodsFromPossibleCombinations(final List<List<Integer>> resultsList) {
		
		final Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
		if(resultsList == null || resultsList.isEmpty()) {
			return null;
		}
		
		resultsList.forEach(results -> {
			map.put(results.size(), results);
		});
		
		final int key = Collections.min(map.keySet());
	
		return map.get(key);
	}
	
	/**
	 * Method to compute all possible combinations of hoods against capacity.
	 *  Logic :
	 *     1. If capacity reaches 0, then add the computed liest into map.
	 *     2. Iterate possible weights (weights under capacity). If capacity index is less than capacity, reduce that index weight from
	 *        the capacity and call the method recursively.
	 */
	private void computeAllPossibleCombinations(final List<List<Integer>> resultsList, 
			final List<Integer> possibleWights, final int capacity, final int weightIndex, final List<Integer> minHoods) {
		
		if(capacity == 0) {
			resultsList.add(new ArrayList<Integer>(minHoods));
		}
		
		for(int i=weightIndex; i < possibleWights.size(); i++) {
			
			if(capacity - possibleWights.get(i) >= 0) {
				minHoods.add(possibleWights.get(i));
				computeAllPossibleCombinations(resultsList, possibleWights, capacity - possibleWights.get(i), i, minHoods);
				minHoods.remove(possibleWights.get(i));
			}
		}
		
	}

	/**
	 *  method to convert string value into int
	 */
	private int getNumber(final String str) {
		
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			
		}
		return -1;
	}
	
	public List<Integer> getMinimumHoods(final String hood_capacity, final String[] present_weights) {
		final List<Integer> weights = new ArrayList<Integer>();
		int weight = 0;
	
		if(present_weights.length == 0) {
			return weights;
		}
		
		for(int i=0;i<present_weights.length;i++) {
			
			weight = getNumber(present_weights[i]);
		
			if(weight != -1) {
				weights.add(weight);
			}
		}
		
		return getMinimumHoods(getNumber(hood_capacity), weights);
	}
}
