package com.better.eat.domain.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Administrator
 */
@Data
@Document("be_user")
public class UserDocument {
    /**
     * user id
     */
    private String id ;
    /**
     * user name
     */
    private String name;
    /**
     * user age
     */
    private int age;
    /**
     * user height
     */
    private Double height;
    /**
     * user weight
     */
    private Double weight;
    /**
     * phone number (unique)
     */
    private long phoneNumber;
    /**
     * user gender
     * true-man
     * false-woman
     */
    private Boolean gender;
    /**
     * status for isDelete
     * true -deleted
     * false -none
     */
    private Boolean isDel;


}
