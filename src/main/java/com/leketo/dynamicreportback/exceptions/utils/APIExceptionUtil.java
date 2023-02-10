package com.leketo.dynamicreportback.exceptions.utils;

import com.leketo.dynamicreportback.exceptions.constant.ErrorConstants;
import com.leketo.dynamicreportback.exceptions.enums.APIExceptionType;
import com.leketo.dynamicreportback.exceptions.errors.APIException;
import org.springframework.http.HttpStatus;

public class APIExceptionUtil {

    public static APIException getUnexpectedInternalException(String intlMsg)
    {
        return APIExceptionUtil.getUnexpectedInternalException(intlMsg, null);
    }

    public static APIException getUnexpectedInternalException(String intlMsg, Exception e)
    {
        return new APIException(
                APIExceptionType.INTERNAL,
                ErrorConstants.ERROR_UNEXPECTED,
                intlMsg,
                false,
                e
        );
    }

    public static APIException getUnexpectedDBException(String intlMsg, Exception e)
    {
        return new APIException(
                APIExceptionType.DATABASE,
                ErrorConstants.ERROR_UNEXPECTED_DB,
                intlMsg,
                false,
                e
        );
    }

    public static APIException getNotFoundException(String intlMsg)
    {
        return APIExceptionUtil.getNotFoundException(intlMsg, null);

    }

    public static APIException getNotFoundException(String intlMsg, Exception e)
    {
        return new APIException(
                APIExceptionType.APPLICATION,
                ErrorConstants.ERROR_ENTITY_NOT_FOUND,
                intlMsg,
                false,
                e
        );
    }

    public static APIException getApplicationExceptionWithUserMessage(String errorCode, String message) {
        return new APIException(APIExceptionType.APPLICATION, errorCode, message, true);
    }

    public static APIException getInternalSecurityException(String errorCode, String message) {
        return new APIException(APIExceptionType.SECURITY, errorCode, message);
    }

    public static int getStatusFromException(APIException ae) {
        String code = ae.getCode();
        if (ae == null)
            return HttpStatus.BAD_REQUEST.value();
        if (ae.getType() == APIExceptionType.DATABASE || ae.getType() == APIExceptionType.APPLICATION) {
            if (ae.getCode().equalsIgnoreCase(ErrorConstants.ERROR_DB_NOT_FOUND))
                return HttpStatus.NOT_FOUND.value();
            else if (ae.getCode().equalsIgnoreCase(ErrorConstants.ERROR_ENTITY_NOT_FOUND))
                return HttpStatus.NOT_FOUND.value();
        }
        return HttpStatus.BAD_REQUEST.value();
    }



}
