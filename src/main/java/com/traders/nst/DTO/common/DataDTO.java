package com.traders.nst.DTO.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO<T> {
    private  T item;
    private List<T> items;


    public DataDTO(T item){
        this.item = item;
    }
    public DataDTO(List<T> items){
        this.items = items;
    }
}
