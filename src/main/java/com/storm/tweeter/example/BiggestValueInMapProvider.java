package com.storm.tweeter.example;

import java.util.Collections;
import java.util.LinkedHashMap;
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

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
