package com.storm.tweeter.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BiggestValueInMapProvider {
	public static Integer getBiggestIntegerValueFromGivenHashmap(Map<String, Integer> counts) {
		Map<String, Integer> sortedMap = sortByValue(counts);
		Set<String> keysetFromSortedMap = sortedMap.keySet();
		Optional<String> firstKey = keysetFromSortedMap.stream().findFirst();
		String firstKeyString = firstKey.get();
		Integer biggestIntegerValue = sortedMap.get(firstKeyString);
		return biggestIntegerValue;
	}

	public static String[] getGivenAmountOfBiggestValuesWithKeysAsStringFromGivenHashmap(Map<String, Integer> counts,
			int numberOfBiggestValues) {
		String[] arrayToReturn = new String[numberOfBiggestValues];
		List<String> givenAmountOfValuesWithKeys = new ArrayList<String>();
		Map<String, Integer> sortedMap = sortByValue(counts);
		Set<String> keysetFromSortedMap = sortedMap.keySet();
		Iterator<String> keysetIterator = keysetFromSortedMap.stream().iterator();

		while (keysetIterator.hasNext()) {
			if (numberOfBiggestValues > 0) {
				String currentKey = keysetIterator.next();
				String currentValueWithKey = "word " + currentKey + " occurs : " + sortedMap.get(currentKey) + " times";
				givenAmountOfValuesWithKeys.add(currentValueWithKey);
				numberOfBiggestValues -= 1;
			} else {
				break;
			}
		}
		for (int i = 0; i < givenAmountOfValuesWithKeys.size(); i++) {
			arrayToReturn[i] = givenAmountOfValuesWithKeys.get(i);
		}
		return arrayToReturn;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
