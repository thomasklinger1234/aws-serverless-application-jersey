package webapi.api.impl;

import java.util.Map;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyResourceRecord {
    public static String PK_ATTRIBUTE_NAME = "pk";
    public static String SK_ATTRIBUTE_NAME = "sk";

    private String pk;
    private String sk;

    /**
     * Construct the record from a map of DynamoDB {@link AttributeValue}.
     *
     * @param record a map of DynamoDB {@link AttributeValue}
     */
    public MyResourceRecord(final Map<String, AttributeValue> record) {
        if(record.containsKey(PK_ATTRIBUTE_NAME)) {
            this.pk = record.get(PK_ATTRIBUTE_NAME).s();
        } else {

        }

        if(record.containsKey(SK_ATTRIBUTE_NAME)) {
            this.sk = record.get(SK_ATTRIBUTE_NAME).s();
        } else {
            
        }
    }


    /**
     * Convert Record to a map of DynamoDB {@link AttributeValue}.
     *
     * @return a map of DynamoDB {@link AttributeValue}
     */
    public Map<String, AttributeValue> toAttributeMap() {
        Map<String, AttributeValue> attributeMap = new HashMap<>();

        if(pk != null) {
            attributeMap.put(PK_ATTRIBUTE_NAME, AttributeValue
                .builder()
                .s(this.pk)
                .build());
        }

        if(sk != null) {
            attributeMap.put(SK_ATTRIBUTE_NAME, AttributeValue
                .builder()
                .s(this.sk)
                .build());
        }

        return attributeMap;
    }
}
