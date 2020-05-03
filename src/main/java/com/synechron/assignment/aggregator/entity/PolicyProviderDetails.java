package com.synechron.assignment.aggregator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Insurance_Provider_Details")
public class PolicyProviderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String policyProviderName;
	private String policyProviderURL;
	private String responseType;
	private String responseFormat;
	private String benefits;
}
