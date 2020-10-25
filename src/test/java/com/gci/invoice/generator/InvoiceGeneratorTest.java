package com.gci.invoice.generator;

import com.gci.invoice.BaseTestData;
import com.gci.invoice.parser.SmsCdrParser;
import com.gci.invoice.parser.SmsCdrParserImpl;
import com.gci.invoice.service.InvoiceService;
import com.gci.invoice.service.InvoiceServiceImpl;
import freemarker.template.TemplateException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

/**
 * This class handles all test cases for InvoiceGenerator.
 */
public class InvoiceGeneratorTest extends BaseTestData {

    private static final String TENANT_NAME = "Tenant 1";

    @Test
    public void testGenerateInvoices() throws IOException, TemplateException {
        String smsCmdSourceFile = "src/test/resources/SMS_CDR_Test.csv";
        SmsCdrParser smsCdrParser = new SmsCdrParserImpl();
        InvoiceService invoiceService = new InvoiceServiceImpl();
        InvoiceGenerator invoiceGenerator = new InvoiceGeneratorImpl(smsCdrParser, invoiceService, smsCmdSourceFile);

        Assert.assertEquals(Boolean.TRUE, invoiceGenerator.generateInvoices(Collections.singletonList(TENANT_NAME)));
    }
}
