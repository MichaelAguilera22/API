package gov.ca.cwds.rest.services.legacy;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.ca.cwds.rest.api.persistence.legacy.Reporter;
import gov.ca.cwds.rest.services.CrudsService;

public class ReporterServiceImpl implements ReporterService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReporterServiceImpl.class);

	private CrudsService<gov.ca.cwds.rest.api.domain.legacy.Reporter, Reporter> crudsService;

	/**
	 * Constructor
	 * 
	 * @param crudsService
	 *            The {@link CrudsService} used by this service
	 */
	public ReporterServiceImpl(CrudsService<gov.ca.cwds.rest.api.domain.legacy.Reporter, Reporter> crudsService) {
		this.crudsService = crudsService;
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.services.CrudsService#find(java.io.Serializable)
	 */
	@Override
	public gov.ca.cwds.rest.api.domain.legacy.Reporter find(Serializable primaryKey) {
		return (gov.ca.cwds.rest.api.domain.legacy.Reporter) crudsService.find(primaryKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.ca.cwds.rest.services.CrudsService#delete(java.io.Serializable)
	 */
	@Override
	public gov.ca.cwds.rest.api.domain.legacy.Reporter delete(Serializable id) {
		return (gov.ca.cwds.rest.api.domain.legacy.Reporter) crudsService.delete(id);
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.services.CrudsService#create(gov.ca.cwds.rest.api.domain.DomainObject)
	 */
	@Override
	public Serializable create(gov.ca.cwds.rest.api.domain.legacy.Reporter object) {
		return crudsService.create(object);
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.services.CrudsService#update(gov.ca.cwds.rest.api.domain.DomainObject)
	 */
	@Override
	public String update(gov.ca.cwds.rest.api.domain.legacy.Reporter object) {
		return crudsService.update(object);
	}
}