package com.tafh.ecommerce.restful.util;

import com.tafh.ecommerce.restful.model.StatusResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusHelper {

    public List<StatusResponse> getStatusResponse(int id) {
        List<StatusResponse> statusResponses = new ArrayList<>();
        if (id == 0) {
            statusResponses.add(createStatusResponse(0, "SUCCESS"));
        } else {
            statusResponses.add(createStatusResponse(1, "FAILED"));
        }
        return statusResponses;
    }

    private StatusResponse createStatusResponse(Integer id, String name) {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setId(id);
        statusResponse.setName(name);
        return statusResponse;
    }

}
