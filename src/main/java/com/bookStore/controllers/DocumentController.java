package com.bookStore.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'DOCUMENT', 'READ')")
    public String readDocument(@PathVariable String id) {
        return "Document " + id + " read successfully";
    }

    @GetMapping("/write/{id}")
    @PreAuthorize("hasPermission(#id, 'DOCUMENT', 'WRITE')")
    public String writeDocument(@PathVariable String id) {
        return "Document " + id + " written successfully";
    }
}