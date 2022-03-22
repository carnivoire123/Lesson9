package ru.gb.lesson9;

public interface PopupMenuItemClicker {
    // void delete(City city, int position);
    // void modify(City city, int position);
    void click (int command /* delete, modify etc */, City city, int position);
}
