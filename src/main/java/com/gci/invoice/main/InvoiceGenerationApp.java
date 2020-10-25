package com.gci.invoice.main;

import com.gci.invoice.commons.Errors;
import com.gci.invoice.commons.Infos;
import com.gci.invoice.generator.InvoiceGenerator;
import com.gci.invoice.generator.InvoiceGeneratorImpl;
import com.gci.invoice.parser.SmsCdrParser;
import com.gci.invoice.parser.SmsCdrParserImpl;
import com.gci.invoice.service.InvoiceService;
import com.gci.invoice.service.InvoiceServiceImpl;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class InvoiceGenerationApp {
    private static final Logger LOGGER = java.util.logging.Logger.getLogger(InvoiceGenerationApp.class.getName());
    private static final String SMS_CDR_FILE = "src/main/resources/SMS_CDR.csv";
    private static final String INVOICE_OUTPUT_FOLDER = "src/main/out";

    @SneakyThrows
    public static void main(String[] args) {
        LOGGER.info(Infos.STARTING_APPLICATION);
        // Initialise various services, create the InvoiceGenerator and generate the customer invoices
        SmsCdrParser smsCdrParser = new SmsCdrParserImpl();
        InvoiceService invoiceService = new InvoiceServiceImpl();
        InvoiceGenerator invoiceGenerator = new InvoiceGeneratorImpl(smsCdrParser, invoiceService, SMS_CDR_FILE);
        invoiceGenerator.generateInvoices(getTenantNames(args));
        LOGGER.info(String.format(Infos.INVOICES_GENERATED, INVOICE_OUTPUT_FOLDER));
    }

    private static List<String> getTenantNames(String[] tenantNames) {
        if (tenantNames.length < 1) {
            LOGGER.severe(Errors.INVALID_ARGUMENTS);
            System.exit(0);
        }
        return Arrays.asList(tenantNames);
    }
}
