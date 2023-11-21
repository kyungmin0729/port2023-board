package org.kyungmin0729.models.file;

import org.kyungmin0729.commons.Utils;
import org.kyungmin0729.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {

    public FileNotFoundException() {
        super(Utils.getMessage("NotFound.file", "error"), HttpStatus.NOT_FOUND);
    }
}