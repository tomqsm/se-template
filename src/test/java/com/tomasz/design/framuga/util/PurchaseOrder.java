package com.tomasz.design.framuga.util;

import java.math.BigDecimal;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = "|", crlf = "UNIX")
public class PurchaseOrder {

    @DataField(pos = 1)
    int amount;
    @DataField(pos = 2, precision = 2)
    BigDecimal price;
    @DataField(pos = 3)
    String name;

    public void setAmount(int i) {
        amount = i;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
