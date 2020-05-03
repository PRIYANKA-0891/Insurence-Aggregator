package com.synechron.assignment.aggregator.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synechron.assignment.aggregator.entity.AggregatorCommonPlanParameter;
import com.synechron.assignment.aggregator.entity.PolicyProviderDetails;
import com.synechron.assignment.aggregator.service.AggregatorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/aggregator")
public class AggregatorController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AggregatorService service;

	private static RestTemplate template;

	private static final Logger log = Logger.getLogger(AggregatorController.class.getName());

	@GetMapping("/getAllInsurenceProviderListWithPlans")
	public List<AggregatorCommonPlanParameter> getAllInsurenceProviderListWithPlans() throws Exception {

		log.info("Inside getAllInsurenceProviderListWithPlans method ::: ");

		HttpHeaders headers = new HttpHeaders();
		template = new RestTemplate();

		String providerUrl = "";

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);

		List<Object> finalResponse = new ArrayList<>();
		List<AggregatorCommonPlanParameter> responseList = new ArrayList<AggregatorCommonPlanParameter>();
		// List<AggregatorCommonPlanParameter> commonParams=new
		// ArrayList<AggregatorCommonPlanParameter>();
		AggregatorCommonPlanParameter common = null;

		for (PolicyProviderDetails list : service.getAll()) {

			providerUrl = list.getPolicyProviderURL();
			log.info("providerUrl :: " + providerUrl);

			/*
			 * String responseOutputFormat = list.getResponseFormat();
			 * 
			 * System.out.println(" responseOutputFormat ::::::::::: " +
			 * responseOutputFormat.toString());
			 */
			ObjectMapper mapper = new ObjectMapper();

			String outputJson = "";
			log.info("ENTIYYYY ::::::::: " + entity.toString());

			ResponseEntity<Object> respEntity = template.exchange(providerUrl, HttpMethod.GET, entity, Object.class);

			if (respEntity != null) {
				log.info("respEntity.getBody() :: " + respEntity.getBody());
				finalResponse = (List<Object>) respEntity.getBody();

				for (Object object : finalResponse) {

					try {
						outputJson = mapper.writeValueAsString(object);
						log.info("outputJson :: " + outputJson);
						common = mapper.readValue(outputJson, AggregatorCommonPlanParameter.class);

						log.info("Policy Id is :: " + common.getPolicyId());
						log.info("common data from mapper.read :: " + common);
						if (common != null) {
							responseList.add(common);
						}

					} catch (Exception e) {

					}

				}

			}

		}
		return responseList;

	}

	@GetMapping("/getAllURL")
	public List<PolicyProviderDetails> getAllURL() {
		return this.service.getAll();
	}

	@PostMapping("/getAllURL")
	public PolicyProviderDetails register(@RequestBody PolicyProviderDetails entity) {
		return this.service.add(entity);

	}

	/*
	 * public List<Policy> getAllPlans(@PathVariable String policyProviderName
	 * , @PathVariable String gender , @PathVariable int age){
	 * 
	 * PolicyProviderDetails
	 * providerDetails=service.findByPolicyProviderName(policyProviderName);
	 * 
	 * if(providerDetails==null) { throw new
	 * RuntimeException("No policy provider is registered "); }
	 * 
	 * String url=providerDetails.getURL(); Log.info("url is :: "+ url);
	 * 
	 * Map<String, Object> uriVariables=new HashMap<>(); uriVariables.put("gender",
	 * gender); uriVariables.put("age", age);
	 * 
	 * ResponseEntity<Policy[]> response=template.getForEntity(url,Policy[].class,
	 * uriVariables); List<Policy> finalResponse=Arrays.asList(response.getBody());
	 * return finalResponse;
	 * 
	 * }
	 */

}
