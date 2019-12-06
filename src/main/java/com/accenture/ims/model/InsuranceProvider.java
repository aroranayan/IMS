package com.accenture.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Entity class to save Insurance Providers
 * @author nayan.arora
 *
 */
@Entity
public class InsuranceProvider {
	
	@Id
	private String motorInsuranceProvider;
	@Column
	private String personalProtectPlanOffered;
	@Column
	private Integer firstYearPremium;
	
	public InsuranceProvider() {}
	
	public InsuranceProvider(String motorInsuranceProvider, String personalProtectPlanOffered,
			Integer firstYearPremium) {
		super();
		this.motorInsuranceProvider = motorInsuranceProvider;
		this.personalProtectPlanOffered = personalProtectPlanOffered;
		this.firstYearPremium = firstYearPremium;
	}

	public String getMotorInsuranceProvider() {
		return motorInsuranceProvider;
	}

	public void setMotorInsuranceProvider(String motorInsuranceProvider) {
		this.motorInsuranceProvider = motorInsuranceProvider;
	}

	public String getPersonalProtectPlanOffered() {
		return personalProtectPlanOffered;
	}

	public void setPersonalProtectPlanOffered(String personalProtectPlanOffered) {
		this.personalProtectPlanOffered = personalProtectPlanOffered;
	}

	public Integer getFirstYearPremium() {
		return firstYearPremium;
	}

	public void setFirstYearPremium(Integer firstYearPremium) {
		this.firstYearPremium = firstYearPremium;
	}
	
	
	
}
