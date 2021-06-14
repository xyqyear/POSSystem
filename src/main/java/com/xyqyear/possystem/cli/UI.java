package com.xyqyear.possystem.cli;

import java.util.Scanner;

import com.xyqyear.possystem.core.POSSystem;

public class UI {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }

    private int readInt() {
        int temp = scanner.nextInt();
        scanner.nextLine();
        return temp;
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private boolean askContinue(String message, String[] choices) {
        while (true) {
            System.out.println(message + "(" + choices[0] + "/" + choices[1] + "):");
            String choice = readLine();
            if (choice.equals(choices[0])) {
                return true;
            } else if (choice.equals(choices[1])) {
                return false;
            } else {
                System.out.println("请输入" + choices[0] + "/" + choices[1] + "!");
                continue;
            }
        }
    }

    private boolean askContinue(String message) {
        return askContinue(message, new String[] { "y", "n" });
    }

    private void run() {
        POSSystem pos = new POSSystem();
        System.out.println("正在初始化（加载产品目录）……");
        pos.startUp();

        while (true) {
            if (askContinue("开始一次销售吗？")) {
                pos.makeNewSale();

                while (true) {
                    System.out.println("请输入id: ");
                    int id = readInt();
                    System.out.println("请输入数量: ");
                    int quantity = readInt();
                    pos.enterItem(id, quantity);

                    if (!askContinue("要继续加购商品吗？")) {
                        break;
                    }
                }

                System.out.println("打钱!");
                pos.makePayment(readInt());
                System.out.println("正在打印单据……");
                pos.finishASale();
            } else {
                System.out.println("正在退出系统……");
                break;
            }
        }
    }
}
