package com.cranesteam.partylist.Domain.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Custom Response Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyResponse implements Serializable {

    private HttpStatus httpStatus;
    private ArrayList<String> bodyList;
}
