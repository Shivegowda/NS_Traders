package com.traders.nst.util;

import com.traders.nst.DTO.common.DataDTO;
import com.traders.nst.DTO.common.ResponseDTO;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

public class CommonUtilityFunction {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> ResponseDTO<T> mapToResponseDTO(T t,String message)   {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        DataDTO<T> dataDTO = new DataDTO<>(t);
        responseDTO.setData(dataDTO);
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> mapToResponseDTO(List<T> t, String message)   {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        DataDTO<T> dataDTO = new DataDTO<>(t);
        responseDTO.setData(dataDTO);
        responseDTO.setMessage(message);
        return responseDTO;
    }
    public static <T> ResponseDTO<T> mapToResponseDTO(String message)   {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage(message);
        return responseDTO;
    }
    public static <T> ResponseDTO<T> mapToResponseDTO(Integer errorCode,String message)   {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage(message);
        responseDTO.setErrorCode(errorCode);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> mapError(Integer errorCode,String message)   {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage(message);
        responseDTO.setErrorCode(errorCode);
        return responseDTO;
    }

}
