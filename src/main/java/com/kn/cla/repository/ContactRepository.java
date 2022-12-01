package com.kn.cla.repository;

import com.kn.cla.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "Select c from Contact c where c.contactName like %?1%")
    Page<Contact> getByKeyword(String keyword, Pageable pageable);

}
