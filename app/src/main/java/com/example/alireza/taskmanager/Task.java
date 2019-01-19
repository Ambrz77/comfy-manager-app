package com.example.alireza.taskmanager;

public class Task {
    String subject;
    int year=0,month=0,day=0,hour=0,min=0;//time
    Priority priority;
    String detail;

    public Task(String subject, Priority priority) {
        this.subject = subject;
        this.priority = priority;
    }

    public void setTime(int year, int month, int day, int hour, int min) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
    }

    public void setDetail(String detail) { this.detail = detail; }

    public Priority getPriority() { return priority; }

    public int compareTime(Task task) {
        if (year > task.year) {
            return 1;
        }
        if(year < task.year) {
            return -1;
        }
        if (month > task.month) {
            return 1;
        }
        if (month < task.month) {
            return -1;
        }
        if (day > task.day) {
            return 1;
        }
        if (day < task.day) {
            return -1;
        }
        if (hour > task.hour) {
            return 1;
        }
        if (hour < task.hour) {
            return -1;
        }
        if (min > task.min) {
            return 1;
        }
        if (min < task.min) {
            return -1;
        }

        return 0;
    }
}

class Priority {
    String name;
    private int importance;//1-10

    public Priority(String name, int importance) {
        this.name = name;
        this.importance = importance;
    }

    public int compare(Priority p) {
        if (importance < p.importance) {
            return -1;
        } else {
            return 1;
        }
    }
}
