package com.gci.invoice.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * This class is the model for the Invoice data.
 */
@Builder
@Data
public class Invoice {
    private String customerName;
    private String sourceAddress;
    private List<InvoiceItem> invoiceItemList;
    private String invoiceDate;
    private String totalCost;
}

