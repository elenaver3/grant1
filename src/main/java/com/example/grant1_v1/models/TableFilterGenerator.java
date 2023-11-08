package com.example.grant1_v1.models;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.Predicate;

//Генератор динамических фильтров для таблиц TableView.
public class TableFilterGenerator<T> {
    TableView<T> housesTable;
    FilteredList<T> filteredItems;
    SortedList<T> sortedData;
    HashMap<String, Predicate<T>> map = new HashMap<>();

    public TableFilterGenerator(TableView<T> housesTable, FilteredList<T> filteredItems) {
        this.housesTable = housesTable;
        this.filteredItems = filteredItems;
    }

    public void addNewEqualsFilter(TextField notClearSearchField, String category) {
        //добавление слушателя
        notClearSearchField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            Predicate<T> a = person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String data = "";
                try {
                    //получение геттеров
                    Method fieldGetter = person.getClass().getMethod("get" + category.substring(0, 1).toUpperCase() + category.substring(1));
                    data = fieldGetter.invoke(person).toString();
                } catch (NoSuchMethodException e) {
                    System.out.println("GetterFindError");
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                if (data.toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                return false; // Does not match.
            };
            map.put(category, a);
            filteredItems.setPredicate(lastPredicates());

        }));
    }
    public void addNewEqualsFilter(ChoiceBox<String> notClearSearchField, String category) {
        notClearSearchField.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            Predicate<T> a = person -> {
                if (newValue == null || newValue.isEmpty() || newValue.equals("")) {
                    return true;
                }
                String data = "";
                try {
                    Method fieldGetter = person.getClass().getMethod("get" + category.substring(0, 1).toUpperCase() + category.substring(1));
                    data = fieldGetter.invoke(person).toString();
                } catch (NoSuchMethodException e) {
                    System.out.println("GetterFindError");
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.toLowerCase().contains(newValue.toLowerCase())) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            };
            map.put(category, a);
            filteredItems.setPredicate(lastPredicates());

        }));
    }

    public void setFiltersToTable() {
        sortedData = new SortedList<>(filteredItems);
        housesTable.setItems(sortedData);
    }

    public Predicate<T> lastPredicates() {
        Predicate<T> a = person -> true;
        for (Predicate p : map.values()) {
            a = a.and(p);
        }
        return a;
    }

    public SortedList<T> getSortedData() {
        return sortedData;
    }
}
