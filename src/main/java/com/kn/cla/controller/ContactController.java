package com.kn.cla.controller;

import com.kn.cla.entity.Contact;
import com.kn.cla.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ContactController {


    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public String getAllPages(Model model, String keyword) {
        return getOnePage(model, 1, keyword);
    }

    @GetMapping(value = {"/contacts/{pageNumber}/{keyword}", "/contacts/{pageNumber}/" })
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage,  @PathVariable(value = "keyword", required = false) String keyword) {
        Page<Contact> page = null;
        if(ObjectUtils.isEmpty(keyword)) {
            page = contactService.findPage(currentPage);
        } else {
            page = contactService.findByKeyword(keyword, currentPage);

        }
        //contactService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Contact> contactList = page.getContent();
        if(!ObjectUtils.isEmpty(keyword)) {
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("contactList", contactList);
        return "contact_list";
    }


}
