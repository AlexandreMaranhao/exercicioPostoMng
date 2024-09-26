

package com.aluraAPI.aluraAPI.domain.product.business.validation;

import com.aluraAPI.aluraAPI.domain.product.dto.ProductRegisterDto;

public interface ProductValidation {

    void validate(ProductRegisterDto inputedData);
}
