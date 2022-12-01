package com.kn.cla.service;

import com.kn.cla.entity.Contact;
import com.kn.cla.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Page<Contact> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 5);
        return contactRepository.findAll(pageable);
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact saveItem(Contact contact) {
        return contactRepository.save(contact);
    }

    public Page<Contact> findByKeyword(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 5);
        return contactRepository.getByKeyword(keyword, pageable);
    }

}
