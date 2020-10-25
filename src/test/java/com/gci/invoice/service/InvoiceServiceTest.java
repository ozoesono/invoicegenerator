package com.gci.invoice.service;

import com.gci.invoice.BaseTestData;
import com.gci.invoice.model.Invoice;
import com.gci.invoice.model.SmsCdr;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * This class handles all test cases for InvoiceService.
 */
public class InvoiceServiceTest extends BaseTestData {
    @Test
    public void testCreateInvoice() {
        InvoiceService invoiceService = new InvoiceServiceImpl();
        List<SmsCdr> smsCdrList = createSmsCdrList();
        Invoice invoice = invoiceService.createInvoice(smsCdrList);
        Assert.assertNotNull(invoice);
        Assert.assertEquals("Tenant 1", invoice.getCustomerName());
        Assert.assertEquals(3, invoice.getInvoiceItemList().size());
    }
}
