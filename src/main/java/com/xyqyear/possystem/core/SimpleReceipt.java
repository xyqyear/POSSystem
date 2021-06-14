package com.xyqyear.possystem.core;

import java.util.List;

public class SimpleReceipt implements IReceipt {
    private static String lineItemsToString(List<SaleLineItem> lineitems) {
        String s = new String();
        s += "品名\t零售价\t数量\t金额\n";
        for (SaleLineItem li : lineitems) {
            ProductDescription description = li.getDescription();
            s += description.getName() + "\t" + description.price + "\t" + li.getQuantity() + "\t" + li.getSubtotal()
                    + "\n";
        }
        return s;
    }

    private static String getTotalString(Sale s) {
        String st = new String();
        st += "价格: " + s.getTotal() + "\n";
        st += "付款: " + s.getCash() + "\n";
        st += "找零: " + s.getBalance();
        return st;
    }

    @Override
    public String saleToString(Sale s) {
        String str = new String();
        str += "-----------------------\n";
        str += "RECEIPT START\n";
        str += lineItemsToString(s.getLineItems());
        str += getTotalString(s);

        return str;
    }
}
