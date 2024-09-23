

package com.aluraAPI.aluraAPI.domain.product.business.validation;

import com.aluraAPI.aluraAPI.domain.product.dto.RegisterProductDto;

public interface ProductValidation {

    void validate(RegisterProductDto inputedData);
}
