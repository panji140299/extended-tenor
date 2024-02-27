package com.panpraz.informasisertifikasidanedukasicp.request.scheme;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor
public class EmailParamScheme {
	private String from;
	private String to;
	private String cc;
	private String subject;
	private String body;

}