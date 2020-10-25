package com.gci.invoice.service;

import com.gci.invoice.model.Invoice;
import com.gci.invoice.model.SmsCdr;

import java.util.List;

/**
 * This class is the service class for the Invoice model.
 */
public interface InvoiceService {
    Invoice createInvoice(List<SmsCdr> smsCdrList);
}
