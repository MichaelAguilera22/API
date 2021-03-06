package gov.ca.cwds.rest.resources.legacy;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.ca.cwds.rest.api.domain.legacy.Referral;
import gov.ca.cwds.rest.core.Api;
import gov.ca.cwds.rest.resources.CrudsResource;
import gov.ca.cwds.rest.resources.legacy.ReferralResourceImpl;
import gov.ca.cwds.rest.services.legacy.ReferralService;
import gov.ca.cwds.rest.services.legacy.ReferralServiceImpl;
import gov.ca.cwds.rest.setup.ServiceEnvironment;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;


public class ReferralResourceImplTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	private static final String ID_FOUND = "1";
	private static final String ID_VERIFY = "1";
	
	private static final String ROOT_RESOURCE = "/" + Api.RESOURCE_REFERRAL + "/";
	
	private static final String FOUND_RESOURCE = ROOT_RESOURCE + ID_FOUND ;
	private static final String VERIFY_RESOURCE = ROOT_RESOURCE + ID_VERIFY ;
	
	private static final ReferralServiceImpl referralService = mock(ReferralServiceImpl.class);
	private static final ServiceEnvironment serviceEnvironment = mock(ServiceEnvironment.class);
	@SuppressWarnings("unchecked")
	private static final CrudsResource<Referral> crudsResource = mock(CrudsResource.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new ReferralResourceImpl(serviceEnvironment, crudsResource)).build();

	@Before
	public void setup(){
		when(serviceEnvironment.getService(ReferralService.class, Api.Version.JSON_VERSION_1.getMediaType())).thenReturn(referralService);
	}

	/*
	 *  delegation check tests
	 */
	@Test
	public void getDelegatestoCrudsResource() throws Exception {
		Referral toVerify =  MAPPER.readValue(fixture("fixtures/legacy/Referral/valid/validNonUnique.json"), Referral.class);
		resources.client().target(VERIFY_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).get();
		verify(crudsResource, times(1)).get(toVerify.getId(), Api.MEDIA_TYPE_JSON_V1);

	}

	@Test
	public void deleteDelegatestoCrudsResource() {
		resources.client().target(FOUND_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).delete();
		verify(crudsResource, times(1)).delete(any(String.class), any(String.class));
	}

	@Test
	public void createDelegatestoCrudsResource() throws Exception {
		Referral toCreate = MAPPER.readValue(fixture("fixtures/legacy/Referral/valid/valid.json"), Referral.class);
		resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
		verify(crudsResource, times(1)).create(any(Referral.class), any(String.class), any(UriInfo.class) );
	}

	
	@Test
	public void updateDelegatestoCrudsResource() throws Exception {
		Referral toCreate = MAPPER.readValue(fixture("fixtures/legacy/Referral/valid/valid.json"), Referral.class);
		resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).put(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
		verify(crudsResource, times(1)).update(any(Referral.class), any(String.class));
	}
	
	@Test
	public void createValidatesReferral() throws Exception {
		Referral toCreate = MAPPER.readValue(fixture("fixtures/legacy/Referral/invalid/id/missing.json"), Referral.class);
		assertThat(resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1)).getStatus(), is(equalTo(422)));
	}

	
	@Test
	public void updateValidatesReferral() throws Exception {
		Referral toCreate = MAPPER.readValue(fixture("fixtures/legacy/Referral/invalid/id/missing.json"), Referral.class);
		assertThat(resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).put(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1)).getStatus(), is(equalTo(422)));
	}
}	
