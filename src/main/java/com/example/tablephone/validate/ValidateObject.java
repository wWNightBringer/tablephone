package com.example.tablephone.validate;

import com.example.tablephone.repository.ValidatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ValidateObject {
    private List<ValidatorRepository> repositoryList;
    private ValidateForm validateForm;

    @Autowired
    public ValidateObject(ValidateForm validateForm) {
        repositoryList = new ArrayList<>();
        this.validateForm = validateForm;
        repositoryList.add(validateForm);
    }

    public boolean checkForm(Object object) throws IllegalAccessException {
        if (Objects.nonNull(object)) {
            for (ValidatorRepository repository : repositoryList) {
                repository.validator(object);
            }
        }
        return validateForm.getListResults().contains(false);
    }

}
