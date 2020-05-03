package com.synechron.assignment.aggregator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.assignment.aggregator.entity.PolicyProviderDetails;
import com.synechron.assignment.aggregator.repos.AggregatorRepository;

@Service
public class AggregatorService {

	@Autowired
	private AggregatorRepository repo;
	
	
	public PolicyProviderDetails add(PolicyProviderDetails entity) {
		return this.repo.save(entity);

	}
	
	public List<PolicyProviderDetails> getAll(){
		return this.repo.findAll();
	}
	
	
	public Optional<PolicyProviderDetails> getById(Integer id) {
		return this.repo.findById(id);
		
	}
	
	/*
	 * public PolicyProviderDetails findByPolicyProviderName(String
	 * policyProviderName) { return (PolicyProviderDetails)
	 * this.repo.findbypolicyProviderName(policyProviderName);
	 * 
	 * }
	 */
}
