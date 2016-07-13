package com.gt.controller;

/**
 * Created by journal on 2016/7/11.
 */
public class Fang {

    //代理费：918
    //天然气安装费用：2780
    //物业费：1180
    //交易服务费：346
    //测绘费：157
    //有限电视：240
    //房地产开发商收取：1000
    //物业维修基金：7498
    //契税：8994
    //首付：139743
    //贷款：310000
    //单价：3898.6
    //
    //

    public static void main(String[] args) {
        int a = 139743 + 310000;
        System.out.println("购买房价总值a：" + a);
        //System.out.println("房价总值：" + (int)(115.3 * 3898.6));
        int b = 918 + 2780 + 1180 + 346 + 157 + 240 + 1000 + 7498 + 8994;
        System.out.println("购买手续花费b：" + b);
        int c = 1838 * 20;
        System.out.println("当前缴纳贷款c：" + c);
        System.out.println("当前总花费(a+b+c)："+(a+b+c));

        int a1 = (int)(4300 * 115.3);
        System.out.println("当前房价估值：" + a1 );
        System.out.println("当前房价估值 + 总花费：" + (a1 + b + c));

        System.out.println();
    }

}
