package com.ejaisoft.model;

import java.time.LocalDateTime;

public class Request {
    private int requestId;
    private Student studentId;
    private RequestStatus status;
    private LocalDateTime requestedAt;
    private RequestType requestType;
}
