package jumba.tec.muanaku.utils;

public interface ValidationMessages {
     String THE_MINIMUM_VALUE_FOR_ALIVE_QUANTITY_SHOULD_BE_1 = "The minimum value for alive quantity should be 1";
     String THE_ENTRANCE_DATE_IS_REQUIRED = "The entranceDate is required";
     String THE_EXPECTED_EXIT_DATE_IS_REQUIRED = "The expectedExitDate is required";
     String THE_PLUCKED_PRICE_IS_REQUIRED = "The pluckedPrice is required";
     String PLUCKED_PRICE_SHOULD_BE_GREATER_THAN_ZERO = "pluckedPrice should be greater than zero";
     String THE_UN_PLUCKED_PRICE_IS_REQUIRED = "The unPluckedPrice is required";
     String UN_PLUCKED_PRICE_SHOULD_BE_GREATER_THAN_ZERO = "unPluckedPrice should be greater than zero";
     String THE_COMPANY_ID_IS_REQUIRED = "The companyId is required";

     String THE_CLIENT_NAME_IS_REQUIRED = "The client Name is required";
     String THE_NAME_LENGTH_SHOULD_BE_LESS_OR_EQUAL_TO_100 = "The name length should be less or equal to 100";
     String INVALID_PHONE_NUMBER = "Invalid phone number";
     String THE_CONTACT_IS_REQUIRED = "The contact is required";
     String INVALID_EMAIL = "Invalid email";
}
