package com.gci.invoice.generator;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

/**
 * This class is used to generate invoices for customers.
 */
public interface InvoiceGenerator {
    void generateInvoices(List<String> tenantNames) throws IOException, TemplateException;
}
