package com.springbootdemo.springbootdemo.web;

import com.springbootdemo.springbootdemo.dto.InvoiceDto;
import com.springbootdemo.springbootdemo.model.Invoice;
import com.springbootdemo.springbootdemo.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public Iterable<Invoice> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/invoices/user/{userId}")
    public Iterable<Invoice> findByUserId(@PathVariable String userId) {
        return invoiceService.findByUserId(userId);
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
