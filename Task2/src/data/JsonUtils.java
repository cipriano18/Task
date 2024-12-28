package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils<T> {
    
    private String filePath;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public JsonUtils(String filePath) {
        this.filePath = filePath;
    }
    public void save(T t) throws IOException {
        List<T> list = getElements((Class<T>) t.getClass());  
        list.add(t);  
        mapper.writeValue(new File(filePath), list); 
    }

     List<T> getElements(Class<T> clazz) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();  
        }
        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionLikeType(List.class, clazz));
    }
     public void saveList(List<T> list) throws IOException {
         mapper.writeValue(new File(filePath), list); 
     }
}

