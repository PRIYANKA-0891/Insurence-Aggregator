package com.synechron.assignment.aggregator.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.assignment.aggregator.entity.PolicyProviderDetails;

@Repository
public interface AggregatorRepository extends JpaRepository<PolicyProviderDetails, Integer> {
	
	/* List<Policy> findbypolicyProviderName(String policyProviderName); */

}
