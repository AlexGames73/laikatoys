package com.tripplea.laikatoys.catalog.Model;

import java.util.HashSet;

public class FiltersModel {

    private int startPrice;
    private int endPrice;

    private boolean isTransport;
    private boolean isMebel;
    private boolean isKind;
    private boolean isOther;

    public FiltersModel() {
        startPrice = 0;
        endPrice = 10000;
        isTransport = true;
        isMebel = true;
        isKind = true;
        isOther = true;
    }

    public boolean containsFilter(String type){
        if (type == "Транспорт"){
            return isTransport;
        }
        if (type == "Мебель"){
            return isMebel;
        }
        if (type == "Для маленьких"){
            return isKind;
        }
        if (type == "Остальное"){
            return isOther;
        }
        return true;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public boolean isTransport() {
        return isTransport;
    }

    public void setTransport(boolean transport) {
        isTransport = transport;
    }

    public boolean isMebel() {
        return isMebel;
    }

    public void setMebel(boolean mebel) {
        isMebel = mebel;
    }

    public boolean isKind() {
        return isKind;
    }

    public void setKind(boolean kind) {
        isKind = kind;
    }

    public boolean isOther() {
        return isOther;
    }

    public void setOther(boolean other) {
        isOther = other;
    }
}
