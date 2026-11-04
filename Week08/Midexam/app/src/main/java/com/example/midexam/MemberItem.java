package com.example.midexam;

public class MemberItem {
    private String name;
    private int birthYear;
    private String job;

    public MemberItem(String name, int birthYear, String job) {
        this.name = name;
        this.birthYear = birthYear;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getJob() {
        return job;
    }

    // 한국 나이 계산 (현재 연도 기준: 2026 - 출생연도 + 1)
    public int getAge() {
        return 2026 - birthYear + 1;
    }
}