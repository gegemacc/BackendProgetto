package my.ecommerce.dtos;


import lombok.Data;

@Data
public class OrderDTO {

    private String phone;
    private String state;
    private String region;
    private String city;
    private String street;

}
