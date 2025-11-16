package com.flightBookingSys.central_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseContent {
    List<ResponsePart> parts;
}
