package edu.kravchenko.infohandling.validator.impl;

import edu.kravchenko.infohandling.validator.TextFileValidator;

import java.io.File;

public class TextFileValidatorImpl implements TextFileValidator {
    @Override
    public boolean isValidFile(String filePath) {
        if (filePath == null) {
            return false;
        }
        File file = new File(filePath);
        return file.isFile() && file.length() != 0;
    }
}
