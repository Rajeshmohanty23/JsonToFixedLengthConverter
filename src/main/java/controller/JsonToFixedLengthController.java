package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JsonToFixedLengthController {

    @PostMapping("/json-to-fixed")
    public String convertToJson(@RequestBody Person person) {
        // Serialize the Person object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            // Handle exception
            return "Error during serialization";
        }

        // Convert JSON to fixed-length string
        int fixedLength = 100;  // Adjust as needed
        StringBuilder fixedLengthString = new StringBuilder();

        // Append JSON values to the fixed-length string
        fixedLengthString.append(json);

        // Pad with spaces if necessary
        if (fixedLengthString.length() < fixedLength) {
            int spacesToAdd = fixedLength - fixedLengthString.length();
            for (int i = 0; i < spacesToAdd; i++) {
                fixedLengthString.append(" ");
            }
        } else if (fixedLengthString.length() > fixedLength) {
            // Truncate if needed
            fixedLengthString.setLength(fixedLength);
        }

        return fixedLengthString.toString();
    }
}
