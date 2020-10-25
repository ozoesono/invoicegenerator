package com.gci.invoice.service;

import com.gci.invoice.commons.Constants;
import com.gci.invoice.model.Invoice;
import com.gci.invoice.model.InvoiceItem;
import com.gci.invoice.model.SmsCdr;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the default implementation of {@link InvoiceService}.
 */
public class InvoiceServiceImpl implements InvoiceService {

    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);

    @Override
    public Invoice createInvoice(List<SmsCdr> smsCdrList) {
        List<InvoiceItem> invoiceItems = smsCdrList.stream().map(smsCdr ->
                InvoiceItem.builder()
                        .senderAddress(smsCdr.getSenderId())
                        .lengthOfMessage(String.valueOf(smsCdr.getMsgLength()))
                        .dateOfMessage(smsCdr.getSentDate().format(dateTimeFormatter))
                        .destinationNumber(smsCdr.getNumber())
                        .messageStatus(smsCdr.getStatus())
                        .cost(computeCost(smsCdr))
                        .build()).collect(Collectors.toList());

        return Invoice.builder()
                .invoiceItemList(invoiceItems)
                .customerName(smsCdrList.get(0).getTenantName())
                .sourceAddress(smsCdrList.get(0).getSenderId())
                .invoiceDate(LocalDateTime.now().format(dateTimeFormatter))
                .totalCost(getTotalInvoiceCost(invoiceItems).toPlainString())
                .build();
    }

    private BigDecimal computeCost(SmsCdr smsCdr) {
        if (smsCdr.getStatus().equals(Constants.OK)) {
            int countryCost = getCountryCodeCost(smsCdr.getNumber());
            return BigDecimal.valueOf(smsCdr.getMsgLength())
                    .multiply(BigDecimal.valueOf(countryCost));
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal getTotalInvoiceCost(List<InvoiceItem> invoiceItemList) {
        return invoiceItemList.stream()
                .map(InvoiceItem::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int getCountryCodeCost(String senderId) {
        if (Strings.isNotBlank(senderId)) {
            if (senderId.startsWith(Constants.FORTY_FOUR)) {
                return Constants.TWO;
            } else if (senderId.startsWith(Constants.THIRTY_THREE)) {
                return Constants.FIVE;
            } else if (senderId.startsWith(Constants.NINETY_ONE)) {
                return Constants.FOUR;
            }
        }
        return 0;
    }
}
