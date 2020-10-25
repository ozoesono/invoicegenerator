package com.gci.invoice.generator;

import com.gci.invoice.commons.Errors;
import com.gci.invoice.commons.Infos;
import com.gci.invoice.model.Invoice;
import com.gci.invoice.model.SmsCdr;
import com.gci.invoice.parser.SmsCdrParser;
import com.gci.invoice.service.InvoiceService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class is the default implementation of {@link InvoiceGenerator}.
 */
@AllArgsConstructor
public class InvoiceGeneratorImpl implements InvoiceGenerator {
    private static final Logger LOGGER = java.util.logging.Logger.getLogger(InvoiceGeneratorImpl.class.getName());
    private final SmsCdrParser smsCdrParser;
    private final InvoiceService invoiceService;
    private final String smsCdrSourceFile;
    private static final String INVOICE_TEMPLATE_LOCATION = "template/invoice.ftl";
    private static final String INVOICE_OUTPUT_FILE = "src/main/out/Invoice-%s.html";
    private static final String INVOICE_PLACEHOLDER = "invoice";

    @Override
    public boolean generateInvoices(List<String> tenantNames) throws IOException, TemplateException {
        List<SmsCdr> smsCdrList = smsCdrParser.parseSmsCdr(smsCdrSourceFile);
        LOGGER.info(Infos.INVOICE_GENERATION_START);
        // Generate invoice for every tenant
        for (String tenantName : tenantNames) {
            List<SmsCdr> tenantSmsCdrList = smsCdrList.stream()
                    .filter(smsCdr -> smsCdr.getTenantName().equals(tenantName))
                    .collect(Collectors.toList());
            if (tenantSmsCdrList.size() < 1) {
                LOGGER.severe(String.format(Errors.CUSTOMER_DOES_NOT_EXIST, tenantName));
            } else {
                generateInvoice(tenantSmsCdrList);
            }
        }
        LOGGER.info(Infos.INVOICE_GENERATION_END);
        return true;
    }

    private void generateInvoice(List<SmsCdr> smsCdrList) throws IOException, TemplateException {
        Invoice invoice = invoiceService.createInvoice(smsCdrList);
        String fileName = String.format(INVOICE_OUTPUT_FILE, invoice.getCustomerName());
        Path newFilePath = Paths.get(fileName);
        if (newFilePath.toFile().exists()) {
            newFilePath.toFile().delete();
        }
        Files.createFile(newFilePath);
        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            Configuration cfg = getConfiguration();
            Map<String, Object> templateVariables = new HashMap<>();
            templateVariables.put(INVOICE_PLACEHOLDER, invoice);
            Template template = cfg.getTemplate(INVOICE_TEMPLATE_LOCATION);
            template.process(templateVariables, fileWriter);
        }
    }

    private Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.UK);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }
}
