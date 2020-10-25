package com.gci.invoice.parser;

import com.gci.invoice.model.SmsCdr;

import java.util.List;

/**
 * This class is used to parse the csv source file.
 */
public interface SmsCdrParser {
    List<SmsCdr> parseSmsCdr(String filename);
}
