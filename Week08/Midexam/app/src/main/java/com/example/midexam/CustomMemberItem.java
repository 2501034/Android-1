package com.example.midexam;

public class CustomMemberItem {
    private int imageResId;
    private String name;
    private int birthYear;
    private String job;

    public CustomMemberItem(int imageResId, String name, int birthYear, String job) {
        this.imageResId = imageResId;
        this.name = name;
        this.birthYear = birthYear;
        this.job = job;
    }

    public int getImageResId() { return imageResId; }
    public String getName() { return name; }
    public String getJob() { return job; }
    public int getAge() { return 2026 - birthYear + 1; }
}
