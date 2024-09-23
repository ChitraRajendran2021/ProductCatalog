package com.inter.ikea.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductData {
    private static final Logger logger = LoggerFactory.getLogger(ProductData.class);
    
    private List<Product> products = new ArrayList<>();

    public ProductData() {
        // Initialization of mock data
        products.add(new Product(1L, "Mattress", "Bed", "Sprung base incl. mattress pad, 120x200 cm",5500 , "https://www.ikea.com/se/en/images/products/skarer-sprung-base-incl-mattress-pad-medium-firm-tuddal__0858267_pe562570_s5.jpg?f=xxs"));
        products.add(new Product(2L, "Sofa", "Sofa", "3 Seater Sofa", 4500, "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoKCgoKCgsMDAsPEA4QDxYUExMUFiIYGhgaGCIzICUgICUgMy03LCksNy1RQDg4QFFeT0pPXnFlZXGPiI+7u/sBCgoKCgoKCwwMCw8QDhAPFhQTExQWIhgaGBoYIjMgJSAgJSAzLTcsKSw3LVFAODhAUV5PSk9ecWVlcY+Ij7u7+//CABEIALoAugMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAAAQIDBAUGB//aAAgBAQAAAAD7MAAAAAAAAAAAAAAAAAAAAAAACEefZ7xha/P6uZEq6vpbJjDwMHzfby5a1y8zPXMzxzPS+n7m4j59r9zw2tlvM5ePlrspw873mHc+gTg+LbXqPKeevaa5dOlc1a4re85FvqPbj5Hyut1Iy30t6+lntobmLTzectX7Ptx4r5v060Y72LVVrazn+u+oo0Pj2lBKYgi9JL/UvUzGl805eaqCU2tkvnzaG99C3pc35pwehSkQJtky7G3vcjL9I35jF5znRUTNkza1rzX0+eYhSISJATMzIAAAAAAAAAAAAAAAAAAAAAAAAAAAH//EABgBAQEBAQEAAAAAAAAAAAAAAAABAgME/9oACgICEAMQAAAAAAAAAAAAAAAANY3gpJQjHWXeN8zWSUDOrOXbWenMEAIOPoOvIAAM3HQsogACUAAAAAAAAAAAAAAAD//EAD4QAAEDAgMEBwQGCgMAAAAAAAEAAgMEERITIQVRYZIGFTFBU3GRECJS0RQyYnKBkxYkM0JDRFRwobEjgrL/2gAIAQEAAT8A/sjiKLw0EkgAAkncAqnpUKTC80RdA42Dw/sP2lH0xgf20j+cFfpXSHtikam7e2c4ftXDzCd0g2cP33nyaj0noIz9SRyPS+jtpA+/Ep/S8/w6UfiU/pvVMqGMNJFgLd5uo+mFK9gxwvYeGq/SPZniP5Uekezfik5UOktECQA9HpNSfDJ6BHpLRu/dl9An9K6dgsymefMgKl6WTVU7mCmjEbDZxv37gmyB7Q5vYRcLEUD7Cpp4oG4pHho/yVNtU1jZI4YrQnQuPa7yRgjkjypWAxvaWvaQBoU0z0dVUUkhu6GVzDxt3rOO/VB3abprrgFXabnErE+SGlrFVTv1yH7jj/kIEi9kH6oOKu9B9whIAq6udEx2HUqgp30tNDC0YnjV5OgLzqSVRbRMbCwFj8J1AVPVw1Iux2u49qHsle2ONz3djRcratc84iXAvdiNjuHcqGvEjA2VjNNGkNtZQSE2BNwV0njyNtskH8enY8+bbsTRcYrppGoTXIIO1V79yq3H6bFuyXf7Cxmya4XQecJQfpZYhhTpNCb6qAfStqUUZ1bmh7vJgxLH2qTaTYBa1z3NBsAqCvkmnL3hrCSAcNxclUbxKzF3j2bdE3VFaYSQ8R4uU3Kr5ZbQzOkc7A/W57naFU7/AHdN/cqKS7Gl3cumNbTivoAJGY2QOxjdd2ibtan75G+q65pb/tW+q65pb/XC66pfFb6rrmm8RqG2qa2sgVTX075I5Gyiwa7W661pwNJG+qG1oO97fVDasHiM5gjten7pG+q65gItmN9U7asB/it9VsyuhZtKGR0gtheB5lGobIwljuwLFicXbzZSzyGpp6eN5Ai99xB1xO+QXRR88lNUmV7nDGA0n2OAc0gi4IsQto0X0apq6J4uGOLR909iopCAGknE04XeYQnnlIhgcWRN/aTHvO5nzVKzZ1M0gRtxHUlwDiTvJKm+gvOscPIo5qCEDDEw8Q0BOr6FwsWjlBThQyOuGxfiCmTUsQADWfgAEaukcLFo5QVJ9EcfdbCBuIKZU0kYsI2fg0I1NBKLPgYfNoToNmY8TIqcebEypoYxYMbpuaAn1VBIbOiYfNoKdDQh+NjIwL/CpRRTxlj2B4PdYJ8ElBIX04c6DsdH2lo3hQyxsiMz3WjazEXcFs1jnl80n15CXEefctl0xpKKGHvDcT/vHU+wrpVRkPp6xg0cMqTzGrVPE5u1GnEQx8ZJaDYOc3ehKbalZqEztRiWedAVnG/ksw8UJTYXvdZrt+izjvJQkNtCVm+aDwQUXneUZB7v+02Sw1cU15F7LHj7XFbRpmOipcIILqnUA2DrDvHBdHaP6RWQMt7kf/I/yah7CtpUoraKen73M9zg8ahVdO6ZrXhxZNG67TbVrgsW0m6uFM//AKFv+inT1A+vSxk/ZefkjUynspGfjIfks6fT9Tj/ADD8kJ5R/JR/mH5LPqSdKOL8woTVP9JH+afknS1PdSR34yH5LOq/6WHnchNVn+Uh53Iz1PYKSHncmy1ffSwc7gs6s76SDncsyqPZRwc7kJKq5tSQA8XuRmrdP1enH4uKEledcFMD5O+aihqZZmy1EjXYBZjWiwaF0XpMmifUOFnTnT7jU32FVEmBpK28IomVdTFpKbEA/Vu5wBNk99OK1sAcTHkFx1OLHisP8IwUn2uZZFIPi5ll032vVZdLx9VgpuPMsFMPi5lhpuPqsFLuPMsul+1zLLpOPMsmjPxcybBR/b5kKSid4nMm0FEe+TmQ2VQnvm5gq+m2ZSCmtJKHPqomPxnsjdo4hU+y6aTaldSF8gihgp3ssfeJlBxXKpsLI2MYAGtAAG4BN9h7FVsLmOXSKMspagns93/0FRRSTvlqSD77vd8gjDJuKMMm4rJkWRKsiVZEu4rIlWRKsiVCCXcUIZNxTYpNyZG8KNr0zEtt0hqKV9u0ahdF6t1dWVkrvrikpI3+bMQVONEz2FPZiC2rsSHaMEkLy8NeLHCbFM2MIGNjYw4WiwR2a/wyurX+GV1a/wAMrqx3hldWO8Mrqx3hldWO8Mrqx3hldWO8Mrqx3hldWO8Mrq13hldWu8MobPf8BQoH/AU7ZhkaWuBsVsno9BsyWoliMl5y0uxHcmMwpvtsUW37llcFkjcsgblkDcskblk8Fk8Fk8FlcFlDcsobllcFlcFlcFlcFlcFl8EGqxQ/tV//xAAfEQACAgICAwEAAAAAAAAAAAAAARESAmEQUCAhUTH/2gAIAQIBAT8A6VL6URRFEVSKIoiiKoajjFDQmSiUNkkolcQNQzB+z9IIIIKkEFfCyLIstllsstllsstlsfrJWzLJRC77/8QAHhEAAgICAwEBAAAAAAAAAAAAAAECEhFhEBNRUCH/2gAIAQMBAT8A+K5eF5HYzskXl6XkXZ2SLSE0+Jv9SExmGYYkYMGHwmJ5SJrKNmSxYsWMmSx5vmsl4VlorLRWWistFZaKy0VlorLRGLzl/e//2Q=="));
        products.add(new Product(3L, "Tables", "Kitchen Table", "Kitchen extendable table 4 leg teak wood",6500 , "https://www.ikea.com/se/en/images/products/skansnaes-extendable-table-brown-beech-veneer__1322525_pe942201_s5.jpg?f=xxs"));
        products.add(new Product(4L, "Sofa", "Arm Chair", "Arm Chair brown color", 2500, "https://www.ikea.com/se/en/images/products/ekenaeset-armchair-kilanda-light-beige__1179060_pe895831_s5.jpg?f=xxs"));
        
        logger.info("Initialized ProductData with {} products", products.size());
    }

    public List<Product> getAllProducts() {
        return products; // Removed logging from here
    }
}
