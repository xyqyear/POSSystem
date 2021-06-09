package com.xyqyear.possystem;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        POSSystem pos = new POSSystem();
        System.out.println("正在初始化（加载产品目录）……");
        pos.startUp();

        while (true) {
            System.out.println("要开始一次销售吗？(y/n): ");
            String choice = scanner.nextLine();
            boolean continueFlag = false;
            if (choice.equals("y")) {
                continueFlag = true;
            } else if (choice.equals("n")) {
                System.out.println("正在退出系统……");
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("请输入y/n!");
                continue;
            }

            if (continueFlag) {
                pos.makeNewSale();

                while (true) {
                    System.out.println("请输入id: ");
                    int id = scanner.nextInt();
                    System.out.println("请输入数量: ");
                    int quantity = scanner.nextInt();
                    pos.enterItem(id, quantity);

                    boolean continueFlag1 = false;
                    while (true) {
                        System.out.println("要继续加购商品吗？(y/n)");
                        String choice1 = scanner.nextLine();
                        if (choice1.equals("y")) {
                            continueFlag1 = true;
                            break;
                        } else if (choice1.equals("n")) {
                            break;
                        } else {
                            System.out.println("请输入y/n!");
                        }
                    }

                    if (continueFlag1) {
                        continue;
                    } else {
                        break;
                    }
                }

                System.out.println("打钱!");
                int paymentAmount = scanner.nextInt();
                pos.makePayment(paymentAmount);
                System.out.println("正在打印单据……");
                pos.finishASale();
            }
        }
    }
}
