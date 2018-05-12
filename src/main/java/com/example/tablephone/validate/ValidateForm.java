package com.example.tablephone.validate;

import com.example.tablephone.annotation.Form;
import com.example.tablephone.repository.ValidatorRepository;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class ValidateForm implements ValidatorRepository {
    private List<Boolean> listResults;
    private boolean flag;

    public ValidateForm() {
        listResults = new ArrayList<>();
    }

    @Override
    public void validator(Object object) throws IllegalAccessException {
        if (Objects.nonNull(object)) {
            Class oClass = object.getClass();
            Field[] field = oClass.getDeclaredFields();
            for (Field f : field) {
                validatorEngine(f, object);
            }
        }
    }

    @Override
    public void validatorEngine(Field field, Object object) throws IllegalAccessException {
        Annotation[] annotation = field.getAnnotations();
        for (Annotation a : annotation) {
            flag = false;
            if (a.annotationType() == Form.class) {
                Form form = (Form) field.getAnnotation(a.annotationType());
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object value = field.get(object);
                if (Objects.nonNull(value)) {
                    Pattern regex = Pattern.compile(form.value());
                    if (!regex.matcher((CharSequence) value).matches()) {
                        flag = false;
                        listResults.add(flag);
                    } else {
                        flag = true;
                        listResults.add(flag);
                    }

                }
            }

        }
    }

    public List<Boolean> getListResults() {
        return listResults;
    }
}
