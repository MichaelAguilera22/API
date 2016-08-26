package gov.ca.cwds.rest.api.domain;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import gov.ca.cwds.rest.core.Api;
import gov.ca.cwds.rest.resources.ReporterResourceImpl;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReporterTest {

	private static final String ROOT_RESOURCE = "/reporters/";
	
	private static final ReporterResourceImpl mockedReporterResource = mock(ReporterResourceImpl.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(mockedReporterResource).build();

	
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private Reporter validReporter = validReporter();

	@Before
	public void setup() {
		when(mockedReporterResource.create(eq(validReporter), eq(Api.Version.JSON_VERSION_1.getMediaType()), any(UriInfo.class))).thenReturn(Response.status(Response.Status.NO_CONTENT).entity(null).build());
	}
	
    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/legacy/Reporter/valid/valid.json"), Reporter.class));

        assertThat(MAPPER.writeValueAsString(validReporter()), is(equalTo(expected)));
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("fixtures/legacy/Reporter/valid/valid.json"), Reporter.class), is(equalTo(validReporter())));
    }
    
	/*
	 * Successful Tests
	 */
	@Test
	public void successfulWithValid() throws Exception {
		Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/valid/valid.json"), Reporter.class);
		assertThat(resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1)).getStatus(), is(equalTo(204)));
	}

	@Test
	public void successfulWithOptionalsNotIncluded() throws Exception {
		Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/valid/optionalsNotIncluded.json"), Reporter.class);
		assertThat(resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1)).getStatus(), is(equalTo(204)));
	}

    /*
    * id Tests
    */
    @Test
    public void failsWhenIdMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/id/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("id may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenIdNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/id/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("id may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenIdEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/id/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("id may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenIdTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/id/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("id size must be between 1 and 10"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * badgeNumber Tests
    */
    @Test
    public void failsWhenBadgeNumberMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/badgeNumber/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("badgeNumber may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenBadgeNumberNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/badgeNumber/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("badgeNumber may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenBadgeNumberEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/badgeNumber/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("badgeNumber may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenBadgeNumberTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/badgeNumber/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("badgeNumber size must be between 1 and 6"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * cityName Tests
    */
    @Test
    public void failsWhenCityNameMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/cityName/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("cityName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCityNameNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/cityName/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("cityName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCityNameEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/cityName/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("cityName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCityNameTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/cityName/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("cityName size must be between 1 and 20"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * colltrClientRptrReltnshpType Tests
    */
    @Test
    public void failsWhenColltrClientRptrReltnshpTypeMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/colltrClientRptrReltnshpType/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("colltrClientRptrReltnshpType may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenColltrClientRptrReltnshpTypeNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/colltrClientRptrReltnshpType/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("colltrClientRptrReltnshpType may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * communicationMethodType Tests
    */
    @Test
    public void failsWhenCommunicationMethodTypeMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/communicationMethodType/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("communicationMethodType may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCommunicationMethodTypeNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/communicationMethodType/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("communicationMethodType may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * confidentialWaiverIndicator Tests
    */
    @Test
    public void failsWhenConfidentialWaiverIndicatorMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/confidentialWaiverIndicator/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("confidentialWaiverIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenConfidentialWaiverIndicatorNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/confidentialWaiverIndicator/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("confidentialWaiverIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenConfidentialWaiverIndicatorEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/confidentialWaiverIndicator/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("confidentialWaiverIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenConfidentialWaiverIndicatorAllWhitespace() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/confidentialWaiverIndicator/allWhitespace.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("confidentialWaiverIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * drmsMandatedRprtrFeedback Tests
    */
    @Test
    public void successWhenDrmsMandatedRprtrFeedbackEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/drmsMandatedRprtrFeedback/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(204)));
    }
    @Test
    public void successWhenDrmsMandatedRprtrFeedbackNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/drmsMandatedRprtrFeedback/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(204)));
    }
    @Test
    public void failsWhenDrmsMandatedRprtrFeedbackTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/drmsMandatedRprtrFeedback/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("drmsMandatedRprtrFeedback size must be between 0 and 10"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * employerName Tests
    */
    @Test
    public void failsWhenEmployerNameMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/employerName/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("employerName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenEmployerNameNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/employerName/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("employerName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenEmployerNameEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/employerName/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("employerName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenEmployerNameTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/employerName/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("employerName size must be between 1 and 35"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * feedbackDate Tests
    */
    @Test
    public void successWhenFeedbackDateEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackDate/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(204)));
    }
    @Test
    public void successWhenFeedbackDateNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackDate/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(204)));
    }
    @Test
    public void failsWhenFeedbackDateWrongFormat() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackDate/wrongFormat.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("feedbackDate must be in the format of yyyy-MM-dd"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * feedbackRequiredIndicator Tests
    */
    @Test
    public void failsWhenFeedbackRequiredIndicatorMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackRequiredIndicator/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("feedbackRequiredIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenFeedbackRequiredIndicatorNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackRequiredIndicator/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("feedbackRequiredIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenFeedbackRequiredIndicatorEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackRequiredIndicator/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("feedbackRequiredIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenFeedbackRequiredIndicatorAllWhitespace() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/feedbackRequiredIndicator/allWhitespace.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("feedbackRequiredIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * firstName Tests
    */
    @Test
    public void failsWhenFirstNameMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/firstName/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("firstName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenFirstNameNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/firstName/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("firstName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenFirstNameEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/firstName/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("firstName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenFirstNameTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/firstName/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("firstName size must be between 1 and 20"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * lastName Tests
    */
    @Test
    public void failsWhenLastNameMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lastName/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("lastName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenLastNameNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lastName/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("lastName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenLastNameEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lastName/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("lastName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenLastNameTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lastName/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("lastName size must be between 1 and 25"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * mandatedReporterIndicator Tests
    */
    @Test
    public void failsWhenMandatedReporterIndicatorMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/mandatedReporterIndicator/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("mandatedReporterIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMandatedReporterIndicatorNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/mandatedReporterIndicator/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("mandatedReporterIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMandatedReporterIndicatorEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/mandatedReporterIndicator/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("mandatedReporterIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMandatedReporterIndicatorAllWhitespace() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/mandatedReporterIndicator/allWhitespace.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("mandatedReporterIndicator may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * messagePhoneNumber Tests
    */
    @Test
    public void failsWhenMessagePhoneNumberMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/messagePhoneNumber/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("messagePhoneNumber may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMessagePhoneNumberNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/messagePhoneNumber/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("messagePhoneNumber may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * middleInitialName Tests
    */
    @Test
    public void failsWhenMiddleInitialNameMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/middleInitialName/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("middleInitialName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMiddleInitialNameNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/middleInitialName/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("middleInitialName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMiddleInitialNameEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/middleInitialName/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("middleInitialName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenMiddleInitialNameTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/middleInitialName/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("middleInitialName size must be 1"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * namePrefixDescription Tests
    */
    @Test
    public void failsWhenNamePrefixDescriptionMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/namePrefixDescription/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("namePrefixDescription may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenNamePrefixDescriptionNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/namePrefixDescription/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("namePrefixDescription may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenNamePrefixDescriptionEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/namePrefixDescription/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("namePrefixDescription may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenNamePrefixDescriptionTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/namePrefixDescription/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("namePrefixDescription size must be between 1 and 6"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * primaryPhoneNumber Tests
    */
    @Test
    public void failsWhenPrimaryPhoneNumberMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/primaryPhoneNumber/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("primaryPhoneNumber may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenPrimaryPhoneNumberNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/primaryPhoneNumber/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("primaryPhoneNumber may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * stateCodeType Tests
    */
    @Test
    public void failsWhenStateCodeTypeMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/stateCodeType/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("stateCodeType may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStateCodeTypeNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/stateCodeType/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("stateCodeType may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * streetName Tests
    */
    @Test
    public void failsWhenStreetNameMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetName/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStreetNameNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetName/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStreetNameEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetName/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetName may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStreetNameTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetName/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetName size must be between 1 and 40"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * streetNumber Tests
    */
    @Test
    public void failsWhenStreetNumberMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetNumber/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetNumber may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStreetNumberNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetNumber/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetNumber may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStreetNumberEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetNumber/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetNumber may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenStreetNumberTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/streetNumber/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("streetNumber size must be between 1 and 10"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * suffixTitleDescription Tests
    */
    @Test
    public void failsWhenSuffixTitleDescriptionMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/suffixTitleDescription/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("suffixTitleDescription may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenSuffixTitleDescriptionNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/suffixTitleDescription/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("suffixTitleDescription may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenSuffixTitleDescriptionEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/suffixTitleDescription/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("suffixTitleDescription may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenSuffixTitleDescriptionTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/suffixTitleDescription/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("suffixTitleDescription size must be between 1 and 4"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * referralId Tests
    */
    @Test
    public void failsWhenReferralIdMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/referralId/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("referralId may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenReferralIdNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/referralId/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("referralId may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenReferralIdEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/referralId/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("referralId may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenReferralIdTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/referralId/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("referralId size must be between 1 and 10"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * lawEnforcementId Tests
    */
    @Test
    public void successWhenLawEnforcementIdEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lawEnforcementId/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(204)));
    }
    @Test
    public void successWhenLawEnforcementIdNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lawEnforcementId/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(204)));
    }
    @Test
    public void failsWhenLawEnforcementIdTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/lawEnforcementId/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("lawEnforcementId size must be between 0 and 10"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * zipSuffixNumber Tests
    */
    @Test
    public void failsWhenZipSuffixNumberMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/zipSuffixNumber/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("zipSuffixNumber may not be null"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenZipSuffixNumberNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/zipSuffixNumber/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("zipSuffixNumber may not be null"), is(greaterThanOrEqualTo(0)));
    }

    /*
    * countySpecificCode Tests
    */
    @Test
    public void failsWhenCountySpecificCodeMissing() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/countySpecificCode/missing.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("countySpecificCode may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCountySpecificCodeNull() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/countySpecificCode/null.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("countySpecificCode may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCountySpecificCodeEmpty() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/countySpecificCode/empty.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("countySpecificCode may not be empty"), is(greaterThanOrEqualTo(0)));
    }
    @Test
    public void failsWhenCountySpecificCodeTooLong() throws Exception {
        Reporter toCreate = MAPPER.readValue(fixture("fixtures/legacy/Reporter/invalid/countySpecificCode/tooLong.json"), Reporter.class);
        Response response = resources.client().target(ROOT_RESOURCE).request().accept(Api.Version.JSON_VERSION_1.getMediaType()).post(Entity.entity(toCreate, Api.MEDIA_TYPE_JSON_V1));
        assertThat(response.getStatus(), is(equalTo(422)));
        assertThat(response.readEntity(String.class).indexOf("countySpecificCode size must be between 1 and 2"), is(greaterThanOrEqualTo(0)));
    }

    /*
	 * Utils
	 */
    private Reporter validReporter() {
    	return new Reporter(
    			  "ABC123",
    			  "A123",
    			  "ABC",
    			  new Short ((short) 12),
    			  new Short ((short) 34),
    			  false,
    			  "ABC123",
    			  "DEF",
    			  "2000-01-01",
    			  false,
    			  "John",
    			  "Smith",
    			  false,
    			  123,
    			  new BigDecimal(1234567),
    			  "A",
    			  "ABC123",
    			  new BigDecimal(1234567),
    			  123,
    			  new Short ((short)1234),
    			  "ABC STREET",
    			  "123",
    			  "AB",
    			  12345,
    			  "DEF",
    			  "DEF",
    			  new Short ((short)1234),
    			  "AB"
				);
    }
}