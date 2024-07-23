package com.controlunion.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/

//
//record StandardResponseVoid(String message){
//}
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandardResponse {
    private String message;
    private Object data;

    public StandardResponse(String message) {
        this.message = message;
    }
}
