package za.co.nico.type;

public class APIResponses {

	public static class APIResponseCodes{
		public static final int SUCCESS=200;
		public static final int CREATED=201;
		public static final int ACCEPTED=202;
		public static final int DELETED=204;
		public static final int INVALID_PARMS=400;
		public static final int FORBIDDEN=403;
		public static final int NOT_FOUND=404;
		public static final int CREATE_FAILED=420;
		public static final int UPDATED_FAILED=421;
		
	}
	public static class APIResponseCodesString{
		public static final String SUCCESS="200";
		public static final String CREATED="201";
		public static final String ACCEPTED="202";
		public static final String DELETED="204";
		public static final String INVALID_PARMS="400";
		public static final String FORBIDDEN="403";
		public static final String NOT_FOUND="404";
		public static final String CREATE_FAILED="420";
		public static final String UPDATED_FAILED="421";
		
	}	
	public static class APIResponseMessage{
		public static final String SUCCESS="Succesfull execution";
		public static final String CREATED="Succesfully Created";
		public static final String ACCEPTED="Accepted for Background Processing";
		public static final String DELETED="Succesfully Deleted";
		public static final String INVALID_PARMS="Invalid parameters provided";
		public static final String NOT_FOUND="Not Found";
		public static final String FORBIDDEN="Forbidden";
		public static final String CREATE_FAILED="Request to Add Failed";
		public static final String UPDATED_FAILED="Request to Update Failed";
		
	}
	

}
