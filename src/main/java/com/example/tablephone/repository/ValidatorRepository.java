package com.example.tablephone.repository;

import java.lang.reflect.Field;
import java.util.List;

public interface ValidatorRepository {
    void validator(Object object) throws IllegalAccessException;
    void validatorEngine(Field field,Object object) throws IllegalAccessException;
}
