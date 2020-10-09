package com.bharath.clinicals.util;

import java.util.List;

import com.bharath.clinicals.model.ClinicalData;

public class BMICalculator {

	public static void calculateBMI(List<ClinicalData> clinicalData, ClinicalData eachEntry) {
		if (eachEntry.getComponentName().equals("hw")) {
			String[] heightAndWeight = eachEntry.getComponentValue().split("/");
			if (heightAndWeight != null && heightAndWeight.length > 1) {
				float heightInMeters = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
				float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMeters * heightInMeters);
				ClinicalData bmiData = new ClinicalData();
				bmiData.setComponentName("bmi");
				bmiData.setComponentValue(Float.toString(bmi));
				clinicalData.add(bmiData);
			}
		}
	}
}
