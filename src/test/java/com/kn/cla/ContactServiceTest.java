package com.kn.cla;

import com.kn.cla.entity.Contact;
import com.kn.cla.repository.ContactRepository;
import com.kn.cla.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;
    private ContactService contactService;

    @Before
    public void setUp() {
        contactService = new ContactService(contactRepository);
    }

    @Test
    public void getAllPagedContacts() {
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Contact contact = new Contact("Bart Simpson", "http://dummy.com");
        Page<Contact> contactPage = new PageImpl<>(Collections.singletonList(contact));
        when(contactRepository.findAll(pageable)).thenReturn(contactPage);
        Page<Contact> contacts = contactRepository.findAll(pageable);
        assertEquals(contacts.getNumberOfElements(), 1);
    }

    @Test
    public void getPagedContactsByKeywords() {
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Contact contact = new Contact("Bart Simpson", "http://dummy.com");
        Contact contact2 = new Contact("Bart Simpson", "http://dummy.com");
        Contact contact3 = new Contact("Bart Simpson", "http://dummy.com");
        Contact contact4 = new Contact("Bart Simpson", "http://dummy.com");
        Contact contact5 = new Contact("Bart Simpson", "http://dummy.com");

        List<Contact> contactList = List.of(contact,contact2,contact3,contact4,contact5);
        Page<Contact> contactPage = new PageImpl<>(contactList);
        when(contactRepository.getByKeyword("Bart", pageable)).thenReturn(contactPage);
        Page<Contact> contacts = contactRepository.getByKeyword("Bart", pageable);
        assertEquals(contacts.getNumberOfElements(), 5);
    }

}
