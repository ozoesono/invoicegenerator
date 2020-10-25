package com.gci.invoice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * This class is the model for the CDR SMS data.
 */
@Builder
@Data
public class SmsCdr {
    private final int msgId;
    private final String tenantName;
    private final String senderId;
    private final String number;
    private final LocalDateTime sentDate;
    private final String status;
    private final int msgLength;
}
