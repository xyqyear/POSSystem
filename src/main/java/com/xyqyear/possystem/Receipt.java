package com.xyqyear.possystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Receipt {
    private String str = "";

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

    private void transToString(Sale s) {
        str += "-----------------------\n";
        str += "RECEIPT START\n";
        str += lineItemsToString(s.getLineItems());
        str += getTotalString(s);
    }

    private void writeToTxt() {
        try {
            FileWriter fileWriter = new FileWriter("receipt.txt");
            fileWriter.append(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(Sale s) {
        transToString(s);
        System.out.println(str);
        writeToTxt();
    }
}
