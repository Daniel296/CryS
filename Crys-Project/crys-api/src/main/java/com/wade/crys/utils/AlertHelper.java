package com.wade.crys.utils;

import com.wade.crys.alert.model.Alert;

public class AlertHelper {

	public static int getOperator(Double alertValue, Double coinPrice) {

		if(alertValue > coinPrice) {

			return 1;
		} else if(alertValue < coinPrice) {

			return -1;
		}

		return 0;
	}

	public static boolean alertShouldBeTriggered(Alert alert) {

		return ((alert.getOperator() == -1 && alert.getValue() >= alert.getCoin().getPriceUsd()) || //in case the price is lower than alert value
				(alert.getOperator() == 1 && alert.getValue() <= alert.getCoin().getPriceUsd()));//in case the price is bigger than alert value
	}
}
