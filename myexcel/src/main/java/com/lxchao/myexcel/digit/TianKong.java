package com.lxchao.myexcel.digit;

import java.io.Serializable;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/18
 */
public class TianKong implements Serializable {
    private String 难度;
    private String 建议题目分值;
    private String 建议作答时间;
    private String 认知分类;
    private String 题目用途;
    private String 题目名称;
    private String 题目内容;
    private String 题目提示;
    private String 题目要求;
    private String 答案;

    public String get难度() {
        return 难度;
    }

    public void set难度(String 难度) {
        this.难度 = 难度;
    }

    public String get建议题目分值() {
        return 建议题目分值;
    }

    public void set建议题目分值(String 建议题目分值) {
        this.建议题目分值 = 建议题目分值;
    }

    public String get建议作答时间() {
        return 建议作答时间;
    }

    public void set建议作答时间(String 建议作答时间) {
        this.建议作答时间 = 建议作答时间;
    }

    public String get认知分类() {
        return 认知分类;
    }

    public void set认知分类(String 认知分类) {
        this.认知分类 = 认知分类;
    }

    public String get题目用途() {
        return 题目用途;
    }

    public void set题目用途(String 题目用途) {
        this.题目用途 = 题目用途;
    }

    public String get题目名称() {
        return 题目名称;
    }

    public void set题目名称(String 题目名称) {
        this.题目名称 = 题目名称;
    }

    public String get题目内容() {
        return 题目内容;
    }

    public void set题目内容(String 题目内容) {
        this.题目内容 = 题目内容;
    }

    public String get题目提示() {
        return 题目提示;
    }

    public void set题目提示(String 题目提示) {
        this.题目提示 = 题目提示;
    }

    public String get题目要求() {
        return 题目要求;
    }

    public void set题目要求(String 题目要求) {
        this.题目要求 = 题目要求;
    }

    public String get答案() {
        return 答案;
    }

    public void set答案(String 答案) {
        this.答案 = 答案;
    }
}
