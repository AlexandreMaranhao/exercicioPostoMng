

package com.aluraAPI.aluraAPI.domain.product.business.validation;

import com.aluraAPI.aluraAPI.domain.product.dto.DetailProductDto;

public interface ProductValidation {

    void validate(DetailProductDto inputedData);
}
