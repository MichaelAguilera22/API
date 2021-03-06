 package gov.ca.cwds.rest.api.domain.legacy;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.ca.cwds.rest.api.domain.DomainObject;
import gov.ca.cwds.rest.core.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} representing a StaffPerson
 * 
 * @author CWDS API Team
 */
@ApiModel
@InjectLinks(
        { 
        	@InjectLink(value="/{resource}/{id}", rel="self", style=Style.ABSOLUTE, bindings={ @Binding(name="id", value="${instance.id}"), @Binding(name="resource", value=Api.RESOURCE_STAFF_PERSON) } ),
        	@InjectLink(value="/{resource}/{id}", rel="cwsOffice", style=Style.ABSOLUTE, bindings={ @Binding(name="id", value="${instance.cwsOffice}"), @Binding(name="resource", value=Api.RESOURCE_CWS_OFFICE) } ),
        	@InjectLink(value="/{resource}/{id}", rel="cwsofficeAddress", style=Style.ABSOLUTE, bindings={ @Binding(name="id", value="${instance.cwsOfficeAddress}"), @Binding(name="resource", value=Api.RESOURCE_CWS_OFFICE_ADDESS) } )
        })
public class StaffPerson extends DomainObject {

	@NotEmpty
	@Size(min=3, max=3, message="size must be 3")
	@ApiModelProperty(required=true, readOnly=true, value="", example="ABC")
	private String id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=DATE_FORMAT)
	@JsonProperty(value="endDate")
	@gov.ca.cwds.rest.validation.Date(format=DATE_FORMAT, required=false)
	@ApiModelProperty(required=false, readOnly=false, value="yyyy-MM-dd", example="2016-05-22")
	private String endDate;

	@NotEmpty
    @Size(min=1, max=20)
	@ApiModelProperty(required=true, readOnly=false, value="", example="John")
	private String firstName;
	
	@NotEmpty
	@Size(min=1, max=30)
	@ApiModelProperty(required=true, readOnly=false, value="", example="Case Worker")
	private String jobTitle;
	
	@NotEmpty
	@Size(min=1, max=25)
	@ApiModelProperty(required=true, readOnly=false, value="", example="Smith")
	private String lastName;
	
	@NotEmpty
	@Size(min=1, max=1, message="size must be 1")
	@ApiModelProperty(required=true, readOnly=false, value="", example="Q")
	private String middleInitial;
	
	@NotEmpty
	@Size(min=1, max=6)
	@ApiModelProperty(required=true, readOnly=false, value="", example="MR.")
	private String namePrefix;
	
	@NotNull
	@ApiModelProperty(required=true, readOnly=false, example="9165551212")
	private BigDecimal phoneNumber;
	
	@NotNull
	@ApiModelProperty(required=true, readOnly=false, example="123")
	private Integer phoneExt;

	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=DATE_FORMAT)
	@JsonProperty(value="startDate")
	@gov.ca.cwds.rest.validation.Date(format=DATE_FORMAT, required=true)
	@ApiModelProperty(required=true, readOnly=false, value="yyyy-MM-dd", example="2000-01-01")
	private String startDate;
	
	@NotEmpty
	@Size(min=1, max=4)
	@ApiModelProperty(required=true, readOnly=false, value="", example="SR.")
	private String nameSuffix;
	
	@NotNull
	@ApiModelProperty(required=true, readOnly=false)
	private Boolean telecommuterIndicator;

	@NotEmpty
	@Size(min=1, max=10)
	@ApiModelProperty(required=true, readOnly=false, value="IDENTIFIER of CWS_OFFT", example="def")
	private String cwsOffice;
	
	@NotEmpty
	@Size(min=1, max=160)
	@ApiModelProperty(required=true, readOnly=false, value="", example="some free form text")
	private String availabilityAndLocationDescription;

	@NotEmpty
	@Size(min=1, max=4)
	@ApiModelProperty(required=true, readOnly=false, value="", example="9021")
	private String ssrsLicensingWorkerId;
	
	@NotEmpty
	@Size(min=1, max=2)
	@ApiModelProperty(required=true, readOnly=false, value="", example="99")
	private String countyCode;
	
	@NotNull
	@ApiModelProperty(required=true, readOnly=false)
	private Boolean dutyWorkerIndicator;

	@NotEmpty
	@Size(min=1, max=10)
	@ApiModelProperty(required=true, readOnly=false, value="IDENTIFIER of CWSADDRT", example="ghi")
	private String cwsOfficeAddress;
	
	@Size(max=50)
	@ApiModelProperty(required=false, readOnly=false, value="", example="john.q.smith@somedomain.com")
	//NOTE : The legacy system doesn't seem to enforce valid email addresses
	//@Email
	private String emailAddress;


	@JsonCreator
	public StaffPerson(
			@JsonProperty("id") String id,
			@JsonProperty("endDate") String endDate,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("jobTitle") String jobTitle,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("middleInitial") String middleInitial,
			@JsonProperty("namePrefix") String namePrefix,
			@JsonProperty("phoneNumber") BigDecimal phoneNumber,
			@JsonProperty("phoneExt") Integer phoneExt,
			@JsonProperty("startDate") String startDate,
			@JsonProperty("nameSuffix") String nameSuffix,
			@JsonProperty("telecommuterIndicator") Boolean telecommuterIndicator,
			@JsonProperty("cwsOffice") String cwsOffice,
			@JsonProperty("availabilityAndLocationDescription") String availabilityAndLocationDescription,
			@JsonProperty("ssrsLicensingWorkerId") String ssrsLicensingWorkerId,
			@JsonProperty("countyCode") String countyCode,
			@JsonProperty("dutyWorkerIndicator") Boolean dutyWorkerIndicator,
			@JsonProperty("cwsOfficeAddress") String cwsOfficeAddress,
			@JsonProperty("emailAddress") String emailAddress) {
		super();
		this.id = id;
		this.endDate = endDate;
		this.firstName = firstName;
		this.jobTitle = jobTitle;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.namePrefix = namePrefix;
		this.phoneNumber = phoneNumber;
		this.phoneExt = phoneExt;
		this.startDate = startDate;
		this.nameSuffix = nameSuffix;
		this.telecommuterIndicator = telecommuterIndicator;
		this.cwsOffice = cwsOffice;
		this.availabilityAndLocationDescription = availabilityAndLocationDescription;
		this.ssrsLicensingWorkerId = ssrsLicensingWorkerId;
		this.countyCode = countyCode;
		this.dutyWorkerIndicator = dutyWorkerIndicator;
		this.cwsOfficeAddress = cwsOfficeAddress;
		this.emailAddress = emailAddress;
	}
	
	public StaffPerson(gov.ca.cwds.rest.api.persistence.legacy.StaffPerson persistedStaffPerson) {
		this.id = persistedStaffPerson.getId();
		this.endDate = DomainObject.cookDate(persistedStaffPerson.getEndDate());
		this.firstName = persistedStaffPerson.getFirstName();
		this.jobTitle = persistedStaffPerson.getJobTitle();
		this.lastName = persistedStaffPerson.getLastName();
		this.middleInitial = persistedStaffPerson.getMiddleInitial();
		this.namePrefix = persistedStaffPerson.getNamePrefix();
		this.phoneNumber = persistedStaffPerson.getPhoneNumber();
		this.phoneExt = persistedStaffPerson.getPhoneExt();
		this.startDate = DomainObject.cookDate(persistedStaffPerson.getStartDate());
		this.nameSuffix = persistedStaffPerson.getNameSuffix();
		this.telecommuterIndicator = DomainObject.uncookBooleanString(persistedStaffPerson.getTelecommuterIndicator());
		this.cwsOffice = persistedStaffPerson.getCwsOffice();
		this.availabilityAndLocationDescription = persistedStaffPerson.getAvailabilityAndLocationDescription();
		this.ssrsLicensingWorkerId = persistedStaffPerson.getSsrsLicensingWorkerId();
		this.countyCode = persistedStaffPerson.getCountyCode();
		this.dutyWorkerIndicator = DomainObject.uncookBooleanString(persistedStaffPerson.getDutyWorkerIndicator());
		this.cwsOfficeAddress = persistedStaffPerson.getCwsOfficeAddress();
		this.emailAddress = persistedStaffPerson.getEmailAddress();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the middleInitial
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}

	/**
	 * @return the namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @return the phoneNumber
	 */
	public BigDecimal getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the phoneExt
	 */
	public Integer getPhoneExt() {
		return phoneExt;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @return the telecommuterIndicator
	 */
	public Boolean getTelecommuterIndicator() {
		return telecommuterIndicator;
	}

	/**
	 * @return the cwsOffice
	 */
	public String getCwsOffice() {
		return cwsOffice;
	}

	/**
	 * @return the availabilityAndLocationDescription
	 */
	public String getAvailabilityAndLocationDescription() {
		return availabilityAndLocationDescription;
	}

	/**
	 * @return the ssrsLicensingWorkerId
	 */
	public String getSsrsLicensingWorkerId() {
		return ssrsLicensingWorkerId;
	}

	/**
	 * @return the countyCode
	 */
	public String getCountyCode() {
		return countyCode;
	}

	/**
	 * @return the dutyWorkerIndicator
	 */
	public Boolean getDutyWorkerIndicator() {
		return dutyWorkerIndicator;
	}

	/**
	 * @return the cwsOfficeAddress
	 */
	public String getCwsOfficeAddress() {
		return cwsOfficeAddress;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availabilityAndLocationDescription == null) ? 0 : availabilityAndLocationDescription.hashCode());
		result = prime * result
				+ ((countyCode == null) ? 0 : countyCode.hashCode());
		result = prime * result
				+ ((dutyWorkerIndicator == null) ? 0 : dutyWorkerIndicator.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((cwsOffice == null) ? 0 : cwsOffice.hashCode());
		result = prime * result
				+ ((cwsOfficeAddress == null) ? 0 : cwsOfficeAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result
				+ ((namePrefix == null) ? 0 : namePrefix.hashCode());
		result = prime * result + phoneExt;
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result
				+ ((ssrsLicensingWorkerId == null) ? 0 : ssrsLicensingWorkerId.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((nameSuffix == null) ? 0 : nameSuffix.hashCode());
		result = prime * result
				+ ((telecommuterIndicator == null) ? 0 : telecommuterIndicator.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffPerson other = (StaffPerson) obj;
		if (availabilityAndLocationDescription == null) {
			if (other.availabilityAndLocationDescription != null)
				return false;
		} else if (!availabilityAndLocationDescription.equals(other.availabilityAndLocationDescription))
			return false;
		if (countyCode == null) {
			if (other.countyCode != null)
				return false;
		} else if (!countyCode.equals(other.countyCode))
			return false;
		if (dutyWorkerIndicator == null) {
			if (other.dutyWorkerIndicator != null)
				return false;
		} else if (!dutyWorkerIndicator.equals(other.dutyWorkerIndicator))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (cwsOffice == null) {
			if (other.cwsOffice != null)
				return false;
		} else if (!cwsOffice.equals(other.cwsOffice))
			return false;
		if (cwsOfficeAddress == null) {
			if (other.cwsOfficeAddress != null)
				return false;
		} else if (!cwsOfficeAddress.equals(other.cwsOfficeAddress))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleInitial == null) {
			if (other.middleInitial != null)
				return false;
		} else if (!middleInitial.equals(other.middleInitial))
			return false;
		if (namePrefix == null) {
			if (other.namePrefix != null)
				return false;
		} else if (!namePrefix.equals(other.namePrefix))
			return false;
		if (phoneExt != other.phoneExt)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (ssrsLicensingWorkerId == null) {
			if (other.ssrsLicensingWorkerId != null)
				return false;
		} else if (!ssrsLicensingWorkerId.equals(other.ssrsLicensingWorkerId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (nameSuffix == null) {
			if (other.nameSuffix != null)
				return false;
		} else if (!nameSuffix.equals(other.nameSuffix))
			return false;
		if (telecommuterIndicator == null) {
			if (other.telecommuterIndicator != null)
				return false;
		} else if (!telecommuterIndicator.equals(other.telecommuterIndicator))
			return false;
		return true;
	}
	
	
}
