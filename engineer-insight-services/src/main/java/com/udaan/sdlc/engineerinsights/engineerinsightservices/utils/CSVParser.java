package com.udaan.sdlc.engineerinsights.engineerinsightservices.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class CSVParser {
    private static final CsvMapper mapper = new CsvMapper();
    public static <T> List<T> read(Class<T> engineerDetails, InputStream stream) throws IOException {
        mapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader reader = mapper.readerFor(engineerDetails).with(schema);
               // System.out.println("read details---"+reader.<T>readValues(stream).readAll());
        return reader.<T>readValues(stream).readAll();
    }
}

