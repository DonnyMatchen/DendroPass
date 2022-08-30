package com.donny.dendropass.json;

public interface ExportableToJson {
    JsonItem export() throws JsonFormattingException;
}
