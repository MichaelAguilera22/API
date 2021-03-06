package gov.ca.cwds.rest.core;


/**
 * Constants defining information about our API.
 * 
 * 1)  Available Resource
 * 2)  Version numbers and their associated media type
 * 
 * @author CDWS API Team
 */
public class Api {

    /**
     * A {@code String} constant representing {@value #RESOURCE_INTAKE_REFERRAL} api.
     */
    public final static String RESOURCE_INTAKE_REFERRAL = "intake/referrals";


    /**
     * A {@code String} constant representing {@value #RESOURCE_ADDRESS} api.
     */
    public final static String RESOURCE_ADDRESS = "legacy/addresses";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_ALLEGATION} api.
     */
    public final static String RESOURCE_ALLEGATION = "legacy/allegations";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_APPLICATION} api.
     */
    public final static String RESOURCE_APPLICATION = "application";

    /**
     * A {@code String} constant representing {@value #RESOURCE_CLIENT} api.
     */
    public final static String RESOURCE_CLIENT = "legacy/clients";

    /**
     * A {@code String} constant representing {@value #RESOURCE_CROSS_REPORT} api.
     */
    public final static String RESOURCE_CROSS_REPORT = "legacy/crossreports";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_CWS_OFFICE} api.
     */
    public final static String RESOURCE_CWS_OFFICE = "legacy/cwsOffice";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_CWS_OFFICE_ADDESS} api.
     */
    public final static String RESOURCE_CWS_OFFICE_ADDESS = "legacy/cwsOfficeAddresses";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_REFERRAL} api.
     */
    public final static String RESOURCE_REFERRAL = "legacy/referrals";

    /**
     * A {@code String} constant representing {@value #RESOURCE_REFERRAL_CLIENT} api.
     */
    public final static String RESOURCE_REFERRAL_CLIENT = "legacy/referralclients";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_REPORTER} api.
     */
    public final static String RESOURCE_REPORTER = "legacy/reporters";
    
    /**
     * A {@code String} constant representing {@value #RESOURCE_STAFF_PERSON} api.
     */
    public final static String RESOURCE_STAFF_PERSON = "legacy/staffpersons";
    
    /**
    * A {@code String} constant representing {@value #RESOURCE_LAW_ENFORCEMENT} api.
    */
    public final static String RESOURCE_LAW_ENFORCEMENT = "lawEnforcements";
   
    /**
     * A {@code String} constant representing {@value #MEDIA_TYPE_JSON_V1} media type.
     */
    public final static String MEDIA_TYPE_JSON_V1 = "application/gov.ca.cwds.rest-v1+json";

    /**
     * A {@code String} constant representing {@value #MEDIA_TYPE_NOOP} media type.
     */
    public final static String MEDIA_TYPE_NOOP = "application/gov.ca.cwds.rest-noop+json";

	/**
	 *  The enum containing media types for each of the API versions. 
	 */
	public enum Version {
		JSON_VERSION_1(MEDIA_TYPE_JSON_V1), VERSION_NOOP(MEDIA_TYPE_NOOP);
		
	    /**
	     * A {@code String} constant representing {@value #VERSION_1} media type.
	     */
		private String mediaType;
		
		/**
		 * Constructor
		 * 
		 * @param mediaType	The media type for this version of the API
		 */
		private Version(String mediaType) {
			this.mediaType = mediaType;
		}

		/**
		 * @return the mediaType	The mediaType for this version of the API
		 */
		public String getMediaType() {
			return mediaType;
		}
		
		/**
		 * Find the version based on a given mediaType
		 * 
		 * @param mediaType The mediaType of the version being sought
		 * 
		 * @return	The version with the given mediaType, null if not found
		 */
		public static Version findByMediaType(String mediaType) {
		    for(Version v : values()) {
		        if(v.mediaType.equals(mediaType)) return v;
		    }
		    return null; 
		}
	}
}
