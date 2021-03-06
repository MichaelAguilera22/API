package gov.ca.cwds.rest.resources.legacy;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.ca.cwds.rest.api.domain.legacy.ReferralClient;
import gov.ca.cwds.rest.resources.BaseResource;
import gov.ca.cwds.rest.resources.CrudsResource;
import gov.ca.cwds.rest.services.legacy.ReferralClientService;
import gov.ca.cwds.rest.setup.ServiceEnvironment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ResponseHeader;

/**
 * Implementation of {@link ReferralClientResource} delegating work to {@link ReferralClientService}
 * 
 * @author CWDS API Team
 */
public class ReferralClientResourceImpl extends BaseResource<ReferralClientService>
		implements ReferralClientResource, CrudsResource<ReferralClient> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferralClientResourceImpl.class);
	
	private CrudsResource<ReferralClient> crudsResource;
	
	public ReferralClientResourceImpl(ServiceEnvironment serviceEnvironment, CrudsResource<ReferralClient> crudsResource) {
		super(serviceEnvironment, ReferralClientService.class);
		this.crudsResource = crudsResource;
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.resources.CrudsResource#get(java.lang.String, java.lang.String)
	 */
	@Override
	@ApiOperation(value = "Find ReferralClient by composite id of referralId and clientId", response = ReferralClient.class)
	public Response get(@ApiParam(required = true, allowMultiple=true, value = "ReferralClient has a composite key of referralId and clientId", example="referralId=abcdefgh,clientId=td89slaz") String id, String acceptHeader) {
		return crudsResource.get(id, acceptHeader);
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.resources.CrudsResource#delete(java.lang.String, java.lang.String)
	 */
	@ApiOperation(value = "Delete ReferralClient by composite id of referralId and clientId", code = HttpStatus.SC_OK)
	@Override
	public Response delete(@ApiParam(required = true, allowMultiple=true, value = "ReferralClient has a composite key of referralId and clientId", example="referralId=abcdefgh,clientId=td89slaz") String id, String acceptHeader) {
		return crudsResource.delete(id, acceptHeader);
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.resources.CrudsResource#update(gov.ca.cwds.rest.api.domain.DomainObject, java.lang.String)
	 */
	@Override
	@ApiOperation(value = "Update ReferralClient", code = HttpStatus.SC_NO_CONTENT, response = Object.class)
	public Response update(@Valid ReferralClient object, String acceptHeader) {
		return crudsResource.update(object, acceptHeader);
	}

	/* (non-Javadoc)
	 * @see gov.ca.cwds.rest.resources.CrudsResource#create(gov.ca.cwds.rest.api.domain.DomainObject, java.lang.String, javax.ws.rs.core.UriInfo)
	 */
	@Override
	@ApiOperation(value = "Create ReferralClient", response = Object.class, code = HttpStatus.SC_CREATED, responseHeaders = @ResponseHeader(name = "Location", description = "Link to the newly created object", response = Object.class))
	public Response create(@Valid ReferralClient object, String acceptHeader, UriInfo uriInfo) {
		return crudsResource.create(object, acceptHeader, uriInfo);
	}
}
