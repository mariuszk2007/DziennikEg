package com.eurogeo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class SerializedLocalDate extends JsonSerializer<LocalDate> {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		String text = date.format(formatter);
		String parsedDate = LocalDate.parse(text, formatter).toString();
		gen.writeString(parsedDate);
	}
}
