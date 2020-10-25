package com.gci.invoice.parser;

import com.gci.invoice.model.SmsCdr;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * This class handles all test cases for SmsCdrParser.
 */
public class SmsCdrParserTest {
    private SmsCdrParser smsCdrParser;
    private final static String smsCdrFileLocation = "src/test/resources/SMS_CDR_Test.csv";

    @Test
    public void testCanParseSmsCdrFile() throws IOException {
        List<SmsCdr> smsCdrList = smsCdrParser.parseSmsCdr(smsCdrFileLocation);
        Assert.assertNotNull(smsCdrList);
        Assert.assertEquals(5, smsCdrList.size());
    }

    @Before
    public void before() {
        smsCdrParser = new SmsCdrParserImpl();
    }

}
