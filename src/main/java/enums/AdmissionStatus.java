package enums;

public enum AdmissionStatus
{
	PENDING, RECEIVED, REQUEST_INFO, ACCEPTED, REJECTED;
	
	private final static String PENDING_STRING = "Pending"
			, RECEIVED_STRING = "Received"
			, REQUEST_INFO_STRING = "Request more information"
			, ACCEPTED_STRING = "Accepted"
			, REJECTED_STRING = "Rejected"
			, UNKNOWN_STRING = "Unknown";
	
	@Override
	public String toString()
	{
		switch(this)
		{
			case PENDING: return PENDING_STRING;
			case RECEIVED: return RECEIVED_STRING;
			case REQUEST_INFO: return REQUEST_INFO_STRING;
			case ACCEPTED: return ACCEPTED_STRING;
			case REJECTED: return REJECTED_STRING;
			default: return UNKNOWN_STRING;
		}
	}
}
