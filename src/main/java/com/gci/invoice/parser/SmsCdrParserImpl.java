package com.gci.invoice.parser;

import com.gci.invoice.commons.Constants;
import com.gci.invoice.commons.Errors;
import com.gci.invoice.commons.Infos;
import com.gci.invoice.model.SmsCdr;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class is the default implementation of {@link SmsCdrParser}.
 */
public class SmsCdrParserImpl implements SmsCdrParser {
    private static final Logger LOGGER = java.util.logging.Logger.getLogger(SmsCdrParserImpl.class.getName());
    private static final String[] HEADERS = {Constants.MSG_ID, Constants.TENANT_NAME, Constants.SENDER_ID,
            Constants.NUMBER, Constants.SENT_DATE, Constants.STATUS, Constants.MSG_LENGTH};

    @Override
    public List<SmsCdr> parseSmsCdr(String filename) {
        try {
            LOGGER.info(String.format(Infos.SMS_CDR_PARSE_START, filename));
            final Reader fileReader = new FileReader(filename);
            Iterable<CSVRecord> records = CSVFormat.EXCEL
                    .withHeader(HEADERS)
                    .withSkipHeaderRecord(Boolean.TRUE)
                    .parse(fileReader);
            LOGGER.info(String.format(Infos.SMS_CDR_PARSE_END, filename));

            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(records.iterator(), Spliterator.ORDERED), Boolean.FALSE)
                    .map(this::createSmsCdr)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.severe(Errors.SMS_CDR_FILE_NOT_FOUND);
            throw new RuntimeException(Errors.SMS_CDR_FILE_NOT_FOUND);
        }
    }

    private SmsCdr createSmsCdr(CSVRecord record) {
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
        return SmsCdr.builder()
                .msgId(Integer.parseInt(record.get(Constants.MSG_ID)))
                .tenantName(record.get(Constants.TENANT_NAME))
                .senderId(record.get(Constants.SENDER_ID))
                .number(record.get(Constants.NUMBER))
                .sentDate(LocalDateTime.parse(record.get(Constants.SENT_DATE), FORMATTER))
                .status(record.get(Constants.STATUS))
                .msgLength(Integer.parseInt(record.get(Constants.MSG_LENGTH)))
                .build();
    }
}
