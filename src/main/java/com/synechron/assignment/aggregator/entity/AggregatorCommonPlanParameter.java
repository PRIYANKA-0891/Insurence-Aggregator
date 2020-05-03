package com.synechron.assignment.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregatorCommonPlanParameter{

	@JsonProperty("policyId")
	@JsonAlias("id")
	private String policyId;
	
	@JsonProperty("policyName")
	@JsonAlias("policyName")
	private String policyName;
	
	@JsonProperty("policyProviderName")
	@JsonAlias("insuranceProviderName")
	private String policyProviderName;
	
	@JsonProperty("planCoverage")
	@JsonAlias("coverage")
	private String planCoverage;
	
	 @JsonIgnore
	 private JsonNode bodyAsNode;

}
