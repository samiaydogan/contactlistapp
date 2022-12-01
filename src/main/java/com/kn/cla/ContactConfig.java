package com.kn.cla;

import com.kn.cla.entity.Contact;
import com.kn.cla.repository.ContactRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ContactConfig {

    @Value("classpath:data/people.csv")
    Resource resourceFile;


    @Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository) {
        return args -> {
            List<Contact> initialContactList = new ArrayList<Contact>();
            try (
                    InputStreamReader inputStreamReader = new InputStreamReader(resourceFile.getInputStream());
                    Reader reader =  new BufferedReader(inputStreamReader);
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withIgnoreHeaderCase()
                            .withTrim());
            ) {
                Contact contact = null;
                for (CSVRecord csvRecord : csvParser) {
                    String name = csvRecord.get("name");
                    String imageUrl = csvRecord.get("url");
                    contact = new Contact(name, imageUrl);
                    initialContactList.add(contact);
                }
            }
            contactRepository.saveAll(initialContactList);
        };
    }
}
