package in.itechvalley.topmoviesoffline.utils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import in.itechvalley.topmoviesoffline.utils.constants.StatusCodes;

public class NetworkHelper
{
    public static int getNetworkErrorCode(Throwable throwable)
    {
        if (throwable instanceof UnknownHostException)
        {
            return StatusCodes.ERROR_CODE_UNKNOWN_HOST_EXCEPTION;
        }
        else if (throwable instanceof SocketTimeoutException)
        {
            return StatusCodes.ERROR_CODE_SOCKET_TIMEOUT;
        }
        else
        {
            return StatusCodes.ERROR_CODE_FAILURE_UNKNOWN;
        }
    }

    public static String getNetworkErrorMessage(int statusCode)
    {
        switch (statusCode)
        {
            case StatusCodes.STATUS_CODE_SUCCESS:
            {
                return null;
            }

            case StatusCodes.ERROR_CODE_RESPONSE_FAILED:
            {
                return "Failed to load Voters List. Please hit refresh. \uD83E\uDD10";
            }

            case StatusCodes.ERROR_CODE_RESPONSE_BODY_NULL:
            {
                return "Server Error Occurred. Please hit refresh or try again. \uD83E\uDD10";
            }

            case StatusCodes.ERROR_CODE_UNKNOWN_HOST_EXCEPTION:
            {
                return "Failed to load Voters List. Please check your Internet connection and try again. \uD83D\uDCE1";
            }

            case StatusCodes.ERROR_CODE_SOCKET_TIMEOUT:
            {
                return "Request Timeout. Please check your Network Connectivity and try again. \u23F3";
            }

            case StatusCodes.ERROR_CODE_FAILURE_UNKNOWN:
            {
                return "Some error Occurred. Please try again. \uD83D\uDE15";
            }

            default:
            {
                return null;
            }
        }
    }
}
