package com.epam.movies.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateConverter {
	private final static String dateFormat = "mm/dd/yyyy";
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);;

	public static LocalDate convert(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}

		return LocalDate.parse(source, formatter);
	}
}