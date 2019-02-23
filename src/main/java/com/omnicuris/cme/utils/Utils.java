package com.omnicuris.cme.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

	public static final String SAVE_MSG = "Save Success";
	public static final String UPDATE_MSG = "Update Success";
	public static final String DELETE_MSG = "Delete Success";
	public static final String ERROR_MSG = "Error while saving Data";

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
