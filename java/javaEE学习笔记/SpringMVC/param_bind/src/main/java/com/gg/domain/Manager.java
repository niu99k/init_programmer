package com.gg.domain;

import lombok.Data;

@Data
public class Manager {
private int id;

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                '}';
    }
}
