package com.apolo.spring.database;

import java.util.Date;
import java.util.List;

/**
 * Esta clase se utiliza para crear criterios  de búsqueda  personalizados
 * La implementación se toma de https://medium.com/backend-habit/spring-jpa-make-dynamic-where-using-predicate-and-criteria-84550dfaa182
 */
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;
    private Date date;
    private String[] joins;


    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public SearchCriteria(String key, Object value, SearchOperation operation, Date date) {

        this.key = key;
        this.value = value;
        this.operation = operation;
        this.date = date;



        if(value.getClass().isAssignableFrom(java.sql.Timestamp.class)){
            date = new Date(date.getTime());
            this.date = date;
            this.value = date;
        }

    }

    //la lista de joins se debe enviar en el orden de jeraquia correspondient al modelo
    public SearchCriteria( String[] joins, SearchOperation operation) {
        this.joins = joins;
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String[] getJoins() {
        return joins;
    }

    public void setJoins(String[] joins) {
        this.joins = joins;
    }
}
