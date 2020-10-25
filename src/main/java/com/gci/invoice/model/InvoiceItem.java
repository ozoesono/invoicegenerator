package com.gci.invoice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * This class is the model for the Invoice Item data.
 */
@Builder
@Data
public class InvoiceItem {
    private String senderAddress;
    private String destinationNumber;
    private String messageStatus;
    private String lengthOfMessage;
    private String dateOfMessage;
    private BigDecimal cost;
}
